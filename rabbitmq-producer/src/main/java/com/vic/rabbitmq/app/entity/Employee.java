package com.vic.rabbitmq.app.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vic.rabbitmq.app.json.CustomLocalDateSerializer;

public class Employee {
	
	@JsonProperty("employe_id")
	private String employeId;
	private String name;
	@JsonProperty("birt_date")
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private LocalDate birtDate;
	
	
	
	public Employee(String employeId, String name, LocalDate birtDate) {	
		this.employeId = employeId;
		this.name = name;
		this.birtDate = birtDate;
	}
	
	public String getEmployeId() {
		return employeId;
	}
	public void setEmployeId(String employeId) {
		this.employeId = employeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getBirtDate() {
		return birtDate;
	}
	public void setBirtDate(LocalDate birtDate) {
		this.birtDate = birtDate;
	}
}
