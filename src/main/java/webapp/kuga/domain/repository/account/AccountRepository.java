package webapp.kuga.domain.repository.account;

import org.apache.ibatis.annotations.Mapper;

import webapp.kuga.domain.model.Account;

@Mapper
public interface AccountRepository {
    public void insert(Account Account);

    public Account find(Long id);

    public Account findByEmail(String email);
}
