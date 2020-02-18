package presentation.controller.score;

import java.util.Objects;

import webapp.kuga.domain.model.Score;

public class ScoreResponseBody {
	private Score score;

	public ScoreResponseBody(Score score) {
		this.score = score;
	}

	public String getId() {
		return this.score.getId();
	}

	public String getMemberName() {
		return Objects.nonNull(this.score.getMember()) ? this.score.getMember().getName() : null;
	}

	public int getGoal() {
		return this.score.getGoal();
	}

	public int getAssist() {
		return this.score.getAssist();
	}

	public int getWin() {
		return this.score.getWin();
	}
}