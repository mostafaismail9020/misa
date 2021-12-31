package com.sap.ibso.eservices.sagiaservices.converters.legalconsultation;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.zesrv.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.GetTextData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryData;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.LegalInquiryHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.entry.ODataEntryImpl;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import java.util.*;
import java.util.stream.Collectors;

import static com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils.extractDateFrom;

/**
 *
 */
public class LegalInquiryHDRPopulator extends ODataPopulator<LegalInquiryHDRData> {
	
	private ContentHDRPopulator contentHDRPopulator;
    @Override
    public void populate(ODataModel model, LegalInquiryHDRData legalInquiryHDRData) {
        super.populate(model, legalInquiryHDRData);

        legalInquiryHDRData.setLegalInquiry(readLegalInquiryDetailsFrom(model.get()));
        legalInquiryHDRData.setContentHDRSet(readLegalInquiryAttachmentsFrom(model.get()));
        legalInquiryHDRData.setGetTextSet(readLegalInquiryMessagesFrom(model.get()));
    }

    private LegalInquiryData readLegalInquiryDetailsFrom(Map<String, Object> map) {

        Object legalInquiryDetailsEntry = map.get("LegalInquiry");
        if (legalInquiryDetailsEntry != null) {
            Map<String, Object> odataEntryImpl = ((ODataEntryImpl) legalInquiryDetailsEntry).getProperties();
            return toLegalInquiryDetailsFrom(odataEntryImpl);
        }
        return null;
    }

    private String extractStringValueFrom(Map<String, Object> map, String key) {
        return CollectionUtils.getValueFrom(map, key);
    }

    private LegalInquiryData toLegalInquiryDetailsFrom(Map<String, Object> map) {

        LegalInquiryData details = new LegalInquiryData();
        details.setSrId(extractStringValueFrom(map, "SrID"));
        details.setLegEnqTitle(extractStringValueFrom(map, "LegEnqTitle"));
        details.setLegEnqDesc(extractStringValueFrom(map, "LegEnqDesc"));
        details.setTextMsg(extractStringValueFrom(map, "TextMsg"));
        return details;
    }

    private List<ContentHDRData> readLegalInquiryAttachmentsFrom(Map<String, Object> map) {
        return contentHDRPopulator.readAttachmentByOdataProperty(map, "ContentHDRSet");
    }

    private List<GetTextData> readLegalInquiryMessagesFrom(Map<String, Object> map) {

        Object legalInquiryMessagesEntries = map.get("GetTextSet");
        if (legalInquiryMessagesEntries != null) {
            List<ODataEntry> messagesODataEntries = ((ODataDeltaFeedImpl) legalInquiryMessagesEntries).getEntries();
            return messagesODataEntries.stream()
                    .map(ODataEntry::getProperties)
                    .map(this::toLegalInquiryMessagesDTO)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private GetTextData toLegalInquiryMessagesDTO(Map<String, Object> map) {

        GetTextData legalInquiryMessagesDTO = new GetTextData();
        legalInquiryMessagesDTO.setSrId(extractStringValueFrom(map, "SrId"));
        legalInquiryMessagesDTO.setSrGuid(extractStringValueFrom(map, "SrGuid"));
        legalInquiryMessagesDTO.setTdid(extractStringValueFrom(map, "Tdid"));
        legalInquiryMessagesDTO.setTdline(extractStringValueFrom(map, "Tdline"));
        legalInquiryMessagesDTO.setCommentDate(extractDateFrom(map, "CommentDate"));
        legalInquiryMessagesDTO.setCommentTime(extractStringValueFrom(map, "CommentTime"));
        legalInquiryMessagesDTO.setTimezone(extractStringValueFrom(map, "Timezone"));
        legalInquiryMessagesDTO.setStage(extractStringValueFrom(map, "Stage"));

        return legalInquiryMessagesDTO;

    }

	public ContentHDRPopulator getContentHDRPopulator() {
		return contentHDRPopulator;
	}

	public void setContentHDRPopulator(ContentHDRPopulator contentHDRPopulator) {
		this.contentHDRPopulator = contentHDRPopulator;
	}

}


