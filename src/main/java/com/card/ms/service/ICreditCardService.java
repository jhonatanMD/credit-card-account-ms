package com.card.ms.service;

import java.util.List;

import com.card.ms.model.EntityCreditCard;
import com.card.ms.model.EntityTransaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditCardService {

	Flux<EntityCreditCard> allCreditCard();
	Flux<EntityCreditCard> creditCardByNumDoc(String numDoc);
	Mono<EntityCreditCard> saveCreditCard(final EntityCreditCard creditCard);
	Mono<EntityCreditCard> updCreditCard(final EntityCreditCard creditCard);
	Mono<Void> dltCreditCard(String id);
	Mono<EntityCreditCard> findByNumCard(String numCard);
	Mono<EntityTransaction> transactionCreditCard(String numCard , String tipo,Double cash,int cuotas);
	Flux<EntityCreditCard> creditCardByNumDocList(List<String> numDoc , String status);
}
