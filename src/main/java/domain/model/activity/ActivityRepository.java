package domain.model.activity;

import java.util.List;

import webapp.kuga.domain.model.Activity;

public interface ActivityRepository {
	public List<Activity> findByTeamId(String teamId);

	public List<Activity> findByTeamIdAndYear(String teamId, int year);
}
