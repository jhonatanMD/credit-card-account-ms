package com.card.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.ms.model.EntityCreditCard;
import com.card.ms.service.CreditCardServiceImp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api")
public class ControllerCreditCard {

	@Autowired
	CreditCardServiceImp imple;
	
	@GetMapping("/getCreditCard")
	Flux<EntityCreditCard> getCreditCard(){
		return imple.allCreditCard();
	}
	
	@GetMapping("/getCreditCardDni/{dni}")
	Mono<EntityCreditCard> getCreditCardDni(@PathVariable("dni") String dni){
		return imple.creditCardDni(dni);
	}
	
	@PostMapping("/postCreditCard")
	Mono<EntityCreditCard> postCreditCard(@RequestBody EntityCreditCard creditCard){
		return imple.saveCreditCard(creditCard);
	}
	
	@PutMapping("/updCreditCard")
	Mono<EntityCreditCard> updCreditCard(@RequestBody EntityCreditCard creditCard){
		return imple.updCreditCard(creditCard);
	}
	
	@PostMapping("/updTransancionesCreditCard/{dni}/{tipo}/{cash}")
	public Mono<EntityCreditCard> updCurrentCash(@PathVariable("dni") String dni 
			,@PathVariable("tipo") String tipo ,@PathVariable("cash")  Double cash){
			return imple.creditCardDni(dni)
					.flatMap(p ->{
						if(tipo.equals("r") && p.getCash() >= cash) {
							p.setCash(p.getCash() - cash);
						}else if (tipo.equals("d")){
							p.setCash( p.getCash() + cash);
						}
				return imple.updCreditCard(p);
				});

	}
	
}
