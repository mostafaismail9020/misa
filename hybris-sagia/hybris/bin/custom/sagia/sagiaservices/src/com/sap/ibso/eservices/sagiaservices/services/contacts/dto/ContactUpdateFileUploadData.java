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
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.services.contacts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.contacts.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class ContactUpdateFileUploadData
{
	@JsonProperty("FileContString")
	private String fileContString;
	@JsonProperty("Filename")
	private String filename;
	@JsonProperty("MimeType")
	private String mimeType;

	/**
	 * @return
	 */
	public String getFileContString()
	{
		return fileContString;
	}

	/**
	 * @param fileContString
	 */
	public void setFileContString(final String fileContString)
	{
		this.fileContString = fileContString;
	}

	/**
	 * @return
	 */
	public String getFilename()
	{
		return filename;
	}

	/**
	 * @param filename
	 */
	public void setFilename(final String filename)
	{
		this.filename = filename;
	}

	/**
	 * @return
	 */
	public String getMimeType()
	{
		return mimeType;
	}

	/**
	 * @param mimeType
	 */
	public void setMimeType(final String mimeType)
	{
		this.mimeType = mimeType;
	}
}
