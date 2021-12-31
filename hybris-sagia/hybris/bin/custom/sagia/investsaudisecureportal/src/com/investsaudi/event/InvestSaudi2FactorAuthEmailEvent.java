package com.investsaudi.event;

import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;

public class InvestSaudi2FactorAuthEmailEvent extends AbstractCommerceUserEvent<BaseSiteModel> {

	private String otp;

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public InvestSaudi2FactorAuthEmailEvent() {
		super();
	}

	public InvestSaudi2FactorAuthEmailEvent(String otp) {
		super();
		this.otp = otp;
	}
	
	
}
