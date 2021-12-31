package com.sap.ibso.eservices.bol.price.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.sap.ibso.eservices.bol.price.SalesOrderBackendService;
import com.sap.ibso.eservices.bol.price.SalesOrderParam;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.Assert;

/**
 * Implements synchronous access to e-services related sales orders in SAP backend system.
 */
public class SalesOrderBackendServiceImpl extends BackendBusinessObjectBaseJCo implements SalesOrderBackendService {

    // Function module name
    private static final String ZESRV_SO_PAYMENT_UPDATE = "ZESRV_SO_PAYMENT_UPDATE";

    private static final String SUCCESS = "success";

    @Override
    public boolean afterPaymentUpdate(SalesOrderParam salesOrderParam) {
        try
        {
            Assert.isTrue(Strings.isNotEmpty(salesOrderParam.getSalesOrderId()),"Sales order id cannot be empty.");
            Assert.isTrue(Strings.isNotEmpty(salesOrderParam.getTransactionNumber()),"Transaction number cannot be empty.");

            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(ZESRV_SO_PAYMENT_UPDATE);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();

            importParameters.setValue("TRANS_NUMBER", salesOrderParam.getTransactionNumber());
            importParameters.setValue("SALES_ORDER_ID", salesOrderParam.getSalesOrderId());
            importParameters.setValue("AMOUNT_PAID", salesOrderParam.getAmountPayed().toString());
            importParameters.setValue("INVESTOR_ID", salesOrderParam.getInvestorId());
            importParameters.setValue("TRANSACTION_DATE", salesOrderParam.getTransactionTime().toString());
            importParameters.setValue("PAYMENT_METHOD", salesOrderParam.getPaymentMethod());

            // Execute function module
            connection.execute(function);

            // Get export parameters
            JCoParameterList exportParameters = function.getExportParameterList();
            String returnParameter = exportParameters.getString("RETURN");

            return returnParameter.toLowerCase().contains(SUCCESS);
        }
        catch(final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to update sale orders";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

}
