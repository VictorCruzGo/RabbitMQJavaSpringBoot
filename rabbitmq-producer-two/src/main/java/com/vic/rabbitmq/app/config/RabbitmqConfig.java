package com.vic.rabbitmq.app.config;


import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Clase para realizar el mapper automaticamente.
@Configuration
public class RabbitmqConfig {
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
		RabbitTemplate template=new RabbitTemplate(connectionFactory);
		template.setMessageConverter(converter);
		
		return template;
	}
	
	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
}
