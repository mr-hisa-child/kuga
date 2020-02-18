package domain.model.team;

import java.util.List;

public interface TeamRepository {
	public Team findByName(TeamName teamName);

	public List<Team> findByAccountId(String accoutId);
}
