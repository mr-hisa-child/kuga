package webapp.kuga.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.kuga.domain.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
	public List<Activity> findByTeamId(String teamId);

	public List<Activity> findByTeamIdAndYear(String teamId, int year);

}
