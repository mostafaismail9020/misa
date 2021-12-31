package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.facades.data.ProfilePersonalData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaProfileValidator")
public class SagiaProfileValidator implements Validator {

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String COMPANY = "company";
    private static final String COUNTRY_CODE = "countryCode";
    private static final String MOBILE_NUMBER = "mobileNumber";

    @Override
    public boolean supports(final Class<?> aClass) {
        return ProfilePersonalData.class.equals(aClass);
    }

    @Override
    public void validate(final Object object, final Errors errors) {
        final ProfilePersonalData profileForm = (ProfilePersonalData) object;
        final String firstName = profileForm.getFirstName();
        final String lastName = profileForm.getLastName();
        final String company = profileForm.getCompany();
        final String countryCode = profileForm.getMobileCountryCode();
        final String mobileNumber = profileForm.getMobileNumber();

        if (StringUtils.isEmpty(firstName)) {
            errors.rejectValue(FIRST_NAME, "profile.firstName.invalid");
        } else if (StringUtils.length(firstName) > 255) {
            errors.rejectValue(FIRST_NAME, "profile.firstName.invalid");
        }

        if (StringUtils.isEmpty(lastName)) {
            errors.rejectValue(LAST_NAME, "profile.lastName.invalid");
        } else if (StringUtils.length(lastName) > 255) {
            errors.rejectValue(LAST_NAME, "profile.lastName.invalid");
        }

        if (StringUtils.isEmpty(company)) {
            errors.rejectValue(COMPANY, "profile.company.invalid");
        }

        if (StringUtils.isEmpty(countryCode)) {
            errors.rejectValue(COUNTRY_CODE, "profile.countryCode.invalid");
        }

        if (StringUtils.isEmpty(mobileNumber)) {
            errors.rejectValue(MOBILE_NUMBER, "profile.mobileNumber.invalid");
        }
    }
}
