package com.sap.ibso.eservices.sagiaservices.services;

/**
 * SagiaInvalidCRMResponseException
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaInvalidCRMResponseException extends RuntimeException {
    private String description;

    /**
     *
     */
    public SagiaInvalidCRMResponseException() {
    }

    /**
     * @param errorMessage errorMessage
     */
    public SagiaInvalidCRMResponseException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * @param errorMessage errorMessage
     * @param description description
     */
    public SagiaInvalidCRMResponseException(String errorMessage, String description) {
        super(errorMessage);
        this.description = description;
    }

    /**
     * @param message message
     * @param cause cause
     */
    public SagiaInvalidCRMResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @return
     */
    public String getDescription() {
        return this.description;
    }

}