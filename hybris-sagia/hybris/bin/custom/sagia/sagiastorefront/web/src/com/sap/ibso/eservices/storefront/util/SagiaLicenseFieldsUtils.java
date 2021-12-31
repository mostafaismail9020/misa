package com.sap.ibso.eservices.storefront.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;

/**
 * Validates fiels in Queemah flow using (fail-safe) checking
 * to avoid copy/paste bypass in front.
 */

@Component("sagiaLicenseFieldsUtils")
public class SagiaLicenseFieldsUtils {

    private final int CAPITAL_MAX_LENGTH = 20;
    private final int ADDRESS_MAX_LENGTH = 60;
    private final int WEBSITE_MAX_LENGTH = 132;
    private final int TELEPHONE_MAX_LENGTH = 30;
    private final int MOBILE_NUMBER_MAX_LENGTH = 30;
    private final int FULL_NAME_IN_ENGLISH_MAX_LENGTH = 100;
    private final int ORGANIZATION_NAME_MAX_LENGTH = 100;
    private final int ORGANIZATION_COMPANY_REG_MAX_LENGTH = 60;
    private final int FIRST_NAME_MAX_LENGTH = 40; //eng and arabic same length
    private final int LAST_NAME_MAX_LENGTH	= 40; //eng and arabic same length
    private final int PASSPORT_NUMBER_MAX_LENGTH =	60;
    private final int CITY_MAX_LENGTH	= 40;
    private final int SH_PERCENTAGE_MAX_LENGTH = 10;

    private final int POBOX_MAX_LENGTH = 5;
    private final int POSTALCODE_MAX_LENGTH = 5;
    private final int COUNTRY_CODE_MAX_LENGTH = 5;


    public boolean isMaxLength(String value, int maxLength) {
        return value.length() <= maxLength;
    }

    public boolean telePhoneNumberCheck(String telephone)
    {
        if(telephone != null && telephone.length() <= TELEPHONE_MAX_LENGTH){
            return isNumberOnly (telephone);
        }
        return false;
    }

    public boolean mobilePhoneNumberCheck(String mobile)
    {
        if(mobile != null && mobile.length() <= MOBILE_NUMBER_MAX_LENGTH){
            return isNumberOnly (mobile);
        }
        return false;
    }

    public boolean websiteCheck(String website)
    {
        if(website != null && website.length() <= WEBSITE_MAX_LENGTH){
            return true;
        }
        return false;
    }

    public boolean firstNameCheck(String firstName)
    {
        if(firstName != null && firstName.length() <= FIRST_NAME_MAX_LENGTH){
            return isCharacterSpaceOnly (firstName) || SagiaValidationUtil.isArabic(firstName);
        }
        return false;
    }


    public boolean lastNameCheck(String lastName)
    {
        if(lastName != null && lastName.length() <= LAST_NAME_MAX_LENGTH){
            return isCharacterSpaceOnly (lastName) || SagiaValidationUtil.isArabic(lastName);
        }
        return false;
    }


    public boolean passportNumberCheck(String passportNumber)
    {
        if(passportNumber != null && passportNumber.length() <= PASSPORT_NUMBER_MAX_LENGTH){
            return isAlphaNumeric (passportNumber);
        }
        return false;
    }


    public boolean fullNameEnglishCheck(String fullNameEnglish)
    {
        if(fullNameEnglish != null && fullNameEnglish.length() <= FULL_NAME_IN_ENGLISH_MAX_LENGTH){
            return true;
        }
        return false;
    }

    public boolean addressCheck(String address)
    {
        if(address != null && address.length() <= ADDRESS_MAX_LENGTH){
            return true;
        }
        return false;
    }

    public boolean capitalCheck(String capital)
    {
        if(capital != null && capital.length() <= CAPITAL_MAX_LENGTH){
            return isNumberOnly (capital);
        }

        return false;
    }

    public boolean cityCheck(String city)
    {
        if(city != null && city.length() <= CITY_MAX_LENGTH){
            return true;
        }
        return false;
    }

    public boolean sharesPercentageCheck(String sharesPercentage)
    {
        if(sharesPercentage != null && sharesPercentage.length() <= SH_PERCENTAGE_MAX_LENGTH){
            return true;
        }

        return false;
    }

    public boolean poBoxCheck(String poBox)
    {
        if(poBox != null && poBox.length() <= POBOX_MAX_LENGTH){
            return isNumberOnly (poBox);
        }
        return false;
    }

    public boolean postalCodeCheck(String postalCode)
    {
        if(postalCode != null && postalCode.length() <= POSTALCODE_MAX_LENGTH){
            return isNumberOnly (postalCode);
        }
        return false;
    }

    public boolean countryCodeCheck(String countryCodeTelephone) {

        if(countryCodeTelephone != null && countryCodeTelephone.length() <= COUNTRY_CODE_MAX_LENGTH){
            return isNumberOnly (countryCodeTelephone);
        }
        return false;
    }

    public boolean companyRegNumberCheck(String companyRegNumber) {

        if(companyRegNumber != null && companyRegNumber.length() <= ORGANIZATION_COMPANY_REG_MAX_LENGTH){
            return true;
        }
        return false;
    }

    public boolean organizationNameCheck(String organizationName) {
        if(organizationName != null && organizationName.length() <= ORGANIZATION_NAME_MAX_LENGTH){
            return true;
        }
        return false;
    }

    public boolean isNumberOnly(String value)
    {
        return StringUtils.isNumeric(value); //value.chars().allMatch(x -> Character.isDigit(x))
    }

    public boolean isAlphaNumeric(String value) {
        return StringUtils.isAlphanumericSpace(value);
    }

    public boolean isCharacterOnly(String value) {
        return value != null && value.matches("^[a-zA-Z]+$");
    }

    public boolean isCharacterSpaceOnly(String value) {
        return value != null && value.matches("^[a-zA-Z ]+$");
    }
    
    public boolean isAlphaNumericWithSpace(String value) {
    	return value != null && value.matches("^[a-zA-Z0-9 ]+$");
    }
    



    
}
