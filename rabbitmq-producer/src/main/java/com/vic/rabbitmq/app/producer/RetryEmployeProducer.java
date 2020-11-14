package com.vic.rabbitmq.app.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.rabbitmq.app.entity.Employee;

@Service
public class RetryEmployeProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public void sendMessage(Employee e) throws JsonProcessingException {
		var json=objectMapper.writeValueAsString(e);
		rabbitTemplate.convertAndSend("x.guideline2.work","",json);
	}
}
