package com.sap.ibso.eservices.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;


public class OpportunityUpdatedEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private String opportunityId;
    private String currentState;

    public String getCurrentState() {
        return currentState;
    }
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
    public String getOpportunityId() {
        return opportunityId;
    }
    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }
    public OpportunityUpdatedEvent() {
        super();
    }

}
