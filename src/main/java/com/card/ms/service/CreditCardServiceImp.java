package com.card.ms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.ms.model.EntityCreditCard;
import com.card.ms.model.EntityTransaction;
import com.card.ms.repository.ICreditCardRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceImp implements ICreditCardService{
	
	@Autowired
	ICreditCardRepository repository;
	EntityTransaction transaction;
	List<EntityTransaction> listTransaction;
	Date dt = new Date();
	
	
	
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
	public Mono<EntityCreditCard> findByNumCard(String numCard) {
		// TODO Auto-generated method stub
		return repository.findByNumCard(numCard);
	}

	@Override
	public Mono<EntityCreditCard> transactionCreditCard(String numCard, String tipo, Double cash) {
		// TODO Auto-generated method stub
		return repository.findByNumCard(numCard)
				.flatMap(p ->{
					transaction = new EntityTransaction();
					 transaction.setCashA(p.getCash());
					if(tipo.equals("c") && p.getCash() >= cash) {
						p.setCash(p.getCash() - cash);
					}else if (tipo.equals("p") && p.getCashLimit() >= (p.getCash() + cash) ){
						p.setCash( p.getCash() + cash);
					}
					transaction.setType(tipo);
					 transaction.setCashO(cash);
					 transaction.setCashT(p.getCash());
					 transaction.setDateTra(dt);
					listTransaction = new ArrayList<>();
					if(p.getTransactions()!=null)
					{
						p.getTransactions().forEach(transac-> {
							listTransaction.add(transac);
						});
					}
					listTransaction.add(transaction);
					p.setTransactions(listTransaction);
			return repository.save(p);
			});
		
		
		
		
	
		
		
	}

}
