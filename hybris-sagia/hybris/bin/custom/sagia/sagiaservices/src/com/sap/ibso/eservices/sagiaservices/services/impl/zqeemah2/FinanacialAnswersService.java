package com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2;

import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.FininvisidPostData;
import com.sap.ibso.eservices.sagiaservices.investor.impl.DefaultInvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * FinanacialAnswersService
 */
public class FinanacialAnswersService extends AbstractSagiaService<FininvisidPostData> {
    DefaultInvestorMappingService investorMappingService;

    /** saves FinancialAnswers
     * @param fininvisidPostData fininvisidPostData
     * @return FininvisidPostData
     */
    public FininvisidPostData saveFinancialAnswers(FininvisidPostData fininvisidPostData){
        fininvisidPostData.setInvestorid(investorMappingService.getApplicantReferenceId(null));
        return super.saveAndParseResponse(fininvisidPostData, FininvisidPostData.class);
    }

    public void setInvestorMappingService(DefaultInvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }
}
