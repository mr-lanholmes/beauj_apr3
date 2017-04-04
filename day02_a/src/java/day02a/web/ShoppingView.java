package day02a.web;

import day02a.model.Cart;
import day02a.model.Item;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class ShoppingView {

	@Inject private Cart cart;

	private static final List<String> sizes = new LinkedList<>();

	private String name;
	private Integer quantity;
	private String size = "L";

	public ShoppingView() {
		sizes.add("XS");
		sizes.add("S");
		sizes.add("M");
		sizes.add("L");
		sizes.add("XL");
	}

	public static List<String> sizes() {
		return (sizes);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String addToCart() {

		if (quantity > 10) {
			FacesMessage msg = new FacesMessage("Sorry we do not have enough stock to fulfil you order");
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage("formId:gridId:quantityField", msg);
			return ("");
		}

		Item i = new Item();
		i.setName(name);
		i.setQuantity(quantity);
		i.setSize(size);

		cart.add(i);

		return ("cart-count?faces-redirect=true");

	}
	
}
