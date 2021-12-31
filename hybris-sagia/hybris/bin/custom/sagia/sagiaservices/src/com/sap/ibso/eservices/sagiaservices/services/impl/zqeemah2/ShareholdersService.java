package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.ShareholderData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import org.springframework.beans.factory.annotation.Required;

import java.util.Collection;

/**
 * ShareholdersService
 */
public class ShareholdersService extends AbstractSagiaService<ShareholderData> {

    private InvestorMappingService investorMappingService;

    /**
     * saves Shareholders
     * @param shareholdersData shareholdersData
     * @return String
     */
    public String saveShareholders(Collection<ShareholderData> shareholdersData){
        for(ShareholderData shareholderData : shareholdersData){
            shareholderData.setQ2refid(investorMappingService.getApplicantReferenceId(null));
            super.save(shareholderData);
        }
        return null;
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
