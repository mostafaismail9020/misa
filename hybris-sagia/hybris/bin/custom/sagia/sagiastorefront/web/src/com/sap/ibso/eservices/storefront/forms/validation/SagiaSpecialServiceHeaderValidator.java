package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.facades.data.specialservices.SpecialServiceHeader;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("specialServiceHeaderValidator")
public class SagiaSpecialServiceHeaderValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return SpecialServiceHeader.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SpecialServiceHeader specialServiceHeader = (SpecialServiceHeader) o;

        if(!specialServiceHeader.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }
    }
}
