package com.sap.ibso.eservices.facades.populators.odata;


import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.IsicMasterModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.odata.IsicInfoData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.media.MediaService;


public class IsicInfoODataPopulator implements Populator<IsicMasterModel, IsicInfoData> {

	@Resource
	private MediaService mediaService;
	
	@Resource
	private SagiaFormatProvider  sagiaFormatProvider;
    
	@Override
	public void populate(IsicMasterModel source, IsicInfoData target) throws ConversionException {
		
		//target.setInvestorid(source.geti);
		//target.setBusinessType(source.get);
		target.setLicenseType(source.getLicenseType());
		target.setIsicSection(source.getIsicSection());
		target.setIsicDivision(source.getIsicDivision());
		target.setIsicBranch(source.getIsicBranch());
		target.setIsicGroup(source.getIsicGroup());
		target.setIsicClass(source.getIsicClass());
		target.setIsicActivity(source.getIsicActivity());
		target.setAct(sagiaFormatProvider.formatBooleanForODATA(source.getActive()));	
	}
	

}
