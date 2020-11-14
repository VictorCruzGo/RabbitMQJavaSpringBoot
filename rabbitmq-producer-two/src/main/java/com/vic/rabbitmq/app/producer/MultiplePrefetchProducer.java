package com.vic.rabbitmq.app.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vic.rabbitmq.app.entity.DummyMessage;

@Service
public class MultiplePrefetchProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendSimulateTransaction() {
		for(int i=0;i<20000;i++) {
			var message=new  DummyMessage("transacction "+i,i);
			rabbitTemplate.convertAndSend("x.transaction","",message);	
		}		
	}
	
	public void sendSimulateScheduler() {
		for(int i=0;i<200;i++) {
			var message=new  DummyMessage("scheduler "+i,i);
			rabbitTemplate.convertAndSend("x.scheduler","",message);	
		}		
	}
}
