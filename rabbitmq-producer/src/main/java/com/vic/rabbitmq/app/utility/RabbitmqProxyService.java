package com.vic.rabbitmq.app.utility;

import java.time.Duration;
import java.util.Base64;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RabbitmqProxyService {
	
	public List<RabbitmpQueue> getAllQueues(){	
		var webClient=WebClient.create("http://localhost:15672/api/queues");
		
		return webClient.get().header("Authorization", createBasicAuthHeaders())
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<RabbitmpQueue>>() {
				}).block(Duration.ofSeconds(10));
	}

	private String createBasicAuthHeaders() {
		var auth="guest:guest";
		return "Basic "+Base64.getEncoder().encodeToString(auth.getBytes());
	}
}
