package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.facades.data.RealEstateData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.RealEstateAttachmentData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class RealEstateDataPopulator extends ODataPopulator<RealEstateData> {
    public static final String OBJECT_ID = "ObjectID";
    public static final String OBJECT_GUID = "ObjectGUID";
    public static final String ATTACHMENTS_SET = "AttachmentsSet";
    public static final String REMARKS = "Remarks";
    public static final String BPID = "BPID";
    public static final String STATUS = "Status";
    public static final String PLOT_NO = "PlotNo";
    public static final String RE_TYPE = "REType";
    public static final String PURCHASE_DATE = "PurchaseDate";
    public static final String POSTING_DATE = "PostingDate";
    public static final String IDENTITY_NUMBER = "IdNo";
    public static final String DEED_DATE = "DeedDate";
    public static final String ISSUER_COURT_NAME = "IssuerCourtName";

    @Override
    public void populate(ODataModel model, RealEstateData realEstateData) throws ConversionException {
        super.populate(model, realEstateData);
        if (model.get(OBJECT_ID) != null) {
            realEstateData.setObjectId(model.get(OBJECT_ID).toString());
        }
        if (model.get(OBJECT_GUID) != null) {
            realEstateData.setObjectGuid(model.get(OBJECT_GUID).toString());
        }
        if (model.get(REMARKS) != null) {
            realEstateData.setRemarks(model.get(REMARKS).toString());
        }
        if (model.get(BPID) != null) {
            realEstateData.setBpID(model.get(BPID).toString());
        }
        if (model.get(STATUS) != null) {
            realEstateData.setStatus(model.get(STATUS).toString());
        }
        if (model.get(PLOT_NO) != null) {
            realEstateData.setPlotNo(model.get(PLOT_NO).toString());
        }
        if (model.get(RE_TYPE) != null) {
            realEstateData.setREType(model.get(RE_TYPE).toString());
        }

        setAttachmentsSet(model, realEstateData);

        if (model.get(PURCHASE_DATE) != null) {
            realEstateData.setPurchaseDate(model.get(PURCHASE_DATE).toString());
        }
        if (model.get(POSTING_DATE) != null) {
            realEstateData.setPostingDate(model.get(POSTING_DATE).toString());
        }
        if (model.get(IDENTITY_NUMBER) != null) {
            realEstateData.setIdNo(model.get(IDENTITY_NUMBER).toString());
        }
        if (model.get(DEED_DATE) != null) {
            realEstateData.setMojDeedDate(model.get(DEED_DATE).toString());
        }
        if (model.get(ISSUER_COURT_NAME) != null) {
            realEstateData.setIssuerCourtName(model.get(ISSUER_COURT_NAME).toString());
        }
    }

    private void setAttachmentsSet(ODataModel model, RealEstateData realEstateData) {
        if (model.get(ATTACHMENTS_SET) != null) {
            List<ODataEntry> attachments = ((ODataDeltaFeedImpl) model.get(ATTACHMENTS_SET)).getEntries();
            if (CollectionUtils.isNotEmpty(attachments)) {
                Set<RealEstateAttachmentData> attachmentDataSet = new HashSet<>();
                for (ODataEntry attachment : attachments) {
                    RealEstateAttachmentData realEstateAttachmentData = new RealEstateAttachmentData();
                    realEstateAttachmentData.setObjectId(attachment.getProperties().get(OBJECT_ID).toString());
                    realEstateAttachmentData.setDocGuid(attachment.getProperties().get("DocGUID").toString());
                    realEstateAttachmentData.setFilename(attachment.getProperties().get("Filename").toString());
                    realEstateAttachmentData.setMimeType(attachment.getProperties().get("MimeType").toString());
                    realEstateAttachmentData.setFileContString(attachment.getProperties().get("FileContString").toString());
                    realEstateAttachmentData.setObjectGUID(attachment.getProperties().get(OBJECT_GUID).toString());
                    attachmentDataSet.add(realEstateAttachmentData);
                }
                realEstateData.setAttachmentsSet(attachmentDataSet);
            }
        }
    }

}
