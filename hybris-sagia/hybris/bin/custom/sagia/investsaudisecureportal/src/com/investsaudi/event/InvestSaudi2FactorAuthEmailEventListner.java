package com.investsaudi.event;

import org.springframework.beans.factory.annotation.Required;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;
import de.hybris.platform.commerceservices.model.process.InvestSaudi2FactorAuthProcessModel;

public class InvestSaudi2FactorAuthEmailEventListner  extends AbstractAcceleratorSiteEventListener<InvestSaudi2FactorAuthEmailEvent> {

	private ModelService modelService;
	private BusinessProcessService businessProcessService;
	
	public ModelService getModelService() {
		return modelService;
	}
	
	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
	
	public BusinessProcessService getBusinessProcessService() {
		return businessProcessService;
	}
	
	@Required
	public void setBusinessProcessService(BusinessProcessService businessProcessService) {
		this.businessProcessService = businessProcessService;
	}
	
		@Override
		protected void onSiteEvent(final InvestSaudi2FactorAuthEmailEvent event)
		{
			//System.out.println("^^^^^ start business process code : "+event.getOtp());
			final InvestSaudi2FactorAuthProcessModel investSaudi2FactorAuthProcessModel = getBusinessProcessService()
					.createProcess("emailInvestSaudi2FactorAuthProcess-" + event.getCustomer().getUid() + "-" + System.currentTimeMillis(),
							"emailInvestSaudi2FactorAuthProcess");
			investSaudi2FactorAuthProcessModel.setSite(event.getSite());
			investSaudi2FactorAuthProcessModel.setCustomer(event.getCustomer());
			investSaudi2FactorAuthProcessModel.setOtp(event.getOtp());
			investSaudi2FactorAuthProcessModel.setLanguage(event.getLanguage());
			investSaudi2FactorAuthProcessModel.setCurrency(event.getCurrency());
			investSaudi2FactorAuthProcessModel.setStore(event.getBaseStore());
			//investSaudi2FactorAuthProcessModel.setType(event.getType());
			getModelService().save(investSaudi2FactorAuthProcessModel);
			getBusinessProcessService().startProcess(investSaudi2FactorAuthProcessModel);
		}
	
		@Override
		protected SiteChannel getSiteChannelForEvent(final InvestSaudi2FactorAuthEmailEvent event)
		{
			final BaseSiteModel site = event.getSite();
			ServicesUtil.validateParameterNotNullStandardMessage("event.site", site);
			return site.getChannel();
		}
	
}
