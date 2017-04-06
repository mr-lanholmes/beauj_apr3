package day04_rev.view;

import day04_rev.business.TeamBean;
import day04_rev.model.Team;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class TeamView {

	@EJB private TeamBean teamBean;

	private String name;
	private Integer teamNumber;	

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

	public void addTeam() {
		Team team = new Team();
		team.setName(name);
		team.setTeamNumber(teamNumber);

		teamBean.createTeam(team);
	}

}
