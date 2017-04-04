package day02a.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer quantity;
	private String size;

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


	
}
