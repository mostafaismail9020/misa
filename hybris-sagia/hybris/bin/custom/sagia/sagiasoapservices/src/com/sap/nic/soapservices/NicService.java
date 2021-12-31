package com.sap.nic.soapservices;

import com.sap.nic.NICUserData;

public interface NicService {
	public NICUserData getUserDetailsFromNIC(int idType, String idNumber, String dob);
}
