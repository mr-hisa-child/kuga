package domain.model.team;

import java.util.List;

import domain.model.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
	private String id;
	private TeamName name;

	private TeamCategory category;

	private List<Member> members;
}