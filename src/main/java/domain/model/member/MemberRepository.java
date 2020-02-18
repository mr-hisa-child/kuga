package domain.model.member;

import java.util.List;

public interface MemberRepository {
	public List<Member> findByTeamId(String teamId);
}
