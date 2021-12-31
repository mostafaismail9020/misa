package com.sap.ibso.eservices.bol.price.data;

import de.hybris.platform.sap.core.common.message.Message;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Provides a data transfer object for price simulation from SAP backend system.
 */
public class PriceSimulationBackendData implements Serializable
{
    private List<PriceSimulationItemBackendData> items;
    private List<Message> messages;

    /**
     * Gets the price simulation items which are priced e-services.
     *
     * @return the price simulation items
     */
    public List<PriceSimulationItemBackendData> getItems()
    {
        return items != null && !items.isEmpty() ? items : Collections.emptyList();
    }

    /**
     * Sets the price simulation items which are priced e-services.
     *
     * @param items the price simulation items
     */
    public void setItems(List<PriceSimulationItemBackendData> items)
    {
        this.items = items;
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
