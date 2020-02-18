package presentation.controller.member;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webapp.kuga.app.security.LoginUser;
import webapp.kuga.domain.model.Member;
import webapp.kuga.domain.service.MemberService;
import webapp.kuga.domain.service.TeamService;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamService teamService;

    @GetMapping(path = "member/{id}")
    public ResponseEntity<MemberResponseBody> find(@PathVariable String id,
            @AuthenticationPrincipal LoginUser loginUser) {

        Member member = memberService.find(id);
        if (Objects.isNull(member)) {
            return ResponseEntity.notFound().build();
        }

        if (!teamService.isEnabled(loginUser.getAccountId(), member.getTeamId())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new MemberResponseBody(member));
    }

    @PutMapping(path = "member/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
            @RequestBody MemberRequestBody requestBody,
            @AuthenticationPrincipal LoginUser loginUser) {

        Member member = memberService.find(id);
        if (Objects.isNull(member)) {
            return ResponseEntity.badRequest().build();
        }

        if (!teamService.isEnabled(loginUser.getAccountId(), member.getTeamId())) {
            return ResponseEntity.badRequest().build();
        }

        member.setName(requestBody.getName());
        member.setNo(requestBody.getNo());

        memberService.update(member);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "member/{id}")
    public ResponseEntity<?> remove(@PathVariable String id,
            @AuthenticationPrincipal LoginUser loginUser) {

        Member member = memberService.find(id);
        if (Objects.isNull(member)) {
            return ResponseEntity.badRequest().build();
        }

        if (!teamService.isEnabled(loginUser.getAccountId(), member.getTeamId())) {
            return ResponseEntity.badRequest().build();
        }

        memberService.remove(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "team/{teamId}/member")
    public ResponseEntity<List<MemberResponseBody>> getMember(@PathVariable String teamId,
            @AuthenticationPrincipal LoginUser loginUser) {
        if (!teamService.isEnabled(loginUser.getAccountId(), teamId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(memberService.findByTeamId(teamId)
                .stream()
                .map(member -> new MemberResponseBody(member))
                .collect(Collectors.toList()));
    }

    @PostMapping(path = "team/{teamId}/member")
    public ResponseEntity<?> addMember(@PathVariable String teamId,
            @RequestBody MemberRequestBody requestBody, @AuthenticationPrincipal LoginUser loginUser) {
        if (!teamService.isEnabled(loginUser.getAccountId(), teamId)) {
            return ResponseEntity.badRequest().build();
        }

        Member member = new Member();
        member.setName(requestBody.getName());
        member.setNo(requestBody.getNo());
        member.setTeamId(teamId);

        memberService.create(member);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}