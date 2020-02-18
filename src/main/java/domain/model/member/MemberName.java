package domain.model.member;

import lombok.Getter;

public class MemberName {
	@Getter
	private final String value;

	public MemberName(String value) {
		this.value = value;
	}
}
