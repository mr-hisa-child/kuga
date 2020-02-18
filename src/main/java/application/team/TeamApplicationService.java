package application.team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.model.team.TeamRepository;

@Service
public class TeamApplicationService {

	@Autowired
	private TeamRepository teamRepository;

//	public Team find(String id) {
//		return teamRepository.select(id);
//	}
//
//	public void create(Team team, String accountId) {
//		teamRepository.insert(team);
//		accountTeamRepository.insert(new AccountTeam(accountId, team.getId()));
//	}
//
//	public void update(Team team) {
//		teamRepository.update(team);
//	}
//
//	public void remove(String id) {
//		teamRepository.delete(id);
//	}
//
//	public List<Team> findByAccountId(String accountId) {
//		return teamRepository.selectByAccountId(accountId);
//	}
//
//	public boolean isEnabled(String accountId, String teamId) {
//		return Optional.fromNullable(accountTeamRepository.find(accountId, teamId)).isPresent();
//	}
}