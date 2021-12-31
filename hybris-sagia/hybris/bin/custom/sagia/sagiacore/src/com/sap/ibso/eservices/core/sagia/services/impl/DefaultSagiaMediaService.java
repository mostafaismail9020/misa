package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaMediaModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaMediaDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaMediaService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

/**
 * Default implementation of Sagia Media Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaMediaService extends AbstractBusinessService implements SagiaMediaService {

    private transient SagiaMediaDAO sagiaMediaDAO;

    public SagiaMediaDAO getSagiaMediaDAO() {
        return sagiaMediaDAO;
    }

    public void setSagiaMediaDAO(SagiaMediaDAO sagiaMediaDAO) {
        this.sagiaMediaDAO = sagiaMediaDAO;
    }

    @Override
    public SagiaMediaModel getSagiaMediaForName(String name) {
        return sagiaMediaDAO.getSagiaMediaForName(name);
    }
}
