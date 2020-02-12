package webapp.kuga.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.kuga.domain.model.Activity;
import webapp.kuga.domain.repository.ActivityRepository;
import webapp.kuga.domain.repository.ScoreRepository;

@Service
public class ActivityService {
	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private ScoreRepository scoreRepository;

	public Activity find(String id) {
		return activityRepository.findById(id).orElse(null);
	}

	public List<Activity> findByTeamId(String teamId) {
		return activityRepository.findByTeamId(teamId);
	}

	public List<Activity> findByTeamIdAndYear(String teamId, int year) {
		return activityRepository.findByTeamIdAndYear(teamId, year);
	}

	public void create(Activity activity) {
		activityRepository.save(activity);
	}

	public void remove(String id) {

		activityRepository.delete(id);
	}
}