package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.OrgInfoData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class OrganizationInformationReversePopulator implements Populator<OrganizationInformation, OrgInfoData> {

    @Override
    public void populate(OrganizationInformation organizationInformation, OrgInfoData orgInfoData) throws ConversionException {
        orgInfoData.setOrgName(organizationInformation.getEntityName());
		orgInfoData.setOrgName1(organizationInformation.getEntityNameArabic());
		orgInfoData.setLegalStatus(organizationInformation.getLegalStatus());
		orgInfoData.setCapital(organizationInformation.getCapital());
		orgInfoData.setEmail(organizationInformation.getEmail());
		orgInfoData.setTelephone(organizationInformation.getTelephone());
		orgInfoData.setMobile(organizationInformation.getMobilePhone());
		orgInfoData.setCcode_Mobile(organizationInformation.getCountryCodeForMobilePhone());
		orgInfoData.setCcode_Tele(organizationInformation.getCountryCodeForTelephone());
		orgInfoData.setWebsite(organizationInformation.getWebsite());
		orgInfoData.setRegion(organizationInformation.getRegion());
		orgInfoData.setCity(organizationInformation.getCity());
		orgInfoData.setStreet(organizationInformation.getAddress());
		orgInfoData.setPostalCode(organizationInformation.getPostalCode());
		orgInfoData.setPoBox(organizationInformation.getPoBox());
		orgInfoData.setMncComp(organizationInformation.getMultinationalCompany());
		orgInfoData.setCommMtd(organizationInformation.getInvestment());
		orgInfoData.setLicenseDuration(organizationInformation.getLicenseDuration());
    }
}
