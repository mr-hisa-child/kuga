package domain.model.activity;

import lombok.Getter;

public class ActivityTitle {
	@Getter
	private final String value;

	public ActivityTitle(String value) {
		this.value = value;
	}
}
