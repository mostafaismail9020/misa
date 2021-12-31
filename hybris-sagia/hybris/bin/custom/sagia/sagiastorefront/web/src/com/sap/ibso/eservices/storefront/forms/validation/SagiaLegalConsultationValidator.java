package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.legalconsultation.dto.LegalConsultationFormData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaLegalConsultationValidator")
public class SagiaLegalConsultationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return LegalConsultationFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LegalConsultationFormData legalConsultationFormData = (LegalConsultationFormData) o ;
        if (StringUtils.isEmpty(legalConsultationFormData.getLegEnqTitle())) {
            errors.rejectValue("legEnqTitle", "legal.consulation.validation.legEnqTitle");
        }

        if (StringUtils.isEmpty(legalConsultationFormData.getTextMsg())) {
            errors.rejectValue("textMsg", "legal.consulation.validation.textMsg");
        }

        if (!Boolean.TRUE.equals(legalConsultationFormData.isTermsAndConditionsChecked())) {
            errors.rejectValue("termsAndConditionsChecked", "legal.consulation.validation.termsAndConditions");
        }
    }
}
