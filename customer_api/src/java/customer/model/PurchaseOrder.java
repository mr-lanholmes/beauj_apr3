package customer.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PURCHASE_ORDER")
public class PurchaseOrder {

	@Id @Column(name="order_num")
	private Integer orderNumber;

	@Column(name="product_id")
	private Integer productId;	

	private Integer quantity;

	@Column(name="shipping_cost")
	private Float shippingCost;

	@Column(name="sales_date")
	@Temporal(TemporalType.DATE)
	private Date salesDate;

	@Column(name="shipping_date")
	@Temporal(TemporalType.DATE)
	private Date shippingDate;

	@Column(name="freight_company")
	private String freightCompany;

	@ManyToOne
	@JoinColumn(name="customer_id", referencedColumnName = "customer_id")
	private Customer customer;

	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(Float shippingCost) {
		this.shippingCost = shippingCost;
	}

	public Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getFreightCompany() {
		return freightCompany;
	}
	public void setFreightCompany(String freightCompany) {
		this.freightCompany = freightCompany;
	}
	
}
