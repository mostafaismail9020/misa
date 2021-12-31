package com.sap.ibso.eservices.core.handlers;

import com.sap.ibso.eservices.core.model.TicketAnswerModel;
import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import de.hybris.platform.ticket.model.CsTicketModel;

import java.util.Set;

public class TicketConfigurationHandler extends AbstractDynamicAttributeHandler<String, CsTicketModel> {

    @Override
    public String get(CsTicketModel model) {
        if (model == null) {
            throw new IllegalArgumentException("Item model is required");
        }

        if (model.getConfiguration() != null && model.getConfiguration().getSector() != null ) {
            return model.getConfiguration().getSector().getCode();
        }

        return "";
    }

    @Override
    public void set(CsTicketModel model, String value) {
        // do nothing
    }

}