package com.sap.ibso.eservices.storefront.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Indicates that authentication failed due to technical error.
 */
public class SagiaAuthenticationException extends AuthenticationException
{
    /**
     * Creates an authentication exception with a message.
     *
     * @param message the message
     */
    public SagiaAuthenticationException(String message)
    {
        super(message);
    }

    /**
     * Creates an authentication exception with message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public SagiaAuthenticationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
