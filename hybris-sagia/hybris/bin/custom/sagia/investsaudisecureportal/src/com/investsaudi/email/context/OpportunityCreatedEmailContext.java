package com.investsaudi.email.context;

import com.investsaudi.model.OpportunityCreatedEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

public class OpportunityCreatedEmailContext  extends AbstractEmailContext<OpportunityCreatedEmailProcessModel> {
    private String opportunityId;

    @Override
    public void init(final OpportunityCreatedEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);
        CustomerModel customerModel = getCustomer(businessProcessModel);

        put("email", customerModel.getUserNameEmail());
        if (businessProcessModel instanceof OpportunityCreatedEmailProcessModel) {

            final OpportunityCreatedEmailProcessModel processModel = (OpportunityCreatedEmailProcessModel) businessProcessModel;
            this.opportunityId = ((OpportunityCreatedEmailProcessModel) processModel).getOpportunityId();
        }
    }

    @Override
    protected BaseSiteModel getSite(OpportunityCreatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(OpportunityCreatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(OpportunityCreatedEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }


}
