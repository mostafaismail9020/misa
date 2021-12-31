package com.sap.ibso.eservices.bol.account.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.ibso.eservices.bol.account.AccountManagerBackendService;
import com.sap.ibso.eservices.bol.account.data.DedicatedAccountManagerBackendData;
import com.sap.ibso.eservices.bol.util.ConversionUtil;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;


/**
 * Implements synchronous access to e-services related account manager data in SAP backend system.
 */
public class AccountManagerBackendServiceImpl extends BackendBusinessObjectBaseJCo implements AccountManagerBackendService
{
    // Function module name
    private static final String Z_ESRV_KEY_ACCT_MANAGER_GET = "Z_ESRV_KEY_ACCT_MANAGER_GET";

    @Override
    public DedicatedAccountManagerBackendData getDedicatedAccountManagerData(String entityId)
    {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(Z_ESRV_KEY_ACCT_MANAGER_GET);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();
            importParameters.setValue("IV_ENTITY", ConversionUtil.addLeadingZeros(entityId, 10)); // ABAP type: Char(10)

            // Execute function module
            connection.execute(function);

            // Get export parameters & create result
            return createDedicatedAccountManagerBackendData(function.getExportParameterList());
        }
        catch(final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to retrieve dedicated account manager data.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for dedicated account manager information.
     *
     * @param exportParameters contain title, first name, last name and email address of the dedicated account manager
     * @return the dedicated account manager backend data
     */
    private static DedicatedAccountManagerBackendData createDedicatedAccountManagerBackendData(JCoParameterList exportParameters)
    {
        DedicatedAccountManagerBackendData dedicatedAccountManagerBackendData = new DedicatedAccountManagerBackendData();

        dedicatedAccountManagerBackendData.setTitle(exportParameters.getString("EV_TITLE"));
        dedicatedAccountManagerBackendData.setFirstName(exportParameters.getString("EV_FNAME"));
        dedicatedAccountManagerBackendData.setLastName(exportParameters.getString("EV_LNAME"));
        dedicatedAccountManagerBackendData.setEmail(exportParameters.getString("EV_EMAIL"));
        dedicatedAccountManagerBackendData.setPhoneNumber(exportParameters.getString("EV_TELE"));
        dedicatedAccountManagerBackendData.setMobileNumber(exportParameters.getString("EV_MOBILE"));

        return dedicatedAccountManagerBackendData;
    }
}
