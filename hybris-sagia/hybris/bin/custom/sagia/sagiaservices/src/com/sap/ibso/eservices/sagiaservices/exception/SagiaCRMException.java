package com.sap.ibso.eservices.sagiaservices.exception;

/**
 * SagiaCRMException
 */
/*
 * Suppress sonar warning (squid:S1165 | Exception classes should be immutable
 */
@SuppressWarnings("squid:S1165")
public class SagiaCRMException extends RuntimeException {
	private static final long serialVersionUID = -3814025915355300910L;
	private String description;

	/**
	 * this exception is used when we want to delegate further the CRM exception
	 */
	public SagiaCRMException() {
	}

	/**
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 */
	public SagiaCRMException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param errorMessage
	 *            errorMessage
	 */
	public SagiaCRMException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * @param errorMessage
	 *            errorMessage
	 * @param description
	 *            description
	 */
	public SagiaCRMException(String errorMessage, String description) {
		super(errorMessage);
		this.description = description;
	}

	/**
	 * @return String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @return String
	 */
	public String getErrorMessage() {
		return super.getMessage();
	}
}
