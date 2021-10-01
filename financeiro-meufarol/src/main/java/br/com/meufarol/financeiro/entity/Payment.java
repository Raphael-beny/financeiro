package br.com.meufarol.financeiro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name = "Pagamento")
@Table(name = "pagamento")
public class Payment {
	
	@Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private int status;

    @Column(name = "valor")
    private double valor;

    @Column(name = "metodo_pagamento")
    private String metodo_pagamento;

    @Column(name = "transaction_id")
    private String transaction_id;

    @Column(name = "transaction_code")
    private String transaction_code;

    @Column(name = "card_brand")
    private String cardBrand;

    @Column(name = "machine_identification")
    private String machineIdentification;
    
    @Column(name = "order_id")
	private String orderId;
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getMetodo_pagamento() {
		return metodo_pagamento;
	}

	public void setMetodo_pagamento(String metodo_pagamento) {
		this.metodo_pagamento = metodo_pagamento;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getTransaction_code() {
		return transaction_code;
	}

	public void setTransaction_code(String transaction_code) {
		this.transaction_code = transaction_code;
	}

	public String getCardBrand() {
		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;
	}

	public String getMachineIdentification() {
		return machineIdentification;
	}

	public void setMachineIdentification(String machineIdentification) {
		this.machineIdentification = machineIdentification;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return transaction_id + "," + transaction_code + "," + orderId;
	}


	
	
}
