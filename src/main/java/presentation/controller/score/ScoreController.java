package presentation.controller.score;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import webapp.kuga.app.security.LoginUser;
import webapp.kuga.domain.dto.Record;
import webapp.kuga.domain.model.Activity;
import webapp.kuga.domain.model.Score;
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
	public List<Record> findByTeamIdAndYear(@PathVariable String teamId, @RequestParam("year") int year,
			@AuthenticationPrincipal LoginUser loginUser) {

		if (!teamService.isEnabled(loginUser.getAccountId(), teamId)) {
			return Lists.newArrayList();
		}

		return scoreService.findByTeamIdAndYear(teamId, year);
	}

	@GetMapping(path = "activity/{activityId}/score")
	public List<ScoreResponseBody> findByActivityId(@PathVariable String activityId,
			@AuthenticationPrincipal LoginUser loginUser) {

		Activity activity = activityService.find(activityId);
		if (Objects.isNull(activity)) {
			return Lists.newArrayList();
		}

		if (!teamService.isEnabled(loginUser.getAccountId(), activity.getTeamId())) {
			return Lists.newArrayList();
		}

		return scoreService.findWithMemberByActivityId(activityId)
				.stream()
				.map(score -> new ScoreResponseBody(score))
				.collect(Collectors.toList());
	}

	@PutMapping(path = "score/{id}/increment/{item}")
	public ResponseEntity<ScoreResponseBody> increment(@PathVariable String id, @PathVariable String item,
			@AuthenticationPrincipal LoginUser loginUser) {

		Score score = scoreService.find(id);

		if (Objects.isNull(score)) {
			return ResponseEntity.badRequest().build();
		}

		if (!teamService.isEnabled(loginUser.getAccountId(), score.getTeamId())) {
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
	public ResponseEntity<ScoreResponseBody> decrement(@PathVariable String id, @PathVariable String item,
			@AuthenticationPrincipal LoginUser loginUser) {

		Score score = scoreService.find(id);

		if (Objects.isNull(score)) {
			return ResponseEntity.badRequest().build();
		}

		if (!teamService.isEnabled(loginUser.getAccountId(), score.getTeamId())) {
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

	@DeleteMapping(path = "score/{id}")
	public ResponseEntity<?> remove(@PathVariable String id,
			@AuthenticationPrincipal LoginUser loginUser) {

		Score score = scoreService.find(id);

		if (Objects.isNull(score)) {
			return ResponseEntity.badRequest().build();
		}

		if (!teamService.isEnabled(loginUser.getAccountId(), score.getTeamId())) {
			return ResponseEntity.badRequest().build();
		}

		scoreService.remove(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping(path = "activity/{activityId}/attend")
	public ResponseEntity<List<ScoreResponseBody>> attend(@PathVariable String activityId,
			@RequestBody ScoreRequestBody requestBody, @AuthenticationPrincipal LoginUser loginUser) {

		Activity activity = activityService.find(activityId);
		if (Objects.isNull(activity)) {
			return ResponseEntity.badRequest().build();
		}

		if (!teamService.isEnabled(loginUser.getAccountId(), activity.getTeamId())) {
			return ResponseEntity.badRequest().build();
		}

		List<ScoreResponseBody> data = scoreService
				.attend(activity, requestBody.getMemberList())
				.stream()
				.map(score -> new ScoreResponseBody(score))
				.collect(Collectors.toList());

		return ResponseEntity.ok(data);
	}

}