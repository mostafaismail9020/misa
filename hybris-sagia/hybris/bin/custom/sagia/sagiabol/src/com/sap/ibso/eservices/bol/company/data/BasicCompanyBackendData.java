package com.sap.ibso.eservices.bol.company.data;

import de.hybris.platform.sap.core.common.message.Message;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Provides a data transfer object for basic company data from SAP backend system.
 */
public class BasicCompanyBackendData
{
    private List<Message> messages;

    private String companyNameInEnglish;
    private String companyNameInArabic;
    private String businessEmailAddress;
    private String legalStatus;
    private String country;
    private String region;
    private String city;
    private BigDecimal capitalValue;
    private String currencyIso;
    private String isinCode;
    private String isicSection;
    private String isicDevision;

    /**
     * Gets backend messages. If there are no backend message available then an empty list is returned.
     *
     * @return the messages
     */
    public List<Message> getMessages()
    {
        return messages != null && !messages.isEmpty() ? messages : Collections.emptyList();
    }

    /**
     * Sets backend messages.
     *
     * @param messages the messages
     */
    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }

    /**
     * Gets the investor company name in English.
     *
     * @return the company name in English
     */
    public String getCompanyNameInEnglish()
    {
        return companyNameInEnglish;
    }

    /**
     * Sets the investor company name in English.
     *
     * @param companyNameInEnglish the company name in English
     */
    public void setCompanyNameInEnglish(String companyNameInEnglish)
    {
        this.companyNameInEnglish = companyNameInEnglish;
    }

    /**
     * Gets the investor company name in Arabic.
     *
     * @return the company name in Arabic
     */
    public String getCompanyNameInArabic()
    {
        return companyNameInArabic;
    }

    /**
     * Sets the investor company name in Arabic.
     *
     * @param companyNameInArabic the company name in Arabic
     */
    public void setCompanyNameInArabic(String companyNameInArabic)
    {
        this.companyNameInArabic = companyNameInArabic;
    }

    /**
     * Gets the official business email address.
     *
     * @return the business email address
     */
    public String getBusinessEmailAddress()
    {
        return businessEmailAddress;
    }

    /**
     * Sets the official business email address.
     *
     * @param businessEmailAddress the business email address
     */
    public void setBusinessEmailAddress(String businessEmailAddress)
    {
        this.businessEmailAddress = businessEmailAddress;
    }

    /**
     * Gets the language dependent legal status name.
     *
     * @return the legal status
     */
    public String getLegalStatus()
    {
        return legalStatus;
    }

    /**
     * Sets the language dependent legal status name.
     *
     * @param legalStatus the legal status
     */
    public void setLegalStatus(String legalStatus)
    {
        this.legalStatus = legalStatus;
    }

    /**
     * Gets the language dependent country name.
     *
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Sets the language dependent country name.
     *
     * @param country the country
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * Gets the language dependent region name.
     *
     * @return the region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * Sets the language dependent region name.
     *
     * @param region the region
     */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
     * Gets the language dependent city name.
     *
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Sets the language dependent city name.
     *
     * @param city the city
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Gets the capital value.
     *
     * @return the capital value
     */
    public BigDecimal getCapitalValue()
    {
        return capitalValue;
    }

    /**
     * Sets the capital value.
     *
     * @param capitalValue the capital value
     */
    public void setCapitalValue(BigDecimal capitalValue)
    {
        this.capitalValue = capitalValue;
    }

    /**
     * Gets the currency ISO code of the currency related to capital value.
     *
     * @return the currency ISO code
     */
    public String getCurrencyIso()
    {
        return currencyIso;
    }

    /**
     * Sets the currency ISO code of the currency related to capital value.
     *
     * @param currencyIso the currency ISO code
     */
    public void setCurrencyIso(String currencyIso)
    {
        this.currencyIso = currencyIso;
    }

    /**
     * Gets the International Securities Identification Number of the company.
     *
     * @return the ISIN code
     */
    public String getIsinCode()
    {
        return isinCode;
    }

    /**
     * Sets the International Securities Identification Number of the company.
     *
     * @param isinCode the ISIN code
     */
    public void setIsinCode(String isinCode)
    {
        this.isinCode = isinCode;
    }

    /**
     * Gets the language dependent International Standard Industrial Classification section name of the company.
     *
     * @return the ISIC section
     */
    public String getIsicSection()
    {
        return isicSection;
    }

    /**
     * Sets the language dependent International Standard Industrial Classification section name of the company.
     *
     * @param isicSection the ISIC section
     */
    public void setIsicSection(String isicSection)
    {
        this.isicSection = isicSection;
    }

    /**
     * Gets the language dependent International Standard Industrial Classification division name of the company.
     *
     * @return the ISIC division
     */
    public String getIsicDevision()
    {
        return isicDevision;
    }

    /**
     * Sets the language dependent International Standard Industrial Classification division name of the company.
     *
     * @param isicDevision the ISIC devision
     */
    public void setIsicDevision(String isicDevision)
    {
        this.isicDevision = isicDevision;
    }
}
