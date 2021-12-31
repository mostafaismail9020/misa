package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.facades.data.financial.FinancialData;

import java.io.IOException;
import java.util.List;

/**
 * SagiaFinancialFacade
 */
public interface SagiaFinancialFacade {

    /**
     * Get all the financial entities.
     *
     * @return - A list of financial entities.
     * @throws IOException exception
     */
    List<FinancialData> getFinancialEntities();

    /**
     * Get a specific financial entity.
     *
     * @param id - The id of the financial entity from CRM.
     * @return - The financial entity with that id.
     * @throws IOException exception
     */
    FinancialData getFinancialData(Object id);
}
