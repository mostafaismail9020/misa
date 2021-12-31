package com.investsaudi.email.context;

import com.investsaudi.model.OpportunityCreatedEmailProcessModel;
import com.investsaudi.model.OpportunityUserEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

public class OpportunityUserEmailContext extends AbstractEmailContext<OpportunityUserEmailProcessModel> {
    private String opportunityId;
    private String userName;
    private String password;
    private boolean isNewCustomer;

    @Override
    public void init(final OpportunityUserEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);
        CustomerModel customerModel = getCustomer(businessProcessModel);

        put("email", customerModel.getUserNameEmail());
        if (businessProcessModel instanceof OpportunityUserEmailProcessModel) {

            final OpportunityUserEmailProcessModel processModel = (OpportunityUserEmailProcessModel) businessProcessModel;
            this.opportunityId = ((OpportunityUserEmailProcessModel) processModel).getOpportunityId();
            this.userName=customerModel.getUserNameEmail();
            this.password=((OpportunityUserEmailProcessModel) processModel).getInitialPassword();
            this.isNewCustomer=((OpportunityUserEmailProcessModel) processModel).isIsNewCustomer();
        }
    }

    @Override
    protected BaseSiteModel getSite(OpportunityUserEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(OpportunityUserEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(OpportunityUserEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNewCustomer() {
        return isNewCustomer;
    }

    public void setNewCustomer(boolean newCustomer) {
        isNewCustomer = newCustomer;
    }
}
