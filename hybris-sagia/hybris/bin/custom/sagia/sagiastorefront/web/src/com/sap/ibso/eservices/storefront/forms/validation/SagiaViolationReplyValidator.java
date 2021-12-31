package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateViolationReplyFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("violationReplyValidator")
public class SagiaViolationReplyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CreateViolationReplyFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateViolationReplyFormData createViolationReplyFormData = (CreateViolationReplyFormData) o;

        if(!createViolationReplyFormData.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }
    }
}
