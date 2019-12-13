package com.card.ms.service;

import com.card.ms.model.EntityCreditCard;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditCardService {

	Flux<EntityCreditCard> allCreditCard();
	Mono<EntityCreditCard> saveCreditCard(final EntityCreditCard creditCard);
	Mono<EntityCreditCard> updCreditCard(final EntityCreditCard creditCard);
	Mono<Void> dltCreditCard(String id);
	Mono<EntityCreditCard> creditCardDni(String dni);
	
}
