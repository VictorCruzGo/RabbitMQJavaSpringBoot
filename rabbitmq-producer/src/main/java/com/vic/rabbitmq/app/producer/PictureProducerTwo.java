package com.vic.rabbitmq.app.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.rabbitmq.app.entity.Employee;
import com.vic.rabbitmq.app.entity.Picture;

@Service
public class PictureProducerTwo {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private ObjectMapper objectMapper=new ObjectMapper();
	
	public void sendMessage(Picture p) throws JsonProcessingException {
		var sb=new StringBuilder();
		
		//source.size.type
		sb.append(p.getSource());
		sb.append(".");
		
		if(p.getSize()>400) {
			sb.append("large");
		} 			
		else {
			sb.append("small");
		}
			
		sb.append(".");
		
		sb.append(p.getType());
		
		var routingKey=sb.toString();
		
		var json = objectMapper.writeValueAsString(p);
		
		rabbitTemplate.convertAndSend("x.picture2",routingKey,json);		
	}
}
