package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaBEServiceTypeModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaBEServiceTypeDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaBETransTypeService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

/**
 * Default implementation of Sagia Trans Type Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaTransTypeService extends AbstractBusinessService implements SagiaBETransTypeService {

    private transient SagiaBEServiceTypeDAO sagiaBEServiceTypeDAO;

    public SagiaBEServiceTypeDAO getSagiaBEServiceTypeDAO() {
        return sagiaBEServiceTypeDAO;
    }

    public void setSagiaBEServiceTypeDAO(SagiaBEServiceTypeDAO sagiaBEServiceTypeDAO) {
        this.sagiaBEServiceTypeDAO = sagiaBEServiceTypeDAO;
    }

    @Override
    public List<SagiaBEServiceTypeModel> getAllSagiaBEServiceTypes() {
        return getSagiaBEServiceTypeDAO().getAllSagiaBEServiceTypes();
    }

    @Override
    public String getTransTypeForCode(String code) {
        return getSagiaBEServiceTypeDAO().getTransTypeForCode(code);
    }
}
