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

import java.util.List;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.services.followup.dto
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class CreateWarningLetterExtensionFormData
{
	private String letterNumber;
	private Integer daysExtension;
	private String extensionReason;
	private Boolean termsAgree;
	private List<String> docs;
	private Boolean termsAndConditionsChecked;

	/**
	 * @return
	 */
	public String getLetterNumber()
	{
		return letterNumber;
	}

	/**
	 * @param letterNumber
	 */
	public void setLetterNumber(final String letterNumber)
	{
		this.letterNumber = letterNumber;
	}

	/**
	 * @return
	 */
	public Integer getDaysExtension()
	{
		return daysExtension;
	}

	/**
	 * @param daysExtension
	 */
	public void setDaysExtension(final Integer daysExtension)
	{
		this.daysExtension = daysExtension;
	}

	/**
	 * @return
	 */
	public String getExtensionReason()
	{
		return extensionReason;
	}

	/**
	 * @param extensionReason
	 */
	public void setExtensionReason(final String extensionReason)
	{
		this.extensionReason = extensionReason;
	}

	/**
	 * @return
	 */
	public Boolean getTermsAgree()
	{
		return termsAgree;
	}

	/**
	 * @param termsAgree
	 */
	public void setTermsAgree(final Boolean termsAgree)
	{
		this.termsAgree = termsAgree;
	}

	/**
	 * @return
	 */
	public List<String> getDocs()
	{
		return docs;
	}

	/**
	 * @param docs
	 */
	public void setDocs(final List<String> docs)
	{
		this.docs = docs;
	}

	/**
	 * @return
	 */
	public Boolean getTermsAndConditionsChecked() {
		return termsAndConditionsChecked;
	}

	/**
	 * @param termsAndConditionsChecked
	 */
	public void setTermsAndConditionsChecked(Boolean termsAndConditionsChecked) {
		this.termsAndConditionsChecked = termsAndConditionsChecked;
	}
}
