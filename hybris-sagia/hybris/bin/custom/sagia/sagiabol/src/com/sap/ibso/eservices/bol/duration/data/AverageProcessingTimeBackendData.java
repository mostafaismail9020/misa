package com.sap.ibso.eservices.bol.duration.data;

import java.io.Serializable;

/**
 * Provides a data transfer object for average e-service processing time information from SAP backend system.
 */
public class AverageProcessingTimeBackendData implements Serializable
{
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    private String description;

    /**
     * Gets the day(s) of average processing time.
     *
     * @return the average processing time day(s)
     */
    public int getDays()
    {
        return days;
    }

    /**
     * Sets the day(s) of average processing time.
     *
     * @param days the average processing time day(s)
     */
    public void setDays(int days)
    {
        this.days = days;
    }

    /**
     * Gets the hour(s) of average processing time.
     *
     * @return the average processing time hour(s)
     */
    public int getHours()
    {
        return hours;
    }

    /**
     * Sets the hour(s) of average processing time.
     *
     * @param hours the average processing time hour(s)
     */
    public void setHours(int hours)
    {
        this.hours = hours;
    }

    /**
     * Gets the minute(s) of average processing time.
     *
     * @return the average processing time minute(s)
     */
    public int getMinutes()
    {
        return minutes;
    }

    /**
     * Sets the minute(s) of average processing time.
     *
     * @param minutes the average processing time minute(s)
     */
    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    /**
     * Gets the second(s) of average processing time.
     *
     * @return the average processing time second(s)
     */
    public int getSeconds()
    {
        return seconds;
    }

    /**
     * Sets the second(s) of average processing time.
     *
     * @param seconds the average processing time second(s)
     */
    public void setSeconds(int seconds)
    {
        this.seconds = seconds;
    }

    /**
     * Gets the average processing time information (in English).
     *
     * @return the average processing time information
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the average processing time information (in English).
     *
     * @param description the average processing time information
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
