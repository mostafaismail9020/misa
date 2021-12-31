/**
 *
 */
package com.sap.ibso.eservices.core.sagia.dao;

import java.util.List;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketSectorModel;


/**
 * @author anouarbadri
 *
 */
public interface TicketSectorDAO
{

	public List<TicketSectorModel> getActiveTicketSectors();
	
	public List<TicketConfigurationModel> getTicketConfigurationBySectorCode(String code);


}
