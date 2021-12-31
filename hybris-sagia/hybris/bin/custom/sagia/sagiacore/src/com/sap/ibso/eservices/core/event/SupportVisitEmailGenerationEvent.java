package com.sap.ibso.eservices.core.event;

import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.SupportVisitData;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;

public class SupportVisitEmailGenerationEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private SupportVisitData supportVisitData;

    public SupportVisitEmailGenerationEvent() {
        super();
    }

    public SupportVisitData getSupportVisitData() {
        return supportVisitData;
    }

    public void setSupportVisitData(SupportVisitData supportVisitData) {
        this.supportVisitData = supportVisitData;
    }
}
