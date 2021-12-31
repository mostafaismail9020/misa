package com.sap.ibso.eservices.core.sagia.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.core.model.SagiaPasswordRuleConfigModel;
import com.sap.ibso.eservices.core.sagia.dao.password.SagiaPasswordRuleConfigDAO;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

public class DefaultSagiaPasswordRuleConfigDAO extends DefaultGenericDao<SagiaPasswordRuleConfigModel>
		implements SagiaPasswordRuleConfigDAO {

	private static final String PASS_REGEX_KEY = "passwordRegex";

	public DefaultSagiaPasswordRuleConfigDAO(String typecode) {
		super(typecode);
	}

	@Override
	public List<SagiaPasswordRuleConfigModel> get() {
		return find();
	}

	@Override
	public SagiaPasswordRuleConfigModel getRule() {
		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaPasswordRuleConfigModel.KEY, PASS_REGEX_KEY);
		List<SagiaPasswordRuleConfigModel> list = find(parameters);
		return list.stream().findFirst().orElse(null);
	}
}
