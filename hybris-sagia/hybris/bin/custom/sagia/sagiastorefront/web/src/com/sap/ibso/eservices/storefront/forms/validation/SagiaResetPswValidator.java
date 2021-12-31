package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.forms.SagiaResetPwdForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("sagiaResetPswValidator")
public class SagiaResetPswValidator extends RegistrationValidator {
    private static final String PASS = "pwd";

    @Resource
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Override
    public void validate(Object object, Errors errors) {
        final SagiaResetPwdForm registerForm = (SagiaResetPwdForm) object;

        final String pwd = registerForm.getPwd();
        final String checkPwd = registerForm.getCheckPwd();

        validatePassword(errors, pwd);
        comparePasswords(errors, pwd, checkPwd);
    }

    @Override
    protected void validatePassword(Errors errors, String pwd) {
        if (StringUtils.isEmpty(pwd)) {
            errors.rejectValue(PASS, "register.pwd.invalid");
        } else {
            String backendRegex = sagiaConfigurationFacade.getPasswordRegex();
            if (backendRegex != null && !backendRegex.trim().isEmpty()) {
                Pattern pattern = Pattern.compile(backendRegex);
                Matcher matcher = pattern.matcher(pwd);
                if (!matcher.matches()) {
                    errors.rejectValue(PASS, "updatePwd.pwd.invalid");
                }
            }
        }
    }

}