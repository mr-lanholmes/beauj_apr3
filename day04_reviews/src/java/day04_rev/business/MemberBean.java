package day04_rev.business;

import day04_rev.model.Member;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MemberBean {

	@PersistenceContext private EntityManager em;

	public void addMember(Member m) {
		em.persist(m);
	}
	
}
