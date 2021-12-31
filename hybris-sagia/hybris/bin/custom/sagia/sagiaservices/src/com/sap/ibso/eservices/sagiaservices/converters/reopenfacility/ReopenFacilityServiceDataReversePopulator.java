package com.sap.ibso.eservices.sagiaservices.converters.reopenfacility;

import com.sap.ibso.eservices.sagiaservices.converters.DocumentAttachmentDataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.converters.ODataReversePopulator;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

/**
 *
 */
public class ReopenFacilityServiceDataReversePopulator extends ODataReversePopulator<ReopenFacilityDetailsData> {

    DocumentAttachmentDataReversePopulator documentAttachmentDataReversePopulator;

    /**
     * @param reopenFacilityData
     * @param model
     */
    @Override
    public void populate(ReopenFacilityDetailsData reopenFacilityData, ODataModel model) {
        model.put("SrID", reopenFacilityData.getSrID());
        model.put("BPID", reopenFacilityData.getBPID());
        model.put("VisitDate", reopenFacilityData.getVisitDate());
        model.put("Text", reopenFacilityData.getText());
        model.put("Tdid", reopenFacilityData.getTdid());
        model.put("DocType", reopenFacilityData.getDocType());
        model.put("VisitType", reopenFacilityData.getVisitType());
        model.put("Source", reopenFacilityData.getSource());
        if(CollectionUtils.isNotEmpty(reopenFacilityData.getDecExecVisitToDocAttach())){
            Collection<DocAttachSetData> branches = reopenFacilityData.getDecExecVisitToDocAttach();
            Set<Map> branchesSet = new HashSet<>();

            for(DocAttachSetData branch : branches){
                ODataModel oDataModel = new ODataModel();
                documentAttachmentDataReversePopulator. populate(branch,oDataModel);
                branchesSet.add(oDataModel.get());
            }
            model.put("DecExecVisitToDocAttach",branchesSet);
        }
    }

    /**
     * @param documentAttachmentDataReversePopulator
     */
    public void setDocumentAttachmentDataReversePopulator(DocumentAttachmentDataReversePopulator documentAttachmentDataReversePopulator) {
        this.documentAttachmentDataReversePopulator = documentAttachmentDataReversePopulator;
    }
}
