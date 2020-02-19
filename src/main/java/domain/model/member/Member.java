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

    public void changeName(MemberName name) throws KugaDomainException {
        if (Objects.isNull(name)) {
            throw new KugaDomainException();
        }
        this.name = name;
    }

    public void changeNo(MemberNo no) {
        this.no = no;
    }

}