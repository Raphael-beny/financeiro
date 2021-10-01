package br.com.meufarol.financeiro.camel.routes;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class OrderPaymentRoute extends RouteBuilder implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String tokenGerado = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL21ldWZhcm9sLmNvbS5ici9wdWJsaWMvaW50ZWdyYXRpb24vY3JlYXRlLWxvZ2luIiwiaWF0IjoxNjEyODkyMTg0LCJuYmYiOjE2MTI4OTIxODQsImp0aSI6Ing5WWdxMXN6MkxNU2RSM0oiLCJzdWIiOjI0LCJwcnYiOiI4N2UwYWYxZWY5ZmQxNTgxMmZkZWM5NzE1M2ExNGUwYjA0NzU0NmFhIn0.G5aoJsiQXRM2DYTGOd-WpLhc2jZtHL1AFAFGGDtvY38";
	
	//@Value("${meufarol.route}")
	//private String meufarol;
	
	private String endpointMeuFarol;
	
	@PostConstruct
	public void inicializar() {
		this.endpointMeuFarol = "https://meufarol.com.br/public/integration/";	
	}
	
	@Override
	public void configure() throws Exception {
		
		from("direct:buscarOrder")
		.id("consultar-order-meufarol")
			.doTry()
				.setProperty("tokenGerado", constant(tokenGerado))
				.setHeader(Exchange.HTTP_METHOD, HttpMethods.GET)
				.setHeader("Authorization", simple("${exchangeProperty[tokenGerado]}"))
				.toD(endpointMeuFarol+"orders/${body}")
			.doCatch(Exception.class)
				.setProperty("error", simple("${exception}"))
		.end();
		
//		from("direct:salvarPagamento")
//		.id("salvar-order-payment")
//			.doTry()
//				.setProperty("tokenGerado", constant(tokenGerado))
//				.process(exchange -> exchange.getIn().setBody("{\"msg\": \"Status atualizado com sucesso!\", \"success\": \"true\"}"))
//				//.process(exchange -> exchange.getIn().setBody("{\"msg\": \"Status atualizado com sucesso!\", \"success\": \"true\"}"))
//				.setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
//				.setHeader("Authorization", simple("${exchangeProperty[tokenGerado]}"))
//				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//				.toD(endpointMeuFarol+"order-payment/${body}/")
//			.doCatch(Exception.class)
//				.setProperty("error", simple("${exception}"))
//		.end();
		
		from("direct:salvarPagamento")		      
		.id("salvar-order-payment")
			.doTry()
				.setProperty("tokenGerado", constant(tokenGerado))
				.setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
				.setHeader("Authorization", simple("${exchangeProperty[tokenGerado]}"))
				.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						
											
						String body = exchange.getMessage().getBody(String.class);
						String array[] = new String[3];
						array = body.split(",");
						
						exchange.setProperty("order_id", array[2]);
						String id = array[1];
						String code = array[0];
						
						JSONObject bodyMap = new JSONObject();

						bodyMap.put("transaction_id", id);
						bodyMap.put("transaction_code", code);
						
						exchange.getMessage().setBody(bodyMap.toString());
						
					}
				})
				.toD(endpointMeuFarol + "order-payment/${exchangeProperty[order_id]}")
//				.process(new Processor() {				
//					@Override
//					public void process(Exchange exchange) throws Exception {
//						String body = exchange.getMessage().getBody(String.class);
//						JSONObject bodyMap = new JSONObject(body);
//						
//						JSONObject novoBodyMap = new JSONObject();
//						novoBodyMap.put("mensagem", bodyMap.opt("msg"));
//						novoBodyMap.put("status", bodyMap.opt("success"));
//						
//						exchange.getMessage().setBody(novoBodyMap.toString());
//					}
//				})
				//.log("${exchangeProperty[transaction_id]}")
				.log("${body}")
			.doCatch(Exception.class)
				.setProperty("error", simple("${exception}"))
				.process(new Processor() {					
					@Override
					public void process(Exchange exchange) throws Exception {						
						Exception ex = exchange.getProperty("error", Exception.class);
						ex.printStackTrace();
					}
				})
        .end();
		
		
	}
	
}
