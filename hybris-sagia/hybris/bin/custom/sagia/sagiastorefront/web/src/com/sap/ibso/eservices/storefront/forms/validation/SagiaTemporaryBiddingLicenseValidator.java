package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.storefront.forms.SagiaTemporaryBiddingLicenseForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component("sagiaTemporaryBiddingLicenseValidator")
public class SagiaTemporaryBiddingLicenseValidator implements Validator {

    // Mandatory attachments indexes in multipart list
    private static List<Integer> requiredIndexes = Arrays.asList(0, 1, 4, 5, 6, 8, 9, 10, 11);

    @Override
    public boolean supports(Class<?> clazz) {
        return SagiaTemporaryBiddingLicenseForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SagiaTemporaryBiddingLicenseForm form = (SagiaTemporaryBiddingLicenseForm) target;
        if(!form.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyNameInEnglish", "validation.companyNameInEnglish");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectNameInArabic", "validation.projectNameInArabic");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectNameInEnglish", "validation.projectNameInEnglish");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "capital", "validation.capital");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "governmentEntity", "validation.governmentEntity");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "validation.country");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "validation.city");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postalCode", "validation.postalCode");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "poBox", "validation.poBox");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "validation.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "validation.mobile");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "validation.telephone");


        final List<MultipartFile> multipartFiles = form.getTemporaryBiddingLicenseMultipartFiles();
        final List<String> draftFiles = form.getDraftFileData();
        if (multipartFiles == null && draftFiles == null) {
            errors.rejectValue("temporaryBiddingLicenseMultipartFiles", "validation.file");
        } else {
            requiredIndexes.forEach(i -> {
                boolean fileIndexExists = indexExists(i, multipartFiles);
                boolean draftFileIndexExists = indexExists(i, draftFiles);
                if ((!fileIndexExists || multipartFiles.get(i) == null) &&
                        (!draftFileIndexExists || draftFiles.get(i) == null))
                {
                    errors.rejectValue("temporaryBiddingLicenseMultipartFiles", "validation.file");
                    return;
                }
            });

        }
    }

    private boolean indexExists(int index, List list) {
        return list != null && index < list.size();
    }
}
