package com.sap.ibso.eservices.bol.price;

import com.sap.ibso.eservices.bol.price.data.PriceSimulationBackendData;

/**
 * Provides synchronous access to e-services related price simulations in SAP backend system.
 */
public interface PriceSimulationBackendService
{
    /**
     * Triggers a price simulation for a service type and entity.
     * Amendment parameters are only relevant for service types which indicate license amendment scenarios.
     * Renewal parameters are only relevant for service types which indicate license renewal scenarios.
     *
     * The returned price simulation backend data contain one or more priced e-services dependent on the service type.
     * A priced e-service is indicated by a language dependent service name. The service name language is determined by
     * the given language ISO code. If multiple priced e-services are returned (within price simulation backend data) then
     * they are sorted (case insensitive and descending) by service name.
     *
     * @param serviceType the service type
     * @param entityId the unique entity identifier
     * @param amendmentParam the amendment parameters or null
     * @param renewalParam the renewal parameters or null
     * @param language the language ISO code
     * @return the price simulation backend data which contain priced e-service(s)
     */
    PriceSimulationBackendData getPriceSimulationData(String serviceType, String entityId, AmendmentBackendParam amendmentParam, RenewalBackendParam renewalParam, String language);

    /**
     * Triggers a price simulation for a service type and a qeemah.
     *
     * The returned price simulation backend data contain one or more priced e-services dependent on the qeemah.
     * A priced e-service is indicated by a language dependent service name. The service name language is determined by
     * the given language ISO code. If multiple priced e-services are returned (within price simulation backend data) then
     * they are sorted (case insensitive and descending) by service name.
     *
     * @param serviceType the service type
     * @param qeemah the unique entity identifier
     * @param language the language ISO code
     * @return the price simulation backend data which contain priced e-service(s)
     */
    PriceSimulationBackendData getPriceSimulationData(String serviceType, String qeemah, String language);
}
