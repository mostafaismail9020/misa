package com.sap.ibso.eservices.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.model.process.SupportVisitEmailGenerationProcessModel;
import de.hybris.platform.processengine.impl.DefaultBusinessProcessService;
import de.hybris.platform.servicelayer.internal.model.impl.DefaultModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

public class SupportVisitEventListener extends AbstractAcceleratorSiteEventListener<SupportVisitEmailGenerationEvent> {
    private DefaultBusinessProcessService businessProcessService;
    private DefaultModelService modelService;

    @Override
    protected SiteChannel getSiteChannelForEvent(SupportVisitEmailGenerationEvent event) {
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
        return site.getChannel();
    }

    @Override
    protected void onSiteEvent(SupportVisitEmailGenerationEvent event) {
        final SupportVisitEmailGenerationProcessModel supportVisitEmailGenerationProcessModel =
                getBusinessProcessService().createProcess("supportVisitEmailGenerationProcess-"
                + event.getCustomer().getUid()+"-"+System.currentTimeMillis(),"supportVisitEmailGenerationProcess");
        supportVisitEmailGenerationProcessModel.setSite(event.getSite());
        supportVisitEmailGenerationProcessModel.setCustomer(event.getCustomer());
        supportVisitEmailGenerationProcessModel.setLanguage(event.getLanguage());
        supportVisitEmailGenerationProcessModel.setCurrency(event.getCurrency());
        supportVisitEmailGenerationProcessModel.setStore(event.getBaseStore());
        supportVisitEmailGenerationProcessModel.setSupportVisitNumber(event.getSupportVisitData().getSrId());
        getModelService().save(supportVisitEmailGenerationProcessModel);
        getBusinessProcessService().startProcess(supportVisitEmailGenerationProcessModel);
    }

    public void setBusinessProcessService(DefaultBusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public DefaultBusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setModelService(DefaultModelService modelService) {
        this.modelService = modelService;
    }

    public DefaultModelService getModelService() {
        return modelService;
    }
}
