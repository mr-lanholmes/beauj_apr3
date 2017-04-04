package day02a.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;

@SessionScoped
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> items = new LinkedList<>();

	public void add(Item i) {
		items.add(i);
	}

	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Integer count() {
		int total = 0;
		for (Item i: items)
			total += i.getQuantity();
		return (total);
	}

	
}
