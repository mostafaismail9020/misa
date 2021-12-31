package com.sap.ibso.eservices.storefront.exceptions;

/**
 * SagiaUserWithoutLicenseException
 */
public class SagiaUserWithoutLicenseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SagiaUserWithoutLicenseException() {
	}

	/**
	 * @param message
	 *            message
	 * @param cause
	 *            cause
	 */
	public SagiaUserWithoutLicenseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param errorMessage
	 *            errorMessage
	 */
	public SagiaUserWithoutLicenseException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * @return String
	 */
	public String getErrorMessage() {
		return super.getMessage();
	}
}
