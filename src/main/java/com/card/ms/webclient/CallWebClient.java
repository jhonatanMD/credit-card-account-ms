package com.card.ms.webclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.card.ms.model.EntityCreditCard;

import reactor.core.publisher.Mono;

public class CallWebClient  implements ICallWebClient{

	 WebClient client = WebClient.builder().baseUrl("http://localhost:8881")
			  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

	
	@Override
	public Mono<EntityCreditCard> pagoCreditCard(String numTarjeta, Double cash) {
		//
		return null;
	}

}
