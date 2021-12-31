package com.sap.ibso.eservices.core.event;

import com.sap.ibso.eservices.core.util.DateUtils;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.PublishEventContext;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Sagia Payment API error event, which contains API relevant information when a Sagia Payment API error is in place.
 */
public class SagiaPaymentApiErrorEvent extends AbstractEvent implements ClusterAwareEvent {
    private CustomerModel customer;
    private String apiUrl;
    private String errorMessage;
    private String httpMethod;
    private String requestBody;
    private String responseBody;
    private Date creationDate;

    /**
     * Empty constructor.
     */
    public SagiaPaymentApiErrorEvent() {
        super();
        this.creationDate = DateUtils.convertToDateViaInstant(LocalDateTime.now());
    }

    /**
     * Constructor with all parameters except creation date, automatically created.
     *
     * @param customer
     * @param apiUrl
     * @param errorMessage
     * @param httpMethod
     * @param requestBody
     */
    public SagiaPaymentApiErrorEvent(CustomerModel customer, String apiUrl, String errorMessage, String httpMethod, String requestBody, String responseBody) {
        this.customer = customer;
        this.apiUrl = apiUrl;
        this.errorMessage = errorMessage;
        this.httpMethod = httpMethod;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.creationDate = DateUtils.convertToDateViaInstant(LocalDateTime.now());
    }

    /**
     * Publishing to all nodes
     * @param publishEventContext
     * @return
     */
    @Override
    public boolean canPublish(PublishEventContext publishEventContext) {
        return true;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
