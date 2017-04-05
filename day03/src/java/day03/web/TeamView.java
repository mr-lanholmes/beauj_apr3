package day03.web;

import day03.business.TeamBean;
import day03.model.Team;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class TeamView {

	@EJB private TeamBean teamBean;

	private String name;
	private Integer teamNumber;
	private Team team;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String createTeam() {
		Team team = new Team();
		team.setName(name);
		team.setTeamNumber(teamNumber);

		this.team = teamBean.createTeam(team);
		//team -- detached

		return ("registered");
	}
	
	
}
