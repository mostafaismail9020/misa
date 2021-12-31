/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.investsaudi.portal.core.cms.container;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.platform.cms2.model.contents.containers.AbstractCMSComponentContainerModel;
import de.hybris.platform.cms2.strategies.CMSComponentContainerStrategy;
import de.hybris.platform.servicelayer.type.TypeService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Implement a strategy to return the Container
 */
public class InvestSaudiPortalContainerStrategy implements CMSComponentContainerStrategy {

    @Override
    public List<AbstractCMSComponentModel> getDisplayComponentsForContainer(final AbstractCMSComponentContainerModel container) {
        return Collections.singletonList(container);
    }
}
