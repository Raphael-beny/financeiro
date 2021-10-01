package br.com.meufarol.financeiro.entity.json;

public class CustomerJson {
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String document;
	
	private AddressJson address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public AddressJson getAddress() {
		return address;
	}

	public void setAddress(AddressJson address) {
		this.address = address;
	}
	
	
}
