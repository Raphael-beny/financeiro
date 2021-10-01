package br.com.meufarol.financeiro.service.proxy;


import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.meufarol.financeiro.entity.Order;
import br.com.meufarol.financeiro.entity.OrderResponse;
import br.com.meufarol.financeiro.entity.Payment;
import br.com.meufarol.financeiro.entity.TipoRequisicao;
import br.com.meufarol.financeiro.service.OrderPaymentService;

@Service
@Qualifier("orderPaymentServiceProxy")
public class OrderPaymentServiceProxy implements OrderPaymentService{
	
	@Autowired
	@Qualifier("orderPaymentServiceImpl")
	private OrderPaymentService orderPaymentService;
	
	@Autowired
	private ProducerTemplate buscarOrderTemplate;
	
	@Autowired
	private ProducerTemplate salvarPaymentTemplate;

	@Override
	public String buscarPor(String orderId) {
				
		OrderResponse order = new OrderResponse();
		
		String retorno = buscarOrderTemplate.requestBody(
						"direct:buscarOrder", orderId, String.class);
				
		order.setId(orderId);
		order.setJson(retorno);
		order.setTipo(TipoRequisicao.GET);
		this.salvar(order);
				
		return retorno;
	}


	@Override
	public OrderResponse salvar(OrderResponse novaOrder) {
		return orderPaymentService.salvar(novaOrder);
	}


	@Override
	public OrderResponse buscarNoBancoDeDados(String orderId) {
		return orderPaymentService.buscarNoBancoDeDados(orderId);
	}


	@Override
	public Payment salvarOrderPayment(Payment payment) {
		return orderPaymentService.salvarOrderPayment(payment);
	}


	@Override
	public String salvarPayment(Payment payment) {
		String resposta = salvarPaymentTemplate.requestBody("direct:salvarPagamento", payment.toString(), String.class);
		OrderResponse orderEncontrada = this.buscarNoBancoDeDados(payment.getOrderId());
		Order order = orderPaymentService.transformarResponseEmOrder(orderEncontrada);
		this.salvar(order);
		payment.setMetodo_pagamento(order.getMetodo_pagamento());
		payment.setValor(order.getTotal());
		payment.setStatus(5);
		this.salvarOrderPayment(payment);
		return resposta;
	}

	@Override
	public Order salvar(Order order) {
		return orderPaymentService.salvar(order);
	}


	@Override
	public Order transformarResponseEmOrder(OrderResponse orderEncontrada) {
		return orderPaymentService.transformarResponseEmOrder(orderEncontrada);
	}

}
