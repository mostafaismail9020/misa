package com.sap.ibso.eservices.sagiaservices.services.authentication;


/**
 * AuthenticationCodeGeneratorService
 */
public interface AuthenticationCodeGeneratorService {

    /**
     * getGeneratedAuthenticationCode
     * @return a random generated six aplhanumeric code used for authentication
     */
    String getGeneratedAuthenticationCode();
}
