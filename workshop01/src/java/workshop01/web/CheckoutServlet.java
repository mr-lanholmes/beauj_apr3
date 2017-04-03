package workshop01.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import workshop01.model.Cart;
import workshop01.model.Item;

@WebServlet(urlPatterns = "/checkout")
public class CheckoutServlet extends HttpServlet {

	@Inject private Cart cart;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		//Get the session object
		HttpSession session = req.getSession();
		System.out.println(">>> id = " + session.getId());
		//Terminate the session - also logout 
		session.invalidate();

		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("<h1>Checkout</h1>");

			pw.println("<ol>");
			for (Item i: cart.getItems())
				pw.println(String.format("<li>%s (%d)"
						, i.getItem(), i.getQuantity()));
			pw.println("</ol>");

			pw.println("<h3>Thank you for shopping with us</h3>");

		}
	}

}
