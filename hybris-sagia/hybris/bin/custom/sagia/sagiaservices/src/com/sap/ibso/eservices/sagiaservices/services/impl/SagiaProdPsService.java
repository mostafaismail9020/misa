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
package com.sap.ibso.eservices.sagiaservices.services.impl;

import java.util.HashMap;
import java.util.Map;

import com.sap.ibso.eservices.sagiaservices.data.ProdPsSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

/**
 * SagiaProdPsService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaProdPsService extends AbstractSagiaService<ProdPsSetData>
{
	public static final String INVESTOR_ID_PARAMETER = "Investorid";

	/**
	 * deletes ProdPs by investorId
	 * @param investorId investorId
	 */
	public void deleteProdPs(final String investorId) {
		Map<String, String> params = new HashMap<>();
		params.put(INVESTOR_ID_PARAMETER, investorId);

		delete(params);
	}
}
