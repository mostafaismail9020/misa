package com.sap.ibso.eservices.bol.document.data;

import de.hybris.platform.sap.core.common.message.Message;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Provides a data transfer object for a document from SAP backend system.
 */
public class DocumentBackendData implements Serializable
{
    private byte[] document;
    private List<Message> messages;

    /**
     * Gets a document in portable document format (PDF) as byte array.
     * A byte array of length zero indicates that no document could be retrieved in the SAP backend system.
     *
     * @return the document as PDF byte array
     */
    public byte[] getDocument()
    {
        return document != null ? document : new byte[0];
    }

    /**
     * Sets a document in portable document format (PDF) as byte array.
     *
     * @param document the document as PDF byte array
     */
    public void setDocument(byte[] document)
    {
        this.document = document;
    }

    /**
     * Gets backend messages. If there are no backend message available then an empty list is returned.
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
