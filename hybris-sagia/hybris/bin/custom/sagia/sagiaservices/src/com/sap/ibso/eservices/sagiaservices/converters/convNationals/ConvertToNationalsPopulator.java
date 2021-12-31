package com.sap.ibso.eservices.sagiaservices.converters.convNationals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.core.ep.feed.ODataDeltaFeedImpl;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.converters.ODataPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.converters.zui5.GetTextDataPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ConvToNationalsData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.GetTextData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

/**
 *
 */
public class ConvertToNationalsPopulator extends ODataPopulator<ConvToNationalsData> {
	private SagiaFormatProvider sagiaFormatProvider;
	private GetTextDataPopulator getTextDataPopulator;
	private ContentHDRPopulator contentHDRPopulator;
	
	@Override
	public void populate(ODataModel model, ConvToNationalsData contentDetailsData) {
		super.populate(model, contentDetailsData);
		contentDetailsData.setConvNatToContentNav(readAttachedFiles(model.get()));
		contentDetailsData.setConvNatToTextNav(readMessages(model.get()));
		contentDetailsData.setSagiaSrCrDate(sagiaFormatProvider.getLocalizedDateTimeData(contentDetailsData.getSrCrDate()));
		contentDetailsData.setSagiaLicExDate(sagiaFormatProvider.getLocalizedDateTimeData(contentDetailsData.getLicExDate()));
		contentDetailsData.setSagiaValidFrom(sagiaFormatProvider.getLocalizedDateTimeData(contentDetailsData.getValidFrom()));
	}

	private List<ContentHDRData> readAttachedFiles(Map<String, Object> map) {
		return contentHDRPopulator.readAttachmentByOdataProperty(map, "ConvNatToContentNav");
	}
	
	private List<GetTextData> readMessages(Map<String, Object> map) {
		Object convToNationalsMessages = map.get("ConvNatToTextNav");
		List<GetTextData> textDataList = new ArrayList<>();
		if (convToNationalsMessages != null) {
			List<ODataEntry> messagesODataEntries = ((ODataDeltaFeedImpl) convToNationalsMessages).getEntries();
			for (ODataEntry entry : messagesODataEntries) {
				GetTextData getTextData = new GetTextData();
				getTextDataPopulator.populate(new ODataModel(entry), getTextData);
				textDataList.add(getTextData);
			}
			return textDataList;
		}
		return Collections.emptyList();
	}

	/**
	 * @return
	 */
	public SagiaFormatProvider getSagiaFormatProvider() {
		return sagiaFormatProvider;
	}

	/**
	 * @param sagiaFormatProvider
	 */
	public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider) {
		this.sagiaFormatProvider = sagiaFormatProvider;
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
