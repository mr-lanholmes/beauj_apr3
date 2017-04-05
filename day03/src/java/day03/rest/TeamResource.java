package day03.rest;

import day03.business.TeamBean;
import day03.model.Team;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/team")
public class TeamResource {

	@EJB private TeamBean teamBean;

	@GET
	public Response getTeams() {

		List<String> teamNames = teamBean.getAllTeamNames();

		return (Response
				.accepted(teamNames.toString())
				.type("text/plain")
				.build());

				//Response.ok(teamNames.toString(), MediaType.TEXT_PLAIN);
	}

	@GET
	@Path("{teamId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTeam(@PathParam("teamId") String teamId) {

		Team team = teamBean.findByTeamId(teamId);

		if (null == team)
			return (
					Response
							.status(Response.Status.NOT_FOUND)
							.entity("Team not found")
							.type("text/plain")
							.build()
					);

		JsonObjectBuilder builder = Json.createObjectBuilder()
				.add("teamId", team.getTeamId())
				.add("name", team.getName())
				.add("teamNumber", team.getTeamNumber())
				.add("accessTime", (new Date()).toGMTString());

		return (
				Response
						.ok(builder.build())
						.build()
		);

	}
	
}
