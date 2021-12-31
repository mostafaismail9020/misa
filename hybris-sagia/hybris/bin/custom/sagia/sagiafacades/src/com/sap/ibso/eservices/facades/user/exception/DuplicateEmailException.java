package com.sap.ibso.eservices.facades.user.exception;

/**
 *
 * DuplicateEmailException
 * @package com.sap.ibso.eservices.facades.user.exception
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DuplicateEmailException extends RuntimeException
{
    /**
     * @param e
     */
    public DuplicateEmailException(Exception e)
    {
        super(e);
    }

    /**
     * Constructor DuplicateEmailException
     * @param message the message
     */
    public DuplicateEmailException(final String message)
    {
        super(message);
    }
}
