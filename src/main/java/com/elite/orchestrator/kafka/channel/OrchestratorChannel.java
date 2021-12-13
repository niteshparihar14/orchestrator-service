package com.elite.orchestrator.kafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

@SuppressWarnings("deprecation")
public interface OrchestratorChannel {

	String INPUT_TRANSACTION = "transaction-in";
	String INPUT_LOAN = "loan-in";
	String FETCH_ACCOUNT = "fetch-account";
	String CUSTOMER_EVENT = "customer-in";

	@Input(INPUT_TRANSACTION)
	SubscribableChannel inboundTransaction();
	
	@Input(INPUT_LOAN)
	SubscribableChannel inboundLoan();
	
	@Input(FETCH_ACCOUNT)
	SubscribableChannel fetchAccount();
	
	@Input(CUSTOMER_EVENT)
	SubscribableChannel inboundCustomer();
}
