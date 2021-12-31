package com.sap.ibso.eservices.bol.license;

/**
 * Enumerates fixed backend service types.
 */
public enum BackendServiceType
{
    /**
     * Indicates a license application process according to Qeemah 1.
     */
    QEEMAH_1("QEE1"),
    /**
     * Indicates a license application process according to Qeemah 2.
     */
    QEEMAH_2("QEE2");

    private final String value;

    /**
     * Create a backend service type enumeration value.
     *
     * @param value the ABAP service type value
     */
    BackendServiceType(String value)
    {
        this.value = value;
    }

    /**
     * Gets the ABAP service type value.
     *
     * @return the ABAP Qeemah value
     */
    @Override
    public String toString()
    {
        return value;
    }
}
