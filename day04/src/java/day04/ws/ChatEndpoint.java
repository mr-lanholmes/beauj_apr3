package day04.ws;

import java.io.IOException;
import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@Dependent
@ServerEndpoint(value = "/chat/{room}/{alias}")
public class ChatEndpoint {

	@OnOpen
	public void newConnection(Session sess, 
			@PathParam("alias")String alias,
			@PathParam("room")String room) {
		System.out.println(">>> session id: " + sess.getId());
		System.out.println(">>> room name: " + room);
		System.out.println(">>> number of connected session: " 
				+ sess.getOpenSessions().size());
		sess.getUserProperties().put("alias", alias);
	}

	@OnMessage
	public void newMessage(Session sess, String msg) {
		System.out.println(">> message from client: " + msg);
		for (Session s: sess.getOpenSessions()) {
			if (s.getUserProperties().get("alias").equals("fred"))
			try {
				s.getBasicRemote().sendText(new Date() + ": " + msg);
				return;
			} catch (IOException ex) { }
		}
	}
	
}
