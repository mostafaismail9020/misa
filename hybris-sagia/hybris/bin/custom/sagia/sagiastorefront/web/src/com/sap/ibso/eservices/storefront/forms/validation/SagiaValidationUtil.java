package com.sap.ibso.eservices.storefront.forms.validation;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Base64;

public class SagiaValidationUtil {

    private static final String ARABIC_REGEX = "^[\\s\\u060C\\u060D\\u0610-\\u0615\\u061B\\u061C\\u061E\\u061F\\u0621-" +
            "\\u062F\\u0630-\\u063A\\u0640-\\u064F\\u0650-\\u065E\\u0671\\u0673\\u0674\\u0678\\u06BE\\u06CC\\u06CE" +
            "\\u06CF\\d\\?><\\(\\)\"\\\\;,\\{\\}\\[\\]\\-_\\+=!@.\\#\\$%^&\\*\\|\\']+$";
    private static final String WEBSITE_REGEX = "^(http:\\/\\/|https:\\/\\/|HTTP:\\/\\/|HTTPS:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-zA-Z]{2,3}.?([a-zA-Z]+)?$";
    private static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*?\\.[A-Za-z]{2,6}|" +
            "(\\d{1,3}\\.){3}\\d{1,3})(:\\d{4})?$";
    private static final String ENGLISH_REGEX = "^[\\s\\w\\d\\?><;,\\{\\}\\[\\]\\-_\\+=!@.\\#\\$%^&\\*\\|\\']+$";
    private static final String ORG_NAME_ENGLISH_REGEX = "^[a-zA-Z ]*$";

    private static final Logger LOG = Logger.getLogger(SagiaValidationUtil.class);

    private SagiaValidationUtil() {
    }

    public static boolean isArabic(String value) {
        return value.matches(ARABIC_REGEX);
    }

    public static boolean isPositiveInteger(String s) {
        try {
            return new BigDecimal(s).signum() > 0;
        } catch (Exception e) {
            LOG.warn(e.getMessage(),e);
            return false;
        }
    }

    public static boolean isPercentage(String value) {
        try {
            Double percentage = Double.valueOf(value);
            if (percentage <= 0 || percentage > 100) {
                return false;
            }
        } catch (NumberFormatException e) {
            LOG.warn(e.getMessage(),e);
            return false;
        }
        return true;
    }


    public static boolean isBase64(String value){
        try {
            Base64.getDecoder().decode(value);
        } catch (IllegalArgumentException e) {
            LOG.warn(e.getMessage(),e);
            return false;
        }

        return true;
    }

    public static boolean isEnglish(String value) {
        return value.matches(ENGLISH_REGEX);
    }

    public static boolean isEmail(String s) {
        return s.matches(EMAIL_REGEX);
    }

    public static boolean isWebAddress(String s) {
        return s.matches(WEBSITE_REGEX);
    }

    public static boolean isOrgNameEnglish(String s) { return s.matches(ORG_NAME_ENGLISH_REGEX); }
}
