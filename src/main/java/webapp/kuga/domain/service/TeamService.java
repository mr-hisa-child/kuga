package webapp.kuga.domain.service;

import java.util.List;

import com.google.common.base.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webapp.kuga.domain.model.AccountTeam;
import webapp.kuga.domain.model.Team;
import webapp.kuga.domain.repository.account_team.AccountTeamRepository;
import webapp.kuga.domain.repository.team.TeamRepository;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AccountTeamRepository accountTeamRepository;

    public Team find(String id) {
        return teamRepository.select(id);
    }

    public void create(Team team, String accountId) {
        teamRepository.insert(team);
        accountTeamRepository.insert(new AccountTeam(accountId, team.getId()));
    }

    public void update(Team team) {
        teamRepository.update(team);
    }

    public void remove(String id) {
        teamRepository.delete(id);
    }

    public List<Team> findByAccountId(String accountId) {
        return teamRepository.selectByAccountId(accountId);
    }

    public boolean isEnabled(String accountId, String teamId) {
        return Optional.fromNullable(accountTeamRepository.find(accountId, teamId)).isPresent();
    }
}