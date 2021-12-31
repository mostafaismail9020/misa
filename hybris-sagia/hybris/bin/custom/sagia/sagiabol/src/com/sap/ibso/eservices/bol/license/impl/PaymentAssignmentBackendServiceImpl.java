package com.sap.ibso.eservices.bol.license.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.sap.ibso.eservices.bol.license.BackendServiceType;
import com.sap.ibso.eservices.bol.license.PaymentAssignmentBackendService;
import com.sap.ibso.eservices.bol.license.data.PaymentAssignmentBackendData;
import com.sap.ibso.eservices.bol.util.ConversionUtil;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;

/**
 * Implements synchronous access to payment information assignments to e-services in SAP backend system.
 */
public class PaymentAssignmentBackendServiceImpl extends BackendBusinessObjectBaseJCo implements PaymentAssignmentBackendService
{
    // Function module name
    private static final String ZFM_ESRV_SR_PAYMENT_TRANS_ID = "ZFM_ESRV_SR_PAYMENT_TRANS_ID";

    @Override
    public PaymentAssignmentBackendData assignPaymentInformation(String serviceId, BackendServiceType serviceType, String transactionId, String entityId)
    {
        return assignPaymentInformation(serviceId, serviceType.toString(), transactionId, entityId);
    }

    @Override
    public PaymentAssignmentBackendData assignPaymentInformation(String serviceId, String serviceType, String transactionId, String entityId)
    {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(ZFM_ESRV_SR_PAYMENT_TRANS_ID);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();

            importParameters.setValue("IV_OBJECT_ID", ConversionUtil.addLeadingZeros(serviceId, 10)); // ABAP type: Char(10)
            importParameters.setValue("IV_SRV_TYPE", serviceType);
            importParameters.setValue("IV_PAYMT_TRANS_ID", transactionId);
            importParameters.setValue("IV_ENTITY_ID", ConversionUtil.addLeadingZeros(entityId, 10)); // ABAP type: Char(10)

            // Execute function module
            connection.execute(function);

            // Get export parameters
            JCoParameterList exportParameters = function.getExportParameterList();
            boolean successful = "X".equals(exportParameters.getString("EV_SUCCESS")) ? true : false;
            JCoTable messageTable = exportParameters.getTable("ET_RETURN");

            return createPaymentAssignmentBackendData(successful, messageTable);
        } catch (final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to assign a payment transaction identifier to an e-service.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for a backend payment assignment.
     *
     * @param successful   <code>true</code> if the payment transaction identifier assignment was successful, <code>false</code> otherwise
     * @param messageTable contains backend messages (if present)
     * @return the payment assignment backend data
     */
    private static PaymentAssignmentBackendData createPaymentAssignmentBackendData(boolean successful, JCoTable messageTable)
    {
        PaymentAssignmentBackendData paymentAssignmentBackendData = new PaymentAssignmentBackendData();

        paymentAssignmentBackendData.setAssignmentSuccessful(successful);
        paymentAssignmentBackendData.setMessages(MessageUtil.getMessages(messageTable));

        return paymentAssignmentBackendData;
    }
}
