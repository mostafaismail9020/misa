package com.sap.ibso.eservices.core.sagia.services.impl;

import com.sap.ibso.eservices.core.model.SagiaTutorialModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaTutorialDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaTutorialService;

/**
 * Default implementation of Tutorial Service
 * @package com.sap.ibso.eservices.core.sagia.services.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaTutorialService implements SagiaTutorialService {
    private SagiaTutorialDAO sagiaTutorialDAO;

    @Override
    public SagiaTutorialModel getSagiaTutorial(String url) {
        return sagiaTutorialDAO.getTutorial(url);
    }

    public void setSagiaTutorialDAO(SagiaTutorialDAO sagiaTutorialDAO) {
        this.sagiaTutorialDAO = sagiaTutorialDAO;
    }
}
