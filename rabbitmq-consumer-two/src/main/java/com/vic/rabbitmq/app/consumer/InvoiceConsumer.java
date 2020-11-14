package com.vic.rabbitmq.app.consumer;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.vic.rabbitmq.app.entity.InvoiceCancelledMessage;
import com.vic.rabbitmq.app.entity.InvoiceCreateMessage;
import com.vic.rabbitmq.app.entity.InvoicePaidMessage;
import com.vic.rabbitmq.app.entity.PaymentCancelStatus;

//@Service
@RabbitListener(queues="q.invoice")
public class InvoiceConsumer {

	@RabbitHandler
	public void handleInvoiceCreated(InvoiceCreateMessage message) {
		System.out.println("on handleInvoiceCreated: "+message);
	}
	
	@RabbitHandler
	public void handleInvoicePaid(InvoicePaidMessage message) {
		System.out.println("on handleInvoicePaid: "+message);
	}
	
	@RabbitHandler(isDefault = true)
	public void handleDefault(Object message) {
		System.out.println("on handleDefault: "+message);
	}
	
	@RabbitHandler
	@SendTo("x.invoice.cancel/")
	public PaymentCancelStatus handleInvoiceCancelled(InvoiceCancelledMessage message) {
		var randomStatus=ThreadLocalRandom.current().nextBoolean();
		return new PaymentCancelStatus(randomStatus, LocalDate.now(), message.getInvoiceNumber());
	}
}
