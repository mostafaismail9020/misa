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
package com.sap.ibso.eservices.sagiaservices.services.followup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.followup.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class FollowUpCRMCreateData
{
	@JsonProperty("Comments")
	private String  comments;
	@JsonProperty("MinistryType")
	private String  ministryType;
	@JsonProperty("ServiceType")
	private String  serviceType;
	@JsonProperty("NoOfDaysExtension")
	private String noOfDaysExtension;
	@JsonProperty("TransType")
	private String  transType;

	@JsonProperty("FollowupServicesToUploadNav")
	private List<FollowUpFileUploadData> docs;
	@JsonProperty("FollowupServicesToViolationsNav")
	private List<FollowUpViolationData> violations;

	/**
	 * @return
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @param comments
	 */
	public void setComments(final String comments)
	{
		this.comments = comments;
	}

	/**
	 * @return
	 */
	public String getMinistryType()
	{
		return ministryType;
	}

	/**
	 * @param ministryType
	 */
	public void setMinistryType(final String ministryType)
	{
		this.ministryType = ministryType;
	}

	/**
	 * @return
	 */
	public String getServiceType()
	{
		return serviceType;
	}

	/**
	 * @param serviceType
	 */
	public void setServiceType(final String serviceType)
	{
		this.serviceType = serviceType;
	}

	/**
	 * @return
	 */
	public String getNoOfDaysExtension()
	{
		return noOfDaysExtension;
	}

	/**
	 * @param noOfDaysExtension
	 */
	public void setNoOfDaysExtension(final String noOfDaysExtension)
	{
		this.noOfDaysExtension = noOfDaysExtension;
	}

	/**
	 * @return
	 */
	public String getTransType()
	{
		return transType;
	}

	/**
	 * @param transType
	 */
	public void setTransType(final String transType)
	{
		this.transType = transType;
	}

	/**
	 * @return
	 */
	public List<FollowUpFileUploadData> getDocs()
	{
		return docs;
	}

	/**
	 * @param docs
	 */
	public void setDocs(final List<FollowUpFileUploadData> docs)
	{
		this.docs = docs;
	}

	/**
	 * @return
	 */
	public List<FollowUpViolationData> getViolations()
	{
		return violations;
	}

	/**
	 * @param violations
	 */
	public void setViolations(final List<FollowUpViolationData> violations)
	{
		this.violations = violations;
	}
}
