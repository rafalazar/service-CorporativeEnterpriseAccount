package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.document.CorporativeEnterprise;
import com.rafalazar.bootcamp.app.dto.EnterpriseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CorporativeEnterpriseService {
	
	public Flux<CorporativeEnterprise> findAll();
	
	public Mono<CorporativeEnterprise> findById(String id);
	
	public Mono<CorporativeEnterprise> save(CorporativeEnterprise ce);
	
	public Mono<CorporativeEnterprise> update(CorporativeEnterprise ce, String id);
	
	public Mono<Void> delete(CorporativeEnterprise ce);
	
	//--------------------------------------------------
	
	public Flux<EnterpriseDto> findAllClients();
	
	public Mono<EnterpriseDto> createById(String id);
}
