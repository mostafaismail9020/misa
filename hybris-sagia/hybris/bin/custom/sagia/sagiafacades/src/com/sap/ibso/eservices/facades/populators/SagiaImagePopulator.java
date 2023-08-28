package com.sap.ibso.eservices.facades.populators;

import java.util.regex.Matcher;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.investsaudi.portal.core.model.InvestSaudiMediaModel;

import de.hybris.platform.acceleratorfacades.device.populators.ResponsiveImagePopulator;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.i18n.I18NService;


public class SagiaImagePopulator implements Populator<InvestSaudiMediaModel, ImageData> {

	
	public void populate(final InvestSaudiMediaModel mediaModel, final ImageData imageData) throws ConversionException
	{
		imageData.setDescription(mediaModel.getDescription());
		imageData.setDescriptionText(mediaModel.getDescriptionText());
	}
}
