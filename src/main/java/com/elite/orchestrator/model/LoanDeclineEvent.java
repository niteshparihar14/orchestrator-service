package com.elite.orchestrator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoanDeclineEvent {

	private Long loanId;
	
	private Long customerId;

	private LoanStatus status;

	public enum LoanStatus {
		NEW, APPROVED, INPROCESS, DECLINED, CLOSED
	}
}
