package com.sap.ibso.eservices.sagiaservices.auth;

import com.sap.ibso.eservices.sagiaservices.auth.impl.exception.CredentialVerificationException;

/**
 * Provides credential verification against an external system based on the current base store configuration.
 */
public interface CredentialVerificationService
{
    /**
     * Checks whether user name and password are valid authentication credentials for an external system targeted via
     * an HTTP(s) destination URL of the SAP configuration of the current base store.
     *
     * @param userName the user name
     * @param password the password
     * @return {@code true} if credentials are valid, {@code false} otherwise
     * @throws CredentialVerificationException if a technical error occurs
     */
    boolean isValid(String userName, String password);
}
