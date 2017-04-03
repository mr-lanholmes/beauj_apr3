package day02.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> items = new LinkedList<>();

	public void add(String i) {
		items.add(i);
	}

	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}


	
}
