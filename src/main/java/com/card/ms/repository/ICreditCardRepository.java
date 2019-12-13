package com.card.ms.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.card.ms.model.EntityCreditCard;

import reactor.core.publisher.Mono;
@Repository
public interface ICreditCardRepository  extends ReactiveMongoRepository<EntityCreditCard, String>{

	Mono<EntityCreditCard> findByDniCli(String dniCli);
	
}