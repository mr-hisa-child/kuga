package infrastructure.datasource.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, String> {
	public AccountEntity findByEmail(String email);
}
