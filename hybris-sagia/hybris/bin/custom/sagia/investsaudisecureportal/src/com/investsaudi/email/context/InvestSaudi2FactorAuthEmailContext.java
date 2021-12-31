package com.investsaudi.email.context;

import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.InvestSaudi2FactorAuthProcessModel;
import org.apache.commons.lang.StringUtils;

import com.investsaudi.model.B2BRegistrationProcessModel;
import org.springframework.beans.factory.annotation.Required;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

public class InvestSaudi2FactorAuthEmailContext extends AbstractEmailContext<StoreFrontCustomerProcessModel> {

		private String otp;
		
		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}
		
		/*
		 * (non-Javadoc)
		 * 
		 * @see de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext#init(de.hybris.platform.
		 * processengine.model.BusinessProcessModel, de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel)
		 */
		@Override
		public void init(final StoreFrontCustomerProcessModel businessProcessModel, final EmailPageModel emailPageModel)
		{
			super.init(businessProcessModel, emailPageModel);
	        CustomerModel customerModel = getCustomer(businessProcessModel);
	        	        
	        put("textAlign", StringUtils.equalsIgnoreCase(getEmailLanguage(businessProcessModel).getIsocode(), "en") ? "left" : "right");
	        put("textDirection", StringUtils.equalsIgnoreCase(getEmailLanguage(businessProcessModel).getIsocode(), "en") ? "ltr" : "rtl");
	        put("email", customerModel.getUserNameEmail());
	        
	        if (businessProcessModel instanceof InvestSaudi2FactorAuthProcessModel) {
	        	setOtp(((InvestSaudi2FactorAuthProcessModel) businessProcessModel).getOtp());
	        }
	        
	        
		}

		
		/*
		 * (non-Javadoc)
		 * 
		 * @see de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext#getSite(de.hybris.platform.
		 * processengine.model.BusinessProcessModel)
		 */
		@Override
		protected BaseSiteModel getSite(final StoreFrontCustomerProcessModel businessProcessModel)
		{
			return businessProcessModel.getSite();
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext#getEmailLanguage(de.hybris.platform
		 * .processengine.model.BusinessProcessModel)
		 */
		@Override
		protected LanguageModel getEmailLanguage(final StoreFrontCustomerProcessModel businessProcessModel)
		{
			return businessProcessModel.getLanguage();
		}		
}
