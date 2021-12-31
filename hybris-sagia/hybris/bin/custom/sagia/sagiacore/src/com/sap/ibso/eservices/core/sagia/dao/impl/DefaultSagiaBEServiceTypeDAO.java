package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaBEServiceTypeModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaBEServiceTypeDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaBEServiceTypeDAO extends DefaultGenericDao<SagiaBEServiceTypeModel> implements SagiaBEServiceTypeDAO {

    public DefaultSagiaBEServiceTypeDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaBEServiceTypeModel> getAllSagiaBEServiceTypes() {
        return find();
    }

    @Override
    public String getTransTypeForCode(String code) {
        validateParameterNotNull(code, "SagiaBEServiceType code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaBEServiceTypeModel.CODE, code);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            final SagiaBEServiceTypeModel beServiceType =  (SagiaBEServiceTypeModel) parameterList.get(0);
            return beServiceType.getTransType();
        } else {
            return null;
        }
    }
}
