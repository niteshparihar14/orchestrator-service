package com.elite.orchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRequest {

	private String message;

	private String email;
	
	private String phone;
}
