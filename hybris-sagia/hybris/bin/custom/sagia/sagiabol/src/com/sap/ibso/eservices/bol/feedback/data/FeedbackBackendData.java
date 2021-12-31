package com.sap.ibso.eservices.bol.feedback.data;

import de.hybris.platform.sap.core.common.message.Message;

import java.util.Collections;
import java.util.List;

/**
 * Provides a data transfer object for feedback submission messages from SAP backend system.
 */
public class FeedbackBackendData
{
    private List<Message> messages;

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
}
