package com.elite.orchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerEvent {
	
	private Long customerId;
	
	private Long loanId;
	
	private String emailId;
	
	private String phone;
	
	private Status status;
	
	public enum Status{
		SUCCESS, FAILURE
	}
}
