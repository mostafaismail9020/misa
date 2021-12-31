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

import com.hybris.cockpitng.dataaccess.context.Context;
import com.hybris.cockpitng.dataaccess.context.impl.DefaultContext;
import com.hybris.cockpitng.dataaccess.facades.object.ObjectFacade;
import com.hybris.cockpitng.dataaccess.facades.object.exceptions.ObjectSavingException;
import com.hybris.cockpitng.engine.WidgetInstanceManager;
import com.hybris.cockpitng.validation.ValidationHandler;
import com.hybris.cockpitng.widgets.baseeditorarea.DefaultEditorAreaLogicHandler;
import com.sap.ibso.eservices.backoffice.exception.SagiaIllegalPatternException;
import com.sap.ibso.eservices.core.model.SagiaDateFormatModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Roman Plokhikh <roman.plokhikh@sap.com>
 * @package sagia.services.handler
 * @link http://sap.com/
 * @copyright 2018 SAP
 */
public class UpdateDateFormatHandler extends DefaultEditorAreaLogicHandler
{
	private ObjectFacade        objectFacade;
	private ValidationHandler   validationHandler;
	private SagiaFormatProvider sagiaFormatProvider;

	private static final Logger LOG = Logger.getLogger(UpdateDateFormatHandler.class);

	public Object performSave(WidgetInstanceManager widgetInstanceManager, Object currentObject) throws ObjectSavingException
	{
		Context ctx = new DefaultContext();
		ctx.addAttribute("ctxDisableCRUDCockpitEventNotification", Boolean.TRUE);

		try {
			final SagiaDateFormatModel model = (SagiaDateFormatModel) currentObject;

			final LocalDateTime dateTimeNow = LocalDateTime.now();
			dateTimeNow.format(DateTimeFormatter.ofPattern(model.getDateTimePattern()));

			final LocalDate datenow = LocalDate.now();
			datenow.format(DateTimeFormatter.ofPattern(model.getDatePattern()));

			final LocalTime timeNow = LocalTime.now();
			timeNow.format(DateTimeFormatter.ofPattern(model.getTimePattern()));

			final Object result = this.objectFacade.save(currentObject, ctx);
			sagiaFormatProvider.updateFormatsInfo();
			return result;
		}
		catch (IllegalArgumentException ex)
		{
			LOG.error(ex.getMessage(),ex);
			throw new SagiaIllegalPatternException(ex.getMessage());
		}
	}

	public ObjectFacade getObjectFacade()
	{
		return objectFacade;
	}

	@Required
	public void setObjectFacade(ObjectFacade objectFacade) {
		this.objectFacade = objectFacade;
	}

	public ValidationHandler getValidationHandler() {
		return this.validationHandler;
	}

	@Required
	public void setValidationHandler(ValidationHandler validationHandler) {
		this.validationHandler = validationHandler;
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
