package com.sap.ibso.eservices.bol.company.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.ibso.eservices.bol.company.CompanyInformationBackendService;
import com.sap.ibso.eservices.bol.company.data.BasicCompanyBackendData;
import com.sap.ibso.eservices.bol.util.ConversionUtil;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;

/**
 * Implements synchronous access to e-services related investor company data in SAP backend system.
 */
public class CompanyInformationBackendServiceImpl extends BackendBusinessObjectBaseJCo implements CompanyInformationBackendService
{
    // Function module name
    private static final String ZFM_ESRV_ENTITY_DETAILS_GET = "ZFM_ESRV_ENTITY_DETAILS_GET";

    @Override
    public BasicCompanyBackendData getBasicInformation(String entityId, String language)
    {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(ZFM_ESRV_ENTITY_DETAILS_GET);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();

            importParameters.setValue("IV_ENTITY_ID", ConversionUtil.addLeadingZeros(entityId, 10)); // ABAP type: Char(10)
            importParameters.setValue("IV_LANGU", language.toUpperCase());

            // Execute function module
            connection.execute(function);

            // Get export parameters
            JCoParameterList exportParameters = function.getExportParameterList();
            JCoStructure entityStructure = exportParameters.getStructure("ES_ENTITY_DETAILS");
            JCoTable messageTable = exportParameters.getTable("ET_MESSAGE");

            return createBasicCompanyBackendData(entityStructure, messageTable);
        }
        catch (final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to retrieve basic information of an investor company.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for basic company information.
     *
     * @param entityStructure contain the basic company data
     * @param messageTable contains backend messages (if present)
     * @return the basic company backend data
     */
    private static BasicCompanyBackendData createBasicCompanyBackendData(JCoStructure entityStructure, JCoTable messageTable)
    {
        BasicCompanyBackendData basicCompanyBackendData = new BasicCompanyBackendData();

        basicCompanyBackendData.setBusinessEmailAddress(entityStructure.getString("OFF_EMAIL"));
        basicCompanyBackendData.setCapitalValue(entityStructure.getBigDecimal("CAPITAL"));
        basicCompanyBackendData.setCity(entityStructure.getString("CITY"));
        basicCompanyBackendData.setCompanyNameInArabic(entityStructure.getString("ENTITY_NAME_AR"));
        basicCompanyBackendData.setCompanyNameInEnglish(entityStructure.getString("ENTITY_NAME_EN"));
        basicCompanyBackendData.setCountry(entityStructure.getString("COUNTRY_TXT"));
        basicCompanyBackendData.setCurrencyIso(entityStructure.getString("CURR_CODE"));
        basicCompanyBackendData.setIsicDevision(entityStructure.getString("ISIC_DIV_TXT"));
        basicCompanyBackendData.setIsicSection(entityStructure.getString("ISIC_SECT_TXT"));
        basicCompanyBackendData.setIsinCode(entityStructure.getString("ISIN_CODE"));
        basicCompanyBackendData.setLegalStatus(entityStructure.getString("LEGAL_STAT_TXT"));
        basicCompanyBackendData.setRegion(entityStructure.getString("REGION_TXT"));
        basicCompanyBackendData.setMessages(MessageUtil.getMessages(messageTable));

        return basicCompanyBackendData;
    }
}
