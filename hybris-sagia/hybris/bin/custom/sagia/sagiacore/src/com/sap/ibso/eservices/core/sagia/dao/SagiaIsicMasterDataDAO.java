package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.IsicTextsModel;

import java.util.Collection;
import java.util.List;

public interface SagiaIsicMasterDataDAO {

    Collection<IsicMasterModel> getAllIsicMasterByLicenseType(String licenseType);

    Collection<IsicMasterModel> getAllIsicMaster(List<String> isicActivityCodes);

    Collection<IsicMasterModel> getAllIsicMasterForAllLicenseType();

    List<IsicTextsModel> getTextsDataFromType(String type, List<String> parentIds);

	List<IsicTextsModel> getActiveISICSection();

	List<IsicTextsModel> getActiveISICDivision(String section);

}
