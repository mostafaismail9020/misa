package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.facades.data.DocumentAttachment;
import com.sap.ibso.eservices.facades.data.reopenfacility.ReopenFacility;
import com.sap.ibso.eservices.facades.populators.ReopenFacilityAddressPopulator;
import com.sap.ibso.eservices.facades.populators.ReopenFacilityPopulator;
import com.sap.ibso.eservices.facades.populators.ReopenFacilityReversePopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaReopenFacilityFacade;
import com.sap.ibso.eservices.sagiaservices.data.DocAttachSetData;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddress;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityAddressData;
import com.sap.ibso.eservices.sagiaservices.data.reopenfacility.ReopenFacilityDetailsData;
import com.sap.ibso.eservices.sagiaservices.services.reopenfacility.ReopenFacilityHistoryService;
import com.sap.ibso.eservices.sagiaservices.services.reopenfacility.ReopenFacilityService;
import de.hybris.platform.catalog.model.CatalogUnawareMediaModel;
import de.hybris.platform.servicelayer.media.MediaService;
import org.apache.commons.lang.StringUtils;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.ws.security.util.Base64;

import java.util.ArrayList;
import java.util.Collection;

/**
 * DefaultSagiaReopenFacilityFacade
 */
public class DefaultSagiaReopenFacilityFacade implements SagiaReopenFacilityFacade {
    private ReopenFacilityService reopenFacilityService;
    private ReopenFacilityHistoryService reopenFacilityHistoryService;
    private ReopenFacilityPopulator reopenFacilityPopulator;
    private ReopenFacilityReversePopulator reopenFacilityReversePopulator;
    private MediaService mediaService;
    private DefaultSagiaDocumentAttachmentFacade documentAttachmentFacade;
    private ReopenFacilityAddressPopulator reopenFacilityAddressPopulator;

    /**
     * Loads facility re-open service request for a given srId
     *
     * @param srId srId
     * @return ReopenFacility
     */
    @Override
    public ReopenFacility loadDecisionExecutionVisit(String srId) {
        ReopenFacilityDetailsData reopenFacilityData = reopenFacilityService.get(srId);
        ReopenFacility reopenFacility = new ReopenFacility();
        reopenFacilityPopulator.populate(reopenFacilityData, reopenFacility);
        Collection<DocumentAttachment> documentAttachments = documentAttachmentFacade.getAttchedDocumentList(srId);
        reopenFacility.setAttachments(documentAttachments);
        return reopenFacility;
    }

    /**
     * Loads facility re-open service request collection
     *
     * @return
     */
    @Override
    public Collection<ReopenFacility> loadDecisionExecutionVisitCollection() {
        Collection<ReopenFacilityDetailsData> reopenFacilityDataCollection = reopenFacilityService.getCollection();
        Collection<ReopenFacility> reopenFacilityCollection = new ArrayList<>();
        for (ReopenFacilityDetailsData reopenFacilityData : reopenFacilityDataCollection) {
            ReopenFacility reopenFacility = new ReopenFacility();
            reopenFacilityPopulator.populate(reopenFacilityData, reopenFacility);
            reopenFacilityCollection.add(reopenFacility);
        }
        return reopenFacilityCollection;
    }

    /**
     * loads ReopenHistory
     * @return ReopenFacilityAddress
     */
    public ReopenFacilityAddress loadReopenHistory() {
        ReopenFacilityAddressData reopenFacilityDataCollection = reopenFacilityHistoryService.get();
        ReopenFacilityAddress reopenFacilityAddress = new ReopenFacilityAddress();
        reopenFacilityAddressPopulator.populate(reopenFacilityDataCollection, reopenFacilityAddress);
        return reopenFacilityAddress;
    }

    @Override
    public String saveDecisionExecutionVisit(ReopenFacility reopenFacility) throws ODataException {
        ReopenFacilityDetailsData reopenFacilityData = new ReopenFacilityDetailsData();
        reopenFacilityReversePopulator.populate(reopenFacility, reopenFacilityData);
        Collection<DocAttachSetData> attachmentCollection = new ArrayList<>();
        for (String fileCode : reopenFacility.getAttachmentIds()) {
            if (!StringUtils.isEmpty(fileCode)) {
                final CatalogUnawareMediaModel file = (CatalogUnawareMediaModel) mediaService.getMedia(fileCode);
                DocAttachSetData document = new DocAttachSetData();
                document.setFilename(file.getRealFileName());
                document.setMimetype(file.getMime());
                document.setFileContent(Base64.encode(mediaService.getDataFromMedia(file)));
                attachmentCollection.add(document);
            }
        }
        reopenFacilityData.setDecExecVisitToDocAttach(attachmentCollection);
        reopenFacilityService.saveEntity(reopenFacilityData);

        return null;
    }

    /**
     * @param reopenFacilityService
     */
    public void setReopenFacilityService(ReopenFacilityService reopenFacilityService) {
        this.reopenFacilityService = reopenFacilityService;
    }

    /**
     * @param reopenFacilityHistoryService
     */
    public void setReopenFacilityHistoryService(ReopenFacilityHistoryService reopenFacilityHistoryService) {
        this.reopenFacilityHistoryService = reopenFacilityHistoryService;
    }

    /**
     * @param reopenFacilityPopulator
     */
    public void setReopenFacilityPopulator(ReopenFacilityPopulator reopenFacilityPopulator) {
        this.reopenFacilityPopulator = reopenFacilityPopulator;
    }

    /**
     * @param reopenFacilityReversePopulator
     */
    public void setReopenFacilityReversePopulator(ReopenFacilityReversePopulator reopenFacilityReversePopulator) {
        this.reopenFacilityReversePopulator = reopenFacilityReversePopulator;
    }

    /**
     * @param mediaService
     */
    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    /**
     * @param documentAttachmentFacade
     */
    public void setDocumentAttachmentFacade(DefaultSagiaDocumentAttachmentFacade documentAttachmentFacade) {
        this.documentAttachmentFacade = documentAttachmentFacade;
    }

    /**
     * @param reopenFacilityAddressPopulator
     */
    public void setReopenFacilityAddressPopulator(ReopenFacilityAddressPopulator reopenFacilityAddressPopulator) {
        this.reopenFacilityAddressPopulator = reopenFacilityAddressPopulator;
    }
}
