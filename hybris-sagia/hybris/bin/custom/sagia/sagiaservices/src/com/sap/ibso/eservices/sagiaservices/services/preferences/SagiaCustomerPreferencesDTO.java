package com.sap.ibso.eservices.sagiaservices.services.preferences;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class SagiaCustomerPreferencesDTO {

	@JsonProperty("setNowCompanyPhoto")
	private boolean setNowCompanyPhoto;

	/**
	 * @return
	 */
	public boolean shouldDisplayOptionToSetNowCompanyPhoto() {
		return setNowCompanyPhoto;
	}

	/**
	 * @param setNowCompanyPhoto
	 */
	public void displayOptionToSetNowCompanyPhoto(boolean setNowCompanyPhoto) {
		this.setNowCompanyPhoto = setNowCompanyPhoto;
	}

}
