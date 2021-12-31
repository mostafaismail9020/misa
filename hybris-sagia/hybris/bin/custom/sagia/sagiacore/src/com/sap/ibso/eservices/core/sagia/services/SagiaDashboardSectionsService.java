package com.sap.ibso.eservices.core.sagia.services;

import java.util.ArrayList;
import java.util.Map;

/**
 * Provides access to the Dashboard Sections Serivce
 * @package com.sap.ibso.eservices.core.sagia.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public interface SagiaDashboardSectionsService {

    /**
     * Update the sections within the dashboard.
     * @param map -
     */
    void updateUserDashboardSections(Map<String, ArrayList<String>> map);
    Map<String, ArrayList<String>> getUserDashboardSections();

    /**
     * Initializes the dashboard sections.
     */
    void initializeDashboardSections();
}
