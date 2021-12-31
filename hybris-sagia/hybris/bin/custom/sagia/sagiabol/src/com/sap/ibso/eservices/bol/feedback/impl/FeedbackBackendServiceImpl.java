package com.sap.ibso.eservices.bol.feedback.impl;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.sap.ibso.eservices.bol.feedback.BackendRatingValue;
import com.sap.ibso.eservices.bol.feedback.FeedbackBackendService;
import com.sap.ibso.eservices.bol.feedback.data.FeedbackBackendData;
import com.sap.ibso.eservices.bol.util.ConversionUtil;
import com.sap.ibso.eservices.bol.util.MessageUtil;
import de.hybris.platform.sap.core.bol.backend.jco.BackendBusinessObjectBaseJCo;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.sap.core.jco.connection.JCoConnection;
import org.apache.log4j.Logger;

import org.apache.commons.lang.StringUtils;

/**
 * Implements synchronous feedback submission to SAP backend system about user experience related to e-services.
 */
public class FeedbackBackendServiceImpl extends BackendBusinessObjectBaseJCo implements FeedbackBackendService
{
    // Function module name service feedback
    private static final String ZFM_ESRV_SR_RATING_UPDATE = "ZFM_ESRV_SR_RATING_UPDATE";

    // Function module name chat feedback
    private static final String ZESRV_CHAT_INTER_FEEDBACK = "ZESRV_CHAT_INTER_FEEDBACK";
    
    private static final Logger LOG = Logger.getLogger(FeedbackBackendServiceImpl.class);

    @Override
    public FeedbackBackendData submitUserExperienceFeedback(String serviceId, BackendRatingValue value, String comment, String email)
    {
        try
        {
            if(value.toInteger() > 3 || (value.toInteger() < 4 && StringUtils.isNotEmpty(comment))){
                JCoConnection connection = getDefaultJCoConnection();
                JCoFunction function;
                if (email == null) {
                    function = connection.getFunction(ZFM_ESRV_SR_RATING_UPDATE);
                } else {
                    function = connection.getFunction(ZESRV_CHAT_INTER_FEEDBACK);
                }
                LOG.info("*****comment : "+comment);
                // Set import parameters
                JCoParameterList importParameters = function.getImportParameterList();

                if (email == null) {
                    importParameters.setValue("IV_OBJECT_ID", ConversionUtil.addLeadingZeros(serviceId, 10)); // ABAP type: Char(10)
                } else {
                    importParameters.setValue("IV_INVESTOR_EMAIL", email);
                }

                importParameters.setValue("IV_RATING_VAL", value.toInteger());

                // Comment can be optional for certain backend rating values
                if (comment != null)
                {
                    // Prefix document number with leading zeros (if necessary)
                    importParameters.setValue("IV_RATING_COMMENT", comment);
                }

                // Execute function module
                connection.execute(function);

                // Get export parameters
                JCoParameterList exportParameters = function.getExportParameterList();
                JCoTable messageTable = null;
                if (email == null) {
                    exportParameters.getTable("ET_RETURN");
                }

                return createFeedbackBackendData(messageTable);
            }
            throw new IllegalStateException();
        }
        catch(final Exception e)
        {
            // Every potential exception (in particular a BackendException) is wrapped into an ApplicationBaseRuntimeException
            String message = "Technical issue occurred while trying to submit e-service rating feedback.";
            throw new ApplicationBaseRuntimeException(message, e);
        }
    }

    /**
     * Creates a data transfer object for feedback submission messages.
     *
     * @param messageTable contains backend messages (if present)
     * @return the document backend data
     */
    private static FeedbackBackendData createFeedbackBackendData(JCoTable messageTable)
    {
        FeedbackBackendData feedbackBackendData = new FeedbackBackendData();

        feedbackBackendData.setMessages(MessageUtil.getMessages(messageTable));

        return feedbackBackendData;
    }
}
