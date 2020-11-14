package com.vic.rabbitmq.app.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//import com.course.rabbitmqconsumer.entity.Picture;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.vic.rabbitmq.app.entity.Picture;

@Service
public class SpringPictureConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();

	@RabbitListener(queues = "q.spring.image.work")
	public void listenImage(String message) throws IOException {
		var p = objectMapper.readValue(message, Picture.class);
		System.out.println("Consuming image: " + p.getName());
		// process the image
		if (p.getSize() > 9000) {
			// throw exception, we will use DLX handler for retry mechanism
			System.out.println("COSUMER IMAGE EXCEPTION - Size too large");
			throw new IOException("Image " + p.getName() + " size too large:" + p.getSize());
		}
		System.out.println("Creating thumbnail & publishing image: " + p.getName());
	}
	
	@RabbitListener(queues = "q.spring.vector.work")
	public void listenVector(String message) throws IOException {
		var p = objectMapper.readValue(message, Picture.class);
		System.out.println("Consuming vector: " + p.getName()); 
		System.out.println("Converting to image, creating thumbnail & publishing vector: " + p.getName());
	}

}
