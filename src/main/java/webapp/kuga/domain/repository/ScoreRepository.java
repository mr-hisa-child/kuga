package webapp.kuga.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.kuga.domain.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, String> {
	public List<Score> findByActivityId(String activityId);

}
