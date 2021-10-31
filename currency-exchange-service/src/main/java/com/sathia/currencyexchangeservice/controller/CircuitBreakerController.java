package com.sathia.currencyexchangeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

	@GetMapping("/sample-api")
	//@Retry(name="default")
	//@Retry(name="five", fallbackMethod = "hardCodeResponse")
	//@RateLimiter(name="default")
	//@Bulkhead(name="default")
	@CircuitBreaker(name="default", fallbackMethod = "hardCodeResponse")
	public String sampleApi() {
		logger.info("Retry --> Sample api new");
		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:9999/dummy", String.class);
		return response.getBody();
	}
	
	public String hardCodeResponse(Exception ex) {
		return "fallback method response";
	}
	
}
