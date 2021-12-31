package com.sap.ibso.eservices.sagiaservices.price;

import com.sap.ibso.eservices.sagiaservices.data.price.PriceSimulationData;
import org.springframework.util.Assert;

/**
 * Provides access to e-services related price simulations.
 */
public interface PriceSimulationService
{
    /**
     * Triggers a price simulation for a service type and implicitly for the currently logged in user.
     * Amendment parameters are only relevant for service types which indicate license amendment scenarios.
     * Renewal parameters are only relevant for service types which indicate license renewal scenarios.
     * <p>
     * The following basic parameter check is performed: service type must not be null.
     * </p>
     * The returned price simulation data contain one or more priced e-services dependent on the service type. A priced
     * e-service is indicated by a language dependent service name. The language is implicitly determined by the
     * current Hybris session language. If multiple priced e-services are returned (within price simulation data) then
     * they are sorted (case insensitive and descending) by service name.
     *
     * @param serviceType the service type which defines the to-be-priced e-service(s)
     * @param amendmentParam the amendment parameters or null
     * @param renewalParam the renewal parameters or null
     * @return the price simulation data which contain priced e-service(s)
     * @throws IllegalArgumentException if the basic parameter check fails
     */
    PriceSimulationData getPriceSimulationData(String serviceType, AmendmentParam amendmentParam, RenewalParam renewalParam);

    /**
     * Triggers a price simulation for a service type for License apply.
     * <p>
     * The following basic parameter check is performed: service type amd qeemah must not be null.
     * </p>
     * The returned price simulation data contain one or more priced e-services dependent on the service type. A priced
     * e-service is indicated by a language dependent service name. The language is implicitly determined by the
     * current Hybris session language. If multiple priced e-services are returned (within price simulation data) then
     * they are sorted (case insensitive and descending) by service name.
     *
     * @param serviceType the service type which defines the to-be-priced e-service(s)
     * @param qeemah the qeemah on which the Apply for New License is made
     * @return the price simulation data which contain priced e-service(s)
     * @throws IllegalArgumentException if the basic parameter check fails
     */
     PriceSimulationData getPriceSimulationData(String serviceType, String qeemah);

    /**
     * Triggers a price simulation for a service type which indicates a license amendment scenario, and implicitly for
     * the currently logged in user.
     * <p>
     * The following basic parameter checks are performed: service type must not be null, amendment parameters must not be null.
     * </p>
     * The returned price simulation data contain one or more priced e-services dependent on the service type. A priced
     * e-service is indicated by a language dependent service name. The language is implicitly determined by the
     * current Hybris session language. If multiple priced e-services are returned (within price simulation data) then
     * they are sorted (case insensitive and descending) by service name.
     *
     * @param serviceType the service type which defines the to-be-priced e-service(s)
     * @param amendmentParam the amendment parameters
     * @return the price simulation data which contain priced e-service(s)
     * @throws IllegalArgumentException if a basic parameter check fails
     */
    default PriceSimulationData getPriceSimulationDataForAmendment(String serviceType, AmendmentParam amendmentParam)
    {
        Assert.notNull(amendmentParam, "Amendment parameters must not be null");
        return getPriceSimulationData(serviceType, amendmentParam, null);
    }

    /**
     * Triggers a price simulation for a service type which indicates a license renewal scenario, and implicitly for
     * the currently logged in user.
     * <p>
     * The following basic parameter checks are performed: service type must not be null, renewal parameters must not be null.
     * </p>
     * The returned price simulation data contain one or more priced e-services dependent on the service type. A priced
     * e-service is indicated by a language dependent service name. The language is implicitly determined by the
     * current Hybris session language. If multiple priced e-services are returned (within price simulation data) then
     * they are sorted (case insensitive and descending) by service name.
     *
     * @param serviceType the service type which defines the to-be-priced e-service(s)
     * @param renewalParam the renewal parameters
     * @return the price simulation data which contain priced e-service(s)
     * @throws IllegalArgumentException if a basic parameter check fails
     */
    default PriceSimulationData getPriceSimulationDataForRenewal(String serviceType, RenewalParam renewalParam)
    {
        Assert.notNull(renewalParam, "Renewal parameters must not be null");
        return getPriceSimulationData(serviceType, null, renewalParam);
    }

    /**
     * Triggers a price simulation for a service type which neither indicates a license amendment scenario nor a license
     * renewal scenario, and implicitly for the currently logged in user.
     * <p>
     * The following basic parameter check is performed: service type must not be null.
     * </p>
     * The returned price simulation data contain one or more priced e-services dependent on the service type. A priced
     * e-service is indicated by a language dependent service name. The language is implicitly determined by the
     * current Hybris session language. If multiple priced e-services are returned (within price simulation data) then
     * they are sorted (case insensitive and descending) by service name.
     *
     * @param serviceType the service type which defines the to-be-priced e-service(s)
     * @return the price simulation data which contain priced e-service(s)
     * @throws IllegalArgumentException if the basic parameter check fails
     */
    default PriceSimulationData getPriceSimulationData(String serviceType)
    {
        return getPriceSimulationData(serviceType, null, null);
    }

}
