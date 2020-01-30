package webapp.kuga.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}