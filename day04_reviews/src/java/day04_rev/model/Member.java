package day04_rev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member {

	@Id
	private String email;

	private String name;

	@Column(name="matrix_no")	
	private String matriculation;

	@ManyToOne
	//@JoinColumn(name="team", referencedColumnName = "team_num")
	@JoinColumn(name="team", referencedColumnName = "team_id")
	private Team team;

	public String getEmaill() {
		return email;
	}
	public void setEmaill(String emaill) {
		this.email = emaill;
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

	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}

}
