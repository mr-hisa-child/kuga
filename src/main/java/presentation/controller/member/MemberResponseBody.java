package presentation.controller.member;

import webapp.kuga.domain.model.Member;

public class MemberResponseBody {
    private Member member;

    public MemberResponseBody(Member member) {
        this.member = member;
    }

    public String getId() {
        return this.member.getId();
    }

    public String getName() {
        return this.member.getName();
    }

    public Integer getNo() {
        return this.member.getNo();
    }
}