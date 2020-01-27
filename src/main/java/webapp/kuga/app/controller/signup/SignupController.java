package webapp.kuga.app.controller.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webapp.kuga.domain.model.Account;
import webapp.kuga.domain.service.AccountService;

@RestController
@RequestMapping("signup")
public class SignupController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public String signup(@RequestBody Signup signup) {
        Account account = new Account();
        account.setEmail(signup.getEmail());
        accountService.signup(account);
        return "";
    }
}
