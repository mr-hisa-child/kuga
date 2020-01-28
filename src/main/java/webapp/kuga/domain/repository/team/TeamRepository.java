package webapp.kuga.domain.repository.team;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webapp.kuga.domain.model.Team;

@Mapper
public interface TeamRepository {
    public Team select(String id);

    public void insert(Team entity);

    public void update(Team entity);

    public void delete(String id);

    public List<Team> selectByAccountId(String accountId);
}