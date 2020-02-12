package webapp.kuga.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.kuga.domain.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	public List<Member> findByTeamId(String teamId);
}
