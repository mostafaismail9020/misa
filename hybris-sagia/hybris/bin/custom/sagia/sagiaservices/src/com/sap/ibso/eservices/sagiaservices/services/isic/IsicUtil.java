package com.sap.ibso.eservices.sagiaservices.services.isic;

import java.util.List;

public class IsicUtil {


    public static final String AND = " and ";

    private IsicUtil() {
    }

    public static String buildFilterExpression(String language, String licenseType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(")
                .append(getLanguage(language))
                .append(AND)
                .append(getLicenseType(licenseType))
                .append(")");
        return stringBuilder.toString();
    }

    public static String buildFilterExpression(String language, List<String> parentIds) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(")
                .append(getInputId(parentIds))
                .append(AND)
                .append(getLanguage(language))
                .append(")");
        return stringBuilder.toString();

    }

    public static String buildFilterExpression(String language, List<String> parentIds, String licenseType) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(")
                .append(getInputId(parentIds))
                .append(AND)
                .append(getLanguage(language))
                .append(AND)
                .append(getLicenseType(licenseType))
                .append(")");
        return stringBuilder.toString();
    }

    private static String getInputId(List<String> parentIds) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(Inputid eq '" + parentIds.get(0) + "' ");
        for (int i = 1; i < parentIds.size(); i++) {
            stringBuilder.append("or Inputid eq '" + parentIds.get(i) + "' ");
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private static String getLanguage(String language) {
        return "(Lang eq '" + language + "')";
    }

    private static String getLicenseType(String licenseType) {
        return "(Lictype eq '" + licenseType + "')";
    }
}
