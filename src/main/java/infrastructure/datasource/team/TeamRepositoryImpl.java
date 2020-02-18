package infrastructure.datasource.team;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.model.team.Team;
import domain.model.team.TeamCategory;
import domain.model.team.TeamName;
import domain.model.team.TeamRepository;

@Repository
public class TeamRepositoryImpl implements TeamRepository {
	@Autowired
	private TeamJpaRepository repository;

	@Override
	public Team findByName(TeamName teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Team> findByAccountId(String accountId) {
		return repository.findByAccountId(accountId)
				.stream()
				.map(entity -> toDomainEntity(entity))
				.collect(Collectors.toList());
	}

	private Team toDomainEntity(TeamEntity entity) {
		Team team = new Team();
		team.setId(entity.getId());
		team.setName(new TeamName(entity.getName()));
		team.setCategory(TeamCategory.from(entity.getCategory()));
		return team;
	}

}
