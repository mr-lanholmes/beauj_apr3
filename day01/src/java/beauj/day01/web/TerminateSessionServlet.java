package beauj.day01.web;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/terminate")
public class TerminateSessionServlet extends HttpServlet {

	@Inject private UserList users;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		HttpSession session = req.getSession();
		session.invalidate();

		System.out.println(">> number of registered users: " 
				+ users.getUsers().size());


		resp.setStatus(HttpServletResponse.SC_OK);

	}

	
	
}
