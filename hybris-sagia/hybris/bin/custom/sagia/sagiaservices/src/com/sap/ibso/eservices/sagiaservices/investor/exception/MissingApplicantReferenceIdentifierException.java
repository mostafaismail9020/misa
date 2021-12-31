package com.sap.ibso.eservices.sagiaservices.investor.exception;

/**
 * Indicates that retrieving the applicant reference identifier associated with a user failed.
 */
public class MissingApplicantReferenceIdentifierException extends RuntimeException
{
    /**
     * Creates a missing applicant reference identifier exception with a message.
     *
     * @param message the message
     */
    public MissingApplicantReferenceIdentifierException(String message)
    {
        super(message);
    }

    /**
     * Creates a missing applicant reference identifier exception with message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public MissingApplicantReferenceIdentifierException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
