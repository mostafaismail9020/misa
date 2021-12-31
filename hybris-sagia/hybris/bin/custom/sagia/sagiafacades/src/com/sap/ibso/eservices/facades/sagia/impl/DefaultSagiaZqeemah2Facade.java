package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.zqeemah2.*;
import com.sap.ibso.eservices.facades.populators.odata.ServiceRequestCreationReverseODataPopulator;
import com.sap.ibso.eservices.facades.populators.odata.ServiceRequestODataCreationPopulator;
import com.sap.ibso.eservices.facades.populators.zqeemah.ISICDetailsPopulator;
import com.sap.ibso.eservices.facades.populators.zqeemah2.*;
import com.sap.ibso.eservices.facades.sagia.SagiaZqeemah2Facade;
import com.sap.ibso.eservices.sagiaservices.data.odata.ODataServiceRequestCreationData;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.*;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import com.sap.ibso.eservices.sagiaservices.services.impl.DiffQeemahService;
import com.sap.ibso.eservices.sagiaservices.services.impl.zqeemah2.*;
import com.sap.ibso.eservices.sagiaservices.services.isicdetails.ISICDetailsService;
import com.sap.ibso.eservices.sagiaservices.services.isicdetails.dto.ISICDetailsRequestParameters;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.DropdownValueService;
import com.sap.ibso.eservices.sagiaservices.services.license.application.odata.QeemahGeneralQstService;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sap.ibso.eservices.sagiaservices.services.odata.ServiceRequestODataCreationDataService;

import java.util.*;

import javax.annotation.Resource;

/**
 * DefaultSagiaZqeemah2Facade
 */
public class DefaultSagiaZqeemah2Facade implements SagiaZqeemah2Facade {

    private ISICDetailsService iSICDetailsService;
    private ISICDetailsPopulator zqeemahISICDetailsPopulator;
    private DropdownValuePopulator dropdownValuePopulator;
    private QeemahGeneralQstPopulator qeemahGeneralQstPopulator;
    private LegalStatusPopulator legalStatusPopulator;
    private DropdownValueService dropdownValueService;
    private QeemahGeneralQstService qeemahGeneralQstService;
    private FininvisidPostPopulator fininvsidPostPopulator;
    private FininvisidPostReversePopulator fininvsidPostReversePopulator;
    private FinanacialAnswersService finanacialAnswersService;
    private InvestorAnswersService investorAnswersService;
    private InvsIdPostPopulator invsIdPostPopulator;
    private InvsIdPostReversePopulator invsIdPostReversePopulator;

    private LegalStatusService legalStatusService;
    private BasicContactInfoReversePopulator basicContactInfoReversePopulator;
    private BasicContactInformationService basicContactInformationService;
    private OrganizationInformationReversePopulator organizationInformationReversePopulator;
    private BasicOrganizationInformationService basicOrganizationInformationService;

    @Autowired
    private CommonI18NService commonI18NService;

    private InvestorMappingService investorMappingService;
    private ShareholderReversePopulator shareholderReversePopulator;
    private ShareholdersService shareholdersService;
    private ProductReversePopulator productReversePopulator;
    private ProductService productService;
    private OppServiceCreationPopulator oppServiceCreationPopulator;
    private OppServiceCreationReversePopulator oppServiceCreationReversePopulator;
    private OppServiceCreationService oppServiceCreationService;
    private OppService2Service oppService2Service;

    private ServiceRequestCreationDataService serviceRequestCreationDataService;
    
    @Resource
    private ServiceRequestODataCreationDataService serviceRequestODataCreationDataService;
    
    private ServiceRequestCreationPopulator serviceRequestCreationPopulator;
    private ServiceRequestCreationReversePopulator serviceRequestCreationReversePopulator;
    
    @Resource
    private ServiceRequestODataCreationPopulator serviceRequestODataCreationPopulator;
    
    
    @Resource
    private ServiceRequestCreationReverseODataPopulator serviceRequestCreationReverseODataPopulator;
    
    private ServiceRequestMDService serviceRequestMDService;

    private DisplayAttachmentDescriptionPopulator displayAttachmentDescriptionPopulator;
    private DisplayAttachmentDescriptionService displayAttachmentDescriptionService;
    private FinancialQuestionService financialQuestionService;
    private FinancialQuestionPopulator financialQuestionPopulator;
    private DiffQeemahService diffQeemahService;
    private I18NService i18NService;

    @Override
    public Collection<ISICDetails> getISICDetails(String flag, String licenceType, String section) {
        ISICDetailsRequestParameters isicDetailsRequestParameters = new ISICDetailsRequestParameters()
                .setLanguage(getLanguage())
                .setFlag(flag)
                .setLictype(licenceType)
                .setSection(section)
                .setDivision("")
                .setComplimentary("")
                .setActivity("");
        Collection<ISICDetailsData> isicDetailsDataCollection = iSICDetailsService.getISICDetailsList(isicDetailsRequestParameters);
        Collection<ISICDetails> isicDetailsCollection = new ArrayList<>();
        for (ISICDetailsData item : isicDetailsDataCollection) {
            ISICDetails isicDetails = new ISICDetails();
            zqeemahISICDetailsPopulator.populate(item, isicDetails);
            isicDetailsCollection.add(isicDetails);
        }
        return isicDetailsCollection;
    }

    /**
     * saves DiffQeemah
     * @param qeemah qeemah
     */
    public void saveDiffQeemah(String qeemah){
        DiffQeemahData diffQeemahData = new DiffQeemahData();
        diffQeemahData.setQeemah(qeemah);
        diffQeemahService.saveDiff(diffQeemahData);
    }

    @Override
    public Collection<DropdownValue> getDropdownValues(String language, String flag, String region) {
        Collection<DropdownValueData> dropdownValuesData = dropdownValueService.getDropdownValuesData(language != null ? language : getLanguage(), flag, region);
        Collection<DropdownValue> dropdownValues = new ArrayList<>();
        for (DropdownValueData item : dropdownValuesData) {
            DropdownValue dropdownValue = new DropdownValue();
            dropdownValuePopulator.populate(item, dropdownValue);
            dropdownValues.add(dropdownValue);
        }
        return dropdownValues;
    }
   
    @Override
    public Collection<QeemahGeneralQst> getGeneralQuestonaireById(String id){
        Collection<QeemahGeneralQstData> generalQstServiceCollection = qeemahGeneralQstService.getCollection(getLanguage(), investorMappingService.getApplicantReferenceId(null), id);
        Collection<QeemahGeneralQst> qeemahGeneralQstCollection = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(generalQstServiceCollection)){
            generalQstServiceCollection.forEach(questionData -> {
                QeemahGeneralQst question = new QeemahGeneralQst();
                qeemahGeneralQstPopulator.populate(questionData, question);
                qeemahGeneralQstCollection.add(question);
            });
        }
        return qeemahGeneralQstCollection;
    }

    @Override
    public Map<String, List<QeemahGeneralDropdown>> getGeneralQst() {
        return getGeneralQst(investorMappingService.getApplicantReferenceId(null));
    }

    @Override
    public Collection<FinancialQuestion> getFinancialQuestions() {
        Collection<FinancialQuestion> financialQuestions = new ArrayList<>();
        Collection<FinancialQuestionData> financialQuestionData = financialQuestionService.getQuestions(i18NService.getCurrentLocale().getLanguage().toUpperCase());
        if(CollectionUtils.isNotEmpty(financialQuestionData)){
            financialQuestionData.forEach( item -> {
                FinancialQuestion financialQuestion = new FinancialQuestion();
                financialQuestionPopulator.populate(item, financialQuestion);
                financialQuestions.add(financialQuestion);
            });
        }
        return financialQuestions;
    }

    /**
     * saves NoStockQuestionsQuestions
     * @param fininvisidPost fininvisidPost
     * @return FininvisidPost
     */
    public FininvisidPost saveNoStockQuestionsQuestions(FininvisidPost fininvisidPost) {
        FininvisidPostData fininvisidPostData = new FininvisidPostData();
        fininvsidPostReversePopulator.populate(fininvisidPost, fininvisidPostData);

        FininvisidPostData fininvisidPostDataResult = finanacialAnswersService.saveFinancialAnswers(fininvisidPostData);
        FininvisidPost fininvisidPostResult = new FininvisidPost();
        fininvsidPostPopulator.populate(fininvisidPostDataResult, fininvisidPostResult);
        return fininvisidPostResult;
    }

    /**
     * saves InvestorAnswers
     * @param invsIdPost invsIdPost
     * @return InvsIdPost
     */
    public InvsIdPost saveInvestorAnswers(InvsIdPost invsIdPost) {
        InvsIdPostData invsIdPostData = new InvsIdPostData();
        invsIdPostReversePopulator.populate(invsIdPost, invsIdPostData);

        InvsIdPostData invsIdPostDataResult = investorAnswersService.saveFinancialAnswers(invsIdPostData);
        InvsIdPost invsIdPostResult = new InvsIdPost();
        invsIdPostPopulator.populate(invsIdPostDataResult, invsIdPostResult);
        return invsIdPostResult;
    }

    @Override
    public Map<String, List<QeemahGeneralDropdown>> getGeneralQst(String applicantId) {
        Map<String, List<QeemahGeneralDropdown>> result = new HashMap<>();
        Collection<QeemahGeneralQstData> generalQstServiceCollection = qeemahGeneralQstService.getCollection(getLanguage(), applicantId, "");
        for(QeemahGeneralQstData item : generalQstServiceCollection){
            QeemahGeneralQst qeemahGeneralQst = new QeemahGeneralQst();
            qeemahGeneralQstPopulator.populate(item, qeemahGeneralQst);

            String questionId1 = qeemahGeneralQst.getQuestionId();
            if ("Q10".equals(questionId1)) {
                result.put("licenseTypes", qeemahGeneralQst.getGeneralQuest());
            } else if ("Q40".equals(questionId1)) {
                result.put("stockMarketCountries", qeemahGeneralQst.getGeneralQuest());
            }
        }
        return result;
    }

    @Override
    public Map<String, Collection> getQeemah2Dropdowns() {
        Map<String, Collection> result = new HashMap<>();

        String language = getLanguage();

        Collection<DropdownValue> countries = getDropdownValues(language, "CY", null);
        result.put("countries", countries);

        Collection<DropdownValue> regions = getDropdownValues(language, "RG", null);
        result.put("regions", regions);

        Collection<DropdownValue> legalStatusShareholder = getDropdownValues(language, "LS", null);
        result.put("legalStatusShareholder", legalStatusShareholder);

        List<LegalStatus> legalStatusesOrganization = getLegalStatusesOrganization();
        result.put("legalStatusOrganization", legalStatusesOrganization);

        return result;
    }

    private List<LegalStatus> getLegalStatusesOrganization() {
        List<LegalStatus> legalStatuses = new ArrayList<>();
        Collection<LegalStatusData> legalStatusesData = legalStatusService.getLegalStatuses();
        if (legalStatusesData != null && !legalStatusesData.isEmpty()) {
            legalStatusesData.forEach(legalStatusData -> {
                LegalStatus legalStatus = new LegalStatus();
                legalStatusPopulator.populate(legalStatusData, legalStatus);
                legalStatuses.add(legalStatus);
            });
        }
        return legalStatuses;
    }

    private String getLanguage() {
        String lang = commonI18NService.getCurrentLanguage().getName();
        final String language;
        if(lang != null && lang.toUpperCase().startsWith("E")) {
            language = "EN";
        } else {
            language = "AR";
        }
        return language;
    }

    @Override
    public String saveBasicContactInfo(BasicContactInfo basicContactInfo) {
        BasicContactInfoData basicContactInfoData = new BasicContactInfoData();
        basicContactInfoReversePopulator.populate(basicContactInfo, basicContactInfoData);
        return basicContactInformationService.saveContact(basicContactInfoData);
    }

    @Override
    public String saveBasicOrganizationInformation(BasicOrganizationInformation basicOrganizationInformation) {
        BasicOrganizationInformationData basicOrganizationInformationData = new BasicOrganizationInformationData();
        organizationInformationReversePopulator.populate(basicOrganizationInformation, basicOrganizationInformationData);
        return basicOrganizationInformationService.saveOrganization(basicOrganizationInformationData);
    }

    @Override
    public String saveShareholders(Collection<Shareholder> shareholders) {
        Collection<ShareholderData> shareholderDataCollection = new ArrayList<>();
        for (Shareholder shareholder : shareholders) {
            ShareholderData shareholderData = new ShareholderData();
            shareholderReversePopulator.populate(shareholder, shareholderData);
            shareholderDataCollection.add(shareholderData);
        }
        return shareholdersService.saveShareholders(shareholderDataCollection);
    }

    @Override
    public String saveProduct(Collection<Product> products) {
        Collection<ProductData> productDataCollection = new ArrayList<>();
        for (Product product : products) {
            ProductData productData = new ProductData();
            productReversePopulator.populate(product, productData);
            productDataCollection.add(productData);
        }
        return productService.saveProducts(productDataCollection);
    }

    @Override
    public Collection<DisplayAttachmentDescription> getAttachmentsDescription() {
        Collection<DisplayAttachmentDescriptionData> displayAttachmentDescriptionDataCollection = displayAttachmentDescriptionService.getAttachmentsDescription();
        Collection<DisplayAttachmentDescription> displayAttachmentDescriptionCollection = new ArrayList<>();
        for (DisplayAttachmentDescriptionData displayAttachmentDescriptionData : displayAttachmentDescriptionDataCollection) {
            DisplayAttachmentDescription displayAttachmentDescription = new DisplayAttachmentDescription();
            displayAttachmentDescriptionPopulator.populate(displayAttachmentDescriptionData, displayAttachmentDescription);
            displayAttachmentDescriptionCollection.add(displayAttachmentDescription);
        }
        return displayAttachmentDescriptionCollection;
    }

    @Override
    public OppServiceCreation saveOppServiceCreation(OppServiceCreation oppServiceCreationFormData) {
        OppServiceCreationData data = new OppServiceCreationData();
        oppServiceCreationReversePopulator.populate(oppServiceCreationFormData, data);
        OppServiceCreationData oppServiceCreationData = oppServiceCreationService.saveOppServiceCreation(data);
        OppServiceCreation oppServiceCreation = new OppServiceCreation();
        oppServiceCreationPopulator.populate(oppServiceCreationData, oppServiceCreation);
        return oppServiceCreation;
    }

    /**
     * saves ServiceRequestCreation
     * @param serviceRequestCreation serviceRequestCreation
     * @return ServiceRequestCreation
     */
    public ServiceRequestCreation saveServiceRequestCreation(ServiceRequestCreation serviceRequestCreation,CustomerModel customer) {
        ServiceRequestCreationData data = new ServiceRequestCreationData();
        serviceRequestCreationReversePopulator.populate(serviceRequestCreation, data);
        ServiceRequestCreationData oppServiceCreationData = serviceRequestCreationDataService.saveServiceRequestCreation(data,customer);
        ServiceRequestCreation serviceRequestCreationResponse = new ServiceRequestCreation();
        serviceRequestCreationPopulator.populate(oppServiceCreationData, serviceRequestCreationResponse);
        return serviceRequestCreationResponse;
    }
    
    

    /**
     *
     * @param guid guid
     * @return the id of the license service request
     */
    @Override
    public String saveOppService2(String guid) {
        OppService2Data oppService2Data = oppService2Service.saveOppService2(guid);
        return oppService2Data.getOppid();
    }

    /**
     * saves ServiceRequestMD
     * @param guid guid
     * @return the id of the license service request
     */
    public String saveServiceRequestMD(String guid,CustomerModel customer) {
        ServiceRequestMDData oppService2Data = serviceRequestMDService.saveServiceRequestMD(guid,customer);
        return oppService2Data.getServiceReqId();
    }

    /**
     * sets iSICDetailsService
     * @param iSICDetailsService iSICDetailsService
     */
    public void setiSICDetailsService(ISICDetailsService iSICDetailsService) {
        this.iSICDetailsService = iSICDetailsService;
    }

    /**
     * @param zqeemahISICDetailsPopulator
     */
    public void setZqeemahISICDetailsPopulator(ISICDetailsPopulator zqeemahISICDetailsPopulator) {
        this.zqeemahISICDetailsPopulator = zqeemahISICDetailsPopulator;
    }


    /**
     * @param dropdownValuePopulator
     */
    public void setDropdownValuePopulator(DropdownValuePopulator dropdownValuePopulator) {
        this.dropdownValuePopulator = dropdownValuePopulator;
    }

    /**
     * @param qeemahGeneralQstPopulator
     */
    public void setQeemahGeneralQstPopulator(QeemahGeneralQstPopulator qeemahGeneralQstPopulator) {
        this.qeemahGeneralQstPopulator = qeemahGeneralQstPopulator;
    }

    /**
     * @param dropdownValueService
     */
    public void setDropdownValueService(DropdownValueService dropdownValueService) {
        this.dropdownValueService = dropdownValueService;
    }

    /**
     * @param qeemahGeneralQstService
     */
    public void setQeemahGeneralQstService(QeemahGeneralQstService qeemahGeneralQstService) {
        this.qeemahGeneralQstService = qeemahGeneralQstService;
    }

    /**
     * @param basicContactInfoReversePopulator
     */
    public void setBasicContactInfoReversePopulator(BasicContactInfoReversePopulator basicContactInfoReversePopulator) {
        this.basicContactInfoReversePopulator = basicContactInfoReversePopulator;
    }

    /**
     * @param basicContactInformationService
     */
    public void setBasicContactInformationService(BasicContactInformationService basicContactInformationService) {
        this.basicContactInformationService = basicContactInformationService;
    }

    /**
     * @param organizationInformationReversePopulator
     */
    public void setOrganizationInformationReversePopulator(OrganizationInformationReversePopulator organizationInformationReversePopulator) {
        this.organizationInformationReversePopulator = organizationInformationReversePopulator;
    }

    /**
     * @param basicOrganizationInformationService
     */
    public void setBasicOrganizationInformationService(BasicOrganizationInformationService basicOrganizationInformationService) {
        this.basicOrganizationInformationService = basicOrganizationInformationService;
    }

    /**
     * @param legalStatusPopulator
     */
    public void setLegalStatusPopulator(LegalStatusPopulator legalStatusPopulator) {
        this.legalStatusPopulator = legalStatusPopulator;
    }



    /**
     * @param shareholderReversePopulator
     */
    public void setShareholderReversePopulator(ShareholderReversePopulator shareholderReversePopulator) {
        this.shareholderReversePopulator = shareholderReversePopulator;
    }

    /**
     * @param shareholdersService
     */
    public void setShareholdersService(ShareholdersService shareholdersService) {
        this.shareholdersService = shareholdersService;
    }



    /**
     * @param legalStatusService
     */
    public void setLegalStatusService(LegalStatusService legalStatusService) {
        this.legalStatusService = legalStatusService;
    }


    /**
     * @param productReversePopulator
     */
    public void setProductReversePopulator(ProductReversePopulator productReversePopulator) {
        this.productReversePopulator = productReversePopulator;
    }


    /**
     * @param productService
     */
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @param oppServiceCreationPopulator
     */
    public void setOppServiceCreationPopulator(OppServiceCreationPopulator oppServiceCreationPopulator) {
        this.oppServiceCreationPopulator = oppServiceCreationPopulator;
    }

    public void setOppServiceCreationReversePopulator(OppServiceCreationReversePopulator oppServiceCreationReversePopulator) {
        this.oppServiceCreationReversePopulator = oppServiceCreationReversePopulator;
    }

    /**
     * @param oppServiceCreationService
     */
    public void setOppServiceCreationService(OppServiceCreationService oppServiceCreationService) {
        this.oppServiceCreationService = oppServiceCreationService;
    }

    public void setServiceRequestCreationDataService(ServiceRequestCreationDataService serviceRequestCreationDataService) {
        this.serviceRequestCreationDataService = serviceRequestCreationDataService;
    }

    public void setServiceRequestCreationPopulator(ServiceRequestCreationPopulator serviceRequestCreationPopulator) {
        this.serviceRequestCreationPopulator = serviceRequestCreationPopulator;
    }

    public void setServiceRequestCreationReversePopulator(ServiceRequestCreationReversePopulator serviceRequestCreationReversePopulator) {
        this.serviceRequestCreationReversePopulator = serviceRequestCreationReversePopulator;
    }

    /**
     * @param oppService2Service
     */
    public void setOppService2Service(OppService2Service oppService2Service) {
        this.oppService2Service = oppService2Service;
    }

    public void setServiceRequestMDService(ServiceRequestMDService serviceRequestMDService) {
        this.serviceRequestMDService = serviceRequestMDService;
    }


    /**
     * @param displayAttachmentDescriptionPopulator
     */
    public void setDisplayAttachmentDescriptionPopulator(DisplayAttachmentDescriptionPopulator displayAttachmentDescriptionPopulator) {
        this.displayAttachmentDescriptionPopulator = displayAttachmentDescriptionPopulator;
    }

    /**
     * @param displayAttachmentDescriptionService
     */
    public void setDisplayAttachmentDescriptionService(DisplayAttachmentDescriptionService displayAttachmentDescriptionService) {
        this.displayAttachmentDescriptionService = displayAttachmentDescriptionService;
    }

    /**
     *
     * @param financialQuestionService
     */
    public void setFinancialQuestionService(FinancialQuestionService financialQuestionService) {
        this.financialQuestionService = financialQuestionService;
    }

    public void setFinancialQuestionPopulator(FinancialQuestionPopulator financialQuestionPopulator) {
        this.financialQuestionPopulator = financialQuestionPopulator;
    }

    public void setFininvsidPostPopulator(FininvisidPostPopulator fininvsidPostPopulator) {
        this.fininvsidPostPopulator = fininvsidPostPopulator;
    }

    public void setFininvsidPostReversePopulator(FininvisidPostReversePopulator fininvsidPostReversePopulator) {
        this.fininvsidPostReversePopulator = fininvsidPostReversePopulator;
    }

    public void setFinanacialAnswersService(FinanacialAnswersService finanacialAnswersService) {
        this.finanacialAnswersService = finanacialAnswersService;
    }

    public void setInvestorAnswersService(InvestorAnswersService investorAnswersService) {
        this.investorAnswersService = investorAnswersService;
    }

    public void setInvsIdPostReversePopulator(InvsIdPostReversePopulator invsIdPostReversePopulator) {
        this.invsIdPostReversePopulator = invsIdPostReversePopulator;
    }

    public void setInvsIdPostPopulator(InvsIdPostPopulator invsIdPostPopulator) {
        this.invsIdPostPopulator = invsIdPostPopulator;
    }

    public void setInvestorMappingService(InvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }

    public void setDiffQeemahService(DiffQeemahService diffQeemahService) {
        this.diffQeemahService = diffQeemahService;
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
