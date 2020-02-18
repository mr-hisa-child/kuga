package presentation.controller.team;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webapp.kuga.app.security.LoginUser;
import webapp.kuga.domain.model.Team;
import webapp.kuga.domain.service.TeamService;

@RestController
@RequestMapping("team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<TeamResponseBody> findByLoginUser(@AuthenticationPrincipal LoginUser loginUser) {
        return teamService.findByAccountId(loginUser.getAccountId())
                .stream()
                .map(team -> new TeamResponseBody(team))
                .collect(Collectors.toList());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody TeamRequestBody teamRequestBody,
            @AuthenticationPrincipal LoginUser loginUser) {

        if (!teamService.isEnabled(loginUser.getAccountId(), id)) {
            return ResponseEntity.badRequest().build();
        }

        Team team = teamService.find(id);
        if (Objects.isNull(team)) {
            return ResponseEntity.badRequest().build();
        }

        team.setName(teamRequestBody.getName());
        team.setCategory(teamRequestBody.getCategory());

        teamService.update(team);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeamRequestBody teamRequestBody,
            @AuthenticationPrincipal LoginUser loginUser) {

        Team team = new Team();
        team.setName(teamRequestBody.getName());
        team.setCategory(teamRequestBody.getCategory());

        teamService.create(team, loginUser.getAccountId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> remove(@PathVariable String id, @AuthenticationPrincipal LoginUser loginUser) {
        if (!teamService.isEnabled(loginUser.getAccountId(), id)) {
            return ResponseEntity.badRequest().build();
        }

        teamService.remove(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}