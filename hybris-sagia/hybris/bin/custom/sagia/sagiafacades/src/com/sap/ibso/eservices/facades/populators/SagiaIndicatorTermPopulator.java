package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.SagiaIndicatorTermModel;
import com.sap.ibso.eservices.facades.data.SagiaIndicatorTermData;
import de.hybris.platform.converters.Populator;


public class SagiaIndicatorTermPopulator implements Populator<SagiaIndicatorTermModel, SagiaIndicatorTermData> {

    @Override
    public void populate(SagiaIndicatorTermModel source, SagiaIndicatorTermData sagiaIndicatorTermData)  {

        sagiaIndicatorTermData.setCode(source.getCode());
        sagiaIndicatorTermData.setDescription(source.getDescription());
        sagiaIndicatorTermData.setIsActive(source.isIsActive());
        sagiaIndicatorTermData.setSource(source.getSource());
        sagiaIndicatorTermData.setName(source.getName());
        sagiaIndicatorTermData.setLinkText(source.getLinkText());
        sagiaIndicatorTermData.setLinkURL(source.getLinkURL());
    }

}
