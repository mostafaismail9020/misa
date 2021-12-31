package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.financial.*;
import com.sap.ibso.eservices.facades.populators.FinancialPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaFinancialFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FinanceHDRS;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaFinancialFacade
 */
public class DefaultSagiaFinancialFacade implements SagiaFinancialFacade {

    @Autowired
    private FinancialPopulator sagiaFinancialPopulator;

    @Autowired
    private ZUI5SagiaFacade zui5SagiaFacade;

    /**
     * Get all the financial entities.
     *
     * @return - A list of financial entities.
     * @throws IOException
     */
    @Override
    public List<FinancialData> getFinancialEntities() {
        Collection<FinanceHDRS> financeHDRS = zui5SagiaFacade.getFinancialEntities();
        List<FinancialData> financialData = new ArrayList<>();

        for(FinanceHDRS finance : financeHDRS)
        {
            FinancialData financialDataDTO = new FinancialData();
            sagiaFinancialPopulator.populate(finance,financialDataDTO);
            financialData.add(financialDataDTO);
        }

        return financialData;
    }

    /**
     * Get a specific financial entity.
     *
     * @param id - The id of the financial entity from CRM.
     * @return - The financial entity with that id.
     * @throws IOException
     */
    @Override
    public FinancialData getFinancialData(Object id) {
        FinanceHDRS data = zui5SagiaFacade.getFinancialData(id);
        FinancialData financialData = new FinancialData();
        sagiaFinancialPopulator.populate(data,financialData);

        return financialData;
    }

    /**
     * @return
     */
    public FinancialPopulator getSagiaFinancialPopulator() {
        return sagiaFinancialPopulator;
    }

    /**
     * @param sagiaFinancialPopulator
     */
    public void setSagiaFinancialPopulator(FinancialPopulator sagiaFinancialPopulator) {
        this.sagiaFinancialPopulator = sagiaFinancialPopulator;
    }

    /**
     * @return
     */
    public ZUI5SagiaFacade getZui5SagiaFacade() {
        return zui5SagiaFacade;
    }

    /**
     * @param zui5SagiaFacade
     */
    public void setZui5SagiaFacade(ZUI5SagiaFacade zui5SagiaFacade) {
        this.zui5SagiaFacade = zui5SagiaFacade;
    }
}
