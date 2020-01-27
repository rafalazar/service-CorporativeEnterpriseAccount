package com.rafalazar.bootcamp.app.client;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.rafalazar.bootcamp.app.dto.EnterpriseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EnterpriseClient {
	@Autowired
	@Qualifier("ms-enterprise")
	private WebClient client;
	
	public Flux<EnterpriseDto> findAllClients(){
		return client.get().uri("/findAll")
				.retrieve()
				.bodyToFlux(EnterpriseDto.class);
	}
	
	public Mono<EnterpriseDto> createById(String id){
		return client.get().uri("/{id}", Collections.singletonMap("id", id))
				.retrieve()
				.bodyToMono(EnterpriseDto.class);
	}
}
