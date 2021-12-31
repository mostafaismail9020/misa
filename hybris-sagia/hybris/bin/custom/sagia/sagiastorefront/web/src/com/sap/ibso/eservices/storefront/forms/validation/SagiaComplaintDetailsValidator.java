package com.sap.ibso.eservices.storefront.forms.validation;


import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.UpdatableComplaintDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaComplaintDetailsValidator")
public class SagiaComplaintDetailsValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UpdatableComplaintDetails.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UpdatableComplaintDetails complaintDetails = (UpdatableComplaintDetails) o ;
        if (StringUtils.isEmpty(complaintDetails.getTextMsg())) {
            errors.rejectValue("textMsg", "complaint.details.validation.textMsg");
        }

    }
}
