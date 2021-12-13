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
import com.elite.orchestrator.model.LoanDeclineEvent;
import com.elite.orchestrator.model.LoanRequestEvent;
import com.elite.orchestrator.model.LoanDeclineEvent.LoanStatus;
import com.elite.orchestrator.model.LoanRequestEvent.Status;
import com.elite.orchestrator.rest.util.RestClient;

@SuppressWarnings("deprecation")
@Component
public class FetchAccountEventListener {

	@Autowired
	private RestClient restClient;

	@Value("${account.service.endpoint}")
	private String fetchAccountEndpoint;
	
	@Value("${customer.service.endpoint}")
	private String customerEndpoint;
	
	@Value("${loan.service.endpoint}")
	private String loanEndpoint;

	private static final Logger logger = LoggerFactory.getLogger(FetchAccountEventListener.class);

	@StreamListener(target = OrchestratorChannel.FETCH_ACCOUNT)
	public void listenFetchAccount(@Payload LoanRequestEvent loanEvent) {

		logger.info("Received a transaction: " + loanEvent);
		logger.info("Going to call fetch account: " + loanEvent.getAccountId());

		restClient.doPostAccount(fetchAccountEndpoint, loanEvent);
	}
	
	@StreamListener(target = OrchestratorChannel.CUSTOMER_EVENT)
	public void listenValidateAccount(@Payload LoanRequestEvent loanEvent) {

		logger.info("Received a transaction: " + loanEvent);
		logger.info("Validate account: " + loanEvent.getAccountId());

		if(Status.SUCCESS.equals(loanEvent.getStatus())) {
			restClient.doPostCustomer(customerEndpoint + "loan", loanEvent);
		} else {
			LoanDeclineEvent event = new LoanDeclineEvent(loanEvent.getLoanId(), loanEvent.getCustomerId(),
					LoanStatus.DECLINED);
			restClient.doPostLoan(loanEndpoint + "update", event);
		}
	}
}
