package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaCompanyProfileModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCompanyProfileDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultSagiaCompanyProfileDAO extends DefaultGenericDao<SagiaCompanyProfileModel> implements SagiaCompanyProfileDAO {

    private FlexibleSearchService flexibleSearchService;


    public DefaultSagiaCompanyProfileDAO(String typecode) {
        super(typecode);
    }


    @Override
    public SagiaCompanyProfileModel getSagiaCompanyProfile(String userPk) {
        validateParameterNotNull(userPk, "User PK cannot be null");
        final Map<String, String> parameters = new HashMap<>();
        parameters.put(SagiaCompanyProfileModel.USER, userPk);
        List<SagiaCompanyProfileModel> companyProfileList = find(parameters);
        if(companyProfileList.size()< 1 ){
            return null;
        }
        return companyProfileList.get(0);
    }



    /**
     * @return the flexibleSearchService
     */
    public FlexibleSearchService getFlexibleSearchService() {
        return flexibleSearchService;
    }

    /**
     * @param flexibleSearchService the flexibleSearchService to set
     */
    public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
