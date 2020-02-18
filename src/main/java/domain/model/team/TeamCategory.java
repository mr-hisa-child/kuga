package domain.model.team;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.Getter;

public enum TeamCategory {
	BEGINNER("01"),
	SUPER_BEGINNER("02"),
	ENJOY("03"),
	MIX("04"),
	OPEN("05");

	@Getter
	private final String code;

	private TeamCategory(String code) {
		this.code = code;
	}

	public static TeamCategory from(String code) {
		return Stream.of(TeamCategory.values())
				.filter(c -> Objects.equals(c.code, code))
				.findAny()
				.orElse(null);
	}

}
