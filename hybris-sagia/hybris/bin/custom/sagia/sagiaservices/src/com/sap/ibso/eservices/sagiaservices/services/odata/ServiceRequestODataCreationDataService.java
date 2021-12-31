package com.sap.ibso.eservices.sagiaservices.services.odata;

import com.sap.ibso.eservices.sagiaservices.data.odata.ODataServiceRequestCreationData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.OppServiceCreationData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ServiceRequestCreationData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import de.hybris.platform.core.model.user.CustomerModel;

import org.springframework.beans.factory.annotation.Required;

/**
 * OppServiceCreationService
 */
public class ServiceRequestODataCreationDataService extends AbstractSagiaService<ODataServiceRequestCreationData> {

    private InvestorMappingService investorMappingService;

    /**
     * saves OppServiceCreation
     * @param oppServiceCreationData oppServiceCreationData
     * @return String
     */
    public ODataServiceRequestCreationData saveServiceRequestCreation(ODataServiceRequestCreationData oppServiceCreationData,CustomerModel customer){
        oppServiceCreationData.setRefid(investorMappingService.getApplicantReferenceId(customer));
        return super.saveAndParseResponse(oppServiceCreationData, ODataServiceRequestCreationData.class);
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
