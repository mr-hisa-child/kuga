package webapp.kuga.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score extends BaseEntity {
	private String activityId;
	private String memberId;
	private String teamId;
	private int goal;
	private int assist;
	private int win;

	private Member member;

	public void setGoal(int goal) {
		this.goal = goal < 0 ? 0 : goal;
	}

	public void setAssist(int assist) {
		this.assist = assist < 0 ? 0 : assist;
	}

	public void setWin(int win) {
		this.win = win < 0 ? 0 : win;
	}
}