/**
 * ***********************************************************************
 * Copyright (c) 2018, SAP <sap.com>
 *
 * All portions of the code written by SAP are property of SAP.
 * All Rights Reserved.
 *
 * SAP
 *
 *
 * Web: sap.com
 * ***********************************************************************
 */
package com.sap.ibso.eservices.sagiaservices.converters;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import com.sap.ibso.eservices.sagiaservices.converters.attachment.zui5.ContentHDRPopulator;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ContentHDRData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FollowTextNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FollowUpServicesData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ViolationNavData;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;

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
public class FollowUpPopulator extends ODataPopulator<FollowUpServicesData>
{
	private FollowUpViolationDataPopulator violationNavPopulator;
	private ContentHDRPopulator contentHDRPopulator;
	private FollowUpTextDataPopulator textNavPopulator;

	@Override
	public void populate(ODataModel model, FollowUpServicesData followUpServicesData) throws ConversionException
	{
		super.populate(model, followUpServicesData);
		
		final Map<String, Object> map = setViolation(model, followUpServicesData);
		followUpServicesData.setContentHdr(setContentHdr(map));
		setFollowText(followUpServicesData, map);
	}

	private Map<String, Object> setViolation(ODataModel model, FollowUpServicesData followUpServicesData) {
		final Map<String, Object> map = model.get();
		final ODataFeed violationsNav = (ODataFeed) map.get("FollowupServicesToViolationsNav");
		if (violationsNav != null && violationsNav.getEntries() != null && !violationsNav.getEntries().isEmpty()) {
			final Set<ViolationNavData> violationNavDataSet = new HashSet<>();
			for(ODataEntry oDataEntry : violationsNav.getEntries()) {
				final ViolationNavData violationNavData = new ViolationNavData();
				violationNavPopulator.populate(new ODataModel(oDataEntry), violationNavData);
				violationNavDataSet.add(violationNavData);
			}
			followUpServicesData.setViolation(violationNavDataSet);
		}
		return map;
	}

	private HashSet<ContentHDRData> setContentHdr(Map<String, Object> map) {
		return new HashSet<ContentHDRData>(
				contentHDRPopulator.readAttachmentByOdataProperty(map, "FollowupServicesToContentHDRNav"));
	}

	private void setFollowText(FollowUpServicesData followUpServicesData, Map<String, Object> map) {
		final ODataFeed textNav = (ODataFeed) map.get("FollowupServicesToTextNav");
		if (textNav != null && textNav.getEntries() != null && !textNav.getEntries().isEmpty()) {
			final Set<FollowTextNavData> followTextNavDataSet = new HashSet<>();
			for(ODataEntry oDataEntry : textNav.getEntries()) {
				final FollowTextNavData followTextNavData = new FollowTextNavData();
				textNavPopulator.populate(new ODataModel(oDataEntry), followTextNavData);
				followTextNavDataSet.add(followTextNavData);
			}
			followUpServicesData.setFollowText(followTextNavDataSet);
		}
	}

	/**
	 * @return
	 */
	public FollowUpViolationDataPopulator getViolationNavPopulator()
	{
		return violationNavPopulator;
	}

	/**
	 * @param violationNavPopulator
	 */
	public void setViolationNavPopulator(final FollowUpViolationDataPopulator violationNavPopulator)
	{
		this.violationNavPopulator = violationNavPopulator;
	}

	/**
	 * @return
	 */
	public ContentHDRPopulator getContentHDRPopulator() {
		return contentHDRPopulator;
	}

	/**
	 * @param contentHdrNavPopulator
	 */
	public void setContentHDRPopulator(final ContentHDRPopulator contentHDRPopulator) {
		this.contentHDRPopulator = contentHDRPopulator;
	}

	/**
	 * @return
	 */
	public FollowUpTextDataPopulator getTextNavPopulator()
	{
		return textNavPopulator;
	}

	/**
	 * @param textNavPopulator
	 */
	public void setTextNavPopulator(final FollowUpTextDataPopulator textNavPopulator)
	{
		this.textNavPopulator = textNavPopulator;
	}
}
