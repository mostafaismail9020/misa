package com.sap.ibso.eservices.core.event;

import com.investsaudi.model.OpportunityUpdatedEmailProcessModel;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;


public class OpportunityUpdatedEventListener extends AbstractAcceleratorSiteEventListener<OpportunityUpdatedEvent>{




    private ModelService modelService;
    private BusinessProcessService businessProcessService;

    @Required
    public void setBusinessProcessService(final BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    /**
     * @return the modelService
     */
    protected ModelService getModelService() {
        return modelService;
    }

    /**
     * @param modelService the modelService to set
     */
    @Required
    public void setModelService(final ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    protected void onSiteEvent(final OpportunityUpdatedEvent event) {
        final OpportunityUpdatedEmailProcessModel opportunityUpdatedEmailProcessModel = (OpportunityUpdatedEmailProcessModel) getBusinessProcessService()
                .createProcess(
                        "opportunityUpdatedEmailProcess-" + event.getOpportunityId() + "-" + System.currentTimeMillis(),
                        "opportunityUpdatedEmailProcess");

        opportunityUpdatedEmailProcessModel.setSite(event.getSite());
        opportunityUpdatedEmailProcessModel.setCustomer(event.getCustomer());
        opportunityUpdatedEmailProcessModel.setLanguage(event.getLanguage());
        opportunityUpdatedEmailProcessModel.setCurrency(event.getCurrency());
        opportunityUpdatedEmailProcessModel.setStore(event.getBaseStore());
        opportunityUpdatedEmailProcessModel.setOpportunityId(event.getOpportunityId());
        opportunityUpdatedEmailProcessModel.setCurrentState(event.getCurrentState());
        getModelService().save(opportunityUpdatedEmailProcessModel);
        getBusinessProcessService().startProcess(opportunityUpdatedEmailProcessModel);
    }

    @Override
    protected SiteChannel getSiteChannelForEvent(OpportunityUpdatedEvent event) {
        return SiteChannel.B2B;
    }

    protected BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

}
