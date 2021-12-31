package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.model.SagiaRhqRegionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaCountryService;
import com.sap.ibso.eservices.core.sagia.services.SagiaRegionService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

/**
 * Default implementation of the Sagia Region Service.
 * Service for the Countries.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaRegionService implements SagiaRegionService {

    private SagiaRegionDAO sagiaRegionDAO;

    public SagiaRegionDAO getSagiaRegionDAO() {
        return sagiaRegionDAO;
    }

    public void setSagiaRegionDAO(SagiaRegionDAO sagiaRegionDAO) {
        this.sagiaRegionDAO = sagiaRegionDAO;
    }

    @Override
    public List<SagiaRegionModel> getAllRegions() {
        return getSagiaRegionDAO().getAllRegions();
    }

    @Override
    public SagiaRegionModel getRegionForCode(String code) {
        return getSagiaRegionDAO().getRegionForCode(code);
    }

    @Override
    public List<SagiaRegionModel> getAllRegionsForCountryCode(String code) {
        return getSagiaRegionDAO().getAllRegionsForCountryCode(code);
    }
    
    @Override
    public List<SagiaRhqRegionModel> getRhqRegionsList() {
        return getSagiaRegionDAO().getRhqRegionsList();
    }
}
