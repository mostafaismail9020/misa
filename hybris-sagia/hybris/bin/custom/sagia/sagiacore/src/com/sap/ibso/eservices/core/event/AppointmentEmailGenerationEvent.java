package com.sap.ibso.eservices.core.event;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.AppointmentData;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;

public class AppointmentEmailGenerationEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

    private AppointmentData appointmentData;

    public AppointmentEmailGenerationEvent() {
        super();
    }

    public AppointmentData getAppointmentData() {
        return appointmentData;
    }

    public void setAppointmentData(AppointmentData appointmentData) {
        this.appointmentData = appointmentData;
    }
}
