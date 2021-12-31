package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaCountryDAO extends DefaultGenericDao<SagiaCountryModel> implements SagiaCountryDAO {

    public DefaultSagiaCountryDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaCountryModel> getAllCountries() {
        return find();
    }

    @Override
    public SagiaCountryModel getCountryForCode(String code) {
        validateParameterNotNull(code, "SagiaCountry code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaCountryModel.CODE, code);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaCountryModel) parameterList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public SagiaCountryModel getCountryCodeForName(String name) {
        validateParameterNotNull(name, "SagiaCountry code must not be null!");

        final Map parameters = new HashMap();
        parameters.put(SagiaCountryModel.NAME, name);
        List parameterList = find(parameters);
        if(CollectionUtils.isNotEmpty(parameterList)){
            return (SagiaCountryModel) parameterList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<SagiaCountryModel> getShareHolderCheckCountries() {
    	Map<String,Boolean> parameters = new HashMap();
        parameters.put(SagiaCountryModel.ISSHAREHOLDERCHECK,true);
        return super.find(parameters);
    }

	@Override
	public SagiaCountryModel getCountryForPhonePrefix(String phonePrefix) {
		 validateParameterNotNull(phonePrefix, "SagiaCountry code must not be null!");

	        final Map parameters = new HashMap();
	        parameters.put(SagiaCountryModel.PHONEPREFIX, phonePrefix);
	        List parameterList = find(parameters);
	        if(CollectionUtils.isNotEmpty(parameterList)){
	            return (SagiaCountryModel) parameterList.get(0);
	        } else {
	            return null;
	        }
	}
}
