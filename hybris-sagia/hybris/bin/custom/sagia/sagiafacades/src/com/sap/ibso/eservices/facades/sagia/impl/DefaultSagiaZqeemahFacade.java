package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.BasicCompanyData;
import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.facades.data.zqeemah.*;
import com.sap.ibso.eservices.facades.populators.SagiaRhqActivitiesPopulator;
import com.sap.ibso.eservices.facades.populators.zqeemah.*;
import com.sap.ibso.eservices.facades.sagia.*;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.*;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.impl.*;
import com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah.*;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.ApplicationStatusService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.TelecodeService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.UserRegisterService;
import com.sap.ibso.eservices.sagiaservices.services.odata.BasicContactInformationService;
import com.sap.nic.NICUserData;
import com.sap.nic.soapservices.NicService;


import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.util.Config;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DefaultSagiaZqeemahFacade
 */
public class DefaultSagiaZqeemahFacade implements SagiaZqeemahFacade {
    private UserRegisterService userRegisterService;
    private ApplicationStatusService applicationStatusService;
    private TelecodeService telecodeService;
    private ShareholderInfoService shareholderInfoService;
    private DelegateInfoSetService delegateInfoSetService;
    private ShareholderAttachmentReversePopulator shareholderAttachmentReversePopulator;
    private ExistingShareholderService existingShareholderService;
    private MofaVerificationService mofaVerificationService;
    private UserRegisterPopulator userRegisterPopulator;
    private ApplicationStatusPopulator applicationStatusPopulator;
    private TelecodePopulator telecodePopulator;
    private ShareholderInfoPopulator shareholderInfoPopulator;
    private ShareholderInfoReversePopulator shareholderInfoReversePopulator;
    private DelegateInfoReversePopulator delegateInfoReversePopulator;
    private InvestorMappingService investorMappingService;
    private OrgInfoService orgInfoService;
    private OrganizationInformationReversePopulator organizationInformationReversePopulator;
    private OrgInfoDictionaryService orgInfoDictionaryService;
    private DropdownValuePopulator dropdownValuePopulator;
    private BasicContactInformationReversePopulatorQ1 basicContactInformationReversePopulatorQ1;
    private BasicContactInformationServiceQeemah1 basicContactInformationServiceQeemah1;
    private IsicDetPsReversePopulator isicDetPsReversePopulator;
    private SagiaIsicDetPsService isicDetPsService;
    private BusinessTypePopulator businessTypePopulator;
    private SagiaIsicDetService isicDetService;

    private ISICDetailsObjectsPopulator zqeemahISICDetailsObjectsPopulator;

    private SubmitServiceQ1 submitServiceQ1;
    private QeemahSubmitServiceQ1 qeemahSubmitServiceQ1;
    private LicenseTypePopulator licenseTypePopulator;
    private LicenseTypeService licenseTypeService;

    private SagiaLicenseTypeFacade sagiaLicenseTypeFacade;
    private SagiaLegalStatusFacade sagiaLegalStatusFacade;
    private SagiaRegionFacade sagiaRegionFacade;
    private SagiaCityFacade sagiaCityFacade;
    private SagiaCountryFacade sagiaCountryFacade;

    private ProductReversePopulator productReversePopulator;
    private ProductServiceQ1 productService;

    private EntrepreneurFileService entrepreneurFileService;

    private I18NService i18NService;

    private static final String CRM_ERROR_CODE = "E";
    private static final String TYPE_PERSON = "Person";
    private static final String MOFA_VALID = "X";
    
    @Value("${temporary.licese.constant.value}")
    private String TEMPORARYLICENSE;
    
    @Resource(name="nicService")
    private NicService nicService;

    @Resource(name="sagiaFormatProvider")
    private SagiaFormatProvider sagiaFormatProvider;
    
    @Resource
	private Converter<ShareHolderModel, ShareholderInfoData> shareHolderInfoODataConverter;
    
    @Resource
   	private Converter<DelegateInfoModel, DelegateInfoData> delegateInfoODataConverter;
    
    
    @Resource
   	private Converter<EntityInformationModel, OrgInfoData> organizationInformationODataConverter;
    
    @Resource
   	private Converter<ContactPersonModel, BasicContactInformationData> basicContactInformationODataConverter;
    
    @Resource
   	private Converter<IsicMasterModel, BusinessActivity> businessActivityODataConverter;
    
    @Resource
    private BasicContactInformationService odataBasicContactInformationService ;

    @Resource
    private LicenseApplyService licenseApplyService;
    @Resource
    private SagiaRhqActivitiesPopulator sagiaRhqActivitiesPopulator;
    
    
    
    
    
    /**
     * retrieves ApplicationStatus
     * @param investorId investorId
     * @return Collection of ApplicationStatus
     */
    public Collection<ApplicationStatus> getApplicationStatus(String investorId) {
        Collection<ApplicationStatusData> applicationStatusDataCollection = applicationStatusService.getApplicationStatus(investorId);
        Collection<ApplicationStatus> applicationStatusCollection = new ArrayList<>();
        for (ApplicationStatusData applicationStatusData : applicationStatusDataCollection) {
            ApplicationStatus applicationStatus = new ApplicationStatus();
            applicationStatusPopulator.populate(applicationStatusData, applicationStatus);
            applicationStatusCollection.add(applicationStatus);
        }
        return applicationStatusCollection;
    }

    /**
     * retrieves User
     * @param userRegData userRegData
     * @return Collection of UserRegister
     */
    public Collection<UserRegister> getUser(UserRegisterData userRegData) {
        Collection<UserRegisterData> userRegisterDataCollection = userRegisterService.getUser(userRegData);
        Collection<UserRegister> userRegisterCollection = new ArrayList<>();
        for (UserRegisterData userRegisterData : userRegisterDataCollection) {
            UserRegister userRegister = new UserRegister();
            userRegisterPopulator.populate(userRegisterData, userRegister);
            userRegisterCollection.add(userRegister);
        }
        return userRegisterCollection;
    }

    /**
     * retrieves TeleCode
     * @param countryCode countryCode
     * @return Telecode
     */
    public Telecode getTeleCode(String countryCode) {
        TelecodeData telecodeData = telecodeService.get(countryCode);
        Telecode telecode = new Telecode();
        telecodePopulator.populate(telecodeData, telecode);
        return telecode;
    }

    @Override
    public List<ShareholderInfo> getShareholdersInfo(String refId, String fileType, String entityNo) {
        List<ShareholderInfo> shareholdersInfo = new ArrayList<>();
        Collection<ShareholderInfoData> shareholdersInfoData = shareholderInfoService.getShareholdersInfo(refId, fileType, entityNo);
        if (shareholdersInfoData != null && !shareholdersInfoData.isEmpty()) {
            shareholdersInfoData.forEach(shareholderInfoData -> {
                ShareholderInfo shareholderInfo = new ShareholderInfo();
                shareholderInfoPopulator.populate(shareholderInfoData, shareholderInfo);
                shareholdersInfo.add(shareholderInfo);
            });
        }
        return shareholdersInfo;
    }

    /**
     * saves CompanyData
     * @param basicCompanyData basicCompanyData
     * @return BasicCompanyData
     */
    public BasicCompanyData saveCompanyData(BasicCompanyData basicCompanyData) {
        orgInfoService.updateOrgInfoData(basicCompanyData);
        return null;
    }

    /**
     * saves OrganizationInformation
     * @param basicCompanyData basicCompanyData
     * @return OrganizationInformation
     */
    public OrganizationInformation saveOrganizationInformation(OrganizationInformation basicCompanyData) {
        OrgInfoData orgInfoData = new OrgInfoData();
        organizationInformationReversePopulator.populate(basicCompanyData, orgInfoData);
        orgInfoService.updateOrganizationInformtion(orgInfoData,null);
        return null;
    }
    
    
    

    @Override   // clone this facade and create new method using a model as input and create a new ShareholderInfoODataPopulator. TO from Model to Odaa.
    public void createShareholders(List<ShareholderInfo> shareholderInfo) {
        if (CollectionUtils.isNotEmpty(shareholderInfo)) {
            List<ShareholderInfoData> shareHolderInfoDataList = new ArrayList<>();
            shareholderInfo.forEach(shareholder -> {
                ShareholderInfoData shareholderInfoData = new ShareholderInfoData();
                shareholder.setRefID(investorMappingService.getApplicantReferenceId(null));
                shareholderInfoReversePopulator.populate(shareholder, shareholderInfoData); /// create a new populator.
                shareHolderInfoDataList.add(shareholderInfoData);
            });
            shareholderInfoService.createShareholders(shareHolderInfoDataList);
            uploadShareholdersDocuments(shareholderInfo);
        }
    }
    
    
    
    @Override
    public void createDelegates(List<ShareholderInfo> shareholderInfo) {
    	
    
        if (CollectionUtils.isNotEmpty(shareholderInfo)) {
            List<DelegateInfoData> delegateInfoDataList = new ArrayList<DelegateInfoData>();
            shareholderInfo.forEach(shareholder -> {
            	if (hasDelegate(shareholder)) {
            		        DelegateInfo  delegate =  shareholder.getDelegate();
            				DelegateInfoData delegateInfoData = new DelegateInfoData();
            				delegateInfoData.setRefID(investorMappingService.getApplicantReferenceId(null));
            				delegateInfoReversePopulator.populate(delegate, delegateInfoData);
                    		delegateInfoDataList.add(delegateInfoData);
                    		//delegateInfoData.setIdIssueDate("2012-10-16T00:00:00"); delegateInfoData.setBirthdate("2012-10-16T00:00:00");
            		       		
            	}            	
            });
            
			if (!delegateInfoDataList.isEmpty()) {
				delegateInfoSetService.createDelegates(delegateInfoDataList);
			}
            uploadDelegatesDocuments(shareholderInfo);
        }
    }

	private boolean hasDelegate(ShareholderInfo shareholder) {
		return shareholder.getHasDelegateInfo() != null && shareholder.getHasDelegateInfo().equalsIgnoreCase("true") && shareholder.getSelfDelegate() != null && shareholder.getSelfDelegate().equalsIgnoreCase("false") && shareholder.getDelegate() != null;
	}
    
    /**
     * saves product
     * @param products products
     * @return String
     */
    public String saveProduct(Collection<Product> products) {
        Collection<ProductData> productDataCollection = new ArrayList<>();
        for (Product product : products) {
            ProductData productData = new ProductData();
            productReversePopulator.populate(product, productData);
            productDataCollection.add(productData);
        }
        return productService.saveProducts(productDataCollection);
    }

    /**
     * uploads ShareholdersDocuments
     * @param shareholderInfo shareholderInfo
     */
    public void uploadShareholdersDocuments(Collection<ShareholderInfo> shareholderInfo){
        shareholderInfo.forEach(shareholder -> {
            Collection<ShareholderAttachmentData> shareholderAttachmentDataList = new ArrayList<>();
            shareholder.getAttachments().forEach(shareholderAttachment -> {
                ShareholderAttachmentData shareholderAttachmentData = new ShareholderAttachmentData();
                shareholderAttachmentReversePopulator.populate(shareholderAttachment, shareholderAttachmentData);
                shareholderAttachmentData.setRefId(investorMappingService.getApplicantReferenceId(null));
                shareholderAttachmentDataList.add(shareholderAttachmentData);
            });
            shareholderInfoService.uploadFiles(shareholderAttachmentDataList);
        });
    }
    
    
    /**
     * uploads DelegatesDocuments
     * @param
     */
    public void uploadDelegatesDocuments(Collection<ShareholderInfo> shareholderInfo){
    	if (CollectionUtils.isNotEmpty(shareholderInfo)) {
            shareholderInfo.forEach(shareholder -> {
            	if (hasDelegate(shareholder)) {
            		        DelegateInfo delegate =  shareholder.getDelegate() ;
            			    Collection<DelegateAttachmentData> delegateAttachmentDataList = new ArrayList<>();
            			    delegate.getDelegateAttachments().forEach(delegateAttachment -> {
            			    	DelegateAttachmentData delegateAttachmentData = new DelegateAttachmentData();
            	                shareholderAttachmentReversePopulator.populate(delegateAttachment, delegateAttachmentData);
            	                delegateAttachmentData.setRefId(investorMappingService.getApplicantReferenceId(null));
            	                delegateAttachmentDataList.add(delegateAttachmentData);
            			    });
            			    delegateInfoSetService.uploadFiles(delegateAttachmentDataList);
       			    
            		   		
            	}            	
            });
        }
    }

    /**
     * uploads EntrepreneurFiles
     * @param entrepreneurFiles entrepreneurFiles
     */
    public void uploadEntrepreneurFiles(Collection<ShareholderAttachment> entrepreneurFiles){
        Collection<EntrepreneurFileData> entrepreneurDataFiles = new ArrayList<>();
        entrepreneurFiles.forEach(shareholderAttachment -> {
            EntrepreneurFileData shareholderAttachmentData = new EntrepreneurFileData();
            shareholderAttachmentData.setFileContent(shareholderAttachment.getFileContent());
            shareholderAttachmentData.setFileMtype(shareholderAttachment.getFileMimeType());
            shareholderAttachmentData.setFileName(shareholderAttachment.getFileName());
            shareholderAttachmentData.setFileType(shareholderAttachment.getFileType());
            shareholderAttachmentData.setRefId(investorMappingService.getApplicantReferenceId(null));
            entrepreneurDataFiles.add(shareholderAttachmentData);
        });
        entrepreneurFileService.uploadFiles(entrepreneurDataFiles);
    }

    /**
     * uploads ContactInformationDocument
     * @param attachment attachment
     */
    public void uploadContactInformationDocument(ShareholderAttachment attachment){
        ShareholderAttachmentData shareholderAttachmentData = new ShareholderAttachmentData();
        shareholderAttachmentReversePopulator.populate(attachment, shareholderAttachmentData);
        shareholderAttachmentData.setRefId(investorMappingService.getApplicantReferenceId(null));
        basicContactInformationServiceQeemah1.uploadFile(shareholderAttachmentData);
    }

    /**
     * uploads ActivityAttachments
     * @param attachments attachments
     */
    public void uploadActivityAttachments(Collection<ShareholderAttachment> attachments){
        for(ShareholderAttachment attachment : attachments){
            ShareholderAttachmentData activityAttachment = new ShareholderAttachmentData();
            shareholderAttachmentReversePopulator.populate(attachment, activityAttachment);
            activityAttachment.setRefId(investorMappingService.getApplicantReferenceId(null));
            basicContactInformationServiceQeemah1.uploadFile(activityAttachment);
        }
    }

    @Override
    public ValidateSareholder validateExistingShareholder(String shareholderEntityNumber) {
        ValidateSareholder shareholderInfo = existingShareholderService.validateExistingShareholder(shareholderEntityNumber);
        if (shareholderInfo != null && StringUtils.isNotEmpty(shareholderInfo.getBpNumber()) && !CRM_ERROR_CODE.equals(shareholderInfo.getReturnProperty())) {
            return shareholderInfo;
        }
        return null;
    }
    
    @Override
    public ValidateMofaNumber validateMofaNumber(String mofaNumber) {
    	ValidateMofaNumber mofaInfo = mofaVerificationService.validateMofaNumber(mofaNumber);
    	//ValidateMofaNumber mofaInfo = new ValidateMofaNumber();
    	//mofaInfo.setMofaNumber(mofaNumber);
    	//if(mofaNumber.startsWith("1234")) {
    		//mofaInfo.setIsMofaVerified("X");
    	//}
    	//else {
    		//mofaInfo.setIsMofaVerified("");
    	//}
    	
        if (mofaInfo != null && StringUtils.isNotEmpty(mofaInfo.getMofaNumber()) && MOFA_VALID.equalsIgnoreCase(mofaInfo.getIsMofaVerified())) {
            return mofaInfo;
        }
        return mofaInfo;
    }

    @Override
    public List<DropdownValue> getLegalStatusList() {
        return getSagiaLegalStatusFacade().getAllLegalStatus();
    }

    @Override
    public List<BusinessType> getBusinessTypeList() {
        List<BusinessType> businessTypes = new ArrayList<>();
        Collection<IsicDetData> businessTypesData = isicDetService.getISICDetailsList("L", i18NService.getCurrentLocale().getLanguage().substring(0, 1).toUpperCase());
        if (businessTypesData != null && !businessTypesData.isEmpty()) {
            businessTypesData.forEach(businessTypeData -> {
                        BusinessType businessType = new BusinessType();
                        businessTypePopulator.populate(businessTypeData, businessType);
                        businessTypes.add(businessType);
                    }
            );
        }
        return businessTypes;
    }

    @Override
    public List<IsicDetails> getISICFullDetailsList(String flag, String language, String isicSection, String isicDivision, String isicGroup, String isicClass){
        List<IsicDetails> isicDetailsList = new ArrayList<>();
        Collection<IsicDetData> isicDetailsListData = isicDetService.getISICFullDetailsList( flag, language, isicSection, isicDivision, isicGroup, isicClass);
        isicDetailsListData.forEach(isicDetData -> {

            IsicDetails isicDetails = new IsicDetails();
            zqeemahISICDetailsObjectsPopulator.populate(isicDetData, isicDetails);
            isicDetailsList.add(isicDetails);
        });

        return isicDetailsList;
    }

    @Override
    public List<DropdownValue> getRegionsList() {
        return getSagiaRegionFacade().getAllRegions();
    }
    
    @Override
    public List<DropdownValue> getRhqRegionsList() {
        return getSagiaRegionFacade().getRhqRegionsList();
    }

    @Override
    public List<DropdownValue> getCorporateActivities() {
        final List<RhqActivitiesModel> activities =licenseApplyService.getCorporateActivities();
        List<DropdownValue> corporateActivityList = new ArrayList<>();
        for (RhqActivitiesModel activity : activities) {

            DropdownValue dropdownValue = new DropdownValue();
            sagiaRhqActivitiesPopulator.populate(activity, dropdownValue);
            corporateActivityList.add(dropdownValue);
        }
        return corporateActivityList;
    }

    @Override
    public List<DropdownValue> getStrategicActivities() {
        final List<RhqActivitiesModel> activities =licenseApplyService.getStrategicActivities();
        List<DropdownValue> strategicActivityList = new ArrayList<>();
        for (RhqActivitiesModel activity : activities) {

            DropdownValue dropdownValue = new DropdownValue();
            sagiaRhqActivitiesPopulator.populate(activity, dropdownValue);
            strategicActivityList.add(dropdownValue);
        }
        return strategicActivityList;
    }

    @Override
    public List<DropdownValue> getManagementActivities() {
        final List<RhqActivitiesModel> activities = licenseApplyService.getManagementActivities();
        List<DropdownValue> managementActivityList = new ArrayList<>();
        for (RhqActivitiesModel activity : activities) {

            DropdownValue dropdownValue = new DropdownValue();
            sagiaRhqActivitiesPopulator.populate(activity, dropdownValue);
            managementActivityList.add(dropdownValue);
        }
        return managementActivityList;
    }

    @Override
    public List<DropdownValue> getCitiesList(final String region) {
        return getSagiaCityFacade().getAllCitiesForRegionCode(region);
    }

    @Override
    public void saveContactPerson(BasicContactInformation basicContactInformation) {
        BasicContactInformationData basicContactInformationData = new BasicContactInformationData();
        basicContactInformationReversePopulatorQ1.populate(basicContactInformation, basicContactInformationData);
        basicContactInformationServiceQeemah1.saveContact(basicContactInformationData);
    }

    @Override
    public void batchPostRequest(List<BusinessActivity> businessActivities, String licenseType, String businessType) {
        List<IsicDetPsSetData> isicDetPsSetDataList = new ArrayList<>();
        String applicantReferenceId = investorMappingService.getApplicantReferenceId(null);
        businessActivities.forEach(businessActivity -> {
            businessActivity.setInvestorId(applicantReferenceId);
            businessActivity.setLicenseType(licenseType);
            businessActivity.setBusinessType(businessType);

            IsicDetPsSetData isicDetPsSetData = new IsicDetPsSetData();
            isicDetPsReversePopulator.populate(businessActivity, isicDetPsSetData);
            isicDetPsSetDataList.add(isicDetPsSetData);
        });

        isicDetPsService.batchPostRequest(isicDetPsSetDataList);
    }

	@Override  
	public void saveBusinessActivities(List<BusinessActivity> businessActivities, String licenseType, String businessType, String temporaryTextValue, String licenseTypeCode,CustomerModel customer ) {  
	    String applicantReferenceId = investorMappingService.getApplicantReferenceId(customer);  
	    if(licenseTypeCode.equals(TEMPORARYLICENSE))  
	    {  
	        IsicDetPsSetData isicDetPsSetData = new IsicDetPsSetData();  
	        isicDetPsSetData.setInvestorid(applicantReferenceId);  
	        isicDetPsSetData.setActivity(licenseType);  
	        isicDetPsSetData.setLic(businessType);  
	        isicDetPsSetData.setActDesc(temporaryTextValue);  
	        isicDetPsService.saveBusinessActivity(isicDetPsSetData);  
	    }  
	    else  
	    {  
	     String activitiesDescription = businessActivities.stream().map(BusinessActivity::getDescription).collect(Collectors.joining(","));  
	     businessActivities.forEach(businessActivity -> {  
	         businessActivity.setInvestorId(applicantReferenceId);  
	         businessActivity.setLicenseType(licenseType);  
	         businessActivity.setBusinessType(businessType);  
	         businessActivity.setDescription(activitiesDescription);  
	         IsicDetPsSetData isicDetPsSetData = new IsicDetPsSetData();  
			 isicDetPsReversePopulator.populate(businessActivity, isicDetPsSetData);  
	         isicDetPsService.saveBusinessActivity(isicDetPsSetData);  
	     });  
	    }  
	}
	
	
	@Override  
	public void saveBusinessActivitiesOData(List<IsicMasterModel> businessActivitiesModels, String licenseType, String businessType, String temporaryTextValue, String licenseTypeCode,CustomerModel customer ) {  
	    
		
		List<BusinessActivity> businessActivities = businessActivityODataConverter.convertAll(businessActivitiesModels);
		
		saveBusinessActivities(businessActivities,  licenseType,  businessType,  temporaryTextValue,  licenseTypeCode, customer ) ;
	}
	

    @Override
    public String submitLicense(String advanceLicenseNr,CustomerModel customer) {
        QeemahSubmitData qeemahSubmitData = new QeemahSubmitData();
        qeemahSubmitData.setRefID(investorMappingService.getApplicantReferenceId(customer));
        if(advanceLicenseNr != null) {
            qeemahSubmitData.setAdvLicNo(advanceLicenseNr);
        }
        qeemahSubmitData = qeemahSubmitServiceQ1.saveQeemah(qeemahSubmitData);

        String leadId = qeemahSubmitData.getLeadID();

        SubmitData submitData = new SubmitData();
        submitData.setRefID(qeemahSubmitData.getRefID());
        submitData.setGuid(qeemahSubmitData.getGuid());
        submitServiceQ1.saveQeemah(submitData);


        return leadId;
    }

    @Override
    public List<DropdownValue> getCountriesList() {
        return getSagiaCountryFacade().getAllCountries();
    }
    
//    @Override
//    public List<DropdownValue> getRhqRegionList() {
//        return getSagiaRegionFacade().getRhqRegionList();
//    }
    @Override
    public NICUserData getNICUserdata(int idType, String idNumber, String dob)
    {
    	if(Config.getBoolean("nic.mockup.enable", false))
    	{
    		if(idNumber.contains("2345"))
    		{
    			NICUserData nicUserData = new NICUserData();
    			nicUserData.setResult("Valid Data");
    			String date = "";
    		 	
    	    	date = sagiaFormatProvider.formatBackEndStrToUIStr("20300628");
    	    	
    			nicUserData.setExpiryDate(date);
    			nicUserData.setFirstName_AR("حمد");
    			nicUserData.setLastName_AR("لمالك");
                nicUserData.setFirstName_EN("Hamad Mansour S");
                nicUserData.setSecondName_EN("AlMalik");
                nicUserData.setFullName_EN(nicUserData.getFirstName_EN() + " " + nicUserData.getSecondName_EN()+" "+nicUserData.getThirdName_EN()+" "+nicUserData.getLastName_EN());
                nicUserData.setPremium_Residency(true);
                nicUserData.setGender("Male");
    			nicUserData.setIdType(idType);
    			nicUserData.setIdNumber(idNumber);
    			nicUserData.setDob(dob);
    			date = sagiaFormatProvider.formatBackEndStrToUIStr("20160628");
    			nicUserData.setIssueDate(date);
    			return nicUserData;
    		}
    		return null;
    	}
    	
    	String birthDateStr = "";
    	if(idType == 1)
    	{
    		//For SaudiId calendar is Umm-Al-Qura
    		birthDateStr = StringUtils.isNotBlank(dob) ? sagiaFormatProvider.formatUiUAQStrToBackEndUAQStr(dob):"";
    	}
    	else
    	{
    		birthDateStr = StringUtils.isNotBlank(dob) ? sagiaFormatProvider.formatUIStrToBackStr(dob):"";
    	}
    	NICUserData nicUserData = nicService.getUserDetailsFromNIC(idType, idNumber, birthDateStr);
		convertDate(nicUserData, idType, idNumber, dob);
		return nicUserData;
    	 
    }

	private void convertDate(NICUserData nicUserData, int idType, String idNumber, String dob) {
		if(nicUserData != null)
		{
			nicUserData.setIdType(idType);
			nicUserData.setIdNumber(idNumber);
			nicUserData.setDob(dob);
			String expiryDate = nicUserData.getExpiryDate();
			String issueDate = nicUserData.getIssueDate();
			
		// 	if(idType == 1) change done by MISA team in NIC all the dates are Grogiarian
	    //	{
	    		//For SaudiId calendar convertto Umm-Al-Qura
		 //		expiryDate = StringUtils.isNotBlank(expiryDate) ? sagiaFormatProvider.formatBackEndUAQStrTouUiUAQStr(expiryDate):"";
	    //	}
	   // 	else
	   /// 	{
	    		expiryDate = StringUtils.isNotBlank(expiryDate) ? sagiaFormatProvider.formatBackEndStrToUIStr(expiryDate):"";
	    //	}
		/// 	
	        issueDate = StringUtils.isNotBlank(issueDate) ? sagiaFormatProvider.formatBackEndStrToUIStr(issueDate):"";
	        nicUserData.setIssueDate(issueDate);
			nicUserData.setExpiryDate(expiryDate);
		}
	}
    
//    private String formatDate(String date) {
//        LocalDateTime dateTime = sagiaFormatProvider.getDateTimeFromUIDateString(date);
//        return DateTimeFormatter.ofPattern(DefaultSagiaFormatProvider.DATE_FORMAT_NIC).format(dateTime);
//    }

    
    /**
     * getLicenseTypes
     * @return
     */
    public Collection<LicenseType> getLicenseTypes(){
        return sagiaLicenseTypeFacade.getAllLicenseTypes();
    }

    /**
     * @param userRegisterService
     */
    public void setUserRegisterService(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    /**
     * @param applicationStatusService
     */
    public void setApplicationStatusService(ApplicationStatusService applicationStatusService) {
        this.applicationStatusService = applicationStatusService;
    }

    /**
     * @param telecodeService
     */
    public void setTelecodeService(TelecodeService telecodeService) {
        this.telecodeService = telecodeService;
    }

    /**
     * @param userRegisterPopulator
     */
    public void setUserRegisterPopulator(UserRegisterPopulator userRegisterPopulator) {
        this.userRegisterPopulator = userRegisterPopulator;
    }

    /**
     * @param applicationStatusPopulator
     */
    public void setApplicationStatusPopulator(ApplicationStatusPopulator applicationStatusPopulator) {
        this.applicationStatusPopulator = applicationStatusPopulator;
    }

    /**
     * @param telecodePopulator
     */
    public void setTelecodePopulator(TelecodePopulator telecodePopulator) {
        this.telecodePopulator = telecodePopulator;
    }

    /**
     * @param shareholderInfoService
     */
    public void setShareholderInfoService(ShareholderInfoService shareholderInfoService) {
        this.shareholderInfoService = shareholderInfoService;
    }

    /**
     * @param shareholderAttachmentReversePopulator
     */
    public void setShareholderAttachmentReversePopulator(ShareholderAttachmentReversePopulator shareholderAttachmentReversePopulator) {
        this.shareholderAttachmentReversePopulator = shareholderAttachmentReversePopulator;
    }

    /**
     * @param shareholderInfoPopulator
     */
    public void setShareholderInfoPopulator(ShareholderInfoPopulator shareholderInfoPopulator) {
        this.shareholderInfoPopulator = shareholderInfoPopulator;
    }

    /**
     * @param orgInfoService
     */
    public void setOrgInfoService(OrgInfoService orgInfoService) {
        this.orgInfoService = orgInfoService;
    }


    /**
     * @param organizationInformationReversePopulator
     */
    public void setOrganizationInformationReversePopulator(OrganizationInformationReversePopulator organizationInformationReversePopulator) {
        this.organizationInformationReversePopulator = organizationInformationReversePopulator;
    }

    /**
     * @param shareholderInfoReversePopulator
     */
    public void setShareholderInfoReversePopulator(ShareholderInfoReversePopulator shareholderInfoReversePopulator) {
        this.shareholderInfoReversePopulator = shareholderInfoReversePopulator;
    }

    /**
     * Sets the investor mapping service.
     *
     * @param investorMappingService the investor mapping service
     */
    @Required
    public void setInvestorMappingService(InvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }

    /**
     * @param existingShareholderService
     */
    public void setExistingShareholderService(ExistingShareholderService existingShareholderService) {
        this.existingShareholderService = existingShareholderService;
    }
    
    /**
     * @param MofaVerificationService
     */
    public void setMofaVerificationService(MofaVerificationService mofaVerificationService) {
        this.mofaVerificationService = mofaVerificationService;
    }

    /**
     * @param orgInfoDictionaryService
     */
    public void setOrgInfoDictionaryService(OrgInfoDictionaryService orgInfoDictionaryService) {
        this.orgInfoDictionaryService = orgInfoDictionaryService;
    }

    /**
     * @param dropdownValuePopulator
     */
    public void setDropdownValuePopulator(DropdownValuePopulator dropdownValuePopulator) {
        this.dropdownValuePopulator = dropdownValuePopulator;
    }

    /**
     * @param i18NService
     */
    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }

    /**
     * @param basicContactInformationReversePopulatorQ1
     */
    public void setBasicContactInformationReversePopulatorQ1(BasicContactInformationReversePopulatorQ1 basicContactInformationReversePopulatorQ1) {
        this.basicContactInformationReversePopulatorQ1 = basicContactInformationReversePopulatorQ1;
    }

    /**
     * @param basicContactInformationServiceQeemah1
     */
    public void setBasicContactInformationServiceQeemah1(BasicContactInformationServiceQeemah1 basicContactInformationServiceQeemah1) {
        this.basicContactInformationServiceQeemah1 = basicContactInformationServiceQeemah1;
    }

    public void setIsicDetPsReversePopulator(IsicDetPsReversePopulator isicDetPsReversePopulator) {
        this.isicDetPsReversePopulator = isicDetPsReversePopulator;
    }

    public void setIsicDetPsService(SagiaIsicDetPsService isicDetPsService) {
        this.isicDetPsService = isicDetPsService;
    }

    public void setBusinessTypePopulator(BusinessTypePopulator businessTypePopulator) {
        this.businessTypePopulator = businessTypePopulator;
    }

    public void setIsicDetService(SagiaIsicDetService isicDetService) {
        this.isicDetService = isicDetService;
    }

    public void setSubmitServiceQ1(SubmitServiceQ1 submitServiceQ1) {
        this.submitServiceQ1 = submitServiceQ1;
    }

    public void setQeemahSubmitServiceQ1(QeemahSubmitServiceQ1 qeemahSubmitServiceQ1) {
        this.qeemahSubmitServiceQ1 = qeemahSubmitServiceQ1;
    }

    public void setZqeemahISICDetailsObjectsPopulator(ISICDetailsObjectsPopulator zqeemahISICDetailsObjectsPopulator) {
        this.zqeemahISICDetailsObjectsPopulator = zqeemahISICDetailsObjectsPopulator;
    }

    public void setLicenseTypePopulator(LicenseTypePopulator licenseTypePopulator) {
        this.licenseTypePopulator = licenseTypePopulator;
    }

    public void setLicenseTypeService(LicenseTypeService licenseTypeService) {
        this.licenseTypeService = licenseTypeService;
    }

    public void setProductReversePopulator(ProductReversePopulator productReversePopulator) {
        this.productReversePopulator = productReversePopulator;
    }

    public void setEntrepreneurFileService(EntrepreneurFileService entrepreneurFileService) {
        this.entrepreneurFileService = entrepreneurFileService;
    }

    public void setProductService(ProductServiceQ1 productService) {
        this.productService = productService;
    }

	public void setDelegateInfoSetService(DelegateInfoSetService delegateInfoSetService) {
		this.delegateInfoSetService = delegateInfoSetService;
	}

	public void setDelegateInfoReversePopulator(DelegateInfoReversePopulator delegateInfoReversePopulator) {
		this.delegateInfoReversePopulator = delegateInfoReversePopulator;
	}

    public void setSagiaLicenseTypeFacade(SagiaLicenseTypeFacade sagiaLicenseTypeFacade) {
        this.sagiaLicenseTypeFacade = sagiaLicenseTypeFacade;
    }

    public SagiaLicenseTypeFacade getSagiaLicenseTypeFacade() {
        return sagiaLicenseTypeFacade;
    }

    public SagiaLegalStatusFacade getSagiaLegalStatusFacade() {
        return sagiaLegalStatusFacade;
    }

    public void setSagiaLegalStatusFacade(SagiaLegalStatusFacade sagiaLegalStatusFacade) {
        this.sagiaLegalStatusFacade = sagiaLegalStatusFacade;
    }

    public SagiaRegionFacade getSagiaRegionFacade() {
        return sagiaRegionFacade;
    }

    public void setSagiaRegionFacade(SagiaRegionFacade sagiaRegionFacade) {
        this.sagiaRegionFacade = sagiaRegionFacade;
    }

    public SagiaCityFacade getSagiaCityFacade() {
        return sagiaCityFacade;
    }

    public void setSagiaCityFacade(SagiaCityFacade sagiaCityFacade) {
        this.sagiaCityFacade = sagiaCityFacade;
    }

    public SagiaCountryFacade getSagiaCountryFacade() {
        return sagiaCountryFacade;
    }

    public void setSagiaCountryFacade(SagiaCountryFacade sagiaCountryFacade) {
        this.sagiaCountryFacade = sagiaCountryFacade;
    }

	@Override
	public void createShareholdersInfoOData(List<ShareHolderModel> ShareHolderList) {
		
        if (CollectionUtils.isNotEmpty(ShareHolderList)) {
            List<ShareholderInfoData> shareHolderInfoDataList = new ArrayList<>();
                ShareHolderList.forEach(shareholder -> {
                ShareholderInfoData shareholderInfoData = new ShareholderInfoData();
               // shareholderInfoData.set(investorMappingService.getApplicantReferenceId(shareholder.getLicense().getCustomer())); 
                shareHolderInfoODataConverter.convert(shareholder,shareholderInfoData);/// create a new populator.
                shareHolderInfoDataList.add(shareholderInfoData);
            });
            shareholderInfoService.createShareholders(shareHolderInfoDataList);
           // uploadShareholdersDocuments(shareholderInfo);
        }
    
	}
	
	
	@Override
    public void createDelegatesInfoOData(List<ShareHolderModel> ShareHolderList) {
    	
    
        if (CollectionUtils.isNotEmpty(ShareHolderList)) {
            List<DelegateInfoData> delegateInfoDataList = new ArrayList<DelegateInfoData>();
            ShareHolderList.forEach(shareholder -> {
            	if (shareholder.getDelegateInfo() != null) {
            		        DelegateInfoModel  delegate =  shareholder.getDelegateInfo();
            				DelegateInfoData delegateInfoData = new DelegateInfoData();
            				delegateInfoData.setRefID(investorMappingService.getApplicantReferenceId(shareholder.getLicense().getCustomer()));
            				delegateInfoODataConverter.convert(delegate, delegateInfoData);
                    		delegateInfoDataList.add(delegateInfoData);
                    		//delegateInfoData.setIdIssueDate("2012-10-16T00:00:00"); delegateInfoData.setBirthdate("2012-10-16T00:00:00");
            		       		
            	}            	
            });
            
			if (!delegateInfoDataList.isEmpty()) {
				delegateInfoSetService.createDelegates(delegateInfoDataList);
			}
          //  uploadDelegatesDocuments(shareholderInfo);
        }
    }
	
	/**
     * saves OrganizationInformation
     * @param basicCompanyData basicCompanyData
     * @return OrganizationInformation
     */
	@Override
    public void saveOrganizationInformationOData(EntityInformationModel entityInformationModel) {
        OrgInfoData orgInfoData = new OrgInfoData();
        if (entityInformationModel == null ) 
        	return;
        organizationInformationODataConverter.convert(entityInformationModel,orgInfoData);
        orgInfoService.updateOrganizationInformtion(orgInfoData,entityInformationModel.getLicense().getCustomer());
 
    }
	
	
	
}


