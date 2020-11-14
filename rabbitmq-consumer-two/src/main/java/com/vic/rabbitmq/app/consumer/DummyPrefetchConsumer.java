package com.vic.rabbitmq.app.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.vic.rabbitmq.app.entity.DummyMessage;

//@Service
public class DummyPrefetchConsumer {

	@RabbitListener(queues = "q.dummy", concurrency = "2")
	public void listenDummy(DummyMessage message) throws InterruptedException {		
			System.out.println("mensaje::: "+message.toString());
			Thread.sleep(20000);
	}
}
