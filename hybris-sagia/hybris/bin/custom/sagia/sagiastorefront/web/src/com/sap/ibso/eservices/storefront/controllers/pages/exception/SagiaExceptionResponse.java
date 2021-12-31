package com.sap.ibso.eservices.storefront.controllers.pages.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SagiaExceptionResponse {

    @JsonProperty("description")
    private String description;
    @JsonProperty("message")
    private String message;

    public String getDescription() {
        return description;
    }

    public SagiaExceptionResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SagiaExceptionResponse setMessage(String message) {
        this.message = message;
        return this;
    }

}
