package com.vic.rabbitmq.app.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vic.rabbitmq.app.json.CustomLocalDateDeserializer;

public class InvoiceRejectedMessage {
	
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private LocalDate rejectDate;
	private String invoiceNumber;
	private String reason;
	
	public InvoiceRejectedMessage() {}
	
	public InvoiceRejectedMessage(LocalDate rejectDate, String invoiceNumber, String reason) {
		super();
		this.rejectDate = rejectDate;
		this.invoiceNumber = invoiceNumber;
		this.reason = reason;
	}
	public LocalDate getRejectDate() {
		return rejectDate;
	}
	public void setRejectDate(LocalDate rejectDate) {
		this.rejectDate = rejectDate;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "InvoiceRejectedMessage [rejectDate=" + rejectDate + ", invoiceNumber=" + invoiceNumber + ", reason="
				+ reason + "]";
	}
	
	
}
