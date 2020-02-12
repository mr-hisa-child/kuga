package webapp.kuga.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.kuga.domain.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	public Account findByEmail(String email);
}
