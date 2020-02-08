package webapp.kuga.domain.model;

import java.util.Objects;
import java.util.stream.Stream;

import lombok.Getter;

public enum Category {
	BEGINNER("01"),
	SUPER_BEGINNER("02"),
	ENJOY("03"),
	MIX("04"),
	OPEN("05");

	@Getter
	private final String code;

	private Category(String code) {
		this.code = code;
	}

	public static Category from(String code) {
		return Stream.of(Category.values())
				.filter(c -> Objects.equals(c.code, code))
				.findAny()
				.orElse(null);
	}

}
