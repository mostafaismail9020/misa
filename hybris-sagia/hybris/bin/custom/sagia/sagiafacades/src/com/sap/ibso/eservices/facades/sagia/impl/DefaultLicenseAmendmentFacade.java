package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.license.amendment.*;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.AttachmentListItem;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItem;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.ListItems;
import com.sap.ibso.eservices.facades.data.license.amendment.listItem.SubListItem;
import com.sap.ibso.eservices.facades.data.license.simulation.SimulatedPriceData;
import com.sap.ibso.eservices.facades.populators.license.amendment.*;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseAmendmentFacade;
import com.sap.ibso.eservices.sagiaservices.converters.simulation.SimulatedPriceParamsConverter;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationData;
import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationItemData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CustomizingGetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.LicenseAmendmentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ProductData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.license.amendment.ShareholderData;
import com.sap.ibso.eservices.sagiaservices.price.AmendmentParam;
import com.sap.ibso.eservices.sagiaservices.price.PriceSimulationService;
import com.sap.ibso.eservices.sagiaservices.services.impl.*;
import de.hybris.platform.servicelayer.i18n.I18NService;
import com.sap.ibso.eservices.sagiaservices.data.moj.MOJInheritSetData;
import com.sap.ibso.eservices.facades.data.MOJInheritSet;

import org.springframework.context.MessageSource;
import org.apache.commons.collections.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * DefaultLicenseAmendmentFacade
 */
public class DefaultLicenseAmendmentFacade implements SagiaLicenseAmendmentFacade {

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
    private static final String ADD = "01";
    private static final String MODIFY = "02";
    private static final String DELETE = "03";
    private static final String AMEND_TYPE_VALUE = "X";
    private static final String INDUSTRIAL_LICENSE = "1";
    private static final String AGRICULTURAL_LICENSE = "5";
    private static final String ESTB = "ESTB";
    private static final String LLC = "LLC";
    private static final String ILLC = "ILLC";
    private static final String INDIVIDUAL_SHAREHOLDER_TYPE = "1";
    private static final String ACTION_01 = "01";
    private static final String ZSR5 = "ZSR5";
    private static final String ACTION_05 = "05";

    private LicenseAmendmentService licenseAmendmentService;
    private LicenseAmendmentShareholderService licenseAmendmentShareholderService;
    private AmendProductsService amendProductsService;
    private CustomizationListService customizationListService;
    private GlobalValsService globalValsService;
    private PriceSimulationService priceSimulationService;
    private MojInheritService mojInheritService;

    private MOJInheritPopulator mojInheritPopulator;
    private LicenseAmendmentPopulator licenseAmendmentPopulator;
    private LicenseAmendmentReversePopulator licenseAmendmentReversePopulator;
    private ShareholderPopulator shareholderPopulator;
    private ListItemPopulator listItemPopulator;
    private SubListItemPopulator subListItemPopulator;
    private AttachmentListItemPopulator attachmentListItemPopulator;
    private MessageSource messageSource;
    private I18NService i18nService;


    @Override
    public LicenseAmendment getLicenseAmendment(String srId) {
        LicenseAmendment licenseAmendment = new LicenseAmendment();
        LicenseAmendmentData licenseAmendmentData = licenseAmendmentService.getLicenseAmendment(srId);
        licenseAmendmentPopulator.populate(licenseAmendmentData, licenseAmendment);
        return licenseAmendment;
    }

    @Override
    public List<LicenseAmendment> getLicenseAmendmentsHeaders() {
        List<LicenseAmendment> licenseAmendmentsHeaders = new ArrayList<>();
        Collection<LicenseAmendmentData> licenseAmendmentsHeadersData = licenseAmendmentService.getCollection(LicenseAmendmentData.class);
        if (licenseAmendmentsHeadersData != null && !licenseAmendmentsHeadersData.isEmpty()) {
            for (LicenseAmendmentData licenseAmendmentHeaderData : licenseAmendmentsHeadersData) {
                LicenseAmendment licenseAmendmentHeader = new LicenseAmendment();
                licenseAmendmentPopulator.populate(licenseAmendmentHeaderData, licenseAmendmentHeader);
                licenseAmendmentsHeaders.add(licenseAmendmentHeader);
            }
        }
        return licenseAmendmentsHeaders;
    }

    @Override
    public Shareholder getShareholder(String shareholderId) {
        Shareholder shareholder = new Shareholder();
        ShareholderData shareholderData = licenseAmendmentShareholderService.getLicenseAmendmentShareholder(shareholderId);
        shareholderPopulator.populate(shareholderData, shareholder);
        shareholder.setShBpId(shareholderData.getBpID()); // temp fix; should be fixed in crm
        return shareholder;
    }
    
    @Override
    public MOJInheritSet getVerifyInherit(String deceasedId, String deedNumber) {
    	Collection<MOJInheritSetData> mojInheritData = mojInheritService.get(deceasedId, deedNumber);
    	MOJInheritSetData mojInheritDataItem = new MOJInheritSetData();
    	if(mojInheritData.stream().findFirst().isPresent()){
        	mojInheritDataItem = mojInheritData.stream().findFirst().get();
    	}
    	
//    	MOJInheritSetData mojInheritDataItem = new MOJInheritSetData();
//    	mojInheritDataItem.setDeceasedId("12345");
//    	mojInheritDataItem.setDeceasedName("test");
//    	mojInheritDataItem.setDeedNumber("67890");
//    	mojInheritDataItem.setIsMojVerified("X");
    	
    	
    	MOJInheritSet mojInherit = new MOJInheritSet();
    	mojInheritPopulator.populate(mojInheritDataItem, mojInherit);
    	
    	return mojInherit;
    }

    @Override
    public void saveLicenseAmendment(LicenseAmendment licenseAmendment) {
        licenseAmendment.setAction(ACTION_01);
        if (!licenseAmendment.getInstantAmendment()) {
            licenseAmendment.setTransactionType(ZSR5);
        }

        List<BusinessActivity> businessActivities = licenseAmendment.getBusinessActivities();
        if (businessActivities != null && !businessActivities.isEmpty()) {
            licenseAmendment.getEntity().setAction(MODIFY);
            StringBuilder sb = new StringBuilder();
            sb.append(businessActivities.get(0).getId()).append(" - ").append(businessActivities.get(0).getDescription());
            for (int i = 1; i < businessActivities.size(); i++) {
                sb.append(System.lineSeparator()).append(businessActivities.get(i).getId()).append(" - ").append(businessActivities.get(i).getDescription());
            }
            licenseAmendment.getEntity().setActivity(sb.toString());
        }

        if (licenseAmendment.getAmendmentTypesData().getEnEstToLlc() != null) {
            licenseAmendment.getEntity().setLegalStatus(LLC);
        }

        LicenseAmendmentData licenseAmendmentData = new LicenseAmendmentData();
        licenseAmendmentReversePopulator.populate(licenseAmendment, licenseAmendmentData);
        licenseAmendmentService.save(licenseAmendmentData);
    }

    @Override
    public boolean isInstantAmendment(LicenseAmendment licenseAmendment) {
        licenseAmendment.setAction(ACTION_05);

        LicenseAmendmentData licenseAmendmentData = new LicenseAmendmentData();
        licenseAmendmentReversePopulator.populate(licenseAmendment, licenseAmendmentData);
        return licenseAmendmentService.isInstantAmendment(licenseAmendmentData);
    }

    @Override
    public LicenseAmendment getLicenseAmendmentTypes(LicenseAmendment modifiedLicenseAmendment) {
        List<LicenseAmendmentTypeView> amendmentTypesView = new ArrayList<>();
        LicenseAmendmentTypeData amendmentTypesData = new LicenseAmendmentTypeData();

        // Products: if license type 1 or 5 at least one product
        // Max 5 branches in one amendment

        // Validate status change
        if (!validateStatusChange(modifiedLicenseAmendment)) {
            return modifiedLicenseAmendment;
        }

        Entity entity = modifiedLicenseAmendment.getEntity();
        entity.setAction("");

        modifyBrandName(amendmentTypesView, amendmentTypesData, entity);
        setCapitalReductionAndIncrease(amendmentTypesView, amendmentTypesData, entity);
        setIncreaseWorkforce(amendmentTypesView, amendmentTypesData, entity);
        setActivityChange(modifiedLicenseAmendment, amendmentTypesView, amendmentTypesData, entity);
        setShareholders(modifiedLicenseAmendment.getShareholders(), amendmentTypesView, amendmentTypesData);
        setConverters(modifiedLicenseAmendment, amendmentTypesView, amendmentTypesData);
        setBranches(modifiedLicenseAmendment, amendmentTypesView, amendmentTypesData);
        setProductAmendment(modifiedLicenseAmendment, amendmentTypesView, amendmentTypesData);

        modifiedLicenseAmendment.setAmendmentTypesData(amendmentTypesData);
        modifiedLicenseAmendment.setAmendmentTypesView(amendmentTypesView);

        modifiedLicenseAmendment.setInstantAmendment(isInstantAmendment(modifiedLicenseAmendment));
        modifiedLicenseAmendment.setSimulatedPrices(computeSimulatedPrices(modifiedLicenseAmendment));

        return modifiedLicenseAmendment;
    }

    private boolean validateStatusChange(LicenseAmendment modifiedLicenseAmendment) {
        String legalStatus = modifiedLicenseAmendment.getEntity().getLegalStatus();
        String legalStatusOld = modifiedLicenseAmendment.getEntity().getLegalStatusOld();

        Set<String> errors = new HashSet<>();
        modifiedLicenseAmendment.setErrors(errors);
        if (ESTB.equals(legalStatusOld)) {
            long activeShareholdersCount = modifiedLicenseAmendment.getShareholders().stream().filter(s -> !DELETE.equals(s.getAction())).count();
            long activeShareholdersPersonCount = modifiedLicenseAmendment.getShareholders().stream().filter(s -> !DELETE.equals(s.getAction()) && INDIVIDUAL_SHAREHOLDER_TYPE.equals(s.getBpType())).count();
            if (LLC.equals(legalStatus) && activeShareholdersCount < 2L) {
                errors.add(getLocalizedValue("license.amend.type.convertEstablishment.validation"));
                return false;
            } else if (ILLC.equals(legalStatus) && activeShareholdersPersonCount != 1L) {
                errors.add(getLocalizedValue("license.amend.type.convertIndividual.validation"));
                return false;
            }

        }
        return true;
    }

    private List<SimulatedPriceData> computeSimulatedPrices(LicenseAmendment modifiedLicenseAmendment) {
		AmendmentParam functionParams = SimulatedPriceParamsConverter.from(modifiedLicenseAmendment.getAmendmentTypesData());
		PriceSimulationData simulationData = priceSimulationService.getPriceSimulationDataForAmendment(ZSR5,  functionParams);
        List<PriceSimulationItemData> simulatedPrices = simulationData.getPriceSimulationItems();
        return SimulatedPriceParamsConverter.from(simulatedPrices);
    }
    
    private void setConverters(LicenseAmendment modifiedLicenseAmendment, List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {

        long activeShareholdersCount = modifiedLicenseAmendment.getShareholders().stream().filter(s -> !DELETE.equals(s.getAction())).count();
        String legalStatus = modifiedLicenseAmendment.getEntity().getLegalStatus();
        String legalStatusOld = modifiedLicenseAmendment.getEntity().getLegalStatusOld();

        if (activeShareholdersCount > 1L && (ESTB.equals(legalStatusOld) || ILLC.equals(legalStatusOld))) {
            modifiedLicenseAmendment.getEntity().setAction(MODIFY);
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.convertEstablishment"), "60"));
            amendmentTypesData.setEnEstToLlc(AMEND_TYPE_VALUE);
        } else if (activeShareholdersCount == 1L && ESTB.equals(legalStatusOld) && ILLC.equals(legalStatus)) {
            Optional<Shareholder> uniqueShareholder = modifiedLicenseAmendment.getShareholders().stream().filter(s -> !DELETE.equals(s.getAction())).findFirst();
            if (uniqueShareholder.isPresent() && INDIVIDUAL_SHAREHOLDER_TYPE.equals(uniqueShareholder.get().getBpType())) {
                modifiedLicenseAmendment.getEntity().setAction(MODIFY);
                amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.convertIndividual"), "170"));
                amendmentTypesData.setEnLegalstatToIllc(AMEND_TYPE_VALUE);
            }
        } else {
            modifiedLicenseAmendment.getEntity().setLegalStatus(legalStatusOld);
        }
    }

    private void setProductAmendment(LicenseAmendment modifiedLicenseAmendment, List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {
        boolean modifiedProducts = modifiedLicenseAmendment.getProducts().stream().anyMatch(p -> p.getAction() != null && !p.getAction().isEmpty());
        if (modifiedProducts) {
            String licenseType = modifiedLicenseAmendment.getLicenseType();
            if (INDUSTRIAL_LICENSE.equals(licenseType) || AGRICULTURAL_LICENSE.equals(licenseType)) {
                amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.productAmendment"), "110"));
                amendmentTypesData.setPrAddReCh(AMEND_TYPE_VALUE);

                if (modifiedLicenseAmendment.getProducts().stream().anyMatch(p -> p.getDescriptionChanged() != null && p.getDescriptionChanged())) {
                    amendmentTypesData.setPrDescChange(AMEND_TYPE_VALUE);
                }
            }
        }
    }

    private void setIncreaseWorkforce(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData, Entity entity) {
        BigDecimal currentWorkforce = new BigDecimal(entity.getLabourOld());
        BigDecimal modifiedWorkforce = new BigDecimal(entity.getLabour());
        if (currentWorkforce.compareTo(modifiedWorkforce) < 0) {
            entity.setAction(MODIFY);
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.increaseWorkforce"), "140"));
            amendmentTypesData.setEnIncrWf(AMEND_TYPE_VALUE);
        }
    }

    private void setActivityChange(LicenseAmendment modifiedLicenseAmendment, List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData, Entity entity) {
        List<BusinessActivity> businessActivities = modifiedLicenseAmendment.getBusinessActivities();
        if (businessActivities != null && !businessActivities.isEmpty()) {
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.activityAmendment"), "100"));
            amendmentTypesData.setEnActCh(AMEND_TYPE_VALUE);
        }
    }

    private void setCapitalReductionAndIncrease(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData, Entity entity) {
        BigDecimal currentCapital = new BigDecimal(entity.getCapitalOld());
        BigDecimal modifiedCapital = new BigDecimal(entity.getCapital());
        if (currentCapital.compareTo(modifiedCapital) < 0) {
            entity.setAction(MODIFY);
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.capitalIncrease"), "130"));
            amendmentTypesData.setEnCapIncr(AMEND_TYPE_VALUE);
        } else if (currentCapital.compareTo(modifiedCapital) > 0) {
            entity.setAction(MODIFY);
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.capitalReduction"), "120"));
            amendmentTypesData.setEnCapRed(AMEND_TYPE_VALUE);
        }
    }

    private void modifyBrandName(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData, Entity entity) {
        String currentName = entity.getNameOld();
        String modifiedName = entity.getName();
        if (!currentName.equals(modifiedName)) {
            entity.setAction(MODIFY);
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.modifyBrand"), "160"));
            amendmentTypesData.setEnNameCh(AMEND_TYPE_VALUE);
        }
    }

    private void setShareholders(List<Shareholder> shareholders, List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {

        boolean newShareholder = false;
        boolean newCompany = false;
        boolean ownershipToInheritors = false;
        boolean reDistributionOfQuotas = false;
        boolean shareholderExit = false;

        for (Shareholder shareholder : shareholders) {

            if (ADD.equals(shareholder.getAction())) {
                amendmentTypesData.setShAddExNew(AMEND_TYPE_VALUE);

                if (INDIVIDUAL_SHAREHOLDER_TYPE.equals(shareholder.getBpType()) && !newShareholder) {
                    amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.newShareholder"), "30"));
                    if("Y".equalsIgnoreCase(shareholder.getPremiumResident())) {
                    	amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.newShareholder.premiumResident"), "190"));
                    }
                    if(shareholder.getDelegate()!=null) {
                    	amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.newShareholder.delegate"), "200"));
                    }
                    newShareholder = true;
                } else if (!newCompany) {
                    amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.newCompany"), "50"));
                    newCompany = true;
                }

                if (shareholder.getInheritedProperty() && !ownershipToInheritors) {
                    setShInProp(amendmentTypesView, amendmentTypesData);
                    ownershipToInheritors = true;
                }
            } else if (MODIFY.equals(shareholder.getAction())) {
                setShReQu(amendmentTypesView, amendmentTypesData, reDistributionOfQuotas);
                reDistributionOfQuotas = true;

            } else if (DELETE.equals(shareholder.getAction())) {
                setShRem(amendmentTypesView, amendmentTypesData, shareholderExit);
                shareholderExit = true;
            }

        }
    }

    private void setShInProp(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {
        amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.ownershipToInheritors"), "70"));
        amendmentTypesData.setShInProp(AMEND_TYPE_VALUE);
    }

    private void setShReQu(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData, boolean reDistributionOfQuotas) {
        if (!reDistributionOfQuotas) {
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.redistributionOfQuotas"), "10"));
            amendmentTypesData.setShReQu(AMEND_TYPE_VALUE);
        }
    }

    private void setShRem(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData, boolean shareholderExit) {
        if (!shareholderExit) {
            amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.shareholderExit"), "20"));
            amendmentTypesData.setShRem(AMEND_TYPE_VALUE);
        }
    }

    /*
     * Suppress sonar warning (squid:S1151 | "switch case" clauses should not have too many lines of code
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S1151"})
    private void setBranches(LicenseAmendment modifiedLicenseAmendment, List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {

        boolean openBranch = false;
        boolean locationAmendment = false;
        boolean closeBranch = false;
        int newBranchesCount = 0;
        for (Branch b : modifiedLicenseAmendment.getBranches()) {
            switch (b.getAction() == null ? "" : b.getAction()) {
                case ADD:
                    if (!openBranch) {
                        setBrOpen(amendmentTypesView, amendmentTypesData);
                        openBranch = true;
                    }
                    newBranchesCount++;
                    break;
                case MODIFY:
                    if (!locationAmendment) {
                        setEnLocCh(amendmentTypesView, amendmentTypesData);
                        locationAmendment = true;
                    }
                    break;
                case DELETE:
                    if (!closeBranch) {
                        setBrClose(amendmentTypesView, amendmentTypesData);
                        closeBranch = true;
                    }
                    break;
                default:
                    break;
            }
        }
      amendmentTypesData.setNewBranchesCount(newBranchesCount);
    }

    private void setBrClose(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {
        amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.closeBranch"), "90"));
        amendmentTypesData.setBrClose(AMEND_TYPE_VALUE);
    }

    private void setEnLocCh(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {
        amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.locationAmendment"), "150"));
        amendmentTypesData.setEnLocCh(AMEND_TYPE_VALUE);
    }

    private void setBrOpen(List<LicenseAmendmentTypeView> amendmentTypesView, LicenseAmendmentTypeData amendmentTypesData) {
        amendmentTypesView.add(getLicenseAmendmentType(getLocalizedValue("license.amend.type.openBranch"), "80"));
        amendmentTypesData.setBrOpen(AMEND_TYPE_VALUE);
    }

    @Override
    public void checkLicenseAmendmentAvailability() {
        globalValsService.checkLicenseAmendmentAvailability();
    }

    private LicenseAmendmentTypeView getLicenseAmendmentType(String name, String code) {
        LicenseAmendmentTypeView licenseAmendmentType = new LicenseAmendmentTypeView();
        licenseAmendmentType.setName(name);
        licenseAmendmentType.setCode(code);
        return licenseAmendmentType;
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


        for (CustomizingGetData data : customizationListService.getLicenseAmendmentListItems()) {

            switch (data.getFieldname() == null ? "" : data.getFieldname()) {

                case BRANCHTYPE:
                    branchTypes.add(getListItem(data));
                    break;

                case REGION:
                    regions.add(getListItem(data));
                    break;

                case CITY:
                    cities.add(getSubListItem(data));
                    break;

                case INDUSTRY:
                    sectors.add(getListItem(data));
                    break;

                case LEGALSTATUS:
                    legalStatus.add(getListItem(data));
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
                    countries.add(getListItem(data));
                    break;

                case ATTACHMENT:
                    attachments.add(getAttachmentListItem(data));
                    break;

                default:
                    break;

            }
        }
        ListItems listItemsResult = new ListItems();

        regions.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setRegions(regions);

        cities.sort(Comparator.comparing(SubListItem::getName));
        listItemsResult.setCities(cities);

        branchTypes.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setBranchTypes(branchTypes);

        gender.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setGender(gender);

        status.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setStatus(status);

        academicTitle.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setAcademicTitle(academicTitle);
        
        premiumResident.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setPremiumResident(premiumResident);

        unit.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setUnit(unit);

        sectors.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setSectors(sectors);

        multinationalCompany.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setMultinationalCompany(multinationalCompany);

        legalStatus.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setLegalStatus(legalStatus);

        countries.sort(Comparator.comparing(ListItem::getName));
        listItemsResult.setCountries(countries);

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

    @Override
    public Collection<ProductData> getAmendProductsListWithId(String productId, String skip, String top) {
        return amendProductsService.getAmendProductsWithId(productId, skip, top);
    }

    @Override
    public Collection<ProductData> getAmendProductsListWithDescription(String productDescription, String skip, String top) {
        return amendProductsService.getAmendProductsWithDescription(productDescription, skip, top);
    }

    @Override
    public Collection<ProductData> getAmendProductsList(String skip, String top) {
        return amendProductsService.getAmendProductsWithDescription(skip, top);
    }

    /**
     * @return
     */
    public AmendProductsService getAmendProductsService() {
        return amendProductsService;
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
    
    /**
     * @return
     */
    public PriceSimulationService getPriceSimulationService() {
        return priceSimulationService;
    }

    /**
     * @param
     */
    public void setPriceSimulationService(PriceSimulationService priceSimulationService) {
        this.priceSimulationService = priceSimulationService;
    }

    /**
     * @return
     */
    public LicenseAmendmentPopulator getLicenseAmendmentPopulator() {
        return licenseAmendmentPopulator;
    }

    /**
     * @param licenseAmendmentPopulator
     */
    public void setLicenseAmendmentPopulator(LicenseAmendmentPopulator licenseAmendmentPopulator) {
        this.licenseAmendmentPopulator = licenseAmendmentPopulator;
    }

    /**
     * @return
     */
    public LicenseAmendmentService getLicenseAmendmentService() {
        return licenseAmendmentService;
    }

    /**
     * @param licenseAmendmentService
     */
    public void setLicenseAmendmentService(LicenseAmendmentService licenseAmendmentService) {
        this.licenseAmendmentService = licenseAmendmentService;
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
    public LicenseAmendmentShareholderService getLicenseAmendmentShareholderService() {
        return licenseAmendmentShareholderService;
    }

    /**
     * @param licenseAmendmentShareholderService
     */
    public void setLicenseAmendmentShareholderService(LicenseAmendmentShareholderService licenseAmendmentShareholderService) {
        this.licenseAmendmentShareholderService = licenseAmendmentShareholderService;
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

    /**
     * @return
     */
    public LicenseAmendmentReversePopulator getLicenseAmendmentReversePopulator() {
        return licenseAmendmentReversePopulator;
    }

    /**
     * @param licenseAmendmentReversePopulator
     */
    public void setLicenseAmendmentReversePopulator(LicenseAmendmentReversePopulator licenseAmendmentReversePopulator) {
        this.licenseAmendmentReversePopulator = licenseAmendmentReversePopulator;
    }

    /**
     * @param amendProductsService
     */
    public void setAmendProductsService(AmendProductsService amendProductsService) {
        this.amendProductsService = amendProductsService;
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

	public MojInheritService getMojInheritService() {
		return mojInheritService;
	}

	public void setMojInheritService(MojInheritService mojInheritService) {
		this.mojInheritService = mojInheritService;
	}

	public MOJInheritPopulator getMojInheritPopulator() {
		return mojInheritPopulator;
	}

	public void setMojInheritPopulator(MOJInheritPopulator mojInheritPopulator) {
		this.mojInheritPopulator = mojInheritPopulator;
	}
    
}
