package domain.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.model.account.Account;
import domain.model.account.AccountEmail;
import domain.model.account.AccountRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public boolean isExist(AccountEmail email) {
		Account account = accountRepository.findByEmail(email);
		return Objects.nonNull(account);
	}
}
