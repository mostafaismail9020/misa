package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.sagia.SagiaPaymentDetailsFacade;
import com.sap.ibso.eservices.sagiaservices.data.payment.PaymentDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.license.payment.LicensePaymentService;

/**
 * DefaultSagiaPaymentDetailsFacade
 */
public class DefaultSagiaPaymentDetailsFacade implements SagiaPaymentDetailsFacade {

    private LicensePaymentService licensePaymentService;

    /**
     * Payment details of a new license.
     *
     * @return - Payment details DTO containing requested information about license payment.
     */
    @Override
    public PaymentDetailsData requestPaymentDetails(String serviceType, String qeemah) {
        PaymentDetailsData paymentDetailsData = licensePaymentService.requestPaymentDetails(serviceType, qeemah);
        return paymentDetailsData;
    }

    /**
     * @param licensePaymentService
     */
    public void setLicensePaymentService(LicensePaymentService licensePaymentService) {
        this.licensePaymentService = licensePaymentService;
    }

    /**
     * @return
     */
    public LicensePaymentService getLicensePaymentService() {
        return licensePaymentService;
    }

}
