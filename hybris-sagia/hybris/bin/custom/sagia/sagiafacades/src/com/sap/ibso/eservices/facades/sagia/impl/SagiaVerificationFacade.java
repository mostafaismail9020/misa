package com.sap.ibso.eservices.facades.sagia.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hsqldb.lib.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sap.ibso.eservices.core.event.EmailVerificationEvent;
import com.sap.ibso.eservices.core.event.TwoFactorAuthenticationEmailEvent;
import com.sap.ibso.eservices.sagiaservices.data.zverifyModuleSrvOData.EmailSubscriptionFeeData;
import com.sap.ibso.eservices.sagiaservices.data.zverifyModuleSrvOData.EmailVerificationData;
import com.sap.ibso.eservices.sagiaservices.services.impl.EmailSubscriptionFeeSetService;
import com.sap.ibso.eservices.sagiaservices.services.impl.EmailVerificationSetService;
import org.springframework.context.MessageSource;


import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

public class SagiaVerificationFacade {
	
    private static final Logger LOG = LoggerFactory.getLogger(SagiaVerificationFacade.class);
    
	@Resource(name = "i18nService")
	private I18NService i18nService;
	@Resource
	private MessageSource messageSource;
    @Resource
    private ModelService modelService;

	EmailVerificationSetService emailVerificationSetService;
    private EmailSubscriptionFeeSetService emailSubscriptionFeeSetService;
    private EventService eventService;
    private BaseStoreService baseStoreService;
    private BaseSiteService baseSiteService;
    private CommonI18NService commonI18NService;
    
    

	public EmailSubscriptionFeeData getEmailSubscriptionFee(String id) {
    	EmailSubscriptionFeeData emailSubscriptionFeeData = emailSubscriptionFeeSetService.get(id);
        return emailSubscriptionFeeData;
    }

	public EmailVerificationData getEmailVerification(String id) {
    	EmailVerificationData emailVerificationData = emailVerificationSetService.get(id);
        return emailVerificationData;
    }
    
    public void createEmailVerification(EmailVerificationData emailVerificationData) {
    	emailVerificationSetService.create(emailVerificationData);
    }
    
    public void sendVerification(String token, String type, CustomerModel customerModel) {
        getEventService().publishEvent(initializeEvent(new EmailVerificationEvent(token, type), customerModel));
    }
    
    public void sendTwoFactorAuthenticationMail(String token, String type, CustomerModel customerModel) {
        getEventService().publishEvent(initializeEvent(new TwoFactorAuthenticationEmailEvent(token, type), customerModel));
    }
    
    public EmailVerificationSetService getEmailVerificationSetService() {
		return emailVerificationSetService;
	}

	public void setEmailVerificationSetService(EmailVerificationSetService emailVerificationSetService) {
		this.emailVerificationSetService = emailVerificationSetService;
	}
	
	protected AbstractCommerceUserEvent initializeEvent(final AbstractCommerceUserEvent event, final CustomerModel customerModel)
	{
		event.setBaseStore(baseStoreService.getCurrentBaseStore());
		event.setSite(baseSiteService.getCurrentBaseSite());
		event.setCustomer(customerModel);
		event.setLanguage(commonI18NService.getCurrentLanguage());
		event.setCurrency(commonI18NService.getCurrentCurrency());
		return event;
	}
	
	public String getMaskedEmail(CustomerModel customerModel) {
		String email = customerModel.getUserNameEmail();
		String userName   = email.substring(0, email.lastIndexOf("@"));
		String domain = email.substring(email.lastIndexOf("@") +1);
		if(StringUtil.isEmpty(email))
		{
			return StringUtils.EMPTY;
		}
		else
		{
			return userName.substring(0, 1)+"******"+"@"+domain;
		}
	}
	
	public String getMaskedPhoneNumber(CustomerModel customerModel) {
		String contactNumber=customerModel.getMobileNumber();
		if(StringUtil.isEmpty(contactNumber))
		{
			return StringUtils.EMPTY;
		}
		else
		{
			return "*****"+contactNumber.substring(contactNumber.length() - 4);
		}
	}
	
	public void setCustomerLastLoginTime(CustomerModel customerModel) {
		customerModel.setLastSuccessLogin(new Date());
		modelService.save(customerModel);
	}
	
    /**
     * @return
     */
    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    /**
     * @param baseStoreService baseStoreService
     */
    public void setBaseStoreService(BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    /**
     * @return
     */
    public BaseSiteService getBaseSiteService() {
        return baseSiteService;
    }

    /**
     * @param baseSiteService
     */
    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    /**
     * @return
     */
    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    /**
     * sets CommonI18NService
     *
     * @param commonI18NService commonI18NService
     */
    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

	/**
     * gets EventService
     *
     * @return EventService
     */
    public EventService getEventService() {
        return eventService;
    }

    /**
     * sets EventService
     *
     * @param eventService eventService
     */
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
    
    /**
	 * @return the emailSubscriptionFeeSetService
	 */
	public EmailSubscriptionFeeSetService getEmailSubscriptionFeeSetService() {
		return emailSubscriptionFeeSetService;
	}

	/**
	 * @param emailSubscriptionFeeSetService the emailSubscriptionFeeSetService to set
	 */
	public void setEmailSubscriptionFeeSetService(EmailSubscriptionFeeSetService emailSubscriptionFeeSetService) {
		this.emailSubscriptionFeeSetService = emailSubscriptionFeeSetService;
	}
}
