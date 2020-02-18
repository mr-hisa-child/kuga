package domain.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.model.team.Team;
import domain.model.team.TeamName;
import domain.model.team.TeamRepository;

@Service
public class TeamService {
	@Autowired
	private TeamRepository teamRepository;

	public boolean isExist(TeamName teamName) {
		Team team = teamRepository.findByName(teamName);
		return Objects.nonNull(team);
	}
}
