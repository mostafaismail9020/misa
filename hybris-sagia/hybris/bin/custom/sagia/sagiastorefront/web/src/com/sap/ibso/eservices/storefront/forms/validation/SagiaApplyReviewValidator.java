package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.storefront.forms.SagiaApplyReviewForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaApplyReviewValidator")
public class SagiaApplyReviewValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return SagiaApplyReviewForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SagiaApplyReviewForm applyReviewFormData = (SagiaApplyReviewForm) o;

        if(!applyReviewFormData.isTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }
    }
}
