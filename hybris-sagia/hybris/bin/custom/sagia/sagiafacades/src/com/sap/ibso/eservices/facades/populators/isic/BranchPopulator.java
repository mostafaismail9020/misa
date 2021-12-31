package com.sap.ibso.eservices.facades.populators.isic;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicBranch;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicBranchData;
import de.hybris.platform.converters.Populator;

public class BranchPopulator implements Populator<IsicMasterModel, IsicBranch> {
    @Override
    public void populate(IsicMasterModel source, IsicBranch target) {
        target.setBranchId(source.getIsicBranch());
        target.setClassId(source.getIsicClass());
    }
}