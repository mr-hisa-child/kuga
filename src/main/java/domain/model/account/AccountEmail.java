package domain.model.account;

import lombok.Getter;

public class AccountEmail {
	@Getter
	private final String value;

	public AccountEmail(String value) {
		this.value = value;
	}
}
