package com.elite.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrchestrationServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(OrchestrationServiceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public WebClient.Builder builderRef(){
		return WebClient.builder();
	}
	
	@Bean
	public WebClient client(WebClient.Builder builderRef) {
		return builderRef.build();
	}
	
	@Bean(name="lbRestTemplate")
	@LoadBalanced
	public RestTemplate lbRestTemplate() {
	    return new RestTemplate();
	}
	
	@Bean(name="restTemplate")
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
