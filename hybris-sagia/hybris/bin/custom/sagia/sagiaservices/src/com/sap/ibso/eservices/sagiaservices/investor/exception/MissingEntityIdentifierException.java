package com.sap.ibso.eservices.sagiaservices.investor.exception;

/**
 * Indicates that retrieving the entity identifier associated with a user failed.
 */
public class MissingEntityIdentifierException extends RuntimeException
{
    /**
     * Creates a missing entity identifier exception with a message.
     *
     * @param message the message
     */
    public MissingEntityIdentifierException(String message)
    {
        super(message);
    }

    /**
     * Creates a missing entity identifier exception with message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public MissingEntityIdentifierException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
