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

import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ViolationNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.WarningLettersInfos;
import com.sap.ibso.eservices.sagiaservices.services.ODataModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.olingo.odata2.api.ep.entry.ODataEntry;
import org.apache.olingo.odata2.api.ep.feed.ODataFeed;

import java.util.Collections;
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
public class WarningLettersInfoPopulator extends ODataPopulator<WarningLettersInfos>
{
	private FollowUpViolationDataPopulator violationNavPopulator;

	@Override
	public void populate(ODataModel model, WarningLettersInfos warningLettersInfos) throws ConversionException
	{
		super.populate(model, warningLettersInfos, SagiaPropertyNamingStrategy.UPPER_CAMEL_CASE);
		if(warningLettersInfos.getViolations() == null) {
			warningLettersInfos.setViolations(Collections.emptySet());
		}
		final Map<String, Object> map = model.get();
		final ODataFeed violationsNav = (ODataFeed) map.get("WarningInfosToViolationsNav");
		if (violationsNav != null && violationsNav.getEntries() != null && !violationsNav.getEntries().isEmpty()) {
			final Set<ViolationNavData> violationNavDataSet = new HashSet<>();
			for(ODataEntry oDataEntry : violationsNav.getEntries()) {
				final ViolationNavData violationNavData = new ViolationNavData();
				violationNavPopulator.populate(new ODataModel(oDataEntry), violationNavData);
				violationNavDataSet.add(violationNavData);
			}
			warningLettersInfos.setViolations(violationNavDataSet);
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
}
