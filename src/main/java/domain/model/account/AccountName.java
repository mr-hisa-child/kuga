package domain.model.account;

import lombok.Getter;

public class AccountName {
	@Getter
	private final String value;

	public AccountName(String value) {
		this.value = value;
	}
}
