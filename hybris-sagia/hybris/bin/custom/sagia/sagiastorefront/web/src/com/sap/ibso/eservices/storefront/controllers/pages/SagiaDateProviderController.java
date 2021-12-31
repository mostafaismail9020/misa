/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.storefront.controllers.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sap.ibso.eservices.storefront.controllers.pages.abs.SagiaAbstractPageController;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.controllers.pages
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
@Controller
@RequestMapping("/date-provider")
public class SagiaDateProviderController extends SagiaAbstractPageController
{

	@RequestMapping(value = "/date", method = RequestMethod.GET)
	@ResponseBody
	public String getFormattedDate(@RequestParam("date") final String millis)
	{
		return null;
	}


}
