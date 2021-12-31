package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GovtDropdown;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;

import java.io.IOException;
import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_FILTER;

/**
 * AmanahService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class AmanahService extends AbstractSagiaService<GovtDropdown> {

    /**
     * Get the values for the Amanah dropdown
     *
     * @return - A collection of Government items
     * @throws IOException exception
     */
    public Collection<GovtDropdown> getCollection() throws SagiaODataException {
        return super.getCollection(GovtDropdown.class, REQUEST_PARAMETER_FILTER, "Key eq 'A'");
    }
}
