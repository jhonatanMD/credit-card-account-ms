package com.card.ms.webclient;

import com.card.ms.model.EntityCreditCard;

import reactor.core.publisher.Mono;

public interface ICallWebClient {

	Mono<EntityCreditCard> pagoCreditCard(String numTarjeta,Double cash);
	
}
