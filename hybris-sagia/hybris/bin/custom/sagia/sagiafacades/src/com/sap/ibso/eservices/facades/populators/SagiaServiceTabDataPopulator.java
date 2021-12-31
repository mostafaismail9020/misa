package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaServiceTabModel;
import com.sap.ibso.eservices.facades.data.SagiaServiceTabData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaServiceTabDataPopulator implements Populator<SagiaServiceTabModel,SagiaServiceTabData> {
    @Override
    public void populate(SagiaServiceTabModel source, SagiaServiceTabData target) throws ConversionException {
        if(source.getContent()!=null){
            target.setContent(source.getLargeContent());

        }
        if(source.getTitle()!=null){
            target.setTitle(source.getTitle());
        }
    }
}
