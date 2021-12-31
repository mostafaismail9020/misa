package com.sap.ibso.eservices.facades.sagia;

/**
 * SagiaDashboardWithoutLicenseFacade
 */
public interface SagiaDashboardWithoutLicenseFacade {
    /**
     * checks if has licence
     * @return boolean
     */
    boolean hasLicense();

    /**
     * Checks if the investor has an awaiting payment for license.
     * This is helpful to check whether to show or not the notification
     * button in the top menu bar, even when the user does not have
     * a license.
     * @return
     */
    boolean hasAwaitingPayment();

	void evictApplicationStatus();
}
