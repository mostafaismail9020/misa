package com.sap.ibso.eservices.storefront.forms.validation;


import com.sap.ibso.eservices.sagiaservices.services.license.ConvToNationalsFormData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component("sagiaConvertToNationalsValidator")
public class SagiaConvertToNationalsValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ConvToNationalsFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object validatee, Errors errors) {
        final ConvToNationalsFormData convToNationalsFormData = (ConvToNationalsFormData) validatee;

        if(!convToNationalsFormData.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }
    }

    public void validate(Object validatee, Errors errors, Integer requiredFilesCount) {
        final ConvToNationalsFormData convToNationalsFormData = (ConvToNationalsFormData) validatee;

        if(!convToNationalsFormData.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }

        Integer filesCount = 0;
        if (convToNationalsFormData.getFiles() != null && !convToNationalsFormData.getFiles().isEmpty()) {
            filesCount += convToNationalsFormData.getFiles().size();
        }

        if (convToNationalsFormData.getFileData() != null && !convToNationalsFormData.getFileData().isEmpty()) {
            filesCount += convToNationalsFormData.getFileData().size();
        }

        if (!Objects.equals(filesCount, requiredFilesCount)) {
            errors.rejectValue("missingFiles", "general.missingFiles");
        }
    }
}
