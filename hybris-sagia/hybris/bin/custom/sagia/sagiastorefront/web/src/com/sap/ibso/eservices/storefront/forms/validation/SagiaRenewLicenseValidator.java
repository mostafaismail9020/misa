package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.facades.form.LicenseRenewalForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("sagiaRenewLicenseValidator")
public class SagiaRenewLicenseValidator implements Validator {

    private static final String VALIDATION = "validation.";
    private static final String STREET = "street";
    private static final String STREET_NO = "streetNo";
    private static final String CITY = "city";
    private static final String ADDIT_NO = "additNo";
    private static final String COUNTRY = "country";
    private static final String BUILDING_NO = "buildingNo";
    private static final String POSTAL_CODE = "postalCode";
    private static final String ZIP_CODE = "zipCode";
    private static final String HOUSE_NO = "houseNo";
    private static final String DURATION = "duration";


    @Override
    public boolean supports(Class<?> aClass) {
        return LicenseRenewalForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LicenseRenewalForm form = (LicenseRenewalForm) target;

        if(!form.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }

        ValidationUtils.rejectIfEmpty(errors, STREET, VALIDATION + STREET);
        ValidationUtils.rejectIfEmpty(errors, HOUSE_NO, VALIDATION + STREET_NO);
        ValidationUtils.rejectIfEmpty(errors, CITY, VALIDATION + CITY);
        ValidationUtils.rejectIfEmpty(errors, ADDIT_NO, VALIDATION + ADDIT_NO);
        ValidationUtils.rejectIfEmpty(errors, COUNTRY, VALIDATION + COUNTRY);
        ValidationUtils.rejectIfEmpty(errors, BUILDING_NO, VALIDATION + BUILDING_NO);
        ValidationUtils.rejectIfEmpty(errors, ZIP_CODE, VALIDATION + POSTAL_CODE);
        ValidationUtils.rejectIfEmpty(errors, DURATION, VALIDATION + DURATION);

    }
}


