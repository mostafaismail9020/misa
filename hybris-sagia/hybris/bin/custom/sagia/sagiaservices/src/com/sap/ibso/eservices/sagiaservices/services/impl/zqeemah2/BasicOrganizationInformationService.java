package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.BasicOrganizationInformationData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.springframework.beans.factory.annotation.Required;

/**
 * BasicOrganizationInformationService
 */
public class BasicOrganizationInformationService extends AbstractSagiaService<BasicOrganizationInformationData> {

    private InvestorMappingService investorMappingService;

    /**
     * saves Organization
     *
     * @param basicOrganizationInformationData basicOrganizationInformationData
     * @return String
     */
    public String saveOrganization(BasicOrganizationInformationData basicOrganizationInformationData) {
        basicOrganizationInformationData.setQ2refid(investorMappingService.getApplicantReferenceId(null));
        return super.save(basicOrganizationInformationData);
    }

    /**
     * Sets the investor mapping service.
     *
     * @param investorMappingService the investor mapping service
     */
    @Required
    public void setInvestorMappingService(InvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }
}
