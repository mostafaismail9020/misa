package com.sap.ibso.eservices.storefront.forms.validation;

import com.sap.ibso.eservices.storefront.forms.SagiaRealEstateForm;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("sagiaRealEstateValidator")
public class SagiaRealEstateValidator implements Validator {

    static final String PERSONAL_HOUSING = "personalHousing";
    static final String INDUSTRIAL = "industrial";
    static final String ADMINISTRATIVE = "administrative";
    static final String WAREHOUSING = "warehousing";
    static final String INVESTMENT = "investment";
    private static final String REAL_ESTATE_VALIDATOR = "realEstate.validator.";
    private static final String PURCHASE_TYPE = "purchaseType";
    private static final String OUTSIDE_MAKKAH = "outsideMakkah";
    private static final String APPROVED_INDUSTRIAL = "approvedIndustrial";
    private static final String PROJECT_VALUE = "projectValue";
    private static final String HOUSING_TYPE = "housingType";
    private static final String THIRTY_MORE = "thirtyMore";
    private static final String REQUEST_TYPE = "requestType";
    private static final String PLOT_NO = "plotNo";
    private static final String PLOT_AREA = "plotArea";
    private static final String DEED_NO = "deedNo";
    private static final String PURCHASE_DATE = "purchaseDate";
    private static final String PRICE = "price";
    private static final String DISTRICT = "district";
    private static final String UNIT_NO = "unitNo";
    private static final String REMARKS = "remarks";
    private static final String BLOCK_NO = "blockNo";
    private static final String TERMS_AND_CONDITIONS_CHECKED = "termsAndConditionsChecked";
    private static final String GENERAL_ACCEPTTERMSANDCONDITIONS = "general.accepttermsandconditions";

    @Override
    public boolean supports(Class<?> aClass) {
        return SagiaRealEstateForm.class.equals(aClass);
    }


    @Override
    public void validate(Object object, Errors errors) {
        SagiaRealEstateForm realEstate = (SagiaRealEstateForm) object;

        validateCommonFields(errors, realEstate);
        validateInvestment(errors, realEstate);
        validateIndustrial(errors, realEstate);
        validateAdministrative(errors, realEstate);
        validatePersonalHousing(errors, realEstate);



    }

    private void validateInvestment(Errors errors, SagiaRealEstateForm realEstate) {
        if (INVESTMENT.equals(realEstate.getPurchaseType())) {
            if (realEstate.getOutsideMakkah() == null) {
                errors.rejectValue(OUTSIDE_MAKKAH, REAL_ESTATE_VALIDATOR + OUTSIDE_MAKKAH);
            }
            if (realEstate.getThirtyMore() == null) {
                errors.rejectValue(THIRTY_MORE, REAL_ESTATE_VALIDATOR + THIRTY_MORE);
            }
        }
    }

    private void validatePersonalHousing(Errors errors, SagiaRealEstateForm realEstate) {
        if (PERSONAL_HOUSING.equals(realEstate.getPurchaseType()) && StringUtils.isBlank(realEstate.getHousingType())) {
            errors.rejectValue(HOUSING_TYPE, REAL_ESTATE_VALIDATOR + HOUSING_TYPE);
        }
    }

    private void validateAdministrative(Errors errors, SagiaRealEstateForm realEstate) {
        if ((ADMINISTRATIVE.equals(realEstate.getPurchaseType()) || INVESTMENT.equals(realEstate.getPurchaseType())) && (StringUtils.isBlank(realEstate.getProjectValue()) || !StringUtils.isNumeric(realEstate.getProjectValue()))) {
            errors.rejectValue(PROJECT_VALUE, REAL_ESTATE_VALIDATOR + PROJECT_VALUE);
        }
    }

    private void validateIndustrial(Errors errors, SagiaRealEstateForm realEstate) {
        if (INDUSTRIAL.equals(realEstate.getPurchaseType()) && realEstate.getApprovedIndustrial() == null) {
            errors.rejectValue(APPROVED_INDUSTRIAL, REAL_ESTATE_VALIDATOR + APPROVED_INDUSTRIAL);
        }
    }

    private void validateCommonFields(Errors errors, SagiaRealEstateForm realEstate) {


        validateString(errors, realEstate.getRequestType(), REQUEST_TYPE);
        validateString(errors, realEstate.getPurchaseType(), PURCHASE_TYPE);
        validateString(errors, realEstate.getDistrict(), DISTRICT);
        validateString(errors, realEstate.getRemarks(), REMARKS);
        validateString(errors, realEstate.getPlotArea(), PLOT_AREA);

        validateString(errors, realEstate.getPlotNo(), PLOT_NO);
        validateString(errors, realEstate.getDeedNo(), DEED_NO);
        validateNumeric(errors, realEstate.getPrice(), PRICE);
        validateNumeric(errors, realEstate.getUnitNo(), UNIT_NO);
        validateNumeric(errors, realEstate.getBlockNo(), BLOCK_NO);

        validateDate(errors, realEstate);
        validateTerms(errors, realEstate);

    }

    private void validateTerms(Errors errors, SagiaRealEstateForm realEstate) {
        if (!realEstate.isTermsAndConditionsChecked()) {
            errors.rejectValue(TERMS_AND_CONDITIONS_CHECKED, GENERAL_ACCEPTTERMSANDCONDITIONS);
        }
    }

    private void validateDate(Errors errors, SagiaRealEstateForm realEstate) {
        if (realEstate.getPurchaseDate() == null || realEstate.getPurchaseDate().length() < 7) {
            errors.rejectValue(PURCHASE_DATE, REAL_ESTATE_VALIDATOR + PURCHASE_DATE);
        }
    }

    private void validateNumeric(Errors errors, String price, String price2) {
        if (StringUtils.isBlank(price) || !StringUtils.isNumeric(price)) {
            errors.rejectValue(price2, REAL_ESTATE_VALIDATOR + price2);
        }
    }

    private void validateString(Errors errors, String plotArea, String plotArea2) {
        if (StringUtils.isBlank(plotArea)) {
            errors.rejectValue(plotArea2, REAL_ESTATE_VALIDATOR + plotArea2);
        }
    }
}
