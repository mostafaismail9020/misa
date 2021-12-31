package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.SagiaCountryData;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaCountryDataPopulator implements Populator<SagiaCountryModel, SagiaCountryData> {


    @Override
    public void populate(SagiaCountryModel source, SagiaCountryData target) throws ConversionException {
        target.setCode(source.getCode());
        target.setName(source.getName());
        target.setPhoneprefix(source.getPhoneprefix());
        target.setBlacklisted(source.getBlacklisted());
        target.setIsShareHolderCheck(source.getIsShareHolderCheck());
    }
}
