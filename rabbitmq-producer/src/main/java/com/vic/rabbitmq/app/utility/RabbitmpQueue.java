package com.vic.rabbitmq.app.utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RabbitmpQueue {
	//represent json response
	//message count
	@JsonProperty
	private long messages;
	
	@JsonProperty
	private String name;

	
	public long getMessages() {
		return messages;
	}

	public void setMessages(long messages) {
		this.messages = messages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//One logic for check dirty queue
	public boolean isDirty() {
		return messages>0;
	}
}
