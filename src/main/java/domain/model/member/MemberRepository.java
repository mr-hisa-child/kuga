package domain.model.member;

import java.util.List;

public interface MemberRepository {
    public Member find(String memberId);

    public List<Member> findByTeamId(String teamId);

    public List<Member> findByNameAndTeamId(MemberName name, String teamId);

    public void save(Member member);

    public void remove(String memberId);
}
