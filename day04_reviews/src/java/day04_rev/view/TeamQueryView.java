package day04_rev.view;

import day04_rev.business.TeamBean;
import day04_rev.model.Team;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class TeamQueryView {

	@EJB private TeamBean teamBean;

	private Integer teamId;
	private Team team;

	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

	public void search() {
		team = teamBean.findByTeamNumber(teamId);
	}

	
}
