package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaRegionModel;
import com.sap.ibso.eservices.core.model.SagiaRhqRegionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaRegionDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaRegionDAO extends DefaultGenericDao<SagiaRegionModel> implements SagiaRegionDAO {

    public DefaultSagiaRegionDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaRegionModel> getAllRegions() {
        return find();
    }
    
    @Override
    public List<SagiaRhqRegionModel> getRhqRegionsList() {
    	 final StringBuilder query = new StringBuilder();

         query.append(" SELECT {PK} FROM ");
         query.append(" { ");
         query.append("  SagiaRhqRegion");
         query.append(" } ");


         final SearchResult<SagiaRhqRegionModel> result = getFlexibleSearchService().search(query.toString());
         return result.getResult();
    }

    @Override
    public SagiaRegionModel getRegionForCode(String code) {
        validateParameterNotNull(code, "SagiaRegion code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaRegionModel.CODE, code);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaRegionModel) parameterList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SagiaRegionModel> getAllRegionsForCountryCode(String code) {

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {PK} FROM ");
        query.append(" { ");
        query.append("  SagiaRegion as region ");
        query.append("  JOIN SagiaCountry AS country ON {country.pk} = {region.country} ");
        query.append(" } ");
        query.append(" WHERE {country.code} = ?code ");

        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("code", code);

        final SearchResult<SagiaRegionModel> result = getFlexibleSearchService().search(query.toString(), parameters);
        return result.getResult();
    }

    @Override
    public SagiaRhqRegionModel getRhqRegionForCode(String code) {
        validateParameterNotNull(code, "Sagia RHQ Region code must not be null!");

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {PK} FROM ");
        query.append(" { ");
        query.append("  SagiaRhqRegion");
        query.append(" } ");
        query.append(" WHERE {code} = ?code ");


        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("code", code);

        final SearchResult<SagiaRhqRegionModel> result = getFlexibleSearchService().search(query.toString(), parameters);
        if(CollectionUtils.isNotEmpty(result.getResult())){
            return (SagiaRhqRegionModel) result.getResult().get(0);
        } else {
            return null;
        }
    }

    @Override
    public SagiaRhqRegionModel getRhqRegionCodeForName(String name) {
        validateParameterNotNull(name, "Sagia RHQ Region code must not be null!");

        final StringBuilder query = new StringBuilder();

        query.append(" SELECT {PK} FROM ");
        query.append(" { ");
        query.append("  SagiaRhqRegion");
        query.append(" } ");
        query.append(" WHERE {name} = ?name ");


        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", name);

        final SearchResult<SagiaRhqRegionModel> result = getFlexibleSearchService().search(query.toString(), parameters);
        if(CollectionUtils.isNotEmpty(result.getResult())){
            return (SagiaRhqRegionModel) result.getResult().get(0);
        } else {
            return null;
        }
    }
}
