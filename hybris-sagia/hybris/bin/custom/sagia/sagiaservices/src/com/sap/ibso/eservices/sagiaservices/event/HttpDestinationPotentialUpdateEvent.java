package com.sap.ibso.eservices.sagiaservices.event;

import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;

/**
 * Indicates that SAP HTTP destination attributes have potentially changed. This event indicates a potential change only.
 * Which might be true in most cases. However, it is not ensured.
 */
public class HttpDestinationPotentialUpdateEvent extends AbstractEvent implements ClusterAwareEvent
{
    @Override
    public boolean publish(int i, int i1)
    {
        return true; // Publish always (independent of cluster nodes)
    }
}
