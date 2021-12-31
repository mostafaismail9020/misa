package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaMediaModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaMediaDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaMediaDAO extends DefaultGenericDao<SagiaMediaModel> implements SagiaMediaDAO {

    public DefaultSagiaMediaDAO(String typecode) {
        super(typecode);
    }

    @Override
    public SagiaMediaModel getSagiaMediaForName(String name) {
        validateParameterNotNull(name, "SagiaMedia name must not be null!");
        final Map parameters = new HashMap();
        parameters.put(SagiaMediaModel.NAME, name);
        List parameterList = find(parameters);
        if (CollectionUtils.isNotEmpty(parameterList)) {
            return (SagiaMediaModel) parameterList.get(0);
        } else {
            return null;
        }
    }
}
