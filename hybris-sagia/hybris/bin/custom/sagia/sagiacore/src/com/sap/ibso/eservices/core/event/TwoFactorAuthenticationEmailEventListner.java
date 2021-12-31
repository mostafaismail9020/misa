package com.sap.ibso.eservices.core.event;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.commerceservices.model.process.TwoFactorAuthenticationProcessModel;

public class TwoFactorAuthenticationEmailEventListner extends AbstractAcceleratorSiteEventListener<TwoFactorAuthenticationEmailEvent> {
	
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
	protected void onSiteEvent(final TwoFactorAuthenticationEmailEvent event)
	{
		final TwoFactorAuthenticationProcessModel twoFactorAuthenticationProcessModel = getBusinessProcessService()
				.createProcess("emailTwoFactorAuthenticationProcess-" + event.getCustomer().getUid() + "-" + System.currentTimeMillis(),
						"emailTwoFactorAuthenticationProcess");
		twoFactorAuthenticationProcessModel.setSite(event.getSite());
		twoFactorAuthenticationProcessModel.setCustomer(event.getCustomer());
		twoFactorAuthenticationProcessModel.setToken(event.getToken());
		twoFactorAuthenticationProcessModel.setLanguage(event.getLanguage());
		twoFactorAuthenticationProcessModel.setCurrency(event.getCurrency());
		twoFactorAuthenticationProcessModel.setStore(event.getBaseStore());
		twoFactorAuthenticationProcessModel.setType(event.getType());
		getModelService().save(twoFactorAuthenticationProcessModel);
		getBusinessProcessService().startProcess(twoFactorAuthenticationProcessModel);
	}

	@Override
	protected SiteChannel getSiteChannelForEvent(final TwoFactorAuthenticationEmailEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
		return site.getChannel();
	}
}
