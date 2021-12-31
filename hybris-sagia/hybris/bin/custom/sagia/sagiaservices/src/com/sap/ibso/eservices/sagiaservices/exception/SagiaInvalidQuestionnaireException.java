package com.sap.ibso.eservices.sagiaservices.exception;

public class SagiaInvalidQuestionnaireException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    public SagiaInvalidQuestionnaireException() {
    }

    public SagiaInvalidQuestionnaireException(String message, Throwable cause) {
        super(message, cause);
    }

    public SagiaInvalidQuestionnaireException(String errorMessage) {
        super(errorMessage);
    }


    public String getErrorMessage() {
        return super.getMessage();
    }
}
