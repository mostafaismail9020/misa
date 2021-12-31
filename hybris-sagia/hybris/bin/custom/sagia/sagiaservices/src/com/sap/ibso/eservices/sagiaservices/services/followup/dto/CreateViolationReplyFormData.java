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
public class CreateViolationReplyFormData
{
	private List<String> violationsTexts;
	private List<String> violations;
	private String letterNumber;
	private String comments;
	private List<String> docs;
	private Boolean termsAndConditionsChecked;

	/**
	 * @return
	 */
	public List<String> getViolationsTexts()
	{
		return violationsTexts;
	}

	/**
	 * @param violationsTexts
	 */
	public void setViolationsTexts(final List<String> violationsTexts)
	{
		this.violationsTexts = violationsTexts;
	}

	/**
	 * @return
	 */
	public List<String> getViolations()
	{
		return violations;
	}

	/**
	 * @param violations
	 */
	public void setViolations(final List<String> violations)
	{
		this.violations = violations;
	}

	/**
	 * @return
	 */
	public String getLetterNumber()
	{
		return letterNumber;
	}

	public void setLetterNumber(final String letterNumber)
	{
		this.letterNumber = letterNumber;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(final String comments)
	{
		this.comments = comments;
	}

	public List<String> getDocs()
	{
		return docs;
	}

	public void setDocs(final List<String> docs)
	{
		this.docs = docs;
	}

	public Boolean getTermsAndConditionsChecked() {
		return termsAndConditionsChecked;
	}

	public void setTermsAndConditionsChecked(Boolean termsAndConditionsChecked) {
		this.termsAndConditionsChecked = termsAndConditionsChecked;
	}
}
