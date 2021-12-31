package com.sap.ibso.eservices.storefront.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateEmailForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("sagiaEmailValidator")
public class SagiaEmailValidator implements Validator {

    public static final Pattern EMAIL_REGEX = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");
    private static final String EMAIL = "email";
    private static final String CHK_EMAIL = "chkEmail";
    private static final String PASS = "password";

    @Override
    public boolean supports(final Class<?> aClass) {
        return UpdateEmailForm.class.equals(aClass);
    }

    @Override
    public void validate(final Object object, final Errors errors) {
        final UpdateEmailForm updateEmailForm = (UpdateEmailForm) object;
        final String email = updateEmailForm.getEmail();
        final String chkEmail = updateEmailForm.getChkEmail();
        final String password = updateEmailForm.getPassword();

        if (StringUtils.isEmpty(email)) {
            errors.rejectValue(EMAIL, "profile.email.invalid");
        } else if (!email.equals(chkEmail)) {
            errors.rejectValue(CHK_EMAIL, "validation.chkEmail.equals");
        } else if (StringUtils.length(email) > 255 || !EMAIL_REGEX.matcher(email).matches()) {
            errors.rejectValue(EMAIL, "profile.email.invalid");
        }

        if (StringUtils.isEmpty(chkEmail)) {
            errors.rejectValue(CHK_EMAIL, "profile.checkEmail.invalid");
        }

        if (StringUtils.isEmpty(password)) {
            errors.rejectValue(PASS, "profile.pwd.invalid");
        }
    }
}
