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

import com.sap.ibso.eservices.facades.data.BasicCompanyDictionary;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoDictionaryData;
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
public class SagiaBasicInfoDictionaryPopulator implements Populator<OrgInfoDictionaryData, BasicCompanyDictionary>
{
	@Override
	public void populate(final OrgInfoDictionaryData src,
	                     final BasicCompanyDictionary trg) throws ConversionException
	{
		trg.setBezei_city(src.getBezei_city());
		trg.setBezei_reg(src.getBezei_reg());
		trg.setBland_cty(src.getBland_cty());
		trg.setBland_reg(src.getBland_reg());
		trg.setCityCode_cty(src.getCityCode_cty());
		trg.setCityName_cty(src.getCityName_cty());
		trg.setCityCode_reg(src.getCityCode_reg());
		trg.setCityName_reg(src.getCityName_reg());
		trg.setDesc(src.getDesc());
		trg.setDescription(src.getDescription());
		trg.setDescription20(src.getDescription20());
		trg.setLand1(src.getLand1());
		trg.setLandx(src.getLandx());
		trg.setLandx50(src.getLandx50());
		trg.setNatio(src.getNatio());
		trg.setNatio50(src.getNatio50());
		trg.setTextshort(src.getTextshort());
		trg.setSource(src.getSource());
		trg.setItem(src.getItem());
		trg.setISINCode(src.getISINCode());
		trg.setTextlong(src.getTextlong());
		trg.setMseh3(src.getMseh3());
		trg.setMseh6(src.getMseh6());
		trg.setMsehi(src.getMsehi());
		trg.setMsehl(src.getMsehl());
		trg.setMseht(src.getMseht());
		trg.setLvkey(src.getLvkey());
	}
}
