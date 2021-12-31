package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaSectorModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaSectorDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaSectorDAO extends DefaultGenericDao<SagiaSectorModel> implements SagiaSectorDAO
{
    public DefaultSagiaSectorDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaSectorModel> getAllSectors() {
        return find();
    }

    @Override
    public SagiaSectorModel getSectorForCode(String code) {
        validateParameterNotNull(code, "SagiaSector code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaSectorModel.CODE, code);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaSectorModel) parameterList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public SagiaSectorModel getSectorForSectorCode(String sectorCode) {
        validateParameterNotNull(sectorCode, "New SagiaSector code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaSectorModel.SECTORCODE, sectorCode);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaSectorModel) parameterList.get(0);
        } else {
            return null;
        }
    }
}
