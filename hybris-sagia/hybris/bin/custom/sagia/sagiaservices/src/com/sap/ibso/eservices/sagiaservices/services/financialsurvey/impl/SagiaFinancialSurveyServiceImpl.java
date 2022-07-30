package com.sap.ibso.eservices.sagiaservices.services.financialsurvey.impl;

import com.sap.ibso.eservices.core.enums.FinancialSurveyAffiliateType;
import com.sap.ibso.eservices.core.enums.FinancialSurveyCompanyStatus;
import com.sap.ibso.eservices.core.enums.FinancialSurveyScaleLevel;
import com.sap.ibso.eservices.core.enums.FinancialSurveyShareholderType;
import com.sap.ibso.eservices.core.enums.FinancialSurveyStatus;
import com.sap.ibso.eservices.core.model.FinancialSurveyAffiliateModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyBranchModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyQuarterModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCompanyProfileModel;
import com.sap.ibso.eservices.core.model.SagiaSubsidiaryModel;
import com.sap.ibso.eservices.core.model.SagiaSurveyMessageModel;
import com.sap.ibso.eservices.core.model.SagiaSurveyTransactionModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyDAO;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyQuarterDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCityDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCompanyProfileDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaIsicMasterDataDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLegalStatusDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.finance.survey.Affiliate;
import com.sap.ibso.eservices.facades.data.finance.survey.Shareholder;
import com.sap.ibso.eservices.facades.data.license.amendment.Address;
import com.sap.ibso.eservices.facades.data.license.amendment.Branch;
import com.sap.ibso.eservices.facades.data.license.amendment.BusinessActivity;
import com.sap.ibso.eservices.facades.data.license.amendment.Subsidiary;
import com.sap.ibso.eservices.facades.data.license.amendment.Transaction;
import com.sap.ibso.eservices.sagiaservices.services.financialsurvey.SagiaFinancialSurveyService;
import de.hybris.platform.commercefacades.user.data.CompanyProfileData;
import de.hybris.platform.commercefacades.user.data.FinancialSurvey;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.security.PrincipalModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class SagiaFinancialSurveyServiceImpl implements SagiaFinancialSurveyService {


    @Resource
    private ModelService modelService;
    @Resource
    private UserService userService;
    @Resource
    private SagiaCompanyProfileDAO sagiaCompanyProfileDAO;
    @Resource
    private FinancialSurveyDAO financialSurveyDao;
    @Resource
    private FinancialSurveyQuarterDAO financialSurveyQuarterDAO;
    @Resource
    private SagiaFormatProvider sagiaFormatProvider;
    @Resource
    private BusinessProcessService businessProcessService;
    @Resource
    private BaseStoreService baseStoreService;
    @Resource
    private BaseSiteService baseSiteService;
    @Resource
    private CommonI18NService commonI18NService;
    @Resource
    private SagiaLegalStatusDAO  sagiaLegalStatusDAO;
    @Resource
    SagiaIsicMasterDataDAO sagiaIsicMasterDataDAO;

    @Resource
    private SagiaCountryDAO sagiaCountryDAO;

    @Resource
    private SagiaRegionDAO sagiaRegionDAO;

    @Resource
    private SagiaCityDAO sagiaCityDAO;








    @Override
    public void saveMessage(String messageContent, String quarterCode) {
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(quarterCode);
        SagiaSurveyMessageModel  sagiaSurveyMessageModel  = new SagiaSurveyMessageModel();
        sagiaSurveyMessageModel.setContent(messageContent);
        sagiaSurveyMessageModel.setFinancialSurvey(financialSurveyModel);
        final UserModel currentUser = userService.getCurrentUser();
        sagiaSurveyMessageModel.setCommentBy(currentUser);
        modelService.save(sagiaSurveyMessageModel);
    }

    @Override
    public void saveFinancialSurvey(FinancialSurvey financialSurveyData) {


        //save company Profile master data
        SagiaCompanyProfileModel companyProfile = saveCompanyProfile(financialSurveyData.getCompanyProfile());

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurveyData.getQuarterCode());
        financialSurveyModel.setCompanyProfile(companyProfile);
        saveFinancialSurveyModel(financialSurveyData, financialSurveyModel);


        if(financialSurveyModel == null ){
            financialSurveyModel = initiateFinancialSurveyModel(financialSurveyData);
        }

        savetBusinessActivities(financialSurveyData, financialSurveyModel);
        saveBranches(financialSurveyData, financialSurveyModel);
        saveShareholders(financialSurveyData, financialSurveyModel);
        saveAffiliates(financialSurveyData, financialSurveyModel);
        saveSubsidiaries(financialSurveyData, financialSurveyModel);
    }

    private void saveFinancialSurveyModel(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel) {
        financialSurveyModel.setCompanyStatus( FinancialSurveyCompanyStatus.valueOf(financialSurveyData.getCompanyStatus()));
        if(!"".equals(financialSurveyData.getSuspensionDate()) && !FinancialSurveyCompanyStatus.ACTIVE.getCode().equals(financialSurveyData.getCompanyStatus())){
            financialSurveyModel.setSuspensionDate(sagiaFormatProvider.formatUIStrToBackDate(financialSurveyData.getSuspensionDate()));
        }else{
            financialSurveyModel.setSuspensionDate(null);
        }
        //financialSurveyModel.setPaidUpCapitalCurrentQuarter(financialSurveyData.getPaidUpCapitalCurrentQuarter());
        financialSurveyModel.setIsConsolidated(financialSurveyData.getIsConsolidated());
        financialSurveyModel.setCompanyName(financialSurveyData.getCompanyProfile().getCompanyName());
        financialSurveyModel.setCommercialRegistrationNo(financialSurveyData.getCompanyProfile().getCommercialRegistrationNo());
        financialSurveyModel.setDisclosureCurrency(financialSurveyData.getDisclosureCurrency());
        financialSurveyModel.setPaidUpCapitalCurrentQuarter(financialSurveyData.getShareholderEquity().getPaidUpCapitalCurrentQuarter());
        if(financialSurveyData.getIsScaleLevelActualUnit()){
            financialSurveyModel.setScaleLevel(FinancialSurveyScaleLevel.ACTUAL_UNIT);
        }else {
            financialSurveyModel.setScaleLevel(FinancialSurveyScaleLevel.THOUSANDS);
        }

        modelService.save(financialSurveyModel);
    }

    private void saveShareholderEquity(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel){
        financialSurveyModel.setPaidUpCapitalCurrentQuarter(financialSurveyData.getShareholderEquity().getPaidUpCapitalCurrentQuarter());
        financialSurveyModel.setAdditionalPaidUpCapitalCurrentQuarter(financialSurveyData.getShareholderEquity().getAdditionalPaidUpCapitalCurrentQuarter());
        financialSurveyModel.setRetainedEarningsIncludeCurrentQuarter(financialSurveyData.getShareholderEquity().getRetainedEarningsIncludeCurrentQuarter());
        financialSurveyModel.setProfitLossQuarterCurrentQuarter(financialSurveyData.getShareholderEquity().getProfitLossQuarterCurrentQuarter());
        financialSurveyModel.setTotalReservesCurrentQuarter(financialSurveyData.getShareholderEquity().getTotalReservesCurrentQuarter());
        financialSurveyModel.setTreasurySharesCurrentQuarter(financialSurveyData.getShareholderEquity().getTreasurySharesCurrentQuarter());
        financialSurveyModel.setHeadOfficeAccountInBranchCurrentQuarter(financialSurveyData.getShareholderEquity().getHeadOfficeAccountInBranchCurrentQuarter());
        financialSurveyModel.setShareholderEquityOthersCurrentQuarter(financialSurveyData.getShareholderEquity().getShareholderEquityOthersCurrentQuarter());
        financialSurveyModel.setMinorityRightsCurrentQuarter(financialSurveyData.getShareholderEquity().getMinorityRightsCurrentQuarter());
        financialSurveyModel.setTotalShareholderEquityCurrentQuarter(financialSurveyData.getShareholderEquity().getTotalShareholderEquityCurrentQuarter());
        modelService.save(financialSurveyModel);
    }


    private void saveSubsidiaries(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel) {
        if(financialSurveyData.getSubsidiaries().size() > 0 ) {
            for (Subsidiary subsidiary : financialSurveyData.getSubsidiaries() ) {
                // Delete exiting branches
                if ("03".equals(subsidiary.getAction())) {
                    // Found and deleted the branch.
                    try {
                        modelService.remove(PK.parse(subsidiary.getSrId()));
                    }catch (Exception e) {

                    }
                } else { // Update or add a new branch

                    SagiaSubsidiaryModel sagiaSubsidiaryModel = new SagiaSubsidiaryModel();
                    if (subsidiary.getSrId() != null  ) {
                        sagiaSubsidiaryModel = modelService.get(PK.parse(subsidiary.getSrId()));
                    }
                    sagiaSubsidiaryModel.setCommercialRegistrationNo(subsidiary.getRegistrationName());
                    sagiaSubsidiaryModel.setUnifiedNo700(subsidiary.getUnifiedNo());
                    sagiaSubsidiaryModel.setContribution(subsidiary.getContribution());
                    sagiaSubsidiaryModel.setIsDataIncludedInHeadOffice(subsidiary.getDataIncludedInHeadOffice());
                    sagiaSubsidiaryModel.setName(subsidiary.getSubsidiaryName());
                    sagiaSubsidiaryModel.setFinancialSurvey(financialSurveyModel);
                    modelService.save(sagiaSubsidiaryModel);
                }
            }
        }
    }

    private void saveShareholders(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel) {
        if(financialSurveyData.getShareholders().size() > 0 ) {
            for (Shareholder shareholder : financialSurveyData.getShareholders()) {
                // Delete exiting branches
                if ("03".equals(shareholder.getAction())) {
                    // Found and deleted the branch.
                    try {
                        modelService.remove(PK.parse(shareholder.getSrId()));
                    }catch(Exception e){
                        // log errror.
                    }

                } else { // Update or add a new branch

                    FinancialSurveyShareholderModel financialSurveyShareholderModel = new FinancialSurveyShareholderModel();
                    SagiaSurveyTransactionModel  sagiaSurveyTransactionModel = new SagiaSurveyTransactionModel();
                    if (shareholder.getSrId() != null  ) {
                        financialSurveyShareholderModel = modelService.get(PK.parse(shareholder.getSrId()));
                        sagiaSurveyTransactionModel = financialSurveyShareholderModel.getTransaction();
                    }
                    populateShareholderModel(shareholder,financialSurveyShareholderModel);

                    FinancialSurveyShareholderModel financialSurveyShareholderPrevious = financialSurveyShareholderModel.getFinancialSurveyShareholderPreviousQuarter();
                    boolean valuesPrevQuarterChanged = populateTransactionModel(shareholder.getTransaction(),sagiaSurveyTransactionModel,financialSurveyShareholderPrevious!=null?financialSurveyShareholderPrevious.getTransaction():null);
                    if (valuesPrevQuarterChanged){
                        FinancialSurveyModel finanSurveyPrev = financialSurveyShareholderPrevious.getFinancialSurvey();
                        finanSurveyPrev.setSurveyStatus(FinancialSurveyStatus.UPDATED);
                        modelService.save(finanSurveyPrev);
                    }

                    financialSurveyShareholderModel.setTransaction(sagiaSurveyTransactionModel);
                    financialSurveyShareholderModel.setFinancialSurvey(financialSurveyModel);
                    modelService.save(sagiaSurveyTransactionModel);

                    modelService.save(financialSurveyShareholderModel);
                }
            }
        }
    }

    private void saveAffiliates(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel) {
        if(financialSurveyData.getAffiliates().size() > 0 ) {
            for (Affiliate affiliate : financialSurveyData.getAffiliates() ) {
                // Delete exiting branches
                if ("03".equals(affiliate.getAction())) {
                    // Found and deleted the branch.
                    try{
                        modelService.remove(PK.parse(affiliate.getSrId()));
                    }catch(Exception e) {
                        // TODO log error
                    }

                } else { // Update or add a new branch

                    FinancialSurveyAffiliateModel financialSurveyAffiliateModel = new FinancialSurveyAffiliateModel();
                    SagiaSurveyTransactionModel  sagiaSurveyTransactionModel = new SagiaSurveyTransactionModel();
                    if (affiliate.getSrId() != null  ) {
                        financialSurveyAffiliateModel = modelService.get(PK.parse(affiliate.getSrId()));
                        sagiaSurveyTransactionModel = financialSurveyAffiliateModel.getTransaction();
                    }
                    populateAffiliateModel(affiliate,financialSurveyAffiliateModel);


                    FinancialSurveyAffiliateModel financialSurveyAffiliateModelPrevious = financialSurveyAffiliateModel.getFinancialSurveyAffiliatePreviousQuarter();
                    boolean valuesPrevQuarterChanged = populateTransactionModel(affiliate.getTransaction(),sagiaSurveyTransactionModel,financialSurveyAffiliateModelPrevious!=null?financialSurveyAffiliateModelPrevious.getTransaction():null);
                    if (valuesPrevQuarterChanged){
                        FinancialSurveyModel finanSurveyPrev = financialSurveyAffiliateModelPrevious.getFinancialSurvey();
                        finanSurveyPrev.setSurveyStatus(FinancialSurveyStatus.UPDATED);
                        modelService.save(finanSurveyPrev);
                    }
                    financialSurveyAffiliateModel.setTransaction(sagiaSurveyTransactionModel);
                    financialSurveyAffiliateModel.setFinancialSurvey(financialSurveyModel);
                    modelService.save(sagiaSurveyTransactionModel);
                    modelService.save(financialSurveyAffiliateModel);
                }
            }
        }
    }


    private void saveBranches(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel) {
        if(financialSurveyData.getBranches().size() > 0 ) {
            for (Branch branch : financialSurveyData.getBranches() ) {
                // Delete exiting branches
                if ("03".equals(branch.getAction())) {
                    // Found and deleted the branch.
                    try {
                        modelService.remove(PK.parse(branch.getSrId()));
                    }catch (Exception e){

                    }
                } else { // Update or add a new branch

                    FinancialSurveyBranchModel financialSurveyBranchModel = new FinancialSurveyBranchModel();
                    if (branch.getSrId() != null  ) {
                        financialSurveyBranchModel = modelService.get(PK.parse(branch.getSrId()));
                    }
                    financialSurveyBranchModel.setWeight(branch.getVolumeWeight());
                    financialSurveyBranchModel.setName(branch.getName());
                    financialSurveyBranchModel.setIsHeadOffice(true);
                    financialSurveyBranchModel.setMain(true);
                    financialSurveyBranchModel.setType(branch.getType());
                    financialSurveyBranchModel.setTypeDescription(branch.getTypeDescription());
                    financialSurveyBranchModel.setFinancialSurvey(financialSurveyModel);

                    ///SagiaSurveyAddressModel sagiaSurveyAddressModel = new SagiaSurveyAddressModel();
                    populateAddressModel(financialSurveyBranchModel,branch.getAddress());
                  //  modelService.save(sagiaSurveyAddressModel);
                 //   financialSurveyBranchModel.setAddress(sagiaSurveyAddressModel);
                    modelService.save(financialSurveyBranchModel);
                }
            }
        }
    }

    private void savetBusinessActivities(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel) {
        if( financialSurveyData.getBusinessActivities().size() > 0 ){
            BusinessActivity businessActivity = financialSurveyData.getBusinessActivities().get(0) ;
            financialSurveyModel.setBusinessActivityId(businessActivity.getId());
            financialSurveyModel.setBusinessActivityDescription(businessActivity.getDescription());
        }

        financialSurveyModel.setEconomicActivityClass(sagiaIsicMasterDataDAO.getIsicTextsByCode(financialSurveyData.getEconomicActivityClass(),"CLASS" ));
        financialSurveyModel.setEconomicActivityDivision(sagiaIsicMasterDataDAO.getIsicTextsByCode(financialSurveyData.getEconomicActivityDivision(),"DIVISION" ));
        financialSurveyModel.setEconomicActivityGroup(sagiaIsicMasterDataDAO.getIsicTextsByCode(financialSurveyData.getEconomicActivityGroup(),"GROUP" ));
        financialSurveyModel.setEconomicActivitySection(sagiaIsicMasterDataDAO.getIsicTextsByCode(financialSurveyData.getEconomicActivitySection(),"SECTION" ));
        financialSurveyModel.setEconomicActivityBranch(sagiaIsicMasterDataDAO.getIsicTextsByCode(financialSurveyData.getEconomicActivityBranch(),"BRANCH" ));
        financialSurveyModel.setEconomicActivity(sagiaIsicMasterDataDAO.getIsicTextsByCode(financialSurveyData.getEconomicActivity(),"ACTIVITY" ));
    }

    private FinancialSurveyModel initiateFinancialSurveyModel(FinancialSurvey financialSurveyData) {
        FinancialSurveyModel financialSurveyModel;
        financialSurveyModel = new FinancialSurveyModel();
        FinancialSurveyQuarterModel financialSurveyQuarterModel = financialSurveyQuarterDAO.findFinancialSurveyQuarterByCode(financialSurveyData.getQuarterCode());
        financialSurveyModel.setQuarter(financialSurveyQuarterModel);
        final PrincipalModel currentUser = userService.getCurrentUser();
        financialSurveyModel.setUser((CustomerModel) currentUser);
        return financialSurveyModel;
    }

    private void populateAddressModel(FinancialSurveyBranchModel financialSurveyBranchModel, Address address) {
        financialSurveyBranchModel.setCity(sagiaCityDAO.getCityForCode(address.getCity()));
        financialSurveyBranchModel.setCountry(sagiaCountryDAO.getCountryForCode("SA"));
        financialSurveyBranchModel.setTelephone(address.getTelephone());
        financialSurveyBranchModel.setStreet(address.getStreet());
        financialSurveyBranchModel.setRegion(sagiaRegionDAO.getRegionForCode(address.getRegion()));
        financialSurveyBranchModel.setNumber(address.getNumber());
        financialSurveyBranchModel.setWebsite(address.getWebsite());
        financialSurveyBranchModel.setEmail(address.getEmail());
        financialSurveyBranchModel.setZipCode(address.getWebsite());
    }

    @Override
    public FinancialSurveyModel getFinancialSurvey(String quarterCode) {
        final PrincipalModel currentUser = userService.getCurrentUser();
        final FinancialSurveyQuarterModel financialSurveyQuarter = financialSurveyQuarterDAO.findFinancialSurveyQuarterByCode(quarterCode);
        return financialSurveyDao.getFinancialSurveyForQuarter(currentUser.getPk().getLong().toString(),financialSurveyQuarter.getPk().getLong().toString());
    }


    @Override
    public SagiaCompanyProfileModel getCompanyProfile() {
        final PrincipalModel currentUser = userService.getCurrentUser();
        return sagiaCompanyProfileDAO.getSagiaCompanyProfile(currentUser.getPk().getLong().toString());
    }

    @Override
    public void saveFinancialSurveyCompanyProfile(FinancialSurvey financialSurveyData) {

        //save company Profile master data
        SagiaCompanyProfileModel sagiaCompanyProfileModel = saveCompanyProfile(financialSurveyData.getCompanyProfile()) ;

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurveyData.getQuarterCode());
        financialSurveyModel.setSurveyStatus(FinancialSurveyStatus.IN_PROGRESS);
        financialSurveyModel.setCompanyProfile(sagiaCompanyProfileModel);
        financialSurveyModel.setIsCompanyProfileSectionFilled(true);
        savetBusinessActivities(financialSurveyData, financialSurveyModel);
        saveFinancialSurveyModel(financialSurveyData, financialSurveyModel);

        if(financialSurveyModel == null ){
            financialSurveyModel = initiateFinancialSurveyModel(financialSurveyData);
        }


    }

    @Override
    public void saveFinancialSurveyBranchesAndSubsidiaries(FinancialSurvey financialSurveyData) {

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurveyData.getQuarterCode());

        saveBranches(financialSurveyData, financialSurveyModel);
        saveSubsidiaries(financialSurveyData, financialSurveyModel);

        financialSurveyModel.setSurveyStatus(FinancialSurveyStatus.IN_PROGRESS);
        financialSurveyModel.setIsBranchSectionFilled(true);
        modelService.save(financialSurveyModel);

    }

    @Override
    public void saveFinancialSurveyShareholders(FinancialSurvey financialSurveyData) {

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurveyData.getQuarterCode());

        saveShareholders(financialSurveyData,financialSurveyModel);
        saveAffiliates(financialSurveyData,financialSurveyModel);

        financialSurveyModel.setSurveyStatus(FinancialSurveyStatus.IN_PROGRESS);
        financialSurveyModel.setIsShareholdersSectionFilled(true);
        modelService.save(financialSurveyModel);

    }


    @Override
    public void submitFinancialSurveyForReview(MediaModel mediaModel,String quarterCode
            ,Integer hoursToCompleteSurvey, Integer minutesToCompleteSurvey, String sourceOfKnowledge) {

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(quarterCode);
        financialSurveyModel.setAnnualFinancialStatementFile(mediaModel);
        financialSurveyModel.setHoursToCompleteSurvey(hoursToCompleteSurvey);
        financialSurveyModel.setMinutesToCompleteSurvey(minutesToCompleteSurvey);
        financialSurveyModel.setSourceOfKnowledge(sourceOfKnowledge);
        financialSurveyModel.setSurveyStatus(FinancialSurveyStatus.SUBMITTED);
        modelService.save(financialSurveyModel);
        /*final SagiaNewFinancialSurveyProcessModel sagiaNewFinancialSurveyProcess =
                businessProcessService.createProcess("sendNewFinancialSurveyEmailProcess-"
                        + financialSurveyModel.getUser().getUid()+"-"+System.currentTimeMillis(),"sendNewFinancialSurveyEmailProcess");
        sagiaNewFinancialSurveyProcess.setSite(baseSiteService.getCurrentBaseSite());
        sagiaNewFinancialSurveyProcess.setCustomer(financialSurveyModel.getUser());
        sagiaNewFinancialSurveyProcess.setLanguage(commonI18NService.getCurrentLanguage());
        sagiaNewFinancialSurveyProcess.setCurrency(commonI18NService.getCurrentCurrency());
        sagiaNewFinancialSurveyProcess.setStore(baseStoreService.getCurrentBaseStore());
        //sagiaNewFinancialSurveyProcess.setQuarterCode(quarterCode);
        //sagiaNewFinancialSurveyProcess.setQuarterDescription(quarterCode);
        modelService.save(sagiaNewFinancialSurveyProcess);
        businessProcessService.startProcess(sagiaNewFinancialSurveyProcess);*/
    }




    @Override
    public void saveFinancialSurveyShareholderEquity(FinancialSurvey financialSurvey) {
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurvey.getQuarterCode());

        financialSurveyModel.setSurveyStatus(FinancialSurveyStatus.IN_PROGRESS);
        financialSurveyModel.setIsEquitySectionFilled(true);
        saveShareholderEquity(financialSurvey,financialSurveyModel);

        String prevQuarterCode = financialSurveyModel.getQuarter().getPreviousQuarter()!=null ? financialSurveyModel.getQuarter().getPreviousQuarter().getCode() : null;

        if (prevQuarterCode != null){
            FinancialSurveyModel prevFinancialSurveyModel = getFinancialSurvey(prevQuarterCode);
            if (prevFinancialSurveyModel!= null && isPreviousFinancialShareholderEquityUpdated(prevFinancialSurveyModel,financialSurvey)){
                prevFinancialSurveyModel.setSurveyStatus(FinancialSurveyStatus.UPDATED);
                prevFinancialSurveyModel.setIsEquitySectionFilled(true);
                savePrevQuarterShareholderEquity(financialSurvey,prevFinancialSurveyModel);
            }
        }



    }

    private void savePrevQuarterShareholderEquity(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel) {

        financialSurveyModel.setPaidUpCapitalCurrentQuarter(financialSurveyData.getShareholderEquity().getPaidUpCapitalPreviousQuarter());
        financialSurveyModel.setAdditionalPaidUpCapitalCurrentQuarter(financialSurveyData.getShareholderEquity().getAdditionalPaidUpCapitalPreviousQuarter());
        financialSurveyModel.setRetainedEarningsIncludeCurrentQuarter(financialSurveyData.getShareholderEquity().getRetainedEarningsIncludePreviousQuarter());
        financialSurveyModel.setProfitLossQuarterCurrentQuarter(financialSurveyData.getShareholderEquity().getProfitLossQuarterPreviousQuarter());
        financialSurveyModel.setTotalReservesCurrentQuarter(financialSurveyData.getShareholderEquity().getTotalReservesPreviousQuarter());
        financialSurveyModel.setTreasurySharesCurrentQuarter(financialSurveyData.getShareholderEquity().getTreasurySharesPreviousQuarter());
        financialSurveyModel.setHeadOfficeAccountInBranchCurrentQuarter(financialSurveyData.getShareholderEquity().getHeadOfficeAccountInBranchPreviousQuarter());
        financialSurveyModel.setShareholderEquityOthersCurrentQuarter(financialSurveyData.getShareholderEquity().getShareholderEquityOthersPreviousQuarter());
        financialSurveyModel.setMinorityRightsCurrentQuarter(financialSurveyData.getShareholderEquity().getMinorityRightsPreviousQuarter());
        financialSurveyModel.setTotalShareholderEquityCurrentQuarter(financialSurveyData.getShareholderEquity().getTotalShareholderEquityPreviousQuarter());
        modelService.save(financialSurveyModel);

    }

    private boolean isPreviousFinancialShareholderEquityUpdated(FinancialSurveyModel prevFinancialSurveyModel, FinancialSurvey financialSurvey) {

        if ( financialSurvey.getShareholderEquity().getAdditionalPaidUpCapitalPreviousQuarter().equals(prevFinancialSurveyModel.getAdditionalPaidUpCapitalCurrentQuarter())
           && financialSurvey.getShareholderEquity().getRetainedEarningsIncludePreviousQuarter().equals(prevFinancialSurveyModel.getRetainedEarningsIncludeCurrentQuarter())
           && financialSurvey.getShareholderEquity().getProfitLossQuarterPreviousQuarter().equals(prevFinancialSurveyModel.getProfitLossQuarterCurrentQuarter())
           && financialSurvey.getShareholderEquity().getTotalReservesPreviousQuarter().equals(prevFinancialSurveyModel.getTotalReservesCurrentQuarter())
           && financialSurvey.getShareholderEquity().getTreasurySharesPreviousQuarter().equals(prevFinancialSurveyModel.getTreasurySharesCurrentQuarter())
           && financialSurvey.getShareholderEquity().getHeadOfficeAccountInBranchPreviousQuarter().equals(prevFinancialSurveyModel.getHeadOfficeAccountInBranchCurrentQuarter())
           && financialSurvey.getShareholderEquity().getShareholderEquityOthersPreviousQuarter().equals(prevFinancialSurveyModel.getShareholderEquityOthersCurrentQuarter())
           && financialSurvey.getShareholderEquity().getMinorityRightsPreviousQuarter().equals(prevFinancialSurveyModel.getMinorityRightsCurrentQuarter())
           && financialSurvey.getShareholderEquity().getTotalShareholderEquityPreviousQuarter().equals(prevFinancialSurveyModel.getTotalShareholderEquityCurrentQuarter())
           && financialSurvey.getShareholderEquity().getPaidUpCapitalPreviousQuarter().equals(prevFinancialSurveyModel.getPaidUpCapitalCurrentQuarter()
        )
        ){

            return  false;
        }else {
            return true;
        }
    }


    @Override
    public List<FinancialSurveyModel> getFinancialSurveys() {

        List<FinancialSurveyModel> financialSurveyModelList = new ArrayList<>();
        final PrincipalModel currentUser = userService.getCurrentUser();

        List<FinancialSurveyQuarterModel> financialSurveyQuarterList = financialSurveyQuarterDAO.findFinancialSurveyQuarters();
        for (FinancialSurveyQuarterModel quarter: financialSurveyQuarterList) {
            FinancialSurveyModel financialSurveyModel = getFinancialSurvey(quarter.getCode());
            if(financialSurveyModel == null ){
                financialSurveyModel = new FinancialSurveyModel();
                financialSurveyModel.setQuarter(quarter);
                financialSurveyModel.setUser((CustomerModel) currentUser);
                financialSurveyModel.setSurveyStatus(FinancialSurveyStatus.OPEN);

                SagiaCompanyProfileModel companyProfile = sagiaCompanyProfileDAO.getSagiaCompanyProfile(currentUser.getPk().getLong().toString());
                if( companyProfile != null ){
                    if (financialSurveyModel.getCompanyName() == null || "".equals(financialSurveyModel.getCompanyName())) {
                        financialSurveyModel.setCompanyName(companyProfile.getCompanyName());
                    }
                    if (financialSurveyModel.getCommercialRegistrationNo() == null || "".equals(financialSurveyModel.getCommercialRegistrationNo())) {
                        financialSurveyModel.setCommercialRegistrationNo(companyProfile.getCommercialRegistrationNo());
                    }
                }
                modelService.save(financialSurveyModel);

                // Copy shareholders,affiliates and branches from the previous quarter.
                //Copy existing shareholders
               // copyShareholdersFromPreviousQurterSurvey(financialSurveyModel,quarter);
                //

            }
            financialSurveyModelList.add(financialSurveyModel);
        }

        return financialSurveyModelList;
    }

    @Override
    public void copyShareholdersFromPreviousQurterSurvey(FinancialSurveyModel financialSurveyModel, FinancialSurveyQuarterModel quarter) {

        if (quarter.getPreviousQuarter()==null) {
            return;
        }
        String prevQuarterCode = quarter.getPreviousQuarter()!=null ? quarter.getPreviousQuarter().getCode() : null;
        FinancialSurveyModel prevFinancialSurveyModel = getFinancialSurvey(prevQuarterCode);

        if (prevFinancialSurveyModel == null ){
            return;
        }

        for(FinancialSurveyShareholderModel shareholderModelFromPrevQuarter: prevFinancialSurveyModel.getShareholders()){
            FinancialSurveyShareholderModel financialSurveyShareholderModel = new FinancialSurveyShareholderModel();
            financialSurveyShareholderModel.setFinancialSurvey(financialSurveyModel);
            financialSurveyShareholderModel.setShareholderNameEnglish(shareholderModelFromPrevQuarter.getShareholderNameEnglish());
            financialSurveyShareholderModel.setNationalityOfUCPRef(shareholderModelFromPrevQuarter.getNationalityOfUCPRef());
            financialSurveyShareholderModel.setShareholderNationalityCurrentRef(shareholderModelFromPrevQuarter.getShareholderNationalityCurrentRef());
            financialSurveyShareholderModel.setCompanyCountry(shareholderModelFromPrevQuarter.getCompanyCountry());
            financialSurveyShareholderModel.setCompanyCountryRef(shareholderModelFromPrevQuarter.getCompanyCountryRef());
            financialSurveyShareholderModel.setShareholderNationalityCurrentRef(shareholderModelFromPrevQuarter.getShareholderNationalityCurrentRef());
            financialSurveyShareholderModel.setIndustry(shareholderModelFromPrevQuarter.getIndustry());
            financialSurveyShareholderModel.setShareholderType(shareholderModelFromPrevQuarter.getShareholderType());
            financialSurveyShareholderModel.setShareholderTypeRef(shareholderModelFromPrevQuarter.getShareholderTypeRef());
            financialSurveyShareholderModel.setShareholderGender(shareholderModelFromPrevQuarter.getShareholderGender());
            financialSurveyShareholderModel.setFinancialSurveyShareholderPreviousQuarter(shareholderModelFromPrevQuarter);
            financialSurveyShareholderModel.setShareholderPercentage(shareholderModelFromPrevQuarter.getShareholderPercentage());
            financialSurveyShareholderModel.setShareholderCapital(shareholderModelFromPrevQuarter.getShareholderCapital());
            financialSurveyShareholderModel.setTreasurySharesCurrentQuarter(shareholderModelFromPrevQuarter.getTreasurySharesCurrentQuarter());
            financialSurveyShareholderModel.setAdditionalPaidUpCapitalCurrentQuarter(shareholderModelFromPrevQuarter.getAdditionalPaidUpCapitalCurrentQuarter());
            financialSurveyShareholderModel.setShareholderIsVotingPower(shareholderModelFromPrevQuarter.isShareholderIsVotingPower());
            financialSurveyShareholderModel.setMinorityRightsCurrentQuarter(shareholderModelFromPrevQuarter.getMinorityRightsCurrentQuarter());
            financialSurveyShareholderModel.setShareholderHasPreferredShares(shareholderModelFromPrevQuarter.isShareholderHasPreferredShares());
            financialSurveyShareholderModel.setHeadOfficeAccountInBranchCurrentQuarter(shareholderModelFromPrevQuarter.getHeadOfficeAccountInBranchCurrentQuarter());
            financialSurveyShareholderModel.setValueOfReverseInvestment(shareholderModelFromPrevQuarter.getValueOfReverseInvestment());
            financialSurveyShareholderModel.setShareholderVotingPower(shareholderModelFromPrevQuarter.getShareholderVotingPower());
            financialSurveyShareholderModel.setShareholderCountryRef(shareholderModelFromPrevQuarter.getShareholderCountryRef());
            financialSurveyShareholderModel.setIndustry(shareholderModelFromPrevQuarter.getIndustry());
            financialSurveyShareholderModel.setShareholderSector(shareholderModelFromPrevQuarter.getShareholderSector());
            financialSurveyShareholderModel.setRetainedEarningsIncludeCurrentQuarter(shareholderModelFromPrevQuarter.getRetainedEarningsIncludeCurrentQuarter());
            financialSurveyShareholderModel.setAdditionalPaidUpCapitalCurrentQuarter(shareholderModelFromPrevQuarter.getAdditionalPaidUpCapitalCurrentQuarter());
            financialSurveyShareholderModel.setProfitLossQuarterCurrentQuarter(shareholderModelFromPrevQuarter.getProfitLossQuarterCurrentQuarter());
            financialSurveyShareholderModel.setTotalReservesCurrentQuarter(shareholderModelFromPrevQuarter.getTotalReservesCurrentQuarter());
            financialSurveyShareholderModel.setTreasurySharesCurrentQuarter(shareholderModelFromPrevQuarter.getTreasurySharesCurrentQuarter());
            financialSurveyShareholderModel.setHeadOfficeAccountInBranchCurrentQuarter(shareholderModelFromPrevQuarter.getHeadOfficeAccountInBranchCurrentQuarter());
            financialSurveyShareholderModel.setShareholderEquityOthersCurrentQuarter(shareholderModelFromPrevQuarter.getShareholderEquityOthersCurrentQuarter());
            financialSurveyShareholderModel.setMinorityRightsCurrentQuarter(shareholderModelFromPrevQuarter.getMinorityRightsCurrentQuarter());
            financialSurveyShareholderModel.setTotalShareholderEquityCurrentQuarter(shareholderModelFromPrevQuarter.getTotalShareholderEquityCurrentQuarter());
            financialSurveyShareholderModel.setShareholderCapital(shareholderModelFromPrevQuarter.getShareholderCapital());
            financialSurveyShareholderModel.setPaidUpCapitalCurrentQuarter(shareholderModelFromPrevQuarter.getPaidUpCapitalCurrentQuarter());

            SagiaSurveyTransactionModel transaction = new SagiaSurveyTransactionModel();
            //Fill with empty transaction.
            financialSurveyShareholderModel.setTransaction(transaction);
            modelService.save(financialSurveyShareholderModel);

            financialSurveyModel.setIsShareholdersSectionFilled(true);
            modelService.save(financialSurveyModel);
        }

    }

    @Override
    public void copyAffiliatesFromPreviousQurterSurvey(FinancialSurveyModel financialSurveyModel, FinancialSurveyQuarterModel quarter) {

        if (quarter.getPreviousQuarter()==null) {
            return;
        }
        String prevQuarterCode = quarter.getPreviousQuarter()!=null ? quarter.getPreviousQuarter().getCode() : null;
        FinancialSurveyModel prevFinancialSurveyModel = getFinancialSurvey(prevQuarterCode);

        if (prevFinancialSurveyModel == null ){
            return;
        }

        for(FinancialSurveyAffiliateModel affiliateModelFromPrevQuarter: prevFinancialSurveyModel.getAffiliates()){
            FinancialSurveyAffiliateModel financialSurveyAffiliateModel = new FinancialSurveyAffiliateModel();
            financialSurveyAffiliateModel.setFinancialSurvey(financialSurveyModel);
            financialSurveyAffiliateModel.setFinancialSurveyAffiliatePreviousQuarter(affiliateModelFromPrevQuarter);
            financialSurveyAffiliateModel.setCompanyCountryRef(affiliateModelFromPrevQuarter.getCompanyCountryRef());
            financialSurveyAffiliateModel.setCompanyCountry(affiliateModelFromPrevQuarter.getCompanyCountry());
            financialSurveyAffiliateModel.setCompanyCountryRef(affiliateModelFromPrevQuarter.getCompanyCountryRef());
            financialSurveyAffiliateModel.setIndustry(affiliateModelFromPrevQuarter.getIndustry());
            financialSurveyAffiliateModel.setIndustry(affiliateModelFromPrevQuarter.getIndustry());
            financialSurveyAffiliateModel.setAffiliateCountryRef(affiliateModelFromPrevQuarter.getAffiliateCountryRef());
            financialSurveyAffiliateModel.setAffiliateCountry(affiliateModelFromPrevQuarter.getAffiliateCountry());
            financialSurveyAffiliateModel.setAffiliateNationalityCurrent(affiliateModelFromPrevQuarter.getAffiliateNationalityCurrent());
            financialSurveyAffiliateModel.setCompanyCountryRef(affiliateModelFromPrevQuarter.getCompanyCountryRef());
            financialSurveyAffiliateModel.setAffiliateNameEnglish(affiliateModelFromPrevQuarter.getAffiliateNameEnglish());
            financialSurveyAffiliateModel.setAffiliateTypeRef(affiliateModelFromPrevQuarter.getAffiliateTypeRef());
            financialSurveyAffiliateModel.setAffiliateType(affiliateModelFromPrevQuarter.getAffiliateType());
            financialSurveyAffiliateModel.setAffiliateSector(affiliateModelFromPrevQuarter.getAffiliateSector());
            financialSurveyAffiliateModel.setAffiliateSubsector(affiliateModelFromPrevQuarter.getAffiliateSubsector());
            financialSurveyAffiliateModel.setAffiliateNationalityCurrentRef(affiliateModelFromPrevQuarter.getAffiliateNationalityCurrentRef());
            financialSurveyAffiliateModel.setAffiliateGender(affiliateModelFromPrevQuarter.getAffiliateGender());
            financialSurveyAffiliateModel.setAffiliateMultinationalCompany(affiliateModelFromPrevQuarter.getAffiliateMultinationalCompany());


            SagiaSurveyTransactionModel transaction = new SagiaSurveyTransactionModel();
            //Fill with empty transaction.
            financialSurveyAffiliateModel.setTransaction(transaction);
            modelService.save(financialSurveyAffiliateModel);

            financialSurveyModel.setIsShareholdersSectionFilled(true);
            modelService.save(financialSurveyModel);
        }

    }



    private SagiaCompanyProfileModel saveCompanyProfile(CompanyProfileData companyProfileData){

        final PrincipalModel currentUser = userService.getCurrentUser();
        SagiaCompanyProfileModel companyProfile = sagiaCompanyProfileDAO.getSagiaCompanyProfile(currentUser.getPk().getLong().toString());
        if( companyProfile == null ){
            companyProfile = new SagiaCompanyProfileModel();
            companyProfile.setUser((CustomerModel) currentUser);
        }
        companyProfile.setCompanyName(companyProfileData.getCompanyName());
        companyProfile.setCommercialRegistrationNo(companyProfileData.getCommercialRegistrationNo());
        companyProfile.setUnifiedNo700(companyProfileData.getUnifiedNo700());

        companyProfile.setUser((CustomerModel) currentUser);
        companyProfile.setFinancialManagerEmail(companyProfileData.getFinanceManagerEmail());
        companyProfile.setFinancialManagerName(companyProfileData.getFinanceManagerName());
        companyProfile.setFinancialManagerTelephone(companyProfileData.getFinanceManagerTelephone());
        companyProfile.setLegalStatus(sagiaLegalStatusDAO.getLegalStatusForCode(companyProfileData.getLegalStatus()));
        companyProfile.setCrIssueDate(sagiaFormatProvider.formatUIStrToBackDate(companyProfileData.getCrIssueDate()));
        companyProfile.setIncorporationDate(sagiaFormatProvider.formatUIStrToBackDate(companyProfileData.getIncorporationDate()));
        modelService.save(companyProfile);
        return companyProfile;
    }

    private void populateShareholderModel(Shareholder shareholder, FinancialSurveyShareholderModel financialSurveyShareholderModel) throws ConversionException {
        financialSurveyShareholderModel.setShareholderType(shareholder.getShareholderType());
        financialSurveyShareholderModel.setShareholderTypeRef("1".equals(shareholder.getShareholderType())? FinancialSurveyShareholderType.INDIVIDUAL:FinancialSurveyShareholderType.ENTITY);

        financialSurveyShareholderModel.setCompanyCountry(shareholder.getCompanyCountry());
        financialSurveyShareholderModel.setCompanyCountryRef(sagiaCountryDAO.getCountryForCode(shareholder.getCompanyCountry()));
        financialSurveyShareholderModel.setShareholderNameEnglish(shareholder.getShareholderNameEnglish());
        financialSurveyShareholderModel.setShareholderSector(shareholder.getShareholderSector());
        financialSurveyShareholderModel.setShareholderSubsector(shareholder.getShareholderSubsector());
        //financialSurveyShareholderModel.setNationalityOfUCP(shareholder.getNationalityOfUCP());
        financialSurveyShareholderModel.setNationalityOfUCPRef(shareholder.getNationalityOfUCP()!=null?sagiaCountryDAO.getCountryForCode(shareholder.getNationalityOfUCP()):null);
        financialSurveyShareholderModel.setShareholderGender(shareholder.getShareholderGender());
        financialSurveyShareholderModel.setShareholderNationalityCurrent(shareholder.getShareholderNationalityCurrent());
        financialSurveyShareholderModel.setShareholderNationalityCurrentRef(sagiaCountryDAO.getCountryForCode(shareholder.getShareholderNationalityCurrent()));
        // this field is deprecated


        if ( "2".equals(shareholder.getShareholderType())) { // Entity

            financialSurveyShareholderModel.setShareholderCountry(shareholder.getCompanyCountry());
            financialSurveyShareholderModel.setShareholderCountryRef(sagiaCountryDAO.getCountryForCode(shareholder.getCompanyCountry()));

        }else {  //Individual

            financialSurveyShareholderModel.setShareholderCountry(shareholder.getShareholderCountry());
            financialSurveyShareholderModel.setShareholderCountryRef(sagiaCountryDAO.getCountryForCode(shareholder.getShareholderCountry()));
        }

        financialSurveyShareholderModel.setShareholderPercentage(shareholder.getShareholderPercentage());
        financialSurveyShareholderModel.setShareholderCapital(shareholder.getShareholderCapital());


        financialSurveyShareholderModel.setPaidUpCapitalCurrentQuarter(shareholder.getPaidUpCapitalCurrentQuarter());
        financialSurveyShareholderModel.setAdditionalPaidUpCapitalCurrentQuarter(shareholder.getAdditionalPaidUpCapitalCurrentQuarter());
        financialSurveyShareholderModel.setRetainedEarningsIncludeCurrentQuarter(shareholder.getRetainedEarningsIncludeCurrentQuarter());
        financialSurveyShareholderModel.setProfitLossQuarterCurrentQuarter(shareholder.getProfitLossQuarterCurrentQuarter());
        financialSurveyShareholderModel.setTotalReservesCurrentQuarter(shareholder.getTotalReservesCurrentQuarter());
        financialSurveyShareholderModel.setTreasurySharesCurrentQuarter(shareholder.getTreasurySharesCurrentQuarter());
        financialSurveyShareholderModel.setHeadOfficeAccountInBranchCurrentQuarter(shareholder.getHeadOfficeAccountInBranchCurrentQuarter());
        financialSurveyShareholderModel.setShareholderEquityOthersCurrentQuarter(shareholder.getShareholderEquityOthersCurrentQuarter());
        financialSurveyShareholderModel.setMinorityRightsCurrentQuarter(shareholder.getMinorityRightsCurrentQuarter());
        financialSurveyShareholderModel.setTotalShareholderEquityCurrentQuarter(shareholder.getTotalShareholderEquityCurrentQuarter());

        // Update Previous Quarter Values
        FinancialSurveyShareholderModel financialSurveyShareholderPrevious = financialSurveyShareholderModel.getFinancialSurveyShareholderPreviousQuarter();
        if(financialSurveyShareholderPrevious != null) {

            boolean isValuesChanged  = false;

            if(!shareholder.getAdditionalPaidUpCapitalPreviousQuarter().equals(financialSurveyShareholderPrevious.getAdditionalPaidUpCapitalCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setAdditionalPaidUpCapitalCurrentQuarter(shareholder.getAdditionalPaidUpCapitalPreviousQuarter());
            }

            if(!shareholder.getRetainedEarningsIncludePreviousQuarter().equals(financialSurveyShareholderPrevious.getRetainedEarningsIncludeCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setRetainedEarningsIncludeCurrentQuarter(shareholder.getRetainedEarningsIncludePreviousQuarter());
            }

            if(!shareholder.getProfitLossQuarterPreviousQuarter().equals(financialSurveyShareholderPrevious.getProfitLossQuarterCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setProfitLossQuarterCurrentQuarter(shareholder.getProfitLossQuarterPreviousQuarter());
            }

            if(!shareholder.getTotalReservesPreviousQuarter().equals(financialSurveyShareholderPrevious.getTotalReservesCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setTotalReservesCurrentQuarter(shareholder.getTotalReservesPreviousQuarter());
            }

            if(!shareholder.getTreasurySharesPreviousQuarter().equals(financialSurveyShareholderPrevious.getTreasurySharesCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setTreasurySharesCurrentQuarter(shareholder.getTreasurySharesPreviousQuarter());
            }

            if(!shareholder.getHeadOfficeAccountInBranchPreviousQuarter().equals(financialSurveyShareholderPrevious.getHeadOfficeAccountInBranchCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setHeadOfficeAccountInBranchCurrentQuarter(shareholder.getHeadOfficeAccountInBranchPreviousQuarter());
            }

            if(!shareholder.getShareholderEquityOthersPreviousQuarter().equals(financialSurveyShareholderPrevious.getShareholderEquityOthersCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setShareholderEquityOthersCurrentQuarter(shareholder.getShareholderEquityOthersPreviousQuarter());
            }

            if(!shareholder.getMinorityRightsPreviousQuarter().equals(financialSurveyShareholderPrevious.getMinorityRightsCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setMinorityRightsCurrentQuarter(shareholder.getMinorityRightsPreviousQuarter());
            }

            if(!shareholder.getTotalShareholderEquityPreviousQuarter().equals(financialSurveyShareholderPrevious.getTotalShareholderEquityCurrentQuarter())){
                isValuesChanged = true;
                financialSurveyShareholderPrevious.setTotalShareholderEquityCurrentQuarter(shareholder.getTotalShareholderEquityPreviousQuarter());
            }

            if (isValuesChanged){
                modelService.save(financialSurveyShareholderPrevious);
                FinancialSurveyModel prevQuarterSurvey = financialSurveyShareholderPrevious.getFinancialSurvey();
                prevQuarterSurvey.setSurveyStatus(FinancialSurveyStatus.UPDATED);
                modelService.save(prevQuarterSurvey);
            }


            //check transaction

        }






        if(shareholder.getShareholderIsVotingPower() != null ){
            financialSurveyShareholderModel.setShareholderIsVotingPower(shareholder.getShareholderIsVotingPower());
        }
        financialSurveyShareholderModel.setShareholderVotingPower(shareholder.getShareholderVotingPower());
        if(shareholder.getShareholderHasPreferredShares() != null ){
            financialSurveyShareholderModel.setShareholderHasPreferredShares(shareholder.getShareholderHasPreferredShares());
        }
        if(shareholder.getShareholderHasPreferredShares() != null ){
            financialSurveyShareholderModel.setShareholderHaveReverseInvestment(shareholder.getShareholderHaveReverseInvestment());
        }
        financialSurveyShareholderModel.setValueOfReverseInvestment(shareholder.getValueOfReverseInvestment());
        financialSurveyShareholderModel.setShareholderMultinationalCompany(shareholder.getShareholderMultinationalCompany());
    }

    private void populateAffiliateModel(Affiliate affiliate, FinancialSurveyAffiliateModel financialSurveyAffiliateModel) throws ConversionException {
        financialSurveyAffiliateModel.setAffiliateType(affiliate.getAffiliateType());
        financialSurveyAffiliateModel.setAffiliateTypeRef("1".equals(affiliate.getAffiliateType())?FinancialSurveyAffiliateType.INDIVIDUAL:FinancialSurveyAffiliateType.ENTITY);
        financialSurveyAffiliateModel.setCompanyCountry(affiliate.getCompanyCountry());
        financialSurveyAffiliateModel.setCompanyCountryRef(sagiaCountryDAO.getCountryForCode(affiliate.getCompanyCountry()));
        financialSurveyAffiliateModel.setAffiliateNameEnglish(affiliate.getAffiliateNameEnglish());
        financialSurveyAffiliateModel.setAffiliateSector(affiliate.getAffiliateSector());
        financialSurveyAffiliateModel.setAffiliateSubsector(affiliate.getAffiliateSubsector());
        financialSurveyAffiliateModel.setAffiliateGender(affiliate.getAffiliateGender());
        financialSurveyAffiliateModel.setAffiliateNationalityCurrent(affiliate.getAffiliateNationalityCurrent());
        financialSurveyAffiliateModel.setAffiliateNationalityCurrentRef(sagiaCountryDAO.getCountryForCode(affiliate.getAffiliateNationalityCurrent()));

        if ( "2".equals(affiliate.getAffiliateType())) { // Entity

            financialSurveyAffiliateModel.setAffiliateCountry(affiliate.getCompanyCountry());
            financialSurveyAffiliateModel.setAffiliateCountryRef(sagiaCountryDAO.getCountryForCode(affiliate.getCompanyCountry()));

        }else {  //Individual
            financialSurveyAffiliateModel.setAffiliateCountry(affiliate.getAffiliateCountry());
            financialSurveyAffiliateModel.setAffiliateCountryRef(sagiaCountryDAO.getCountryForCode(affiliate.getAffiliateCountry()));
        }

        financialSurveyAffiliateModel.setAffiliateMultinationalCompany(affiliate.getAffiliateMultinationalCompany());


        // Update Previous Quarter Values
        FinancialSurveyAffiliateModel financialSurveyAffiliatePrevious = financialSurveyAffiliateModel.getFinancialSurveyAffiliatePreviousQuarter();
        if(financialSurveyAffiliatePrevious != null) {

            boolean isValuesChanged  = false;

            //check transaction

            if (isValuesChanged){
                modelService.save(financialSurveyAffiliatePrevious);
                FinancialSurveyModel prevQuarterSurvey = financialSurveyAffiliatePrevious.getFinancialSurvey();
                prevQuarterSurvey.setSurveyStatus(FinancialSurveyStatus.UPDATED);
                modelService.save(prevQuarterSurvey);
            }

        }

    }


    private boolean populateTransactionModel(Transaction transaction, SagiaSurveyTransactionModel sagiaSurveyTransactionModel, SagiaSurveyTransactionModel sagiaSurveyTransactionPrevQuarterModel) throws ConversionException {


        sagiaSurveyTransactionModel.setTradeDebitCurrentQuarter(transaction.getTradeDebitCurrentQuarter());
        sagiaSurveyTransactionModel.setTradeCreditCurrentQuarter(transaction.getTradeCreditCurrentQuarter());
        sagiaSurveyTransactionModel.setLoansAssetsCurrentQuarter(transaction.getLoansAssetsCurrentQuarter());
        sagiaSurveyTransactionModel.setLoansLiabilitiesCurrentQuarter(transaction.getLoansLiabilitiesCurrentQuarter());
        sagiaSurveyTransactionModel.setInterestReceivedCurrentQuarter(transaction.getInterestReceivedCurrentQuarter());
        sagiaSurveyTransactionModel.setInterestPayableCurrentQuarter(transaction.getInterestPayableCurrentQuarter());
        sagiaSurveyTransactionModel.setDividendsReceivedCurrentQuarter(transaction.getDividendsReceivedCurrentQuarter());
        sagiaSurveyTransactionModel.setDividendsPaidCurrentQuarter(transaction.getDividendsPaidCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesReceivedCurrentQuarter(transaction.getExpensesReceivedCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesPaidCurrentQuarter(transaction.getExpensesPaidCurrentQuarter());
        sagiaSurveyTransactionModel.setSellProductionSuppliesCurrentQuarter(transaction.getSellProductionSuppliesCurrentQuarter());
        sagiaSurveyTransactionModel.setPurchaseProductionSuppliesCurrentQuarter(transaction.getPurchaseProductionSuppliesCurrentQuarter());
        sagiaSurveyTransactionModel.setSellMachineryCurrentQuarter(transaction.getSellMachineryCurrentQuarter());
        sagiaSurveyTransactionModel.setPurchaseMachineryCurrentQuarter(transaction.getPurchaseMachineryCurrentQuarter());
        sagiaSurveyTransactionModel.setCurrentDebitAccountCurrentQuarter(transaction.getCurrentDebitAccountCurrentQuarter());
        sagiaSurveyTransactionModel.setCurrentCreditAccountCurrentQuarter(transaction.getCurrentCreditAccountCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesReceivableCurrentQuarter(transaction.getExpensesReceivableCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesPayableCurrentQuarter(transaction.getExpensesPayableCurrentQuarter());
        sagiaSurveyTransactionModel.setInsuranceCommissionReceivableCurrentQuarter(transaction.getInsuranceCommissionReceivableCurrentQuarter());
        sagiaSurveyTransactionModel.setInsuranceCommissionPayableCurrentQuarter(transaction.getInsuranceCommissionPayableCurrentQuarter());
        sagiaSurveyTransactionModel.setOtherDebitCurrentQuarter(transaction.getOtherDebitCurrentQuarter());
        sagiaSurveyTransactionModel.setOtherCreditCurrentQuarter(transaction.getOtherCreditCurrentQuarter());
        sagiaSurveyTransactionModel.setTotalDebitCurrentQuarter(transaction.getTotalDebitCurrentQuarter());
        sagiaSurveyTransactionModel.setTotalCreditCurrentQuarter(transaction.getTotalCreditCurrentQuarter());


        boolean valueChanged = false;
        if (sagiaSurveyTransactionPrevQuarterModel != null ) {
            if(!transaction.getTradeCreditPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getTradeCreditCurrentQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setTradeCreditCurrentQuarter(transaction.getTradeCreditPreviousQuarter());
                valueChanged = true;
            }
            if(!transaction.getLoansAssetsPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getLoansAssetsPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setLoansAssetsCurrentQuarter(transaction.getLoansAssetsPreviousQuarter());
                valueChanged = true;
            }

            if(!transaction.getInterestReceivedPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getInterestReceivedPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setInterestReceivedCurrentQuarter(transaction.getInterestReceivedPreviousQuarter());

                valueChanged = true;
            }

            if(!transaction.getLoansLiabilitiesPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getLoansLiabilitiesPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setLoansLiabilitiesCurrentQuarter(transaction.getLoansLiabilitiesPreviousQuarter());

                valueChanged = true;
            }


            if(!transaction.getInterestPayablePreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getInterestPayablePreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setInterestPayableCurrentQuarter(transaction.getInterestPayablePreviousQuarter());

                valueChanged = true;
            }


            if(!transaction.getDividendsReceivedPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getDividendsReceivedPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setDividendsReceivedCurrentQuarter(transaction.getDividendsReceivedPreviousQuarter());

                valueChanged = true;
            }

            if(!transaction.getExpensesReceivedPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getExpensesReceivedPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setExpensesReceivedCurrentQuarter(transaction.getExpensesReceivedPreviousQuarter());

                valueChanged = true;
            }


            if(!transaction.getSellProductionSuppliesPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getSellProductionSuppliesPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setSellProductionSuppliesCurrentQuarter(transaction.getSellProductionSuppliesPreviousQuarter());

                valueChanged = true;
            }


            if(!transaction.getDividendsPaidPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getDividendsPaidPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setDividendsPaidCurrentQuarter(transaction.getDividendsPaidPreviousQuarter());

                valueChanged = true;
            }


            if(!transaction.getExpensesPaidPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getExpensesPaidPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setExpensesPaidCurrentQuarter(transaction.getExpensesPaidPreviousQuarter());

                valueChanged = true;
            }


            if(!transaction.getSellMachineryPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getSellMachineryPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setSellMachineryCurrentQuarter(transaction.getSellMachineryPreviousQuarter());

                valueChanged = true;
            }

            if(!transaction.getPurchaseProductionSuppliesPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getPurchaseProductionSuppliesPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setPurchaseProductionSuppliesCurrentQuarter(transaction.getPurchaseProductionSuppliesPreviousQuarter());

                valueChanged = true;

            }


            if(!transaction.getPurchaseMachineryPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getPurchaseMachineryPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setPurchaseMachineryCurrentQuarter(transaction.getPurchaseMachineryPreviousQuarter());

                valueChanged = true;

            }


            if(!transaction.getCurrentDebitAccountPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getCurrentDebitAccountPreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setCurrentDebitAccountCurrentQuarter(transaction.getCurrentDebitAccountPreviousQuarter());

                valueChanged = true;
            }


            if(!transaction.getExpensesReceivablePreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getExpensesReceivablePreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setExpensesReceivableCurrentQuarter(transaction.getExpensesReceivablePreviousQuarter());

                valueChanged = true;

            }



            if(!transaction.getCurrentCreditAccountPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getCurrentCreditAccountPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setCurrentCreditAccountCurrentQuarter(transaction.getCurrentCreditAccountPreviousQuarter());

                valueChanged = true;

            }


            if(!transaction.getExpensesPayablePreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getExpensesPayablePreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setExpensesPayableCurrentQuarter(transaction.getExpensesPayablePreviousQuarter());
                valueChanged = true;
            }


            if(!transaction.getInsuranceCommissionReceivablePreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getInsuranceCommissionReceivablePreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setInsuranceCommissionReceivableCurrentQuarter(transaction.getInsuranceCommissionReceivablePreviousQuarter());
                valueChanged = true;

            }


            if(!transaction.getInsuranceCommissionPayablePreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getInsuranceCommissionPayablePreviousQuarter())){
                sagiaSurveyTransactionPrevQuarterModel.setInsuranceCommissionPayableCurrentQuarter(transaction.getInsuranceCommissionPayablePreviousQuarter());
                valueChanged = true;
            }


            if(!transaction.getOtherDebitPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getOtherDebitPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setOtherDebitCurrentQuarter(transaction.getOtherDebitPreviousQuarter());
                valueChanged = true;
            }



            if(!transaction.getOtherCreditPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getOtherCreditPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setOtherCreditCurrentQuarter(transaction.getOtherCreditPreviousQuarter());
                valueChanged = true;
            }

            if(!transaction.getTotalCreditPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getTotalCreditPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setTotalCreditCurrentQuarter(transaction.getTotalCreditPreviousQuarter());
                valueChanged = true;
            }



            if(!transaction.getTradeDebitPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getTradeDebitPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setTradeDebitCurrentQuarter(transaction.getTradeDebitPreviousQuarter());
                valueChanged = true;
            }


            if(!transaction.getTotalDebitPreviousQuarter().equals(sagiaSurveyTransactionPrevQuarterModel.getTotalDebitPreviousQuarter())){

                sagiaSurveyTransactionPrevQuarterModel.setTotalDebitCurrentQuarter(transaction.getTotalDebitPreviousQuarter());
                valueChanged = true;
            }

            if(valueChanged) {
                modelService.save(sagiaSurveyTransactionPrevQuarterModel);
            }

        }
        return valueChanged;

    }


    @Override
    public FinancialSurveyQuarterModel getFinancialSurveyQuarterByCode(String quarterCode) {
        return financialSurveyQuarterDAO.findFinancialSurveyQuarterByCode(quarterCode);
    }
}
