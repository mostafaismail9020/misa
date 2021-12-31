/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 * <p>
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 * <p>
 * SAP
 * <p>
 * <p>
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.facades.user.exception;

import com.sap.ibso.eservices.core.sagia.enums.ValidationError;

import java.util.List;

/**
 * RegistrationValidationFailException
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.user.exception
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class RegistrationValidationFailException extends RuntimeException {
    private final List<ValidationError> errors;

    public RegistrationValidationFailException(final String message, final List<ValidationError> errors) {
        super(message);
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

}
