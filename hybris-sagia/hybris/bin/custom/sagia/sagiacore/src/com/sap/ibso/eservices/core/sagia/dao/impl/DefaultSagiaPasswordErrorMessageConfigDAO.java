package com.sap.ibso.eservices.core.sagia.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.core.model.SagiaPasswordErrorMessageConfigModel;
import com.sap.ibso.eservices.core.sagia.dao.password.SagiaPasswordErrorMessageConfigDAO;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

public class DefaultSagiaPasswordErrorMessageConfigDAO extends DefaultGenericDao<SagiaPasswordErrorMessageConfigModel>
		implements SagiaPasswordErrorMessageConfigDAO {

	public DefaultSagiaPasswordErrorMessageConfigDAO(String typecode) {
		super(typecode);
	}

	@Override
	public List<SagiaPasswordErrorMessageConfigModel> get() {
		return find();
	}

	@Override
	public SagiaPasswordErrorMessageConfigModel getMessageByLang(String lang) {
		validateParameterNotNull(lang, "language code must not be null!");
		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaPasswordErrorMessageConfigModel.LANGCODE, lang);
		List<SagiaPasswordErrorMessageConfigModel> list = find(parameters);
		return list.stream().findFirst().orElse(null);
	}
}
