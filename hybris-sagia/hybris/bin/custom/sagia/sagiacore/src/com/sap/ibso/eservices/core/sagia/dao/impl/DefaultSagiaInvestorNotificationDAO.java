package com.sap.ibso.eservices.core.sagia.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sap.ibso.eservices.core.model.SagiaInvestorNotificationModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaInvestorNotificationDAO;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

public class DefaultSagiaInvestorNotificationDAO extends DefaultGenericDao<SagiaInvestorNotificationModel>
		implements SagiaInvestorNotificationDAO {

	public DefaultSagiaInvestorNotificationDAO(String transactionId) {
		super(transactionId);
	}

	@Override
	public Optional<SagiaInvestorNotificationModel> getNotificationBy(String investorId, String transactionId) {
		validateParameterNotNull(investorId, "Sagia Investor Id must not be null!");
		validateParameterNotNull(transactionId, "Sagia Transaction Id must not be null!");
		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaInvestorNotificationModel.TRANSACTIONID, transactionId);
		parameters.put(SagiaInvestorNotificationModel.INVESTORID, investorId);
		return find(parameters).stream().findFirst();
	}

	@Override
	public List<SagiaInvestorNotificationModel> getNotificationsFor(String investorId) {
		validateParameterNotNull(investorId, "Sagia Investor Id must not be null!");
		final Map<String, String> parameters = new HashMap<>();
		parameters.put(SagiaInvestorNotificationModel.INVESTORID, investorId);
		return find(parameters);
	}

}
