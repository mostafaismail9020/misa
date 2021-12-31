package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.sap.ibso.eservices.facades.data.zqeemah2.BasicContactInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("basicContactInfoValidatorQ2")
public class ContactPersonValidatorQeemah2 implements Validator {

    private static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*?\\.[A-Za-z]{2,6}|" +
            "(\\d{1,3}\\.){3}\\d{1,3})(:\\d{4})?$";

    @Override
    public boolean supports(Class<?> aClass) {
        return BasicContactInfo.class.equals(aClass);
    }

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:MethodCyclomaticComplexity"})
    @Override
    public void validate(Object object, Errors errors) {
        final BasicContactInfo basicContactInfo = (BasicContactInfo)object;
        if(StringUtils.isEmpty(basicContactInfo.getGender())){
            errors.rejectValue("gender","validation.contactPerson.gender");
        }
        String firstName = basicContactInfo.getFirstName();
        if(StringUtils.isEmpty(firstName) || !StringUtils.isAlpha(firstName) || !isMaxLength(firstName, 40)){
            errors.rejectValue("firstName","validation.contactPerson.firstName");
        }
        String lastName = basicContactInfo.getLastName();
        if(StringUtils.isEmpty(lastName) || !StringUtils.isAlpha(lastName) || !isMaxLength(lastName, 40)){
            errors.rejectValue("lastName","validation.contactPerson.lastName");
        }
        if(StringUtils.isEmpty(basicContactInfo.getNationality())){
            errors.rejectValue("nationality","validation.contactPerson.nationality");
        }
        if(StringUtils.isEmpty(basicContactInfo.getCountry())){
            errors.rejectValue("country","validation.contactPerson.country");
        }
        if(StringUtils.isEmpty(basicContactInfo.getRole())){
            errors.rejectValue("role","validation.contactPerson.role");
        }
        String email = basicContactInfo.getEmail();
        if(StringUtils.isEmpty(email) || !isEmail(email)){
            errors.rejectValue("email","validation.contactPerson.email");
        }
        String telephone = basicContactInfo.getTelephone();
        if(StringUtils.isEmpty(telephone) || !StringUtils.isNumeric(telephone)){
            errors.rejectValue("telephone","validation.contactPerson.telephone");
        }
        String mobileNumber = basicContactInfo.getMobileNumber();
        if(StringUtils.isEmpty(mobileNumber) || !StringUtils.isNumeric(mobileNumber)){
            errors.rejectValue("mobileNumber","validation.contactPerson.mobileNumber");
        }
    }
    private boolean isEmail(String value) {
        return value.matches(EMAIL_REGEX);
    }
    private boolean isMaxLength(String value, int maxLength) {
        return value.length() <= maxLength;
    }
}
