package com.sap.ibso.eservices.core.sagia.dao.impl;

import com.sap.ibso.eservices.core.model.SagiaPaymentSessionModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaPaymentSessionDAO;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.apache.commons.collections.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultSagiaPaymentSessionDAO  extends DefaultGenericDao<SagiaPaymentSessionModel> implements SagiaPaymentSessionDAO {
    /**
     * DefaultGenericDao is only usable when typecode is set.
     *
     * @param typecode
     */
    public DefaultSagiaPaymentSessionDAO(String typecode) {
        super(typecode);
    }


    public Optional<SagiaPaymentSessionModel> getSagiaPaymentSessionByCustomer(CustomerModel customerModel) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put(SagiaPaymentSessionModel.CUSTOMER,customerModel);
        List<SagiaPaymentSessionModel> sagiaPaymentModels = super.find(parameters);
        if(CollectionUtils.isNotEmpty(sagiaPaymentModels)) {
            return Optional.of(sagiaPaymentModels.get(0));
        }
        return Optional.empty();
    }
}
