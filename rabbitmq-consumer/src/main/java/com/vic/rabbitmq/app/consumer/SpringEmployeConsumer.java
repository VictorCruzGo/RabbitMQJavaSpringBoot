package com.vic.rabbitmq.app.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.rabbitmq.app.entity.Employee;

@Service
public class SpringEmployeConsumer {

	private ObjectMapper objectMapper = new ObjectMapper();

	@RabbitListener(queues = "q.spring2.accounting.work")
	public void listenAccounting(String message) throws IOException {
		var e = objectMapper.readValue(message, Employee.class);
		System.out.println("Consuming employee accounting: " + e);

		if ( org.apache.commons.lang3.StringUtils.isEmpty(e.getName())) {
			// throw exception, we will use DLX handler for retry mechanism
			System.out.println("COSUMER EMPLOYE EXCEPTION - On accounting, name is empty:"+e);
			throw new IOException("Name is empty");
		}
		System.out.println(" CONSUMER ACCOUNTING - On accounting: " + e);
	}
	
	@RabbitListener(queues = "q.spring2.marketing.work")
	public void listenMarketing(String message) throws IOException {
		var e = objectMapper.readValue(message, Employee.class);
		System.out.println("Consuming employee marketing: " +e); 
		System.out.println("CONSUMER MARKETING - On accounting: " + e);
	}

}
