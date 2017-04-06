package day03_a.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class FXTask implements Runnable {

	private Float sgd;
	private AsyncContext asyncCtx;

	public FXTask(Float sgd, AsyncContext ctx) {
		this.sgd = sgd;
		asyncCtx = ctx;
	}

	@Override
	public void run() {
		System.out.println(">>> FXTask started runnning...");
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

		HttpServletResponse resp = (HttpServletResponse)asyncCtx.getResponse();

		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/html");
		try (PrintWriter pw = resp.getWriter()) {
			pw.println("<h3>FROM ASYNC You get USD" + exchangeRate);
		} catch (IOException ex) { }

		System.out.println(">>> async completed = " + result.toString());

		//Signal to resume processing
		asyncCtx.complete();
	}
	
}
