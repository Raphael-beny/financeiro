package br.com.meufarol.financeiro.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.meufarol.financeiro.entity.Cliente;
import br.com.meufarol.financeiro.entity.Endereco;
import br.com.meufarol.financeiro.entity.Loja;
import br.com.meufarol.financeiro.entity.Order;
import br.com.meufarol.financeiro.entity.OrderResponse;
import br.com.meufarol.financeiro.entity.Payment;
import br.com.meufarol.financeiro.entity.json.OrderJson;
import br.com.meufarol.financeiro.repository.ClientesRepository;
import br.com.meufarol.financeiro.repository.EnderecosRepository;
import br.com.meufarol.financeiro.repository.LojasRepository;
import br.com.meufarol.financeiro.repository.OrderResponseRepository;
import br.com.meufarol.financeiro.repository.OrdersRepository;
import br.com.meufarol.financeiro.repository.PaymentsRepository;
import br.com.meufarol.financeiro.service.OrderPaymentService;

@Service
@Qualifier("orderPaymentServiceImpl")
public class OrderPaymentServiceImpl implements OrderPaymentService{
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private EnderecosRepository enderecosRepository;
	
	@Autowired
	private LojasRepository lojasRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private OrderResponseRepository orderResponseRepository;
	
	@Autowired
	private PaymentsRepository paymentsRepository;

	@Override
	public String buscarPor(String orderId) {
		throw new UnsupportedOperationException("Esse método não possui implementação");
	}

	@Override
	public OrderResponse salvar(@Valid OrderResponse novaOrder) {
		return orderResponseRepository.save(novaOrder);
	}

	@Override
	public OrderResponse buscarNoBancoDeDados(String orderId) {
		return orderResponseRepository.buscarOrderResponseSalva(orderId);
	}

	@Override
	public Payment salvarOrderPayment(Payment payment) {
		return paymentsRepository.save(payment);
	}

	@Override
	public String salvarPayment(Payment payment) {
		throw new UnsupportedOperationException("Esse método não possui implementação");
	}

	@Override
	public Order transformarResponseEmOrder(OrderResponse orderEncontrada) {
		Gson gson = new Gson();
		OrderJson orderJson = gson.fromJson(orderEncontrada.getJson(), OrderJson.class);

		
		Endereco enderecoLoja = new Endereco();
		enderecoLoja.setBairro(orderJson.getBusiness().getAddress().getNeighborhood());
		enderecoLoja.setCidade(orderJson.getBusiness().getAddress().getCity());
		enderecoLoja.setPais(orderJson.getBusiness().getAddress().getCountry());
		enderecoLoja.setNumero(orderJson.getBusiness().getAddress().getNumber());
		enderecoLoja.setRua(orderJson.getBusiness().getAddress().getStreet());
		enderecoLoja.setUf(orderJson.getBusiness().getAddress().getUf());
		enderecoLoja.setCep(orderJson.getBusiness().getAddress().getZipcode());
		enderecoLoja.setCompleto(orderJson.getBusiness().getAddress().toString());
		enderecosRepository.save(enderecoLoja);
		
		Endereco enderecoCliente = new Endereco();
		enderecoCliente.setBairro(orderJson.getCustomer().getAddress().getNeighborhood());
		enderecoCliente.setCidade(orderJson.getCustomer().getAddress().getCity());
		enderecoCliente.setPais(orderJson.getCustomer().getAddress().getCountry());
		enderecoCliente.setNumero(orderJson.getCustomer().getAddress().getNumber());
		enderecoCliente.setRua(orderJson.getCustomer().getAddress().getStreet());
		enderecoCliente.setUf(orderJson.getCustomer().getAddress().getUf());
		enderecoCliente.setCep(orderJson.getCustomer().getAddress().getZipcode());
		enderecoCliente.setCompleto(orderJson.getCustomer().getAddress().toString());
		enderecosRepository.save(enderecoCliente);
		
		Loja loja = new Loja();
		loja.setNome(orderJson.getBusiness().getName());
		loja.setDocumento(orderJson.getBusiness().getDocument());
		loja.setTelefone(orderJson.getBusiness().getPhone());
		loja.setId_endereco(enderecoLoja.getId());
		lojasRepository.save(loja);
		
		Cliente cliente = new Cliente();
		cliente.setNome(orderJson.getCustomer().getName());
		cliente.setDocumento(orderJson.getCustomer().getDocument());
		cliente.setEmail(orderJson.getCustomer().getEmail());
		cliente.setTelefone(orderJson.getCustomer().getPhone());
		cliente.setId_endereco(enderecoCliente.getId());
		clientesRepository.save(cliente);
		
		Order order = new Order();
		order.setId(orderJson.getId());
		order.setMetodo_pagamento(orderJson.getPayment_method());
		order.setTaxa(orderJson.getCharge());
		order.setTotal(orderJson.getTotal());
		order.setLogo(orderJson.getLogo());
		order.setId_cliente(cliente.getId());
		order.setId_loja(loja.getId());
		return order;
	}

	@Override
	public Order salvar(@Valid Order order) {
		return ordersRepository.save(order);
	}

}
