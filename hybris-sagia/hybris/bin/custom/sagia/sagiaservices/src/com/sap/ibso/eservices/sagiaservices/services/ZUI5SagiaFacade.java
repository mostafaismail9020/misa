package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.facades.data.ProfileCompanyRepresentativeData;
import com.sap.ibso.eservices.facades.data.ProfileGeneralManagerData;
import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.facades.data.RegionCityData;
import com.sap.ibso.eservices.facades.data.account.ContactUpdateForm;
import com.sap.ibso.eservices.sagiaservices.company.CompanyInformationService;
import com.sap.ibso.eservices.sagiaservices.data.GovtDocCheck;
import com.sap.ibso.eservices.sagiaservices.data.RemovePopupALR;
import com.sap.ibso.eservices.sagiaservices.data.SagiaGovDocWasselCheck;
import com.sap.ibso.eservices.sagiaservices.data.company.BasicCompanyData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.*;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMCreateException;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateViolationReplyFormData;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateWarningLetterExtensionFormData;
import com.sap.ibso.eservices.sagiaservices.services.impl.*;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.GlobalLicenseCancellationService;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseCancellationSetService;
import com.sap.ibso.eservices.sagiaservices.services.licensecancellation.LicenseClearanceSetService;
import com.sap.ibso.eservices.sagiaservices.services.licensereplacement.LicenseReplacementService;
import com.sap.ibso.eservices.sagiaservices.services.questionnaires.dto.SurveyResultData;
import com.sap.ibso.eservices.sagiaservices.services.surveys.zui5.SurveyHDRService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *  ZUI5SagiaFacade
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ZUI5SagiaFacade {
    private HomeHDRService homeHDRService;
    private SurveyHDRService surveyHDRService;
    private AppointmentService appointmentService;
    private CalendarSlotService calendarSlotService;
    private AmendHeadersService amendHeadersService;
    private GovtHeaderService govtHeaderService;
    private AmanahService amanahService;
    private CustomizationListService customizationListService;

    private ServiceBpHDRService serviceBpHDRService;
    private LicenseReplacementService licenseReplacementService;
    private WarningLettersInfosService warningLettersInfosService;
    private LicenseClearanceSetService licenseClearanceSetService;
    private GlobalLicenseCancellationService globalLicenseCancellationService;
    private LicenseCancellationSetService licenseCancellationSetService;
    private FinancialService financialService;
    private FollowUpService followUpService;
    private SagiaRegionCityService sagiaRegionCityService;
    private SagiaRealEstateService sagiaRealEstateService;

    private ServiceReqHDRService servicesReqHDRService;
    private ServicesRequestHDRService servicesRequestHDRService;
    private GovDocWasselCheckService govDocWasselCheckService;
    private GlobalValsService globalValsService;

    private CompanyInformationService companyInformationService;

    private SagiaRealEstateEntityDetailsSetService realEstateEntityDetailsSetService;
    private CheckGovernmentDocService checkGovDocService;
    private RemovePopupALRService removePopupALRService;

    /**
     * retrieves GovDocWasselCheckService
     * @return GovDocWasselCheckService
     */
    public GovDocWasselCheckService getGovDocWasselCheckService() {
        return govDocWasselCheckService;
    }

    /**
     * sets GovDocWasselCheckService
     * @param govDocWasselCheckService govDocWasselCheckService
     */
    public void setGovDocWasselCheckService(GovDocWasselCheckService govDocWasselCheckService) {
        this.govDocWasselCheckService = govDocWasselCheckService;
    }

    /**
     * requestWasselCheck
     * @return SagiaGovDocWasselCheck
     */
    public SagiaGovDocWasselCheck requestWasselCheck(){
        return getGovDocWasselCheckService().requestWasselCheck();
    }


    /**
     * sets AppointmentService
     * @param appointmentService appointmentService
     */
    @Required
    public void setAppointmentService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * sets CalendarSlotService
     * @param calendarSlotService calendarSlotService
     */
    @Required
    public void setCalendarSlotService(CalendarSlotService calendarSlotService) {
        this.calendarSlotService = calendarSlotService;
    }

    /**
     * sets CustomizationListService
     * @param customizationListService customizationListService
     */
    @Required
    public void setCustomizationListService(CustomizationListService customizationListService) {
        this.customizationListService = customizationListService;
    }

    /**
     * sets HomeHDRService
     * @param homeHDRService homeHDRService
     */
    @Required
    public void setHomeHDRService(HomeHDRService homeHDRService) {
        this.homeHDRService = homeHDRService;
    }

    /**
     * retrieves HomeHDRService
     * @return HomeHDRService
     */
    HomeHDRService getHomeHDRService() {
        return homeHDRService;
    }

    /**
     * sets SurveyHDRService
     * @param surveyHDRService surveyHDRService
     */
    @Required
    public void setSurveyHDRService(SurveyHDRService surveyHDRService) {
        this.surveyHDRService = surveyHDRService;
    }

    /**
     * retrieves SurveyHDRService
     * @return SurveyHDRService
     */
    SurveyHDRService getSurveyHDRService() {
        return surveyHDRService;
    }

    /**
     * retrieves AppointmentService
     * @return AppointmentService
     */
    AppointmentService getAppointmentService() {
        return appointmentService;
    }

    /**
     * sets GovtHeaderService
     * @param  govtHeaderService govtHeaderService
     */
    @Required
    public void setGovtHeaderService(GovtHeaderService govtHeaderService) {
        this.govtHeaderService = govtHeaderService;
    }

    /**
     * retrieves GovtHeaderService
     * @return GovtHeaderService
     */
    GovtHeaderService getGovtHeaderService() {
        return govtHeaderService;
    }

    /**
     * sets AmanahService
     * @param amanahService amanahService
     */
    @Required
    public void setAmanahService(AmanahService amanahService) {
        this.amanahService = amanahService;
    }

    /**
     * retrieves AmanahService
     * @return AmanahService
     */
    AmanahService getAmanahService() {
        return amanahService;
    }

    /**
     * sets AmendHeadersService
     * @param amendHeadersService amendHeadersService
     */
    @Required
    public void setAmendHeadersService(AmendHeadersService amendHeadersService) {
        this.amendHeadersService = amendHeadersService;
    }

    /**
     * retrieves AmendHeadersService
     * @return AmendHeadersService
     */
    AmendHeadersService getAmendHeadersService() {
        return amendHeadersService;
    }

    /**
     * sets FinancialService
     * @param financialService financialService
     */
    @Required
    public void setFinancialService(FinancialService financialService) {
        this.financialService = financialService;
    }

    /**
     * retrieves FinancialService
     * @return FinancialService
     */
    FinancialService getFinancialService() {
        return financialService;
    }

    /**
     * sets ServiceBpHDRService
     * @param serviceBpHDRService serviceBpHDRService
     */
    @Required
    public void setServiceBpHDRService(final ServiceBpHDRService serviceBpHDRService) {
        this.serviceBpHDRService = serviceBpHDRService;
    }

    /**
     * retrieves ServiceBpHDRService
     * @return ServiceBpHDRService
     */
    ServiceBpHDRService getServiceBpHDRService() {
        return serviceBpHDRService;
    }

    /**
     * sets LicenseClearanceSetService
     * @param licenseClearanceSetService licenseClearanceSetService
     */
    @Required
    public void setLicenseClearanceSetService(LicenseClearanceSetService licenseClearanceSetService) {
        this.licenseClearanceSetService = licenseClearanceSetService;
    }

    /**
     * retrieves LicenseClearanceSetService
     * @return LicenseClearanceSetService
     */
    public LicenseClearanceSetService getLicenseClearanceSetService() {
        return licenseClearanceSetService;
    }

    /**
     * Save Real Estate object to CRM.
     *
     * @param realEstateData realEstateData
     * @return true / false based on the success of the saving operation.
     */
    public boolean saveRealEstate(RealEstateData realEstateData) {
        return sagiaRealEstateService.createObjectWithBatchPost(realEstateData, "RealEstateSet");
    }

    /**
     * sets GlobalLicenseCancellationService
     * @param globalLicenseCancellationService globalLicenseCancellationService
     */
    @Required
    public void setGlobalLicenseCancellationService(GlobalLicenseCancellationService globalLicenseCancellationService) {
        this.globalLicenseCancellationService = globalLicenseCancellationService;
    }

    /**
     * retrieves GlobalLicenseCancellationService
     * @return GlobalLicenseCancellationService
     */
    GlobalLicenseCancellationService getGlobalLicenseCancellationService() {
        return globalLicenseCancellationService;
    }

    /**
     * sets LicenseCancellationSetService
     * @param  licenseCancellationSetService licenseCancellationSetService
     */
    @Required
    public void setLicenseCancellationSetService(LicenseCancellationSetService licenseCancellationSetService) {
        this.licenseCancellationSetService = licenseCancellationSetService;
    }

    /**
     * retrieves LicenseCancellationSetService
     * @return LicenseCancellationSetService
     */
    public LicenseCancellationSetService getLicenseCancellationSetService() {
        return licenseCancellationSetService;
    }

    /**
     * retrieves ServicesRequestHDRService
     * @param servicesRequestHDRService servicesRequestHDRService
     */
    @Required
    public void setServicesRequestHDRService(ServicesRequestHDRService servicesRequestHDRService) {
        this.servicesRequestHDRService = servicesRequestHDRService;
    }

    /**
     * sets FollowUpService
     * @param followUpService followUpService
     */
    @Required
    public void setFollowUpService(final FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    /**
     * retrieves FollowUpService
     * @return FollowUpService
     */
    FollowUpService getFollowUpService() {
        return followUpService;
    }

    @Required
    public void setLicenseReplacementService(LicenseReplacementService licenseReplacementService) {
        this.licenseReplacementService = licenseReplacementService;
    }

    /**
     * retrieves LicenseReplacementService
     * @return LicenseReplacementService
     */
    LicenseReplacementService getLicenseReplacementService() {
        return licenseReplacementService;
    }

    /**
     * sets s SagiaRegionCityService
     * @param sagiaRegionCityService sagiaRegionCityService
     */
    @Required
    public void setSagiaRegionCityService(SagiaRegionCityService sagiaRegionCityService) {
        this.sagiaRegionCityService = sagiaRegionCityService;
    }

    /**
     * sets s SagiaRealEstateService
     * @param sagiaRealEstateService sagiaRealEstateService
     */
    @Required
    public void setSagiaRealEstateService(SagiaRealEstateService sagiaRealEstateService) {
        this.sagiaRealEstateService = sagiaRealEstateService;
    }

    /**
     * Dashboard data from CRM
     *
     * @return - HomeHDR entity from CRM containing most of dashboard data
     */
    public HomeHDRData getHomeHDRData() {
        return homeHDRService.get();
    }

    /**
     * retrieves SurveyHDRData
     * @param id id
     * @return SurveyHDRData
     */
    public SurveyHDRData getSurveyHDRData(String id) {
        return surveyHDRService.get(id);
    }

    /**
     * sends SurveyResults
     * @param data data
     */
    public void sendSurveyResults(SurveyResultData data) {
        surveyHDRService.sendSurveyResults(data);
    }

    /**
     * retrieves s all the appointmens from the CRM
     *
     * @return Collection of AppointmentData
     */
    public Collection<AppointmentData> getAppointments() {
        return appointmentService.getCollection();
    }

    /**
     * retrieves s the appointment information from the CRM
     *
     * @param id id
     * @return AppointmentData
     */
    public AppointmentData getAppointment(Integer id) {
        return appointmentService.get(id);
    }

    /**
     * Loads the appointment drop down elements
     *
     * @return Collection
     */
    public Collection<CustomizingGetData> getAppointmentOptions() {
        return customizationListService.getCollection();
    }

    /**
     * Loads the time slots for an appointment
     *
     * @param calendarSlotData calendarSlotData
     * @return Collection of CalendarSlotData
     */
    public Collection<CalendarSlotData> getCalendarSlots(CalendarSlotData calendarSlotData) {
        return calendarSlotService.getCollection(calendarSlotData);
    }

    /**
     * retrieves AmendHeaders
     * @return Collection of AmendHeaderData
     */
    public Collection<AmendHeaderData> getAmendHeaders() {
        return amendHeadersService.getCollection();
    }

    /**
     * retrieves  the GovtHeader without its sub-branches
     *
     * @param entityId - Entity id for which the GovtHeader will be retrieved.
     *                 Used for Government Documents process.
     * @return - GovtHeader(Main branch).
     */
    public GovtHeaderData getGovtHeader(String entityId) {
        return govtHeaderService.getGovtHeader(entityId);
    }

    /**
     * retrieves  the GovtHeader with its sub-branches
     *
     * @param entityId - Entity id for which the GovtHeader will be retrieved.
     *                 Used for Government Documents process.
     * @return - GovtHeader(Main branch) which contains its sub-branches.
     */
    public GovtHeaderData getGovtHeaderWithBranchSet(String entityId) {
        return govtHeaderService.getGovtHeaderWithBranchSet(entityId);
    }

    /**
     * retrieves  the values for the Amanah dropdown
     *
     * @return - A collection of Government items
     */
    public Collection<GovtDropdown> getGovtDropdowns() {
        return amanahService.getCollection();
    }

    /**
     * sends ServicesReqHDRService
     * @param serviceReqData serviceReqData
     * @return String
     */
    public String sendServicesReqHDRService(ServiceReqData serviceReqData) {
        return servicesReqHDRService.save(serviceReqData);
    }

    /**
     * retrieves ServicesReqHDRService
     * @return ServiceReqHDRService
     */
    public ServiceReqHDRService getServicesReqHDRService() {
        return servicesReqHDRService;
    }

    /**
     * retrieves ServicesReqHDRService
     * @param servicesReqHDRService servicesReqHDRService
     */
    public void setServicesReqHDRService(ServiceReqHDRService servicesReqHDRService) {
        this.servicesReqHDRService = servicesReqHDRService;
    }

    /**
     * retrieves WarningLettersInfosService
     * @return WarningLettersInfosService
     */
    public WarningLettersInfosService getWarningLettersInfosService() {
        return warningLettersInfosService;
    }

    /**
     * sets s WarningLettersInfosService
     * @param warningLettersInfosService warningLettersInfosService
     */
    public void setWarningLettersInfosService(final WarningLettersInfosService warningLettersInfosService) {
        this.warningLettersInfosService = warningLettersInfosService;
    }

    /**
     * retrieves FinancialEntities
     * @return - A collection of Financial entities retrieved from CRM
     */
    public Collection<FinanceHDRS> getFinancialEntities() {
        return financialService.getFinancialEntities();
    }

    /**
     * retrieves FinancialData
     * @param id - The id of the financial entity
     * @return - The financial entity with that id
     */
    public FinanceHDRS getFinancialData(Object id) {
        return financialService.getFinancialData(id);
    }

    /**
     * retrieves ServicesRequestHDRServiceData
     * @return Collection of ServiceRequestHDRsData
     */
    public Collection<ServiceRequestHDRsData> getServicesRequestHDRServiceData() {
        return servicesRequestHDRService.getCollection();
    }

    /**
     * retrieves ServiceRequestData
     * @param id id
     * @return ServiceRequestHDRsData
     */
    public ServiceRequestHDRsData getServiceRequestData(String id) {
        return servicesRequestHDRService.get(id);
    }

    public ServiceRequestHDRsData getServiceRequestData() {
        return servicesRequestHDRService.get("");
    }

    /**
     * retrieves LatestServiceRequestData
     * @return ServiceRequestHDRsData
     */
    public ServiceRequestHDRsData getLatestServiceRequestData() {
        return getServicesRequestHDRServiceData().stream().findFirst().orElse(null);
    }

    /**
     * retrieves  list of RegionCity.
     *
     * @return list of RegionCity.
     */
    public Collection<RegionCityData> getRegionCityData() {
        return sagiaRegionCityService.getCollection();
    }

    /**
     * retrieves  all cities for a given region id.
     *
     * @param regionId regionId
     * @return all cities for a given region id.
     */
    public Collection<RegionCityData> getCities(String regionId) {
        return sagiaRegionCityService.getCities(regionId);
    }

    /**
     * retrieves s a collection of RealEstateData objects.
     *
     * @return collection of RealEstateData objects.
     */
    public Collection<RealEstateData> getRealEstateHistoryData() {
        return sagiaRealEstateService.getRealEstateHistory();
    }

    /**
     * retrieves  a Real Estate object based on a given id.
     *
     * @param realEstateId realEstateId
     * @return Real Estate object based on a given id.
     */
    public RealEstateData getRealEstateById(String realEstateId) {
        return sagiaRealEstateService.getRealEstateById(realEstateId);
    }

    /**
     * retrieves LatestRealEstate
     * @return RealEstateData
     */
    public RealEstateData getLatestRealEstate() {
        return sagiaRealEstateService.getLatestRealEstate();
    }

    /**
     * creates ServiceRequestHDR
     * @param serviceRequestHDRsData serviceRequestHDRsData
     */
    public void createServiceRequestHDR(ServiceRequestHDRsData serviceRequestHDRsData) {
        servicesRequestHDRService.save(serviceRequestHDRsData);
    }

    /**
     * fills DocumentsFromServiceBPHDR
     * @param generalManagerData generalManagerData
     * @param companyRepresentativeData companyRepresentativeData
     */
    public void fillDocumentsFromServiceBPHDR(ProfileGeneralManagerData generalManagerData, List<ProfileCompanyRepresentativeData> companyRepresentativeData) {
        serviceBpHDRService.fillDocumentsFromServiceBPHDR(generalManagerData, companyRepresentativeData);
    }

    /**
     * Update company contacts.
     * @param contactUpdateForm - The contact update form.
     */
    public void updateContacts(final ContactUpdateForm contactUpdateForm) {
        serviceBpHDRService.createUpdateRequest(contactUpdateForm);
    }

    /**
     * reads Attachment
     * @param objectId objectId
     * @param documentId documentId
     * @return InputStream
     */
    public InputStream readAttachment(final String objectId, final String documentId) {
        return homeHDRService.readAttachmentFile(objectId, documentId);
    }

    /**
     * retrieves ViolationRepliesEntries
     * @return Collection
     */
    public Collection<FollowUpServicesData> getViolationRepliesEntries() {
        return getFollowUpService().getViolationRepliesEntries();
    }

    /**
     * retrieves WarningLetterExtensionEntries
     * @return Collection
     */
    public Collection<FollowUpServicesData> getWarningLetterExtensionEntries() {
        return getFollowUpService().getWarningLetterExtensionEntries();
    }

    /**
     * retrieves FollowUpSingleEntry
     * @param id id
     * @return FollowUpServicesData
     */
    public FollowUpServicesData getFollowUpSingleEntry(final Object id) {
        return getFollowUpService().getSingleEntry(id);
    }

    /**
     *
     * creates ViolationReply
     * @param form from
     * @throws SagiaCRMCreateException exception
     */
    public void createViolationReply(final CreateViolationReplyFormData form)
        throws SagiaCRMCreateException{
        final Collection<WarningLettersInfos> warningLettersInfos
                = getWarningLettersInfosService().getWarningLettersViolationReplies();
        Optional<WarningLettersInfos> letter = warningLettersInfos
        		.stream()
        		.filter(info -> StringUtils.equals(info.getWarningLetterNo(), form.getLetterNumber()))
        		.findFirst();
        if (!letter.isPresent()) {
            throw new SagiaCRMCreateException();
        }
        getFollowUpService().createViolationReply(letter.get(), form);
    }

    /**
     * creates WarningLetterExtension
     * @param form form
     * @throws SagiaCRMCreateException exception
     */
    public void createWarningLetterExtension(final CreateWarningLetterExtensionFormData form)
            throws SagiaCRMCreateException
    {
        final Collection<WarningLettersInfos> warningLettersInfos
                = getWarningLettersInfosService().getWarningLettersWarningLetterExtension();

        WarningLettersInfos letter = null;
        for (WarningLettersInfos info : warningLettersInfos) {
            if (info.getWarningLetterNo().equals(form.getLetterNumber())) {
                letter = info;
                break;
            }
        }

        if (letter == null) {
            throw new SagiaCRMCreateException();
        }

        getFollowUpService().createWarningLetterExtension(letter, form);
    }
    /**
     * retrieves  GlobalLicenseCancellation object based on a given id and stage.
     *
     * @param id id
     * @param stage stage
     * @return GlobalLicenseCancellation on a given id and stage..
     */
    public GlobalLicenseCancellation getGlobalLicenseCancellation(String id, String stage) {
        return getGlobalLicenseCancellationService().getGlobalLicenseCancellation(id, stage);
    }

    /**
     * retrieves LatestLicenceCancellationCreated
     * @return LicenseCancellationSetData
     */
    public LicenseCancellationSetData getLatestLicenceCancellationCreated() {
        return getLicenseCancellationSetService().getLatestEntityCreated();
    }

    /**
     * updates GlobalLicenseCancellation
     * @param srID srID
     * @param stage stage
     * @param licenseClearanceSetData licenseClearanceSetData
     */
    public void updateGlobalLicenseCancellation(String srID, String stage, LicenseClearanceSetData licenseClearanceSetData) {
        getLicenseClearanceSetService().updateGlobalLicenseCancellationData(srID, stage, licenseClearanceSetData);
    }
    /**
     * retrieves  LicenseClearanceSetData object based on a given id and stage.
     *
     * @param id id
     * @param stage stage
     * @return LicenseClearanceSetData on a given id and stage..
     */

    public LicenseClearanceSetData getLicenseClearanceSetData(String id, String stage) {
        return getLicenseClearanceSetService().get(id, stage);
    }
    /**
     * retrieves  LicenseCancellationSetData object based on a given id and stage.
     *
     * @param id id
     * @param stage stage
     * @return LicenseCancellationSetData on a given id and stage..
     */
    public LicenseCancellationSetData getLicenseCancellationSetData(String id, String stage) {
        return getLicenseCancellationSetService().get(id, stage);
    }
    /**
     * retrieves s a collection of CustomizingGetData objects, used on LicenseClearanceSet (ZSR9)
     *
     * @return collection of CustomizingGetData objects.
     */
    public Collection<CustomizingGetData> readClearSetAttachments() {
        return customizationListService.readClearSetAttachments();
    }

    /**
     * retrieves s a collection of CustomizingGetData objects, used on LicenseCancellationSet (ZSR6)
     *
     * @return collection of CustomizingGetData objects.
     */

    public Collection<CustomizingGetData> readCancelLicenseSupportingAttachments()  {
        return customizationListService.readCancelLicenseSupportingAttachments();
    }

    /**
     * Save the Govt Header Data to CRM (Main branch with its branches)
     * @param data data
     * @return GovtHeaderData
     */
    public GovtHeaderData saveGovtHeaderData(GovtHeaderData data) {
        return getGovtHeaderService().saveAndParseResponse(data, GovtHeaderData.class);
    }

    /**
     * Saves an appointment
     *
     * @param appointmentData appointmentData
     * @return AppointmentData
     */
    public AppointmentData saveAppointment(AppointmentData appointmentData) {
        if(appointmentData.getApptID() != null){
            return appointmentService.saveAppointment(appointmentData, appointmentData.getApptID());
        } else {
            return appointmentService.saveAppointment(appointmentData);
        }
    }

    /**
     * prints AppointmentDetails
     * @param id id
     * @return InputStream
     */
    public InputStream printAppointmentDetails(String id){
        return appointmentService.printAppointmentDetails(id);
    }

    public Collection<ServiceBpHDRData> getContactUpdateHistory(){
        return serviceBpHDRService.getAll();
    }

    /**
     * Return a contact update history entry.
     * @param id - The id for which the history entry is retrieved.
     * @return - The history entry with that specific id.
     */
    public ServiceBpHDRData getHistoryEntry(String id){
        return serviceBpHDRService.getHistoryEntry(id);
    }

    public void setGlobalValsService(GlobalValsService globalValsService) {
        this.globalValsService = globalValsService;
    }

    /**
     * Checks if the contact update request may be completed.
     */
    public void checkContactUpdateAvailability(){
        globalValsService.checkContactUpdateAvailability();
    }

    public void setCompanyInformationService(CompanyInformationService companyInformationService) {
        this.companyInformationService = companyInformationService;
    }

    public BasicCompanyData getCompanyInformation(){
        return companyInformationService.getBasicInformation();
    }

    public void setRealEstateEntityDetailsSetService(SagiaRealEstateEntityDetailsSetService realEstateEntityDetailsSetService) {
        this.realEstateEntityDetailsSetService = realEstateEntityDetailsSetService;
    }


    public Collection<RealEstateEntityDetailsSetData> getRealEstateEntityDetailsSet(){
      return realEstateEntityDetailsSetService.getRealEstateEntityDetailsSet();
    }

    public void setCheckGovDocService(CheckGovernmentDocService checkGovDocService) {
        this.checkGovDocService = checkGovDocService;
    }

    /**
     * Check government documents for License Renewal
     * @return - Government Documents Check response object
     */
    public GovtDocCheck checkGovernmentDocuments(){
        return checkGovDocService.checkGovernmentDocuments();
    }

    public void setRemovePopupALRService(RemovePopupALRService removePopupALRService) {
        this.removePopupALRService = removePopupALRService;
    }

    /**
     * Removes the License renewal popup from dashboard
     * @return Response object
     */
    public RemovePopupALR removePopupALR(){
        return removePopupALRService.removePopupALR();
    }
}
