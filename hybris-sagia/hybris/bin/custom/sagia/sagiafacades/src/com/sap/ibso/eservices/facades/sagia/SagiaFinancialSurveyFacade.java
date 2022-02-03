package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.FinancialSurveyData;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItems;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;
import de.hybris.platform.commercefacades.user.data.Messsage;
import de.hybris.platform.core.model.media.MediaModel;

import java.util.List;

/**
 * Created by MAB on 2/12/2021.
 */
public interface SagiaFinancialSurveyFacade {

    List<Messsage> getFinancialSurveyMessages(String quarterCode);

    List<Messsage> addFinancialSurveyMessage(String messageContent, String quarterCode);


    /**
     * retrieves LicenseAmendment
     * @param srId srId
     * @return LicenseAmendment
     */
    FinancialSurvey getFinancialSurvey(String srId);

    /**
     * savesLicenseAmendment
     *
     * @param financialSurvey financialSurvey
     */
    void saveFinancialSurvey(FinancialSurvey financialSurvey);

    void saveFinancialSurveyCompanyProfile(FinancialSurvey financialSurveyData);

    /**
     * retrieves ListItems
     * @return ListItems
     */
    ListItems getListItems();

    List<FinancialSurveyData> getFinancialSurveyList();

    void saveFinancialSurveyBranchesAndSubsidiaries(FinancialSurvey financialSurvey);

    void saveShareholderEquity(FinancialSurvey financialSurvey);

    void saveFinancialSurveyShareholders(FinancialSurvey financialSurvey);

    void submitFinancialSurveyForReview(MediaModel mediaModel, String quarterCode);
}
