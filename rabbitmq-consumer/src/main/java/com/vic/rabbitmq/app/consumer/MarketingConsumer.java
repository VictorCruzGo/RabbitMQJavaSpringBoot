package com.vic.rabbitmq.app.consumer;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vic.rabbitmq.app.entity.Employee;

@Service
public class MarketingConsumer {

	private ObjectMapper objectMapper=new ObjectMapper();
	
	@RabbitListener(queues="course.employee")
	public void listen(String message) {
		Employee emp;
		try {
			emp=objectMapper.readValue(message, Employee.class);
			System.out.println("Employee is: "+emp);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
