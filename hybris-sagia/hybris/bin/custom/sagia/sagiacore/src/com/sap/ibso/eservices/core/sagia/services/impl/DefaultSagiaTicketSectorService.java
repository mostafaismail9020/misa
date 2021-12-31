package com.sap.ibso.eservices.core.sagia.services.impl;

import java.util.List;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketSectorModel;
import com.sap.ibso.eservices.core.sagia.dao.TicketConfigurationDAO;
import com.sap.ibso.eservices.core.sagia.dao.TicketSectorDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaTicketSectorService;

public class DefaultSagiaTicketSectorService implements SagiaTicketSectorService {
	
	
	
	
	@Resource
	private TicketSectorDAO ticketSectorDAO;
	
	@Resource
	private TicketConfigurationDAO ticketConfigurationDAO;
	
	
	@Override
	public List<TicketSectorModel> getAllTicketSector() {
		return ticketSectorDAO.getActiveTicketSectors();
	}

	@Override
	public List<TicketConfigurationModel> getAllTicketConfigurationForSectorCode(String code) {
		
		return ticketSectorDAO.getTicketConfigurationBySectorCode(code);
	}

}
