package com.sap.ibso.eservices.core.event;

import com.investsaudi.model.OpportunityCreatedApproverEmailProcessModel;
import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

public class OpportunityCreatedApproverEventListener extends AbstractAcceleratorSiteEventListener<OpportunityCreatedApproverEvent> {


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
    protected void onSiteEvent(final OpportunityCreatedApproverEvent event) {

        final OpportunityCreatedApproverEmailProcessModel opportunityCreatedApproverEmailProcessModel = (OpportunityCreatedApproverEmailProcessModel) getBusinessProcessService()
                .createProcess(
                        "opportunityCreatedApproverEmailProcess-" + event.getOpportunityId() + "-" + System.currentTimeMillis(),
                        "opportunityCreatedApproverEmailProcess");

        opportunityCreatedApproverEmailProcessModel.setSite(event.getSite());
        opportunityCreatedApproverEmailProcessModel.setCustomer(event.getCustomer());
        opportunityCreatedApproverEmailProcessModel.setLanguage(event.getLanguage());
        opportunityCreatedApproverEmailProcessModel.setCurrency(event.getCurrency());
        opportunityCreatedApproverEmailProcessModel.setStore(event.getBaseStore());
        opportunityCreatedApproverEmailProcessModel.setOpportunityId(event.getOpportunityId());
        opportunityCreatedApproverEmailProcessModel.setApprovers(event.getApproversEmail());
        getModelService().save(opportunityCreatedApproverEmailProcessModel);
        getBusinessProcessService().startProcess(opportunityCreatedApproverEmailProcessModel);
    }

    @Override
    protected SiteChannel getSiteChannelForEvent(OpportunityCreatedApproverEvent event) {
        return SiteChannel.B2B;
    }

    protected BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

}
