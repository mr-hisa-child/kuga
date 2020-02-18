package domain.model.account;

import java.util.List;

import domain.model.team.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

	private String id;

	private AccountName name;

	private AccountEmail email;

	private String password;

	private List<Team> teams;
}
