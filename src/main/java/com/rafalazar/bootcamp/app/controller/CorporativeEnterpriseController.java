package com.rafalazar.bootcamp.app.controller;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.bootcamp.app.document.CorporativeEnterprise;
import com.rafalazar.bootcamp.app.dto.EnterpriseDto;
import com.rafalazar.bootcamp.app.service.CorporativeEnterpriseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/corportativeEnterprise")
public class CorporativeEnterpriseController {
	
	@Autowired
	private CorporativeEnterpriseService service;
	
	@GetMapping("/findAll")
	Flux<CorporativeEnterprise> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/findById/{id}")
	Mono<CorporativeEnterprise> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}
	
	@PostMapping("/create")
	public Mono<ResponseEntity<CorporativeEnterprise>> create(@RequestBody CorporativeEnterprise ce) {
		
		if(ce.getCreateAt() == null) {
			ce.setCreateAt(new Date());
		}	
		return service.save(ce).map(c -> ResponseEntity.created(URI.create("/plazoFijoVip/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(c));
	}
	
	//Esta es la forma correcta de eliminar un producto.F!
	@DeleteMapping("/deleteById/{id}")
	public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id) {
		return service.findById(id)
				.flatMap(cv -> {
					return service.delete(cv)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
				}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	
	//Esta es la forma correcta de actualizar - F!
	@PutMapping("/update/{id}")
	public Mono<ResponseEntity<CorporativeEnterprise>> update(@PathVariable String id, @RequestBody CorporativeEnterprise ce) {
		return service.update(ce, id)
				.map(c -> ResponseEntity.created(URI.create("/corportativeEnterprise".concat(c.getId())))
						.contentType(MediaType.APPLICATION_JSON).body(c))
				.defaultIfEmpty(ResponseEntity.notFound().build());
				
	}
	
	//--------------------------------------------------->
	@GetMapping("/findAllClients")
	public Flux<EnterpriseDto> findAllClients(){
		return service.findAllClients();
	}
	
	@PostMapping("/createById/{id}")
	public Mono<CorporativeEnterprise> createById(@PathVariable String id, @RequestBody CorporativeEnterprise ce){
		if(ce.getCreateAt() == null) {
			ce.setCreateAt(new Date());
		}
		return service.createById(id)
				.flatMap(c -> {
					ce.setRuc(c.getRuc());
					ce.setNumAccount(ce.getNumAccount());
					ce.setRazonSocial(c.getSocialName());
					ce.setAddress(c.getAddress());	
					ce.setAmount(ce.getAmount());
					
					createAcountById(id, ce);
					
					return service.save(ce);
				});
	}
	
	//------------------------------->
	//Experimental ! - 
	@PostMapping("/createAccount/{id}")
	public Mono<EnterpriseDto> findByIdClient(@PathVariable String id,@RequestBody CorporativeEnterprise ce){
		return service.findByIdDto(id)
				.flatMap(e -> {
					e.account.setId(ce.getId());
					e.account.setNumberAccount(ce.getNumAccount());
					e.account.setTypeAccount("Cuenta Empresarial Corporativa");
					e.account.setAmount(ce.getAmount());
					
					return service.saveDto(e);
				});
	}
	
	//-------EXPERIMENTAL 2!
	public Mono<EnterpriseDto> createAcountById(String id, CorporativeEnterprise ce){
		return service.findByIdDto(id)
				.flatMap(x -> {
					x.account.setNumberAccount(ce.getNumAccount());
					x.account.setTypeAccount("Cuenta Corporativa Empresarial");
					x.account.setAmount(ce.getAmount());
					
					return service.saveDto(x);
				});
	}
}