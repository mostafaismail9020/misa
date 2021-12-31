package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaClassificationModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaClassificationDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaClassificationDAO extends DefaultGenericDao<SagiaClassificationModel> implements SagiaClassificationDAO {

    public DefaultSagiaClassificationDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaClassificationModel> getAllClassifications() {
        return find();
    }

    @Override
    public SagiaClassificationModel getClassificationForCode(Integer code) {

        validateParameterNotNull(code, "SagiaClassification code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaClassificationModel.CODE, code);
        List parameterList = find(parameters);
        if (CollectionUtils.isNotEmpty(parameterList)) {
            return (SagiaClassificationModel) parameterList.get(0);
        } else {
            return null;
        }
    }
}