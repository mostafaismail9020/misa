package com.investsaudi.email.context;

import com.investsaudi.model.MizaTicketDetailsEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;

public class MizaTicketDetailsEmailContext extends AbstractEmailContext<MizaTicketDetailsEmailProcessModel> {
    private String opportunityId;
    private String mizaTicketUserCompanyName;
    private String mizaTicketUserName;
    private String mizaTicketUserPhoneNumber;
    private String mizaTicketUserPosition;
    private String mizaTicketUserEmail;
    private String mizaTicketUserService;
    private String mizaTicketUserEnquiry;

    @Override
    public void init(final MizaTicketDetailsEmailProcessModel businessProcessModel, final EmailPageModel emailPageModel) {
        super.init(businessProcessModel, emailPageModel);

        put(EMAIL, "miza@misa.gov.sa");
        put(DISPLAY_NAME, "MIZA Default Customer");
        if (businessProcessModel instanceof MizaTicketDetailsEmailProcessModel) {

            final MizaTicketDetailsEmailProcessModel processModel = (MizaTicketDetailsEmailProcessModel) businessProcessModel;
            this.opportunityId = ((MizaTicketDetailsEmailProcessModel) processModel).getOpportunityId();
            this.mizaTicketUserName = ((MizaTicketDetailsEmailProcessModel) processModel).getMizaTicketUserName();
            this.mizaTicketUserCompanyName = ((MizaTicketDetailsEmailProcessModel) processModel).getMizaTicketUserCompanyName();
            this.mizaTicketUserPhoneNumber = ((MizaTicketDetailsEmailProcessModel) processModel).getMizaTicketUserPhoneNumber();
            this.mizaTicketUserPosition = ((MizaTicketDetailsEmailProcessModel) processModel).getMizaTicketUserPosition();
            this.mizaTicketUserEmail = ((MizaTicketDetailsEmailProcessModel) processModel).getMizaTicketUserEmail();
            this.mizaTicketUserService = ((MizaTicketDetailsEmailProcessModel) processModel).getMizaTicketUserService();
            this.mizaTicketUserEnquiry = ((MizaTicketDetailsEmailProcessModel) processModel).getMizaTicketUserEnquiry();
        }
    }

   public String getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(String opportunityId) {
        this.opportunityId = opportunityId;
    }
    public String getMizaTicketUserName() {
        return mizaTicketUserName;
    }

    public void setMizaTicketUserName(String mizaTicketUserName) {
        this.mizaTicketUserName = mizaTicketUserName;
    }

    public String getMizaTicketUserCompanyName() {
        return mizaTicketUserCompanyName;
    }

    public void setMizaTicketUserCompanyName(String mizaTicketUserCompanyName) {
        this.mizaTicketUserCompanyName = mizaTicketUserCompanyName;
    }

    public String getMizaTicketUserPhoneNumber() {
        return mizaTicketUserPhoneNumber;
    }

    public void setMizaTicketUserPhoneNumber(String mizaTicketUserPhoneNumber) {
        this.mizaTicketUserPhoneNumber = mizaTicketUserPhoneNumber;
    }

    public String getMizaTicketUserPosition() {
        return mizaTicketUserPosition;
    }

    public void setMizaTicketUserPosition(String mizaTicketUserPosition) {
        this.mizaTicketUserPosition = mizaTicketUserPosition;
    }

    public String getMizaTicketUserEmail() {
        return mizaTicketUserEmail;
    }

    public void setMizaTicketUserEmail(String mizaTicketUserEmail) {
        this.mizaTicketUserEmail = mizaTicketUserEmail;
    }

    public String getMizaTicketUserService() {
        return mizaTicketUserService;
    }

    public void setMizaTicketUserService(String mizaTicketUserService) {
        this.mizaTicketUserService = mizaTicketUserService;
    }

    public String getMizaTicketUserEnquiry() {
        return mizaTicketUserEnquiry;
    }

    public void setMizaTicketUserEnquiry(String mizaTicketUserEnquiry) {
        this.mizaTicketUserEnquiry = mizaTicketUserEnquiry;
    }

    @Override
    protected BaseSiteModel getSite(MizaTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(MizaTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(MizaTicketDetailsEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }
}
