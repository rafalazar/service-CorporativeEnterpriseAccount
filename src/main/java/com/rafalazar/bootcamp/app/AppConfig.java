package com.rafalazar.bootcamp.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	@Bean
	@Qualifier("ms-enterprise")
	public WebClient getClient() {
		return WebClient.create("http://localhost:8092/api/enterprise_clients");   
	}

}
