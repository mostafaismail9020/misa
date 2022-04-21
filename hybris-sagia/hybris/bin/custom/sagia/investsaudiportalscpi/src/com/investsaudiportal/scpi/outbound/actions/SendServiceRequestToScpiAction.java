/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.investsaudiportal.scpi.outbound.actions;

import com.investsaudi.portal.core.model.ServiceRequestModel;
import com.investsaudiportal.scpi.outbound.services.ScpiOutboundServiceImpl;
import com.sap.ibso.eservices.core.model.ScpiOutServiceRequestProcessModel;
import de.hybris.platform.processengine.action.AbstractAction;
import de.hybris.platform.task.RetryLaterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

/**
 * SendServiceRequestToScpiAction providing a common implementation for the execute method.
 */

public class SendServiceRequestToScpiAction extends AbstractAction<ScpiOutServiceRequestProcessModel> {

    private static final Logger LOG = LoggerFactory.getLogger(SendServiceRequestToScpiAction.class);

    @Resource(name = "defaultScpiOutboundService")
    ScpiOutboundServiceImpl scpiOutboundService;

    @Override
    public Set<String> getTransitions() {
        return AbstractAction.createTransitions("OK", "NOK");
    }

    @Override
    public String execute(ScpiOutServiceRequestProcessModel scpiOutServiceRequestProcessModel) throws RetryLaterException, Exception {

        try {
             ServiceRequestModel serviceRequest = scpiOutServiceRequestProcessModel.getServiceRequest();
            scpiOutboundService.sendServiceRequest(serviceRequest);
            return("OK");
        }
        catch (Exception e) {
          LOG.error("Error while sending serviceRequest: " + scpiOutServiceRequestProcessModel.getServiceRequest().getId()+" to SCPI ",e);
            return("NOK");
        }

    }

}
