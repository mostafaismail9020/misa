package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.sagiaservices.services.SagiaConfigurationFacade;
import com.sap.ibso.eservices.storefront.forms.SagiaRegisterForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("sagiaRegistrationValidator")
public class SagiaRegistrationValidator extends RegistrationValidator {
    public static final String NUMBER_REGEX = "[0-9]+";
    private static final String PASS = "pwd";
    private static final String COMPANY = "company";
    private static final String MOBILE_NUMBER = "mobileNumber";

    @Resource
    private SagiaConfigurationFacade sagiaConfigurationFacade;

    @Override
    public void validate(Object object, Errors errors) {
        final SagiaRegisterForm registerForm = (SagiaRegisterForm) object;

        final String titleCode = registerForm.getTitleCode();
        final String firstName = registerForm.getFirstName();
        final String lastName = registerForm.getLastName();
        final String email = registerForm.getEmail();
        final String pwd = registerForm.getPwd();
        final String checkPwd = registerForm.getCheckPwd();
        final String company = registerForm.getCompany();
        final String mobile = registerForm.getMobileNumber();
        final String mobileCountryCode = registerForm.getMobileCountryCode();
        final String countryCode = registerForm.getCountryCode();
        final String sectorCode = registerForm.getSector();
        final Boolean termsAndConditionsChecked = registerForm.getTermsAndConditionsChecked();
        final String username = registerForm.getUserName();

        if(!termsAndConditionsChecked){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }

        validateTitleCode(errors, titleCode);
        validateName(errors, firstName, "firstName", "register.firstName.invalid");
        validateName(errors, lastName, "lastName", "register.lastName.invalid");

        if (StringUtils.length(firstName) + StringUtils.length(lastName) > 255) {
            errors.rejectValue("lastName", "register.name.invalid");
            errors.rejectValue("firstName", "register.name.invalid");
        }
        if (StringUtils.isEmpty(countryCode)) {
            errors.rejectValue("countryCode", "register.countrycode.invalid");
        }

        if (StringUtils.isEmpty(mobileCountryCode)) {
            errors.rejectValue("mobileCountryCode", "register.mobileCountryCode.invalid");
        }

        if (StringUtils.isEmpty(sectorCode)) {
            errors.rejectValue("sector", "register.sector.invalid");
        }

        if (StringUtils.isEmpty(username)) {
            errors.rejectValue("userName", "Please enter an username");
        }

        validateEmail(errors, email);
        validateCompany(errors, company);
        validateMobileNumber(errors, mobile);
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

    protected void validateCompany(final Errors errors, final String company) {
        if (StringUtils.isEmpty(company)) {
            errors.rejectValue(COMPANY, "register.company.invalid");
        } else if (StringUtils.length(company) < 2 || StringUtils.length(company) > 64) {
            errors.rejectValue(COMPANY, "register.company.invalid");
        }
    }

    protected void validateMobileNumber(final Errors errors, final String mobileNumber) {
        if (StringUtils.isEmpty(mobileNumber)) {
            errors.rejectValue(MOBILE_NUMBER, "register.mobileNumber.invalid");
        } else if (StringUtils.length(mobileNumber) > 255 || !mobileNumber.matches(NUMBER_REGEX)) {
            errors.rejectValue(MOBILE_NUMBER, "register.mobileNumber.invalid");
        }
    }
}