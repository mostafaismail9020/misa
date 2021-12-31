package com.sap.ibso.eservices.sagiaservices.jobs;

import com.sap.ibso.eservices.core.model.SAGIASiteCronJobModel;
import com.sap.ibso.eservices.core.model.SagiaPaymentModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaPaymentService;
import com.sap.ibso.eservices.sagiaservices.price.SalesOrderParam;
import com.sap.ibso.eservices.sagiaservices.price.SalesOrderService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.sap.core.common.exceptions.ApplicationBaseRuntimeException;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.annotation.Resource;

public class SagiaUpdateSaleOrderJob extends AbstractJobPerformable<SAGIASiteCronJobModel>{

    private static final Logger LOG = LoggerFactory.getLogger(SagiaUpdateSaleOrderJob.class);

    private SagiaPaymentService sagiaPaymentService;

    private SalesOrderService salesOrderService;

    private ModelService modelService;
    

	@Resource(name = "baseSiteService")
	private BaseSiteService baseSiteService;

    @Override
    public PerformResult perform(SAGIASiteCronJobModel updateSaleOrderDataJobModel) {
    	
    	baseSiteService.setCurrentBaseSite(updateSaleOrderDataJobModel.getBaseSite() ,  true);
	
    	
    	
        LOG.info("Starting to update Sale Orders in CRM...");
        List<SagiaPaymentModel> paymentsToUpdate = sagiaPaymentService.getNotUpdatedPayments();

        for(SagiaPaymentModel payment : paymentsToUpdate){
            try {
               if(salesOrderService.afterPaymentUpdate(createSalesOrderParam(payment))){
                   payment.setUpdated(true);
                   BaseStoreService baseStoreService ;
                   modelService.save(payment);
               }
            }
            catch (ApplicationBaseRuntimeException exception){
                LOG.error("Sales order "+payment.getSalesOrderId()+" has not been marked in CRM as payed.",exception);
            }
        }
        LOG.info("Successfully updated "+paymentsToUpdate.size()+" sale order(s) in CRM.");
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public SalesOrderParam createSalesOrderParam(SagiaPaymentModel sagiaPaymentModel){
        SalesOrderParam salesOrderUpdate = new SalesOrderParam();
        salesOrderUpdate.setSalesOrderAmount(sagiaPaymentModel.getSalesOrderAmount());
        salesOrderUpdate.setTotalAmountPayed(sagiaPaymentModel.getTotalPayedAmount());
        salesOrderUpdate.setSalesOrderId(sagiaPaymentModel.getSalesOrderId());
        salesOrderUpdate.setTransactionNumber(sagiaPaymentModel.getTransactionId());
        salesOrderUpdate.setTransactionTime(sagiaPaymentModel.getCreationtime());
        salesOrderUpdate.setInvestorId(sagiaPaymentModel.getUser().getEntityID());
        return salesOrderUpdate;
    }

    public void setSagiaPaymentService(SagiaPaymentService sagiaPaymentService) {
        this.sagiaPaymentService = sagiaPaymentService;
    }

    public void setSalesOrderService(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
    
    
}