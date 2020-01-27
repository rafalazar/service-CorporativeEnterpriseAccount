package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.CorporativeEnterprise;
import com.rafalazar.bootcamp.app.dto.EnterpriseDto;

import reactor.core.publisher.Mono;

public interface CorporativeEnterpriseRepository extends ReactiveMongoRepository<CorporativeEnterprise, String>{
	
}
