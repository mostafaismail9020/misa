package com.sap.ibso.eservices.sagiaservices.services.impl;

import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.facades.data.zqeemah.ValidateMofaNumber;

import java.util.Arrays;

public class MofaVerificationService extends AbstractSagiaService<ValidateMofaNumber> {
	private static final String MOFANO = "Mofano='";

	public ValidateMofaNumber validateMofaNumber(String mofaNumber){
		return super.get(ValidateMofaNumber.class, Arrays.asList(MOFANO + mofaNumber + "'"));
	}
}
