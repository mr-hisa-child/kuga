package presentation.controller.activity;

import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webapp.kuga.app.security.LoginUser;
import webapp.kuga.domain.model.Activity;
import webapp.kuga.domain.service.ActivityService;
import webapp.kuga.domain.service.TeamService;

@RestController
public class ActivityController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private ActivityService activityService;

    @GetMapping(path = "team/{teamId}/activity")
    public ResponseEntity<List<ActivityResponseBody>> findAll(@PathVariable String teamId,
            @RequestParam("year") int year,
            @AuthenticationPrincipal LoginUser loginUser) {

        if (!teamService.isEnabled(loginUser.getAccountId(), teamId)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(activityService.findByTeamIdAndYear(teamId, year)
                .stream()
                .map(activity -> new ActivityResponseBody(activity))
                .sorted(Comparator.comparing(ActivityResponseBody::getDate).reversed())
                .collect(Collectors.toList()));
    }

    @GetMapping(path = "activity/{id}")
    public ResponseEntity<ActivityResponseBody> find(@PathVariable String id,
            @AuthenticationPrincipal LoginUser loginUser) {

        Activity activity = activityService.find(id);
        if (Objects.isNull(activity)) {
            return ResponseEntity.notFound().build();
        }

        if (!teamService.isEnabled(loginUser.getAccountId(), activity.getTeamId())) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ActivityResponseBody(activity));
    }

    @PutMapping(path = "activity/{id}")
    public ResponseEntity<?> update(@PathVariable String id,
            @RequestBody ActivityRequestBody requestBody,
            @AuthenticationPrincipal LoginUser loginUser) {

        Activity activity = activityService.find(id);
        if (Objects.isNull(activity)) {
            return ResponseEntity.badRequest().build();
        }

        if (!teamService.isEnabled(loginUser.getAccountId(), activity.getTeamId())) {
            return ResponseEntity.badRequest().build();
        }

        activity.setTitle(requestBody.getTitle());
        activity.setDate(requestBody.getDate());

//		activityService.update(activity);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "activity/{id}")
    public ResponseEntity<?> remove(@PathVariable String id,
            @AuthenticationPrincipal LoginUser loginUser) {

        Activity activity = activityService.find(id);
        if (Objects.isNull(activity)) {
            return ResponseEntity.badRequest().build();
        }

        if (!teamService.isEnabled(loginUser.getAccountId(), activity.getTeamId())) {
            return ResponseEntity.badRequest().build();
        }

        activityService.remove(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping(path = "team/{teamId}/activity")
    public ResponseEntity<?> addActivity(@PathVariable String teamId, @RequestBody ActivityRequestBody requestBody,
            @AuthenticationPrincipal LoginUser loginUser) {
        if (!teamService.isEnabled(loginUser.getAccountId(), teamId)) {
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