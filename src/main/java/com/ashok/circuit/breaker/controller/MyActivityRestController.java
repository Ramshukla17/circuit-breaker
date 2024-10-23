package com.ashok.circuit.breaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ashok.circuit.breaker.model.Activity;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class MyActivityRestController {

	@GetMapping("/")
	@CircuitBreaker(name = "bored-api", fallbackMethod = "fallbackActivity")
	public String invokeBoredApi() {
		
		String url = "https://bored-api.appbrewery.com/random";
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Activity> forEntity = rt.getForEntity(url, Activity.class);
		Activity body = forEntity.getBody();
		String activity = body.getActivity();
		
		return activity;
	}
	
	public String fallbackActivity(Throwable throwable) {
        return "Hi"; // Return a default response in case of failure
    }
}
