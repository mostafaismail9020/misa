package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.sap.ibso.eservices.facades.data.zqeemah2.Shareholder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("shareholderPersonValidatorQm2")
public class ShareholderPersonValidatorQm2 implements Validator  {


    @Override
    public boolean supports(Class<?> aClass) {
        return Shareholder.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        final Shareholder shareholder = (Shareholder)object;

        if(!StringUtils.isBlank(shareholder.getName()) || shareholder.getName().length() > 100) {
            errors.rejectValue("name","validation.shareholder.entity.companyName");
        }

        if(StringUtils.isBlank(shareholder.getType())){
            errors.rejectValue("type", "validation.shareholder.person.type");
        }

        if(StringUtils.isBlank(shareholder.getNationality())) {
            errors.rejectValue("nationality", "validation.shareholder.person.nationality");
        }

    }

}
