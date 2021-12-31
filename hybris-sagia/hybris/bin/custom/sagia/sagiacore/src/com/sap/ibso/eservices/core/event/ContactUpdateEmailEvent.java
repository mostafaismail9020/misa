package com.sap.ibso.eservices.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;

public class ContactUpdateEmailEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private String oldEmail;

    public ContactUpdateEmailEvent() {
        super();
    }

    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

}
