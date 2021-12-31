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
package com.sap.ibso.eservices.storefront.response;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.storefront.response
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class UploadFileResponse
{

	private String code;
	private String fileCode;
	private String fileName;
	private Integer status;

	public UploadFileResponse() {

	}

	public UploadFileResponse(final String code)
	{
		this.code = code;
	}

	public UploadFileResponse(final String code, final String fileCode, final String fileName)
	{
		this.code = code;
		this.fileCode = fileCode;
		this.fileName = fileName;
	}

	public UploadFileResponse(final String code, final Integer status)
	{
		this.code = code;
		this.status = status;
	}

	public UploadFileResponse(final String code, final String fileCode, final String fileName, final Integer status)
	{
		this.code = code;
		this.fileCode = fileCode;
		this.fileName = fileName;
		this.status = status;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(final String code)
	{
		this.code = code;
	}

	public String getFileCode()
	{
		return fileCode;
	}

	public void setFileCode(final String fileCode)
	{
		this.fileCode = fileCode;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(final String fileName)
	{
		this.fileName = fileName;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(final Integer status)
	{
		this.status = status;
	}
}
