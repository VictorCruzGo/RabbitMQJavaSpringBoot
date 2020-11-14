package com.vic.rabbitmq.app;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vic.rabbitmq.app.entity.Employee;
import com.vic.rabbitmq.app.entity.Picture;
import com.vic.rabbitmq.app.producer.EmployeeJsonProducer;
import com.vic.rabbitmq.app.producer.HelloRabbitProducer;
import com.vic.rabbitmq.app.producer.HumanResourceProducer;
import com.vic.rabbitmq.app.producer.MyPictureProducer;
import com.vic.rabbitmq.app.producer.PictureProducer;
import com.vic.rabbitmq.app.producer.PictureProducerTwo;
import com.vic.rabbitmq.app.producer.RetryEmployeProducer;
import com.vic.rabbitmq.app.producer.RetryPictureProducer;
import com.vic.rabbitmq.app.producer.SpringEmployeProducer;
import com.vic.rabbitmq.app.producer.SpringPictureProducer;

@SpringBootApplication
//@EnableScheduling //Hablita la plafinicacion de ejecuciones
public class RabbitmqProducerApplication implements CommandLineRunner {

	@Autowired
	private HelloRabbitProducer helloRabbitProducer;
	
	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;
	
	@Autowired
	private HumanResourceProducer humanResourceProducer;
	
	@Autowired
	private PictureProducer pictureProducer;
	
	@Autowired
	private PictureProducerTwo pictureProducerTwo;
	
	@Autowired
	private MyPictureProducer myPictureProducer;
	
	@Autowired
	private RetryPictureProducer retryPictureProducer;
	
	@Autowired
	private RetryEmployeProducer retryEmployeProducer;
	
	@Autowired
	private SpringPictureProducer springPictureProducer;
	
	@Autowired
	private SpringEmployeProducer springEmployeProducer;
	
	private final List<String> SOURCES=List.of("mobile","web");
	private final List<String> TYPES=List.of("jpg","png","svg");	
	
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		//producirUnMensaje();
		//producirMensajesJson();
		//producirMensajesJsonExchangeFanOut();
		//producirPictureDirect();
		//producirPictureTopic();
		//producirMyPictureFanout();
		//producirRetryPictureDirect();
		//producirRetryEmployeeFanout();
		//producirPictureSpring();
		producirEmployeeSpringFanout();
	}
	
	public void producirUnMensaje(){
		helloRabbitProducer.sendHello("Victor "+Math.random());		
	}
	
	public void producirMilMensajesConDelay() throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			Thread.sleep(1000);
			helloRabbitProducer.sendHello("Victor "+Math.random());	
		}
	}
	
	public void producirMensajesJson(){
		for (int i = 0; i < 500; i++) {
			var e=new Employee("emp "+i, "employee "+i, LocalDate.now());
			employeeJsonProducer.sendMessage(e);
		}		
	}
	
	public void producirMensajesJsonExchangeFanOut(){
		for (int i = 0; i < 5; i++) {
			var e=new Employee("emp "+i, "employee "+i, LocalDate.now());
			humanResourceProducer.sendMessage(e);
		}		
	}
	
	public void producirPictureDirect(){
		for (int i = 0; i < 10; i++) {
			var p=new Picture();
			p.setName("Picture "+i);
			p.setSize(ThreadLocalRandom.current().nextLong(1,1001));
			p.setSource(SOURCES.get(i%SOURCES.size()));
			p.setType(TYPES.get(i%TYPES.size()));
			pictureProducer.sendMessage(p);
		}		
	}
	
	public void producirPictureTopic() throws JsonProcessingException{
		for (int i = 0; i < 10; i++) {
			var p=new Picture();
			p.setName("Picture "+i);
			p.setSize(ThreadLocalRandom.current().nextLong(1,1001));
			p.setSource(SOURCES.get(i%SOURCES.size()));
			p.setType(TYPES.get(i%TYPES.size()));
			pictureProducerTwo.sendMessage(p);
		}		
	}
	
	public void producirMyPictureFanout() throws JsonProcessingException{
		for (int i = 0; i < 1; i++) {
			var p=new Picture();
			p.setName("Picture "+i);
			p.setSize(ThreadLocalRandom.current().nextLong(9001,10001));
			p.setSource(SOURCES.get(i%SOURCES.size()));
			p.setType(TYPES.get(i%TYPES.size()));
			myPictureProducer.sendMessage(p);
		}		
	}
	
	public void producirRetryPictureDirect() throws JsonProcessingException{
		for (int i = 0; i < 3; i++) {
			var p=new Picture();
			p.setName("Picture "+i);
			p.setSize(ThreadLocalRandom.current().nextLong(9001,10001));
			p.setSource(SOURCES.get(i%SOURCES.size()));
			p.setType(TYPES.get(i%TYPES.size()));
			retryPictureProducer.sendMessage(p);
		}		
	}
	
	
	public void producirRetryEmployeeFanout() throws JsonProcessingException{
		for (int i = 0; i < 3; i++) {
			Employee e=new Employee("PRODUCER Employe id"+i, null, LocalDate.now());
			retryEmployeProducer.sendMessage(e);
		}		
	}
	
	public void producirPictureSpring() throws JsonProcessingException{
		for (int i = 0; i < 1; i++) {
			var p=new Picture();
			p.setName("Picture "+i);
			p.setSize(ThreadLocalRandom.current().nextLong(9001,10001));
			p.setSource(SOURCES.get(i%SOURCES.size()));
			p.setType(TYPES.get(i%TYPES.size()));
			springPictureProducer.sendMessage(p);
		}		
	}
	
	public void producirEmployeeSpringFanout() throws JsonProcessingException{
		for (int i = 0; i < 1; i++) {
			Employee e=new Employee("PRODUCER Employe id"+i, null, LocalDate.now());
			springEmployeProducer.sendMessage(e);
		}		
	}
}
