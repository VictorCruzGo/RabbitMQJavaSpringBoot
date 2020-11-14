package com.vic.rabbitmq.app.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.vic.rabbitmq.app.entity.DummyMessage;

@Service
public class AnotherDummyConsumer {

	@RabbitListener(queues = "q.dummy.another")
	public void listenDummy(DummyMessage message) {		
			System.out.println("mensaje::: "+message.toString());			
	}
}
