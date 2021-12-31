/**
 *
 */
package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;


/**
 * @author anouarbadri
 *
 */
public interface TicketConfigurationDAO
{

	public List<TicketConfigurationModel> getActiveTicketConfiguration();
	
	public TicketConfigurationModel getTicketConfigurationByCode(String code);

	public List<TicketConfigurationModel> getActiveServiceRequestConfiguration();

	public List<TicketConfigurationModel> getActiveOpportunityConfiguration();

}
