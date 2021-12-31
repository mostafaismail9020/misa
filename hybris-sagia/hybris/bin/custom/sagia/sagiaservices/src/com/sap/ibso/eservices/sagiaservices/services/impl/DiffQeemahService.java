package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.DiffQeemahData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;

/**
 * DiffQeemahService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DiffQeemahService extends AbstractSagiaService<DiffQeemahData> {
    private InvestorMappingService investorMappingService;

    /**
     * saves DiffQeemahData
     * @param diffQeemahData diffQeemahData
     */
    public void saveDiff(DiffQeemahData diffQeemahData){
        diffQeemahData.setRefid(investorMappingService.getApplicantReferenceId(null));
        super.save(diffQeemahData);
    }

    /**
     * retrieves DiffQeemahData
     * @return DiffQeemahData
     * @throws SagiaODataException exception
     */
    public DiffQeemahData get() throws SagiaODataException {
        return super.get(DiffQeemahData.class);
    }

    public void setInvestorMappingService(InvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }
}
