package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.OppServiceCreationData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.springframework.beans.factory.annotation.Required;

/**
 * OppServiceCreationService
 */
public class OppServiceCreationService extends AbstractSagiaService<OppServiceCreationData> {

    private InvestorMappingService investorMappingService;

    /**
     * saves OppServiceCreation
     * @param oppServiceCreationData oppServiceCreationData
     * @return String
     */
    public OppServiceCreationData saveOppServiceCreation(OppServiceCreationData oppServiceCreationData){
        oppServiceCreationData.setRefid(investorMappingService.getApplicantReferenceId(null));
        return super.saveAndParseResponse(oppServiceCreationData, OppServiceCreationData.class);
    }

    /**
     * saves OppServiceCreation
     * @return OppServiceCreationData
     */
    public OppServiceCreationData saveOppServiceCreation(){
        OppServiceCreationData oppServiceCreationData = new OppServiceCreationData();
        oppServiceCreationData.setRefid(investorMappingService.getApplicantReferenceId(null));
        return super.saveAndParseResponse(oppServiceCreationData, OppServiceCreationData.class);
    }

    /**
     * Sets the investor mapping service.
     *
     * @param investorMappingService the investor mapping service
     */
    @Required
    public void setInvestorMappingService(InvestorMappingService investorMappingService)
    {
        this.investorMappingService = investorMappingService;
    }
}
