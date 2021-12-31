package com.sap.ibso.eservices.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.model.process.UpdateContactEmailProcessModel;
import de.hybris.platform.processengine.impl.DefaultBusinessProcessService;
import de.hybris.platform.servicelayer.internal.model.impl.DefaultModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

public class ContactUpdateEmailEventListener extends AbstractAcceleratorSiteEventListener<ContactUpdateEmailEvent> {
    private DefaultBusinessProcessService businessProcessService;
    private DefaultModelService modelService;

    @Override
    protected SiteChannel getSiteChannelForEvent(ContactUpdateEmailEvent event) {
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
        return site.getChannel();
    }

    @Override
    protected void onSiteEvent(ContactUpdateEmailEvent event) {
        final UpdateContactEmailProcessModel updateContactEmailProcessModel =
                getBusinessProcessService().createProcess("updateContactEmailProcess-"
                + event.getCustomer().getUid()+"-"+System.currentTimeMillis(),"updateContactEmailProcess");
        updateContactEmailProcessModel.setSite(event.getSite());
        updateContactEmailProcessModel.setCustomer(event.getCustomer());
        updateContactEmailProcessModel.setLanguage(event.getLanguage());
        updateContactEmailProcessModel.setCurrency(event.getCurrency());
        updateContactEmailProcessModel.setStore(event.getBaseStore());
        updateContactEmailProcessModel.setOldEmail(event.getOldEmail());

        getModelService().save(updateContactEmailProcessModel);
        getBusinessProcessService().startProcess(updateContactEmailProcessModel);
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
