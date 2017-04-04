package workshop02.web;

import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import workshop02.model.Customer;
import workshop02.model.PurchaseOrder;

@RequestScoped
@Named
public class NameView {

	@PersistenceContext private EntityManager em;

	public List<String> getAllCustomerNames() {

		TypedQuery<String> query = em.createQuery(
				"select c.name from Customer c", 
				String.class);

		return (query.getResultList());

	}

	public List<Customer> findAllCustomer(Double cost) {
		TypedQuery<Customer> query = em.createQuery(
				"select c from Customer c", Customer.class);

		List<Customer> result = new LinkedList<>();
		for (Customer c: query.getResultList()) {
			for (PurchaseOrder po: c.getPurchaseOrders())
				if (po.getShippingCost() > cost) {
					result.add(c);
					break;
				}

		}
		return (result);
	}

	//Find all customers whose po shipping cost > $$$
	public List<Customer> findCustomerByShippingCost(Double cost) {

		TypedQuery<Customer> query = em.createNamedQuery(
				"Customer.findCustomerByShippingCost", Customer.class);


		query.setParameter("sCost", cost);

		return (query.getResultList());

	}
	
}
