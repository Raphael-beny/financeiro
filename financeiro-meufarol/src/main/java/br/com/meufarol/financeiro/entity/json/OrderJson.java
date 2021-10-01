package br.com.meufarol.financeiro.entity.json;

import java.util.List;

public class OrderJson {
	
	private String id;
	
	private String payment_method;
	
	private double charge;
	
	private double total;
	
	private List<ProductJson> products;
	
	private CustomerJson customer;
	
	private AddressJson address;
	
	private BusinessJson business;
	
	private String logo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<ProductJson> getProducts() {
		return products;
	}

	public void setProducts(List<ProductJson> products) {
		this.products = products;
	}

	public CustomerJson getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerJson customer) {
		this.customer = customer;
	}

	public AddressJson getAddress() {
		return address;
	}

	public void setAddress(AddressJson address) {
		this.address = address;
	}

	public BusinessJson getBusiness() {
		return business;
	}

	public void setBusiness(BusinessJson business) {
		this.business = business;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	

}
