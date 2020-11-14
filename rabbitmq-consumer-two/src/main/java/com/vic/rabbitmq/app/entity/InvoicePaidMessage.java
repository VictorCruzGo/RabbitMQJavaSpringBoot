package com.vic.rabbitmq.app.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vic.rabbitmq.app.json.CustomLocalDateDeserializer;

public class InvoicePaidMessage {
	
	private String invoiceNumber;
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private LocalDate paidDate;
	
	private String paymentNumber;
	
	public InvoicePaidMessage() {
		
	}
				
	public InvoicePaidMessage(String invoiceNumber, LocalDate paidDate, String paymentNumber) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.paidDate = paidDate;
		this.paymentNumber = paymentNumber;
	}


	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}




	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public LocalDate getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(LocalDate paidDate) {
		this.paidDate = paidDate;
	}


	@Override
	public String toString() {
		return "InvoicePaidMessage [invoiceNumber=" + invoiceNumber + ", paidDate=" + paidDate + ", paymentNumber="
				+ paymentNumber + "]";
	}
	
	
	
}
