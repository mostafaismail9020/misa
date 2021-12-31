package com.sap.ibso.eservices.sagiaservices.services.license.application;

/**
 * Indicates that the investor registration as license applicant failed.
 */
public class InvestorRegistrationException extends RuntimeException
{
    /**
     * Creates an investor registration exception with a message.
     *
     * @param message the message
     */
    public InvestorRegistrationException(String message)
    {
        super(message);
    }

    /**
     * Creates an investor registration exception with message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public InvestorRegistrationException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
