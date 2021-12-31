package com.sap.ibso.eservices.sagiaservices.converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.zui5.GetTextDataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.SagiaCRMGovtService;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GetTextData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaGovtServiceDataPopulator extends ODataPopulator<SagiaCRMGovtService> {
	
	private GetTextDataPopulator getTextDataPopulator;
	private ContentHDRPopulator contentHDRPopulator;

	@Override
	public void populate(ODataModel model, SagiaCRMGovtService sagiaService) throws ConversionException {
		super.populate(model, sagiaService);
		sagiaService.setAttachments(readAttachments(model.get()));
		sagiaService.setGovtServicesToTextNav(readMessages(model.get()));
	}

	private List<ContentHDRData> readAttachments(Map<String, Object> map) {
		return contentHDRPopulator.readAttachmentByOdataProperty(map, "GovtServicesToContentHDRNav");
	}
    
	private List<GetTextData> readMessages(Map<String, Object> map) {
		Object govtMessages = map.get("GovtServicesToTextNav");
		List<GetTextData> textDataList = new ArrayList<>();
		if (govtMessages != null) {
			List<ODataEntry> messagesODataEntries = ((ODataDeltaFeedImpl) govtMessages).getEntries();
			for (ODataEntry entry : messagesODataEntries) {
				GetTextData getTextData = new GetTextData();
				getTextDataPopulator.populate(new ODataModel(entry), getTextData);
				textDataList.add(getTextData);
			}
			return textDataList;
		}
		return Collections.emptyList();
	}
    
	public GetTextDataPopulator getGetTextDataPopulator() {
		return getTextDataPopulator;
	}

	public void setGetTextDataPopulator(GetTextDataPopulator getTextDataPopulator) {
		this.getTextDataPopulator = getTextDataPopulator;
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
