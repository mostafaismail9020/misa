package com.sap.ibso.eservices.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.TwoFactorAuthenticationProcessModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import org.apache.commons.lang.StringUtils;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import org.springframework.beans.factory.annotation.Required;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import de.hybris.platform.core.model.user.CustomerModel;
public class TwoFactorAuthenticationEmailContext extends ForgottenPasswordEmailContext {
	
	private static final String EMAIL_TYPE_REG = "reg";
	private SagiaConfigurationFacade sagiaConfigurationFacade;
	private String type;

	@Required
	public void setSagiaConfigurationFacade(SagiaConfigurationFacade sagiaConfigurationFacade) {
		this.sagiaConfigurationFacade = sagiaConfigurationFacade;
	}
	
	public SagiaConfigurationFacade getSagiaConfigurationFacade() {
		return sagiaConfigurationFacade;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
    public void init(final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, final EmailPageModel emailPageModel) {
        super.init(storeFrontCustomerProcessModel, emailPageModel);
        CustomerModel customerModel = getCustomer(storeFrontCustomerProcessModel);
        
        put("textAlign", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "left" : "right");
        put("textDirection", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "ltr" : "rtl");

        if (storeFrontCustomerProcessModel instanceof TwoFactorAuthenticationProcessModel) {
            setToken(((TwoFactorAuthenticationProcessModel) storeFrontCustomerProcessModel).getToken());
        }
        if(EMAIL_TYPE_REG.equalsIgnoreCase(getType())) {
        	 put("email", customerModel.getUserNameEmail());
        }
       
    }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext#getCustomer(de.hybris.platform
	 * .processengine.model.BusinessProcessModel)
	 */
	@Override
	protected CustomerModel getCustomer(final StoreFrontCustomerProcessModel businessProcessModel)
	{
		return businessProcessModel.getCustomer();
	}

}
