package webapp.kuga.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import webapp.kuga.domain.model.Activity;
import webapp.kuga.domain.model.Score;
import webapp.kuga.domain.repository.score.ScoreRepository;

@Service
public class ScoreService {
	@Autowired
	private ScoreRepository scoreRepository;

	public Score find(String id) {
		return scoreRepository.select(id);
	}

	public List<Score> findByActivityId(String activityId) {
		return scoreRepository.selectByActivityId(activityId);
	}

	public List<Score> findWithMemberByActivityId(String activityId) {
		return scoreRepository.selectWithMemberByActivityId(activityId);
	}

	public List<Score> findByTeamIdAndYear(String teamId, int year) {
		return null;
	}

	public void create(Score score) {
		scoreRepository.insert(score);
	}

	public void update(Score score) {
		scoreRepository.update(score);
	}

	public void remove(String id) {
		scoreRepository.delete(id);
	}

	public List<Score> attend(Activity activity, List<String> attendMemberList) {
		List<Score> scoreList = findByActivityId(activity.getId())
				.stream()
				.collect(Collectors.toList());

		List<Score> newScoreList = Lists.newArrayList();
		List<String> memberList = Lists.newArrayList();

		scoreList.parallelStream()
				.forEach(score -> {
					memberList.add(score.getMemberId());
					if (attendMemberList.contains(score.getMemberId())) {
						newScoreList.add(score);
					} else {
						remove(score.getId());
					}
				});

		attendMemberList.parallelStream()
				.filter(memberId -> !memberList.contains(memberId))
				.forEach(memberId -> {
					Score score = new Score();
					score.setActivityId(activity.getId());
					score.setMemberId(memberId);
					score.setTeamId(activity.getTeamId());
					create(score);
					newScoreList.add(score);
				});

		return newScoreList;
	}
}