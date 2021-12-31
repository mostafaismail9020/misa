package com.sap.ibso.eservices.core.event;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.model.process.EmailVerificationProcessModel;
import de.hybris.platform.commerceservices.model.process.ForgottenPasswordProcessModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;

public class EmailVerificationEventListener extends AbstractAcceleratorSiteEventListener<EmailVerificationEvent> {
	
	private ModelService modelService;
	private BusinessProcessService businessProcessService;


	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	protected void onSiteEvent(final EmailVerificationEvent event)
	{
		final EmailVerificationProcessModel emailVerificationProcessModel = getBusinessProcessService()
				.createProcess("emailVerificationProcess-" + event.getCustomer().getUid() + "-" + System.currentTimeMillis(),
						"emailVerificationProcess");
		emailVerificationProcessModel.setSite(event.getSite());
		emailVerificationProcessModel.setCustomer(event.getCustomer());
		emailVerificationProcessModel.setToken(event.getToken());
		emailVerificationProcessModel.setLanguage(event.getLanguage());
		emailVerificationProcessModel.setCurrency(event.getCurrency());
		emailVerificationProcessModel.setStore(event.getBaseStore());
		emailVerificationProcessModel.setType(event.getType());
		getModelService().save(emailVerificationProcessModel);
		getBusinessProcessService().startProcess(emailVerificationProcessModel);
	}

	@Override
	protected SiteChannel getSiteChannelForEvent(final EmailVerificationEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
		return site.getChannel();
	}
}
