package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentList;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentListData;
import de.hybris.platform.converters.Populator;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class RealEstateAttachmentSetPopulator implements Populator<RealEstateAttachmentListData, RealEstateAttachmentList> {

    /**
     * Populate from RealEstateAttachmentListData to RealEstate.
     *
     * @param source the source RealEstateAttachmentList
     * @param target the target to fill
     */
    @Override
    public void populate(RealEstateAttachmentListData source, RealEstateAttachmentList target){
        target.setType(source.getType());
        target.setSubtype(source.getSubtype());
        target.setAttachmentName(source.getAttachmentName());
        target.setLanguage(source.getLanguage());
    }

}
