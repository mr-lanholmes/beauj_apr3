package workshop04.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;

@ApplicationScoped
public class GameTable {

	//gameId , list of Session
	private Map<String, List<Session>> games;

	@PostConstruct
	private void init() {
		games = new HashMap<>();
	}

	//Create a new game slot
	public String createGame() {

		String gid = UUID.randomUUID().toString().substring(0, 8);
		List<Session> session = new LinkedList<>();
		games.put(gid, session);
		//games.put(gid, new LinkedList<>());

		return (gid);
	}

	//Add a player (session) to a game
	public boolean addPlayerToGame(String gid, Session sess) {

		if (!games.containsKey(gid))
			return (false);

		List<Session> session = games.get(gid);
		session.add(sess);
		//games.get(gid).add(sess);

		return (true);
	}

	//Return List<Session> for a given gid. if gid does not exist, return null
	public List<Session> getPlayers(String gid) {
		return (games.get(gid));
	}
	
}
