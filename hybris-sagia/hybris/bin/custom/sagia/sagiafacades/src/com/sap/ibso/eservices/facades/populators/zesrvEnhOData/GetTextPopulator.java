package com.sap.ibso.eservices.facades.populators.zesrvEnhOData;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zesrvEnhOData.GetText;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.time.LocalDateTime;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class GetTextPopulator implements Populator<GetTextData, GetText> {

    private SagiaFormatProvider sagiaFormatProvider;

    /**
     * Populate from GetTextData to GetText
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */

    @Override
    public void populate(GetTextData source, GetText target) throws ConversionException {
        target.setSrId(source.getSrId());
        target.setSrGuid(source.getSrGuid());
        target.setTdid(source.getTdid());
        target.setTdline(source.getTdline());
        target.setCommentsBy(source.getCommentsBy());
        target.setCommentTime(source.getCommentTime());
        target.setTimezone(source.getTimezone());
        target.setStage(source.getStage());

        LocalDateTime commentDate = source.getCommentDate();
        if (commentDate != null) {
            target.setCommentDate(sagiaFormatProvider.getLocalizedDateData(commentDate));
        }
    }

    /**
     * @param sagiaFormatProvider
     */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}



