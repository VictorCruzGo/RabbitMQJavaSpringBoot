package com.vic.rabbitmq.app.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.rabbitmq.app.entity.Employee;
import com.vic.rabbitmq.app.entity.Picture;

@Service
public class PictureLogConsumer {

	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RabbitListener(queues="q.picture.log")
	public void listen(String message) {
		Picture p;
		try {
			p=objectMapper.readValue(message, Picture.class);
			System.out.println("PictureLogConsumer: "+p);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
