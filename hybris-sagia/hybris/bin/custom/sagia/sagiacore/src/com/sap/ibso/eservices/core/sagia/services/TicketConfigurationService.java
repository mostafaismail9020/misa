/**
 * 
 */
package com.sap.ibso.eservices.core.sagia.services;

import java.util.List;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketQuestionModel;

/**
 * @author anouarbadri
 *
 */
public interface TicketConfigurationService
{
	
	public TicketConfigurationModel getTicketConfigurationByCode(String code);
	
	public List<TicketQuestionModel> getQuestionsByConfigurationCode(String code);
	
	public List<TicketConfigurationModel> getActiveServiceRequestConfiguration();
	
	public List<TicketConfigurationModel> getActiveOpportunityConfiguration();

}
