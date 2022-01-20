package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.FinancialSurveyData;
import com.sap.ibso.eservices.facades.data.MOJInheritSet;
import com.sap.ibso.eservices.facades.data.license.amendment.LicenseAmendment;
import com.sap.ibso.eservices.facades.data.license.amendment.Shareholder;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItems;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;
import de.hybris.platform.commercefacades.user.data.Messsage;

import java.util.Collection;
import java.util.List;

/**
 * Created by i335541 on 2/12/18.
 */
public interface SagiaFinancialSurveyFacade {

    List<Messsage> getFinancialSurveyMessages(String quarterCode);

    List<Messsage> addFinancialSurveyMessage(String messageContent, String quarterCode);

    /**
     * retrieves LicenseAmendmentsHeaders
     * @return List of LicenseAmendment
     */
    List<LicenseAmendment> getLicenseAmendmentsHeaders();

    /**
     * retrieves LicenseAmendment
     * @param srId srId
     * @return LicenseAmendment
     */
    FinancialSurvey getFinancialSurvey(String srId);

    /**
     * retrieves LicenseAmendmentTypes
     * @param licenseAmendment licenseAmendment
     * @return LicenseAmendment
     */
    LicenseAmendment getLicenseAmendmentTypes(LicenseAmendment licenseAmendment);

    /**
     * checks LicenseAmendmentAvailability
     */
    void checkLicenseAmendmentAvailability();

    /**
     * checks if is InstantAmendment
     * @param licenseAmendment licenseAmendment
     * @return boolean
     */
    boolean isInstantAmendment(LicenseAmendment licenseAmendment);

    /**
     * savesLicenseAmendment
     *
     * @param financialSurvey financialSurvey
     */
    void saveFinancialSurvey(FinancialSurvey financialSurvey);

    /**
     * retrieves Shareholder
     * @param shareholderId shareholderId
     * @return Shareholder
     */
    Shareholder getShareholder(String shareholderId);

    /**
     * retrieves ListItems
     * @return ListItems
     */
    ListItems getListItems();

    /**
     * retrieves AmendProductsListWithId
     * @param productId productId
     * @param skip skip
     * @param top top
     * @return Collection of ProductData
     */
    Collection<ProductData> getAmendProductsListWithId(String productId, String skip, String top);

    /**
     * retrieves AmendProductsListWithDescription
     * @param productDescription productDescription
     * @param skip skip
     * @param top top
     * @return Collection of ProductData
     */
    Collection<ProductData> getAmendProductsListWithDescription(String productDescription, String skip, String top);

    /**
     * retrieves AmendProductsList
     * @param skip skip
     * @param top top
     * @return Collection of ProductData
     */
    Collection<ProductData> getAmendProductsList(String skip, String top);


    MOJInheritSet getVerifyInherit(String deceasedId, String deedNumber);

    List<FinancialSurveyData> getFinancialSurveyList();
}
