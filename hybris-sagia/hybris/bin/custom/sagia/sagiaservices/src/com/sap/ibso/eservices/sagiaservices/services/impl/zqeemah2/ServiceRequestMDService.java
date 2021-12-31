package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.OppService2Data;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ServiceRequestMDData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import de.hybris.platform.core.model.user.CustomerModel;

import org.springframework.beans.factory.annotation.Required;

import java.util.UUID;

/**
 * OppService2Service
 */
public class ServiceRequestMDService extends AbstractSagiaService<ServiceRequestMDData> {

    private InvestorMappingService investorMappingService;

    /**
     * saves ServiceRequestMD
     * @param serviceRequestMDData serviceRequestMDData
     * @return String
     */
    public String saveServiceRequestMD(ServiceRequestMDData serviceRequestMDData,CustomerModel customer){
        serviceRequestMDData.setRefid(investorMappingService.getApplicantReferenceId(customer));
        serviceRequestMDData.setGuid(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        return super.save(serviceRequestMDData);
    }

    /**
     * saves ServiceRequestMD
     * @param guid guid
     * @return String
     */
    public ServiceRequestMDData saveServiceRequestMD(String guid,CustomerModel customer){
        ServiceRequestMDData serviceRequestMDData = new ServiceRequestMDData();
        serviceRequestMDData.setRefid(investorMappingService.getApplicantReferenceId(customer));
        serviceRequestMDData.setGuid(guid);
        return super.saveAndParseResponse(serviceRequestMDData, ServiceRequestMDData.class);
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
