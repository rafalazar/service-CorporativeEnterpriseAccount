package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.CorporativeEnterprise;

public interface CorporativeEnterpriseRepository extends ReactiveMongoRepository<CorporativeEnterprise, String>{
	
}
