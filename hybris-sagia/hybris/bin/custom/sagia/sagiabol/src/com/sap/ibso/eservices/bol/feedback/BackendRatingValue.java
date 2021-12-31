package com.sap.ibso.eservices.bol.feedback;

/**
 * Provides the backend rating values of satisfaction with the user experience of an e-service.
 */
public enum BackendRatingValue
{
    /**
     * Indicates very poor user experience with an e-service.
     */
    ONE_STAR(1),
    /**
     * Indicates poor user experience with an e-service.
     */
    TWO_STARS(2),
    /**
     * Indicates average user experience with an e-service.
     */
    THREE_STARS(3),
    /**
     * Indicates good user experience with an e-service.
     */
    FOUR_STARS(4),
    /**
     * Indicates very good user experience with an e-service.
     */
    FIVE_STARS(5);

    private final int value;

    /**
     * Create a backend rating enumeration value.
     *
     * @param value the ABAP rating value
     */
    BackendRatingValue(int value)
    {
        this.value = value;
    }

    /**
     * Gets the ABAP rating value.
     *
     * @return the ABAP rating value
     */
    public int toInteger()
    {
        return value;
    }
}