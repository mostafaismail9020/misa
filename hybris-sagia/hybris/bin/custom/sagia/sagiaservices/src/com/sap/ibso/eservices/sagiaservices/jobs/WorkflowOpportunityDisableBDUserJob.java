package com.sap.ibso.eservices.sagiaservices.jobs;

import com.sap.ibso.eservices.sagiaservices.services.b2b.SagiaB2BCustomer;
import de.hybris.platform.b2b.model.B2BCustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Set;

/**
 * The type Workflow opportunity disable bd user job.
 */
public class WorkflowOpportunityDisableBDUserJob extends AbstractJobPerformable<CronJobModel> {
    private static final Logger LOG = Logger.getLogger(WorkflowOpportunityDisableBDUserJob.class);

    /**
     * The Model service.
     */
    private ModelService modelService;

    /**
     * The Sagia b 2 b customer.
     */
    private SagiaB2BCustomer sagiaB2BCustomer;

    @Override
    public PerformResult perform(final CronJobModel cronJob) {
        PerformResult result = null;
        try {
            Set<B2BCustomerModel> customerModelSet = sagiaB2BCustomer.getB2BCustomersToDisable();
            if(CollectionUtils.isEmpty(customerModelSet)) {
                LOG.info("No Workflow Opportunity Busniess Development User found for disabling.");
            } else {
                disableWOBDUsers(customerModelSet);
            }
            result = new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        } catch (Exception e) {
            LOG.error("Error while running the cronjob - ", e);
            result = new PerformResult(CronJobResult.ERROR, CronJobStatus.ABORTED);
        }
        return result;
    }

    private void disableWOBDUsers(final Set<B2BCustomerModel> customerModelSet) {
        if(LOG.isDebugEnabled()) {
            customerModelSet.forEach(b2BCustomerModel ->
                    LOG.debug(String.format("Customer - %s will be disabled.", b2BCustomerModel.getUid())));
        }
        customerModelSet.forEach(b2BCustomerModel -> b2BCustomerModel.setActive(false));
        modelService.saveAll(customerModelSet);
    }

    /**
     * Gets model service.
     *
     * @return the model service
     */
    public ModelService getModelService() {
        return modelService;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    /**
     * Gets sagia b 2 b customer.
     *
     * @return the sagia b 2 b customer
     */
    public SagiaB2BCustomer getSagiaB2BCustomer() {
        return sagiaB2BCustomer;
    }

    /**
     * Sets sagia b 2 b customer.
     *
     * @param sagiaB2BCustomer the sagia b 2 b customer
     */
    public void setSagiaB2BCustomer(SagiaB2BCustomer sagiaB2BCustomer) {
        this.sagiaB2BCustomer = sagiaB2BCustomer;
    }
}
