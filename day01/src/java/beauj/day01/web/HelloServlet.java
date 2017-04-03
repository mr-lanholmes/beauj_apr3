package beauj.day01.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/hello", "/hello/*"})
public class HelloServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println(">> in init");
	}

	@Override
	public void destroy() {
		System.out.println(">> shutting down");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String userAgent = null;

		if (null != req.getPathInfo())
			userAgent = req.getPathInfo().substring(1);
		else
			userAgent = req.getHeader("User-Agent");

		String currentDate = (new Date()).toGMTString();

		//200 OK
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		resp.setHeader("X-Location", "ISS classroom 3-01");

		//Send data in payload
		try (PrintWriter pw = resp.getWriter()) {
			pw.println(String.format("<h1>Hello %s</h1>", userAgent));
			pw.println(String.format("<h2>Current date/time is %s</h2>", currentDate));
		}

	}

	
    
}
