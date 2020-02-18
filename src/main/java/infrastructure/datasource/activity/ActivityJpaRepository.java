package infrastructure.datasource.activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityJpaRepository extends JpaRepository<ActivityEntity, String> {
	public List<ActivityEntity> findByTeamId(String teamId);

	public List<ActivityEntity> findByTeamIdAndYear(String teamId, int year);

}
