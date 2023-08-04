package com.sap.ibso.eservices.sagiaservices.services.complaints.dto;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CategorizationSchemaGetListData;
import org.springframework.web.multipart.MultipartFile;
import java.util.Collection;

public class ContactUsFormData {
	private String firstName;
	private String contactUsName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String message;
	private String selectedEnquiryType;
	/**
	 * @return the selectedEnquiryType
	 */
	public String getSelectedEnquiryType() {
		return selectedEnquiryType;
	}
	/**
	 * @param selectedEnquiryType the selectedEnquiryType to set
	 */
	public void setSelectedEnquiryType(String selectedEnquiryType) {
		this.selectedEnquiryType = selectedEnquiryType;
	}
	private String selectedCategoryOne;
	/**
	 * @return the selectedCategoryOne
	 */
	public String getSelectedCategoryOne() {
		return selectedCategoryOne;
	}
	/**
	 * @param selectedCategoryOne the selectedCategoryOne to set
	 */
	public void setSelectedCategoryOne(String selectedCategoryOne) {
		this.selectedCategoryOne = selectedCategoryOne;
	}
	private Collection<CategorizationSchemaGetListData> enquiryTypes;
    /**
	 * @return the enquiryTypes
	 */
	public Collection<CategorizationSchemaGetListData> getEnquiryTypes() {
		return enquiryTypes;
	}
	/**
	 * @param enquiryTypes the enquiryTypes to set
	 */
	public void setEnquiryTypes(Collection<CategorizationSchemaGetListData> enquiryTypes) {
		this.enquiryTypes = enquiryTypes;
	}
	private Collection<CategorizationSchemaGetListData> categoryOne;
    /**
	 * @return the categoryOne
	 */
	public Collection<CategorizationSchemaGetListData> getCategoryOne() {
		return categoryOne;
	}
	/**
	 * @param categoryOne the categoryOne to set
	 */
	public void setCategoryOne(Collection<CategorizationSchemaGetListData> categoryOne) {
		this.categoryOne = categoryOne;
	}
	private Collection<CategorizationSchemaGetListData> categoryTwo;
	
	/**
	 * @return the categoryTwo
	 */
	public Collection<CategorizationSchemaGetListData> getCategoryTwo() {
		return categoryTwo;
	}
	/**
	 * @param categoryTwo the categoryTwo to set
	 */
	public void setCategoryTwo(Collection<CategorizationSchemaGetListData> categoryTwo) {
		this.categoryTwo = categoryTwo;
	}
	private MultipartFile contactfile;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getContactUsName() {
		return contactUsName;
	}
	public void setContactUsName(String contactUsName) {
		this.contactUsName = contactUsName;
	}
	public MultipartFile getContactfile() {
		return contactfile;
	}
	
	public void setContactfile(MultipartFile contactfile ) {
		this.contactfile = contactfile;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
