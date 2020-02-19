package domain.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.model.member.Member;
import domain.model.member.MemberRepository;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * メンバーの氏名が重複している場合はTrue、それ以外はFalseを返却します。
     * 
     * @param member メンバー
     * @return True/False
     */
    public boolean isDuplicatedMemberName(Member tergetMember) {
        return memberRepository.findByNameAndTeamId(tergetMember.getName(), tergetMember.getTeamId())
                .stream()
                .anyMatch(member -> !Objects.equals(member.getId(), tergetMember.getId()));
    }
}
