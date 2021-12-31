package com.investsaudi.forms.validation;

import com.investsaudi.forms.SagiaAuthenticateCodeForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component("sagiaAuthenticateCodeFormValidator")
public class SagiaAuthenticateCodeFormValidator extends RegistrationValidator {

    @Override
    public void validate(Object object, Errors errors) {

        final SagiaAuthenticateCodeForm sagiaAuthenticateCodeForm = (SagiaAuthenticateCodeForm) object;

        final String code = sagiaAuthenticateCodeForm.getCode();
        
        if(StringUtils.isEmpty(code)){
            errors.rejectValue("code", "authenticate.code.invalid");
        }
    }
}
