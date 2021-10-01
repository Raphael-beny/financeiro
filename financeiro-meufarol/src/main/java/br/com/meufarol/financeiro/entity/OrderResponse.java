package br.com.meufarol.financeiro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_resposta")
public class OrderResponse {
	
	@Id
	@Column(name = "orderId")
	private String id;
	
	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoRequisicao tipo;
	
	@Column(name = "json")
	private String json;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TipoRequisicao getTipo() {
		return tipo;
	}

	public void setTipo(TipoRequisicao tipo) {
		this.tipo = tipo;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	

}
