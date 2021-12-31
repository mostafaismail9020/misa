package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketQuestionModel;
import com.sap.ibso.eservices.core.sagia.dao.TicketConfigurationDAO;
import com.sap.ibso.eservices.core.sagia.dao.TicketQuestionDAO;
import com.sap.ibso.eservices.core.sagia.services.TicketConfigurationService;

public class DefaultTicketConfigurationService implements TicketConfigurationService {

	@Resource
	private TicketConfigurationDAO ticketConfigurationDAO;
	
	@Resource
	private TicketQuestionDAO ticketQuestionDAO;
	
	@Override
	public TicketConfigurationModel getTicketConfigurationByCode(String code) {
		return ticketConfigurationDAO.getTicketConfigurationByCode(code);
	}

	@Override
	public List<TicketQuestionModel> getQuestionsByConfigurationCode(String code) {
		
		return ticketQuestionDAO.getQuestionForConfigurationID(code);
	}

	@Override
	public List<TicketConfigurationModel> getActiveServiceRequestConfiguration() {
		
		return ticketConfigurationDAO.getActiveServiceRequestConfiguration();
	}

	@Override
	public List<TicketConfigurationModel> getActiveOpportunityConfiguration() {
		return ticketConfigurationDAO.getActiveOpportunityConfiguration();
	}

}
