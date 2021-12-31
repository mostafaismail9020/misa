package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.facades.data.zqeemah.ValidateSareholder;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;

import java.util.Arrays;

/**
 * ExistingShareholderService
 * @package com.sap.ibso.eservices.sagiaservices.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ExistingShareholderService extends AbstractSagiaService<ValidateSareholder> {
	private static final String BPNO = "Bpno='";

	/**
	 * retrieves ShareholderInfo by entityBpNo
	 * @param entityBpNo entityBpNo
	 * @return ShareholderInfo
	 */
	public ValidateSareholder validateExistingShareholder(String entityBpNo){
		return super.get(ValidateSareholder.class, Arrays.asList(BPNO + entityBpNo + "'"));
	}
}
