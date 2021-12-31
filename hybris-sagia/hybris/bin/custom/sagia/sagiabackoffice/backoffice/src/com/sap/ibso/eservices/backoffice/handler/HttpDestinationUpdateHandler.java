package com.sap.ibso.eservices.backoffice.handler;

import com.hybris.backoffice.editorarea.BackofficeEditorAreaLogicHandler;
import com.hybris.cockpitng.dataaccess.facades.object.exceptions.ObjectSavingException;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.sap.ibso.eservices.sagiaservices.event.HttpDestinationPotentialUpdateEvent;
import de.hybris.platform.sap.core.configuration.model.SAPConfigurationModel;
import de.hybris.platform.sap.core.configuration.model.SAPHTTPDestinationModel;
import de.hybris.platform.servicelayer.event.EventService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Publishes an {@link HttpDestinationPotentialUpdateEvent} if a {@link SAPHTTPDestinationModel} or {@link SAPConfigurationModel}
 * is persisted in back office.
 */
public class HttpDestinationUpdateHandler extends BackofficeEditorAreaLogicHandler
{
    private EventService eventService;

    public Object performSave(WidgetInstanceManager widgetInstanceManager, Object currentObject) throws ObjectSavingException
    {
        Object savedObject = super.performSave(widgetInstanceManager, currentObject);

        if (savedObject instanceof SAPHTTPDestinationModel || savedObject instanceof SAPConfigurationModel)
        {
            // Note: in case of SAP configuration model changes the SAP HTTP destination might have or might not have been changed as well.
            eventService.publishEvent(new HttpDestinationPotentialUpdateEvent());
        }

        return savedObject;
    }

    @Required
    public void setEventService(EventService eventService)
    {
        this.eventService = eventService;
    }
}
