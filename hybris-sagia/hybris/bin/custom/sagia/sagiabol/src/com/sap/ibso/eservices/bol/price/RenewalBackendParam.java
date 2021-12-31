package com.sap.ibso.eservices.bol.price;

/**
 * Defines license renewal backend parameters for a price simulation.
 */
public class RenewalBackendParam
{
    private int renewalPeriods;

    /**
     * Gets the number of renewal periods.
     *
     * @return the renewal periods
     */
    public int getRenewalPeriods()
    {
        return renewalPeriods;
    }

    /**
     * Sets the number of renewal periods.
     *
     * @param renewalPeriods the renewal periods
     */
    public void setRenewalPeriods(int renewalPeriods)
    {
        this.renewalPeriods = renewalPeriods;
    }
}
