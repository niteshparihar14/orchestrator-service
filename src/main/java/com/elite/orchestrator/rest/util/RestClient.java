package com.elite.orchestrator.rest.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.elite.orchestrator.exception.ResourceNotFoundException;
import com.elite.orchestrator.model.LoanDeclineEvent;
import com.elite.orchestrator.model.LoanRequestEvent;
import com.elite.orchestrator.model.NotificationRequest;

@Component
public class RestClient {
	
//	@Autowired
//	private WebClient client;
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("lbRestTemplate")
	private RestTemplate lbRestTemplate;

	public String doPost(String url, NotificationRequest event) throws ResourceNotFoundException {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			String response = restTemplate.postForObject(url, event, String.class);
			System.out.println("response *** " + response);
			return response;

		} catch (RestClientException e) {
			throw new ResourceNotFoundException("service unavailable");
		}

	}
	
	public ResponseEntity<String> doPostAccount(String url, LoanRequestEvent event) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			System.out.println("url *** " + url);

			HttpEntity<LoanRequestEvent> httpEntity = new HttpEntity<>(event, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			System.out.println("response *** " + response);
			return response;

		} catch (RestClientException e) {
			e.getMessage();
		}
		return null;
	}
	
	public ResponseEntity<String> doPostDecline(String url, LoanDeclineEvent event) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<LoanDeclineEvent> httpEntity = new HttpEntity<>(event, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			System.out.println("response *** " + response);
			return response;

		} catch (RestClientException e) {
			e.getMessage();
		}
		return null;
	}
	
	public ResponseEntity<String> doPostCustomer(String url, LoanRequestEvent event) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			System.out.println("url *** " + url);

			HttpEntity<LoanRequestEvent> httpEntity = new HttpEntity<>(event, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			System.out.println("response *** " + response);
			return response;
		} catch (RestClientException e) {
			e.getMessage();
		}
		return null;
	}
	
	public ResponseEntity<String> doPostLoan(String url, LoanDeclineEvent event) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<LoanDeclineEvent> httpEntity = new HttpEntity<>(event, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			System.out.println("response *** " + response);
			return response;

		} catch (RestClientException e) {
			e.getMessage();
		}
		return null;
	}
}
