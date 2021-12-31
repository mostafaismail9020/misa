package com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.ContentHDRUtils;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;


public class ContentHDRPopulator extends ODataPopulator<ContentHDRData> {
	
    @Override
    public void populate(ODataModel model, ContentHDRData contentHDRData) {
        super.populate(model, contentHDRData);
        String fullFileName = ContentHDRUtils.createFullFileNameFrom(contentHDRData.getFilename(), contentHDRData.getMimetype());
        contentHDRData.setFullFileName(fullFileName);
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

}
