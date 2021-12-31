package com.sap.ibso.eservices.bol.price.impl;

import static com.sap.ibso.eservices.bol.util.ConversionUtil.toFlag;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.ibso.eservices.bol.price.AmendmentBackendParam;
import com.sap.ibso.eservices.bol.price.PriceSimulationBackendService;
import com.sap.ibso.eservices.bol.price.RenewalBackendParam;
import com.sap.ibso.eservices.bol.price.data.PriceSimulationBackendData;
import com.sap.ibso.eservices.bol.price.data.PriceSimulationItemBackendData;
import com.sap.ibso.eservices.bol.util.ConversionUtil;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements synchronous access to e-services related price simulations in SAP backend system.
 */
public class PriceSimulationBackendServiceImpl extends BackendBusinessObjectBaseJCo implements PriceSimulationBackendService
{
    private static final Logger LOGGER = LogManager.getLogger(PriceSimulationBackendServiceImpl.class);

    // Function module name for Amendment and Renewal
    private static final String ZCRM_SRV_SO_PRICE_SIMULATION = "ZCRM_SRV_SO_PRICE_SIMULATION";

    // Function module name for License Apply
    private static final String ZCRM_PSP_SO_PRICE_SIMULATION = "ZCRM_PSP_SO_PRICE_SIMULATION";

    @Override
    public PriceSimulationBackendData getPriceSimulationData(String serviceType, String entityId, AmendmentBackendParam amendmentParam, RenewalBackendParam renewalParam, String language)
    {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(ZCRM_SRV_SO_PRICE_SIMULATION);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();
            JCoStructure request = importParameters.getStructure("IS_REQUEST");

            request.setValue("SR_TYPE", serviceType);
            request.setValue("BP_ID", ConversionUtil.addLeadingZeros(entityId, 10));

            // Amendment parameters are optional as they are not considered for all service types
            if (amendmentParam != null)
            {
                request.setValue("SH_RE_QU", toFlag(amendmentParam.isShareholderShareRedistribution()));
                request.setValue("SH_REM", toFlag(amendmentParam.isShareholderRemoval()));
                request.setValue("SH_NAME_CH", toFlag(amendmentParam.isShareholderNameChange()));
                request.setValue("SH_ADD_EX_NEW", toFlag(amendmentParam.isShareholderAddition()));
                request.setValue("SH_IN_PROP", toFlag(amendmentParam.isShareholderPropertyInheritance()));
                request.setValue("BR_OPEN", toFlag(amendmentParam.isBranchOpening()));
                request.setValue("BR_CLOSE", toFlag(amendmentParam.isBranchClosing()));
                request.setValue("PR_ADD_RE_CH", toFlag(amendmentParam.isProductChange()));
                request.setValue("EN_NAME_CH", toFlag(amendmentParam.isEntityNameChange()));
                request.setValue("EN_CAP_RED", toFlag(amendmentParam.isEntityCapitalReduction()));
                request.setValue("EN_INCR_WF", toFlag(amendmentParam.isEntityLaborIncreasing()));
                request.setValue("EN_CAP_INCR", toFlag(amendmentParam.isEntityCapitalIncreasing()));
                request.setValue("EN_ACT_CH", toFlag(amendmentParam.isEntityActivityChange()));
                request.setValue("EN_EST_TO_LLC", toFlag(amendmentParam.isEntityConversionToLimitedLiabilityCompany()));
                request.setValue("EN_LEGALSTAT_TO_ILLC", toFlag(amendmentParam.isEntityConversionToIndividualLimitedLiabilityCompany()));
                request.setValue("EN_LOC_CH", toFlag(amendmentParam.isEntityMainBranchLocationChange()));
                request.setValue("AMEND_BRCH_CREATE_COUNT", amendmentParam.getBranchNumberOfNewBranches());
            }

            // Renewal parameters are optional as they are not considered for all service types
            if (renewalParam != null)
            {
                request.setValue("RENEW_PERIODS", renewalParam.getRenewalPeriods()); // ABAP type: NUMC2 - two digit number (NUMC)
            }

            // Execute function module
            connection.execute(function);

            // Get export parameters
            JCoParameterList exportParameters = function.getExportParameterList();

            JCoTable priceTable = exportParameters.getTable("ET_PRICES");
            JCoTable messageTable = exportParameters.getTable("ET_MESSAGES");

            return createPriceSimulationBackendData(priceTable, language, messageTable);
        }
        catch(final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to perform a price simulation for e-services.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    @Override
    public PriceSimulationBackendData getPriceSimulationData(String serviceType, String qeemah, String language) {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(ZCRM_PSP_SO_PRICE_SIMULATION);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();
            JCoStructure request = importParameters.getStructure("IS_REQUEST");

            request.setValue("SR_TYPE", serviceType);
            request.setValue("QEEMAH_TYPE", qeemah);
            request.setValue("LANG", language.toUpperCase());

            // Execute function module
            connection.execute(function);

            // Get export parameters
            JCoParameterList exportParameters = function.getExportParameterList();

            JCoTable priceTable = exportParameters.getTable("ET_PRICES");
            JCoTable messageTable = exportParameters.getTable("ET_MESSAGES");

            return createPriceSimulationBackendData(priceTable, language, messageTable);
        }
        catch(final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to perform a price simulation for e-services.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for a price simulation.
     *
     * @param priceTable contains price simulation result data
     * @param language language ISO code to select the language dependent service name(s) from price simulation result data
     * @param messageTable contains backend messages (if present)
     * @return the price simulation backend data
     */
    private static PriceSimulationBackendData createPriceSimulationBackendData(JCoTable priceTable, String language, JCoTable messageTable)
    {
        PriceSimulationBackendData priceSimulationBackendData = new PriceSimulationBackendData();

        priceSimulationBackendData.setItems(processPriceTable(priceTable, language));
        priceSimulationBackendData.setMessages(MessageUtil.getMessages(messageTable));

        return priceSimulationBackendData;
    }

    /**
     * Processes the price simulation result data line by line.
     *
     * @param priceTable contains price simulation result data
     * @param language language ISO code to select the language dependent service name(s) from price simulation result data
     * @return the filtered (according to language ISO code) and sorted (according to service name) list of priced e-service(s)
     */
    private static List<PriceSimulationItemBackendData> processPriceTable(JCoTable priceTable, String language)
    {
        // Process price table to retrieve priced e-services
        if (priceTable.getNumRows() > 0)
        {
            List<PriceSimulationItemBackendData> items = new ArrayList<>();

            // Process table: ET_PRICES
            do
            {
                PriceSimulationItemBackendData item = new PriceSimulationItemBackendData();

                item.setServiceName(priceTable.getString("SERVICENAME"));
                item.setLanguage(priceTable.getString("LANGU"));
                item.setNetValue(priceTable.getBigDecimal("NET_VALUE"));
                item.setCurrency(priceTable.getString("CURRENCY"));

                items.add(item);
            }
            while (priceTable.nextRow());

            // Filter according to language ISO code
            List<PriceSimulationItemBackendData> filteredItems = items.stream().filter(item -> language.equalsIgnoreCase(item.getLanguage())).collect(Collectors.toList());

            // Try to auto-fix missing language in price simulation result data
            if (filteredItems.isEmpty())
            {
                // Seems that there are no service names for the given language ISO code, let's try at least with English
                filteredItems = items.stream().filter(item -> "EN".equalsIgnoreCase(item.getLanguage())).collect(Collectors.toList());
            }

            // Sort result list according to service name (case insensitive)
            return filteredItems.stream().sorted(Comparator.comparing(PriceSimulationItemBackendData::getServiceName, String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());
        }
        else
        {
            // No priced e-services available
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug("No priced e-services retrieved from function module " + ZCRM_SRV_SO_PRICE_SIMULATION);
            }

            return Collections.emptyList();
        }
    }
}
