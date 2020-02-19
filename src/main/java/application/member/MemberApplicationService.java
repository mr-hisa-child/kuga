package application.member;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.KugaApplicationException;
import domain.KugaDomainException;
import domain.model.member.Member;
import domain.model.member.MemberRepository;
import domain.service.MemberService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class MemberApplicationService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    public MemberApplicationService(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    public MemberData find(String id) {
        return new MemberData(memberRepository.find(id));
    }

    public List<MemberData> findByTeamId(String teamId) {
        return memberRepository.findByTeamId(teamId)
                .stream()
                .map(member -> new MemberData(member))
                .collect(Collectors.toList());
    }

    /**
     * メンバーを新規作成します。
     * 
     * @param command メンバー作成コマンド
     * @throws KugaApplicationException 氏名が重複している場合に発生
     * @throws KugaDomainException
     */
    public void create(MemberCreateCommand command) throws Exception {
        Member member = command.toDomainEntity();
        if (memberService.isDuplicatedMemberName(member)) {
            throw new KugaApplicationException("氏名が重複しています。", command);
        }
        memberRepository.save(member);
    }

    public void update(MemberUpdateCommand command) throws Exception {
        Member member = command.toDomainEntity();
        if (memberService.isDuplicatedMemberName(member)) {
            throw new KugaApplicationException("氏名が重複しています。", command);
        }
        memberRepository.save(member);
    }

    public void remove(MemberRemoveCommand command) throws Exception {
        Member removeMember = memberRepository.find(command.getMemberId());
        if (Objects.isNull(removeMember)) {
            throw new KugaApplicationException("該当メンバーが存在しません。", command);
        }
        memberRepository.remove(command.getMemberId());
    }
}