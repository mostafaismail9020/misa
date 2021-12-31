package com.sap.ibso.eservices.core.event;
import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import com.sap.ibso.eservices.core.event.OpportunityCreatedEvent;
import com.investsaudi.model.OpportunityCreatedEmailProcessModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

public class OpportunityCreatedEventListener  extends AbstractAcceleratorSiteEventListener<OpportunityCreatedEvent> {
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
    protected void onSiteEvent(final OpportunityCreatedEvent event) {
        final OpportunityCreatedEmailProcessModel opportunityCreatedEmailProcessModel = (OpportunityCreatedEmailProcessModel) getBusinessProcessService()
                .createProcess(
                        "opportunityCreatedEmailProcess-" + event.getOpportunityId() + "-" + System.currentTimeMillis(),
                        "opportunityCreatedEmailProcess");

        opportunityCreatedEmailProcessModel.setSite(event.getSite());
        opportunityCreatedEmailProcessModel.setCustomer(event.getCustomer());
        opportunityCreatedEmailProcessModel.setLanguage(event.getLanguage());
        opportunityCreatedEmailProcessModel.setCurrency(event.getCurrency());
        opportunityCreatedEmailProcessModel.setStore(event.getBaseStore());
        opportunityCreatedEmailProcessModel.setOpportunityId(event.getOpportunityId());
        getModelService().save(opportunityCreatedEmailProcessModel);
        getBusinessProcessService().startProcess(opportunityCreatedEmailProcessModel);
    }

    @Override
    protected SiteChannel getSiteChannelForEvent(OpportunityCreatedEvent event) {
        return SiteChannel.B2B;
    }

    protected BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

}