package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateEntity;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateForm;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateHistory;
import com.sap.ibso.eservices.facades.populators.SagiaBasicCompanyInfoPopulator;
import com.sap.ibso.eservices.facades.populators.SagiaBasicInfoDictionaryPopulator;
import com.sap.ibso.eservices.facades.populators.SurveyDataPopulator;
import com.sap.ibso.eservices.facades.populators.account.ContactUpdateHistoryPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaAccountFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeContactDetailData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceBpHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SurveyHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.servicelayer.session.SessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;

/**
 * DefaultSagiaAccountFacade
 */
public class DefaultSagiaAccountFacade implements SagiaAccountFacade {
    private static final Logger LOG = Logger.getLogger(DefaultSagiaAccountFacade.class);

    private static final String CONTACT_PERSON_GENERAL_MANAGER = "GM";
    private static final String COMPANY_REPRESENTATIVE = "R";
    private static final String SOLD_TO_PARTY = "SP";
    private static final String CRM_CHECK_INDICATOR = "X";
    public static final String RENEWAL_INDICATORS = "renewalIndicators";
    public static final String PRIMARY_CONTACT = "primaryContact";

    private CustomerFacade customerFacade;
    private SagiaBasicCompanyInfoPopulator basicCompanyInfoPopulator;
    private SagiaBasicInfoDictionaryPopulator basicInfoDictionaryPopulator;
    private SurveyDataPopulator surveyDataPopulator;
    private SagiaFormatProvider sagiaFormatProvider;
    private ContactUpdateHistoryPopulator contactUpdateHistoryPopulator;

    @Resource(name = "ZUI5SagiaFacade")
    private ZUI5SagiaFacade zui5SagiaFacade;

    @Autowired
    private SessionService sessionService;

    @Override
    public ContactData getCompanyContacts() {
        final ContactData result = new ContactData();
        final HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();

        final ProfileGeneralManagerData generalManagerData = getGeneralManager(homeHDR);
        final List<ProfileCompanyRepresentativeData> companyRepresentatives = getCompanyRepresentatives(homeHDR);
        zui5SagiaFacade.fillDocumentsFromServiceBPHDR(generalManagerData, companyRepresentatives);

        result.setProfileGeneralManagerData(generalManagerData);
        result.setProfileCompanyRepresentativesData(companyRepresentatives);

        return result;
    }

    @Override
    public BasicCompanyData getProfileCompanyData() {
        final com.sap.ibso.eservices.sagiaservices.data.company.BasicCompanyData sagiaCompanyData = zui5SagiaFacade.getCompanyInformation();
        final BasicCompanyData facadeCompanyData = new BasicCompanyData();
        basicCompanyInfoPopulator.populate(sagiaCompanyData, facadeCompanyData);
        return facadeCompanyData;
    }

    @Override
    public ProfileCompanyRepresentativeData getPrimaryContact() {
        // primary contact DTO which is returned at the end of the method.
        ProfileCompanyRepresentativeData primaryContact = sessionService.getCurrentSession().getAttribute(PRIMARY_CONTACT);

        // if the primary contact does not exist on the Hybris session.
        if(primaryContact == null) {
            try {
                final HomeHDRData homeHDR = zui5SagiaFacade.getHomeHDRData();

                // find the primary contact between representatives
                Optional<ProfileCompanyRepresentativeData> primaryContactOptional = getCompanyEntitiesContacts(homeHDR).stream().findFirst();
                if (primaryContactOptional.isPresent()) {
                    primaryContact = primaryContactOptional.get();
                }
                // set the current primary contact to the session.
                sessionService.setAttribute(PRIMARY_CONTACT, primaryContact);
            } catch(Exception e) {
                LOG.debug(e.getMessage(), e); //there is no primary contact in CRM yet (for instance if it's a newly created user)
                return null;
            }
        }
        // return the primary contact DTO.
        return primaryContact;
    }

    @Override
    public SurveyData getSurveyHDRById(String id) {
        final SurveyHDRData surveyHDRData = zui5SagiaFacade.getSurveyHDRData(id);
        final SurveyData surveyData = new SurveyData();
        surveyDataPopulator.populate(surveyHDRData, surveyData);
        return surveyData;
    }

    @Override
    public void updateContacts(ContactUpdateForm contactUpdateForm) {
        zui5SagiaFacade.updateContacts(contactUpdateForm);
        Optional<ContactUpdateEntity> primaryContactOptional = contactUpdateForm.getUpdatedContacts().stream().filter(contactUpdateEntity -> SOLD_TO_PARTY.equals(contactUpdateEntity.getContactType())).findFirst();
        ProfileCompanyRepresentativeData spContact = new ProfileCompanyRepresentativeData();
        /// A primary contact must always be present on the contact update.
        if(primaryContactOptional.isPresent()) {
            ContactUpdateEntity contactUpdateEntity = primaryContactOptional.get();
            spContact.setIsPrimaryContact(true);
            spContact.setEmail(contactUpdateEntity.getEmail());
            spContact.setFirstName(contactUpdateEntity.getFirstName());
            spContact.setMiddleName(contactUpdateEntity.getMiddleName());
            spContact.setLastName(contactUpdateEntity.getLastName());
            spContact.setMobileNumber(contactUpdateEntity.getMobileNumber());
            spContact.setNationality(contactUpdateEntity.getNationalId());
            if(spContact.getEmail() != null && !spContact.getEmail().trim().isEmpty()){
                sessionService.setAttribute(PRIMARY_CONTACT, spContact);
            }
        }
    }

    /**
     * Checks if the contact update request may be completed.
     */
    @Override
    public void checkContactUpdateAvailability() {
        zui5SagiaFacade.checkContactUpdateAvailability();
    }

    @Override
    public InputStream readAttachment(final String objectId, final String documentId) {
        return zui5SagiaFacade.readAttachment(objectId, documentId);
    }

    /**
     * Gets all the history of the contact updates.
     *
     * @return - Collection of contact updates history
     */
    @Override
    public Collection<ContactUpdateHistory> getAllContactUpdateHistory() {
        Collection<ServiceBpHDRData> contactUpdateHistory = zui5SagiaFacade.getContactUpdateHistory();
        Collection<ContactUpdateHistory> history = new ArrayList<>();
        List<ServiceBpHDRData> contactUpdateHistoryList = new ArrayList<>(contactUpdateHistory);
        contactUpdateHistoryList.sort(Comparator.comparing(ServiceBpHDRData::getSrCrDate).reversed());

        for (ServiceBpHDRData h : contactUpdateHistoryList) {
            ContactUpdateHistory contactUpdateHistoryData = new ContactUpdateHistory();
            contactUpdateHistoryPopulator.populate(h, contactUpdateHistoryData);
            history.add(contactUpdateHistoryData);
        }

        return history;
    }

    /**
     * Return a contact update history entry DTO
     *
     * @param id - The id for which the history entry DTO is retrieved.
     * @return - The history entry DTO with that specific id.
     */
    @Override
    public ContactUpdateHistory getContactUpdateHistoryEntry(String id) {
        ServiceBpHDRData serviceBpHDRData = zui5SagiaFacade.getHistoryEntry(id);
        ContactUpdateHistory profileCompanyRepresentativeData = new ContactUpdateHistory();
        contactUpdateHistoryPopulator.populate(serviceBpHDRData, profileCompanyRepresentativeData);
        return profileCompanyRepresentativeData;
    }

    @Override
    public LicenseRenewalIndicators getRenewalIndicators(HomeHDRData homeHDRData) {
        LicenseRenewalIndicators indicators = new LicenseRenewalIndicators();
        indicators.setAutoRenewal(CRM_CHECK_INDICATOR.equals(homeHDRData.getAutoRenewalFlag()));
        indicators.setAutoRenewalForClearance(CRM_CHECK_INDICATOR.equals(homeHDRData.getAutorrfcEligible()));
        indicators.setCheckGovernmentDocuments(CRM_CHECK_INDICATOR.equals(homeHDRData.getCheckGovtFlag()));
        indicators.setCheckRenewalForClearance(CRM_CHECK_INDICATOR.equals(homeHDRData.getCheckRfcFlag()));
        indicators.setEnforcePopup(CRM_CHECK_INDICATOR.equals(homeHDRData.getEnforcepopup()));
        indicators.setEnforceRenewalForClearancePopup(CRM_CHECK_INDICATOR.equals(homeHDRData.getEnforceRfcPopup()));

        return indicators;
    }
    
    @Override
    public SagiaDateData getLicExDateData(HomeHDRData homeHDRData) {
    	try
    	{
    	return sagiaFormatProvider.getLocalizedDateData(homeHDRData.getLicenseInfoData().getLicExDate());
    	}
    	catch (Exception e) {
			// TODO: handle exception
		}
        return null;
    }
    
    @Override
    public Integer getRenewalMaxDuration(String maxDuration) {
       
        if(null != maxDuration) {
        	return Integer.parseInt(maxDuration);
        }

        return 0 ; // check with Ilam weather we are going to use a default value.
    }
    
    @Override
    public Integer getRenewalLicenseFeePerYr(String licenseFeePerYr) {
       
        if(null != licenseFeePerYr && Strings.isNotEmpty(licenseFeePerYr)) {
        	String licenseFeePerYrTrim = licenseFeePerYr.trim();
        	return Integer.parseInt(licenseFeePerYrTrim);
        }

        return 0 ; 
    }
    
    @Override
    public Integer getSubsOutstandingFee(String subsOutstandingFee) {
       
        if(null != subsOutstandingFee && Strings.isNotEmpty(subsOutstandingFee)) {
        	String subsOutstandingFeeTrim = subsOutstandingFee.trim();
        	return Integer.parseInt(subsOutstandingFeeTrim);
        }

        return 0 ; 
    }

    @Override
    public MandatorySurveyIndicators getMandatorySurveyIndicators(HomeHDRData homeHDRData) {
        MandatorySurveyIndicators indicators = new MandatorySurveyIndicators();
        indicators.setMandSurvey(CRM_CHECK_INDICATOR.equals(homeHDRData.getMandSurvey()));
        indicators.setActiveSurvey(CRM_CHECK_INDICATOR.equals(homeHDRData.getActiveSurvey()));
        return indicators;

    }

    private ProfileGeneralManagerData getGeneralManager(HomeHDRData homeHDRData) {
        List<HomeContactDetailData> contacts = new ArrayList<>(homeHDRData.getHomeContactDetailDataSet());
        HomeContactDetailData generalManager = new HomeContactDetailData();
        for (HomeContactDetailData contact:
                contacts) {
            if (contact.getContactType().equals(CONTACT_PERSON_GENERAL_MANAGER)) {
                generalManager = contact;
            }
        }
        ProfileGeneralManagerData generalManagerData = new ProfileGeneralManagerData();
        generalManagerData.setBpId(generalManager.getBpID());
        generalManagerData.setEmail(generalManager.getEmail());
        generalManagerData.setMobileNumber(generalManager.getMobileNumber());
        generalManagerData.setFirstName(generalManager.getFirstName());
        generalManagerData.setLastName(generalManager.getLastName());
        generalManagerData.setMiddleName(generalManager.getMiddleName());
        generalManagerData.setNationality(generalManager.getNationality());
        generalManagerData.setNationalId(generalManager.getNationalId());
        if(generalManager.getPriContFlag() != null){
            generalManagerData.setIsPrimaryContact(generalManager.getPriContFlag().equalsIgnoreCase(CRM_CHECK_INDICATOR));
        }
        return generalManagerData;
    }

    private List<ProfileCompanyRepresentativeData> getCompanyRepresentatives(HomeHDRData homeHDRData) {
        List<HomeContactDetailData> contacts = new ArrayList(homeHDRData.getHomeContactDetailDataSet());
        contacts = contacts.stream().filter(c -> c.getContactType().startsWith(COMPANY_REPRESENTATIVE)).collect(Collectors.toList());
        List<ProfileCompanyRepresentativeData> representatives = new ArrayList<>();

        getContacts(contacts, representatives);

        representatives.sort(Comparator.comparing(ProfileCompanyRepresentativeData::getContactType));
        return representatives;
    }

    private List<ProfileCompanyRepresentativeData> getCompanyEntitiesContacts(HomeHDRData homeHDRData) {
        List<HomeContactDetailData> contacts = new ArrayList(homeHDRData.getHomeContactDetailDataSet());
        List<ProfileCompanyRepresentativeData> entitiesContacts = new ArrayList<>();
        contacts = contacts.stream().filter(c -> c.getContactType().equals(SOLD_TO_PARTY)).collect(Collectors.toList());
        getContacts(contacts, entitiesContacts);
        entitiesContacts.sort(Comparator.comparing(ProfileCompanyRepresentativeData::getContactType));
        return entitiesContacts;
    }

    private void getContacts(List<HomeContactDetailData> contacts, List<ProfileCompanyRepresentativeData> entitiesContacts) {
        for(HomeContactDetailData contactPerson : contacts){
            ProfileCompanyRepresentativeData contactPersonData = new ProfileCompanyRepresentativeData();
            contactPersonData.setBpId(contactPerson.getBpID());
            contactPersonData.setEmail(contactPerson.getEmail());
            contactPersonData.setFirstName(contactPerson.getFirstName());
            contactPersonData.setLastName(contactPerson.getLastName());
            contactPersonData.setMobileNumber(contactPerson.getMobileNumber());
            contactPersonData.setMiddleName(contactPerson.getMiddleName());
            contactPersonData.setNationality(contactPerson.getNationality());
            contactPersonData.setNationalId(contactPerson.getNationalId());
            contactPersonData.setContactType(contactPerson.getContactType());
            contactPersonData.setIsPrimaryContact(contactPerson.getPriContFlag().equalsIgnoreCase(CRM_CHECK_INDICATOR));
            entitiesContacts.add(contactPersonData);
        }
    }

    /**
     * @return customer facade
     */
    public CustomerFacade getCustomerFacade() {
        return customerFacade;
    }

    /**
     * @param customerFacade to set
     */
    public void setCustomerFacade(final CustomerFacade customerFacade) {
        this.customerFacade = customerFacade;
    }

    /**
     * @return basicCompanyInfoPopulator
     */
    public SagiaBasicCompanyInfoPopulator getBasicCompanyInfoPopulator() {
        return basicCompanyInfoPopulator;
    }

    /**
     * @param basicCompanyInfoPopulator to set
     */
    public void setBasicCompanyInfoPopulator(final SagiaBasicCompanyInfoPopulator basicCompanyInfoPopulator) {
        this.basicCompanyInfoPopulator = basicCompanyInfoPopulator;
    }

    /**
     * @return basicInfoDictionaryPopulator
     */
    public SagiaBasicInfoDictionaryPopulator getBasicInfoDictionaryPopulator() {
        return basicInfoDictionaryPopulator;
    }

    /**
     * @param basicInfoDictionaryPopulator to set
     */
    public void setBasicInfoDictionaryPopulator(final SagiaBasicInfoDictionaryPopulator basicInfoDictionaryPopulator) {
        this.basicInfoDictionaryPopulator = basicInfoDictionaryPopulator;
    }

    /**
     * @return surveyDataPopulator
     */
    public SurveyDataPopulator getSurveyDataPopulator() {
        return surveyDataPopulator;
    }

    /**
     * @param surveyDataPopulator to set
     */
    public void setSurveyDataPopulator(final SurveyDataPopulator surveyDataPopulator) {
        this.surveyDataPopulator = surveyDataPopulator;
    }

    /**
     * @return sagiaFormatProvider
     */
    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    /**
     * @param sagiaFormatProvider to set
     */
    public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    public ContactUpdateHistoryPopulator getContactUpdateHistoryPopulator() {
        return contactUpdateHistoryPopulator;
    }

    public void setContactUpdateHistoryPopulator(ContactUpdateHistoryPopulator contactUpdateHistoryPopulator) {
        this.contactUpdateHistoryPopulator = contactUpdateHistoryPopulator;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
