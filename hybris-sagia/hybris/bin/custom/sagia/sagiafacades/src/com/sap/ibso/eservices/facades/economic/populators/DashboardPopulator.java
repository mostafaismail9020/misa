package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.DashboardModel;
import com.sap.ibso.eservices.facades.data.DashboardData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DashboardPopulator implements Populator<DashboardModel, DashboardData> {
    @Override
    public void populate(DashboardModel source, DashboardData target) throws ConversionException {
        target.setUid(source.getUid());
        target.setValue(source.getValue());
        target.setValueLabel(source.getValueLabel());
        target.setPercentage(source.getPercentage());
        target.setPercentageLabel(source.getPercentageLabel());
        target.setIndex(source.getIndex());
        target.setDisplayName(source.getDisplayName());
        target.setCalenderValue(source.getCalenderValue());
        target.setNotes(source.getNotes());
//        target.setCurrenyValue(source.getCurrenyValue());
    }
}
