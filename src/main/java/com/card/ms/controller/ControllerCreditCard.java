package com.card.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.ms.model.EntityCreditCard;
import com.card.ms.model.EntityTransaction;
import com.card.ms.service.ICreditCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class ControllerCreditCard {

	@Autowired
	ICreditCardService imple;
	
	@GetMapping("/getCreditCard")
	Flux<EntityCreditCard> getCreditCard(){
		return imple.allCreditCard();
	}
	
	
	@GetMapping("/getCreditCardNumDoc/{numDoc}")
	Flux<EntityCreditCard> getCreditCardNumDoc(@PathVariable("numDoc") String numDoc){
		return imple.creditCardByNumDoc(numDoc);
	}
	
	
	@GetMapping("/getCreditCarNumAcc/{numCard}")
	Mono<EntityCreditCard> getCreditCardDni(@PathVariable("numCard") String numCard){
		return imple.findByNumCard(numCard);
	}
	
	@PostMapping("/postCreditCard")
	Mono<EntityCreditCard> postCreditCard(@RequestBody EntityCreditCard creditCard){
		return imple.saveCreditCard(creditCard);
	}
	
	@PutMapping("/updCreditCard")
	Mono<EntityCreditCard> updCreditCard(@RequestBody EntityCreditCard creditCard){
		return imple.updCreditCard(creditCard);
	}
	
	@PostMapping("/updTransancionesCreditCard/{numCard}/{tipo}/{cash}/{cuotas}")
	public Mono<EntityTransaction> updCurrentCash(@PathVariable("numCard") String numCard 
			,@PathVariable("tipo") String tipo ,@PathVariable("cash")  Double cash,@PathVariable("cuotas") int cuotas){
			return imple.transactionCreditCard(numCard, tipo, cash,cuotas);

	}
	
	@PostMapping("/creditCardByNumDocList/{status}")
	public Flux<EntityCreditCard> creditCardByNumDocList(@RequestBody List<String> numDoc
			,@PathVariable("status") String status){
		return imple.creditCardByNumDocList(numDoc,status);
		
	}
	
}
