package web.src.com.sap.ibso.eservices.storefront.forms;

import org.springframework.web.multipart.MultipartFile;

public class ContactUsForm {
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private String message;
	private String selectCtrl;
	private String selectCtrl_2;
	
	private MultipartFile contactfile;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public String getSelectCtrl() {
		return selectCtrl;
	}
	public void setSelectCtrl(String selectCtrl) {
		this.selectCtrl = selectCtrl;
	}
	public String getSelectCtrl_2() {
		return selectCtrl_2;
	}
	public void setSelectCtrl_2(String selectCtrl_2) {
		this.selectCtrl_2 = selectCtrl_2;
	}
	

}
