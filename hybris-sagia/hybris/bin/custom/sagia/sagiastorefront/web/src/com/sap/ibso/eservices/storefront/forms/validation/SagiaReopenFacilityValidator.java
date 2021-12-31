package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacility;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaReopenFacilityValidator")
public class SagiaReopenFacilityValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ReopenFacility.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        final ReopenFacility reopenFacility = (ReopenFacility) object;
        if(!reopenFacility.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }
    }
}
