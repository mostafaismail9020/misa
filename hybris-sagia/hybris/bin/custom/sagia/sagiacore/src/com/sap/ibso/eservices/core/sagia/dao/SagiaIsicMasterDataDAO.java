package com.sap.ibso.eservices.core.sagia.dao;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.IsicTextsModel;
import com.sap.ibso.eservices.core.sagia.IsicData;

import java.util.Collection;
import java.util.List;

public interface SagiaIsicMasterDataDAO {

    Collection<IsicMasterModel> getAllIsicMasterByLicenseType(String licenseType);

    Collection<IsicMasterModel> getAllIsicMaster(List<String> isicActivityCodes);

    Collection<IsicMasterModel> getAllIsicMasterForAllLicenseType();

    List<IsicTextsModel> getTextsDataFromType(String type, List<String> parentIds);

	List<IsicTextsModel> getActiveISICSection();

	List<IsicTextsModel> getActiveISICDivision(String section);

    IsicTextsModel getIsicTextsByCode(String isicCode, String isicType);

    List<IsicTextsModel> getActiveISICGroup();

    List<IsicTextsModel> getActiveISICClass();

    List<IsicTextsModel> getActiveISICGroupByDivisionID(String divisionID);

    List<IsicTextsModel> getActiveISICClassByGroupID(String groupID);

    List<IsicTextsModel> getActiveISICBranchByClassID(String classID);

    List<IsicTextsModel> getActiveISICActivityByBranchID(String branchID);
}
