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
package com.sap.ibso.eservices.backoffice.handler;

import com.hybris.cockpitng.config.jaxb.wizard.CustomType;
import com.hybris.cockpitng.core.model.WidgetModel;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandler;
import com.hybris.cockpitng.widgets.configurableflow.FlowActionHandlerAdapter;
import com.sap.ibso.eservices.backoffice.exception.SagiaIllegalPatternException;
import com.sap.ibso.eservices.core.model.SagiaDateFormatModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package sagia.services.handler
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class CreateDateFormatHandler implements FlowActionHandler
{

	private static final Logger LOG = Logger.getLogger(CreateDateFormatHandler.class);

	private ModelService        modelService;
	private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void perform(final CustomType customType, final FlowActionHandlerAdapter adapter,
	                    final Map<String, String> map)
	{
		final WidgetModel widget = adapter.getWidgetInstanceManager().getModel();

		try {
			final SagiaDateFormatModel language = widget.getValue("newDate", SagiaDateFormatModel.class);

			final LocalDateTime dateTimeNow = LocalDateTime.now();
			dateTimeNow.format(DateTimeFormatter.ofPattern(language.getDateTimePattern()));

			final LocalDate datenow = LocalDate.now();
			datenow.format(DateTimeFormatter.ofPattern(language.getDatePattern()));

			final LocalTime timeNow = LocalTime.now();
			timeNow.format(DateTimeFormatter.ofPattern(language.getTimePattern()));

			getModelService().save(language);
			sagiaFormatProvider.updateFormatsInfo();
			adapter.cancel();
		}
		catch (IllegalArgumentException ex)
		{
			LOG.error(ex.getMessage(),ex);
			throw new SagiaIllegalPatternException(ex.getMessage());
		}
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	public SagiaFormatProvider getSagiaFormatProvider()
	{
		return sagiaFormatProvider;
	}

	public void setSagiaFormatProvider(final SagiaFormatProvider sagiaFormatProvider)
	{
		this.sagiaFormatProvider = sagiaFormatProvider;
	}
}
