package day04_rev.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello/*")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String path = req.getPathInfo();

		resp.setStatus(200);
		resp.setContentType("text/plain");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("path info = " + path);
		}
	}
}
