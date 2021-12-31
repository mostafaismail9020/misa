package com.sap.ibso.eservices.core.sagia.exception;

public class SagiaItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5162703428306410313L;

	public SagiaItemNotFoundException() {
	}

	public SagiaItemNotFoundException(String errorMessage) {
		super(errorMessage);
	}

	public SagiaItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
