package infrastructure.datasource.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreJpaRepository extends JpaRepository<ScoreEntity, String> {
	public List<ScoreEntity> findByActivityId(String activityId);

}
