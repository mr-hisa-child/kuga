package infrastructure.datasource.score;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.model.score.ScoreRepository;
import webapp.kuga.domain.model.Score;

@Repository
public class ScoreRepositoryImpl implements ScoreRepository {
	@Autowired
	private ScoreJpaRepository repository;

	@Override
	public List<Score> findByActivityId(String activityId) {

		return repository.findByActivityId(activityId)
				.stream()
				.map(entity -> toDomainEntity(entity))
				.collect(Collectors.toList());
	}

	@Override
	public List<Score> findByTeamIdAndYear(String teamId, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	private Score toDomainEntity(ScoreEntity entity) {
		Score score = new Score();
		score.setGoal(entity.getGoal());
		score.setId(entity.getId());
		score.setAssist(entity.getAssist());
		score.setWin(entity.getWin());
		score.setMemberId(entity.getMemberId());
		return score;
	}
}
