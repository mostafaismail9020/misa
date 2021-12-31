package com.sap.ibso.eservices.bol.document.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.sap.ibso.eservices.bol.document.BackendDocumentType;
import com.sap.ibso.eservices.bol.document.DocumentBackendService;
import com.sap.ibso.eservices.bol.document.data.DocumentBackendData;
import com.sap.ibso.eservices.bol.util.ConversionUtil;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Implements synchronous access to e-services related documents in SAP backend system.
 */
public class DocumentBackendServiceImpl extends BackendBusinessObjectBaseJCo implements DocumentBackendService
{
    private static final Logger LOGGER = LogManager.getLogger(DocumentBackendServiceImpl.class);

    // Function module name
    private static final String ZFM_ESRV_PDF_DOC_GET = "ZFM_ESRV_PDF_DOC_GET";

    @Override
    public DocumentBackendData getDocumentAsPdf(String documentNumber, BackendDocumentType documentType, String entityId)
    {
        try
        {
            JCoConnection connection = getDefaultJCoConnection();
            JCoFunction function = connection.getFunction(ZFM_ESRV_PDF_DOC_GET);

            // Set import parameters
            JCoParameterList importParameters = function.getImportParameterList();

            // Document number can be optional for certain document type
            if (documentNumber != null)
            {
                // Prefix document number with leading zeros (if necessary)
                importParameters.setValue("IV_OBJECT_ID", ConversionUtil.addLeadingZeros(documentNumber, 10)); // ABAP type: Char(10)
            }

            importParameters.setValue("IV_DOC_TYPE", documentType.toString());
            importParameters.setValue("IV_ENTITY_ID", entityId);


            // Execute function module
            connection.execute(function);

            // Get export parameters
            JCoParameterList exportParameters = function.getExportParameterList();
            JCoTable documentContentTable = exportParameters.getTable("ET_CONTENT");
            JCoTable messageTable = exportParameters.getTable("ET_RETURN");

            return createDocumentBackendData(documentContentTable, messageTable);
        }
        catch(final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to retrieve a document as PDF.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for a backend document.
     *
     * @param documentContentTable contains the binary PDF of the backend document
     * @param messageTable contains backend messages (if present)
     * @return the document backend data
     */
    private static DocumentBackendData createDocumentBackendData(JCoTable documentContentTable, JCoTable messageTable)
    {
        DocumentBackendData documentBackendData = new DocumentBackendData();

        documentBackendData.setDocument(processDocumentContentTable(documentContentTable));
        documentBackendData.setMessages(MessageUtil.getMessages(messageTable));

        return documentBackendData;
    }

    /**
     * Converts a JCo document content table into an document PDF byte array.
     *
     * @param documentContentTable the document content table to be converted
     * @return the document PDF byte array
     */
    private static byte[] processDocumentContentTable(final JCoTable documentContentTable)
    {
        // Process document content table to retrieve the document byte array
        if (documentContentTable.getNumRows() > 0)
        {
            final ByteArrayOutputStream os = new ByteArrayOutputStream();

            // Process table: ET_CONTENT
            documentContentTable.firstRow();
            do
            {
                try
                {
                    os.write(documentContentTable.getByteArray("LINE"));
                }
                catch (final IOException e)
                {
                    LOGGER.error("Unable to write document PDF byte array", e);
                    return new byte[0];
                }
            }
            while (documentContentTable.nextRow());

            return os.toByteArray();
        }
        else
        {
            // No document PDF available
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug("No document PDF retrieved from function module " + ZFM_ESRV_PDF_DOC_GET);
            }

            return new byte[0];
        }
    }
}
