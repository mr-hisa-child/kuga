package application.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.KugaApplicationException;
import domain.model.account.Account;
import domain.model.account.AccountEmail;
import domain.model.account.AccountName;
import domain.model.account.AccountRepository;
import domain.service.AccountService;

@Service
public class AccountApplicationService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	public void signup(AccountSignupCommand command) throws KugaApplicationException {
		Account account = new Account();
		account.setName(new AccountName(command.getName()));
		account.setPassword(command.getPassword());
		account.setEmail(new AccountEmail(command.getEmail()));

		if (accountService.isExist(account.getEmail())) {
			throw new KugaApplicationException();
		}

		accountRepository.save(account);
	}
}
