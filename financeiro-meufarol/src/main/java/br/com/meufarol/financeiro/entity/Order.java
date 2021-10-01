package br.com.meufarol.financeiro.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Order")
@Table(name = "orders")
public class Order {
	
	@Id
	@NotNull
	private String id;
	
	@Column(name = "metodo_pagamento")
	private String metodo_pagamento;

	@Column(name = "taxa")
	private double taxa;

	@Column(name = "total")
	private double total;

	@OneToMany(mappedBy = "order", targetEntity = Produto.class, cascade = CascadeType.ALL)
	private List<Produto> produtos;

	@Column(name = "id_cliente")
	private int id_cliente;

	@Column(name = "id_loja")
	private int id_loja;

	@Column(name = "logo")
	private String logo;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetodo_pagamento() {
		return metodo_pagamento;
	}

	public void setMetodo_pagamento(String metodo_pagamento) {
		this.metodo_pagamento = metodo_pagamento;
	}

	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_loja() {
		return id_loja;
	}

	public void setId_loja(int id_loja) {
		this.id_loja = id_loja;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	

}
