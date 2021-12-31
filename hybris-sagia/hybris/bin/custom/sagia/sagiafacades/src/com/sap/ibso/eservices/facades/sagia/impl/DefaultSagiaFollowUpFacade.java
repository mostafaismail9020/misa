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
package com.sap.ibso.eservices.facades.sagia.impl;

import com.sap.ibso.eservices.core.sagia.format.SagiaDateData;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.WarningLettersData;
import com.sap.ibso.eservices.facades.data.specialservices.FollowUpData;
import com.sap.ibso.eservices.facades.populators.FollowUpDataPopulator;
import com.sap.ibso.eservices.facades.populators.SagiaWarningLettersInfosPopulator;
import com.sap.ibso.eservices.facades.sagia.SagiaFollowUpFacade;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.FollowUpServicesData;
import com.sap.ibso.eservices.sagiaservices.data.zui5sagia.WarningLettersInfos;
import com.sap.ibso.eservices.sagiaservices.exception.SagiaCRMCreateException;
import com.sap.ibso.eservices.sagiaservices.services.ZUI5SagiaFacade;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateViolationReplyFormData;
import com.sap.ibso.eservices.sagiaservices.services.followup.dto.CreateWarningLetterExtensionFormData;
import com.sap.ibso.eservices.sagiaservices.services.impl.GlobalValsService;
import com.sap.ibso.eservices.sagiaservices.services.impl.WarningLettersInfosService;
import de.hybris.platform.servicelayer.media.MediaService;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * DefaultSagiaFollowUpFacade
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package com.sap.ibso.eservices.facades.sagia.impl
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class DefaultSagiaFollowUpFacade implements SagiaFollowUpFacade
{
	@Autowired
	private ZUI5SagiaFacade                   zui5SagiaFacade;
	@Autowired
	private FollowUpDataPopulator             sagiaFollowUpPopulator;
	@Autowired
	private SagiaWarningLettersInfosPopulator sagiaWarningLettersInfosPopulator;
	@Autowired
	private SagiaFormatProvider               sagiaFormatProvider;
	@Autowired
	private WarningLettersInfosService        warningLettersInfosService;
	@Autowired
	private MediaService                      mediaService;
	@Autowired
	private GlobalValsService                 globalValsService;


	@Override
	public List<FollowUpData> getViolationReplyEntries()
	{
		final Collection<FollowUpServicesData> data = zui5SagiaFacade.getViolationRepliesEntries();
		final List<FollowUpData> result = new ArrayList<>();
		data.forEach(element -> {
			final FollowUpData resultElement = new FollowUpData();
			sagiaFollowUpPopulator.populate(element, resultElement);
			result.add(resultElement);
		});

		return result;
	}

	@Override
	public List<FollowUpData> getWarningLetterEntries()
	{
		Collection<FollowUpServicesData> data = zui5SagiaFacade.getWarningLetterExtensionEntries();
		final List<FollowUpData> result = new ArrayList<>();
		data.forEach(element -> {
			final FollowUpData resultElement = new FollowUpData();

			sagiaFollowUpPopulator.populate(element, resultElement);
			result.add(resultElement);
		});
		return result;
	}

	@Override
	public FollowUpData getFollowUpEntry(final Object id)
	{
		final FollowUpServicesData entry = zui5SagiaFacade.getFollowUpSingleEntry(id);
		final FollowUpData resultElement = new FollowUpData();
		sagiaFollowUpPopulator.populate(entry, resultElement);
		return resultElement;
	}

	@Override
	public SagiaDateData getDateData(final String startDate, final Integer days)
	{
		return sagiaFormatProvider.getLocalizedDateWithDaysShift(startDate, days);
	}

	@Override
	public List<WarningLettersData> getWarningLettersExtensionData()
	{
		final List<WarningLettersData> result = new ArrayList<>();

		final Collection<WarningLettersInfos> warningLettersInfos = warningLettersInfosService.getWarningLettersWarningLetterExtension();
		warningLettersInfos.forEach(letter -> {
			final WarningLettersData letterData = new WarningLettersData();
			sagiaWarningLettersInfosPopulator.populate(letter, letterData);
			result.add(letterData);
		});

		return result;
	}

	@Override
	public List<WarningLettersData> getWarningLettersViolationRepliesData()
	{
		final List<WarningLettersData> result = new ArrayList<>();

		final Collection<WarningLettersInfos> warningLettersInfos = warningLettersInfosService.getWarningLettersViolationReplies();
		warningLettersInfos.forEach(letter -> {
			final WarningLettersData letterData = new WarningLettersData();
			sagiaWarningLettersInfosPopulator.populate(letter, letterData);
			result.add(letterData);
		});

		return result;
	}

	@Override
	public void createViolationReply(final CreateViolationReplyFormData createViolationReplyFormData)
					throws SagiaCRMCreateException	{
		zui5SagiaFacade.createViolationReply(createViolationReplyFormData);
	}

	@Override
	public void createWarningLetterExtension(final CreateWarningLetterExtensionFormData createWarningLetterExtensionFormData)
					throws SagiaCRMCreateException{
		zui5SagiaFacade.createWarningLetterExtension(createWarningLetterExtensionFormData);
	}

	@Override
	public void checkCreateViolationReply()
	{
		globalValsService.checkViolationRepliesCreateAvalability();
	}

	@Override
	public void checkCreateWarningLetter()
	{
		globalValsService.checkWarningLetterCreateAvalability();
	}

	@Override
	public WarningLettersData getWarningLetterData(final String letterNumber)
	{
		final Collection<WarningLettersInfos> warningLettersInfos = warningLettersInfosService.getWarningLettersViolationReplies();
		final WarningLettersData result = new WarningLettersData();

		for (WarningLettersInfos infos : warningLettersInfos)		{
			if (Strings.isNotEmpty(infos.getWarningLetterNo()) && infos.getWarningLetterNo().equals(letterNumber)) {
				sagiaWarningLettersInfosPopulator.populate(infos, result);

				break;
			}
		}

		return result;
	}

	/**
	 * @return
	 */
	public ZUI5SagiaFacade getZui5SagiaFacade()
	{
		return zui5SagiaFacade;
	}

	/**
	 * @param zui5SagiaFacade
	 */
	public void setZui5SagiaFacade(final ZUI5SagiaFacade zui5SagiaFacade)
	{
		this.zui5SagiaFacade = zui5SagiaFacade;
	}

	/**
	 * @return
	 */
	public FollowUpDataPopulator getSagiaFollowUpPopulator()
	{
		return sagiaFollowUpPopulator;
	}

	/**
	 * @param sagiaFollowUpPopulator
	 */
	public void setSagiaFollowUpPopulator(final FollowUpDataPopulator sagiaFollowUpPopulator)
	{
		this.sagiaFollowUpPopulator = sagiaFollowUpPopulator;
	}

	/**
	 * @return
	 */
	public SagiaWarningLettersInfosPopulator getSagiaWarningLettersInfosPopulator()
	{
		return sagiaWarningLettersInfosPopulator;
	}

	/**
	 * @param sagiaWarningLettersInfosPopulator
	 */
	public void setSagiaWarningLettersInfosPopulator(final SagiaWarningLettersInfosPopulator sagiaWarningLettersInfosPopulator)
	{
		this.sagiaWarningLettersInfosPopulator = sagiaWarningLettersInfosPopulator;
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

	/**
	 * @return
	 */
	public WarningLettersInfosService getWarningLettersInfosService()
	{
		return warningLettersInfosService;
	}

	/**
	 * @param warningLettersInfosService
	 */
	public void setWarningLettersInfosService(final WarningLettersInfosService warningLettersInfosService)
	{
		this.warningLettersInfosService = warningLettersInfosService;
	}

	/**
	 * @return
	 */
	public MediaService getMediaService()
	{
		return mediaService;
	}

	/**
	 * @param mediaService
	 */
	public void setMediaService(final MediaService mediaService)
	{
		this.mediaService = mediaService;
	}

	/**
	 * @return
	 */
	public GlobalValsService getGlobalValsService()
	{
		return globalValsService;
	}

	/**
	 * @param globalValsService
	 */
	public void setGlobalValsService(final GlobalValsService globalValsService)
	{
		this.globalValsService = globalValsService;
	}
}
