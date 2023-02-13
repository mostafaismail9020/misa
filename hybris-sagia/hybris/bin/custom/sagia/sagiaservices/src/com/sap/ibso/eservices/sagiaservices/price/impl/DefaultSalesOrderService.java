package com.sap.ibso.eservices.sagiaservices.price.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.price.SalesOrderBackendService;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.sagiaservices.price.SalesOrderParam;
import com.sap.ibso.eservices.sagiaservices.price.SalesOrderService;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides RFC implementation access to e-services related sales orders.
 */
public class DefaultSalesOrderService extends BackendAwareService implements SalesOrderService {

    private static final Logger LOGGER = LogManager.getLogger(DefaultSalesOrderService.class);
    /**
     * Creates an backend aware service instance.
     *
     * @param backendServiceBeanName the backend service bean name
     */
    public DefaultSalesOrderService(String backendServiceBeanName) {
        super(backendServiceBeanName);
    }

    @Override
    public boolean afterPaymentUpdate(SalesOrderParam salesOrderParam) {
        // Backend service access
        SalesOrderBackendService backendService = getBackendService();
        LOGGER.info("Updating sales order "+salesOrderParam.getSalesOrderId()+" in CRM");
        try{
            return backendService.afterPaymentUpdate(convert(salesOrderParam));
        }
        catch (ApplicationBaseRuntimeException exception){
            LOGGER.error("Sales order "+salesOrderParam.getSalesOrderId()+" has not been marked as payed in CRM.",exception);
            return false;
        }
    }

    public com.sap.ibso.eservices.bol.price.SalesOrderParam convert(SalesOrderParam salesOrderParam){
        com.sap.ibso.eservices.bol.price.SalesOrderParam convertedParam = new com.sap.ibso.eservices.bol.price.SalesOrderParam();
        //convertedParam.setAmountPayed(salesOrderParam.getSalesOrderAmount()); Commented as per CRM request
        convertedParam.setAmountPayed(salesOrderParam.getTotalAmountPayed());
        convertedParam.setSalesOrderId(salesOrderParam.getSalesOrderId());
        convertedParam.setInvestorId(salesOrderParam.getInvestorId());
        convertedParam.setTransactionNumber(salesOrderParam.getTransactionNumber());
        convertedParam.setTransactionTime(salesOrderParam.getTransactionTime());
        return  convertedParam;
    }
}
