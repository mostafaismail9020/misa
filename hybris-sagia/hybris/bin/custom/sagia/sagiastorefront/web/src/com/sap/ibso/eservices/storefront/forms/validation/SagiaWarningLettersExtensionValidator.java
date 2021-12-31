package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateWarningLetterExtensionFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("warningLettersExtensionValidator")
public class SagiaWarningLettersExtensionValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CreateWarningLetterExtensionFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateWarningLetterExtensionFormData warningLetterExtensionFormData = (CreateWarningLetterExtensionFormData) o;

        if(!warningLetterExtensionFormData.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }

    }
}
