package com.sap.ibso.eservices.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.commerceservices.model.process.UpdateContactEmailProcessModel;

/**
 *
 */
public class ContactUpdateEmailContext extends CustomerEmailContext {

    /**
     * @param storeFrontCustomerProcessModel
     * @param emailPageModel
     */
    @Override
    public void init(StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, EmailPageModel emailPageModel) {
        super.init(storeFrontCustomerProcessModel, emailPageModel);

        put("oldEmail", ((UpdateContactEmailProcessModel) storeFrontCustomerProcessModel).getOldEmail());
        put("newEmail", ((UpdateContactEmailProcessModel) storeFrontCustomerProcessModel).getCustomer().getUserNameEmail());
    }
}
