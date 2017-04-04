package day02_b.web;

import day02_b.model.Customer;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequestScoped
@Named
public class QueryView {

	@PersistenceContext private EntityManager em;

	private Integer customerId;
	private Customer customer;

	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void query() {
		System.out.println(">>> customer id: " + customerId);

		customer = em.find(Customer.class, customerId);

		if (null == customer) {
			FacesMessage msg = new FacesMessage("Customer not found: " + customerId);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
}
