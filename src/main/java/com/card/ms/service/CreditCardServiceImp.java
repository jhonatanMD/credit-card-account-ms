package com.card.ms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.ms.model.EntityCreditCard;
import com.card.ms.repository.ICreditCardRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceImp implements ICreditCardService{
	
	@Autowired
	ICreditCardRepository repository;

	@Override
	public Flux<EntityCreditCard> allCreditCard() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<EntityCreditCard> saveCreditCard(final EntityCreditCard creditCard) {
		// TODO Auto-generated method stub
		return repository.save(creditCard);
	}

	@Override
	public Mono<EntityCreditCard> updCreditCard(final EntityCreditCard creditCard) {
		// TODO Auto-generated method stub
		return repository.save(creditCard);
	}

	@Override
	public Mono<Void> dltCreditCard(String id) {
		// TODO Auto-generated method stub
		return repository.deleteById(id);
	}

	@Override
	public Mono<EntityCreditCard> creditCardDni(String dni) {
		// TODO Auto-generated method stub
		return repository.findByDniCli(dni);
	}

}
