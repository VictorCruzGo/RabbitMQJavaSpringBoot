package com.vic.rabbitmq.app.utility;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqScheduler {
	
	@Autowired
	private RabbitmqProxyService rabbitmqProxyService;
	
	//@Scheduled(fixedDelay = 90000)
	@Scheduled(fixedDelay = 1000)
	public void sweepDirtyQueues() {
		try {
			var dirtyQueues = rabbitmqProxyService.getAllQueues().stream()
					.filter(p->p.isDirty())
					.collect(Collectors.toList());
			
			dirtyQueues.forEach(q->System.out.println("Queue "+q.getName()+" has "+q.getMessages()+" unprocessed messages"));
		}
		catch(Exception e) {
			System.out.println("Cannot sweep queues:"+e.getMessage());
		}
	}
}
