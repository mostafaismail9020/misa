package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseClearanceSetData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseToTextNavData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import com.sap.ibso.eservices.sagiaservices.utils.CollectionUtils;
import com.sap.ibso.eservices.sagiaservices.utils.ObjectUtils;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class LicenseClereanceSetPopulator extends ODataPopulator<LicenseClearanceSetData> {
	private ContentHDRPopulator contentHDRPopulator;

    @Override
    public void populate(ODataModel model, LicenseClearanceSetData licenseClearanceSetData) throws ConversionException
    {
        super.populate(model,licenseClearanceSetData);
        licenseClearanceSetData.setLicenseClearToContentNav(readLicenseClearanceSetAttachmentsFrom(model.get()));
        licenseClearanceSetData.setLicenseClearToTextNav(readLicenseClearanceSetMessagesFrom(model.get()));

    }

    private List<ContentHDRData> readLicenseClearanceSetAttachmentsFrom(Map<String, Object> map) {
		return contentHDRPopulator.readAttachmentByOdataProperty(map, "LicenseClearToContentNav");
    }

    private List<LicenseToTextNavData> readLicenseClearanceSetMessagesFrom(Map<String, Object> map) {

        Object licreplMessagesEntries = map.get("LicenseClearToTextNav");
        if (licreplMessagesEntries != null) {
            List<ODataEntry> messagesODataEntries = ((ODataDeltaFeedImpl) licreplMessagesEntries).getEntries();
            return messagesODataEntries.stream()
                    .map(ODataEntry::getProperties)
                    .map(this::toLicenceClearsetMessagesDTO)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private LicenseToTextNavData toLicenceClearsetMessagesDTO(Map<String, Object> map) {

        LicenseToTextNavData licenseCancellationMessagesDTO = new LicenseToTextNavData();
        licenseCancellationMessagesDTO.setSrID(extractStringValueFrom(map, "SrID"));
        licenseCancellationMessagesDTO.setSrGuid(extractStringValueFrom(map, "SrGuid"));
        licenseCancellationMessagesDTO.setCommentDate(extractDateFrom(map, "CommentDate"));
        licenseCancellationMessagesDTO.setCommentTime(extractStringValueFrom(map, "CommentTime"));
        licenseCancellationMessagesDTO.setZone(extractStringValueFrom(map, "Zone"));
        licenseCancellationMessagesDTO.setCommentBy(extractStringValueFrom(map, "CommentBy"));
        licenseCancellationMessagesDTO.setTdID(extractStringValueFrom(map, "TdID"));
        licenseCancellationMessagesDTO.setComments(extractStringValueFrom(map, "Comments"));
        licenseCancellationMessagesDTO.setStage(extractStringValueFrom(map, "Stage"));

        return licenseCancellationMessagesDTO;

    }

    private String extractStringValueFrom(Map<String, Object> map, String key) {
        return CollectionUtils.getValueFrom(map, key);
    }

    private LocalDateTime extractDateFrom(Map<String, Object> map, String key) {

        Object dateObject = map.get(key);

        if(dateObject == null) {
            return null;
        }
        if (dateObject instanceof GregorianCalendar) {
            Calendar calendar = ((GregorianCalendar) dateObject);
            TimeZone tz = calendar.getTimeZone();
            ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
            return LocalDateTime.ofInstant(calendar.toInstant(), zid);
        }
        return ObjectUtils.convertToDate(CollectionUtils.getValueFrom(map, key));
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
