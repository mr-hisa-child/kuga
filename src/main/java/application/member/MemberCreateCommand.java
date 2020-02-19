package application.member;

import domain.KugaDomainException;
import domain.model.member.Member;
import domain.model.member.MemberName;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberCreateCommand {
    private String name;
    private String no;
    private String teamId;

    public Member toDoaminEntity() throws KugaDomainException {
        Member member = new Member();
        member.setName(new MemberName(this.name));
        member.setTeamId(this.teamId);
        return member;
    }
}
