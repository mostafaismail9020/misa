package com.sap.ibso.eservices.facades.populators.isic;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicDivision;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicDivisionData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class DivisionPopulator implements Populator<IsicMasterModel, IsicDivision> {

    @Override
    public void populate(IsicMasterModel source, IsicDivision target) {
        target.setDivisionId(source.getIsicDivision());
        target.setSectionId(source.getIsicSection());
    }
}