package workshop01.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import workshop01.model.Cart;
import workshop01.model.Item;

@WebServlet(urlPatterns = "/add-to-cart")
public class AddToCartServlet extends HttpServlet {

	@Inject private Cart cart;

	private String createList(List<Item> items) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");
		for (Item i: items)
			sb.append(String.format("<li>%s (%d)</li>", 
					i.getItem(), i.getQuantity()));
		sb.append("</ul>");
		return (sb.toString());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		String item = req.getParameter("item");
		Integer quantity = Integer.parseInt(req.getParameter("quantity"));

		Item newItem = new Item();
		newItem.setItem(item);
		newItem.setQuantity(quantity);

		cart.addItem(newItem);

		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
		resp.setContentType("text/html");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("<h1>My Cart</h1>");
			//pw.println(createList(cart.getItems()));

			pw.println("<ul>");
			for (Item i: cart.getItems())
				pw.println(String.format("<li>%s (%d)</li>", 
						i.getItem(), i.getQuantity()));
			pw.println("</ul>");

			pw.println("<a href='index.html'>Back</a>");

			pw.println("<form action='checkout'><button>Checkout</button></form>");
		}
	}
	
}
