package day03_a.web;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/fx-async", asyncSupported = true)
public class FXAsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		AsyncContext asyncCtx = req.startAsync(req, resp);

		Float sgd = Float.parseFloat(req.getParameter("sgd"));
		FXTask task = new FXTask(sgd, asyncCtx);

		System.out.println(">>> dispatching task");
		asyncCtx.setTimeout(15 * 1000);
		asyncCtx.start(task);

		System.out.println(">> when I exit, the request will be suspended");
	}

	
	
}
