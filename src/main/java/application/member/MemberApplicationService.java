package application.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberApplicationService {

	@Autowired
	private MemberRepository memberRepository;

	public Member find(String id) {
		return memberRepository.select(id);
	}

	public List<Member> findByTeamId(String teamId) {
		return memberRepository.selectByTeamId(teamId);
	}

	public void create(Member member) {
		memberRepository.insert(member);
	}

	public void update(Member member) {
		memberRepository.update(member);
	}

	public void remove(String id) {
		memberRepository.delete(id);
	}
}