package com.sap.ibso.eservices.bol.duration.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.ibso.eservices.bol.duration.AverageProcessingTimeBackendService;
import com.sap.ibso.eservices.bol.duration.data.AverageProcessingTimeBackendData;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;

/**
 * Implements synchronous access to e-services related average processing time information in SAP backend system.
 */
public class AverageProcessingTimeBackendServiceImpl extends BackendBusinessObjectBaseJCo implements AverageProcessingTimeBackendService
{
    // Function module name
    private static final String Z_CRM_CALC_AVG_SERVICE_TIME = "Z_CRM_CALC_AVG_SERVICE_TIME";

    @Override
    public AverageProcessingTimeBackendData getAverageProcessingTimeData(String serviceType)
    {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(Z_CRM_CALC_AVG_SERVICE_TIME);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();
            importParameters.setValue("IV_PROCESS_TYPE", serviceType);

            // Execute function module
            connection.execute(function);

            // Get export parameters & create result
            return createAverageProcessingTimeBackendData(function.getExportParameterList());
        }
        catch(final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to retrieve average processing time data.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for average processing time information.
     *
     * @param exportParameters contain days, hours, minutes and seconds information
     * @return the average processing time backend data
     */
    private static AverageProcessingTimeBackendData createAverageProcessingTimeBackendData(JCoParameterList exportParameters)
    {
        AverageProcessingTimeBackendData averageProcessingTimeBackendData = new AverageProcessingTimeBackendData();

        averageProcessingTimeBackendData.setDays(exportParameters.getInt("EV_DAYS"));
        averageProcessingTimeBackendData.setHours(exportParameters.getInt("EV_HOURS"));
        averageProcessingTimeBackendData.setMinutes(exportParameters.getInt("EV_MINUTES"));
        averageProcessingTimeBackendData.setSeconds(exportParameters.getInt("EV_SECONDS"));
        averageProcessingTimeBackendData.setDescription(exportParameters.getString("EV_DURATION_TEXT"));

        return averageProcessingTimeBackendData;
    }
}
