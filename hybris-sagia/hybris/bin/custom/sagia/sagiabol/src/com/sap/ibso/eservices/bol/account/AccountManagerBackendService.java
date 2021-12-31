package com.sap.ibso.eservices.bol.account;

import com.sap.ibso.eservices.bol.account.data.DedicatedAccountManagerBackendData;

/**
 * Provides synchronous access to e-services related account manager data in SAP backend system.
 */
public interface AccountManagerBackendService
{
    /**
     * Retrieves certain information about the dedicated account manager of an entity.
     *
     * @param entityId the entity identifier
     * @return the dedicated account manager backend data
     */
    DedicatedAccountManagerBackendData getDedicatedAccountManagerData(String entityId);
}
