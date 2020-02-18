package domain.model.activity;

import java.util.Date;

import lombok.Getter;

public class ActivityDate {
	@Getter
	private final Date value;

	public ActivityDate(Date value) {
		this.value = value;
	}
}
