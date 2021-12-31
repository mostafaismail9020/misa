package com.sap.ibso.eservices.facades.populators.zqeemah;
import com.sap.ibso.eservices.facades.data.zqeemah.BusinessActivity;
import com.sap.ibso.eservices.core.model.IsicMasterModel;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class BusinessActivityODataPopulator implements Populator<IsicMasterModel, BusinessActivity> {

	@Override
	public void populate(IsicMasterModel source, BusinessActivity target) throws ConversionException {
		
		target.setActivityId(source.getIsicActivity());
		target.setBranchId(source.getIsicBranch());
		//target.setBusinessType(source.get); // Missing
		target.setClassId(source.getIsicClass());
		//target.setDescription(source.get);
		target.setDivisionId(source.getIsicDivision());
		target.setGroupId(source.getIsicGroup());
		//target.setInvestorId(source.get);
		target.setLicenseType(source.getLicenseType());
		target.setSectionId(source.getIsicSection());
		
	}

}
