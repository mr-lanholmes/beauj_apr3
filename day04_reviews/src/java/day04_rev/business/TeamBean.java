package day04_rev.business;

import day04_rev.model.Team;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TeamBean {

	@PersistenceContext private EntityManager em;

	public Team findTeam(String teamId) {
		return (em.find(Team.class, teamId));
	}

	public List<Team> getAllTeams() {

		TypedQuery<Team> query = em.createQuery(
				"select t from Team t", 
				Team.class);
		return (query.getResultList());
	}

	public void createTeam(Team team) {
		//team - new
		team.setTeamId(UUID.randomUUID().toString().substring(0, 8));
		em.persist(team);
		//team - managed
	}
	
}
