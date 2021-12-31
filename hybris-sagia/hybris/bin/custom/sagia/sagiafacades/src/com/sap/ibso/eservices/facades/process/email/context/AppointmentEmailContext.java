package com.sap.ibso.eservices.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.AppointmentEmailGenerationProcessModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import org.apache.commons.lang.StringUtils;

/**
 *
 */
public class AppointmentEmailContext extends CustomerEmailContext {

    /**
     * @param storeFrontCustomerProcessModel
     * @param emailPageModel
     */
    @Override
    public void init(StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, EmailPageModel emailPageModel) {
        super.init(storeFrontCustomerProcessModel, emailPageModel);

        put("textAlign", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "left" : "right");
        put("textDirection", StringUtils.equalsIgnoreCase(getEmailLanguage(storeFrontCustomerProcessModel).getIsocode(), "en") ? "ltr" : "rtl");

        put("appointmentNumber",((AppointmentEmailGenerationProcessModel) storeFrontCustomerProcessModel).getAppoimentNumber());
        put("branchDescription",((AppointmentEmailGenerationProcessModel) storeFrontCustomerProcessModel).getBranch());
        put("timeFrom",((AppointmentEmailGenerationProcessModel) storeFrontCustomerProcessModel).getTimeFrom());
        put("appointmentDate",((AppointmentEmailGenerationProcessModel) storeFrontCustomerProcessModel).getDate());
    }
}
