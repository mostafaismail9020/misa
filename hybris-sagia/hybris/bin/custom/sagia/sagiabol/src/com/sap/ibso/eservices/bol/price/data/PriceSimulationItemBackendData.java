package com.sap.ibso.eservices.bol.price.data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Provides a data transfer object for a price simulation item which is a priced e-service from SAP backend system.
 */
public class PriceSimulationItemBackendData  implements Serializable
{
    private String serviceName;
    private String language;
    private BigDecimal netValue;
    private String currency;

    /**
     * Gets the language dependent service name of the priced e-service.
     *
     * @return the service name
     */
    public String getServiceName()
    {
        return serviceName;
    }

    /**
     * Sets the language dependent service name of the priced e-service.
     *
     * @param serviceName the service name
     */
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    /**
     * Gets the two-letter (ISO 639) language code of the language in which the service name of the priced e-service is expressed.
     *
     * @return the language ISO code
     */
    public String getLanguage()
    {
        return language;
    }

    /**
     * Sets the two-letter (ISO 639) language code of the language in which the service name of the priced e-service is expressed.
     *
     * @param language the language ISO code
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }

    /**
     * Gets the net value of the priced e-service.
     *
     * @return the net value
     */
    public BigDecimal getNetValue()
    {
        return netValue;
    }

    /**
     * Sets the net value of the priced e-service.
     *
     * @param netValue the net value
     */
    public void setNetValue(BigDecimal netValue)
    {
        this.netValue = netValue;
    }

    /**
     * Gets the currency ISO code of the currency related to the net value of the priced e-service.
     *
     * @return the currency ISO code
     */
    public String getCurrency()
    {
        return currency;
    }

    /**
     *  Sets the currency ISO code of the currency related to the net value of the priced e-service.
     *
     * @param currency the currency ISO code
     */
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
}
