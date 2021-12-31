package com.sap.ibso.eservices.sagiaservices.auth.impl.exception;

/**
 * Indicates that credential verification was not executed due to technical error.
 */
public class CredentialVerificationException extends RuntimeException
{
    /**
     * Creates a credential verification exception with a message.
     *
     * @param message the message
     */
    public CredentialVerificationException(String message)
    {
        super(message);
    }

    /**
     * Creates a credential verification exception with message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public CredentialVerificationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
