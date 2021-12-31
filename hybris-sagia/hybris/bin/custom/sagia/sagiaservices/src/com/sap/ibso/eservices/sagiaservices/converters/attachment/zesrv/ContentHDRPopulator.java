package com.sap.ibso.eservices.sagiaservices.converters.attachment.zesrv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import com.sap.ibso.eservices.facades.data.zesrvEnhOData.ContentHDR;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.ContentHDRUtils;
import com.sap.ibso.eservices.sagiaservices.data.zesrvEnhOData.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ContentHDRPopulator extends ODataPopulator<ContentHDRData> {
    /**
     * Populate from ContentHDRData to ContentHDR
     * @param source the source object
     * @param target the target to fill
     * @throws ConversionException
     */

    @Override
    public void populate(ODataModel model, ContentHDRData target) throws ConversionException {
        super.populate(model, target);
        String fullFileName = ContentHDRUtils.createFullFileNameFrom(target.getFilename(), target.getMimetype());
        target.setFullFileName(fullFileName);
    }
    
	public List<ContentHDRData> readAttachmentByOdataProperty(Map<String, Object> map, String odataPropertyName) {
		List<ContentHDRData> contentHDRData = new ArrayList<>();
		Object attachmentEntries = map.get(odataPropertyName);
		if (attachmentEntries != null) {
			List<ODataEntry> attachmentsODataEntries = ((ODataDeltaFeedImpl) attachmentEntries).getEntries();
			for (ODataEntry entry : attachmentsODataEntries) {
				final ContentHDRData data = new ContentHDRData();
				this.populate(new ODataModel(entry), data);
				contentHDRData.add(data);
			}
			return contentHDRData;
		}
		return Collections.emptyList();
	}
	
	public ContentHDR from(ContentHDRData source) {
		ContentHDR contentHDR = new ContentHDR();
		contentHDR.setObjectId(source.getObjectId());
		contentHDR.setObjectGuid(source.getObjectGuid());
		contentHDR.setFilename(source.getFilename());
		contentHDR.setFilesize(source.getFilesize());
		contentHDR.setMimetype(source.getMimetype());
		contentHDR.setDocumentId(source.getDocumentId());
		contentHDR.setContenttype(source.getContenttype());
		contentHDR.setCrtdby(source.getCrtdby());
		contentHDR.setCrtdon(source.getCrtdon());
		contentHDR.setStage(source.getStage());
		contentHDR.setDockeyid(source.getDockeyid());
		contentHDR.setShDocId(source.getShDocId());
		contentHDR.setFullFileName(source.getFullFileName());
		return contentHDR;
	}
}
