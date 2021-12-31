package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.facades.data.BranchData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component("sagiaBranchValidator")
public class SagiaBranchValidator implements Validator {

    public static final String NUMBER_REGEX = "[0-9]+";
    public static final String NOT_EMPTY = "govDocs.notEmpty";
    public static final String NOT_NUMBER = "govDocs.notNumber";
    @Override
    public boolean supports(Class<?> aClass) {
        return BranchData.class.equals(aClass);
    }


    /**
     * Overridden method that validates a branch.
     *
     * @param object - The data that is validated
     * @param errors - Errors that pop up on validating
     */
    @Override
    public void validate(Object object, Errors errors) {
        final BranchData branchData = (BranchData) object;
        String crn = branchData.getCommercialRegisterNumber();
        String gosi = branchData.getGosiNumber();
        String zakat = branchData.getZakatNumber();
        String mol = branchData.getMolNumber();
        Boolean hasMomra = branchData.getHasMomra();
        String shopLicenseNumber = branchData.getShopLicenseNumber();
        String amanah = branchData.getAmanah();

        if(!branchData.getTermsAndConditionsChecked()){
            errors.rejectValue("termsAndConditionsChecked","general.accepttermsandconditions");
        }

        validateNonNull(errors,crn,"commercialRegisterNumber",NOT_EMPTY);
        validateNonNull(errors,gosi,"gosiNumber",NOT_EMPTY);
        validateNonNull(errors,zakat,"zakatNumber",NOT_EMPTY);
        validateNonNull(errors,mol,"molNumber",NOT_EMPTY);

        validateNumber(errors,crn,"commercialRegisterNumber",NOT_NUMBER);
        validateNumber(errors,crn,"gosiNumber",NOT_NUMBER);
        validateNumber(errors,crn,"zakatNumber",NOT_NUMBER);
        validateNumber(errors,crn,"molNumber",NOT_NUMBER);

        if(hasMomra){
            validateNonNull(errors,shopLicenseNumber,"shopLicenseNumber",NOT_EMPTY);
            validateNumber(errors,crn,"shopLicenseNumber",NOT_NUMBER);
            validateNonNull(errors,amanah,"amanah",NOT_EMPTY);
        }
    }

    protected void validateNonNull(final Errors errors, final String value,final String propertyName,final String property) {
        if (StringUtils.isBlank(value)) {
            errors.rejectValue(propertyName, property);
        }
    }

    protected void validateNumber(final Errors errors, final String value,final String propertyName,final String property)
    {
        if (!value.matches(NUMBER_REGEX)) {
            errors.rejectValue(propertyName, property);
        }
    }


}

