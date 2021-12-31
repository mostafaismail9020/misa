package com.sap.ibso.eservices.core.event;

import com.investsaudi.model.CustomerUserGroupUpdateEmailProcessModel;
import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;


/**
 * Listener for customer usergroup update.
 */
public class CustomerUserGroupUpdateEventListener extends AbstractAcceleratorSiteEventListener<CustomerUserGroupUpdateEvent>
{
    private ModelService modelService;
    private BusinessProcessService businessProcessService;

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
    protected void onSiteEvent(final CustomerUserGroupUpdateEvent event)
    {
        final CustomerUserGroupUpdateEmailProcessModel customerUserGroupUpdateEmailProcessModel = (CustomerUserGroupUpdateEmailProcessModel) getBusinessProcessService()
                .createProcess(
                        "customerUserGroupUpdateEmailProcess-" + event.getCustomer().getUid() + "-" + System.currentTimeMillis(),
                        "customerUserGroupUpdateEmailProcess");

        customerUserGroupUpdateEmailProcessModel.setSite(event.getSite() );
        customerUserGroupUpdateEmailProcessModel.setCustomer(event.getCustomer());
        customerUserGroupUpdateEmailProcessModel.setLanguage(event.getLanguage());
        customerUserGroupUpdateEmailProcessModel.setCurrency(event.getCurrency());
        customerUserGroupUpdateEmailProcessModel.setStore(event.getBaseStore());
        customerUserGroupUpdateEmailProcessModel.setUserGroup(event.getUserGroup());
        getModelService().save(customerUserGroupUpdateEmailProcessModel);
        getBusinessProcessService().startProcess(customerUserGroupUpdateEmailProcessModel);
    }

    @Override
    protected SiteChannel getSiteChannelForEvent(CustomerUserGroupUpdateEvent event) {
        return SiteChannel.B2B;
    }

    protected BusinessProcessService getBusinessProcessService()
    {
        return businessProcessService;
    }
}