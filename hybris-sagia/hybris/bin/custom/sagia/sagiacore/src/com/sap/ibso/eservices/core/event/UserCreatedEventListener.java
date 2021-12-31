package com.sap.ibso.eservices.core.event;

import com.investsaudi.model.UserCreatedEmailProcessModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import org.springframework.beans.factory.annotation.Required;

public class UserCreatedEventListener extends AbstractAcceleratorSiteEventListener<UserCreatedEvent> {

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
    protected void onSiteEvent(final UserCreatedEvent event)
    {
        final UserCreatedEmailProcessModel userCreatedEmailProcessModel = (UserCreatedEmailProcessModel) getBusinessProcessService()
                .createProcess(
                        "userCreatedEmailProcess-" + event.getCustomer().getUid() + "-" + System.currentTimeMillis(),
                        "userCreatedEmailProcess");

        userCreatedEmailProcessModel.setSite(event.getSite() );
        userCreatedEmailProcessModel.setCustomer(event.getCustomer());
        userCreatedEmailProcessModel.setLanguage(event.getLanguage());
        userCreatedEmailProcessModel.setCurrency(event.getCurrency());
        userCreatedEmailProcessModel.setStore(event.getBaseStore());
        userCreatedEmailProcessModel.setRole(event.getRole());
        userCreatedEmailProcessModel.setInitialPassword(event.getInitialPassword());
        getModelService().save(userCreatedEmailProcessModel);
        getBusinessProcessService().startProcess(userCreatedEmailProcessModel);
    }
    @Override
    protected SiteChannel getSiteChannelForEvent(UserCreatedEvent event) {
        return SiteChannel.B2B;
    }

    protected BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

}



