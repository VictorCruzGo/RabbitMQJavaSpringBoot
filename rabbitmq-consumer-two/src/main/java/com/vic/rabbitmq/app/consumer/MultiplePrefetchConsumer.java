package com.vic.rabbitmq.app.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.vic.rabbitmq.app.entity.DummyMessage;

//@Service
public class MultiplePrefetchConsumer {

	@RabbitListener(queues = "q.transaction", concurrency = "2")
	public void listenTransaction(DummyMessage message) throws InterruptedException {		
			System.out.println("transaction mensaje::: "+message.toString());
			Thread.sleep(100);
	}
	
	@RabbitListener(queues = "q.scheduler", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
	public void listenScheduler(DummyMessage message) throws InterruptedException {		
			System.out.println("scheduler mensaje::: "+message.toString());
			Thread.sleep(60000);
	}
}
