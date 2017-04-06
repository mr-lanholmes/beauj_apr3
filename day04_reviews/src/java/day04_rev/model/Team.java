package day04_rev.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="team")
public class Team {

	@Id @Column(name="team_id")
	private String teamId;

	@Column(name="team_num")
	private Integer teamNumber;

	private String name;

	@OneToMany(mappedBy = "team")
	private List<Member> members;

	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}


	/*
	@Override
	public String toString() {
		return (name);
//	}
*/

	
}
