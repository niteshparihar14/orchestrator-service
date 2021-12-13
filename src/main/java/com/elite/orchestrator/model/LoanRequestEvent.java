package com.elite.orchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanRequestEvent {

	private Long loanId; 
	
	private Long accountId;
	
	private Long customerId;
	
	private Status status;
	
	public enum Status{
		SUCCESS, FAILURE
	}
}
