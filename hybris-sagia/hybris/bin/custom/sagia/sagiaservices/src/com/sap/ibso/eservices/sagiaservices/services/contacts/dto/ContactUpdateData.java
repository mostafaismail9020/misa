/**
 * ***********************************************************************
 * Copyright (c) 2017, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 * Moscow, Russian Federation
 *
 * Web sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.contacts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.contacts.dto
 * @link http//sap.com/
 * @copyright 2018 SAP
 */
public class ContactUpdateData
{
	@JsonProperty("ContactType")
	private String contactType;
	@JsonProperty("Email")
	private String email;
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("MiddleName")
	private String middleName;
	@JsonProperty("MobileNumber")
	private String mobileNumber;
	@JsonProperty("NationalID")
	private String nationalId;
	@JsonProperty("PrimaryContact")
	private String primaryContact;

	/**
	 * @return
	 */
	public String getContactType()
	{
		return contactType;
	}

	/**
	 * @param contactType
	 */
	public void setContactType(final String contactType)
	{
		this.contactType = contactType;
	}

	/**
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(final String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @return
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 */
	public void setMobileNumber(final String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return
	 */
	public String getPrimaryContact()
	{
		return primaryContact;
	}

	/**
	 * @param primaryContact
	 */
	public void setPrimaryContact(final String primaryContact)
	{
		this.primaryContact = primaryContact;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
}
