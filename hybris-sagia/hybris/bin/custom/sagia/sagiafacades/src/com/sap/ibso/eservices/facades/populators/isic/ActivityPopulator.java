package com.sap.ibso.eservices.facades.populators.isic;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.IsicActivity;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.IsicActivityData;
import de.hybris.platform.converters.Populator;

/**
 *
 */
public class ActivityPopulator implements Populator<IsicMasterModel, IsicActivity> {

    @Override
    public void populate(IsicMasterModel source, IsicActivity target) {
        target.setActivityId(source.getIsicActivity());
        target.setBranchId(source.getIsicBranch());
        target.setSplRequirementId(source.getLReqid());
        target.setQeemahChannel(source.getQeemahChannel());
        target.setGroupId(source.getIsicGroup());
        target.setSectionId(source.getIsicSection());
        target.setClassId(source.getIsicClass());
        target.setDivisionId(source.getIsicDivision());
    }
}