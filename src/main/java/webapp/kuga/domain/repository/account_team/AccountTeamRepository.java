package webapp.kuga.domain.repository.account_team;

import org.apache.ibatis.annotations.Mapper;

import webapp.kuga.domain.model.AccountTeam;

@Mapper
public interface AccountTeamRepository {

    public void insert(AccountTeam entity);
}