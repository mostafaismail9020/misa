package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.InvsIdPost;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.InvsIdPostData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class InvsIdPostPopulator implements Populator<InvsIdPostData, InvsIdPost> {
    @Override
    public void populate(InvsIdPostData invsIdPostData, InvsIdPost invsIdPost) throws ConversionException {
        invsIdPost.setInvestorid(invsIdPostData.getInvestorid());
    }
}
