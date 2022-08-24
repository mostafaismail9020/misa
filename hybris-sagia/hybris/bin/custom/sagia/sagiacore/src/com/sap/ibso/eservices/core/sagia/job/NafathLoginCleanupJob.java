package com.sap.ibso.eservices.core.sagia.job;

import com.sap.ibso.eservices.core.sagia.services.NafathService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.NafathLoginCleanupCronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;

public class NafathLoginCleanupJob extends AbstractJobPerformable<NafathLoginCleanupCronJobModel> {

    private static final Logger LOG = Logger.getLogger(NafathLoginCleanupJob.class);

    private NafathService nafathService;

    @Override
    public PerformResult perform(NafathLoginCleanupCronJobModel model) {
        if(nafathService.removeOldLoginRecords(model.getXDaysOld())){
            return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
        }else {
            return new PerformResult(CronJobResult.FAILURE, CronJobStatus.ABORTED);
        }
    }

    public NafathService getNafathService() {
        return nafathService;
    }

    public void setNafathService(NafathService nafathService) {
        this.nafathService = nafathService;
    }
}
