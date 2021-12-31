package com.investsaudi.email.context;

import com.investsaudi.model.OpportunityCreatedApproverEmailProcessModel;
import com.sap.ibso.eservices.core.constants.SagiaCoreConstants;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

public class OpportunityCreatedApproverEmailContext extends AbstractEmailContext<OpportunityCreatedApproverEmailProcessModel> {
    private String opportunityId;

    @Override
    public void init(final OpportunityCreatedApproverEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);
        CustomerModel customerModel = getCustomer(businessProcessModel);

      //  put("email", customerModel.getUserNameEmail());
        if (businessProcessModel instanceof OpportunityCreatedApproverEmailProcessModel) {

            final OpportunityCreatedApproverEmailProcessModel processModel = (OpportunityCreatedApproverEmailProcessModel) businessProcessModel;
            this.opportunityId = ((OpportunityCreatedApproverEmailProcessModel) processModel).getOpportunityId();
            put(SagiaCoreConstants.TO_EMAIL_LIST ,((OpportunityCreatedApproverEmailProcessModel) processModel).getApprovers()); //Set list of approver emails
        }
    }

    @Override
    protected BaseSiteModel getSite(OpportunityCreatedApproverEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(OpportunityCreatedApproverEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(OpportunityCreatedApproverEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }


}
