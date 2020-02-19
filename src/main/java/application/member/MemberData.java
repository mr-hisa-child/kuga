package application.member;

import domain.model.member.Member;
import lombok.Getter;

@Getter
public class MemberData {
    private String id;
    private String name;
    private int no;

    public MemberData(Member member) {
        this.id = member.getId();
        this.name = member.getName().getValue();
        this.no = member.getNo().getValue();
    }
}
