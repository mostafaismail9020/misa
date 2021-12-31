package com.sap.ibso.eservices.core.sagia.dto;

import java.io.Serializable;

/**
* @author gandrade
*
*/
public class CustomerWsDTO  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7023227259474476377L;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.customerID</code> attribute defined at extension
	 * <code>core</code>.
	 */
	private String customerId;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.title</code> attribute defined at extension
	 * <code>commerceservices</code>.
	 */
	private String title;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.contactEmail</code> attribute defined at extension
	 * <code>commerceservices</code>.
	 */
	private String contactEmail;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.company</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String company;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.mobileCountryCode</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String mobileCountryCode;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.userNameEmail</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String userNameEmail;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.mobileNumber</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String mobileNumber;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.sector</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private SectorWsDTO sector;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.country</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private CountryWsDTO country;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.applicantReferenceID</code> attribute defined at
	 * extension <code>sagiacore</code>.
	 */
	private String applicantReferenceID;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.entityID</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String entityID;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.internetUserID</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String internetUserID;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.applicationServiceRequestID</code> attribute defined at
	 * extension <code>sagiacore</code>.
	 */
	private String applicationServiceRequestID;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.regEmailStatus</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String regEmailStatus;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.mobileStatus</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String mobileStatus;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.qeemahEmailStatus</code> attribute defined at extension
	 * <code>sagiacore</code>.
	 */
	private String qeemahEmailStatus;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.department</code> attribute defined at extension
	 * <code>investsaudisecureportal</code>.
	 */
	private String department;

	/**
	 * <i>Generated constant</i> - Attribute key of <code>Customer.otherUserEntity</code> attribute defined at extension
	 * <code>investsaudisecureportal</code>.
	 */
	private String otherUserEntity;
	
	private String uid;
	
	private String name;
	
	private ImageWsDTO profilePicture;
	
	private MediaWsDTO dashboardMedia;
	
	private String qeemahEmail;
	
	private String isOutstandingFee;
	
	

	public CustomerWsDTO()
	{
		// default constructor
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId()
	{
		return customerId;
	}

	/**
	 * @param customerId
	 *           the customerId to set
	 */
	public void setCustomerId(final String customerId)
	{
		this.customerId = customerId;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title
	 *           the title to set
	 */
	public void setTitle(final String title)
	{
		this.title = title;
	}

	/**
	 * @return the contactEmail
	 */
	public String getContactEmail()
	{
		return contactEmail;
	}

	/**
	 * @param contactEmail
	 *           the contactEmail to set
	 */
	public void setContactEmail(final String contactEmail)
	{
		this.contactEmail = contactEmail;
	}

	/**
	 * @return the company
	 */
	public String getCompany()
	{
		return company;
	}

	/**
	 * @param company
	 *           the company to set
	 */
	public void setCompany(final String company)
	{
		this.company = company;
	}

	/**
	 * @return the mobileCountryCode
	 */
	public String getMobileCountryCode()
	{
		return mobileCountryCode;
	}

	/**
	 * @param mobileCountryCode
	 *           the mobileCountryCode to set
	 */
	public void setMobileCountryCode(final String mobileCountryCode)
	{
		this.mobileCountryCode = mobileCountryCode;
	}

	/**
	 * @return the userNameEmail
	 */
	public String getUserNameEmail()
	{
		return userNameEmail;
	}

	/**
	 * @param userNameEmail
	 *           the userNameEmail to set
	 */
	public void setUserNameEmail(final String userNameEmail)
	{
		this.userNameEmail = userNameEmail;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *           the mobileNumber to set
	 */
	public void setMobileNumber(final String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the applicantReferenceID
	 */
	public String getApplicantReferenceID()
	{
		return applicantReferenceID;
	}

	/**
	 * @param applicantReferenceID
	 *           the applicantReferenceID to set
	 */
	public void setApplicantReferenceID(final String applicantReferenceID)
	{
		this.applicantReferenceID = applicantReferenceID;
	}

	/**
	 * @return the entityID
	 */
	public String getEntityID()
	{
		return entityID;
	}

	/**
	 * @param entityID
	 *           the entityID to set
	 */
	public void setEntityID(final String entityID)
	{
		this.entityID = entityID;
	}

	/**
	 * @return the internetUserID
	 */
	public String getInternetUserID()
	{
		return internetUserID;
	}

	/**
	 * @param internetUserID
	 *           the internetUserID to set
	 */
	public void setInternetUserID(final String internetUserID)
	{
		this.internetUserID = internetUserID;
	}

	/**
	 * @return the applicationServiceRequestID
	 */
	public String getApplicationServiceRequestID()
	{
		return applicationServiceRequestID;
	}

	/**
	 * @param applicationServiceRequestID
	 *           the applicationServiceRequestID to set
	 */
	public void setApplicationServiceRequestID(final String applicationServiceRequestID)
	{
		this.applicationServiceRequestID = applicationServiceRequestID;
	}

	/**
	 * @return the regEmailStatus
	 */
	public String getRegEmailStatus()
	{
		return regEmailStatus;
	}

	/**
	 * @param regEmailStatus
	 *           the regEmailStatus to set
	 */
	public void setRegEmailStatus(final String regEmailStatus)
	{
		this.regEmailStatus = regEmailStatus;
	}

	/**
	 * @return the mobileStatus
	 */
	public String getMobileStatus()
	{
		return mobileStatus;
	}

	/**
	 * @param mobileStatus
	 *           the mobileStatus to set
	 */
	public void setMobileStatus(final String mobileStatus)
	{
		this.mobileStatus = mobileStatus;
	}

	/**
	 * @return the qeemahEmailStatus
	 */
	public String getQeemahEmailStatus()
	{
		return qeemahEmailStatus;
	}

	/**
	 * @param qeemahEmailStatus
	 *           the qeemahEmailStatus to set
	 */
	public void setQeemahEmailStatus(final String qeemahEmailStatus)
	{
		this.qeemahEmailStatus = qeemahEmailStatus;
	}

	/**
	 * @return the department
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * @param department
	 *           the department to set
	 */
	public void setDepartment(final String department)
	{
		this.department = department;
	}

	/**
	 * @return the otherUserEntity
	 */
	public String getOtherUserEntity()
	{
		return otherUserEntity;
	}

	/**
	 * @param otherUserEntity
	 *           the otherUserEntity to set
	 */
	public void setOtherUserEntity(final String otherUserEntity)
	{
		this.otherUserEntity = otherUserEntity;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQeemahEmail() {
		return qeemahEmail;
	}

	public void setQeemahEmail(String qeemahEmail) {
		this.qeemahEmail = qeemahEmail;
	}

	public String getIsOutstandingFee() {
		return isOutstandingFee;
	}

	public void setIsOutstandingFee(String isOutstandingFee) {
		this.isOutstandingFee = isOutstandingFee;
	}

	public SectorWsDTO getSector() {
		return sector;
	}

	public void setSector(SectorWsDTO sector) {
		this.sector = sector;
	}

	public CountryWsDTO getCountry() {
		return country;
	}

	public void setCountry(CountryWsDTO country) {
		this.country = country;
	}

	public ImageWsDTO getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(ImageWsDTO profilePicture) {
		this.profilePicture = profilePicture;
	}

	public MediaWsDTO getDashboardMedia() {
		return dashboardMedia;
	}

	public void setDashboardMedia(MediaWsDTO dashboardMedia) {
		this.dashboardMedia = dashboardMedia;
	}


}
