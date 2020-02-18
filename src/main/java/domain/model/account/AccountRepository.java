package domain.model.account;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {
	public Account findByEmail(AccountEmail email);

	public void save(Account account);
}
