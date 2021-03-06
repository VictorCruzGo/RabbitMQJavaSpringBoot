package com.vic.rabbitmq.app.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vic.rabbitmq.app.entity.InvoiceCancelledMessage;
import com.vic.rabbitmq.app.entity.InvoiceCreateMessage;
import com.vic.rabbitmq.app.entity.InvoicePaidMessage;
import com.vic.rabbitmq.app.entity.InvoiceRejectedMessage;

@Service
public class InvoiceProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private static final String EXCHANGE = "x.invoice";
	
	public void sendInvoiceCreated(InvoiceCreateMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "",message);
	}
	
	public void sendInvoicePaid(InvoicePaidMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "",message);
	}
	
	public void sendInvoiceCancelled(InvoiceCancelledMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "",message);
	}
	
	public void sendInvoiceRejected(InvoiceRejectedMessage message) {
		rabbitTemplate.convertAndSend(EXCHANGE, "",message);
	}
}
