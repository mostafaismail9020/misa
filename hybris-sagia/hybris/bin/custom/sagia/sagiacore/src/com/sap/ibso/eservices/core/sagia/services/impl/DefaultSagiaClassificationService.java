package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaClassificationModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaClassificationDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaClassificationService;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;

import java.util.List;

/**
 * Default implementation of Sagia Classification Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaClassificationService extends AbstractBusinessService implements SagiaClassificationService {

    private transient SagiaClassificationDAO sagiaClassificationDAO;

    public SagiaClassificationDAO getSagiaClassificationDAO() {
        return sagiaClassificationDAO;
    }

    public void setSagiaClassificationDAO(SagiaClassificationDAO sagiaClassificationDAO) {
        this.sagiaClassificationDAO = sagiaClassificationDAO;
    }

    @Override
    public List<SagiaClassificationModel> getClassifications() {
        return getSagiaClassificationDAO().getAllClassifications();
    }

    @Override
    public SagiaClassificationModel getClassificationForCode(Integer code) {
        return sagiaClassificationDAO.getClassificationForCode(code);
    }
}
