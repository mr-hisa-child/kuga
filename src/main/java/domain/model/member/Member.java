package domain.model.member;

import java.util.Objects;

import domain.KugaDomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = { "id" })
public class Member {

    private String id;

    private MemberName name;

    private MemberNo no;

    private String teamId;

    private Member() {

    }

    public static Member getMember(MemberName name, MemberNo no, String teamId) {
        Member member = new Member();
        member.name = name;
        member.no = no;
        member.teamId = teamId;
        return member;
    }

    public static Member getMember(String id, MemberName name, MemberNo no, String teamId) {
        Member member = new Member();
        member.id = id;
        member.name = name;
        member.no = no;
        member.teamId = teamId;
        return member;
    }

    public void changeName(final MemberName name) throws KugaDomainException {
        if (Objects.isNull(name)) {
            throw new KugaDomainException("氏名がnullです", name);
        }
        this.name = name;
    }

    public void changeNo(MemberNo no) {
        this.no = no;
    }

}