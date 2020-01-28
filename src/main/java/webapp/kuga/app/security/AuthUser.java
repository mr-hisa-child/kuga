package webapp.kuga.app.security;

import com.google.common.collect.Lists;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class AuthUser extends User {

    private String accountId;

    public AuthUser(String username, String password, String id) {

        super(username, password, true, true, true, true, Lists.newArrayList());
        this.accountId = id;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}