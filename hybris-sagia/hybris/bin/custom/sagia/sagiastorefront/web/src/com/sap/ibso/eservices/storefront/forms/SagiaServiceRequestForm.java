package com.sap.ibso.eservices.storefront.forms;

import com.sap.ibso.eservices.core.enums.IncidentCategory;
import com.sap.ibso.eservices.core.enums.ServiceCategory;

import com.sap.ibso.eservices.core.enums.Priority;

public class SagiaServiceRequestForm {
	
	private String subject;
    private String description;
    
    private IncidentCategory incidentCategory;
    private ServiceCategory serviceCategory;
    private Priority priority;
    
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the incidentCategory
	 */
	public IncidentCategory getIncidentCategory() {
		return incidentCategory;
	}
	/**
	 * @param incidentCategory the incidentCategory to set
	 */
	public void setIncidentCategory(IncidentCategory incidentCategory) {
		this.incidentCategory = incidentCategory;
	}
	/**
	 * @return the serviceCategory
	 */
	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}
	/**
	 * @param serviceCategory the serviceCategory to set
	 */
	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	

}
