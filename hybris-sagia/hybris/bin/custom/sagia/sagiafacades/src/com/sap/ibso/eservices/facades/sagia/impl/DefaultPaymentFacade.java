package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.model.SagiaPaymentModel;
import com.sap.ibso.eservices.facades.data.account.SuccessfulPaymentData;
import com.sap.ibso.eservices.facades.data.payment.PaymentData;
import com.sap.ibso.eservices.facades.populators.AfterPaymentPopulator;
import com.sap.ibso.eservices.facades.populators.SagiaPaymentPopulator;
import com.sap.ibso.eservices.facades.sagia.PaymentFacade;
import com.sap.ibso.eservices.sagiaservices.data.payment.SalesOrderPayment;
import com.sap.ibso.eservices.sagiaservices.price.SalesOrderParam;
import com.sap.ibso.eservices.sagiaservices.price.SalesOrderService;
import com.sap.ibso.eservices.sagiaservices.price.impl.DefaultSalesOrderService;
import com.sap.ibso.eservices.sagiaservices.services.ZePaymentDetailsFacade;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.core.model.user.CustomerModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaPaymentService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultPaymentFacade
 */
public class DefaultPaymentFacade implements PaymentFacade{
    private static final Logger LOGGER = LogManager.getLogger(DefaultPaymentFacade.class);

    private SagiaPaymentPopulator paymentPopulator;
    private ZePaymentDetailsFacade zePaymentFacade;
    private ModelService modelService;
    private SalesOrderService salesOrderService;
    private AfterPaymentPopulator afterPaymentPopulator;
    private SagiaPaymentService sagiaPaymentService;
    private UserService userService;
    /**
     * retrieves Payments
     * @return - Payments DTOs for the dashboard
     */
    public List<PaymentData> getPayments(){
        Collection<SalesOrderPayment> salesOrderPayments = zePaymentFacade.getPayments();
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();
        List<SagiaPaymentModel> userPayments = sagiaPaymentService.getSagiaPaymentsByCustomer(customer);
        List<PaymentData> paymentDataList = new ArrayList<>();
        if(salesOrderPayments != null){
            for(SalesOrderPayment payment : salesOrderPayments){
                PaymentData paymentData = new PaymentData();
                for(SagiaPaymentModel userPayment : userPayments) {
                	if(payment.getServiceId().equals(userPayment.getSalesOrderId())) {
                		if(StringUtils.isNotEmpty(userPayment.getTransactionId())) {
                			LOGGER.info("*****CRM ServiceId "+payment.getServiceId()+" ### Hybris SalesOrderId: "+userPayment.getSalesOrderId());
                			paymentData.setHybrisStatusDescription("Paid");
                		}
                	}
                }
                paymentPopulator.populate(payment,paymentData);
                paymentDataList.add(paymentData);
            }
        }
        return paymentDataList;
    }

    /**
     * retrieves Payment
     * @param id - The id of the payment entity
     * @return - Payment DTO for the dashboard retrieved by id
     */
    public PaymentData getPayment(String id){
        SalesOrderPayment salesOrderPayment = zePaymentFacade.getPayment(id);
        CustomerModel customer = (CustomerModel) userService.getCurrentUser();
        List<SagiaPaymentModel> userPayments = sagiaPaymentService.getSagiaPaymentsByCustomer(customer);
        
        PaymentData paymentData = new PaymentData();
        
        for(SagiaPaymentModel userPayment : userPayments) {
        	if(salesOrderPayment.getServiceId().equals(userPayment.getSalesOrderId())) {
        		if(StringUtils.isNotEmpty(userPayment.getTransactionId())) {
        			paymentData.setHybrisStatusDescription("Paid");
        			LOGGER.info("***** CRM ServiceId "+salesOrderPayment.getServiceId()+" ### Hybris SalesOrderId: "+userPayment.getSalesOrderId());
        		}
        	}
        }

        paymentPopulator.populate(salesOrderPayment,paymentData);
        return paymentData;
    }

    @Override
    public void savePayment(SuccessfulPaymentData paymentData) {
        SagiaPaymentModel salesOrder1 = new SagiaPaymentModel();
        SagiaPaymentModel salesOrder2 = new SagiaPaymentModel();
        afterPaymentPopulator.populateSagiaPayments(paymentData,salesOrder1,salesOrder2);

        modelService.save(salesOrder1);
        if(StringUtils.isNotEmpty(salesOrder2.getSalesOrderId())) {
        	modelService.save(salesOrder2);
        }

        updateLicenseApplicationSaleOrdersInCRM(salesOrder1);
        if(StringUtils.isNotEmpty(salesOrder2.getSalesOrderId())) {
        	updateLicenseApplicationSaleOrdersInCRM(salesOrder2);
        }
    }

    @Override
    public void updateLicenseApplicationSaleOrdersInCRM(SagiaPaymentModel salesOrder) {
        SalesOrderParam salesOrderUpdate1 = afterPaymentPopulator.createSalesOrderParam(salesOrder);
        if(salesOrderService.afterPaymentUpdate(salesOrderUpdate1)){
            salesOrder.setUpdated(true);
            modelService.save(salesOrder);
        }
    }

    /**
     * @param paymentPopulator
     */
    public void setPaymentPopulator(SagiaPaymentPopulator paymentPopulator) {
        this.paymentPopulator = paymentPopulator;
    }

    /**
     * @return
     */
    public SagiaPaymentPopulator getPaymentPopulator() {
        return paymentPopulator;
    }

    /**
     * @param zePaymentFacade
     */
    public void setZePaymentFacade(ZePaymentDetailsFacade zePaymentFacade) {
        this.zePaymentFacade = zePaymentFacade;
    }

    /**
     * @return
     */
    public ZePaymentDetailsFacade getZePaymentFacade() {
        return zePaymentFacade;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public void setSalesOrderService(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    public void setAfterPaymentPopulator(AfterPaymentPopulator afterPaymentPopulator) {
        this.afterPaymentPopulator = afterPaymentPopulator;
    }

	/**
	 * @param sagiaPaymentService the sagiaPaymentService to set
	 */
	public void setSagiaPaymentService(SagiaPaymentService sagiaPaymentService) {
		this.sagiaPaymentService = sagiaPaymentService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
    
}
