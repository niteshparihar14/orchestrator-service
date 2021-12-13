package com.elite.orchestrator.kafka.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationEvent {

	private Long customerId;

	private String emailAddress;

	private String message;

	private String phone;

	public static enum Action {
		NOTIFICATION_FETCHED, NOTIFICATION_PUT
	}
}
