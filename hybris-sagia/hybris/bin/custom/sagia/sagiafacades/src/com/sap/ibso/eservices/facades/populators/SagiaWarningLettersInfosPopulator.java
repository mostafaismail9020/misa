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
package com.sap.ibso.eservices.facades.populators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.fest.util.Strings;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.WarningLettersData;
import com.sap.ibso.eservices.facades.data.specialservices.ViolationData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.ViolationNavData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.WarningLettersInfos;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.populators
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class SagiaWarningLettersInfosPopulator implements Populator<WarningLettersInfos, WarningLettersData>
{
	private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void populate(final WarningLettersInfos source, final WarningLettersData target)
					throws ConversionException
	{
		if (!Strings.isEmpty(source.getWarningEndDate()))
		{
			target.setEndDate(sagiaFormatProvider.getLocalizedDateData(Long.parseLong(source.getWarningEndDate())));
		}
		target.setNumber(source.getWarningLetterNo());

		if (!CollectionUtils.isEmpty(source.getViolations()))
		{
			final List<ViolationData> violationData = new ArrayList<>();
			for (final ViolationNavData srcData : source.getViolations())
			{
				final ViolationData trgData = new ViolationData();
				trgData.setSrId(srcData.getSrId());
				trgData.setServiceType(srcData.getServiceType());
				trgData.setViolationKey(srcData.getViolationKey());
				trgData.setViolationText(srcData.getViolationText());
				trgData.setViolationReply(srcData.getViolationReply());
				trgData.setViolationReplyText(srcData.getViolationReplyText());
				trgData.setViolationStatus(srcData.getViolationStatus());
				trgData.setViolationStatusText(srcData.getViolationStatusText());
				trgData.setViolationNote(srcData.getViolationNote());
				violationData.add(trgData);
			}
			target.setViolations(violationData);
		}
	}

	/**
	 * @return
	 */
	public SagiaFormatProvider getSagiaFormatProvider()
	{
		return sagiaFormatProvider;
	}

	/**
	 * @param sagiaFormatProvider
	 */
	public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider)
	{
		this.sagiaFormatProvider = sagiaFormatProvider;
	}
}
