package com.sap.ibso.eservices.sagiaservices.converters.complaints;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CompAndEnqHdrToDetailNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.CompAndEnqHdrToTextNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ComplaintsAndEnquiryHdrsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.entry.ODataEntryImpl;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils.extractDateFrom;

/**
 *
 */
public class ComplaintEnquiriesPopulator extends ODataPopulator<ComplaintsAndEnquiryHdrsData> {
	private ContentHDRPopulator contentHDRPopulator;
    private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void populate(ODataModel model, ComplaintsAndEnquiryHdrsData complaintEnquiryData) {
		super.populate(model, complaintEnquiryData);
		complaintEnquiryData.setCompAndEnqHdrToDetailNav(readComplaintDetailsFrom(model.get()));
		complaintEnquiryData.setCompAndEnqHdrToContentNav(readComplaintAttachmentsFrom(model.get()));
		complaintEnquiryData.setCompAndEnqHdrToTextNav(readComplaintMessagesFrom(model.get()));
	}

	private List<ContentHDRData> readComplaintAttachmentsFrom(Map<String, Object> map) {
		return contentHDRPopulator.readAttachmentByOdataProperty(map, "CompAndEnqHdrToContentNav");
	}

	private List<CompAndEnqHdrToTextNavData> readComplaintMessagesFrom(Map<String, Object> map) {

		Object complaintMessagesEntries = map.get("CompAndEnqHdrToTextNav");
		if (complaintMessagesEntries != null) {
			List<ODataEntry> messagesODataEntries = ((ODataDeltaFeedImpl) complaintMessagesEntries).getEntries();
			return messagesODataEntries.stream()
									   .map(ODataEntry::getProperties)
									   .map(this::toComplaintMessagesDTO)
									   .collect(Collectors.toList());
		}
		return Collections.emptyList();
	}

	private CompAndEnqHdrToDetailNavData readComplaintDetailsFrom(Map<String, Object> map) {
		
		Object complaintDetailsEntry = map.get("CompAndEnqHdrToDetailNav");
		if(complaintDetailsEntry != null) {
			 Map<String, Object> odataEntryImpl = ((ODataEntryImpl) complaintDetailsEntry).getProperties();
			    return toComplaintDetailsFrom(odataEntryImpl);
		}
		return null;
	}
	
	private String extractStringValueFrom(Map<String, Object> map, String key) {
		return CollectionUtils.getValueFrom(map, key);
	}
	
	private CompAndEnqHdrToDetailNavData toComplaintDetailsFrom(Map<String, Object> map) {
		
		CompAndEnqHdrToDetailNavData details = new CompAndEnqHdrToDetailNavData();
		details.setSrID(extractStringValueFrom(map, "SrID"));
		details.setCategory1(extractStringValueFrom(map, "Category1"));
		details.setCategory2(extractStringValueFrom(map, "Category2"));
		details.setBranch(extractStringValueFrom(map, "Branch"));
		details.setEnquiryType(extractStringValueFrom(map, "EnquiryType"));
		details.setTextMsg(extractStringValueFrom(map, "TextMsg"));
		return details;
	}
	
	private CompAndEnqHdrToTextNavData toComplaintMessagesDTO(Map<String, Object> map) {

		CompAndEnqHdrToTextNavData complaintMessagesDTO = new CompAndEnqHdrToTextNavData();
		complaintMessagesDTO.setSrID(extractStringValueFrom(map, "SrID"));
		complaintMessagesDTO.setSrGuid(extractStringValueFrom(map, "SrGuid"));
		complaintMessagesDTO.setCommentDate(sagiaFormatProvider.getLocalizedDateData(extractDateFrom(map, "CommentDate")));
		complaintMessagesDTO.setCommentTime(extractStringValueFrom(map, "CommentTime"));
		complaintMessagesDTO.setZone(extractStringValueFrom(map, "Zone"));
		complaintMessagesDTO.setCommentBy(extractStringValueFrom(map, "CommentBy"));
		complaintMessagesDTO.setTdID(extractStringValueFrom(map, "TdID"));
		complaintMessagesDTO.setComments(extractStringValueFrom(map, "Comments"));
		complaintMessagesDTO.setStage(extractStringValueFrom(map, "Stage"));

		return complaintMessagesDTO;
	}

	/**
	 * @param sagiaFormatProvider
	 */
    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
    
	public ContentHDRPopulator getContentHDRPopulator() {
		return contentHDRPopulator;
	}

	/**
	 * @param contentHDRPopulator
	 */
	public void setContentHDRPopulator(final ContentHDRPopulator contentHDRPopulator) {
		this.contentHDRPopulator = contentHDRPopulator;
	}

}