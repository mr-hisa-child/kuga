package webapp.kuga.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.kuga.domain.model.Activity;
import webapp.kuga.domain.repository.activity.ActivityRepository;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public Activity find(String id) {
        return activityRepository.select(id);
    }

    public List<Activity> findByTeamId(String teamId) {
        return activityRepository.selectByTeamId(teamId);
    }

    public void create(Activity activity) {
        activityRepository.insert(activity);
    }

    public void update(Activity activity) {
        activityRepository.update(activity);
    }

    public void remove(String id) {
        activityRepository.delete(id);
    }
}