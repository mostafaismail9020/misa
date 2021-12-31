package com.sap.ibso.eservices.sagiaservices.services.isicdetails.dto;

import java.util.HashMap;
import java.util.Map;

public class ISICDetailsParametersBuilder {

    private static final String LANGUAGE = "Language";
    private static final String FLAG = "Flag";
    private static final String LICTYPE = "lictype";
    private static final String SECTION = "section";
    private static final String DIVISION = "Division";
    private static final String ACTIVITY = "Activity";
    private static final String COMPLIMENTARY = "Complimentary";
    private static Map<String, String> functionImportParams = new HashMap<>();

    public static String[] build() {
        return new String[]{ LANGUAGE, functionImportParams.get(LANGUAGE),
                             FLAG, functionImportParams.get(FLAG),
                             LICTYPE, functionImportParams.get(LICTYPE),
                             SECTION, functionImportParams.get(SECTION),
                             DIVISION, functionImportParams.get(DIVISION),
                             ACTIVITY, functionImportParams.get(ACTIVITY),
                             COMPLIMENTARY, functionImportParams.get(COMPLIMENTARY)};

    }

    public ISICDetailsParametersBuilder language(String language) {
        functionImportParams.put(LANGUAGE, language);
        return this;
    }

    public ISICDetailsParametersBuilder flag(String flag) {
        functionImportParams.put(FLAG, flag);
        return this;
    }

    public ISICDetailsParametersBuilder lictype(String lictype) {
        functionImportParams.put(LICTYPE, lictype);
        return this;
    }

    public ISICDetailsParametersBuilder section(String section) {
        functionImportParams.put(SECTION, section);
        return this;
    }

    public ISICDetailsParametersBuilder division(String division) {
        functionImportParams.put(DIVISION, division);
        return this;
    }

    public ISICDetailsParametersBuilder activity(String activity) {
        functionImportParams.put(ACTIVITY, activity);
        return this;
    }

    public ISICDetailsParametersBuilder complimentary(String complimentary) {
        functionImportParams.put(COMPLIMENTARY, complimentary);
        return this;
    }
}
