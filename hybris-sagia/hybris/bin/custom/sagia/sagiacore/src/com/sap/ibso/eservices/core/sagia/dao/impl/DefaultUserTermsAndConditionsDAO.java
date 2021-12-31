package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaUserTermsAndConditionsModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaUserTermsAndConditionsDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

public class DefaultUserTermsAndConditionsDAO extends DefaultGenericDao<SagiaUserTermsAndConditionsModel> implements SagiaUserTermsAndConditionsDAO {

    public DefaultUserTermsAndConditionsDAO(String typecode) {
        super(typecode);
    }

}
