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

package com.sap.ibso.eservices.storefront.controllers.cms;

import com.sap.ibso.eservices.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorcms.model.components.CMSTabParagraphContainerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for CMSTabParagraphContainer. This controller is used for displaying the container own page
 */
@Controller("CMSTabParagraphContainerController")
@RequestMapping(value = ControllerConstants.Actions.Cms.CMSTabParagraphContainer)
public class CMSTabParagraphContainerController extends AbstractAcceleratorCMSComponentController<CMSTabParagraphContainerModel> {
    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final CMSTabParagraphContainerModel component) {
        model.addAttribute("components", component.getSimpleCMSComponents());
    }
}