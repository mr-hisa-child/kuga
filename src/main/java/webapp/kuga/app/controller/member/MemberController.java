package webapp.kuga.app.controller.member;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webapp.kuga.app.security.LoginUser;
import webapp.kuga.domain.model.Member;
import webapp.kuga.domain.model.Team;
import webapp.kuga.domain.service.MemberService;
import webapp.kuga.domain.service.TeamService;

@RestController
@RequestMapping("team/{teamId}/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private TeamService teamService;

    @GetMapping(path = "{id}")
    public ResponseEntity<MemberResponseBody> find(@PathVariable String teamId, @PathVariable String id) {

        Team team = teamService.find(id);
        if (Objects.isNull(team)) {
            return ResponseEntity.notFound().build();
        }

        Member member = memberService.find(id);
        if (Objects.isNull(member) || Objects.equals(member.getTeamId(), teamId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new MemberResponseBody(member));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable String teamId, @PathVariable String id,
            @RequestBody MemberRequestBody requestBody,
            @AuthenticationPrincipal LoginUser loginUser) {

        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return ResponseEntity.badRequest().build();
        }

        Member member = memberService.find(id);
        if (Objects.isNull(member) || Objects.equals(member.getTeamId(), teamId)) {
            return ResponseEntity.badRequest().build();
        }

        member.setName(requestBody.getName());
        member.setNo(requestBody.getNo());

        memberService.update(member);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> remove(@PathVariable String teamId, @PathVariable String id,
            @AuthenticationPrincipal LoginUser loginUser) {

        Team team = teamService.find(id);
        if (Objects.isNull(team)) {
            return ResponseEntity.badRequest().build();
        }

        Member member = memberService.find(id);
        if (Objects.isNull(member) || Objects.equals(member.getTeamId(), teamId)) {
            return ResponseEntity.badRequest().build();
        }

        memberService.remove(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public List<MemberResponseBody> getMember(@PathVariable String teamId) {
        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return Lists.newArrayList();
        }

        return memberService.findByTeamId(teamId)
                .stream()
                .map(member -> new MemberResponseBody(member))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> addMember(@PathVariable String teamId,
            @RequestBody MemberRequestBody requestBody) {
        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
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