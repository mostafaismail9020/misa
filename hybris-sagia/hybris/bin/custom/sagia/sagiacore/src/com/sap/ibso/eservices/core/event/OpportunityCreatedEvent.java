package com.sap.ibso.eservices.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;


public class OpportunityCreatedEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private String opportunityId;

    public String getOpportunityId() {
        return opportunityId;
    }
    public OpportunityCreatedEvent() {
        super();
    }


    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

}
