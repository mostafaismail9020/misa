package com.sap.ibso.eservices.sagiaservices.converters.reopenfacility;

import com.sap.ibso.eservices.sagiaservices.converters.DocumentAttachmentDataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 *
 */
public class ReopenFacilityServiceDataPopulator extends ODataPopulator<ReopenFacilityDetailsData> {

    private DocumentAttachmentDataPopulator documentAttachmentDataPopulator;

    /**
     * @param model
     * @param reopenFacilityData
     */
    @Override
    public void populate(ODataModel model, ReopenFacilityDetailsData reopenFacilityData) {
        super.populate(model, reopenFacilityData);
        Map<String, Object> map = model.get();
        ODataFeed attachments = (ODataFeed) map.get("contentHdr");
        if (attachments != null && CollectionUtils.isNotEmpty(attachments.getEntries())) {
            Collection<DocAttachSetData> attachmentDataSet = new ArrayList<>();
            for (ODataEntry oDataEntry : attachments.getEntries()) {
                DocAttachSetData serviceAttachmentData = new DocAttachSetData();
                ODataModel tempModel = new ODataModel(oDataEntry);
                documentAttachmentDataPopulator.populate(tempModel, serviceAttachmentData);
                attachmentDataSet.add(serviceAttachmentData);
            }
            reopenFacilityData.setDecExecVisitToDocAttach(attachmentDataSet);
        }
    }

    /**
     * @param documentAttachmentDataPopulator
     */
    public void setDocumentAttachmentDataPopulator(DocumentAttachmentDataPopulator documentAttachmentDataPopulator) {
        this.documentAttachmentDataPopulator = documentAttachmentDataPopulator;
    }
}
