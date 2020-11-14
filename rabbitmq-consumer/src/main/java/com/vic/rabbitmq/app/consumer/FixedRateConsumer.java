package com.vic.rabbitmq.app.consumer;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRateConsumer {

//	@RabbitListener(queues = "course.fixedrate")
//	public void listen(String message) {
//		System.out.println("Consuming {}"+ message);
//	}
	
	@RabbitListener(queues = "course.fixedrate", concurrency = "3") //concurrency=3=consumidores
	public void listen(String message) {
		System.out.println("Consuming "+message+" on thread "+Thread.currentThread().getName());
		
		try {
			Thread.sleep(ThreadLocalRandom.current().nextLong(2000));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
