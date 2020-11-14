package com.vic.rabbitmq.app.consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.vic.rabbitmq.app.entity.Picture;

@Service
public class MyPictureImageConsumer {

	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RabbitListener(queues="q.mypicture.image")
	//public void listen(String message) throws JsonMappingException, JsonProcessingException {
	//Message, representa todo el mensaje de rabbitmq
	//Channel, es como un tunel donde el mensaje corre entre java y rabbitmq
	public void listen(Message message, Channel channel) throws IOException {
		Picture p;
		p=objectMapper.readValue(message.getBody(), Picture.class);
		
		if(p.getSize()>9000) {
			System.out.println("Se genero una excepcion por el tamano de la imagen");
			//throw new IllegalArgumentException("Picture size too large:"+p);
			//throw new AmqpRejectAndDontRequeueException("Picture size too large:"+p);
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		}
		
		System.out.println("PictureImageConsumer: "+p);
		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		
	}
}
