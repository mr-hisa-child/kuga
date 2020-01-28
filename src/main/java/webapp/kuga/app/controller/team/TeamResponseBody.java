package webapp.kuga.app.controller.team;

import webapp.kuga.domain.model.Team;

public class TeamResponseBody {
    private Team team;

    public TeamResponseBody(Team team) {
        this.team = team;
    }

    public String getId() {
        return this.team.getId();
    }

    public String getName() {
        return this.team.getName();
    }

    public String getCategory() {
        return this.team.getCategory();
    }
}