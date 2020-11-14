package com.vic.rabbitmq.app.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vic.rabbitmq.app.json.CustomLocalDateSerializer;

public class PaymentCancelStatus {
	
	private boolean cancelStatus;
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	private LocalDate cancelDate;
	private String invoiceNumber;
		
	public PaymentCancelStatus() {
		
	} 

	public PaymentCancelStatus(boolean cancelStatus, LocalDate cancelDate, String invoiceNumber) {
		super();
		this.cancelStatus = cancelStatus;
		this.cancelDate = cancelDate;
		this.invoiceNumber = invoiceNumber;
	}
	public boolean isCancelStatus() {
		return cancelStatus;
	}
	public void setCancelStatus(boolean cancelStatus) {
		this.cancelStatus = cancelStatus;
	}
	public LocalDate getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(LocalDate cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Override
	public String toString() {
		return "PaymentCancelStatus [cancelStatus=" + cancelStatus + ", cancelDate=" + cancelDate + ", invoiceNumber="
				+ invoiceNumber + "]";
	}
	
	
	

}
