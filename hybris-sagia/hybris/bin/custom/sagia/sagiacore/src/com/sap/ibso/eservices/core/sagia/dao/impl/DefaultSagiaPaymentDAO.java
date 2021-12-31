package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.google.common.collect.Maps;
import com.sap.ibso.eservices.core.model.SagiaPaymentModel;
import com.sap.ibso.eservices.core.sagia.dao.password.SagiaPaymentDAO;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSagiaPaymentDAO extends DefaultGenericDao<SagiaPaymentModel> implements SagiaPaymentDAO {

    public DefaultSagiaPaymentDAO(String typecode) {
        super(typecode);
    }

    @Override
    public List<SagiaPaymentModel> getNotUpdatedPayments() {
        Map<String,Boolean> parameters = new HashMap();
        parameters.put(SagiaPaymentModel.UPDATED,false);
        return super.find(parameters);
    }

    @Override
    public SagiaPaymentModel getBySalesOrderId(String salesOrderId) {
        Map<String,String> parameters = new HashMap();
        parameters.put(SagiaPaymentModel.SALESORDERID,salesOrderId);

        List<SagiaPaymentModel> result = super.find(parameters);
        if(!result.isEmpty())
            return super.find(parameters).get(0);

        return null;
    }
    
    @Override
    public List<SagiaPaymentModel> getSagiaPaymentsByCustomer(CustomerModel customer) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(SagiaPaymentModel.USER,customer);
        return super.find(parameters);
    }
}
