package domain.model.score;

import lombok.Getter;

public class ScoreCount {
	@Getter
	private final int value;

	public ScoreCount(int value) {
		this.value = value < 0 ? 0 : value;
	}
}
