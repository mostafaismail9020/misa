package com.sap.ibso.eservices.sagiaservices.services.isicdetails.dto;


import java.util.Objects;

public class ISICDetailsRequestParameters {


    private String language;
    private String flag;
    private String lictype;
    private String section;
    private String division;
    private String activity;
    private String complimentary;

    private static final String DEFAULT_VALUE = "' '";

    public String getLanguage() {
        return language;
    }

    public ISICDetailsRequestParameters setLanguage(String language) {
        if (Objects.isNull(language) || language.isEmpty()) {
            this.language = DEFAULT_VALUE;
        } else {
            this.language = quote(language);
        }
        return this;
    }

    public String getFlag() {
        return flag;
    }

    public ISICDetailsRequestParameters setFlag(String flag) {
        if (Objects.isNull(flag) || flag.isEmpty()) {
            this.flag = DEFAULT_VALUE;
        } else {
            this.flag = quote(flag);
        }
        return this;
    }

    public String getLictype() {
        return lictype;
    }

    public ISICDetailsRequestParameters setLictype(String lictype) {
        if (Objects.isNull(lictype) || lictype.isEmpty()) {
            this.lictype = DEFAULT_VALUE;
        } else {
            this.lictype = quote(lictype);
        }
        return this;
    }

    public String getSection() {
        return section;
    }

    public ISICDetailsRequestParameters setSection(String section) {
        if (Objects.isNull(section) || section.isEmpty()) {
            this.section = DEFAULT_VALUE;
        } else {
            this.section = quote(section);
        }
        return this;
    }

    public String getDivision() {
        return division;
    }

    public ISICDetailsRequestParameters setDivision(String division) {
        if (Objects.isNull(division) || division.isEmpty()) {
            this.division = DEFAULT_VALUE;
        } else {
            this.division = quote(division);
        }
        return this;
    }

    public String getActivity() {
        return activity;
    }

    public ISICDetailsRequestParameters setActivity(String activity) {
        if (Objects.isNull(activity) || activity.isEmpty()) {
            this.activity = DEFAULT_VALUE;
        } else {
            this.activity = quote(activity);
        }
        return this;
    }

    public String getComplimentary() {
        return complimentary;
    }

    public ISICDetailsRequestParameters setComplimentary(String complimentary) {
        if (Objects.isNull(complimentary) || complimentary.isEmpty()) {
            this.complimentary = DEFAULT_VALUE;
        } else {
            this.complimentary = quote(complimentary);
        }
        return this;
    }

    private String quote(String param){
        return new StringBuilder().append("'").append(param).append("'").toString();
    }
}
