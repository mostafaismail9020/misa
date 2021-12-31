package com.sap.ibso.eservices.facades.populators.isic;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicSection;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicSectionData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class SectionPopulator implements Populator<IsicMasterModel, IsicSection> {

    @Override
    public void populate(IsicMasterModel source, IsicSection target) {
        target.setSectionId(source.getIsicSection());
    }
}