package webapp.kuga.app.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginUser {

    public LoginUser(String id) {

        this.accountId = id;
    }

    private String accountId;
}