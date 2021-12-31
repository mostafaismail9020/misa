package com.sap.ibso.eservices.storefront.controllers.pages.exception;

public class UsernameMismatchException extends Exception
{
    public UsernameMismatchException(final String message)
    {
        super(message);
    }

    public UsernameMismatchException(final Throwable cause)
    {
        super(cause);
    }

    public UsernameMismatchException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
