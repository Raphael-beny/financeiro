package br.com.meufarol.financeiro.entity.json;

public class ProductJson {

	private String name;
	
	private int quantity;
	
	private double unitary_value;
	
	private double total_value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitary_value() {
		return unitary_value;
	}

	public void setUnitary_value(double unitary_value) {
		this.unitary_value = unitary_value;
	}

	public double getTotal_value() {
		return total_value;
	}

	public void setTotal_value(double total_value) {
		this.total_value = total_value;
	}
	
	
}
