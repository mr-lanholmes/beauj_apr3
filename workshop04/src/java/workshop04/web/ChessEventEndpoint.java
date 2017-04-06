package workshop04.web;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import workshop04.model.GameTable;

@Dependent
@ServerEndpoint(value = "/chess-event/{gameId}")
public class ChessEventEndpoint {

	@Resource(lookup = "concurrent/myThreadpool")
	private ManagedScheduledExecutorService myThreadPool;

	@Inject private GameTable gameTable;

	@OnOpen
	public void onOpen(Session session, @PathParam("gameId") String gameId) {

		if (!gameTable.addPlayerToGame(gameId, session)) {
			try {
				session.close();
			} catch (IOException ex) { }
			return;
		}

		session.getUserProperties().put("gameId", gameId);

		System.out.println("Added player to game " + gameId);
	}

	@OnMessage
	public void onMessage(Session session, String gameMove) {

		System.out.println(">> game move: " + gameMove);
		String gameId = (String)session.getUserProperties().get("gameId");

		List<Session> players = gameTable.getPlayers(gameId);
		if (null == players)
			return;

		BroadcastMoveTask task = new BroadcastMoveTask(gameMove, players);

		System.out.println(">>> dispatching brodcast task to pool");
		myThreadPool.submit(task);
		System.out.println(">> leaving onMessage method");

		/*
		for (Session s: players)
			try {
				s.getBasicRemote().sendText(gameMove);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		*/
	}
	
}
