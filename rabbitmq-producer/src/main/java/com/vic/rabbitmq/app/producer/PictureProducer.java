package com.vic.rabbitmq.app.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.rabbitmq.app.entity.Employee;
import com.vic.rabbitmq.app.entity.Picture;

@Service
public class PictureProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public void sendMessage(Picture p) {
		try {
			var json = objectMapper.writeValueAsString(p);
			rabbitTemplate.convertAndSend("x.picture",p.getType(),json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
