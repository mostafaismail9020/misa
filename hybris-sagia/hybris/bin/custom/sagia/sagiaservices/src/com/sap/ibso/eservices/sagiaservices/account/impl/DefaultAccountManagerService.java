package com.sap.ibso.eservices.sagiaservices.account.impl;

import com.sap.ibso.eservices.bol.BackendAwareService;
import com.sap.ibso.eservices.bol.account.AccountManagerBackendService;
import com.sap.ibso.eservices.bol.account.data.DedicatedAccountManagerBackendData;
import com.sap.ibso.eservices.sagiaservices.account.AccountManagerService;
import com.sap.ibso.eservices.sagiaservices.data.account.DedicatedAccountManagerData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;

/**
 * Implements access to e-services related account manager data.
 */
public class DefaultAccountManagerService extends BackendAwareService implements AccountManagerService
{
    private InvestorMappingService investorMappingService;

    /**
     * Creates the default account manager service.
     *
     * @param accountManagerBackendServiceBeanName the bean name (or alias) of the account manager backend service
     * @param investorMappingService               the investor mapping service to retrieve the entity identifier associated with the current user
     */
    public DefaultAccountManagerService(String accountManagerBackendServiceBeanName, InvestorMappingService investorMappingService)
    {
        super(accountManagerBackendServiceBeanName);
        this.investorMappingService = investorMappingService;
    }

    @Override
    public DedicatedAccountManagerData getDedicatedAccountManagerData()
    {
        // Backend service access
        AccountManagerBackendService backendService = getBackendService();
        // Retrieve the document in the corresponding backend system
        DedicatedAccountManagerBackendData backendData = backendService.getDedicatedAccountManagerData(investorMappingService.getEntityId());

        return convert(backendData);
    }

    /**
     * Converts a {@link DedicatedAccountManagerBackendData} instance into a {@link DedicatedAccountManagerData} instance.
     *
     * @param backendData the dedicated account manager backend data
     * @return the dedicated account manager data
     */
    private DedicatedAccountManagerData convert(DedicatedAccountManagerBackendData backendData)
    {
        DedicatedAccountManagerData data = new DedicatedAccountManagerData();

        data.setTitle(backendData.getTitle());
        data.setFirstName(backendData.getFirstName());
        data.setLastName(backendData.getLastName());
        data.setEmail(backendData.getEmail());
        data.setPhoneNumber(backendData.getPhoneNumber());
        data.setMobileNumber(backendData.getMobileNumber());

        return data;
    }
}
