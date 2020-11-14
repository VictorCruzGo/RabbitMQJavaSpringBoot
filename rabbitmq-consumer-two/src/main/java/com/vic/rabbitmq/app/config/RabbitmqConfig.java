package com.vic.rabbitmq.app.config;


import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitmqConfig {

	//Permite definir el valor de la distribucion de mensajes (fetchfactory)
	@Bean
	public RabbitListenerContainerFactory<SimpleMessageListenerContainer> prefetchOneContainerFactory(
			SimpleRabbitListenerContainerFactoryConfigurer configurer,
			ConnectionFactory connectionFactory
			){
		var factory=new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setPrefetchCount(1);
		
		return factory;
	}
	
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
