package com.sap.ibso.eservices.bol.license.data;

import de.hybris.platform.sap.core.common.message.Message;

import java.util.Collections;
import java.util.List;

/**
 * Provides a data transfer object for payment assignment result from SAP backend system.
 */
public class PaymentAssignmentBackendData
{
    private List<Message> messages;
    private boolean assignmentSuccessful;

    /**
     * Gets backend messages. If thee are no backend message available then an empty list is returned.
     *
     * @return the messages
     */
    public List<Message> getMessages()
    {
        return messages != null && !messages.isEmpty() ? messages : Collections.emptyList();
    }

    /**
     * Sets backend messages.
     *
     * @param messages the messages
     */
    public void setMessages(List<Message> messages)
    {
        this.messages = messages;
    }

    /**
     * Informs whether a payment transaction identifier was successfully assigned.
     *
     * @return <code>true</code> if the payment transaction identifier was successfully assigned, <code>false</code> otherwise
     */
    public boolean isAssignmentSuccessful()
    {
        return assignmentSuccessful;
    }

    /**
     * Sets a flag whether a payment transaction identifier was successfully assigned.
     *
     * @param assignmentSuccessful <code>true</code> if the payment transaction identifier was successfully assigned, <code>false</code> otherwise
     */
    public void setAssignmentSuccessful(boolean assignmentSuccessful)
    {
        this.assignmentSuccessful = assignmentSuccessful;
    }
}
