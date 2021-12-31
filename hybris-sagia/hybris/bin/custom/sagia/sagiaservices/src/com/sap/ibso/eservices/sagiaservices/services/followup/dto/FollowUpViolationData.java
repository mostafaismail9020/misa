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

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.followup.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class FollowUpViolationData
{

	@JsonProperty("ViolationReply")
	private String violationReply;
	@JsonProperty("BpId")
	private String bpId;
	@JsonProperty("ViolationKey")
	private String violationKey;
	@JsonProperty("ViolationText")
	private String violationText;
	@JsonProperty("WarningEndDate")
	private String warningEndDate;
	@JsonProperty("ViolationNote")
	private String violationNote;
	@JsonProperty("WarningLetterNo")
	private String warningLetterNo;
	@JsonProperty("ServiceType")
	private String serviceType;

	/**
	 * @return
	 */
	public String getViolationReply()
	{
		return violationReply;
	}

	/**
	 * @param violationReply
	 */
	public void setViolationReply(final String violationReply)
	{
		this.violationReply = violationReply;
	}

	/**
	 * @return
	 */
	public String getBpId()
	{
		return bpId;
	}

	/**
	 * @param bpId
	 */
	public void setBpId(final String bpId)
	{
		this.bpId = bpId;
	}

	/**
	 * @return
	 */
	public String getViolationKey()
	{
		return violationKey;
	}

	/**
	 * @param violationKey
	 */
	public void setViolationKey(final String violationKey)
	{
		this.violationKey = violationKey;
	}

	/**
	 * @return
	 */
	public String getViolationText()
	{
		return violationText;
	}

	/**
	 * @param violationText
	 */
	public void setViolationText(final String violationText)
	{
		this.violationText = violationText;
	}

	/**
	 * @return
	 */
	public String getWarningEndDate()
	{
		return warningEndDate;
	}

	/**
	 * @param warningEndDate
	 */
	public void setWarningEndDate(final String warningEndDate)
	{
		this.warningEndDate = warningEndDate;
	}

	/**
	 * @return
	 */
	public String getViolationNote()
	{
		return violationNote;
	}

	/**
	 * @param violationNote
	 */
	public void setViolationNote(final String violationNote)
	{
		this.violationNote = violationNote;
	}

	/**
	 * @return
	 */
	public String getWarningLetterNo()
	{
		return warningLetterNo;
	}

	/**
	 * @param warningLetterNo
	 */
	public void setWarningLetterNo(final String warningLetterNo)
	{
		this.warningLetterNo = warningLetterNo;
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
}
