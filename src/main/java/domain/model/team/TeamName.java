package domain.model.team;

import lombok.Getter;

public class TeamName {
	@Getter
	private final String value;

	public TeamName(String value) {
		this.value = value;
	}
}
