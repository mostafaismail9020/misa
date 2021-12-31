package com.sap.ibso.eservices.bol.util;

import com.sap.conn.jco.JCoTable;
import de.hybris.platform.sap.core.common.TechKey;
import de.hybris.platform.sap.core.common.message.Message;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessageUtil
{
    /**
     * Avoids instance creation.
     */
    private MessageUtil()
    {
    }

    /**
     * Converts a JCo message table with row type that includes all BAPIRET2 fields into a message list.
     *
     * @param messageTable the message table (to be converted)
     * @return the message list
     */
    public static List<Message> getMessages(final JCoTable messageTable)
    {
        List<Message> messages = null;

        // Process message table
        if (messageTable != null && messageTable.getNumRows() > 0)
        {
            messages = new ArrayList<>(messageTable.getNumRows());

            // Process table: BAPIRET2 fields
            messageTable.firstRow();
            do
            {
                messages.add(createMessage(messageTable));
            }
            while (messageTable.nextRow());
        }

        return messages != null ? messages : Collections.emptyList();
    }

    /**
     * Creates a message from the current message table row.
     *
     * @param messageTable the message table with row type that includes all BAPIRET2 fields
     * @return the message
     */
    private static Message createMessage(final JCoTable messageTable)
    {
        // Creates a message with a SAP core message type
        final Message message = new Message(toSapCoreMessageType(messageTable.getString("TYPE")));
        // The technical key consists of the ABAP message class (ID) followed by space followed by ABAP message number (NUMBER)
        // Cf. Message class -> getTechKey() Javadoc
        message.setTechKey(new TechKey(messageTable.getString("ID") + " " + messageTable.getString("NUMBER")));

        message.setDescription(messageTable.getString("MESSAGE"));

        message.setResourceArgs(new String[]
                {messageTable.getString("MESSAGE_V1"), messageTable.getString("MESSAGE_V2"), messageTable.getString("MESSAGE_V3"),
                        messageTable.getString("MESSAGE_V4")});

        /*
         * The following BAPIRET2 fields are not processed: LOG_NO - application log: log number, LOG_MSG_NO - application
         * log: internal message serial number, PARAMETER - parameter name, ROW - lines in parameter, FIELD - field in
         * parameter, SYSTEM - logical system from which the message originates
         */

        return message;
    }

    /**
     * Maps an ABAP message type to an integer representing a {@link Message SAP core message} type as follows:
     * <p>
     * <table border="1">
     * <tr>
     * <th>ABAP message type</th>
     * <th>SAP core message type</th>
     * </tr>
     * <tr>
     * <td>S</td>
     * <td>{@link Message#SUCCESS}</td>
     * </tr>
     * <tr>
     * <td>I</td>
     * <td>{@link Message#INFO}</td>
     * </tr>
     * <tr>
     * <td>W</td>
     * <td>{@link Message#WARNING}</td>
     * </tr>
     * <tr>
     * <td>E</td>
     * <td>{@link Message#ERROR}</td>
     * </tr>
     * <tr>
     * <td>A</td>
     * <td>{@link Message#ERROR}</td>
     * </tr>
     * </table>
     * </p>
     * If the ABAP message type is {@code null} or other than listed above then {@link Message#INITIAL} is returned.
     *
     * @param abapMessageType the ABAP message type
     * @return the SAP core message type
     */
    private static int toSapCoreMessageType(final String abapMessageType)
    {
        if (abapMessageType == null || abapMessageType.length() != 1)
        {
            return Message.INITIAL;
        }

        final char type = abapMessageType.charAt(0);

        switch (type)
        {
            case 'S':
                return Message.SUCCESS;
            case 'W':
                return Message.WARNING;
            case 'I':
                return Message.INFO;
            case 'E':
            case 'A':
                return Message.ERROR;
            default:
                return Message.INITIAL;
        }
    }

    /**
     * Logs SAP backend messages.
     *
     * @param messages the message to be logged.
     * @param logger   the central interface for logging operations
     */
    public static void logBackendMessages(List<Message> messages, Logger logger)
    {
        for (Message message : messages)
        {
            switch (message.getType())
            {
                case Message.INITIAL:
                    logger.info(toSimpleString(message));
                    break;

                case Message.SUCCESS:
                    logger.info(toSimpleString(message));
                    break;

                case Message.ERROR:
                    logger.error(toSimpleString(message));
                    break;

                case Message.WARNING:
                    logger.warn(toSimpleString(message));
                    break;

                case Message.INFO:
                    logger.info(toSimpleString(message));
                    break;

                case Message.DEBUG:
                    logger.debug(toSimpleString(message));
                    break;

                default:
                    logger.error(toSimpleString(message));
            }
        }
    }

    /**
     * Returns a simple string representation of a {@link Message} instance.
     *
     * @param message the message instance
     * @return the string representation
     */
    public static String toSimpleString(final Message message)
    {
        return message != null ? "Backend Message[type=" + toSapCoreMessageTypeString(message.getType()) +
                " (" + message.getType() + "), techKey=" + message.getTechKey() + ", description=" + message.getDescription() +
                ", resource arguments=" + Arrays.asList(message.getResourceArgs()) + "]" : null;
    }

    /**
     * Returns a string representation of a {@link Message SAP core message} type. An empty string is returned if the
     * provided integer does not represent a valid SAP core message type.
     *
     * @param i the integer representing a SAP core message type
     * @return the string representation
     */
    private static String toSapCoreMessageTypeString(int i)
    {
        switch (i)
        {
            case Message.SUCCESS:
                return "success";
            case Message.INFO:
                return "info";
            case Message.WARNING:
                return "warning";
            case Message.ERROR:
                return "error";
            case Message.DEBUG:
                return "debug";
            case Message.INITIAL:
                return "initial";
            default:
                return "";
        }
    }
}
