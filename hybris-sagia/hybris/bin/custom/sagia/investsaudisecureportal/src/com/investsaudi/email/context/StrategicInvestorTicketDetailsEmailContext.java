package com.investsaudi.email.context;

import com.investsaudi.model.MizaTicketDetailsEmailProcessModel;
import com.investsaudi.model.StrategicInvestorTicketDetailsEmailProcessModel;
import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;

public class StrategicInvestorTicketDetailsEmailContext extends AbstractEmailContext<StrategicInvestorTicketDetailsEmailProcessModel> {
    private String opportunityId;
    private String strategicInvestorTicketUserCompanyName;
    private String strategicInvestorTicketUserName;
    private String strategicInvestorTicketUserPhoneNumber;
    private String strategicInvestorTicketUserPosition;
    private String strategicInvestorTicketUserEmail;
    private String strategicInvestorTicketUserService;
    private String strategicInvestorTicketUserEnquiry;
    @Autowired
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Override
    public void init(final StrategicInvestorTicketDetailsEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);

        put(EMAIL, sagiaConfigurationFacade.getStrategicInvestorEmailId());
        put(DISPLAY_NAME, sagiaConfigurationFacade.getStrategicInvestorEmailUserName());
        if (businessProcessModel instanceof StrategicInvestorTicketDetailsEmailProcessModel) {

            final StrategicInvestorTicketDetailsEmailProcessModel processModel = (StrategicInvestorTicketDetailsEmailProcessModel) businessProcessModel;
            this.opportunityId = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getOpportunityId();
            this.strategicInvestorTicketUserName = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getStrategicInvestorTicketUserName();
            this.strategicInvestorTicketUserCompanyName = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getStrategicInvestorTicketUserCompanyName();
            this.strategicInvestorTicketUserPhoneNumber = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getStrategicInvestorTicketUserPhoneNumber();
            this.strategicInvestorTicketUserPosition = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getStrategicInvestorTicketUserPosition();
            this.strategicInvestorTicketUserEmail = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getStrategicInvestorTicketUserEmail();
            this.strategicInvestorTicketUserService = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getStrategicInvestorTicketUserService();
            this.strategicInvestorTicketUserEnquiry = ((StrategicInvestorTicketDetailsEmailProcessModel) processModel).getStrategicInvestorTicketUserEnquiry();
        }
    }


    @Override
    protected BaseSiteModel getSite(StrategicInvestorTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(StrategicInvestorTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(StrategicInvestorTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getStrategicInvestorTicketUserCompanyName() {
        return strategicInvestorTicketUserCompanyName;
    }

    public void setStrategicInvestorTicketUserCompanyName(String strategicInvestorTicketUserCompanyName) {
        this.strategicInvestorTicketUserCompanyName = strategicInvestorTicketUserCompanyName;
    }

    public String getStrategicInvestorTicketUserName() {
        return strategicInvestorTicketUserName;
    }

    public void setStrategicInvestorTicketUserName(String strategicInvestorTicketUserName) {
        this.strategicInvestorTicketUserName = strategicInvestorTicketUserName;
    }

    public String getStrategicInvestorTicketUserPhoneNumber() {
        return strategicInvestorTicketUserPhoneNumber;
    }

    public void setStrategicInvestorTicketUserPhoneNumber(String strategicInvestorTicketUserPhoneNumber) {
        this.strategicInvestorTicketUserPhoneNumber = strategicInvestorTicketUserPhoneNumber;
    }

    public String getStrategicInvestorTicketUserPosition() {
        return strategicInvestorTicketUserPosition;
    }

    public void setStrategicInvestorTicketUserPosition(String strategicInvestorTicketUserPosition) {
        this.strategicInvestorTicketUserPosition = strategicInvestorTicketUserPosition;
    }

    public String getStrategicInvestorTicketUserEmail() {
        return strategicInvestorTicketUserEmail;
    }

    public void setStrategicInvestorTicketUserEmail(String strategicInvestorTicketUserEmail) {
        this.strategicInvestorTicketUserEmail = strategicInvestorTicketUserEmail;
    }

    public String getStrategicInvestorTicketUserService() {
        return strategicInvestorTicketUserService;
    }

    public void setStrategicInvestorTicketUserService(String strategicInvestorTicketUserService) {
        this.strategicInvestorTicketUserService = strategicInvestorTicketUserService;
    }

    public String getStrategicInvestorTicketUserEnquiry() {
        return strategicInvestorTicketUserEnquiry;
    }

    public void setStrategicInvestorTicketUserEnquiry(String strategicInvestorTicketUserEnquiry) {
        this.strategicInvestorTicketUserEnquiry = strategicInvestorTicketUserEnquiry;
    }
}
