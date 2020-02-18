package infrastructure.datasource.team;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamJpaRepository extends JpaRepository<TeamEntity, String> {
	public List<TeamEntity> findByAccountId(String accountId);
}
