/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.basiccompanydata;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.basiccompanydata
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class BasicCompanyFormData
{
	private String companyName;
	private String companyNameArabic;
	private String capital;
	private String legalStatus;
	private String region;
	private String city;

	/**
	 * @return
	 */
	public String getCompanyName()
	{
		return companyName;
	}

	/**
	 * @param companyName
	 */
	public void setCompanyName(final String companyName)
	{
		this.companyName = companyName;
	}

	/**
	 * @return
	 */
	public String getCompanyNameArabic()
	{
		return companyNameArabic;
	}

	/**
	 * @param companyNameArabic
	 */
	public void setCompanyNameArabic(final String companyNameArabic)
	{
		this.companyNameArabic = companyNameArabic;
	}

	/**
	 * @return
	 */
	public String getCapital()
	{
		return capital;
	}

	/**
	 * @param capital
	 */
	public void setCapital(final String capital)
	{
		this.capital = capital;
	}

	/**
	 * @return
	 */
	public String getLegalStatus()
	{
		return legalStatus;
	}

	/**
	 * @param legalStatus
	 */
	public void setLegalStatus(final String legalStatus)
	{
		this.legalStatus = legalStatus;
	}

	/**
	 * @return
	 */
	public String getRegion()
	{
		return region;
	}

	/**
	 * @param region
	 */
	public void setRegion(final String region)
	{
		this.region = region;
	}

	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(final String city)
	{
		this.city = city;
	}
}
