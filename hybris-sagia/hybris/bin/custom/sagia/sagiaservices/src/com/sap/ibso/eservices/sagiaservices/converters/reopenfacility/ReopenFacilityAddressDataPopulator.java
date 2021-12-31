package com.sap.ibso.eservices.sagiaservices.converters.reopenfacility;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddressData;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityHistoryData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class ReopenFacilityAddressDataPopulator extends ODataPopulator<ReopenFacilityAddressData> {

    private ReopenFacilityHistoryDataPopulator reopenFacilityHistoryDataPopulator;

    /**
     * @param model
     * @param reopenFacilityData
     */
    @Override
    public void populate(ODataModel model, ReopenFacilityAddressData reopenFacilityData) {
        super.populate(model, reopenFacilityData);
        Map<String, Object> map = model.get();

        ODataFeed oDataFeed = (ODataFeed) map.get("BPAddrToDepndtSrvReq");
        if (oDataFeed != null && CollectionUtils.isNotEmpty(oDataFeed.getEntries())) {
            Set<ReopenFacilityHistoryData> attachmentDataSet = new HashSet<>();
            for (ODataEntry oDataEntry : oDataFeed.getEntries()) {
                ReopenFacilityHistoryData reopenFacilityHistoryData = new ReopenFacilityHistoryData();
                ODataModel tempModel = new ODataModel(oDataEntry);
                reopenFacilityHistoryDataPopulator.populate(tempModel, reopenFacilityHistoryData);
                attachmentDataSet.add(reopenFacilityHistoryData);
            }
            reopenFacilityData.setBPAddrToDepndtSrvReq(attachmentDataSet);
        }
    }

    /**
     * @param reopenFacilityHistoryDataPopulator
     */
    public void setReopenFacilityHistoryDataPopulator(ReopenFacilityHistoryDataPopulator reopenFacilityHistoryDataPopulator) {
        this.reopenFacilityHistoryDataPopulator = reopenFacilityHistoryDataPopulator;
    }
}
