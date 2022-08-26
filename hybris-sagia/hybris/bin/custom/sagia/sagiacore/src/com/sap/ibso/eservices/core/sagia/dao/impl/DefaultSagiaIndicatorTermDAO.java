package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaIndicatorTermModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaIndicatorTermDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.internal.dao.SortParameters;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSagiaIndicatorTermDAO extends DefaultGenericDao<SagiaIndicatorTermModel> implements SagiaIndicatorTermDAO {

    public DefaultSagiaIndicatorTermDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaIndicatorTermModel> getActiveIndicatorTerms() {

        final Map filterParameters = new HashMap();
        filterParameters.put(SagiaIndicatorTermModel.ISACTIVE, true);

        final SortParameters sortParameters = new SortParameters();
        sortParameters.addSortParameter(SagiaIndicatorTermModel.CODE, SortParameters.SortOrder.ASCENDING);

        List indicatorTermList = find(filterParameters,sortParameters);

        if (CollectionUtils.isNotEmpty(indicatorTermList)) {
            return indicatorTermList;
        } else {
            //return empty list
            return new ArrayList<SagiaIndicatorTermModel>();
        }
    }
}
