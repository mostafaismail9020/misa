package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.LicenseReplaceMentToTextNavData;
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
public class LicenseReplacementPopulator extends ODataPopulator<LicenseReplaceMentData> {
	private ContentHDRPopulator contentHDRPopulator;

    @Override
    public void populate(ODataModel model, LicenseReplaceMentData licenseReplaceMentData) throws ConversionException
    {
        super.populate(model,licenseReplaceMentData);
        licenseReplaceMentData.setLicenseReplaceMentToContentNav(readLicenseReplacementAttachmentsFrom(model.get()));
        licenseReplaceMentData.setLicenseReplaceMentToTextNav(readLicenseReplacementMessagesFrom(model.get()));

    }

    private List<ContentHDRData> readLicenseReplacementAttachmentsFrom(Map<String, Object> map) {
        return contentHDRPopulator.readAttachmentByOdataProperty(map, "LicenseReplToContentNav");
    }

    private List<LicenseReplaceMentToTextNavData> readLicenseReplacementMessagesFrom(Map<String, Object> map) {

        Object licreplMessagesEntries = map.get("LicenseReplToTextNav");
        if (licreplMessagesEntries != null) {
            List<ODataEntry> messagesODataEntries = ((ODataDeltaFeedImpl) licreplMessagesEntries).getEntries();
            return messagesODataEntries.stream()
                    .map(ODataEntry::getProperties)
                    .map(this::toLicenceReplacementMessagesDTO)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private LicenseReplaceMentToTextNavData toLicenceReplacementMessagesDTO(Map<String, Object> map) {

        LicenseReplaceMentToTextNavData licenseReplacementMessagesDTO = new LicenseReplaceMentToTextNavData();
        licenseReplacementMessagesDTO.setSrID(extractStringValueFrom(map, "SrID"));
        licenseReplacementMessagesDTO.setSrGuid(extractStringValueFrom(map, "SrGuid"));
        licenseReplacementMessagesDTO.setCommentDate(extractDateFrom(map, "CommentDate"));
        licenseReplacementMessagesDTO.setCommentTime(extractStringValueFrom(map, "CommentTime"));
        licenseReplacementMessagesDTO.setZone(extractStringValueFrom(map, "Zone"));
        licenseReplacementMessagesDTO.setCommentBy(extractStringValueFrom(map, "CommentBy"));
        licenseReplacementMessagesDTO.setTdID(extractStringValueFrom(map, "TdID"));
        licenseReplacementMessagesDTO.setComments(extractStringValueFrom(map, "Comments"));
        licenseReplacementMessagesDTO.setStage(extractStringValueFrom(map, "Stage"));

        return licenseReplacementMessagesDTO;

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
