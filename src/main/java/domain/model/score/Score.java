package domain.model.score;

import domain.model.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Score {

	private String id;

	private ScoreCount goal;

	private ScoreCount assist;

	private ScoreCount win;

	private Member member;
}