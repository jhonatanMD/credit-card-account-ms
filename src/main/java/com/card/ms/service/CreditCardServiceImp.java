package com.card.ms.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.card.ms.model.EntityCreditCard;
import com.card.ms.model.EntityTransaction;
import com.card.ms.repository.ICreditCardRepository;
import com.card.ms.webclient.CallWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardServiceImp implements ICreditCardService{
	
	@Autowired
	ICreditCardRepository repository;
	
	@Autowired
	@Qualifier("webClient")
	CallWebClient webClient;
	
	EntityTransaction transaction;
	List<EntityTransaction> listTransaction;
	
	@Override
	public Flux<EntityCreditCard> allCreditCard() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Mono<EntityCreditCard> saveCreditCard(final EntityCreditCard creditCard) {
		// TODO Auto-generated method stub
		List<String> docs = new ArrayList<>();
		docs.add(creditCard.getCustomer().getDniH());
		creditCard.setCash(creditCard.getCashLimit());
		
		return webClient.responde(docs).flatMap(res -> {
			if(res.getMsg().equals("")) {
				return repository.save(creditCard);
			}else {
				return null;
			}
		});
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
	public Mono<EntityTransaction> transactionCreditCard(String numCard, String tipo, Double cash,int cuotas) {
		// TODO Auto-generated method stub
		return repository.findByNumCard(numCard)
				.flatMap(p ->{
					List<String> dates = new ArrayList<>();
					transaction = new EntityTransaction();
					 transaction.setCashA(p.getCash());
					if(tipo.equals("c") && p.getCash() >= cash) {
						
						for(int i = 1 ; i <= cuotas ;i++ ) {
							dates.add(new SimpleDateFormat("yyyy-MM-dd").format(addDates(new Date(),i)));
						}
						p.setCashPay(cash / cuotas);
						p.setCuotas(cuotas);
						p.setDatesPay(dates);
						p.setCash(p.getCash() - cash);
						
					}else if (tipo.equals("p") && p.getCashLimit() >= (p.getCash() + cash) ){
						p.setCash( p.getCash() + cash);
						
						if(p.getCashLimit()== p.getCash()) {
							p.setStatus("1");
							p.setDatesPay(null);
							p.setCuotas(0);
						}
					}
					
					 transaction.setType(tipo);
					 transaction.setCashO(cash);
					 transaction.setCashT(p.getCash());
					 transaction.setDateTra(new Date());				
			 repository.save(p).subscribe();
			
			return  Mono.just(transaction);
			});
		
		
		
		
	
		
		
	}

	@Override
	public Flux<EntityCreditCard> creditCardByNumDoc(String numDoc) {
		// TODO Auto-generated method stub
		return repository.findByNumDoc(numDoc);
	}
	
	
	
	public Date addDates(Date date , int month){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	@Override
	public Flux<EntityCreditCard> creditCardByNumDocList(List<String> numDoc , String status) {
		return repository.findByNumDocList(numDoc,status);
	}
	

}
