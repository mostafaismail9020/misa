package com.sap.ibso.eservices.facades.populators.zqeemah;

import com.sap.ibso.eservices.facades.data.zqeemah2.BasicContactInfo;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.BasicContactInfoData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class BasicContactInfoPopulator implements Populator<BasicContactInfoData, BasicContactInfo> {
    @Override
    public void populate(BasicContactInfoData basicContactInfoData, BasicContactInfo basicContactInfo) throws ConversionException {
        basicContactInfo.setCountryText(basicContactInfoData.getQ2countrytext());
		basicContactInfo.setCountryCodeForMobileNumbe(basicContactInfoData.getQ2mobilecde());
		basicContactInfo.setRefId(basicContactInfoData.getQ2refid());
		basicContactInfo.setNationalityText(basicContactInfoData.getQ2nationaitytext());
		basicContactInfo.setCountryCodeForTelephone(basicContactInfoData.getQ2telecode());
		basicContactInfo.setFirstName(basicContactInfoData.getQ2firstname());
		basicContactInfo.setTitle(basicContactInfoData.getQ2gendertext());
		basicContactInfo.setLastName(basicContactInfoData.getQ2lastname());
		basicContactInfo.setRoleText(basicContactInfoData.getQ2roletext());
		basicContactInfo.setGender(basicContactInfoData.getQ2gender());
		basicContactInfo.setCountry(basicContactInfoData.getQ2country());
		basicContactInfo.setNationality(basicContactInfoData.getQ2nationaity());
		basicContactInfo.setRole(basicContactInfoData.getQ2role());
		basicContactInfo.setMobileNumber(basicContactInfoData.getQ2mobileno());
		basicContactInfo.setTelephone(basicContactInfoData.getQ2teleno());
		basicContactInfo.setEmail(basicContactInfoData.getQ2email());
		basicContactInfo.setReturnProperty(basicContactInfoData.getQ2returnproperty());
		basicContactInfo.setQeemah(basicContactInfoData.getQ2qeemah());
    }
}
