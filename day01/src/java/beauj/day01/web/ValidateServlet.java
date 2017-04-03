package beauj.day01.web;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/validate")
public class ValidateServlet extends HttpServlet {

	@Inject private User user;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		System.out.println(">>> in validate ");
		System.out.println(">>> validate: name: " + user.getName());
		System.out.println(">>> validate: age: " + user.getAge());
		System.out.println(">>> validate: email: " + user.getEmail());

		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String email = req.getParameter("email");

		user.setName(name);
		user.setAge(Integer.parseInt(age));
		user.setEmail(email);

		//Check database for potential blacklisted names
		if ("barney".equals(user.getName())) {
			resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			return;
		}

		RequestDispatcher rd = req.getRequestDispatcher("register");
		rd.forward(req, resp);
	}
	
}
