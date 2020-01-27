package webapp.kuga.app.controller.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import webapp.kuga.domain.service.AccountService;

@RestController
public class AccountController {

  @Autowired
  private AccountService accountService;


}
