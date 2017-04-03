package day02.web;

import day02.model.Cart;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class ShoppingView {

	@Inject private Cart cart;

	private String item;
	private Integer quanity;

	public String getMyItem() {
		return item;
	}
	public void setMyItem(String item) {
		this.item = item;
	}

	public Integer getQuanity() {
		return quanity;
	}
	public void setQuanity(Integer quanity) {
		this.quanity = quanity;
	}

	public String addToCart() {

		System.out.println(">> item: " + item);
		System.out.println(">> quantity: " + quanity);

		cart.add(item);

		item = null;
		quanity = null;

		System.out.println(">>> cart: " + cart.getItems());

		return ("cart");

	}
	
}
