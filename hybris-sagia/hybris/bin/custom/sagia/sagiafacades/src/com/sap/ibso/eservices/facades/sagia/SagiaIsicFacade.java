package com.sap.ibso.eservices.facades.sagia;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.facades.data.zqeemah2.ISICDetails;

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

	Map<String, List> getIsicDetails(Collection<IsicMasterModel> isicMasterModels);

	List<ISICDetails> getActiveISICSection();

	List<ISICDetails> getActiveISICDivision(String sectionID);

}
