/**
 * ***********************************************************************
 * Copyright (c) 2017, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 * Moscow, Russian Federation
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.converters;

import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContactUpdateMessagesData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ServiceBpHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.SrvBPHdrContactData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.sagiaservices.converters
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ServiceBpHDRPopulator extends ODataPopulator<ServiceBpHDRData>
{
	private ServiceBpHDRContactPopulator serviceBpHDRContactPopulator;
	private ContentHDRPopulator contentHDRPopulator;
	private CommentsUpdateHistoryPopulator commentsUpdateHistoryPopulator;

	/*
	 * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
	 * The structure of this method is not difficult to understand in the given context.
	 */
	@SuppressWarnings({ "squid:MethodCyclomaticComplexity"})
	@Override
	public void populate(ODataModel model, ServiceBpHDRData serviceBpHDRData) throws ConversionException
	{
		super.populate(model, serviceBpHDRData);

		Map<String, Object> map = model.get();
		ODataFeed serviceBpHdrToContactNav = (ODataFeed) map.get("SrvBPHdrToBPContactNav");
		if(serviceBpHdrToContactNav != null && serviceBpHdrToContactNav.getEntries() != null && !serviceBpHdrToContactNav.getEntries().isEmpty()) {
			Set<SrvBPHdrContactData> homeContactDetailDataSet = new HashSet<>();
			for (ODataEntry entry : serviceBpHdrToContactNav.getEntries()) {
				final SrvBPHdrContactData data = new SrvBPHdrContactData();
				serviceBpHDRContactPopulator.populate(new ODataModel(entry), data);
				homeContactDetailDataSet.add(data);
			}
			serviceBpHDRData.setSrvBPHdrToBPContactDataSet(homeContactDetailDataSet);
		}

		ODataFeed srvBPHdrToAttachNav = (ODataFeed) map.get("SrvBPHdrToAttachNav");
		if(srvBPHdrToAttachNav != null && srvBPHdrToAttachNav.getEntries() != null && !srvBPHdrToAttachNav.getEntries().isEmpty()) {
			Set<ContentHDRData> contentHDRData = new HashSet<>();
			for (ODataEntry entry : srvBPHdrToAttachNav.getEntries()) {
				final ContentHDRData data = new ContentHDRData();
				contentHDRPopulator.populate(new ODataModel(entry), data);
				contentHDRData.add(data);
			}
			serviceBpHDRData.setSrvBPHdrToAttachNav(contentHDRData);
		}

		ODataFeed srvBPHdrToTextNav = (ODataFeed) map.get("SrvBPHdrToTextNav");
		if(srvBPHdrToTextNav != null && CollectionUtils.isNotEmpty(srvBPHdrToTextNav.getEntries())){
			Set<ContactUpdateMessagesData> contactUpdateMessagesData = new HashSet<>();
			for (ODataEntry entry : srvBPHdrToTextNav.getEntries()) {
				final ContactUpdateMessagesData data = new ContactUpdateMessagesData();
				commentsUpdateHistoryPopulator.populate(new ODataModel(entry), data);
				contactUpdateMessagesData.add(data);
			}
			serviceBpHDRData.setSrvBPHdrToTextNav(contactUpdateMessagesData);
		}
	}

	/**
	 * @param serviceBpHDRContactPopulator
	 */
	public void setServiceBpHDRContactPopulator(final ServiceBpHDRContactPopulator serviceBpHDRContactPopulator)
	{
		this.serviceBpHDRContactPopulator = serviceBpHDRContactPopulator;
	}

	public void setContentHDRPopulator(ContentHDRPopulator contentHDRPopulator) {
		this.contentHDRPopulator = contentHDRPopulator;
	}

	public void setCommentsUpdateHistoryPopulator(CommentsUpdateHistoryPopulator commentsUpdateHistoryPopulator) {
		this.commentsUpdateHistoryPopulator = commentsUpdateHistoryPopulator;
	}
}
