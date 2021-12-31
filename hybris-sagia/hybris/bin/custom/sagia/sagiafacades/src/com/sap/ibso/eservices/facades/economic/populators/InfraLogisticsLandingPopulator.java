
package com.sap.ibso.eservices.facades.economic.populators;

import com.sap.ibso.eservices.core.model.InfraLogisticsLandingModel;
import com.sap.ibso.eservices.facades.data.InfraLogisticsLandingData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class InfraLogisticsLandingPopulator
		implements Populator<InfraLogisticsLandingModel, InfraLogisticsLandingData> {

	@Override
	public void populate(final InfraLogisticsLandingModel source, final InfraLogisticsLandingData target)
			throws ConversionException {
		target.setInternationalAirportslabel(source.getInternationalAirportslabel());
		target.setInternationalAirportsValue(source.getInternationalAirportsValue());
		target.setDomesticAirportsLabel(source.getDomesticAirportsLabel());
		target.setDomesticAirportsValue(source.getDomesticAirportsValue());
		target.setContainersLabel(source.getContainersLabel());
		target.setContainersValue(source.getContainersValue());
		target.setPortsCapacityLabel(source.getPortsCapacityLabel());
		target.setPortsCapacityValue(source.getPortsCapacityValue());
		target.setPortsCapacityAmount(source.getPortsCapacityAmount());
		target.setAirportsLabel(source.getAirportsLabel());
		target.setAirportsValue(source.getAirportsValue());
		target.setPortsLabel(source.getPortsLabel());
		target.setPortsValue(source.getPortsValue());
		target.setDisplayAirportslabel(source.getDisplayAirportslabel());

	}

}
