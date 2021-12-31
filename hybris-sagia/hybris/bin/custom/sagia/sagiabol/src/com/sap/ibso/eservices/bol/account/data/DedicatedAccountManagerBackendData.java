package com.sap.ibso.eservices.bol.account.data;

import java.io.Serializable;

/**
 * Provides a data transfer object for dedicated account manager information from SAP backend system.
 */
public class DedicatedAccountManagerBackendData implements Serializable
{
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String mobileNumber;

    /**
     * Gets the title of the dedicated account manager.
     *
     * @return the title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title of the dedicated account manager.
     *
     * @param title the title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Gets the first name of the dedicated account manager.
     *
     * @return the first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Sets the first name of the dedicated account manager.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the dedicated account manager.
     *
     * @return the last name
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Sets the last name of the dedicated account manager.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the dedicated account manager.
     *
     * @return the email address
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the email address of the dedicated account manager.
     *
     * @param email the email address
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Gets the phone number of the dedicated account manager.
     *
     * @return the phone number
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the dedicated account manager.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the mobile number of the dedicated account manager.
     *
     * @return the mobile number
     */
    public String getMobileNumber()
    {
        return mobileNumber;
    }

    /**
     * Sets the mobile number of the dedicated account manager.
     *
     * @param mobileNumber the mobile number
     */
    public void setMobileNumber(String mobileNumber)
    {
        this.mobileNumber = mobileNumber;
    }
}
