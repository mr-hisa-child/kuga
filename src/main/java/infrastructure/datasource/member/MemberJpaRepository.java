package infrastructure.datasource.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJpaRepository extends JpaRepository<MemberEntity, String> {
	public List<MemberEntity> findByTeamId(String teamId);
}
