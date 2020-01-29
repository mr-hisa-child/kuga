package webapp.kuga.app.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import webapp.kuga.domain.model.Account;
import webapp.kuga.domain.repository.account.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException(username);
        }
        return new AuthUser(account.getEmail(), account.getPassword(), account.getId());
    }

}