package webapp.kuga.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.kuga.domain.model.Member;
import webapp.kuga.domain.repository.member.MemberRepository;

@Service
public class MemberService {

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