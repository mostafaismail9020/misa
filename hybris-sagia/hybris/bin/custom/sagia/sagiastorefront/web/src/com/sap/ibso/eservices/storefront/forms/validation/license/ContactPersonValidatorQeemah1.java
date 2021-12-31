package com.sap.ibso.eservices.storefront.forms.validation.license;

import static com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil.isEmail;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zqeemah.BasicContactInformation;

@Component("basicContactInfoValidatorQ1")
public class ContactPersonValidatorQeemah1 implements Validator {
    @Resource(name = "sagiaFormatProvider")
    SagiaFormatProvider sagiaFormatProvider;

    @Override
    public boolean supports(Class<?> aClass) {
        return BasicContactInformation.class.equals(aClass);
    }

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:S3776","squid:MethodCyclomaticComplexity"})
    @Override
    public void validate(Object object, Errors errors) {
        final BasicContactInformation basicContactInformation = (BasicContactInformation)object;

        if(StringUtils.isEmpty(basicContactInformation.getTitle())){
            errors.rejectValue("title","validation.contactPerson.title");
        }
        String firstName = basicContactInformation.getFirstName();
        if(StringUtils.isEmpty(firstName) || !StringUtils.isAlpha(firstName) || !isMaxLength(firstName, 40)){
            errors.rejectValue("firstName","validation.contactPerson.firstName");
        }
        String lastName = basicContactInformation.getLastName();
        if(StringUtils.isEmpty(lastName) || !StringUtils.isAlpha(lastName) || !isMaxLength(lastName, 40)){
            errors.rejectValue("lastName","validation.contactPerson.lastName");
        }
        if(StringUtils.isEmpty(basicContactInformation.getRole())){
            errors.rejectValue("role","validation.contactPerson.role");
        }
        if(StringUtils.isEmpty(basicContactInformation.getEducation())){
            errors.rejectValue("education","validation.contactPerson.education");
        }
        String dateOfBirth = basicContactInformation.getDateOfBirth();
        if(StringUtils.isEmpty(dateOfBirth) || !isDateInThePast(dateOfBirth)){
            errors.rejectValue("dateOfBirth","validation.contactPerson.dateOfBirth");
        }
        String passportNumber = basicContactInformation.getPassportNumber();
        if(StringUtils.isEmpty(passportNumber) || !StringUtils.isAlphanumeric(passportNumber)){
            errors.rejectValue("passportNumber","validation.contactPerson.passportNumber");
        }
        String passportIssueDate = basicContactInformation.getPassportIssueDate();
        if(StringUtils.isEmpty(passportIssueDate) || !isDateInThePast(passportIssueDate)){
            errors.rejectValue("passportIssueDate","validation.contactPerson.passportIssueDate");
        }
        String passportExpiryDate = basicContactInformation.getPassportExpiryDate();
        if(StringUtils.isEmpty(passportExpiryDate) || !isDateInTheFuture(passportExpiryDate)){
            errors.rejectValue("passportExpiryDate","validation.contactPerson.passportExpiryDate");
        }
        if(StringUtils.isEmpty(basicContactInformation.getCountry())){
            errors.rejectValue("country","validation.contactPerson.country");
        }

        String city = basicContactInformation.getCity();
        if(StringUtils.isEmpty(city) || !StringUtils.isAlpha(city)){
            errors.rejectValue("city","validation.contactPerson.city");
        }
        String address = basicContactInformation.getAddress();
        if(StringUtils.isEmpty(address)){
            errors.rejectValue("address","validation.contactPerson.city");
        }
        String poBox = basicContactInformation.getPoBox();
        if(StringUtils.isEmpty(poBox) || !StringUtils.isNumeric(poBox)){
            errors.rejectValue("poBox","validation.contactPerson.poBox");
        }
        String postalCode = basicContactInformation.getPostalCode();
        if(StringUtils.isEmpty(postalCode)) {
            errors.rejectValue("postalCode","validation.contactPerson.postalCode");
        }
        String telephone = basicContactInformation.getTelephone();
        if(StringUtils.isEmpty(telephone) || !StringUtils.isNumeric(telephone)){
            errors.rejectValue("telephone","validation.contactPerson.telephone");
        }
        String mobileNumber = basicContactInformation.getMobileNumber();
        if(StringUtils.isEmpty(mobileNumber) || !StringUtils.isNumeric(mobileNumber)){
            errors.rejectValue("mobileNumber","validation.contactPerson.mobileNumber");
        }
        String email = basicContactInformation.getEmail();
        if(StringUtils.isEmpty(email) || !isEmail(email)){
            errors.rejectValue("email","validation.contactPerson.email");
        }
    }
    private boolean isDateInThePast(String value) {
        LocalDate date = sagiaFormatProvider.getDateFromUIDateStringOrNull(value);
        return LocalDate.now().isAfter(date);
    }
    private boolean isDateInTheFuture(String value) {
        LocalDate date = sagiaFormatProvider.getDateFromUIDateStringOrNull(value);
        return LocalDate.now().isBefore(date);
    }
    private boolean isMaxLength(String value, int maxLength) {
        return value.length() <= maxLength;
    }
}
