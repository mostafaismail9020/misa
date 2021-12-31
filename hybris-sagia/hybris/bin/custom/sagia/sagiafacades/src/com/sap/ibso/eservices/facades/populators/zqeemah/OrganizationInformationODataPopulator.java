package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class OrganizationInformationODataPopulator implements Populator<EntityInformationModel, OrgInfoData> {

	@Override
	public void populate(EntityInformationModel source, OrgInfoData target) throws ConversionException {
		    target.setOrgName(source.getEntityName());
	        target.setOrgName1(source.getEntityNameArabic());
	        target.setLegalStatus(source.getLegalStatus().getCode());
	        target.setCapital(source.getCapital());
	        target.setEmail(source.getEmail());
	        target.setTelephone(source.getTelephone());
	        target.setMobile(source.getMobilePhone());
	        target.setCcode_Mobile(source.getCountryCodeForMobilePhone());
	        target.setCcode_Tele(source.getCountryCodeForTelephone());	        
	        target.setWebsite(source.getWebsite());
	        target.setRegion(source.getRegion().getCode());
	        target.setCity(source.getCity().getCode());	        
	        target.setStreet(source.getAddress());
	        target.setPostalCode(source.getPostalCode());
	        target.setPoBox(source.getPoBox());	        
	        target.setMncComp(source.getBasicInfoExtendedMultinationalCompany());	        
	        target.setCommMtd(source.getInvestment());	        
	        target.setLicenseDuration(source.getLicenseDuration());
		
	}

}
