package com.sap.ibso.eservices.facades.sagia;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.sagia.IsicData;
import com.sap.ibso.eservices.facades.data.zqeemah2.ISICDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by i335541 on 2/12/18.
 */
public interface SagiaIsicFacade {
    /**
     * retrieves Isic
     * @param licenseType licenseType
     * @return isic Map
     */
    Map<String, List> getIsic(String licenseType);

    Map<String, List> getAllIsic();

    Map<String, List> getIsicDetails(Collection<IsicMasterModel> isicMasterModels);

	List<ISICDetails> getActiveISICSection();


    List<ISICDetails> getActiveISICGroup();

    List<ISICDetails> getActiveISICClass();

    List<ISICDetails> getActiveISICDivision(String sectionID);

    List<IsicData> getActiveISICGroup(String divisionID);

    List<IsicData> getActiveISICClass(String groupID);
}
