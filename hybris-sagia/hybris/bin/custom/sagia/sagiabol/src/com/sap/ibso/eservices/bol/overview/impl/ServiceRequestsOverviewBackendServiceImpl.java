package com.sap.ibso.eservices.bol.overview.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.sap.ibso.eservices.bol.overview.ServiceRequestsOverviewBackendService;
import com.sap.ibso.eservices.bol.overview.data.ServiceRequestBackendData;
import com.sap.ibso.eservices.bol.overview.data.ServiceRequestsBackendData;
import com.sap.ibso.eservices.bol.util.ConversionUtil;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import com.sap.ibso.eservices.core.model.ServiceTypeModel;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Implements synchronous access to an overview of e-service requests in SAP backend system.
 */
public class ServiceRequestsOverviewBackendServiceImpl extends BackendBusinessObjectBaseJCo implements ServiceRequestsOverviewBackendService
{
    private static final Logger LOGGER = LogManager.getLogger(ServiceRequestsOverviewBackendServiceImpl.class);

    // Function module name
    private static final String ZFM_ESRV_ENT_SRVREQ_GET = "ZFM_ESRV_ENT_SRVREQ_GET";

    @Override
    public ServiceRequestsBackendData getServiceRequests(String entityId,
                                                         Map<String, ServiceTypeModel> serviceTypesByCodes,
                                                         Set<String> serviceTypeCategoryCodes)
    {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(ZFM_ESRV_ENT_SRVREQ_GET);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();

            importParameters.setValue("IV_ENTITY_ID", ConversionUtil.addLeadingZeros(entityId, 10)); // ABAP type: Char(10)
            importParameters.setValue("IV_LANGU", "EN"); // Hard-coded as language dependent texts are maintained in Hybris
            JCoTable processTypeTable = importParameters.getTable("IT_PROC_TYPE");

            for (ServiceTypeModel serviceType : serviceTypesByCodes.values())
            {
                processTypeTable.appendRow();
                processTypeTable.setValue("PROCESS_TYPE", serviceType.getCode());
                // Indicate whether category information is required for the current service/process type
                processTypeTable.setValue("CATEG_NEED_FLAG", ConversionUtil.toFlag(serviceType.getCategoryConsideration()));
            }

            // Execute function module
            connection.execute(function);

            // Get export parameters
            JCoParameterList exportParameters = function.getExportParameterList();

            JCoTable serviceRequestTable = exportParameters.getTable("ET_SRVREQ_DETAILS");
            JCoTable messageTable = exportParameters.getTable("ET_MESSAGE");

            return createServiceRequestsBackendData(serviceRequestTable, messageTable, serviceTypesByCodes, serviceTypeCategoryCodes);
        } catch (final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to retrieve overview data of e-service requests for an entity.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for a list of service requests considering overview relevant service type categories.
     *
     * @param serviceRequestTable contains service request (overview) data
     * @param messageTable        contains backend messages (if present)
     * @param serviceTypesByCodes the overview relevant service types
     * @param categoryCodes       the service type category codes for overview relevant service types
     * @return the service requests (overview) backend data
     */
    private static ServiceRequestsBackendData createServiceRequestsBackendData(JCoTable serviceRequestTable,
                                                                               JCoTable messageTable,
                                                                               Map<String, ServiceTypeModel> serviceTypesByCodes,
                                                                               Set<String> categoryCodes)
    {
        ServiceRequestsBackendData serviceRequestsBackendData = new ServiceRequestsBackendData();

        serviceRequestsBackendData.setServiceRequests(processServiceRequestTable(serviceRequestTable, serviceTypesByCodes, categoryCodes));
        serviceRequestsBackendData.setMessages(MessageUtil.getMessages(messageTable));

        return serviceRequestsBackendData;
    }

    /**
     * Processes the service request table line by line.
     *
     * @param serviceRequestTable contains service request (overview) data
     * @param serviceTypesByCodes the overview relevant service types
     * @param categoryCodes       the service type category codes for overview relevant service types
     * @return the list of service requests
     */
    /* Suppress Sonar warning: the cyclomatic complexity of methods should not exceed a defined threshold.
     * Reason: the cyclomatic complexity of this method seems still to be manageable in the given context.
     * Suppress Sonar warning: squid:S134 | control flow statements "if", "for", "while", "switch" and "try" should not be nested too deeply.
     * Reason: Code readability seems still to be manageable in the given context.
     * Suppress Sonar warning: squid:S135 | loops should not contain more than a single "break" or "continue" statement
     * Reason: Code readability seems still to be manageable in the given context.
     */
    @SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S134", "squid:S135"})
    private static List<ServiceRequestBackendData> processServiceRequestTable(JCoTable serviceRequestTable,
                                                                              Map<String, ServiceTypeModel> serviceTypesByCodes,
                                                                              Set<String> categoryCodes)
    {
        // Process service request table to retrieve service request overview data for each service request
        if (serviceRequestTable.getNumRows() > 0)
        {
            List<ServiceRequestBackendData> serviceRequests = new ArrayList<>();

            // Process table: ET_SRVREQ_DETAILS
            do
            {
                ServiceRequestBackendData serviceRequest = new ServiceRequestBackendData();

                serviceRequest.setServiceRequestId(ConversionUtil.removeLeadingZeros(serviceRequestTable.getString("OBJECT_ID")));
                serviceRequest.setServiceRequestCreationDate(ConversionUtil.toLocalDate(serviceRequestTable.getString("REQ_DATE")));
                /*
                 * STATUS_TXT in lowercase letters is used as service request status code,
                 * because STATUS_KEY is not unique with respect to service types and categories.
                 */
                String statusText = serviceRequestTable.getString("STATUS_TXT");
                serviceRequest.setServiceRequestStatusCode(statusText != null ? statusText.toLowerCase(Locale.ENGLISH) : "");

                String serviceTypeCode = serviceRequestTable.getString("PROC_TYPE");
                serviceRequest.setServiceTypeCode(serviceTypeCode);

                ServiceTypeModel serviceTypeModel = serviceTypesByCodes.get(serviceTypeCode);
                boolean filterCategories = serviceTypeModel.getCategoryConsideration();

                if (filterCategories) // Consider categories for the current service type?
                {
                    /* If a service request retrieved from SAP CRM backend has category assignments
                     * then all these categories must be maintained in Hybris, otherwise the service request is
                     * considered as not e-services relevant.
                     *
                     * Furthermore, if categories have to be considered for a service type then a service request for
                     * this service type must also have at least one category assignment, other the service request is
                     * considered as not e-services relevant.
                     */

                    String categoryOneCode = serviceRequestTable.getString("CAT1_ID");
                    serviceRequest.setCategoryLevelOneCode(categoryOneCode);
                    if (StringUtils.hasLength(categoryOneCode) && !categoryCodes.contains(categoryOneCode))
                    {
                        // Skip service request as the category for the overview relevant service type is not maintained in Hybris
                        continue;
                    }

                    String categoryTwoCode = serviceRequestTable.getString("CAT2_ID");
                    serviceRequest.setCategoryLevelTwoCode(categoryTwoCode);
                    if (StringUtils.hasLength(categoryTwoCode) && !categoryCodes.contains(categoryTwoCode))
                    {
                        // Skip service request as the category for the overview relevant service type is not maintained in Hybris
                        continue;
                    }

                    String categoryThreeCode = serviceRequestTable.getString("CAT3_ID");
                    serviceRequest.setCategoryLevelThreeCode(categoryThreeCode);
                    if (StringUtils.hasLength(categoryThreeCode) && !categoryCodes.contains(categoryThreeCode))
                    {
                        // Skip service request as the category for the overview relevant service type is not maintained in Hybris
                        continue;
                    }

                    String categoryFourCode = serviceRequestTable.getString("CAT4_ID");
                    serviceRequest.setCategoryLevelFourCode(categoryFourCode);
                    if (StringUtils.hasLength(categoryFourCode) && !categoryCodes.contains(categoryFourCode))
                    {
                        // Skip service request as the category for the overview relevant service type is not maintained in Hybris
                        continue;
                    }

                    if (!StringUtils.hasLength(categoryOneCode) &&
                            !StringUtils.hasLength(categoryTwoCode) &&
                            !StringUtils.hasLength(categoryThreeCode) &&
                            !StringUtils.hasLength(categoryFourCode))
                    {
                        // Skip service request as there is no category assignment at all (although categories shall be considered)
                        continue;
                    }
                }
                else
                {
                    // Category information is ignored for the current service type
                    serviceRequest.setCategoryLevelOneCode(null);
                    serviceRequest.setCategoryLevelTwoCode(null);
                    serviceRequest.setCategoryLevelThreeCode(null);
                    serviceRequest.setCategoryLevelFourCode(null);
                }

                serviceRequests.add(serviceRequest);
            }
            while (serviceRequestTable.nextRow());

            return serviceRequests;
        }
        else
        {
            // No service request overview data available
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug("No service request overview data retrieved from function module " + ZFM_ESRV_ENT_SRVREQ_GET);
            }

            return Collections.emptyList();
        }
    }
}
