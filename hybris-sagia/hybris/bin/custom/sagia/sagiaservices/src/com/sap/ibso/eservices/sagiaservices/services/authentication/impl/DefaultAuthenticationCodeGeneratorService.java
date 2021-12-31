package com.sap.ibso.eservices.sagiaservices.services.authentication.impl;


import com.sap.ibso.eservices.sagiaservices.services.authentication.AuthenticationCodeGeneratorService;

/**
 * DefaultAuthenticationCodeGeneratorService
 */
public class DefaultAuthenticationCodeGeneratorService implements AuthenticationCodeGeneratorService {

    private static final int MAX = 9999;
    private static final int MIN = 1000;

    @Override
    public String getGeneratedAuthenticationCode() {
        return String.valueOf((int) (Math.random() * ((MAX - MIN) + 1)) + MIN);
    }
}
