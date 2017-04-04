package workshop02.web;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import workshop02.model.Customer;

@RequestScoped
@Named
public class CustomerQueryView {

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

	public void findCustomer() {

		customer = em.find(Customer.class, customerId);

		if (null == customer) {
			FacesMessage msg = new FacesMessage(
					String.format("Customer %d not found", customerId));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
