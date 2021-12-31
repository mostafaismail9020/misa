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

import com.google.gson.FieldNamingPolicy;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.IsicDetPsSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SagiaIsicDetPsService
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class SagiaIsicDetPsService extends AbstractSagiaService<IsicDetPsSetData>
{
	public static final String INVESTOR_ID_PARAMETER = "Investorid";
	public static final String INVESTOR_ID_PARAMETER2 = "Investorid";
	/**
	 * deletes IsicDetPs by investorId
	 * @param investorId investorId
	 */
	public void deleteIsicDetPs(final String investorId) {

		Map<String, String> params = new HashMap<>();
		params.put(INVESTOR_ID_PARAMETER, investorId);

		delete(params);
	}

	/**
	 * batchPostRequest
	 * @param isicDetPsSetDataList isicDetPsSetDataList
	 * @return boolean
	 */
	public boolean batchPostRequest(List<?> isicDetPsSetDataList) {
		return createObjectsWithBatchPost(isicDetPsSetDataList, "IsicDetPsSet", FieldNamingPolicy.UPPER_CAMEL_CASE);
	}

	/**
	 * saves BusinessActivity
	 * @param isicDetPsSetData isicDetPsSetData
	 */
    public void saveBusinessActivity(IsicDetPsSetData isicDetPsSetData){
	    super.save(isicDetPsSetData);
    }
}
