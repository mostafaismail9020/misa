package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.event.ContactUpdateEmailEvent;
import com.sap.ibso.eservices.core.sagia.services.SagiaChangingEmailService;
import com.sap.ibso.eservices.facades.sagia.SagiaChangingEmailFacade;
import com.sap.ibso.eservices.facades.user.impl.SagiaCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.exceptions.PasswordMismatchException;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * DefaultSagiaChangingEmailFacade
 */
public class DefaultSagiaChangingEmailFacade extends SagiaCustomerFacade implements SagiaChangingEmailFacade{

    @Autowired
    private SagiaChangingEmailService sagiaChangingEmailService;

    @Resource
    private EventService eventService;

    @Resource
    private BaseStoreService baseStoreService;

    @Resource
    private BaseSiteService baseSiteService;

    @Override
    public void updateEmail(CustomerData customerData, String passw) throws PasswordMismatchException {

        final CustomerModel customer = getCurrentSessionCustomer();
        String oldEmail = customer.getUserNameEmail();
        customer.setUserNameEmail(customerData.getEmail());
        if (!getPasswordEncoderService().isValid(customer, passw))
        {
            throw new PasswordMismatchException(customerData.getEmail());
        }
        sagiaChangingEmailService.updateEmail(customer);
        eventService.publishEvent(initializeEvent(new ContactUpdateEmailEvent(), customerData, oldEmail));
    }

    protected AbstractCommerceUserEvent initializeEvent(final ContactUpdateEmailEvent event, final CustomerData customerData, final String oldEmail)
    {
        event.setBaseStore(baseStoreService.getCurrentBaseStore());
        event.setSite(baseSiteService.getCurrentBaseSite());
        event.setOldEmail(oldEmail);
        event.setCustomer((CustomerModel) getUserService().getCurrentUser());
        event.setLanguage(getCommonI18NService().getCurrentLanguage());
        event.setCurrency(getCommonI18NService().getCurrentCurrency());
        return event;
    }
    /**
     * @return
     */
    public SagiaChangingEmailService getSagiaChangingEmailService() {
        return sagiaChangingEmailService;
    }

    /**
     * @param sagiaChangingEmailService
     */
    public void setSagiaChangingEmailService(SagiaChangingEmailService sagiaChangingEmailService) {
        this.sagiaChangingEmailService = sagiaChangingEmailService;
    }
}
