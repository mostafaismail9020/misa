package com.sap.ibso.eservices.core.sagia.dao.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.sap.ibso.eservices.core.model.SagiaLicenseTypeModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLicenseTypeDAO;

import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;

public class DefaultSagiaLicenseTypeDAO extends DefaultGenericDao<SagiaLicenseTypeModel> implements SagiaLicenseTypeDAO {

    public DefaultSagiaLicenseTypeDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaLicenseTypeModel> getAllLicenseTypes() {
        return find(SortParameters.singletonAscending(SagiaLicenseTypeModel.CODE));
    }

    @Override
    public SagiaLicenseTypeModel getLicenseTypeForCode(String code) {

        validateParameterNotNull(code, "SagiaLicenseType code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaLicenseTypeModel.CODE, code);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaLicenseTypeModel) parameterList.get(0);
        } else {
            return null;
        }
    }
}
