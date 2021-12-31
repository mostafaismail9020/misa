package com.sap.ibso.eservices.sagiaservices.account;

import com.sap.ibso.eservices.sagiaservices.data.account.DedicatedAccountManagerData;

/**
 * Provides access to e-services related account manager data.
 */
public interface AccountManagerService
{
    /**
     * Retrieves certain information about the dedicated account manager.
     *
     * @return the dedicated account manager data
     */
    DedicatedAccountManagerData getDedicatedAccountManagerData();
}
