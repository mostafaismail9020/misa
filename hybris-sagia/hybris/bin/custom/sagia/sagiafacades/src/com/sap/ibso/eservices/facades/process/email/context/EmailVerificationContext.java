package com.sap.ibso.eservices.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.EmailVerificationProcessModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import org.apache.commons.lang.StringUtils;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import org.springframework.beans.factory.annotation.Required;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import de.hybris.platform.core.model.user.CustomerModel;
public class EmailVerificationContext extends ForgottenPasswordEmailContext {
	
	private static final String EMAIL_VERIFY = "/verification/emailverify";
	private static final String EMAIL_TYPE_REG = "reg";
    private static final String EMAIL_TYPE_QEEMAH = "qeemah";
	private String type;
	private SagiaConfigurationFacade sagiaConfigurationFacade;
//	private long emailExpiresInMinutes = getSagiaConfigurationFacade().getEmailValidityInSeconds();
//	
//	public long getEmailExpiresInMinutes() {
//		return emailExpiresInMinutes;
//	}
//
//	public void setEmailExpiresInMinutes(long emailExpiresInMinutes) {
//		this.emailExpiresInMinutes = emailExpiresInMinutes;
//	}
	
	
//	private int emailExpiresInMinutes = getConfigurationService().getConfiguration().getInt("token.expires.in.minutes");
//
//
//	public int getEmailExpiresInMinutes() {
//		return emailExpiresInMinutes;
//	}
//
//	public void setEmailExpiresInMinutes(int emailExpiresInMinutes) {
//		this.emailExpiresInMinutes = emailExpiresInMinutes;
//	}

	

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

	/**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getEmailVerifyUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), false, EMAIL_VERIFY,
                "token=" + getURLEncodedToken());
    }

  	/**
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getSecureEmailVerifyUrl() throws UnsupportedEncodingException {
        return getSiteBaseUrlResolutionService().getWebsiteUrlForSite(getBaseSite(), getUrlEncodingAttributes(), true, EMAIL_VERIFY,
                "token=" + getURLEncodedToken()  + (getBaseSite() != null && StringUtils.isNotEmpty(getBaseSite().getUid()) ? "&site=" + getBaseSite().getUid(): "") + "&type="+getType());
    }
	
	@Override
    public void init(final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, final EmailPageModel emailPageModel) {
        super.init(storeFrontCustomerProcessModel, emailPageModel);
        CustomerModel customerModel = getCustomer(storeFrontCustomerProcessModel);
        
        put("textAlign", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "left" : "right");
        put("textDirection", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "ltr" : "rtl");
        if(getSagiaConfigurationFacade().getEmailValidityInSeconds() > 0L) {
        	put("emailExpiresInMinutes", getSagiaConfigurationFacade().getEmailValidityInSeconds()/(60*60));
        }else {
        	put("emailExpiresInMinutes", "30");
        }
        //put("emailExpiresInSeconds", getConfigurationService().getConfiguration().getInt("token.expires.in.minutes"));
        
        if (storeFrontCustomerProcessModel instanceof EmailVerificationProcessModel) {
            setToken(((EmailVerificationProcessModel) storeFrontCustomerProcessModel).getToken());
            setType(((EmailVerificationProcessModel) storeFrontCustomerProcessModel).getType());
        }
        if(EMAIL_TYPE_QEEMAH.equalsIgnoreCase(getType())) {
        	 put("email", customerModel.getQeemahEmail());
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
