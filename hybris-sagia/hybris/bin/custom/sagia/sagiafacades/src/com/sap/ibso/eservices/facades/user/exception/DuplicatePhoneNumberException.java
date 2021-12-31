package com.sap.ibso.eservices.facades.user.exception;

/**
 * Indicates that a country code / mobile number combination already exists for a user (as customer)
 * @package com.sap.ibso.eservices.facades.user.exception
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DuplicatePhoneNumberException extends RuntimeException {
    /**
     * @param e
     */
    public DuplicatePhoneNumberException(Exception e) {
        super(e);
    }

    /**
     * Constructor DuplicatePhoneNumberException
     * @param message the message
     */
    public DuplicatePhoneNumberException(final String message) {
        super(message);
    }

    public DuplicatePhoneNumberException(String message, Exception e) {
        super(message, e);
    }
}
