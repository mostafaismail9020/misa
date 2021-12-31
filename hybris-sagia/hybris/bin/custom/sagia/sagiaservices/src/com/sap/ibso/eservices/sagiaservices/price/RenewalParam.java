package com.sap.ibso.eservices.sagiaservices.price;

import java.io.Serializable;

/**
 * Defines license renewal parameters for a price simulation.
 */
public class RenewalParam implements Serializable
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
