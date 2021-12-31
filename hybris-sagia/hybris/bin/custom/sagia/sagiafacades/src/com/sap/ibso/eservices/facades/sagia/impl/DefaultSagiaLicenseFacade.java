package com.sap.ibso.eservices.facades.sagia.impl;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import com.sap.ibso.eservices.facades.data.BranchData;
import com.sap.ibso.eservices.facades.data.ContactPersonData;
import com.sap.ibso.eservices.facades.data.GeneralManagerData;
import com.sap.ibso.eservices.facades.data.LicenseData;
import com.sap.ibso.eservices.facades.data.ProfileCompanyData;
import com.sap.ibso.eservices.facades.data.ShareholderData;
import com.sap.ibso.eservices.facades.populators.BranchReversePopulator;
import com.sap.ibso.eservices.facades.populators.license.BranchPopulator;
import com.sap.ibso.eservices.facades.populators.license.ClassificToCompanyPopulator;
import com.sap.ibso.eservices.facades.populators.license.ContactPersonPopulator;
import com.sap.ibso.eservices.facades.populators.license.GeneralManagerPopulator;
import com.sap.ibso.eservices.facades.populators.license.LicensePopulator;
import com.sap.ibso.eservices.facades.populators.license.LicenseToCompanyPopulator;
import com.sap.ibso.eservices.facades.populators.license.MainBranchPopulator;
import com.sap.ibso.eservices.facades.populators.license.ShareholdersPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaLicenseFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ClassificData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtBranchData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtHeaderData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeContactDetailData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseInfoData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ShareHolderInfoData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;

import de.hybris.platform.servicelayer.i18n.I18NService;

/**
 * DefaultSagiaLicenseFacade
 */
public class DefaultSagiaLicenseFacade implements SagiaLicenseFacade {
    private static final String CONTACT_PERSON_GENERAL_MANAGER = "GM";
    private static final String CONTACT_PERSON = "R1";

    private static final Logger LOG = Logger.getLogger(DefaultSagiaLicenseFacade.class);

    @Autowired
    private ZUI5SagiaFacade zui5SagiaFacade;

    private BranchReversePopulator branchReversePopulator;
    private LicensePopulator licensePopulator;
    private ShareholdersPopulator shareholdersPopulator;
    private BranchPopulator branchPopulator;
    private MainBranchPopulator mainBranchPopulator;
    private GeneralManagerPopulator generalManagerPopulator;
    private ContactPersonPopulator contactPersonPopulator;
    private LicenseToCompanyPopulator licenseToCompanyPopulator;
    private ClassificToCompanyPopulator classificToCompanyPopulator;
    private I18NService i18NService;
    private MessageSource messageSource;

    /**
     * Retrieves a license DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - Licence DTO split from Home HDR
     */
    @Override
    public LicenseData getLicense(HomeHDRData homeHDRData) {
        LicenseData licenseData = new LicenseData();
        if (homeHDRData != null) {
            licensePopulator.populate(homeHDRData, licenseData);

            licenseData.setShareholdersData(getShareholders(homeHDRData));
            licenseData.setGeneralManager(getGeneralManager(homeHDRData));
            licenseData.setContactPerson(getContactPerson(homeHDRData));
            licenseData.setProfileCompanyData(getProfileCompany(homeHDRData));
            licenseData.setBranches(getBranches(homeHDRData.getBpID()));
        }
        return licenseData;
    }
    
    /**
     * get status of current license
     */
    @Override
    public String getEntityStatus(HomeHDRData homeHDRData) {
    		LicenseData currentLicense = getLicense(homeHDRData);
    		ProfileCompanyData profileCompanyData = currentLicense.getProfileCompanyData();
    		return profileCompanyData.getEntityStatus();
    }
    
	@Override
	public boolean hasInvalidLicense(HomeHDRData homeHDRData) {
		String currentStatus = getEntityStatus(homeHDRData);
		String validLicenseKey = messageSource.getMessage("general.license.active", null,
				i18NService.getCurrentLocale());
		if (StringUtils.isEmpty(currentStatus)) {
			return true;
		}
		return !ObjectUtils.equalsIgnoreCase(currentStatus, validLicenseKey);
	}

    /**
     * Retrieves a shareholders DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - Shareholders DTO split from Home HDR
     */
    @Override
    public List<ShareholderData> getShareholders(HomeHDRData homeHDRData) {
        Set<ShareHolderInfoData> shareholders = homeHDRData.getShareHolderInfoDataSet();

        List<ShareholderData> shareholdersData = new ArrayList<>();
        if(shareholders != null) {
            for (ShareHolderInfoData shareholder : shareholders) {
                ShareholderData shareholderData = new ShareholderData();
                getShareholdersPopulator().populate(shareholder, shareholderData);
                shareholdersData.add(shareholderData);
            }
            shareholdersData.sort(Comparator.comparingDouble(ShareholderData::getSharesPercentage).reversed());
        }
        return shareholdersData;
    }

    /**
     * Get all the branches
     *
     * @return - The list of all branches
     */
    @Override
    public List<BranchData> getBranches(String entityId) {
        List<GovtBranchData> branches = zui5SagiaFacade.getGovtHeaderWithBranchSet(entityId).getGovtBranchSet();
        List<BranchData> branchesData = new ArrayList<>();

        for (int i = 0; i < branches.size(); i++) {
            GovtBranchData branch = branches.get(i);
            BranchData branchData = new BranchData();
            branchData.setSelectedIndex(i);
            getBranchPopulator().populate(branch, branchData);
            branchesData.add(branchData);
        }

        return branchesData;
    }

    /**
     * Get the main branch(Government Header) from the CRM
     *
     * @param entityId - The entity id for which the branches are retrieved
     * @return - Branch data for the main branch
     */
    @Override
    public BranchData getGovtHeader(String entityId) {
        GovtHeaderData branch = zui5SagiaFacade.getGovtHeader(entityId);
        BranchData branchData = new BranchData();
        getMainBranchPopulator().populate(branch, branchData);
        return branchData;
    }

    /**
     * Retrieves a general manager DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - General Manager DTO split from Home HDR
     */
    @Override
    public GeneralManagerData getGeneralManager(HomeHDRData homeHDRData) {
        GeneralManagerData generalManagerData = new GeneralManagerData();

        try {
            List<HomeContactDetailData> contacts = new ArrayList<HomeContactDetailData>(homeHDRData.getHomeContactDetailDataSet());
            HomeContactDetailData generalManager = new HomeContactDetailData();
            for (HomeContactDetailData contact:
                    contacts) {
                if (contact.getContactType().equals(CONTACT_PERSON_GENERAL_MANAGER)) {
                    generalManager = contact;
                }
            }
            getGeneralManagerPopulator().populate(generalManager, generalManagerData);
        } catch(Exception e) {
            LOG.warn(e.getMessage(), e);
        }

        return generalManagerData;
    }

    /**
     * Retrieves list with contact persons DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - List<Contact person DTO> split from Home HDR
     */
	@Override
	public List<ContactPersonData> getContactPerson(HomeHDRData homeHDRData) {
		List<ContactPersonData> contactPersons = new ArrayList<>();
		try {
			List<HomeContactDetailData> contacts = new ArrayList<>(homeHDRData.getHomeContactDetailDataSet());
			for (HomeContactDetailData contact : contacts) {
				if (StringUtils.equals(contact.getContactType(), CONTACT_PERSON)) {
					ContactPersonData contactPersonData = new ContactPersonData();
					getContactPersonPopulator().populate(contact, contactPersonData);
					contactPersons.add(contactPersonData);
				}
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		}
		return contactPersons;
	}

    /**
     * Retrieves a profile company DTO from the HomeHDR data that comes from the CRM
     *
     * @param homeHDRData - Home HDR data(the whole information for the dashboard)
     * @return - Profile company DTO split from Home HDR
     */
    @Override
    public ProfileCompanyData getProfileCompany(HomeHDRData homeHDRData) {
        ProfileCompanyData companyData = new ProfileCompanyData();

        try {
            LicenseInfoData licenseInfo = homeHDRData.getLicenseInfoData();
            ClassificData classific = homeHDRData.getClassificData();

            getLicenseToCompanyPopulator().populate(licenseInfo, companyData);
            getClassificToCompanyPopulator().populate(classific, companyData);
        } catch(Exception e) {
            LOG.warn(e.getMessage(), e);
        }

        return companyData;
    }

    /***
     * Post the main branch among with its sub-branches to the CRM
     * @param branchData - Main branch DTO containing the subset of its branches
     */
    @Override
    public GovtHeaderData saveGovernmentHeader(BranchData branchData) {
        GovtHeaderData data = new GovtHeaderData();
        branchReversePopulator.populateMainBranch(branchData, data);

        List<GovtBranchData> govtBranchDataList = new ArrayList<>();

        for (BranchData b : branchData.getBranches()) {
            GovtBranchData govtBranchData = new GovtBranchData();
            branchReversePopulator.populate(b, govtBranchData);
            govtBranchDataList.add(govtBranchData);
        }
        data.setGovtBranchSet(govtBranchDataList);

        return zui5SagiaFacade.saveGovtHeaderData(data);
    }

    /**
     * @return
     */
    public BranchReversePopulator getBranchReversePopulator() {
        return branchReversePopulator;
    }

    /**
     * @param branchReversePopulator
     */
    public void setBranchReversePopulator(BranchReversePopulator branchReversePopulator) {
        this.branchReversePopulator = branchReversePopulator;
    }

    /**
     * @return
     */
    public LicensePopulator getLicensePopulator() {
        return licensePopulator;
    }

    /**
     * @param licensePopulator
     */
    public void setLicensePopulator(LicensePopulator licensePopulator) {
        this.licensePopulator = licensePopulator;
    }

    /**
     * @return
     */
    public ShareholdersPopulator getShareholdersPopulator() {
        return shareholdersPopulator;
    }

    /**
     * @param shareholdersPopulator
     */
    public void setShareholdersPopulator(ShareholdersPopulator shareholdersPopulator) {
        this.shareholdersPopulator = shareholdersPopulator;
    }

    /**
     * @return
     */
    public BranchPopulator getBranchPopulator() {
        return branchPopulator;
    }

    /**
     * @param branchPopulator
     */
    public void setBranchPopulator(BranchPopulator branchPopulator) {
        this.branchPopulator = branchPopulator;
    }

    /**
     * @return
     */
    public MainBranchPopulator getMainBranchPopulator() {
        return mainBranchPopulator;
    }

    /**
     * @param mainBranchPopulator
     */
    public void setMainBranchPopulator(MainBranchPopulator mainBranchPopulator) {
        this.mainBranchPopulator = mainBranchPopulator;
    }

    /**
     * @return
     */
    public GeneralManagerPopulator getGeneralManagerPopulator() {
        return generalManagerPopulator;
    }

    /**
     * @param generalManagerPopulator
     */
    public void setGeneralManagerPopulator(GeneralManagerPopulator generalManagerPopulator) {
        this.generalManagerPopulator = generalManagerPopulator;
    }

    /**
     * @return
     */
    public ContactPersonPopulator getContactPersonPopulator() {
        return contactPersonPopulator;
    }

    /**
     * @param contactPersonPopulator
     */
    public void setContactPersonPopulator(ContactPersonPopulator contactPersonPopulator) {
        this.contactPersonPopulator = contactPersonPopulator;
    }

    /**
     * @param licenseToCompanyPopulator
     */
    public void setLicenseToCompanyPopulator(LicenseToCompanyPopulator licenseToCompanyPopulator) {
        this.licenseToCompanyPopulator = licenseToCompanyPopulator;
    }

    /**
     * @return
     */
    public LicenseToCompanyPopulator getLicenseToCompanyPopulator() {
        return licenseToCompanyPopulator;
    }

    /**
     * @param classificToCompanyPopulator
     */
    public void setClassificToCompanyPopulator(ClassificToCompanyPopulator classificToCompanyPopulator) {
        this.classificToCompanyPopulator = classificToCompanyPopulator;
    }

    /**
     * @return
     */
    public ClassificToCompanyPopulator getClassificToCompanyPopulator() {
        return classificToCompanyPopulator;
    }
    
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 
     * @param i18nService
     */
    public void setI18nService(I18NService i18NService) {
        this.i18NService = i18NService;
    }

}
