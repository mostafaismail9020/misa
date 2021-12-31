package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaConfigurationModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaConfigurationDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaConfigurationDAO extends DefaultGenericDao<SagiaConfigurationModel> implements SagiaConfigurationDAO {
    public DefaultSagiaConfigurationDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaConfigurationModel> get() {
        return find();
    }

    @Override
    public SagiaConfigurationModel get(String key) {
        validateParameterNotNull(key, "key code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaConfigurationModel.KEY, key);
        List<SagiaConfigurationModel> parameterList = find(parameters);
        if (CollectionUtils.isNotEmpty(parameterList)) {
            return parameterList.get(0);
        } else {
            return null;
        }
    }
}
