package application.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountSignupCommand {
	private String name;
	private String email;
	private String password;
}
