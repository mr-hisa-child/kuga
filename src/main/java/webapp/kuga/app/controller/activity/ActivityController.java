package webapp.kuga.app.controller.activity;

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
import webapp.kuga.domain.model.Activity;
import webapp.kuga.domain.model.Team;
import webapp.kuga.domain.service.ActivityService;
import webapp.kuga.domain.service.TeamService;

@RestController
@RequestMapping("team/{teamId}/activity")
public class ActivityController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<ActivityResponseBody> find(@PathVariable String teamId) {
        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return Lists.newArrayList();
        }
        return activityService.findByTeamId(teamId)
                .stream()
                .map(activity -> new ActivityResponseBody(activity))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ActivityResponseBody> find(@PathVariable String teamId, @PathVariable String id) {

        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return ResponseEntity.notFound().build();
        }

        Activity activity = activityService.find(id);
        if (Objects.isNull(activity) || !Objects.equals(activity.getTeamId(), teamId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ActivityResponseBody(activity));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<?> update(@PathVariable String teamId, @PathVariable String id,
            @RequestBody ActivityRequestBody requestBody,
            @AuthenticationPrincipal LoginUser loginUser) {

        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return ResponseEntity.badRequest().build();
        }

        Activity activity = activityService.find(id);
        if (Objects.isNull(activity) || !Objects.equals(activity.getTeamId(), teamId)) {
            return ResponseEntity.badRequest().build();
        }

        activity.setTitle(requestBody.getTitle());
        activity.setDate(requestBody.getDate());

        activityService.update(activity);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> remove(@PathVariable String teamId, @PathVariable String id,
            @AuthenticationPrincipal LoginUser loginUser) {

        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return ResponseEntity.badRequest().build();
        }

        Activity activity = activityService.find(id);
        if (Objects.isNull(activity) || !Objects.equals(activity.getTeamId(), teamId)) {
            return ResponseEntity.badRequest().build();
        }

        activityService.remove(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<?> addActivity(@PathVariable String teamId, @RequestBody ActivityRequestBody requestBody) {
        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return ResponseEntity.badRequest().build();
        }

        Activity activity = new Activity();

        activity.setTeamId(teamId);
        activity.setTitle(requestBody.getTitle());
        activity.setDate(requestBody.getDate());

        activityService.create(activity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}