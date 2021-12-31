package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaConfigurationModel;
import com.sap.ibso.eservices.core.model.SagiaPasswordErrorMessageConfigModel;
import com.sap.ibso.eservices.core.model.SagiaPasswordRuleConfigModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaConfigurationDAO;
import com.sap.ibso.eservices.core.sagia.dao.password.SagiaPasswordErrorMessageConfigDAO;
import com.sap.ibso.eservices.core.sagia.dao.password.SagiaPasswordRuleConfigDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaConfigurationService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of Sagia Configuration Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaConfigurationService extends AbstractBusinessService implements SagiaConfigurationService {
	private transient SagiaConfigurationDAO sagiaConfigurationDAO;
	private transient SagiaPasswordErrorMessageConfigDAO sagiaPasswordErrorMessageConfigDAO;
	private transient SagiaPasswordRuleConfigDAO sagiaPasswordRuleConfigDAO;

	@Override
	public List<SagiaConfigurationModel> get() {
		return sagiaConfigurationDAO.get();
	}

	@Override
	public String get(String key) {
		SagiaConfigurationModel model = sagiaConfigurationDAO.get(key);
		if (model == null) {
			return null;
		}
		return model.getValue();
	}
	
	@Override
	public String getPasswordRule() {
		SagiaPasswordRuleConfigModel passwordRule = sagiaPasswordRuleConfigDAO.getRule();
		return Optional.ofNullable(passwordRule)
					  .map(SagiaPasswordRuleConfigModel::getValue)
					  .orElse(null);
	}

	@Override
	public String getPasswordErrorMessageByLang(String lang) {
		SagiaPasswordErrorMessageConfigModel errorMessageModel = sagiaPasswordErrorMessageConfigDAO
				.getMessageByLang(lang);
		return Optional.ofNullable(errorMessageModel)
					   .map(SagiaPasswordErrorMessageConfigModel::getValue)
					   .orElse(null);
	}

	public SagiaConfigurationDAO getSagiaConfigurationDAO() {
		return sagiaConfigurationDAO;
	}

	public void setSagiaConfigurationDAO(SagiaConfigurationDAO sagiaConfigurationDAO) {
		this.sagiaConfigurationDAO = sagiaConfigurationDAO;
	}

	public SagiaPasswordErrorMessageConfigDAO getSagiaPasswordErrorMessageConfigDAO() {
		return sagiaPasswordErrorMessageConfigDAO;
	}

	public void setSagiaPasswordErrorMessageConfigDAO(
			SagiaPasswordErrorMessageConfigDAO sagiaPasswordErrorMessageConfigDAO) {
		this.sagiaPasswordErrorMessageConfigDAO = sagiaPasswordErrorMessageConfigDAO;
	}

	public SagiaPasswordRuleConfigDAO getSagiaPasswordRuleConfigDAO() {
		return sagiaPasswordRuleConfigDAO;
	}

	public void setSagiaPasswordRuleConfigDAO(SagiaPasswordRuleConfigDAO sagiaPasswordRuleConfigDAO) {
		this.sagiaPasswordRuleConfigDAO = sagiaPasswordRuleConfigDAO;
	}

}
