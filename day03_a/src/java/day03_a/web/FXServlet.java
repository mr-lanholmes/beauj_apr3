package day03_a.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@WebServlet(urlPatterns = "/fx")
public class FXServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		Float sgd = Float.parseFloat(req.getParameter("sgd"));

		Client client = ClientBuilder.newClient();

		// http://api.fixer.io/latest?base=SGD&symbols=USD
		WebTarget target = client.target("http://api.fixer.io/latest");
		target = target.queryParam("base", "SGD")
				.queryParam("symbols", "USD");

		Invocation.Builder inv = target.request(MediaType.APPLICATION_JSON);

		JsonObject result = inv.get(JsonObject.class);
		JsonObject rates = result.getJsonObject("rates");
		JsonNumber num = rates.getJsonNumber("USD");
		BigDecimal bigDecimal = num.bigDecimalValue();
		Float exchangeRate = bigDecimal.floatValue() * sgd;

		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("<h3>You get USD" + exchangeRate);
		}

		System.out.println(">>> result = " + result.toString());
	}

	
	
}
