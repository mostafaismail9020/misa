package com.sap.ibso.eservices.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;

public class CustomerUserGroupUpdateEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private String userGroup;
    private CustomerModel customerModel;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public CustomerUserGroupUpdateEvent() {
        super();
    }

    public CustomerUserGroupUpdateEvent(String userGroup) {
        super();
        this.userGroup = userGroup;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
