package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.core.jalo.ShareHolder;
import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.facades.data.BasicCompanyData;
import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.facades.data.zqeemah.*;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.UserRegisterData;
import com.sap.nic.NICUserData;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Collection;
import java.util.List;

/**
 * Provides access to SagiaZqeemahFacade
 * @package com.sap.ibso.eservices.facades.sagia
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaZqeemahFacade {
    /**
     * Retrieves the Application Statuses
     * @param investorId investorId
     * @return Collection of ApplicationStatus
     */
    Collection<ApplicationStatus> getApplicationStatus(String investorId);

    /**
     * Retrieves a UserRegister collection
     * @param userRegisterData userRegisterData
     * @return Collection of UserRegister
     */
    Collection<UserRegister> getUser(UserRegisterData userRegisterData);

    /**
     * Retrieves TeleCode
     * @param countryCode countryCode
     * @return Telecode
     */
    Telecode getTeleCode(String countryCode);

    /**
     * Retrieves ShareholdersInfo
     * @param refId refId
     * @param fileType fileType
     * @param entityNo entityNo
     * @return Collection of ShareholderInfo
     */
    Collection<ShareholderInfo> getShareholdersInfo(String refId, String fileType, String entityNo);

    /**
     * saves CompanyData
     * @param basicCompanyData basicCompanyData
     * @return BasicCompanyData
     */
    BasicCompanyData saveCompanyData(BasicCompanyData basicCompanyData);

    /**
     * saves Organization Information
     * @param basicCompanyData basicCompanyData
     * @return OrganizationInformation
     */
    OrganizationInformation saveOrganizationInformation(OrganizationInformation basicCompanyData);

    /**
     * creates Shareholders
     * @param shareholderInfo shareholderInfo
     */
    void createShareholders(List<ShareholderInfo> shareholderInfo);

    /**
     * Save products for qeemah1 flow
     * @param products products
     * @return String
     */
    String saveProduct(Collection<Product> products);

    /**
     * Uploads documents associated to a shareholder
     * @param shareholderInfo shareholderInfo
     */
    void uploadShareholdersDocuments(Collection<ShareholderInfo> shareholderInfo);

    /**
     * Uploads EntrepreneurFiles
     * @param entrepreneurFiles entrepreneurFiles
     */
    void uploadEntrepreneurFiles(Collection<ShareholderAttachment> entrepreneurFiles);

    /**
     * Uploads basic contact information document
     * @param attachment attachment
     */
    void uploadContactInformationDocument(ShareholderAttachment attachment);

    /**
     * uploads ActivityAttachments
     * @param attachments attachments
     */
    void uploadActivityAttachments(Collection<ShareholderAttachment> attachments);

    /**
     * validates Existing Shareholder
     * @param shareholderEntityNumber shareholderEntityNumber
     * @return ShareholderInfoData
     */
    ValidateSareholder validateExistingShareholder(String shareholderEntityNumber);

    /**
     * Retrieves Countries List
     * @return List of DropdownValue
     */
    List<DropdownValue> getCountriesList();

    /**
     * Retrieves LegalStatus List
     * @return List of DropdownValue
     */
    List<DropdownValue> getLegalStatusList();

    /**
     * Retrieves BusinessType List
     * @return List of BusinessType
     */
    List<BusinessType> getBusinessTypeList();


    /**
     * Retrieves IsicDetails List
     * @param flag flag
     * @param language language
     * @param isicSection isicSection
     * @param isicDivision isicDivision
     * @param isicGroup isicGroup
     * @param isicClass isicClass
     * @return List of IsicDetails
     */
    List<IsicDetails> getISICFullDetailsList(String flag, String language, String isicSection, String isicDivision, String isicGroup, String isicClass);

    /**
     * Retrieves Regions List
     * @return List of DropdownValue
     */
    List<DropdownValue> getRegionsList();

    /**
     * Retrieves Cities List
     * @param region region
     * @return List of DropdownValue
     */
    List<DropdownValue> getCitiesList(String region);

    /**
     * Retrives license types
     * @return
     */
    Collection<LicenseType> getLicenseTypes();

    /**
     * saves ContactPerson
     * @param basicContactInformation basicContactInformation
     */
    void saveContactPerson(BasicContactInformation basicContactInformation);

    /**
     * saves BusinessActivities
     * @param businessActivities businessActivities
     * @param licenseType licenseType
     * @param businessType businessType
     */
    void batchPostRequest(List<BusinessActivity> businessActivities, String licenseType, String businessType);

    /**
     * saves BusinessActivities
     * @param businessActivities businessActivities
     * @param licenseType licenseType
     * @param businessType businessType
     */
	void saveBusinessActivities(List<BusinessActivity> businessActivities, String licenseType, String businessType, String temporaryTextValue, String licenseTypeCode , CustomerModel customer); 

    /**
     * saves License
     * @param advanceLicenseNr advanceLicenseNr
     * @return String
     */
	 public String submitLicense(String advanceLicenseNr,CustomerModel customer) ;

    /**
     * creates Delegates
     * @param shareholderInfo shareholderInfo
     */
    void createDelegates(List<ShareholderInfo> shareholderInfo);
    
    
    public void createShareholdersInfoOData(List<ShareHolderModel> ShareHolderList);
    
    public void createDelegatesInfoOData(List<ShareHolderModel> ShareHolderList);
    
	NICUserData getNICUserdata(int idType, String idNumber, String dob);

	void saveOrganizationInformationOData(EntityInformationModel entityInformationModel);

	
	void saveBusinessActivitiesOData(List<IsicMasterModel> businessActivitiesModels, String licenseType,
			String businessType, String temporaryTextValue, String licenseTypeCode, CustomerModel customer);

	
	ValidateMofaNumber validateMofaNumber(String mofaNumber);

    /**
     * Retrieves RHQ Regions List
     * @return List of DropdownValue
     * */
	List<DropdownValue> getRhqRegionsList();
	
    
}
