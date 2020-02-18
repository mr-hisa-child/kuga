package infrastructure.datasource.activity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import infrastructure.datasource.BaseEntity;
import infrastructure.datasource.score.ScoreEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import webapp.kuga.util.DateUtil;

@Getter
@Setter
@ToString
@Entity
@Table
public class ActivityEntity extends BaseEntity {
	@Column(name = "team_id")
	private String teamId;
	@Column
	private String title;
	@Column
	private int year;
	@Column
	private int month;
	@Column
	private int day;
	@OneToMany
	@JoinColumn(name = "activity_id")
	private List<ScoreEntity> scoreList;

	public void setDate(String date) {
		LocalDate d = DateUtil.parse(date, "yyyy-MM-dd");
		this.year = d.getYear();
		this.month = d.getMonthValue();
		this.day = d.getDayOfMonth();
	}

	public String getDate() {
		return DateUtil.format(LocalDate.of(this.year, this.month, this.day), "yyyy-MM-dd");
	}
}