package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaConfigurationModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;

/**
 * Configuration ValueAttribute Handler
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaConfigurationValueAttributeHandler extends AbstractDynamicAttributeHandler<String, SagiaConfigurationModel> {
    @Override
    public void set(SagiaConfigurationModel model, String s) {
        model.setValue(s);
    }

    @Override
    public String get(SagiaConfigurationModel model) {
        if(model.getDisplayValue() == null || !model.getDisplayValue()) {
            return "*****";
        }
        return model.getValue();
    }
}