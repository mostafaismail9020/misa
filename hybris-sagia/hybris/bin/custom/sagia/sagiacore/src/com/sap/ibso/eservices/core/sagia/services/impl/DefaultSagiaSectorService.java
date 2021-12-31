package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaSectorDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaSectorService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

/**
 * Default implementation of Sagia Sector Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaSectorService extends AbstractBusinessService implements SagiaSectorService {

    private transient SagiaSectorDAO sagiaSectorDAO;

    public SagiaSectorDAO getSagiaSectorDAO() {
        return sagiaSectorDAO;
    }

    public void setSagiaSectorDAO(SagiaSectorDAO sagiaSectorDAO) {
        this.sagiaSectorDAO = sagiaSectorDAO;
    }

    @Override
    public List<SagiaSectorModel> getSectors() {
        return sagiaSectorDAO.getAllSectors();
    }

    @Override
    public SagiaSectorModel getSectorForCode(String code) {
        return sagiaSectorDAO.getSectorForCode(code);
    }

    @Override
    public SagiaSectorModel getSectorForSectorCode(String sectorCode) {
        return sagiaSectorDAO.getSectorForSectorCode(sectorCode);
    }
}
