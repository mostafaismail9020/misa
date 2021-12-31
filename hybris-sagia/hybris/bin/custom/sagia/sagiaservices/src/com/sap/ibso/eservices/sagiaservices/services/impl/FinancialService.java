package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FinanceHDRS;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaODataException;

import java.util.Collection;

import static com.sap.ibso.eservices.sagiaservices.constants.SagiaservicesConstants.REQUEST_PARAMETER_EXPAND;

/**
 * FinancialService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class FinancialService extends AbstractSagiaService<FinanceHDRS> {
    private static final String EXPAND_VALUE = "FinanceHDRToUI5ChangeEquityNav,FinanceHDRToUI5BalanceSheetNav,FinanceHDRToUI5IncomeStatNav,FinanceHDRToContentNav";

    /**
     * Get the financial entities from CRM
     * @return - A list of all financial entities
     * @throws SagiaODataException - Thrown when an error is faced between the Hybris and CRM communication or something wrong happened in the OData logic.
     */
    public Collection<FinanceHDRS> getFinancialEntities() throws SagiaODataException {
        return super.getCollection(FinanceHDRS.class);
    }

    /**
     * Get a financial entities from CRM by id
     * @param id id
     * @return - A financial entity with that specific id
     * @throws SagiaODataException - Thrown when an error is faced between the Hybris and CRM communication or something wrong happened in the OData logic.
     */
    public FinanceHDRS getFinancialData(Object id) throws SagiaODataException {
        return super.get(FinanceHDRS.class,id,REQUEST_PARAMETER_EXPAND,EXPAND_VALUE);
    }
}
