package beauj.day01.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

	@Inject private User user; //request
	@Inject private UserList userList; //session

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		System.out.println(">> in register ");

		User ur = new User();
		System.out.println("@Inject user" + user.getClass().getName());
		System.out.println("new user" + ur.getClass().getName());

		userList.addUser();
		//userList.addUser(user.createCopy());

		//Insert into database

		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		resp.setContentType("text/plain");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("Thank you for registering with us");
			pw.println("There are now " + userList.getUsers().size() + " users");
			for (User u: userList.getUsers())
				pw.println(">> user = " + u.getName());
		}
	}

	
	
}
	

