package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaLegalStatusModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLegalStatusDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaLegalStatusDAO extends DefaultGenericDao<SagiaLegalStatusModel> implements SagiaLegalStatusDAO {

    public DefaultSagiaLegalStatusDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaLegalStatusModel> getAllLegalStatus() {
        return find();
    }

    @Override
    public SagiaLegalStatusModel getLegalStatusForCode(String code) {

        validateParameterNotNull(code, "SagiaLegalStatus code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaLegalStatusModel.CODE, code);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaLegalStatusModel) parameterList.get(0);
        } else {
            return null;
        }
    }
}
