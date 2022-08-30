package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.NafathLoginModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;

import java.util.List;

public interface NafathDAO {

    List<NafathLoginModel> getOldLoginRecords(Integer daysOld);

    NafathLoginModel getLoginFromTransactionId(String transactionID, String nationalId, String randomText);

    SagiaLicenseModel getLicense(String license);
}
