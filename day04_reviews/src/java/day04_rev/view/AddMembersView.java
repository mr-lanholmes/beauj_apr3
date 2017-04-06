package day04_rev.view;

import day04_rev.business.AddMembersProcess;
import day04_rev.business.TeamBean;
import day04_rev.model.Member;
import day04_rev.model.Team;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class AddMembersView implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB private TeamBean teamBean;
	@EJB private AddMembersProcess addMembers;

	private String teamId;

	private String name;
	private String matriculation;
	private String email;

	private List<Member> members;
	private List<Team> teams;

	@PostConstruct
	private void init() {
		teams = teamBean.getAllTeams();
		members = new LinkedList<>();
	}

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getMatriculation() {
		return matriculation;
	}
	public void setMatriculation(String matriculation) {
		this.matriculation = matriculation;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void addToTeam() {
		System.out.println(">>> " + teamId);
		Member m = new Member();
		m.setEmaill(email);
		m.setName(name);
		m.setMatriculation(matriculation);
		members.add(m);
		email = "";
		name = "";
		matriculation = "";
	}

	public String doIt() {

		try {
			addMembers.addMembersToTeam(teamId, members);
		} catch (Throwable t) {
			return (null);
		}

		return ("index");

	}
	
}
