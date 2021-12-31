package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.OppService2Data;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.springframework.beans.factory.annotation.Required;

import java.util.UUID;

/**
 * OppService2Service
 */
public class OppService2Service extends AbstractSagiaService<OppService2Data> {

    private InvestorMappingService investorMappingService;

    /**
     * saves OppService2
     * @param oppService2Data oppService2Data
     * @return String
     */
    public String saveOppService2(OppService2Data oppService2Data){
        oppService2Data.setRefid(investorMappingService.getApplicantReferenceId(null));
        oppService2Data.setGuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        return super.save(oppService2Data);
    }

    /**
     * saves OppService2
     * @param guid guid
     * @return String
     */
    public OppService2Data saveOppService2(String guid){
        OppService2Data oppService2Data = new OppService2Data();
        oppService2Data.setRefid(investorMappingService.getApplicantReferenceId(null));
        oppService2Data.setGuid(guid);
        return super.saveAndParseResponse(oppService2Data, OppService2Data.class);
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
