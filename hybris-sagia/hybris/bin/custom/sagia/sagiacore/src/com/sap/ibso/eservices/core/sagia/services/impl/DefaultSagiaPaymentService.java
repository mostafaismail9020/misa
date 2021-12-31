package com.sap.ibso.eservices.core.sagia.services.impl;


import com.sap.ibso.eservices.core.model.SagiaPaymentModel;
import com.sap.ibso.eservices.core.sagia.dao.password.SagiaPaymentDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaPaymentService;
import de.hybris.platform.servicelayer.internal.dao.GenericDao;
import de.hybris.platform.servicelayer.internal.service.AbstractBusinessService;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;
import java.util.Objects;

public class DefaultSagiaPaymentService extends AbstractBusinessService implements SagiaPaymentService {
    private SagiaPaymentDAO sagiaPaymentDAO;

    public void setSagiaPaymentDAO(SagiaPaymentDAO sagiaPaymentDAO) {
        this.sagiaPaymentDAO = sagiaPaymentDAO;
    }

    @Override
    public List<SagiaPaymentModel> getNotUpdatedPayments() {
        return sagiaPaymentDAO.getNotUpdatedPayments();
    }

    @Override
    public SagiaPaymentModel getBySalesOrder(String salesOrderId) {
        return sagiaPaymentDAO.getBySalesOrderId(salesOrderId);
    }

    @Override
    public boolean isSalesOrderSaved(String salesOrderId) {
        return !Objects.isNull(sagiaPaymentDAO.getBySalesOrderId(salesOrderId));
    }
    
    @Override
    public List<SagiaPaymentModel> getSagiaPaymentsByCustomer(CustomerModel customer) {
        return sagiaPaymentDAO.getSagiaPaymentsByCustomer(customer);
    }
}
