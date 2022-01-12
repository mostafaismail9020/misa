/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.controllers.cms;

import com.investsaudi.portal.core.model.InvestSaudiOfficesContainerModel;

import com.sap.ibso.eservices.facades.sagia.SagiaComplaintFacade;
import com.sap.ibso.eservices.sagiaservices.services.complaints.dto.ContactUsFormData;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for InvestSaudiOfficesContainerController. This controller is used for displaying the container own page
 */
@Controller("InvestSaudiOfficesContainerController")
@RequestMapping("/view/InvestSaudiOfficesContainerController")
public class InvestSaudiOfficesContainerController extends AbstractAcceleratorCMSComponentController<InvestSaudiOfficesContainerModel> {
	
	private static final Logger LOG = Logger.getLogger(InvestSaudiOfficesContainerController.class);
	
	@Resource(name = "sagiaComplaintFacade")
    private SagiaComplaintFacade sagiaComplaintFacade;
    
	@Override
    protected void fillModel(final HttpServletRequest request, final Model model, final InvestSaudiOfficesContainerModel component) {
		
		LOG.info("Inside InvestSaudiOfficesContainerController");
        model.addAttribute("components", component.getSimpleCMSComponents());
        
        ContactUsFormData contactUsFormData = sagiaComplaintFacade.getContactUsFormData();
        /*if(contactUsFormData != null) {
        Collection<CategorizationSchemaGetListData> categoryOne = contactUsFormData.getCategoryOne();
        if(categoryOne != null) {
			for (CategorizationSchemaGetListData schema : categoryOne) {
				LOG.debug("Cat ID : "+ schema.getCatID() + " cat Desc : " + schema.getCatDesc() + "_______________" );
			}
		}        
        }*/
	   model.addAttribute("contactUsFormData", contactUsFormData);
    }
}
