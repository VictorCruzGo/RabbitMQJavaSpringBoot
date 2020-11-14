package com.vic.rabbitmq.app.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.rabbitmq.app.entity.Employee;

@Service
public class HumanResourceProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public void sendMessage(Employee emp) {
		try {
			var json = objectMapper.writeValueAsString(emp);
			rabbitTemplate.convertAndSend("x.hr","",json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
