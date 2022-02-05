package com.sap.ibso.eservices.sagiaservices.services.isic;

import java.util.Collection;
import java.util.List;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.model.IsicTextsModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaIsicMasterDataDAO;
import com.sap.ibso.eservices.sagiaservices.data.IsicMasterSetData;
import com.sap.ibso.eservices.sagiaservices.services.AbstractSagiaService;
import com.sap.ibso.eservices.sagiaservices.utils.QueryOptionsBuilder;

/**
 * IsicMasterDataService
 * @package com.sap.ibso.eservices.sagiaservices.services
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class IsicMasterDataService extends AbstractSagiaService<IsicMasterSetData> {

    private SagiaIsicMasterDataDAO sagiaIsicMasterDataDAO;

    public Collection<IsicMasterModel> getAllIsicMasterByLicenseType(String licenseType){
        return sagiaIsicMasterDataDAO.getAllIsicMasterByLicenseType(licenseType);
    }

    public Collection<IsicMasterModel> getAllIsicMasterForAllLicenseType(){
        return sagiaIsicMasterDataDAO.getAllIsicMasterForAllLicenseType();
    }

    public Collection<IsicMasterModel> getAllIsicMaster(List<String> isicActivityCodes){
        return sagiaIsicMasterDataDAO.getAllIsicMaster(isicActivityCodes);
    }

    public List<IsicTextsModel> getTextsDataFromType(String type, List<String> ids) {
        return sagiaIsicMasterDataDAO.getTextsDataFromType(type, ids);
    }

    public List<IsicTextsModel> getActiveISICSection() {
        return sagiaIsicMasterDataDAO.getActiveISICSection();
    }

    public List<IsicTextsModel> getActiveISICDivision(String sectionID) {
        return sagiaIsicMasterDataDAO.getActiveISICDivision(sectionID);
    }

    //TODO dont need to use all this methods above
/*    public Collection<IsicMasterModel>  getAllSectionsByLicenseType(String licenseType){
        return sagiaIsicMasterDataDAO.getAllSectionsByLicenseType(licenseType);
    }

    public Collection<IsicMasterModel> getAllDivisionsByLicenseType(List<String> parentIds, String licenseType) {
        return sagiaIsicMasterDataDAO.getAllDivisionsByLicenseType(parentIds, licenseType);
    }

    public Collection<IsicMasterModel> getAllGroupsByLicenseType(List<String> parentIds) {
        return sagiaIsicMasterDataDAO.getAllGroupsByLicenseType(parentIds);
    }

    public Collection<IsicMasterModel> getAllClassesByLicenseType(List<String> parentIds) {
        return sagiaIsicMasterDataDAO.getAllClassesByLicenseType(parentIds);
    }

    public Collection<IsicMasterModel> getAllBranchesByLicenseType(List<String> parentIds){
        return sagiaIsicMasterDataDAO.getAllBranchesByLicenseType(parentIds);
    }

    public Collection<IsicMasterModel> getAllActivitiesByLicenseType(List<String> parentIds, String licenseType) {
        return sagiaIsicMasterDataDAO.getAllActivitiesByLicenseType(parentIds, licenseType);
    }*/

    public Collection<IsicMasterSetData> getDeltaCollection(){
        QueryOptionsBuilder query = new QueryOptionsBuilder().filter("Delta eq 'X'");
        return super.getCollection(IsicMasterSetData.class, query.build());
    }

    public Collection<IsicMasterSetData> getCollection(){
        return super.getCollection(IsicMasterSetData.class);
    }

    public SagiaIsicMasterDataDAO getSagiaIsicMasterDataDAO() {
        return sagiaIsicMasterDataDAO;
    }

    public void setSagiaIsicMasterDataDAO(SagiaIsicMasterDataDAO sagiaIsicMasterDataDAO) {
        this.sagiaIsicMasterDataDAO = sagiaIsicMasterDataDAO;
    }


}
