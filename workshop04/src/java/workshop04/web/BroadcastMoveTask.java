package workshop04.web;

import java.io.IOException;
import java.util.List;
import javax.websocket.Session;

public class BroadcastMoveTask implements Runnable {

	private List<Session> players;
	private String gameMove;

	public BroadcastMoveTask(String gameMove, List<Session> p) {
		players = p;
		this.gameMove = gameMove;
	}

	@Override
	public void run() { //Thread's body
		System.out.println(">> start the move broadcast for " + gameMove);
		for (Session s: players)
			try {
				s.getBasicRemote().sendText(gameMove);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		System.out.println(">>> completed broadcast");
	}
	
}
