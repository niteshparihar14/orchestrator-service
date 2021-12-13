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
import com.elite.orchestrator.model.CustomerEvent;
import com.elite.orchestrator.model.LoanDeclineEvent;
import com.elite.orchestrator.model.NotificationRequest;
import com.elite.orchestrator.model.CustomerEvent.Status;
import com.elite.orchestrator.model.LoanDeclineEvent.LoanStatus;
import com.elite.orchestrator.rest.util.RestClient;

@SuppressWarnings("deprecation")
@Component
public class CustomerEventListener {

	@Autowired
	private RestClient restClient;

	@Value("${account.service.endpoint}")
	private String fetchAccountEndpoint;
	
	@Value("${customer.service.endpoint}")
	private String customerEndpoint;
	
	@Value("${loan.service.endpoint}")
	private String loanEndpoint;
	
	@Value("${notification.service.endpoint}")
	private String notificationEndpoint;

	private static final Logger logger = LoggerFactory.getLogger(CustomerEventListener.class);

	@StreamListener(target = OrchestratorChannel.INPUT_LOAN)
	public void listenValidateCustomer(@Payload CustomerEvent event) {

		logger.info("Received a transaction: " + event);
		logger.info("Validate account: " + event.getCustomerId());

		try {
			if(Status.SUCCESS.equals(event.getStatus())) {
				String message = "Loan request processed...";
				NotificationRequest notify = new NotificationRequest(message, event.getEmailId(), event.getPhone());
				restClient.doPost(notificationEndpoint + event.getCustomerId(), notify);
				LoanDeclineEvent updateEvent = new LoanDeclineEvent(event.getLoanId(), event.getCustomerId(),
						LoanStatus.NEW);
				restClient.doPostDecline(loanEndpoint + "update", updateEvent);
			} else {
				LoanDeclineEvent declineEvent = new LoanDeclineEvent(event.getLoanId(), event.getCustomerId(),
						LoanStatus.DECLINED);
				restClient.doPostDecline(loanEndpoint + "update", declineEvent);
				String message = "Loan request decline...";
				NotificationRequest notify = new NotificationRequest(message, event.getEmailId(), event.getPhone());
				restClient.doPost(notificationEndpoint + event.getCustomerId(), notify);
			}
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
	}
}
