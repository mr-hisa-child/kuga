package webapp.kuga.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.kuga.domain.repository.AccountRepository;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;


}
