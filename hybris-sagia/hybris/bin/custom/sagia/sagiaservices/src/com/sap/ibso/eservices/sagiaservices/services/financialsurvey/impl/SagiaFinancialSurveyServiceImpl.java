package com.sap.ibso.eservices.sagiaservices.services.financialsurvey.impl;

import com.sap.ibso.eservices.core.enums.FinancialSurveyCompanyStatus;
import com.sap.ibso.eservices.core.enums.FinancialSurveyStatus;
import com.sap.ibso.eservices.core.model.FinancialSurveyAffiliateModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyBranchModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyQuarterModel;
import com.sap.ibso.eservices.core.model.FinancialSurveyShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCompanyProfileModel;
import com.sap.ibso.eservices.core.model.SagiaSubsidiaryModel;
import com.sap.ibso.eservices.core.model.SagiaSurveyAddressModel;
import com.sap.ibso.eservices.core.model.SagiaSurveyMessageModel;
import com.sap.ibso.eservices.core.model.SagiaSurveyTransactionModel;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyDAO;
import com.sap.ibso.eservices.core.sagia.dao.FinancialSurveyQuarterDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCompanyProfileDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLegalStatusDAO;
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
        saveCompanyProfile(financialSurveyData.getCompanyProfile()) ;

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurveyData.getQuarterCode());


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
        financialSurveyModel.setDisclosureCurrency(financialSurveyData.getDisclosureCurrency());
        financialSurveyModel.setPaidUpCapitalCurrentQuarter(financialSurveyData.getPaidUpCapitalCurrentQuarter());
        modelService.save(financialSurveyModel);
    }

    private void saveShareholderEquity(FinancialSurvey financialSurveyData, FinancialSurveyModel financialSurveyModel){
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
                    modelService.remove(PK.parse(subsidiary.getSrId()));
                } else { // Update or add a new branch

                    SagiaSubsidiaryModel sagiaSubsidiaryModel = new SagiaSubsidiaryModel();
                    if (subsidiary.getSrId() != null  ) {
                        sagiaSubsidiaryModel = modelService.get(PK.parse(subsidiary.getSrId()));
                    }
                    sagiaSubsidiaryModel.setCommercialRegistrationNo(subsidiary.getRegistrationName());
                    sagiaSubsidiaryModel.setUnifiedNo700(subsidiary.getUnifiedNo());
                    sagiaSubsidiaryModel.setContribution(subsidiary.getContribution());
                    sagiaSubsidiaryModel.setIsDataIncludedInHeadOffice(false);
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

                    populateTransactionModel(shareholder.getTransaction(),sagiaSurveyTransactionModel);
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
                    populateTransactionModel(affiliate.getTransaction(),sagiaSurveyTransactionModel);
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
                    modelService.remove(PK.parse(branch.getSrId()));
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

                    SagiaSurveyAddressModel sagiaSurveyAddressModel = new SagiaSurveyAddressModel();
                    populateAddressModel(sagiaSurveyAddressModel,branch.getAddress());
                    modelService.save(sagiaSurveyAddressModel);
                    financialSurveyBranchModel.setAddress(sagiaSurveyAddressModel);
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

    private void populateAddressModel(SagiaSurveyAddressModel sagiaSurveyAddressModel, Address address) {
        sagiaSurveyAddressModel.setCity(address.getCity());
        sagiaSurveyAddressModel.setCityDescription(address.getCityDescription());
        sagiaSurveyAddressModel.setCountry(address.getCountry());
        sagiaSurveyAddressModel.setTelephone(address.getTelephone());
        sagiaSurveyAddressModel.setStreet(address.getStreet());
        sagiaSurveyAddressModel.setRegion(address.getRegion());
        sagiaSurveyAddressModel.setRegionDescription(address.getRegionDescription());
        sagiaSurveyAddressModel.setNumber(address.getNumber());
        sagiaSurveyAddressModel.setWebsite(address.getWebsite());
        sagiaSurveyAddressModel.setEmail(address.getEmail());
        sagiaSurveyAddressModel.setZipCode(address.getWebsite());
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
        saveCompanyProfile(financialSurveyData.getCompanyProfile()) ;

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurveyData.getQuarterCode());
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

    }

    @Override
    public void saveFinancialSurveyShareholders(FinancialSurvey financialSurveyData) {

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(financialSurveyData.getQuarterCode());

        saveShareholders(financialSurveyData,financialSurveyModel);
        saveAffiliates(financialSurveyData,financialSurveyModel);

    }


    @Override
    public void submitFinancialSurveyForReview(MediaModel mediaModel,String quarterCode) {

        // fetch the FinancialSurvey for the given quarter
        FinancialSurveyModel financialSurveyModel = getFinancialSurvey(quarterCode);
        financialSurveyModel.setAnnualFinancialStatementFile(mediaModel);
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
        saveShareholderEquity(financialSurvey,financialSurveyModel);
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
                modelService.save(financialSurveyModel);
            }
            financialSurveyModelList.add(financialSurveyModel);
        }

        return financialSurveyModelList;
    }



    private void saveCompanyProfile(CompanyProfileData companyProfileData){

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
    }

    private void populateShareholderModel(Shareholder shareholder, FinancialSurveyShareholderModel financialSurveyShareholderModel) throws ConversionException {
        financialSurveyShareholderModel.setShareholderType(shareholder.getShareholderType());
        financialSurveyShareholderModel.setCompanyCountry(shareholder.getCompanyCountry());
        financialSurveyShareholderModel.setShareholderNameEnglish(shareholder.getShareholderNameEnglish());
        financialSurveyShareholderModel.setShareholderSector(shareholder.getShareholderSector());
        financialSurveyShareholderModel.setShareholderSubsector(shareholder.getShareholderSubsector());
        financialSurveyShareholderModel.setNationalityOfUCP(shareholder.getNationalityOfUCP());
        financialSurveyShareholderModel.setShareholderGender(shareholder.getShareholderGender());
        financialSurveyShareholderModel.setShareholderNationalityCurrent(shareholder.getShareholderNationalityCurrent());
        financialSurveyShareholderModel.setShareholderCountry(shareholder.getShareholderCountry());
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
        financialSurveyAffiliateModel.setCompanyCountry(affiliate.getCompanyCountry());
        financialSurveyAffiliateModel.setAffiliateNameEnglish(affiliate.getAffiliateNameEnglish());
        financialSurveyAffiliateModel.setAffiliateSector(affiliate.getAffiliateSector());
        financialSurveyAffiliateModel.setAffiliateSubsector(affiliate.getAffiliateSubsector());
        financialSurveyAffiliateModel.setAffiliateGender(affiliate.getAffiliateGender());
        financialSurveyAffiliateModel.setAffiliateNationalityCurrent(affiliate.getAffiliateNationalityCurrent());
        financialSurveyAffiliateModel.setAffiliateCountry(affiliate.getAffiliateCountry());
        financialSurveyAffiliateModel.setAffiliateMultinationalCompany(affiliate.getAffiliateMultinationalCompany());
    }


    private void populateTransactionModel(Transaction transaction, SagiaSurveyTransactionModel sagiaSurveyTransactionModel) throws ConversionException {

        sagiaSurveyTransactionModel.setTradeDebitCurrentQuarter(transaction.getTradeDebitCurrentQuarter());
        sagiaSurveyTransactionModel.setTradeDebitPreviousQuarter(transaction.getTradeDebitPreviousQuarter());
        sagiaSurveyTransactionModel.setTradeCreditCurrentQuarter(transaction.getTradeCreditCurrentQuarter());
        sagiaSurveyTransactionModel.setTradeCreditPreviousQuarter(transaction.getTradeCreditPreviousQuarter());

        sagiaSurveyTransactionModel.setLoansAssetsCurrentQuarter(transaction.getLoansAssetsCurrentQuarter());
        sagiaSurveyTransactionModel.setLoansAssetsPreviousQuarter(transaction.getLoansAssetsPreviousQuarter());
        sagiaSurveyTransactionModel.setLoansLiabilitiesCurrentQuarter(transaction.getLoansLiabilitiesCurrentQuarter());
        sagiaSurveyTransactionModel.setLoansLiabilitiesPreviousQuarter(transaction.getLoansLiabilitiesPreviousQuarter());

        sagiaSurveyTransactionModel.setInterestReceivedCurrentQuarter(transaction.getInterestReceivedCurrentQuarter());
        sagiaSurveyTransactionModel.setInterestReceivedPreviousQuarter(transaction.getInterestReceivedPreviousQuarter());
        sagiaSurveyTransactionModel.setInterestPayableCurrentQuarter(transaction.getInterestPayableCurrentQuarter());
        sagiaSurveyTransactionModel.setInterestPayablePreviousQuarter(transaction.getInterestPayablePreviousQuarter());

        sagiaSurveyTransactionModel.setDividendsReceivedCurrentQuarter(transaction.getDividendsReceivedCurrentQuarter());
        sagiaSurveyTransactionModel.setDividendsReceivedPreviousQuarter(transaction.getDividendsReceivedPreviousQuarter());
        sagiaSurveyTransactionModel.setDividendsPaidCurrentQuarter(transaction.getDividendsPaidCurrentQuarter());
        sagiaSurveyTransactionModel.setDividendsPaidPreviousQuarter(transaction.getDividendsPaidPreviousQuarter());

        sagiaSurveyTransactionModel.setExpensesReceivedCurrentQuarter(transaction.getExpensesReceivedCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesReceivedPreviousQuarter(transaction.getExpensesReceivedPreviousQuarter());
        sagiaSurveyTransactionModel.setExpensesPaidCurrentQuarter(transaction.getExpensesPaidCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesPaidPreviousQuarter(transaction.getExpensesPaidPreviousQuarter());

        sagiaSurveyTransactionModel.setSellProductionSuppliesCurrentQuarter(transaction.getSellProductionSuppliesCurrentQuarter());
        sagiaSurveyTransactionModel.setSellProductionSuppliesPreviousQuarter(transaction.getSellProductionSuppliesPreviousQuarter());
        sagiaSurveyTransactionModel.setPurchaseProductionSuppliesCurrentQuarter(transaction.getPurchaseProductionSuppliesCurrentQuarter());
        sagiaSurveyTransactionModel.setPurchaseProductionSuppliesPreviousQuarter(transaction.getPurchaseProductionSuppliesPreviousQuarter());

        sagiaSurveyTransactionModel.setSellMachineryCurrentQuarter(transaction.getSellMachineryCurrentQuarter());
        sagiaSurveyTransactionModel.setSellMachineryPreviousQuarter(transaction.getSellMachineryPreviousQuarter());
        sagiaSurveyTransactionModel.setPurchaseMachineryCurrentQuarter(transaction.getPurchaseMachineryCurrentQuarter());
        sagiaSurveyTransactionModel.setPurchaseMachineryPreviousQuarter(transaction.getPurchaseMachineryPreviousQuarter());

        sagiaSurveyTransactionModel.setCurrentDebitAccountCurrentQuarter(transaction.getCurrentDebitAccountCurrentQuarter());
        sagiaSurveyTransactionModel.setCurrentDebitAccountPreviousQuarter(transaction.getCurrentDebitAccountPreviousQuarter());
        sagiaSurveyTransactionModel.setCurrentCreditAccountCurrentQuarter(transaction.getCurrentCreditAccountCurrentQuarter());
        sagiaSurveyTransactionModel.setCurrentCreditAccountPreviousQuarter(transaction.getCurrentCreditAccountPreviousQuarter());

        sagiaSurveyTransactionModel.setExpensesReceivableCurrentQuarter(transaction.getExpensesReceivableCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesReceivablePreviousQuarter(transaction.getExpensesReceivablePreviousQuarter());
        sagiaSurveyTransactionModel.setExpensesPayableCurrentQuarter(transaction.getExpensesPayableCurrentQuarter());
        sagiaSurveyTransactionModel.setExpensesPayablePreviousQuarter(transaction.getExpensesPayablePreviousQuarter());

        sagiaSurveyTransactionModel.setInsuranceCommissionReceivableCurrentQuarter(transaction.getInsuranceCommissionReceivableCurrentQuarter());
        sagiaSurveyTransactionModel.setInsuranceCommissionReceivablePreviousQuarter(transaction.getInsuranceCommissionReceivablePreviousQuarter());
        sagiaSurveyTransactionModel.setInsuranceCommissionPayableCurrentQuarter(transaction.getInsuranceCommissionPayableCurrentQuarter());
        sagiaSurveyTransactionModel.setInsuranceCommissionPayablePreviousQuarter(transaction.getInsuranceCommissionPayablePreviousQuarter());


        sagiaSurveyTransactionModel.setOtherDebitCurrentQuarter(transaction.getOtherDebitCurrentQuarter());
        sagiaSurveyTransactionModel.setOtherDebitPreviousQuarter(transaction.getOtherDebitPreviousQuarter());
        sagiaSurveyTransactionModel.setOtherCreditCurrentQuarter(transaction.getOtherCreditCurrentQuarter());
        sagiaSurveyTransactionModel.setOtherCreditPreviousQuarter(transaction.getOtherCreditPreviousQuarter());


        sagiaSurveyTransactionModel.setTotalDebitCurrentQuarter(transaction.getTotalDebitCurrentQuarter());
        sagiaSurveyTransactionModel.setTotalDebitPreviousQuarter(transaction.getTotalDebitPreviousQuarter());
        sagiaSurveyTransactionModel.setTotalCreditCurrentQuarter(transaction.getTotalCreditCurrentQuarter());
        sagiaSurveyTransactionModel.setTotalCreditPreviousQuarter(transaction.getTotalCreditPreviousQuarter());


    }

}
