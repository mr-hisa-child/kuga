package webapp.kuga.app.controller.signup;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestBody {
    private String name;
    private String email;
    private String password;
}