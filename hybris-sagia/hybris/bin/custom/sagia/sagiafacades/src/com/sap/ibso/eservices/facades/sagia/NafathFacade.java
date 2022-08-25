package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.core.enums.NafathStatus;
import com.sap.ibso.eservices.facades.data.NafathLoginData;
import de.hybris.platform.cmsfacades.data.UserData;

public interface NafathFacade {

    NafathLoginData login(String nationalID);

    NafathLoginData checkStatus(String transactionID);

    UserData getuserForLicense(String license);
}
