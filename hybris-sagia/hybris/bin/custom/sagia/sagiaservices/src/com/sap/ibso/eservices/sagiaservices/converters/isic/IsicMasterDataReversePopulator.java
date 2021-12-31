package com.sap.ibso.eservices.sagiaservices.converters.isic;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.sagiaservices.data.IsicMasterSetData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class IsicMasterDataReversePopulator implements Populator<IsicMasterSetData, IsicMasterModel>{

    public void populate(IsicMasterSetData source, IsicMasterModel target) throws ConversionException {

        target.setIsicActivity(source.getIsicactivity());
        target.setIsicSection(source.getIsicsection());
        target.setIsicDivision(source.getIsicdivision());
        target.setIsicGroup(source.getIsicgroup());
        target.setIsicClass(source.getIsicclass());
        target.setIsicBranch(source.getIsicbranch());
        target.setLicenseType(source.getLicensetype());

        if (source.getActive().isEmpty()) {
            target.setActive(Boolean.FALSE);
        } else {
            target.setActive(Boolean.TRUE);
        }

        target.setQeemahChannel(source.getQeemahchannel());
        target.setLReqid(source.getLreqid());
    }

}
