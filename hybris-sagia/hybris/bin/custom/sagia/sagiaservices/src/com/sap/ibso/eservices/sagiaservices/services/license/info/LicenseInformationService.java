package com.sap.ibso.eservices.sagiaservices.services.license.info;

/**
 * Provides access to license information.
 */
public interface LicenseInformationService {
    /**
     * Checks whether the company of the currently logged in user has an issued license.
     *
     * @return <code>true</code> if the company of the currently logged in user has an issued license, <code>false</code> otherwise
     */
    boolean hasLicense();

    /**
     * Checks whether the investor has an awaiting payment for a license.
     * @return <code>true</code> if the investor has an awaiting payment, <code>false</code> otherwise
     */
    boolean hasAwaitingPayment();

	void evictApplicationStatus();
}
