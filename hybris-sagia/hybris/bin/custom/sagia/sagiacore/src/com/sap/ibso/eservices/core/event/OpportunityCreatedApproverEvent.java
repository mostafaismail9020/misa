package com.sap.ibso.eservices.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;

import java.util.List;

public class OpportunityCreatedApproverEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private String opportunityId;
    private List<String> approversEmail;



    public String getOpportunityId() {
        return opportunityId;
    }
    public OpportunityCreatedApproverEvent() {
        super();
    }


    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }


    public List<String> getApproversEmail() {
        return approversEmail;
    }

    public void setApproversEmail(List<String> approversEmail) {
        this.approversEmail = approversEmail;
    }
}
