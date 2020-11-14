package com.vic.rabbitmq.app.scheduler;

import java.util.Date;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//Clase planificador (job) para detener los consumidores.
@Service
@EnableScheduling
public class RabbitmqScheduler {

	@Autowired
	private RabbitListenerEndpointRegistry registry;

	//turn on at 23.00
	//Formato «Segundos» «Minutos» «Horas» «Día del mes» «Mes» «Día de la semana» «Año»
	//@Scheduled(cron="0 0 23 * * ?")
	@Scheduled(cron="0 44 20 * * ?")
	public void stopAll() {
		registry.getListenerContainers().forEach(c->{
			System.out.println("Stoping container: "+ c + " hora: "+new Date());		
			c.stop();
		});
	}
	//turn on. one second after the mighnith. 00.01
	//Formato «Segundos» «Minutos» «Horas» «Día del mes» «Mes» «Día de la semana» «Año»
	//@Scheduled(cron="1 0 0 * * ?")
	@Scheduled(cron="1 46 20 * * ?")
	public void startAll() {
		registry.getListenerContainers().forEach(c->{
			System.out.println("Starting container: "+ c +" hora: "+new Date());
			c.start();
		});
	}	
}
