package webapp.kuga.app.controller.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> signup(@RequestBody SignupRequestBody signup) {
        Account account = new Account();
        account.setEmail(signup.getEmail());
        account.setPassword(passwordEncoder.encode(signup.getPassword()));
        account.setName(signup.getName());
        accountService.signup(account);
        return ResponseEntity.ok().build();
    }
}
