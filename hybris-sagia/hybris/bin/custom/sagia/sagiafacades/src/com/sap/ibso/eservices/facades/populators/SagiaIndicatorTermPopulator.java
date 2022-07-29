package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.jalo.SagiaIndicatorTerm;
import com.sap.ibso.eservices.facades.data.SagiaIndicatorTermData;
import de.hybris.platform.converters.Populator;


public class SagiaIndicatorTermPopulator implements Populator<SagiaIndicatorTerm, SagiaIndicatorTermData> {

    @Override
    public void populate(SagiaIndicatorTerm source, SagiaIndicatorTermData sagiaIndicatorTermData)  {
        sagiaIndicatorTermData.setCalculation(source.getCalculation());
        sagiaIndicatorTermData.setDataPoint(source.getDataPoint());
        sagiaIndicatorTermData.setCode(source.getDataPoint());
        sagiaIndicatorTermData.setDescription(source.getDescription());
        sagiaIndicatorTermData.setIsActive(source.isIsActive());
        sagiaIndicatorTermData.setMeasurement(source.getMeasurement());
        sagiaIndicatorTermData.setSource(source.getSource());
    }

}
