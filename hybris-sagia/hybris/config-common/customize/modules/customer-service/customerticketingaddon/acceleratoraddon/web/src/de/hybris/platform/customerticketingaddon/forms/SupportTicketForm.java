/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.customerticketingaddon.forms;

import de.hybris.platform.customerticketingfacades.data.TicketCategory;

import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;


public class SupportTicketForm
{
	private String id;


	@NotNull(message = "{text.account.supporttickets.invalid}")
	@NotEmpty(message = "{text.account.supporttickets.invalid}")
	@Size(max = 255, message = "{supportticket.subject.invalid.length}")
	private String subject;
	private String question2;
	private String question3;
	private String question4;
	private String question5;
	private String question6;
	private String question7;
	private String question8;
	private String question20;
	
	

	private String question14;
	private String question15;
	private String message;
	private String status;
	private String associatedTo;
	private List<MultipartFile> files;
	private TicketCategory ticketCategory;

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *           the id to set
	 */
	public void setId(final String id)
	{
		this.id = id;
	}

	/**
	 * @return the subject
	 */

	public String getSubject()
	{
		return subject;
	}

	/**
	 * @param subject
	 *           the subject to set
	 */
	public void setSubject(final String subject)
	{
		this.subject = subject.trim();
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message
	 *           the message to set
	 */
	public void setMessage(final String message)
	{
		this.message = message.trim();
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 *           the status to set
	 */
	public void setStatus(final String status)
	{
		this.status = status;
	}

	/**
	 * @return the associtedObject
	 */
	public String getAssociatedTo()
	{
		return associatedTo;
	}

	/**
	 * @param associtedTo
	 *           the associtedObject to set
	 */
	public void setAssociatedTo(final String associtedTo)
	{
		this.associatedTo = associtedTo;
	}

	/**
	 * @return the ticketCategory
	 */
	public TicketCategory getTicketCategory()
	{
		return ticketCategory;
	}

	/**
	 * @param ticketCategory
	 *           the ticketCategory to set
	 */
	public void setTicketCategory(final TicketCategory ticketCategory)
	{
		this.ticketCategory = ticketCategory;
	}

	public List<MultipartFile> getFiles()
	{
		return files != null ? files : Collections.emptyList();
	}

	public void setFiles(final List<MultipartFile> files)
	{
		this.files = files;
	}

	public String getQuestion2()
	{
		return question2;
	}

	public void setQuestion2(final String question2)
	{
		this.question2 = question2;
	}
	
	public String getQuestion20()
	{
		return question20;
	}

	public void setQuestion20(final String question20)
	{
		this.question20 = question20;
	}

	public String getQuestion3()
	{
		return question3;
	}

	public void setQuestion3(final String question3)
	{
		this.question3 = question3;
	}

	public String getQuestion4()
	{
		return question4;
	}

	public void setQuestion4(final String question4)
	{
		this.question4 = question4;
	}

	public String getQuestion5()
	{
		return question5;
	}

	public void setQuestion5(final String question5)
	{
		this.question5 = question5;
	}

	public String getQuestion6()
	{
		return question6;
	}

	public void setQuestion6(final String question6)
	{
		this.question6 = question6;
	}

	public String getQuestion7()
	{
		return question7;
	}

	public void setQuestion7(final String question7)
	{
		this.question7 = question7;
	}

	public String getQuestion8()
	{
		return question8;
	}

	public void setQuestion8(final String question8)
	{
		this.question8 = question8;
	}

	
	
	/**
	 * @return the question14
	 */
	public String getQuestion14() {
		return question14;
	}

	/**
	 * @param question14 the question14 to set
	 */
	public void setQuestion14(String question14) {
		this.question14 = question14;
	}

	/**
	 * @return the question15
	 */
	public String getQuestion15() {
		return question15;
	}

	/**
	 * @param question15 the question15 to set
	 */
	public void setQuestion15(String question15) {
		this.question15 = question15;
	}




}