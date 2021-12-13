package com.elite.orchestrator.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionEvent {
	
	private Long customerId;
	
	private String message;
	
	private Action action;
	
	private String status;
	
	private String email;
	
	private String phone;

	public static enum Action {
		TRANSACTION_FETCHED, TRANSACTION_PUT
	}
}
