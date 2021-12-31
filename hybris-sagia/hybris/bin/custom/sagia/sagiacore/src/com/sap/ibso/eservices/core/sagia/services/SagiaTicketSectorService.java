package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.TicketConfigurationModel;
import com.sap.ibso.eservices.core.model.TicketSectorModel;

import java.util.List;

/**
 * Provides access to the City Service
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaTicketSectorService {

    /**
     * Get all the Ticket Sectors
     * @return - A list containing all the Ticket Sectors.
     */
    List<TicketSectorModel> getAllTicketSector();

   
    
    /**
     * Get all the Ticket Configuration by a sector code
     * @return - A list containing all cities found.
     */
    List<TicketConfigurationModel> getAllTicketConfigurationForSectorCode(String code);
    
}
