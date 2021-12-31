package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CompAndEnqHdrToDetailNavData;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ComplaintFormData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaComplaintsValidator")
public class SagiaComplaintsValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return ComplaintFormData.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ComplaintFormData complaintFormData = (ComplaintFormData) o;
        CompAndEnqHdrToDetailNavData formData = complaintFormData.getDetails();

        if (formData != null) {
            if (StringUtils.isEmpty(formData.getBranch())) {
                errors.rejectValue("details.Branch", "complaints.validation.branch");
            }

            if (StringUtils.isEmpty(formData.getCategory1())) {
                errors.rejectValue("details.Category1", "complaints.validation.category1");
            }

            if (StringUtils.isEmpty(formData.getCategory2())) {
                errors.rejectValue("details.Category2", "complaints.validation.category2");
            }

            if (StringUtils.isEmpty(formData.getEnquiryType())) {
                errors.rejectValue("details.EnquiryType", "complaints.validation.enquiryType");
            }

            if (StringUtils.isEmpty(formData.getSubject())) {
                errors.rejectValue("details.Subject", "complaints.validation.subject");
            }

            if (StringUtils.isEmpty(formData.getTextMsg())) {
                errors.rejectValue("details.TextMsg", "complaints.validation.msgTxt");
            }
        }

    }
}
