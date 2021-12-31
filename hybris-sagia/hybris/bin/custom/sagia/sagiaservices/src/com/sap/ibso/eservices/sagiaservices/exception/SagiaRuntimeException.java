package com.sap.ibso.eservices.sagiaservices.exception;

public class SagiaRuntimeException extends RuntimeException {
    public SagiaRuntimeException() {
        super();
    }

    public SagiaRuntimeException(String message) {
        super(message);
    }

    public SagiaRuntimeException(String message, Exception e) {
        super(message, e);
    }
}
