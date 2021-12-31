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
public class SagiaPaymentSessionEmailEvent extends AbstractEvent implements ClusterAwareEvent {
    private CustomerModel customer;
    private String secure3DId;
    private String paymentMap;

    public SagiaPaymentSessionEmailEvent(CustomerModel customer, String secure3DId, String paymentMap) {
        this.customer = customer;
        this.secure3DId = secure3DId;
        this.paymentMap = paymentMap;
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

    public String getSecure3DId() {
        return secure3DId;
    }

    public void setSecure3DId(String secure3DId) {
        this.secure3DId = secure3DId;
    }

    public String getPaymentMap() {
        return paymentMap;
    }

    public void setPaymentMap(String paymentMap) {
        this.paymentMap = paymentMap;
    }
}
