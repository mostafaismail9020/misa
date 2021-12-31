package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.storefront.forms.SagiaUpdateUsernameForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaUsernameValidator")
public class SagiaUsernameValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SagiaUpdateUsernameForm.class.equals(clazz);
    }
    @Override
    public void validate(Object o, Errors errors) {

        final SagiaUpdateUsernameForm sagiaUpdateUsernameForm =(SagiaUpdateUsernameForm) o;
        validateUsername(errors, sagiaUpdateUsernameForm.getUsername(), sagiaUpdateUsernameForm.getCheckUsername(), sagiaUpdateUsernameForm.getPassword());

    }

    protected void validateUsername(final Errors errors, final String username, final String checkUsername, final String password)
    {
        if (StringUtils.isEmpty(username))
        {
            errors.rejectValue("username", "update.username.invalid");
        } else if (!username.equals(checkUsername))
        {
            errors.rejectValue("checkUsername", "validation.checkUsername.equals");
        }

        if (StringUtils.isEmpty(checkUsername))
        {
            errors.rejectValue("checkUsername", "update.username.invalid");
        }

        if (StringUtils.isEmpty(password))
        {
            errors.rejectValue("password", "update.password.invalid");
        }
    }
}
