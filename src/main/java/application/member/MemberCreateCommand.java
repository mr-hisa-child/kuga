package application.member;

import domain.KugaDomainException;
import domain.model.member.Member;
import domain.model.member.MemberName;
import domain.model.member.MemberNo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberCreateCommand {
    private String name;
    private String no;
    private String teamId;

    public Member toDomainEntity() throws KugaDomainException {
        return Member.getMember(new MemberName(this.name), new MemberNo(this.no), this.teamId);
    }
}
