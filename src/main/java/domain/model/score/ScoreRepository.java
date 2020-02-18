package domain.model.score;

import java.util.List;

import webapp.kuga.domain.model.Score;

public interface ScoreRepository {
	public List<Score> findByActivityId(String activityId);

	public List<Score> findByTeamIdAndYear(String teamId, int year);
}
