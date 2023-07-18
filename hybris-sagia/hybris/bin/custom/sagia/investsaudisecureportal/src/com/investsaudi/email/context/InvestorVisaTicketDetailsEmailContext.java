package com.investsaudi.email.context;

import com.investsaudi.model.InvestorVisaTicketDetailsEmailProcessModel;
import com.investsaudi.model.StrategicInvestorTicketDetailsEmailProcessModel;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;

public class InvestorVisaTicketDetailsEmailContext extends AbstractEmailContext<InvestorVisaTicketDetailsEmailProcessModel> {
    private String opportunityId;
    private String investorVisaTicketUserName;
    private String investorVisaTicketUserPhoneNumber;
    private String investorVisaTicketUserEmail;
    private String investorVisaTicketUserEnquiry;
    @Autowired
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Override
    public void init(final InvestorVisaTicketDetailsEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);

        put(EMAIL, sagiaConfigurationFacade.getStrategicInvestorEmailId());
        put(DISPLAY_NAME, sagiaConfigurationFacade.getStrategicInvestorEmailUserName());
        if (businessProcessModel instanceof InvestorVisaTicketDetailsEmailProcessModel) {

            final InvestorVisaTicketDetailsEmailProcessModel processModel = (InvestorVisaTicketDetailsEmailProcessModel) businessProcessModel;
            this.opportunityId = ((InvestorVisaTicketDetailsEmailProcessModel) processModel).getOpportunityId();
            this.investorVisaTicketUserName = ((InvestorVisaTicketDetailsEmailProcessModel) processModel).getInvestorVisaTicketUserName();
            this.investorVisaTicketUserPhoneNumber = ((InvestorVisaTicketDetailsEmailProcessModel) processModel).getInvestorVisaTicketUserPhoneNumber();
            this.investorVisaTicketUserEmail = ((InvestorVisaTicketDetailsEmailProcessModel) processModel).getInvestorVisaTicketUserEmail();
            this.investorVisaTicketUserEnquiry = ((InvestorVisaTicketDetailsEmailProcessModel) processModel).getInvestorVisaTicketUserEnquiry();
        }
    }


    @Override
    protected BaseSiteModel getSite(InvestorVisaTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(InvestorVisaTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(InvestorVisaTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }
    public String getInvestorVisaTicketUserName() {
        return investorVisaTicketUserName;
    }

    public void setInvestorVisaTicketUserName(String investorVisaTicketUserName) {
        this.investorVisaTicketUserName = investorVisaTicketUserName;
    }
    public String getInvestorVisaTicketUserPhoneNumber() {
        return investorVisaTicketUserPhoneNumber;
    }

    public void setInvestorVisaTicketUserPhoneNumber(String investorVisaTicketUserPhoneNumber) {
        this.investorVisaTicketUserPhoneNumber = investorVisaTicketUserPhoneNumber;
    }

    public String getInvestorVisaTicketUserEmail() {
        return investorVisaTicketUserEmail;
    }

    public void setInvestorVisaTicketUserEmail(String investorVisaTicketUserEmail) {
        this.investorVisaTicketUserEmail = investorVisaTicketUserEmail;
    }

    public String getInvestorVisaTicketUserEnquiry() {
        return investorVisaTicketUserEnquiry;
    }

    public void setInvestorVisaTicketUserEnquiry(String investorVisaTicketUserEnquiry) {
        this.investorVisaTicketUserEnquiry = investorVisaTicketUserEnquiry;
    }
}
