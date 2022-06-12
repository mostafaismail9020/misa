package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import com.sap.ibso.eservices.core.model.SagiaCompanyProfileModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaCityService;
import com.sap.ibso.eservices.core.sagia.services.SagiaCountryService;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.core.sagia.services.SagiaLegalStatusService;
import com.sap.ibso.eservices.core.sagia.services.SagiaRegionService;
import com.sap.ibso.eservices.core.sagia.services.SagiaSectorService;
import com.sap.ibso.eservices.facades.data.FinancialSurveyData;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.AttachmentListItem;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItem;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItems;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.SubListItem;
import com.sap.ibso.eservices.facades.populators.financial.survey.CompanyProfilePopulator;
import com.sap.ibso.eservices.facades.populators.financial.survey.FinancialSurveyPopulator;
import com.sap.ibso.eservices.facades.populators.financial.survey.FinancialSurveyReversePopulator;
import com.sap.ibso.eservices.facades.populators.license.amendment.AttachmentListItemPopulator;
import com.sap.ibso.eservices.facades.populators.license.amendment.ListItemPopulator;
import com.sap.ibso.eservices.facades.populators.license.amendment.ShareholderPopulator;
import com.sap.ibso.eservices.facades.populators.license.amendment.SubListItemPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaFinancialSurveyFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.services.financialsurvey.SagiaFinancialSurveyService;
import com.sap.ibso.eservices.sagiaservices.services.impl.CustomizationListService;
import com.sap.ibso.eservices.sagiaservices.services.impl.GlobalValsService;
import de.hybris.platform.commercefacades.user.data.CompanyProfileData;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;
import de.hybris.platform.commercefacades.user.data.Messsage;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * DefaultFinancialSurveyFacade
 */
public class DefaultFinancialSurveyFacade implements SagiaFinancialSurveyFacade {

    private static final String REGION = "REGION";
    private static final String CITY = "CITY";
    private static final String BRANCHTYPE = "BRANCHTYPE";
    private static final String GENDER = "GENDER";
    private static final String MARITALSTATUS = "MARITALSTATUS";
    private static final String ACADEMICTITLE = "ACADEMICTITLE";
    private static final String PREMIUMRESIDENT = "PREMIUMRESIDENT";
    private static final String UNIT = "UNIT";
    private static final String INDUSTRY = "INDUSTRY";
    private static final String MULTINATCOMP = "MULTINATCOMP";
    private static final String LEGALSTATUS = "LEGALSTATUS";
    private static final String COUNTRY = "COUNTRY";
    private static final String ATTACHMENT = "ATTACHMENT";

    @Resource
    private SagiaFormatProvider sagiaFormatProvider ;


    @Resource(name = "sagiaRegionService")
    private SagiaRegionService sagiaRegionService;

    @Resource(name = "sagiaLegalStatusService")
    private SagiaLegalStatusService sagiaLegalStatusService;

    @Resource(name = "sagiaCityService")
    private SagiaCityService sagiaCityService;

    @Resource(name = "sagiaSectorService")
    private SagiaSectorService sagiaSectorService;

    @Resource(name = "sagiaCountryService")
    private SagiaCountryService sagiaCountryService;

    private SagiaFinancialSurveyService sagiaFinancialSurveyService;

    private CustomizationListService customizationListService;
    private GlobalValsService globalValsService;

    @Resource
    private FinancialSurveyPopulator financialSurveyPopulator;

    @Resource
    private CompanyProfilePopulator companyProfilePopulator;

    private FinancialSurveyReversePopulator financialSurveyReversePopulator;
    private ShareholderPopulator shareholderPopulator;
    private ListItemPopulator listItemPopulator;
    private SubListItemPopulator subListItemPopulator;
    private AttachmentListItemPopulator attachmentListItemPopulator;
    private MessageSource messageSource;
    private I18NService i18nService;


    @Override
    public FinancialSurvey getFinancialSurvey(String quarterCode) {

        FinancialSurveyModel financialSurveyModel = sagiaFinancialSurveyService.getFinancialSurvey(quarterCode);
        FinancialSurvey financialSurvey = new FinancialSurvey();
        financialSurveyPopulator.populate(financialSurveyModel,financialSurvey);
        financialSurvey.setQuarterCode(quarterCode);

        List<Messsage> messsageList = new ArrayList<>();
        financialSurveyModel.getMessages().forEach(
                messageModel -> {
                    Messsage message = new Messsage();
                    message.setCommentBy(messageModel.getCommentBy().getName());
                    message.setCommentDate(messageModel.getCreationtime().toString());
                    message.setContent(messageModel.getContent());
                    messsageList.add(message);
                }
        );
        financialSurvey.setMessages(messsageList);
        CompanyProfileData companyProfileData = new CompanyProfileData();
        SagiaCompanyProfileModel companyProfileModel = sagiaFinancialSurveyService.getCompanyProfile();
        if (companyProfileModel != null ){
            companyProfilePopulator.populate(companyProfileModel,companyProfileData);
        }
        financialSurvey.setCompanyProfile(companyProfileData);

        return financialSurvey;
    }


    @Override
    public List<Messsage> getFinancialSurveyMessages(String quarterCode) {

        FinancialSurveyModel financialSurveyModel = sagiaFinancialSurveyService.getFinancialSurvey(quarterCode);

        List<Messsage> messsageList = new ArrayList<>();
        financialSurveyModel.getMessages().forEach(
                messageModel -> {
                    Messsage message = new Messsage();
                    message.setCommentBy(messageModel.getCommentBy().getName());
                    message.setCommentDate(messageModel.getCreationtime().toString());
                    message.setContent(messageModel.getContent());
                    messsageList.add(message);
                }
        );
        return messsageList;
    }

    @Override
    public List<Messsage> addFinancialSurveyMessage(String messageContent, String quarterCode) {

        sagiaFinancialSurveyService.saveMessage(messageContent, quarterCode);


        FinancialSurveyModel financialSurveyModel = sagiaFinancialSurveyService.getFinancialSurvey(quarterCode);

        List<Messsage> messsageList = new ArrayList<>();
        financialSurveyModel.getMessages().forEach(
                messageModel -> {
                    Messsage message = new Messsage();
                    message.setCommentBy(messageModel.getCommentBy().getName());
                    message.setCommentDate(messageModel.getCreationtime().toString());
                    message.setContent(messageModel.getContent());
                    messsageList.add(message);
                }
        );
        return messsageList;
    }


    @Override
    public List<FinancialSurveyData> getFinancialSurveyList() {

        List<FinancialSurveyData> financialSurveyDataList = new ArrayList<>();
        List<FinancialSurveyModel> financialSurveyModelList = sagiaFinancialSurveyService.getFinancialSurveys();
        for (FinancialSurveyModel financialSurveyModel: financialSurveyModelList ) {
            FinancialSurveyData financialSurveyData = new FinancialSurveyData();
            financialSurveyData.setStatus(financialSurveyModel.getSurveyStatus().getCode());
            financialSurveyData.setStatusKey(financialSurveyModel.getSurveyStatus().getCode());
            financialSurveyData.setQuarter(financialSurveyModel.getQuarter().getName());
            financialSurveyData.setQuarterCode(financialSurveyModel.getQuarter().getCode());
            financialSurveyData.setLastUpdate(sagiaFormatProvider.formatBackEndDateToUIStr(financialSurveyModel.getModifiedtime()));
            financialSurveyDataList.add(financialSurveyData);
        }
        return financialSurveyDataList;
    }

    @Override
    public void saveFinancialSurveyBranchesAndSubsidiaries(FinancialSurvey financialSurvey) {
        sagiaFinancialSurveyService.saveFinancialSurveyBranchesAndSubsidiaries(financialSurvey);
    }

    @Override
    public void saveFinancialSurveyShareholders(FinancialSurvey financialSurvey) {
        sagiaFinancialSurveyService.saveFinancialSurveyShareholders(financialSurvey);
    }

    @Override
    public void submitFinancialSurveyForReview(MediaModel mediaModel,String quarterCode, Integer hoursToCompleteSurvey, Integer minutesToCompleteSurvey, String sourceOfKnowledge) {
        sagiaFinancialSurveyService.submitFinancialSurveyForReview( mediaModel, quarterCode, hoursToCompleteSurvey, minutesToCompleteSurvey, sourceOfKnowledge);
    }





    @Override
    public void saveShareholderEquity(FinancialSurvey financialSurvey) {
        sagiaFinancialSurveyService.saveFinancialSurveyShareholderEquity(financialSurvey);
    }

    @Override
    public void saveFinancialSurvey(FinancialSurvey financialSurveyData) {
        sagiaFinancialSurveyService.saveFinancialSurvey(financialSurveyData);
    }

    @Override
    public void saveFinancialSurveyCompanyProfile(FinancialSurvey financialSurveyData) {
        sagiaFinancialSurveyService.saveFinancialSurveyCompanyProfile(financialSurveyData);
    }



    @Override
    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | The cyclomatic complexity of methods should not exceed a defined threshold.)
     * Suppress sonar warning (squid:S138 | Methods should not have too many lines
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:MethodCyclomaticComplexity","squid:S138"})
    public ListItems getListItems() {

        List<ListItem> branchTypes = new ArrayList<>();
        List<ListItem> regions = new ArrayList<>();
        List<SubListItem> cities = new ArrayList<>();
        List<ListItem> sectors = new ArrayList<>();
        List<ListItem> legalStatus = new ArrayList<>();
        List<ListItem> gender = new ArrayList<>();
        List<ListItem> status = new ArrayList<>();
        List<ListItem> academicTitle = new ArrayList<>();
        List<ListItem> premiumResident = new ArrayList<>();
        List<ListItem> unit = new ArrayList<>();
        List<ListItem> multinationalCompany = new ArrayList<>();
        List<ListItem> countries = new ArrayList<>();
        List<AttachmentListItem> attachments = new ArrayList<>();


        /*for (CustomizingGetData data : customizationListService.getLicenseAmendmentListItems()) {

            switch (data.getFieldname() == null ? "" : data.getFieldname()) {

                case BRANCHTYPE:
                    branchTypes.add(getListItem(data));
                    break;

                case REGION:
                    //regions.add(getListItem(data));
                    break;

                case CITY:
                  //  cities.add(getSubListItem(data));
                    break;

                case INDUSTRY:
                    //sectors.add(getListItem(data));
                    break;

                case LEGALSTATUS:
                    //legalStatus.add(getListItem(data));
                    break;

                case GENDER:
                    gender.add(getListItem(data));
                    break;

                case MARITALSTATUS:
                    status.add(getListItem(data));
                    break;

                case ACADEMICTITLE:
                    academicTitle.add(getListItem(data));
                    break;

                case PREMIUMRESIDENT:
                    premiumResident.add(getListItem(data));
                    break;

                case UNIT:
                    unit.add(getListItem(data));
                    break;

                case MULTINATCOMP:
                    multinationalCompany.add(getListItem(data));
                    break;

                case COUNTRY:
                    //countries.add(getListItem(data));
                    break;

                case ATTACHMENT:
                    //attachments.add(getAttachmentListItem(data));
                    break;

                default:
                    break;

            }
        }*/


        ListItems listItemsResult = new ListItems();




        sagiaRegionService.getAllRegions().stream().forEach(regionModel -> {ListItem item = new ListItem() ;item.setId(regionModel.getCode());item.setName(regionModel.getName()); regions.add(item) ;}  );
        regions.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setRegions(regions);



        sagiaLegalStatusService.getAllLegalStatus().stream().forEach(legalStatusModel -> {ListItem item = new ListItem() ;item.setId(legalStatusModel.getCode());item.setName(legalStatusModel.getName()); legalStatus.add(item) ;} );
        legalStatus.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setLegalStatus(legalStatus);


        sagiaCityService.getAllCities().stream().forEach(cityModel -> {SubListItem item = new SubListItem() ;item.setId(cityModel.getCode());item.setName(cityModel.getName());item.setParentId(cityModel.getRegion().getCode()); cities.add(item) ;}  );
        cities.sort(Comparator.comparing(SubListItem::getName));
        listItemsResult.setCities(cities);

        sagiaSectorService.getSectors().stream().forEach(sectorModel -> {ListItem item = new ListItem() ;item.setId(sectorModel.getCode());item.setName(sectorModel.getName()); sectors.add(item) ;}  );
        sectors.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setSectors(sectors);

        sagiaCountryService.getCountries().stream().forEach(countryModel -> {ListItem item = new ListItem() ;item.setId(countryModel.getCode());item.setName(countryModel.getName()); countries.add(item) ;}  );
        countries.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setCountries(countries);


        ListItem itemGender1 =  new ListItem() ;
        itemGender1.setId("male");
        itemGender1.setName(getLocalizedValue("type.male"));

        ListItem itemGender2 =  new ListItem() ;
        itemGender2.setId("female");
        itemGender2.setName(getLocalizedValue("type.female"));

        gender.add(itemGender2);
        gender.add(itemGender1);

        gender.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setGender(gender);


        List<ListItem> custombranchTypes = new ArrayList<>();
        ListItem listItem =  new ListItem() ;
        listItem.setId("head_office");
        listItem.setName(getLocalizedValue("type.head.office"));

        ListItem listItem2 =  new ListItem() ;
        listItem2.setId("branch");
        listItem2.setName(getLocalizedValue("type.branch"));

        custombranchTypes.add(listItem );
        custombranchTypes.add(listItem2 );
        listItemsResult.setBranchTypes(custombranchTypes);



        status.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setStatus(status);

        academicTitle.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setAcademicTitle(academicTitle);

        premiumResident.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setPremiumResident(premiumResident);

        unit.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setUnit(unit);


		ListItem itemMultinationalCompany1 =  new ListItem() ;
        itemMultinationalCompany1.setId("yes");
        itemMultinationalCompany1.setName(getLocalizedValue("type.yes"));

        ListItem itemMultinationalCompany2 =  new ListItem() ;
        itemMultinationalCompany2.setId("no");
        itemMultinationalCompany2.setName(getLocalizedValue("type.no"));

        multinationalCompany.add(itemMultinationalCompany2);
        multinationalCompany.add(itemMultinationalCompany1);

        multinationalCompany.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setMultinationalCompany(multinationalCompany);


        attachments.sort(Comparator.comparing(AttachmentListItem::getName));
        listItemsResult.setAttachments(attachments);

        return listItemsResult;
    }


    private String getLocalizedValue(String message) {
        return messageSource.getMessage(message, null, i18nService.getCurrentLocale());
    }

    private AttachmentListItem getAttachmentListItem(CustomizingGetData data) {
        AttachmentListItem attach = new AttachmentListItem();
        attachmentListItemPopulator.populate(data, attach);
        return attach;
    }

    private SubListItem getSubListItem(CustomizingGetData data) {
        SubListItem city = new SubListItem();
        subListItemPopulator.populate(data, city);
        return city;
    }

    private ListItem getListItem(CustomizingGetData data) {
        ListItem listItem = new ListItem();
        listItemPopulator.populate(data, listItem);
        return listItem;
    }


    /**
     * @return
     */
    public GlobalValsService getGlobalValsService() {
        return globalValsService;
    }

    /**
     * @param globalValsService
     */
    public void setGlobalValsService(GlobalValsService globalValsService) {
        this.globalValsService = globalValsService;
    }

    public SagiaFinancialSurveyService getSagiaFinancialSurveyService() {
        return sagiaFinancialSurveyService;
    }

    public void setSagiaFinancialSurveyService(SagiaFinancialSurveyService sagiaFinancialSurveyService) {
        this.sagiaFinancialSurveyService = sagiaFinancialSurveyService;
    }

    /**
     * @return
     */
    public CustomizationListService getCustomizationListService() {
        return customizationListService;
    }

    /**
     * @param customizationListService
     */
    public void setCustomizationListService(CustomizationListService customizationListService) {
        this.customizationListService = customizationListService;
    }

    /**
     * @return
     */
    public ListItemPopulator getListItemPopulator() {
        return listItemPopulator;
    }

    /**
     * @param listItemPopulator
     */
    public void setListItemPopulator(ListItemPopulator listItemPopulator) {
        this.listItemPopulator = listItemPopulator;
    }

    /**
     * @return
     */
    public SubListItemPopulator getSubListItemPopulator() {
        return subListItemPopulator;
    }

    /**
     * @param subListItemPopulator
     */
    public void setSubListItemPopulator(SubListItemPopulator subListItemPopulator) {
        this.subListItemPopulator = subListItemPopulator;
    }


    /**
     * @return
     */
    public ShareholderPopulator getShareholderPopulator() {
        return shareholderPopulator;
    }

    /**
     * @param shareholderPopulator
     */
    public void setShareholderPopulator(ShareholderPopulator shareholderPopulator) {
        this.shareholderPopulator = shareholderPopulator;
    }

    /**
     * @return
     */
    public AttachmentListItemPopulator getAttachmentListItemPopulator() {
        return attachmentListItemPopulator;
    }

    /**
     * @param attachmentListItemPopulator
     */
    public void setAttachmentListItemPopulator(AttachmentListItemPopulator attachmentListItemPopulator) {
        this.attachmentListItemPopulator = attachmentListItemPopulator;
    }


    public FinancialSurveyReversePopulator getFinancialSurveyReversePopulator() {
        return financialSurveyReversePopulator;
    }

    public void setFinancialSurveyReversePopulator(FinancialSurveyReversePopulator financialSurveyReversePopulator) {
        this.financialSurveyReversePopulator = financialSurveyReversePopulator;
    }

    /**
     *
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     *
     * @param i18nService
     */
    public void setI18nService(I18NService i18nService) {
        this.i18nService = i18nService;
    }



}
