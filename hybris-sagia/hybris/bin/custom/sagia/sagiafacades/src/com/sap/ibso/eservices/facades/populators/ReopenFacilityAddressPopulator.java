package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacilityHistory;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddress;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddressData;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityHistoryData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ReopenFacilityAddressPopulator implements Populator<ReopenFacilityAddressData, ReopenFacilityAddress>{
	ReopenFacilityHistoryPopulator reopenFacilityHistoryPopulator;

    @Override
    public void populate(ReopenFacilityAddressData reopenFacilityData, ReopenFacilityAddress reopenFacility) throws ConversionException {
		reopenFacility.setBpId(reopenFacilityData.getBpid());
		reopenFacility.setStreet(reopenFacilityData.getStreet());
		reopenFacility.setHouseNumber(reopenFacilityData.getHouseNo());
		reopenFacility.setCountry(reopenFacilityData.getCountry());
		reopenFacility.setCountryDescription(reopenFacilityData.getCountryDesc());
		reopenFacility.setZipCode(reopenFacilityData.getZipCode());
		reopenFacility.setCity(reopenFacilityData.getCity());
		reopenFacility.setAdditionalNumber(reopenFacilityData.getAdditNo());
		reopenFacility.setBuilding(reopenFacilityData.getBuilding());
		reopenFacility.setPoBox(reopenFacilityData.getPOBox());
		reopenFacility.setCreateRequestEnabled("X".equalsIgnoreCase(reopenFacilityData.getSrReqCrtFlag()));
		reopenFacility.setMessage(reopenFacilityData.getMessage());
		Collection<ReopenFacilityHistory> reopenFacilityHistoryCollection = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(reopenFacilityData.getBPAddrToDepndtSrvReq())){
			for(ReopenFacilityHistoryData item : reopenFacilityData.getBPAddrToDepndtSrvReq()){
				ReopenFacilityHistory reopenFacilityHistory = new ReopenFacilityHistory();
				reopenFacilityHistoryPopulator.populate(item, reopenFacilityHistory);
				reopenFacilityHistoryCollection.add(reopenFacilityHistory);
			}
		}
		reopenFacility.setServiceRequests(reopenFacilityHistoryCollection);
    }

	/**
	 * @param reopenFacilityHistoryPopulator
	 */
	public void setReopenFacilityHistoryPopulator(ReopenFacilityHistoryPopulator reopenFacilityHistoryPopulator) {
		this.reopenFacilityHistoryPopulator = reopenFacilityHistoryPopulator;
	}
}
