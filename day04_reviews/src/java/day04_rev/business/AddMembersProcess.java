package day04_rev.business;

import day04_rev.model.Member;
import day04_rev.model.Team;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless
public class AddMembersProcess {

	@EJB private TeamBean teamBean;
	@EJB private MemberBean memberBean;

	@Resource SessionContext ctx;

	public void addMembersToTeam(String teamId, List<Member> members) {

		Team team = teamBean.findTeam(teamId);
		if (null == team) {
			ctx.setRollbackOnly();
			return;
		}

		for (Member m: members) {
			m.setTeam(team);
			memberBean.addMember(m);
		}
	}
	
}
