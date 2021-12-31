package com.sap.ibso.eservices.core.sagia.exception;

public class SagiaInconsistentHybrisNotificationData extends RuntimeException  {

    public SagiaInconsistentHybrisNotificationData() { }

    public SagiaInconsistentHybrisNotificationData(String errorMessage) {
        super(errorMessage);
    }

    public SagiaInconsistentHybrisNotificationData(String message, Throwable cause) {
        super(message, cause);
    }

    public String getErrorMessage() {
        return  super.getMessage();
    }
}
