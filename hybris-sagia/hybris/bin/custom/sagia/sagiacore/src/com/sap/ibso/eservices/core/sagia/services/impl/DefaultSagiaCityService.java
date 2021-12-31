package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCityDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaCityService;
import com.sap.ibso.eservices.core.sagia.services.SagiaRegionService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

/**
 * Default implementation of the Sagia City Service.
 * Service for the Countries.
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaCityService implements SagiaCityService {

    private SagiaCityDAO sagiaCityDAO;

    public SagiaCityDAO getSagiaCityDAO() {
        return sagiaCityDAO;
    }

    public void setSagiaCityDAO(SagiaCityDAO sagiaCityDAO) {
        this.sagiaCityDAO = sagiaCityDAO;
    }

    @Override
    public List<SagiaCityModel> getAllCities() {
        return getSagiaCityDAO().getAllCities();
    }

    @Override
    public SagiaCityModel getCityForCode(String code) {
        return getSagiaCityDAO().getCityForCode(code);
    }

    @Override
    public List<SagiaCityModel> getAllCitiesForRegionCode(String code) {
        return getSagiaCityDAO().getAllCitiesForRegionCode(code);
    }
}
