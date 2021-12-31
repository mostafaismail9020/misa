package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.storefront.forms.NationalInvestorForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaNationalInvestorValidator")
public class NationalInvestorValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return NationalInvestorForm.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        NationalInvestorForm nationalInvestor = (NationalInvestorForm)object;
        StringUtils.isNotBlank(nationalInvestor.getCrNumber());
        if(nationalInvestor.getCrNumber().length() < 10){
            errors.rejectValue("crNumber", "register.national.investor.validator.crNumberLength");
        }
        if(!nationalInvestor.getTermsAndConditionsChecked()){
            errors.rejectValue("crNumber", "general.accepttermsandconditions");
        }
    }
}
