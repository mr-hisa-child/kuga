package domain.model.activity;

import java.util.List;

import domain.model.score.Score;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Activity {
	private String id;

	private ActivityTitle title;

	private ActivityDate date;

	private List<Score> scoreList;
}