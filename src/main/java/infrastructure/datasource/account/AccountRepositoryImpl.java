package infrastructure.datasource.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.model.account.Account;
import domain.model.account.AccountEmail;
import domain.model.account.AccountName;
import domain.model.account.AccountRepository;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

	@Autowired
	private AccountJpaRepository repository;

	@Override
	public Account findByEmail(AccountEmail email) {
		return toDomainEntity(repository.findByEmail(email.getValue()));
	}

	@Override
	public void save(Account account) {
		AccountEntity entity = new AccountEntity();
		entity.setEmail(account.getEmail().getValue());
		entity.setName(account.getName().getValue());
		entity.setPassword(account.getPassword());
		entity.setId(account.getId());
		repository.save(entity);
	}

	private Account toDomainEntity(AccountEntity entity) {
		Account account = new Account();
		account.setEmail(new AccountEmail(entity.getEmail()));
		account.setId(entity.getId());
		account.setName(new AccountName(entity.getName()));
		account.setPassword(entity.getPassword());
		return account;
	}

}
