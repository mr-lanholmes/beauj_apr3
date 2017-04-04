package customer.api;

import customer.model.Customer;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/customer")
public class CustomerResource {

	@PersistenceContext private EntityManager mgr;

	@GET
	@Path("{custId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("custId") Integer custId) {

		Customer customer = mgr.find(Customer.class, custId);
		System.out.println(">>> customer = " + customer);

		if (null == customer)
			return (Response.status(Response.Status.NOT_FOUND).build());

		return (Response.ok(customer.toJSON()).build());
	}
	
}
