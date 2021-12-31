package com.sap.ibso.eservices.core.event;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.model.process.AppointmentEmailGenerationProcessModel;
import de.hybris.platform.processengine.impl.DefaultBusinessProcessService;
import de.hybris.platform.servicelayer.internal.model.impl.DefaultModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import java.time.LocalTime;

public class AppointmentEventListener extends AbstractAcceleratorSiteEventListener<AppointmentEmailGenerationEvent> {
    private DefaultBusinessProcessService businessProcessService;
    private DefaultModelService modelService;
    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    protected SiteChannel getSiteChannelForEvent(AppointmentEmailGenerationEvent event) {
        final BaseSiteModel site = event.getSite();
        ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
        return site.getChannel();
    }

    @Override
    protected void onSiteEvent(AppointmentEmailGenerationEvent event) {
        final AppointmentEmailGenerationProcessModel appointmentEmailGenerationProcessModel =
                getBusinessProcessService().createProcess("appointmentEmailGenerationProcess-"
                + event.getCustomer().getUid()+"-"+System.currentTimeMillis(),"appointmentEmailGenerationProcess");
        appointmentEmailGenerationProcessModel.setSite(event.getSite());
        appointmentEmailGenerationProcessModel.setCustomer(event.getCustomer());
        appointmentEmailGenerationProcessModel.setLanguage(event.getLanguage());
        appointmentEmailGenerationProcessModel.setCurrency(event.getCurrency());
        appointmentEmailGenerationProcessModel.setStore(event.getBaseStore());
        appointmentEmailGenerationProcessModel.setAppoimentNumber(event.getAppointmentData().getService1());
        appointmentEmailGenerationProcessModel.setBranch(event.getAppointmentData().getBranchDesc());
        appointmentEmailGenerationProcessModel.setDate(event.getAppointmentData().getDateAppt().toLocalDate().toString());

        LocalTime from = event.getAppointmentData().getTimeFrom();
        appointmentEmailGenerationProcessModel.setTimeFrom(from.toString());

        getModelService().save(appointmentEmailGenerationProcessModel);
        getBusinessProcessService().startProcess(appointmentEmailGenerationProcessModel);
    }

    public void setBusinessProcessService(DefaultBusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public DefaultBusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setModelService(DefaultModelService modelService) {
        this.modelService = modelService;
    }

    public DefaultModelService getModelService() {
        return modelService;
    }

    public SagiaFormatProvider getSagiaFormatProvider() {
        return sagiaFormatProvider;
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
