package com.sap.ibso.eservices.storefront.forms.validation.license;

import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zqeemah.DelegateInfo;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderInfo;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;

@Component("newShareholderPersonQeemah1Validator")
public class NewShareholderPersonQeemah1Validator implements Validator {
    private static final Logger LOG = Logger.getLogger( NewShareholderPersonQeemah1Validator.class.getName() );

    @Resource
    private SagiaFormatProvider sagiaFormatProvider;
    
    @Resource
    private DelegateInfoValidator delegateInfoValidator;

    @Override
    public boolean supports(Class<?> aClass) {
        return ShareholderInfo.class.equals(aClass);
    }

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S138 | Methods should not have too many lines
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:S3776","squid:MethodCyclomaticComplexity","squid:S138"})
    @Override
    public void validate(Object object, Errors errors) {
        ShareholderInfo shareholderInfo = (ShareholderInfo)object;
        if(StringUtils.isEmpty(shareholderInfo.getTitle())){
            errors.rejectValue("title", "validation.shareholder.person.title");
        }
        if(StringUtils.isEmpty(shareholderInfo.getAcademicTitle())){
            errors.rejectValue("academicTitle", "validation.shareholder.person.academicTitle");
        }
        if(StringUtils.isEmpty(shareholderInfo.getFirstNameArabic()) || !SagiaValidationUtil.isArabic(shareholderInfo.getFirstNameArabic())){
            errors.rejectValue("firstNameArabic", "validation.shareholder.person.firstNameArabic");
        }
        if(StringUtils.isEmpty(shareholderInfo.getLastNameArabic()) || !SagiaValidationUtil.isArabic(shareholderInfo.getLastNameArabic())){
            errors.rejectValue("lastNameArabic", "validation.shareholder.person.lastNameArabic");
        }
        if(StringUtils.isEmpty(shareholderInfo.getFullNameEnglish()) || !SagiaValidationUtil.isEnglish(shareholderInfo.getFullNameEnglish())) {
            errors.rejectValue("fullNameEnglish", "validation.shareholder.person.fullNameEnglish");
        }
        if(StringUtils.isBlank(shareholderInfo.getSharesPercentage()) || !isPercentage(shareholderInfo.getSharesPercentage())){
            errors.rejectValue("sharesPercentage", "validation.shareholder.person.sharesPercentage");
        }
        if(StringUtils.isEmpty(shareholderInfo.getCurrentNationality().getVal())){
            errors.rejectValue("currentNationality", "validation.shareholder.person.nationality");
        }
        if(StringUtils.isEmpty(shareholderInfo.getDateOfBirth())){
            errors.rejectValue("dateOfBirth", "validation.shareholder.person.dateOfBirth");
        } else {
            try {
                if(sagiaFormatProvider.getDateFromUIDateStringOrNull(shareholderInfo.getDateOfBirth()).isAfter(LocalDate.now())) {
                    errors.rejectValue("dateOfBirth", "validation.shareholder.person.dateOfBirth");
                }
            } catch(Exception e ) {
                LOG.warn(e.getMessage(),e);
            }
        }
        if(StringUtils.isEmpty(shareholderInfo.getPassportNumber()) || !StringUtils.isAlphanumeric(shareholderInfo.getPassportNumber())){
            errors.rejectValue("passportNumber", "validation.shareholder.person.passportNumber");
        }
        //if(StringUtils.isEmpty(shareholderInfo.getMofaNumber()) || !StringUtils.isAlphanumeric(shareholderInfo.getMofaNumber())){
            //errors.rejectValue("mofaNumber", "validation.shareholder.person.mofaNumber");
        //}
        if(StringUtils.isEmpty(shareholderInfo.getPassportIssueDate())) {
            errors.rejectValue("passportIssueDate", "validation.shareholder.person.passportIssueDate");
        } else {
            try {
                if(sagiaFormatProvider.getDateFromUIDateStringOrNull(shareholderInfo.getPassportIssueDate()).isAfter(LocalDate.now())) {
                    errors.rejectValue("passportIssueDate", "validation.shareholder.person.passportIssueDate");
                }
            } catch(Exception e ) {
                LOG.warn(e.getMessage(),e);
            }
        }
        if(StringUtils.isEmpty(shareholderInfo.getPassportExpiryDate())){
            errors.rejectValue("passportExpiryDate", "validation.shareholder.person.passportExpiryDate");
        } else {
            try {
                if(sagiaFormatProvider.getDateFromUIDateStringOrNull(shareholderInfo.getPassportExpiryDate()).isBefore(LocalDate.now())) {
                    errors.rejectValue("passportExpiryDate", "validation.shareholder.person.passportExpiryDate");
                }
            } catch(Exception e ) {
                LOG.warn(e.getMessage(),e);
            }
        }
        if(StringUtils.isEmpty(shareholderInfo.getPreviousNationality())){
            errors.rejectValue("previousNationality", "validation.shareholder.person.previousNationality");
        }
        if(StringUtils.isEmpty(shareholderInfo.getPremiumResident())){
            errors.rejectValue("premiumResident", "validation.shareholder.person.premiumResident");
        }
        if(StringUtils.isEmpty(shareholderInfo.getCountry())){
            errors.rejectValue("country", "validation.shareholder.person.country");
        }
        if(StringUtils.isEmpty(shareholderInfo.getCity())){
            errors.rejectValue("city", "validation.shareholder.person.city");
        }
        if(StringUtils.isEmpty(shareholderInfo.getPostalCode())) {
            errors.rejectValue("postalCode", "validation.shareholder.person.postalCode");
        }
        if(StringUtils.isNotEmpty(shareholderInfo.getPostalCode()) && shareholderInfo.getPostalCode().length() > 10) {
            errors.rejectValue("postalCode", "validation.shareholder.person.postalCode.length");
        }
        if(StringUtils.isEmpty(shareholderInfo.getPoBox()) || !StringUtils.isNumeric(shareholderInfo.getPoBox())){
            errors.rejectValue("poBox", "validation.shareholder.person.poBox");
        }
        if(StringUtils.isEmpty(shareholderInfo.getCountryCodeForTelephone()) || !StringUtils.isNumeric(shareholderInfo.getCountryCodeForTelephone())){
            errors.rejectValue("countryCodeForTelephone", "validation.shareholder.person.countryCodeForTelephone");
        }
        if(StringUtils.isEmpty(shareholderInfo.getTelephone()) || !StringUtils.isNumeric(shareholderInfo.getTelephone())){
            errors.rejectValue("telephone", "validation.shareholder.person.telephone");
        }
        if(StringUtils.isEmpty(shareholderInfo.getCountryCodeForMobile()) || !StringUtils.isNumeric(shareholderInfo.getCountryCodeForMobile())){
            errors.rejectValue("countryCodeForMobile", "validation.shareholder.person.countryCodeForMobile");
        }
        if(StringUtils.isEmpty(shareholderInfo.getMobile()) || !StringUtils.isNumeric(shareholderInfo.getMobile())){
            errors.rejectValue("mobile", "validation.shareholder.person.mobile");
        }
        if(StringUtils.isEmpty(shareholderInfo.getEmail()) || !SagiaValidationUtil.isEmail(shareholderInfo.getEmail())){
            errors.rejectValue("email", "validation.shareholder.person.email");
        }
        if(StringUtils.isEmpty(shareholderInfo.getPassportFileName())){
            errors.rejectValue("passportFileName", "validation.shareholder.organization.companyRegistrationFile");
        }

        if (shareholderInfo.getPassportFile() == null || !SagiaValidationUtil.isBase64(shareholderInfo.getPassportFile())){
            errors.rejectValue("passportFileName", "validation.shareholder.organization.valid.content.companyRegistrationFile");
        }

        //if(StringUtils.isEmpty(shareholderInfo.getOtherFileName())){
            //errors.rejectValue("otherFileName", "validation.shareholder.organization.companyFinancialStatementFile");
        //}

        //if (shareholderInfo.getOtherFile() == null || !SagiaValidationUtil.isBase64(shareholderInfo.getOtherFile())){
            //errors.rejectValue("otherFileName", "validation.shareholder.organization.valid.content.companyFinancialStatementFile");
        //}

        if(shareholderInfo.getHasDelegateInfo().equalsIgnoreCase("true") && shareholderInfo.getSelfDelegate().equalsIgnoreCase("false")){
    		DelegateInfo shareholderDelegateInfo = shareholderInfo.getDelegate();
    		Map<String,String> delegateError=delegateInfoValidator.validate(shareholderDelegateInfo);
    		for(Map.Entry<String, String> error:delegateError.entrySet()){
    			errors.rejectValue(error.getKey(), error.getValue());
    		}
        }
        
    }

    private boolean isPercentage(String value) {
        try {
            Double percentage = Double.valueOf(value);
            if (percentage <= 0 || percentage > 100) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
