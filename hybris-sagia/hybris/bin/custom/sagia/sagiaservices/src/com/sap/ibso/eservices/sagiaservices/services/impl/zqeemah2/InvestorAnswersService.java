package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.InvsIdPostData;
import com.sap.ibso.eservices.sagiaservices.investor.impl.DefaultInvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * InvestorAnswersService
 */
public class InvestorAnswersService extends AbstractSagiaService<InvsIdPostData> {
    DefaultInvestorMappingService investorMappingService;

    /**
     * method for saving FinancialAnswers
     * @param fininvisidPostData fininvisidPostData
     * @return InvsIdPostData
     */
    public InvsIdPostData saveFinancialAnswers(InvsIdPostData fininvisidPostData) {
        fininvisidPostData.setInvestorid(investorMappingService.getApplicantReferenceId(null));
        return super.saveAndParseResponse(fininvisidPostData, InvsIdPostData.class);
    }

    public void setInvestorMappingService(DefaultInvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }
}
