package com.rafalazar.bootcamp.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.client.EnterpriseClient;
import com.rafalazar.bootcamp.app.document.CorporativeEnterprise;
import com.rafalazar.bootcamp.app.dto.EnterpriseDto;
import com.rafalazar.bootcamp.app.repository.CorporativeEnterpriseRepository;
import com.rafalazar.bootcamp.app.service.CorporativeEnterpriseService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CorporativeEnterpriseServiceImpl implements CorporativeEnterpriseService{

	@Autowired
	private EnterpriseClient client;
	
	@Autowired
	private CorporativeEnterpriseRepository repo;
	
	@Override
	public Flux<CorporativeEnterprise> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<CorporativeEnterprise> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<CorporativeEnterprise> save(CorporativeEnterprise ce) {
		return repo.save(ce);
	}

	@Override
	public Mono<CorporativeEnterprise> update(CorporativeEnterprise ce, String id) {
		return repo.findById(id)
				.flatMap(c -> {
					c.setRuc(ce.getRuc());
					c.setNumAccount(ce.getNumAccount());
					c.setRazonSocial(ce.getRazonSocial());
					c.setAddress(ce.getAddress());
					c.setAmount(ce.getAmount());
					c.setCreateAt(ce.getCreateAt());
					
					return repo.save(c);
				});
	}

	@Override
	public Mono<Void> delete(CorporativeEnterprise ce) {
		return repo.delete(ce);
	}

	@Override
	public Flux<EnterpriseDto> findAllClients() {
		return client.findAllClients();
	}

	@Override
	public Mono<EnterpriseDto> createById(String id) {
		return client.createById(id);
	}

}
