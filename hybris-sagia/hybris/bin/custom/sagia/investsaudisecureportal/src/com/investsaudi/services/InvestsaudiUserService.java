package com.investsaudi.services;

import de.hybris.platform.servicelayer.user.UserService;

public interface InvestsaudiUserService extends UserService {

	
	public boolean  isMobileNumberUsed(String mobileNumber,String countyCode);
	
	
}
