package com.sap.ibso.eservices.sagiaservices.exception;

/**
 * SagiaODataException
 */
/*
 * Suppress sonar warning (squid:S1165 | Exception classes should be immutable
 */
@SuppressWarnings("squid:S1165")
public class SagiaODataException extends RuntimeException {
    private static final long serialVersionUID = -3814025915355300910L;
    private String description;


    /**
     *
     */
    public SagiaODataException() {
    }


    /**
     * @param message message
     * @param cause cause
     */
    public SagiaODataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param errorMessage errorMessage
     */
    public SagiaODataException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * @param errorMessage errorMessage
     * @param description description
     */
    public SagiaODataException(String errorMessage, String description) {
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
