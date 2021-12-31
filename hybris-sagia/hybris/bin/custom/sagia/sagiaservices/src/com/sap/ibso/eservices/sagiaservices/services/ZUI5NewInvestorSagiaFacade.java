package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.data.nip.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.NationalInvestorAppointmentData;
import com.sap.ibso.eservices.sagiaservices.services.impl.*;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.springframework.beans.factory.annotation.Required;

import java.time.LocalDate;
import java.util.Collection;

/**
 * This class is used to handle the National Investor Registration.
 * It handles both scenarios, with and without CR Number.
 * In the NO CR Number scenario it creates an appointment.
 * In the WITH CR Number scenario it registers the investor as a national investor.
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ZUI5NewInvestorSagiaFacade {

    private ApptAvailSet apptAvailSetService;

    private NipAppointmentSetService nipAppointmentSetService;
    private NipService nipService;
    private NipLegalStatusService nipLegalStatusService;
    private NipCountryService nipCountryService;
    private NipRegionService nipRegionService;
    private NipCityService nipCityService;
    private NipISICSectionService nipISICSectionService;
    private NipISICDivisionService nipISICDivisionService;
    private I18NService i18NService;


    @Required
    public void setApptAvailSetService(ApptAvailSet apptAvailSetService) {
        this.apptAvailSetService = apptAvailSetService;
    }

    @Required
    public void setNipAppointmentSetService(NipAppointmentSetService nipAppointmentSetService) {
        this.nipAppointmentSetService = nipAppointmentSetService;
    }

    @Required
    public void setNipService(NipService nipService) {
        this.nipService = nipService;
    }

    @Required
    public void setNipLegalStatusService(NipLegalStatusService nipLegalStatusService) {
        this.nipLegalStatusService = nipLegalStatusService;
    }

    @Required
    public void setNipCountryService(NipCountryService nipCountryService) {
        this.nipCountryService = nipCountryService;
    }

    @Required
    public void setNipRegionService(NipRegionService nipRegionService) {
        this.nipRegionService = nipRegionService;
    }

    @Required
    public void setNipCityService(NipCityService nipCityService) {
        this.nipCityService = nipCityService;
    }

    @Required
    public void setNipISICSectionService(NipISICSectionService nipISICSectionService) {
        this.nipISICSectionService = nipISICSectionService;
    }

    @Required
    public void setNipISICDivisionService(NipISICDivisionService nipISICDivisionService) {
        this.nipISICDivisionService = nipISICDivisionService;
    }

    private String getLanguage(){
        return i18NService.getCurrentLocale().getLanguage().substring(0, 1).toUpperCase();
    }

    /**
     * Loads the available time slots for a given date and branch through the service.
     * @param selectedDate selectedDate
     * @param branch branch
     * @return Collection of CalendarSlotData
     */
    public Collection<CalendarSlotData> getNewInvestorCalendarSlot(LocalDate selectedDate, String branch) {
        return apptAvailSetService.getCollection(selectedDate, branch);
    }

    /**
     * Calls the National Investor service, to load the investor information based on the CR number
     * @param crNumber crNumber
     * @return Collection of NIPHeaderSetData
     */
    public Collection<NIPHeaderSetData> getNationalInvestorHeaderSet(String crNumber){
        return nipService.get(crNumber);
    }

    /**
     * Creates a new appointment for an investor registration without a CR number
     * @param nationalInvestorAppointmentData nationalInvestorAppointmentData
     * @return NationalInvestorAppointmentData
     */
    public NationalInvestorAppointmentData createNewInvestorDataAppointment(NationalInvestorAppointmentData nationalInvestorAppointmentData){
        return nipAppointmentSetService.create(nationalInvestorAppointmentData);
    }

    /**
     * saves NationalInvestor
     * @param nipHeaderSetData nipHeaderSetData
     * @return NIPHeaderSetData
     */
    public NIPHeaderSetData saveNationalInvestor(NIPHeaderSetData nipHeaderSetData){
        return nipService.saveAndParseResponse(nipHeaderSetData, NIPHeaderSetData.class);
    }

    /**
     * Loads the legal statuses from the CRM through the service
     * @return Collection of NIPLegalStatusSetData
     */
    public Collection<NIPLegalStatusSetData> getLegalStatusCollection(){
        return nipLegalStatusService.getCollection(getLanguage());
    }

    /**
     * Loads the countries list from the CRM through the service
     * @return Collection of NIPCountrySetData
     */
    public Collection<NIPCountrySetData> getCountryCollection(){
        return nipCountryService.getCollection(getLanguage());
    }

    /**
     * Loads the countries list from the CRM through the service filtered out by nationality type (SAUDI or GCC)
     * @param nationalityType nationalityType
     * @return Collection of NIPCountrySetData
     */
    public Collection<NIPCountrySetData> getCountryCollection(String nationalityType){
        return nipCountryService.getCollection(nationalityType, getLanguage());
    }

    /**
     * Loads the regions list from the CRM through the service, filtered out by country code.
     * @param countryCode countryCode
     * @return Collection of NIPRegionSetData
     */
    public Collection<NIPRegionSetData> getRegionCollection(String countryCode){
        return nipRegionService.getCollection(countryCode, getLanguage());
    }

    /**
     * Loads the cities list from the CRM through the service.
     * @return Collection of NIPCitySetData
     */
    public Collection<NIPCitySetData> getCityCollection(){
        return nipCityService.getCollection(getLanguage());
    }

    /**
     * Loads the cities list from the CRM through the service, filtered out by region code.
     * @param regionCode regionCode
     * @return Collection of NIPCitySetData
     */
    public Collection<NIPCitySetData> getCityCollection(String regionCode){
        return nipCityService.getCollection(regionCode, getLanguage());
    }

    /**
     * Loads the ISIC Sections list from the CRM through the service.
     * @return Collection of NIPISICSectionSetData
     */
    public Collection<NIPISICSectionSetData> getISICSectionCollection(){
        return nipISICSectionService.getCollection(getLanguage());
    }

    /**
     * Loads the ISIC Divisions list from the CRM through the service.
     * @return Collection of NIPISICDivisionSetData
     */
    public Collection<NIPISICDivisionSetData> getISICDivisionCollection(){
        return nipISICDivisionService.getCollection(getLanguage());
    }

    /**
     * Loads the ISIC Divisions list from the CRM through the service, filtered out by ISIC Section code.
     * @param sectionCode sectionCode
     * @return Collection of NIPISICDivisionSetData
     */
    public Collection<NIPISICDivisionSetData> getISICDivisionCollection(String sectionCode){
        return nipISICDivisionService.getCollection(sectionCode, getLanguage());
    }

    public void setI18NService(I18NService i18NService) {
        this.i18NService = i18NService;
    }
}
