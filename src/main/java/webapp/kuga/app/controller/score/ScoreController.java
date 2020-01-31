package webapp.kuga.app.controller.score;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webapp.kuga.app.security.LoginUser;
import webapp.kuga.domain.model.Activity;
import webapp.kuga.domain.model.Score;
import webapp.kuga.domain.model.Team;
import webapp.kuga.domain.service.ActivityService;
import webapp.kuga.domain.service.ScoreService;
import webapp.kuga.domain.service.TeamService;

@RestController
public class ScoreController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private TeamService teamService;

    @GetMapping(path = "team/{teamId}/score")
    public List<ScoreResponseBody> findByTeamIdAndYear(@PathVariable String teamId, @RequestParam("year") int year) {

        Team team = teamService.find(teamId);
        if (Objects.isNull(team)) {
            return Lists.newArrayList();
        }

        return scoreService.findByTeamIdAndYear(teamId, year)
                .stream()
                .map(score -> new ScoreResponseBody(score))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "activity/{activityId}/score")
    public List<ScoreResponseBody> findByActivityId(@PathVariable String activityId) {

        Activity activity = activityService.find(activityId);
        if (Objects.isNull(activity)) {
            return Lists.newArrayList();
        }

        return scoreService.findByActivityId(activityId)
                .stream()
                .map(score -> new ScoreResponseBody(score))
                .collect(Collectors.toList());
    }

    @PutMapping(path = "score/{id}/increment/{item}")
    public ResponseEntity<ScoreResponseBody> increment(@PathVariable String id, @PathVariable String item) {

        Score score = scoreService.find(id);

        if (Objects.isNull(score)) {
            return ResponseEntity.badRequest().build();
        }

        switch (item) {
        case "goal":
            score.setGoal(score.getGoal() + 1);
            break;
        case "assist":
            score.setAssist(score.getAssist() + 1);
            break;
        case "win":
            score.setWin(score.getWin() + 1);
            break;
        default:
            return ResponseEntity.badRequest().build();
        }

        scoreService.update(score);

        return ResponseEntity.ok(new ScoreResponseBody(score));
    }

    @PutMapping(path = "score/{id}/decrement/{item}")
    public ResponseEntity<ScoreResponseBody> decrement(@PathVariable String id, @PathVariable String item) {

        Score score = scoreService.find(id);

        if (Objects.isNull(score)) {
            return ResponseEntity.badRequest().build();
        }

        switch (item) {
        case "goal":
            score.setGoal(score.getGoal() - 1);
            break;
        case "assist":
            score.setAssist(score.getAssist() - 1);
            break;
        case "win":
            score.setWin(score.getWin() - 1);
            break;
        default:
            return ResponseEntity.badRequest().build();
        }

        scoreService.update(score);

        return ResponseEntity.ok(new ScoreResponseBody(score));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> remove(@PathVariable String id,
            @AuthenticationPrincipal LoginUser loginUser) {

        Score score = scoreService.find(id);

        if (Objects.isNull(score)) {
            return ResponseEntity.badRequest().build();
        }

        scoreService.remove(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(path = "activity/{activityId}/attend")
    @Transactional
    public ResponseEntity<?> attend(@PathVariable String activityId, @RequestBody ScoreRequestBody requestBody) {
        Activity activity = activityService.find(activityId);
        if (Objects.isNull(activity)) {
            return ResponseEntity.badRequest().build();
        }

        List<String> memberList = scoreService.findByActivityId(activityId)
                .stream()
                .map(score -> score.getMemberId())
                .collect(Collectors.toList());

        memberList.parallelStream()
                .filter(memberId -> !requestBody.getMemberList().contains(memberId))
                .forEach(memberId -> {
                    scoreService.remove(activityId, memberId);
                });

        requestBody.getMemberList().parallelStream()
                .filter(memberId -> !memberList.contains(memberId))
                .forEach(memberId -> {
                    Score score = new Score();
                    score.setActivityId(activityId);
                    score.setMemberId(memberId);
                    scoreService.create(score);
                });

        return ResponseEntity.ok().build();
    }

}