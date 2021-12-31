package de.hybris.platform.customerticketingaddon.forms;


import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;



public class BDSupportTicketForm extends SupportTicketForm {
	
	private String question9;
	private String question10;
	
	private String question11;
	private String question12;
	
	
	private String question13;
	private String question16;
	
	
	
	/**
	 * @return the question11
	 */
	public String getQuestion11() {
		return question11;
	}

	/**
	 * @param question11 the question11 to set
	 */
	public void setQuestion11(String question11) {
		this.question11 = question11;
	}

	/**
	 * @return the question12
	 */
	public String getQuestion12() {
		return question12;
	}

	/**
	 * @param question12 the question12 to set
	 */
	public void setQuestion12(String question12) {
		this.question12 = question12;
	}

	/**
	 * @return the question13
	 */
	public String getQuestion13() {
		return question13;
	}

	/**
	 * @param question13 the question13 to set
	 */
	public void setQuestion13(String question13) {
		this.question13 = question13;
	}

	
	/**
	 * @return the question16
	 */
	public String getQuestion16() {
		return question16;
	}

	/**
	 * @param question16 the question16 to set
	 */
	public void setQuestion16(String question16) {
		this.question16 = question16;
	}
	
	public String getQuestion9()
	{
		return question9;
	}

	public void setQuestion9(final String question9)
	{
		this.question9 = question9;
	}

	public String getQuestion10()
	{
		return question10;
	}

	public void setQuestion10(final String question10)
	{
		this.question10 = question10;
	}


}
