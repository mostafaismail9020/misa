package com.sap.ibso.eservices.core.sagia.services;

import com.sap.ibso.eservices.core.model.NafathLoginModel;
import de.hybris.platform.core.model.user.UserModel;

public interface NafathService {

    NafathLoginModel login(String id);

    boolean removeOldLoginRecords(Integer daysOld);

    NafathLoginModel getTransactionStatus(String transactionID);

    UserModel getUserModelForLicense(String license);

    void updateNafathLoginStatus(String transactionID, String status);
}
