package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.appintments.NationalInvestorAppointment;
import com.sap.ibso.eservices.facades.populators.*;
import com.sap.ibso.eservices.facades.sagia.SagiaNationalInvestorFacade;
import com.sap.ibso.eservices.sagiaservices.data.nip.*;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CalendarSlotData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.NationalInvestorAppointmentData;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5NewInvestorSagiaFacade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * DefaultSagiaNationalInvestorFacade
 */
public class DefaultSagiaNationalInvestorFacade implements SagiaNationalInvestorFacade {

    ZUI5NewInvestorSagiaFacade zui5NewInvestorSagiaFacade;

    CalendarSlotPopulator calendarSlotPopulator;

    NationalInvestorAppointmentReversePopulator nationalInvestorAppointmentReversePopulator;
    NationalInvestorAppointmentPopulator nationalInvestorAppointmentPopulator;

    NIPHeaderSetPopulator nipHeaderSetPopulator;

    NIPHeaderSetReversePopulator nipHeaderSetReversePopulator;

    NipLegalStatusPopulator sagiaNipLegalStatusPopulator;

    NipCountryPopulator sagiaNipCountryPopulator;

    NipRegionPopulator sagiaNipRegionPopulator;

    NipCityPopulator sagiaNipCityPopulator;

    NipISICSectionPopulator sagiaNipISICSectionPopulator;

    NipISICDivisionPopulator sagiaNipISICDivisionPopulator;

    NipAttachmentPopulator sagiaNIPAttachmentPopulator;

    /**
     * Loads available appointment calendar slots.
     * Used in National Investor Registration WITHOUT CR Number scenario.
     * @param selectedDate
     * @param branch
     * @return
     */
    @Override
    public Collection<CalendarSlot> getNewInvestorCalendarSlots(LocalDate selectedDate, String branch) {

        Collection<CalendarSlotData> calendarSlotDataCollection = zui5NewInvestorSagiaFacade.getNewInvestorCalendarSlot(selectedDate, branch);
        Collection<CalendarSlot> result = new ArrayList<>();
        for (CalendarSlotData item : calendarSlotDataCollection) {
            CalendarSlot calendarSlotItem = new CalendarSlot();
            calendarSlotPopulator.populate(item, calendarSlotItem);
            result.add(calendarSlotItem);
        }
        return result;
    }

    @Override
    public NationalInvestorAppointment createAppointment(NationalInvestorAppointment appointment) {
        NationalInvestorAppointmentData nationalInvestorAppointmentData = new NationalInvestorAppointmentData();
        nationalInvestorAppointmentReversePopulator.populate(appointment, nationalInvestorAppointmentData);
        NationalInvestorAppointmentData savedInvestorAppointmentData = zui5NewInvestorSagiaFacade.createNewInvestorDataAppointment(nationalInvestorAppointmentData);
        NationalInvestorAppointment savedAppointment = new NationalInvestorAppointment();
        nationalInvestorAppointmentPopulator.populate(savedInvestorAppointmentData, savedAppointment);
        return savedAppointment;
    }

    /**
     * Loads legal statuses list.
     * Used in National Investor Registration WITH CR Number scenario.
     * @return Collection of NIPLeagalStatusSet
     */
    public Collection<NIPLeagalStatusSet> getLegalStatuses() {
        Collection<NIPLegalStatusSetData> statuses = zui5NewInvestorSagiaFacade.getLegalStatusCollection();
        Collection<NIPLeagalStatusSet> result = new ArrayList<>();
        for (NIPLegalStatusSetData item : statuses) {
            NIPLeagalStatusSet leagalStatusSet = new NIPLeagalStatusSet();
            sagiaNipLegalStatusPopulator.populate(item, leagalStatusSet);
            result.add(leagalStatusSet);
        }
        return result;
    }

    /**
     * Loads countries list.
     * Used in National Investor Registration WITH CR Number scenario.
     * @return Collection of NIPCountrySet
     */
    public Collection<NIPCountrySet> getCountries() {
        return getCountries(null);
    }

    /**
     * Loads countries list for a given nationality.
     * Used in National Investor Registration WITH CR Number scenario.
     * @param nationalityType nationalityType
     * @return Collection of NIPCountrySet
     */
    public Collection<NIPCountrySet> getCountries(String nationalityType) {
        Collection<NIPCountrySetData> countries;
        if (nationalityType == null) {
            countries = zui5NewInvestorSagiaFacade.getCountryCollection();
        } else {
            countries = zui5NewInvestorSagiaFacade.getCountryCollection(nationalityType);
        }
        Collection<NIPCountrySet> result = new ArrayList<>();
        for (NIPCountrySetData item : countries) {
            NIPCountrySet country = new NIPCountrySet();
            sagiaNipCountryPopulator.populate(item, country);
            result.add(country);
        }
        return result;
    }

    /**
     * Loads countries list for a given country code.
     * Used in National Investor Registration WITH CR Number scenario.
     * @param countryCode countryCode
     * @return Collection of NIPRegionSet
     */
    public Collection<NIPRegionSet> getRegions(String countryCode) {
        Collection<NIPRegionSetData> regions = zui5NewInvestorSagiaFacade.getRegionCollection(countryCode);
        Collection<NIPRegionSet> result = new ArrayList<>();
        for (NIPRegionSetData item : regions) {
            NIPRegionSet region = new NIPRegionSet();
            sagiaNipRegionPopulator.populate(item, region);
            result.add(region);
        }
        return result;
    }

    /**
     * Loads the list of cities.
     * Used in National Investor Registration WITH CR Number scenario.
     * @return Collection of NipCitySet
     */
    public Collection<NipCitySet> getCities() {
        return getCities(null);
    }

    /**
     * Loads the list of cities for a given region code.
     * Used in National Investor Registration WITH CR Number scenario.
     * @param regionCode regionCode
     * @return Collection of NipCitySet
     */
    public Collection<NipCitySet> getCities(String regionCode) {
        Collection<NIPCitySetData> cities;
        if (regionCode == null) {
            cities = zui5NewInvestorSagiaFacade.getCityCollection();
        } else {
            cities = zui5NewInvestorSagiaFacade.getCityCollection(regionCode);
        }

        Collection<NipCitySet> result = new ArrayList<>();
        for (NIPCitySetData item : cities) {
            NipCitySet city = new NipCitySet();
            sagiaNipCityPopulator.populate(item, city);
            result.add(city);
        }
        return result;
    }

    /**
     * Loads the list of ISIC Sections.
     * Used in National Investor Registration WITH CR Number scenario.
     * @return Collection of NipISICSectionSet
     */
    public Collection<NipISICSectionSet> getISICSections() {
        Collection<NIPISICSectionSetData> nipisicSectionSetData = zui5NewInvestorSagiaFacade.getISICSectionCollection();
        Collection<NipISICSectionSet> nipISICSectionSets = new ArrayList<>();
        for (NIPISICSectionSetData item : nipisicSectionSetData) {
            NipISICSectionSet section = new NipISICSectionSet();
            sagiaNipISICSectionPopulator.populate(item, section);
            nipISICSectionSets.add(section);
        }
        return nipISICSectionSets;
    }

    /**
     * Loads the list of ISIC Divisions.
     * Used in National Investor Registration WITH CR Number scenario.
     * @return Collection of NipISICDivisionSet
     */
    public Collection<NipISICDivisionSet> getISICDivisions() {
        return getISICDivisions(null);
    }

    /**
     * Loads the list of ISIC Divisions for a given ISIC Section.
     * Used in National Investor Registration WITH CR Number scenario.
     * @param sectionCode sectionCode
     * @return Collection of NipISICDivisionSet
     */
    public Collection<NipISICDivisionSet> getISICDivisions(String sectionCode) {
        Collection<NIPISICDivisionSetData> nipisicDivisionSetData;
        if (sectionCode == null) {
            nipisicDivisionSetData = zui5NewInvestorSagiaFacade.getISICDivisionCollection();
        } else {
            nipisicDivisionSetData = zui5NewInvestorSagiaFacade.getISICDivisionCollection(sectionCode);
        }
        Collection<NipISICDivisionSet> nipISICDivisionSetCollection = new ArrayList<>();
        for (NIPISICDivisionSetData item : nipisicDivisionSetData) {
            NipISICDivisionSet division = new NipISICDivisionSet();
            sagiaNipISICDivisionPopulator.populate(item, division);
            nipISICDivisionSetCollection.add(division);
        }
        return nipISICDivisionSetCollection;
    }

    /**
     * Loads the investor information given a CR Number
     * Used in National Investor Registration WITH CR Number scenario.
     * @param crNumber crNumber
     * @return NationalInvestorHeaderSet
     */
    @Override
    public NationalInvestorHeaderSet getNationalInvestorHeaderSet(String crNumber) {
        Collection<NIPHeaderSetData> nipHeaderSetData = zui5NewInvestorSagiaFacade.getNationalInvestorHeaderSet(crNumber);
        NIPHeaderSetData nipHeaderSetDataItem = new NIPHeaderSetData();
        if(nipHeaderSetData.stream().findFirst().isPresent()){
            nipHeaderSetDataItem = nipHeaderSetData.stream().findFirst().get();
        }
        NationalInvestorHeaderSet nationalInvestorHeaderSet = new NationalInvestorHeaderSet();
        nipHeaderSetPopulator.populate(nipHeaderSetDataItem, nationalInvestorHeaderSet);
        return nationalInvestorHeaderSet;
    }

    /**
     * Saves the National Investor Registration data
     * Used in National Investor Registration WITH CR Number scenario.
     * @param  nationalInvestorHeaderSet nationalInvestorHeaderSet
     * @return NationalInvestorHeaderSet
     */
    @Override
    public NationalInvestorHeaderSet saveNationalInvestor(NationalInvestorHeaderSet nationalInvestorHeaderSet) {
        NIPHeaderSetData nipHeaderSetData = new NIPHeaderSetData();
        nipHeaderSetReversePopulator.populate(nationalInvestorHeaderSet, nipHeaderSetData);
        NIPHeaderSetData response = zui5NewInvestorSagiaFacade.saveNationalInvestor(nipHeaderSetData);
        NationalInvestorHeaderSet responseEntity = new NationalInvestorHeaderSet();
        nipHeaderSetPopulator.populate(response, responseEntity);
        return responseEntity;
    }

    /**
     * @param nationalInvestorAppointmentReversePopulator
     */
    public void setNationalInvestorAppointmentReversePopulator(NationalInvestorAppointmentReversePopulator nationalInvestorAppointmentReversePopulator) {
        this.nationalInvestorAppointmentReversePopulator = nationalInvestorAppointmentReversePopulator;
    }

    public void setNationalInvestorAppointmentPopulator(NationalInvestorAppointmentPopulator nationalInvestorAppointmentPopulator) {
        this.nationalInvestorAppointmentPopulator = nationalInvestorAppointmentPopulator;
    }

    /**
     * @param zui5NewInvestorSagiaFacade
     */
    public void setZui5NewInvestorSagiaFacade(ZUI5NewInvestorSagiaFacade zui5NewInvestorSagiaFacade) {
        this.zui5NewInvestorSagiaFacade = zui5NewInvestorSagiaFacade;
    }

    /**
     * @param calendarSlotPopulator
     */
    public void setCalendarSlotPopulator(CalendarSlotPopulator calendarSlotPopulator) {
        this.calendarSlotPopulator = calendarSlotPopulator;
    }

    /**
     * @param nipHeaderSetPopulator
     */
    public void setNipHeaderSetPopulator(NIPHeaderSetPopulator nipHeaderSetPopulator) {
        this.nipHeaderSetPopulator = nipHeaderSetPopulator;
    }

    /**
     *
     * @param nipHeaderSetReversePopulator
     */
    public void setNipHeaderSetReversePopulator(NIPHeaderSetReversePopulator nipHeaderSetReversePopulator) {
        this.nipHeaderSetReversePopulator = nipHeaderSetReversePopulator;
    }

    /**
     * @param sagiaNipLegalStatusPopulator
     */
    public void setSagiaNipLegalStatusPopulator(NipLegalStatusPopulator sagiaNipLegalStatusPopulator) {
        this.sagiaNipLegalStatusPopulator = sagiaNipLegalStatusPopulator;
    }

    /**
     * @param sagiaNipCountryPopulator
     */
    public void setSagiaNipCountryPopulator(NipCountryPopulator sagiaNipCountryPopulator) {
        this.sagiaNipCountryPopulator = sagiaNipCountryPopulator;
    }

    /**
     * @param sagiaNipRegionPopulator
     */
    public void setSagiaNipRegionPopulator(NipRegionPopulator sagiaNipRegionPopulator) {
        this.sagiaNipRegionPopulator = sagiaNipRegionPopulator;
    }

    /**
     * @param sagiaNipCityPopulator
     */
    public void setSagiaNipCityPopulator(NipCityPopulator sagiaNipCityPopulator) {
        this.sagiaNipCityPopulator = sagiaNipCityPopulator;
    }

    /**
     * @param sagiaNipISICSectionPopulator
     */
    public void setSagiaNipISICSectionPopulator(NipISICSectionPopulator sagiaNipISICSectionPopulator) {
        this.sagiaNipISICSectionPopulator = sagiaNipISICSectionPopulator;
    }

    /**
     * @param sagiaNipISICDivisionPopulator
     */
    public void setSagiaNipISICDivisionPopulator(NipISICDivisionPopulator sagiaNipISICDivisionPopulator) {
        this.sagiaNipISICDivisionPopulator = sagiaNipISICDivisionPopulator;
    }

    /**
     * @param sagiaNIPAttachmentPopulator
     */
    public void setSagiaNIPAttachmentPopulator(NipAttachmentPopulator sagiaNIPAttachmentPopulator) {
        this.sagiaNIPAttachmentPopulator = sagiaNIPAttachmentPopulator;
    }
}
