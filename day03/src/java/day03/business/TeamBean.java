package day03.business;

import day03.model.Team;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TeamBean {

	//Transaction scoped
	@PersistenceContext private EntityManager em;

	public Team findByTeamId(String teamId) {
		return (em.find(Team.class, teamId));
	}

	public Team createTeam(Team team) {

		String teamId = UUID.randomUUID().toString().substring(0, 8);
		team.setTeamId(teamId);

		//team -- new
		em.persist(team);
		//team -- managed
		team.setTeamNumber(team.getTeamNumber() + 1);

		return (team);
	}

	public List<String> getAllTeamNames() {
		TypedQuery<String> query = em.createQuery(
				"select t.name from Team t", String.class);
		return (query.getResultList());
	}

	public List<Team> findAllTeams() {
		TypedQuery<Team> query = em.createQuery(
				"select t from Team t", Team.class);
		return (query.getResultList());
	} 
	
}
