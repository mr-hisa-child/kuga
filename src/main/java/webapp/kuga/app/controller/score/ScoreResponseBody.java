package webapp.kuga.app.controller.score;

import webapp.kuga.domain.model.Score;

public class ScoreResponseBody {
    private Score score;

    public ScoreResponseBody(Score score) {
        this.score = score;
    }

    public String getId() {
        return this.score.getId();
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