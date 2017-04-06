package workshop04.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import workshop04.model.GameTable;

@WebServlet(urlPatterns = "/game")
public class GameServlet extends HttpServlet {

	@Inject private GameTable gameTable;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String gameId = gameTable.createGame();

		System.out.println(">>> created a new game: " + gameId);

		resp.setStatus(HttpServletResponse.SC_CREATED);
		resp.setContentType("application/json");

		JsonObjectBuilder builder = Json.createObjectBuilder();
		builder.add("gameId", gameId);
		JsonObject gameData = builder.build();
		
//		JsonObject gameData = Json.createObjectBuilder()
//				.add("gameId", gameId)
//				.build();

		try (PrintWriter pw = resp.getWriter()) {
			pw.print(gameData.toString());
		}
	}
}
