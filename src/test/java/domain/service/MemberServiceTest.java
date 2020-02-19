package domain.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.Lists;

import domain.model.member.Member;
import domain.model.member.MemberName;
import domain.model.member.MemberNo;
import domain.model.member.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Test
    void isDuplicatedMemberName_存在しない場合() {

        Member targetMember = Member.getMember(new MemberName(""), new MemberNo(""), "");

        when(memberRepository.findByNameAndTeamId(targetMember.getName(), targetMember.getTeamId()))
                .thenReturn(Lists.newArrayList());

        MemberService memberService = new MemberService(memberRepository);

        assertThat(memberService.isDuplicatedMemberName(targetMember), is(false));
    }

    @Test
    void isDuplicatedMemberName_存在するがIDが異なる場合() {

        Member targetMember = new Member();
        targetMember.setId("1");

        Member mockMember = new Member();
        mockMember.setId("2");

        when(memberRepository.findByNameAndTeamId(targetMember.getName(), targetMember.getTeamId()))
                .thenReturn(Lists.newArrayList(mockMember));

        MemberService memberService = new MemberService(memberRepository);

        assertThat(memberService.isDuplicatedMemberName(targetMember), is(true));
    }

    @Test
    void isDuplicatedMemberName_存在するがIDが同じ場合() {

        Member targetMember = new Member();
        targetMember.setId("1");

        Member mockMember = new Member();
        mockMember.setId("1");

        when(memberRepository.findByNameAndTeamId(targetMember.getName(), targetMember.getTeamId()))
                .thenReturn(Lists.newArrayList(mockMember));

        MemberService memberService = new MemberService(memberRepository);

        assertThat(memberService.isDuplicatedMemberName(targetMember), is(false));
    }

}
