package com.investsaudi.email.context;
import com.investsaudi.model.OpportunityUpdatedEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;


public class OpportunityUpdatedEmailContext extends AbstractEmailContext<OpportunityUpdatedEmailProcessModel> {

    private String opportunityId;
    private String currentState;

    @Override
    public void init(final OpportunityUpdatedEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);
        CustomerModel customerModel = getCustomer(businessProcessModel);

        put("email", customerModel.getUserNameEmail());
        if (businessProcessModel instanceof OpportunityUpdatedEmailProcessModel) {

            final OpportunityUpdatedEmailProcessModel processModel = (OpportunityUpdatedEmailProcessModel) businessProcessModel;
            this.opportunityId = ((OpportunityUpdatedEmailProcessModel) processModel).getOpportunityId();
            this.currentState = ((OpportunityUpdatedEmailProcessModel) processModel).getCurrentState();
        }
    }

    @Override
    protected BaseSiteModel getSite(OpportunityUpdatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(OpportunityUpdatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(OpportunityUpdatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
