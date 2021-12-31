package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.employment.data.EmploymentData;
import com.sap.ibso.eservices.facades.sagia.SagiaEmploymentFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.HomeHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SalAndEmpData;

import java.math.BigDecimal;

/**
 * DefaultSagiaEmploymentFacade
 */
public class DefaultSagiaEmploymentFacade implements SagiaEmploymentFacade {
    /**
     * Splits an employment DTO from the whole information of dashboard contained in a HomeHDRData object
     *
     * @param homeHDRData - Whole dashboard data
     * @return - Employment DTO for the dashboard
     */
    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity"})
    @Override
    public EmploymentData getEmploymentData(HomeHDRData homeHDRData) {
        EmploymentData employmentData = new EmploymentData();
        if (homeHDRData != null) {
            SalAndEmpData salAndEmp = homeHDRData.getSalAndEmpData();
            if (salAndEmp.getExpatSal() != null) {
                employmentData.setExpatAverage(salAndEmp.getExpatSal());
            }
            if (!isEmpty(salAndEmp.getKsaNo())) {
                employmentData.setNoOfSaudiEmployees(Integer.parseInt(salAndEmp.getKsaNo()));
            }
            if (!isEmpty(salAndEmp.getExpNo()) && !isEmpty(salAndEmp.getKsaNo())) {
                employmentData.setTotalNoOfEmployees(Integer.parseInt(salAndEmp.getExpNo()) + Integer.parseInt(salAndEmp.getKsaNo()));
            }
            if (employmentData.getTotalNoOfEmployees() != null && employmentData.getNoOfSaudiEmployees() != null) {
                employmentData.setNoOfExpatEmployees(employmentData.getTotalNoOfEmployees() - employmentData.getNoOfSaudiEmployees());
            }
            if (salAndEmp.getKsaSal() != null) {
                employmentData.setSaudiAverage(salAndEmp.getKsaSal());
            }
            if (salAndEmp.getExpatSal() != null) {
                employmentData.setExpatAverage(salAndEmp.getExpatSal());
            }
            if (salAndEmp.getKsaSal() != null && salAndEmp.getExpatSal() != null) {
                employmentData.setOverallAverage(salAndEmp.getKsaSal().add(salAndEmp.getExpatSal()).divide(new BigDecimal(2)));
            }
            if (!isEmpty(salAndEmp.getMale_emp())) {
                employmentData.setNoOfMale(Integer.parseInt(salAndEmp.getMale_emp()));
            }
            if (!isEmpty(salAndEmp.getMale_emp()) && !isEmpty(salAndEmp.getFemales_emp())){
                employmentData.setTotalNoGender(Integer.parseInt(salAndEmp.getMale_emp()) + Integer.parseInt(salAndEmp.getFemales_emp()));
            }
            if (!isEmpty(salAndEmp.getFemales_emp())) {
                employmentData.setNoOfFemale(Integer.parseInt(salAndEmp.getFemales_emp()));
            }
        }
        return employmentData;
    }

    private static boolean isEmpty(final String s ) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }

}
