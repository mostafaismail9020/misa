package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.zqeemah2.*;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides access to SagiaZqeemah2Facade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaZqeemah2Facade {
    /**
     * retrieves ISICDetails
     * @param flag flag
     * @param licenceType licenceType
     * @param section section
     * @return Collection of ISICDetails
     */
    Collection<ISICDetails> getISICDetails(String flag, String licenceType, String section);

    /**
     * retrieves Qeemah2Dropdowns
     * @return Map of Collections
     */
    Map<String, Collection> getQeemah2Dropdowns();

    /**
     * saves DiffQeemah
     * @param qeemah qeemah
     */
    void saveDiffQeemah(String qeemah);

    /**
     * retrieves DropdownValues
     * @param language language
     * @param flag flag
     * @param region region
     * @return Collection of DropdownValue
     */
    Collection<DropdownValue> getDropdownValues(String language, String flag, String region);

    /**
     * retrieves GeneralQst
     * @return Map of Lists of QeemahGeneralDropdown
     */
    Map<String, List<QeemahGeneralDropdown>> getGeneralQst();

    /**
     * retrieves GeneralQuestonaireById
     * @param id id
     * @return Collection of QeemahGeneralQst
     */
    Collection<QeemahGeneralQst> getGeneralQuestonaireById(String id);

    /**
     * Gets question used in Qeemah Determination
     * @return
     */
    Collection<FinancialQuestion> getFinancialQuestions();

    /**
     * Save responses to the financial questions (not on stock market)
     * @param fininvisidPost fininvisidPost
     * @return FininvisidPost
     */
    FininvisidPost saveNoStockQuestionsQuestions(FininvisidPost fininvisidPost);

    /**
     * saves InvestorAnswers
     * @param fininvisidPost fininvisidPost
     * @return InvsIdPost
     */
    InvsIdPost saveInvestorAnswers(InvsIdPost fininvisidPost);

    /**
     * retrieves GeneralQst
     * @param applicantId applicantId
     * @return Map of Lists of QeemahGeneralDropdown
     */
    Map<String, List<QeemahGeneralDropdown>> getGeneralQst(String applicantId);

    /**
     * saves BasicContactInfo
     * @param  basicContactInfo basicContactInfo 
     * @return String
     */
    String saveBasicContactInfo(BasicContactInfo basicContactInfo);

    /**
     * saves BasicOrganizationInformation
     * @param basicOrganizationInformation basicOrganizationInformation
     * @return String
     */
    String saveBasicOrganizationInformation(BasicOrganizationInformation basicOrganizationInformation);

    /**
     * saves Shareholders
     * @param shareholders shareholders
     * @return String
     */
    String saveShareholders(Collection<Shareholder> shareholders);

    /**
     * saves Product
     * @param products products
     * @return String
     */
    String saveProduct(Collection<Product> products);

    /**
     * retrieves AttachmentsDescription
     * @return Collection of DisplayAttachmentDescription
     */
    Collection<DisplayAttachmentDescription> getAttachmentsDescription();

    /**
     * saves OppServiceCreation
     * @param oppServiceCreationFormData oppServiceCreationFormData
     * @return OppServiceCreation
     */
    OppServiceCreation saveOppServiceCreation(OppServiceCreation oppServiceCreationFormData);


    /**
     * saves ServiceRequestCreation
     * @param serviceRequestCreation serviceRequestCreation
     * @return ServiceRequestCreation
     */
    ServiceRequestCreation saveServiceRequestCreation(ServiceRequestCreation serviceRequestCreation,CustomerModel customer);

    /**
     * saves OppService2
     * @param guid guid
     * @return String
     */
    String saveOppService2(String guid);

    /**
     * saves ServiceRequestMD
     * @param guid guid
     * @return String
     */
    String saveServiceRequestMD(String guid,CustomerModel customer);
    
    
  
}
