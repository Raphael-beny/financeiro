package br.com.meufarol.financeiro.controller;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meufarol.financeiro.entity.Payment;
import br.com.meufarol.financeiro.service.OrderPaymentService;

@CrossOrigin("*")
@RestController
@RequestMapping("public/integration/")
public class OrderPaymentController {
	
	
	@Autowired
	@Qualifier("orderPaymentServiceProxy")
	private OrderPaymentService orderPaymentService;
	
	
	@GetMapping(value = "orders/{orderId}", produces = "application/json")
    public ResponseEntity<?> retornarPedido(@PathVariable("orderId") String orderId) {
		String resposta = orderPaymentService.buscarPor(orderId);
		return ResponseEntity.ok(resposta);
    }
	
	@PostMapping(value = "order-payment/{orderId}", produces = "application/json")
	@Transactional
	public ResponseEntity<?> efetuarPagamento(@PathVariable("orderId") String orderId, @RequestBody Payment payment){
		payment.setOrderId(orderId);
		String resposta = orderPaymentService.salvarPayment(payment);
		return ResponseEntity.ok(resposta);
	}

}
