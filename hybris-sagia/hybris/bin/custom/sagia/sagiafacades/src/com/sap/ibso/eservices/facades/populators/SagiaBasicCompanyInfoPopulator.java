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

import com.sap.ibso.eservices.facades.data.BasicCompanyData;
import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.fest.util.Strings;

import java.math.BigDecimal;

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
public class SagiaBasicCompanyInfoPopulator implements Populator<com.sap.ibso.eservices.sagiaservices.data.company.BasicCompanyData, BasicCompanyData>
{
	private PriceDataFactory priceDataFactory;

	private static final String SAR_CURRENCY = "SAR";

	@Override
	public void populate(final com.sap.ibso.eservices.sagiaservices.data.company.BasicCompanyData source, final BasicCompanyData target) throws ConversionException
	{
		if(source.getCapitalValue() != null && source.getCapitalValue().compareTo(BigDecimal.ZERO) != 0) {
			if(!Strings.isEmpty(source.getCurrencyIso())){
				target.setCapital(priceDataFactory.create(PriceDataType.FROM, source.getCapitalValue(), source.getCurrencyIso()).getFormattedValue());
			}
			else{
				target.setCapital(priceDataFactory.create(PriceDataType.FROM,source.getCapitalValue(), SAR_CURRENCY).getFormattedValue());
			}
		}

		target.setLegalStatus(source.getLegalStatus());
		target.setCity(source.getCity());
		target.setRegion(source.getRegion());
		target.setCountry(source.getCountry());
		target.setEntityName(source.getCompanyNameInEnglish());
		target.setEntityNameArabic(source.getCompanyNameInArabic());
		target.setCountry(source.getCountry());
		target.setISICDivision(source.getIsicDevision());
		target.setISICSection(source.getIsicSection());
		target.setISINCode(source.getIsinCode());
		target.setBusinessEmailAddress(source.getBusinessEmailAddress());
	}

	public void setPriceDataFactory(PriceDataFactory priceDataFactory) {
		this.priceDataFactory = priceDataFactory;
	}
}
