package application.member;

import domain.KugaDomainException;
import domain.model.member.Member;
import domain.model.member.MemberName;
import domain.model.member.MemberNo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberUpdateCommand {
    private String memberId;
    private String name;
    private String no;
    private String teamId;

    public Member toDoaminEntity() throws KugaDomainException {
        Member member = new Member();
        member.setId(this.memberId);
        member.setName(new MemberName(this.name));
        member.setTeamId(this.teamId);
        member.setNo(new MemberNo(no));
        return member;
    }
}
