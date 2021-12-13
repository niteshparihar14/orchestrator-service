package com.elite.orchestrator.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.elite.orchestrator.exception.ResourceNotFoundException;
import com.elite.orchestrator.kafka.channel.OrchestratorChannel;
import com.elite.orchestrator.kafka.message.TransactionEvent;
import com.elite.orchestrator.model.NotificationRequest;
import com.elite.orchestrator.rest.util.RestClient;

@SuppressWarnings("deprecation")
@Component
public class LoanRequestEventListener {

	@Autowired
	private RestClient restClient;

	@Value("${notification.service.endpoint}")
	private String notificationEndpoint;

	private static final Logger logger = LoggerFactory.getLogger(LoanRequestEventListener.class);

//	@StreamListener(target = OrchestratorChannel.INPUT_TRANSACTION)
//	public void listenTransactionPlaced(@Payload TransactionEvent transactionEvent) {
//		
//		if (TransactionEvent.Action.TRANSACTION_PUT.equals(transactionEvent.getAction())) {
//			logger.info("Received a transaction: " + transactionEvent);
//			logger.info("Going to call notification service for customer: " + transactionEvent.getCustomerId());
//
//			try {
//				NotificationRequest notify = new NotificationRequest(transactionEvent.getMessage(),
//						transactionEvent.getEmail(), transactionEvent.getPhone());
//				restClient.doPost(notificationEndpoint+transactionEvent.getCustomerId(), notify);
//			} catch (ResourceNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
