package br.com.meufarol.financeiro.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import br.com.meufarol.financeiro.entity.Order;
import br.com.meufarol.financeiro.entity.OrderResponse;
import br.com.meufarol.financeiro.entity.Payment;

@Validated
public interface OrderPaymentService {

	String buscarPor(@NotNull(message = "O codigo do pedido nao pode ser nulo") String orderId);
	
	String salvarPayment(Payment payment);
	
	
	OrderResponse salvar(@Valid OrderResponse novaOrder);

	OrderResponse buscarNoBancoDeDados(String orderId);

	Payment salvarOrderPayment(Payment payment);
	
	
	Order salvar (@Valid Order order);

	Order transformarResponseEmOrder(OrderResponse orderEncontrada);
	
}
