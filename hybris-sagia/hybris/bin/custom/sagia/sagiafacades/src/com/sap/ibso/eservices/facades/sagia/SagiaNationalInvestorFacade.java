package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.*;
import com.sap.ibso.eservices.facades.data.appintments.NationalInvestorAppointment;

import java.time.LocalDate;
import java.util.Collection;

/**
 * SagiaNationalInvestorFacade
 */
public interface SagiaNationalInvestorFacade {
    /**
     * Loads the available time slots for a given date and branch.
     * @param selectedDate selectedDate
     * @param branch branch
     * @return Collection of CalendarSlot
     */
    Collection<CalendarSlot> getNewInvestorCalendarSlots(LocalDate selectedDate, String branch);

    /**
     * Creates an appointment for a National Investor that has no CR number.
     * @param appointment appointment
     * @return NationalInvestorAppointment
     */
    NationalInvestorAppointment createAppointment(NationalInvestorAppointment appointment);

    /**
     * Loads the legal statuses needed to populate the register form for national investor with CR number.
     * @return Collection of NIPLeagalStatusSet
     */
    Collection<NIPLeagalStatusSet> getLegalStatuses();

    /**
     * Loads the country list needed to populate the register form for national investor with CR number.
     * @return Collection of NIPCountrySet
     */
    Collection<NIPCountrySet> getCountries();

    /**
     * Loads the country list needed to populate the register form for national investor with CR number.
     * This is loaded based on the selected nationality
     * @param nationalityType nationalityType
     * @return Collection of NIPCountrySet
     */
    Collection<NIPCountrySet> getCountries(String nationalityType);

    /**
     * Loads the regions list needed to populate the register form for national investor with CR number.
     * The list is loaded based on the country code
     * @param countryCode countryCode
     * @return Collection of NIPRegionSet
     */
    Collection<NIPRegionSet> getRegions(String countryCode);

    /**
     * Loads the city list needed to populate the register form for national investor with CR number.
     * This method returns the entire list of cities
     * @return Collection of NipCitySet
     */
    Collection<NipCitySet> getCities();
    /**
     * Loads the city list needed to populate the register form for national investor with CR number.
     * This method returns the list of cities filtered by region code.
     * @param regionCode regionCode
     * @return Collection of NipCitySet
     */
    Collection<NipCitySet> getCities(String regionCode);

    /**
     * Loads the ISIC sections list needed to populate the register form for national investor with CR number.
     * @return Collection of NipISICSectionSet
     */
    Collection<NipISICSectionSet> getISICSections();

    /**
     * Loads the ISIC divisions list needed to populate the register form for national investor with CR number.
     * @return Collection of NipISICDivisionSet
     * @deprecated deprecated
     */
    @Deprecated
    Collection<NipISICDivisionSet> getISICDivisions();

    /**
     * Loads the ISIC divisions list needed to populate the register form for national investor with CR number.
     * The list is filtered by ISIC section code
     * @param sectionCode sectionCode
     * @return Collection of NipISICDivisionSet
     */
    Collection<NipISICDivisionSet> getISICDivisions(String sectionCode);

    /**
     * Loads the information for a specific investor based on the CR number
     * @param crNumber crNumber
     * @return NationalInvestorHeaderSet
     */
    NationalInvestorHeaderSet getNationalInvestorHeaderSet(String crNumber);

    /**
     * Registers a new national investor
     * @param nationalInvestorHeaderSet nationalInvestorHeaderSet
     * @return NationalInvestorHeaderSet
     */
    NationalInvestorHeaderSet saveNationalInvestor(NationalInvestorHeaderSet nationalInvestorHeaderSet);
}
