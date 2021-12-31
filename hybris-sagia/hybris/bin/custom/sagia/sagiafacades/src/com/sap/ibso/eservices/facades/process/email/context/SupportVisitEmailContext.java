package com.sap.ibso.eservices.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.commerceservices.model.process.SupportVisitEmailGenerationProcessModel;

/**
 *
 */
public class SupportVisitEmailContext extends CustomerEmailContext {

    @Override
    public void init(StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, EmailPageModel emailPageModel) {
        super.init(storeFrontCustomerProcessModel, emailPageModel);
        put("supportVisitNumber",((SupportVisitEmailGenerationProcessModel) storeFrontCustomerProcessModel).getSupportVisitNumber());
    }
}
