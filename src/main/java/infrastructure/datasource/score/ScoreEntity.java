package infrastructure.datasource.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import infrastructure.datasource.BaseEntity;
import infrastructure.datasource.member.MemberEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class ScoreEntity extends BaseEntity {
	@Column(name = "activity_id")
	private String activityId;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "team_id")
	private String teamId;
	@Column
	private int goal;
	@Column
	private int assist;
	@Column
	private int win;

	@ManyToOne
	@JoinColumn(name = "member_id", insertable = false, updatable = false)
	private MemberEntity member;

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