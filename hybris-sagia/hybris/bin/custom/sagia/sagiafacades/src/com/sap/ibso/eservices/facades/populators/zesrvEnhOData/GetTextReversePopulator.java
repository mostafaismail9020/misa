package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.GetText;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class GetTextReversePopulator implements Populator<GetText, GetTextData> {
    /**
     * Populate from GetText to GetTextData
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */
    @Override
    public void populate(GetText source, GetTextData target) throws ConversionException {
        target.setSrId(source.getSrId());
        target.setSrGuid(source.getSrGuid());
        target.setTdid(source.getTdid());
        target.setTdline(source.getTdline());
        target.setCommentsBy(source.getCommentsBy());
        target.setCommentTime(source.getCommentTime());
        target.setTimezone(source.getTimezone());
        target.setStage(source.getStage());
    }
}

