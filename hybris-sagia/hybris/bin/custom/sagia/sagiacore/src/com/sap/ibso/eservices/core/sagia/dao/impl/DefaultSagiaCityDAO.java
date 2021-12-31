package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaCityModel;
import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCityDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaCityDAO extends DefaultGenericDao<SagiaCityModel> implements SagiaCityDAO {

    public DefaultSagiaCityDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaCityModel> getAllCities() {
        return find();
    }

    @Override
    public SagiaCityModel getCityForCode(String code) {

        validateParameterNotNull(code, "SagiaCity code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaCityModel.CODE, code);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaCityModel) parameterList.get(0);
        } else {
            return null;
        }
    }


    @Override
    public SagiaCityModel getCityForName(String name) {

        validateParameterNotNull(name, "SagiaCity name must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaCityModel.NAME, name);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaCityModel) parameterList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SagiaCityModel> getAllCitiesForRegionCode(String code) {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {PK} FROM ");
        query.append(" { ");
        query.append("  SagiaCity as city ");
        query.append("  JOIN SagiaRegion AS region ON {region.pk} = {city.region} ");
        query.append(" } ");
        query.append(" WHERE {region.code} = ?code ");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("code", code);

        final SearchResult<SagiaCityModel> result = getFlexibleSearchService().search(query.toString(), parameters);
        return result.getResult();
    }

}
