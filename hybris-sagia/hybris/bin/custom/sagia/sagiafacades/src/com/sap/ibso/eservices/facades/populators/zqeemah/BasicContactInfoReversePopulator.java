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
public class BasicContactInfoReversePopulator implements Populator<BasicContactInfo, BasicContactInfoData> {
    @Override
    public void populate(BasicContactInfo basicContactInfo, BasicContactInfoData basicContactInfoData) throws ConversionException {
        basicContactInfoData.setQ2countrytext(basicContactInfo.getCountryText());
		basicContactInfoData.setQ2mobilecde(basicContactInfo.getCountryCodeForMobileNumbe());
		basicContactInfoData.setQ2refid(basicContactInfo.getRefId());
		basicContactInfoData.setQ2nationaitytext(basicContactInfo.getNationalityText());
		basicContactInfoData.setQ2telecode(basicContactInfo.getCountryCodeForTelephone());
		basicContactInfoData.setQ2firstname(basicContactInfo.getFirstName());
		basicContactInfoData.setQ2gendertext(basicContactInfo.getTitle());
		basicContactInfoData.setQ2lastname(basicContactInfo.getLastName());
		basicContactInfoData.setQ2roletext(basicContactInfo.getRoleText());
		basicContactInfoData.setQ2gender(basicContactInfo.getGender());
		basicContactInfoData.setQ2country(basicContactInfo.getCountry());
		basicContactInfoData.setQ2nationaity(basicContactInfo.getNationality());
		basicContactInfoData.setQ2role(basicContactInfo.getRole());
		basicContactInfoData.setQ2mobileno(basicContactInfo.getMobileNumber());
		basicContactInfoData.setQ2teleno(basicContactInfo.getTelephone());
		basicContactInfoData.setQ2email(basicContactInfo.getEmail());
		basicContactInfoData.setQ2returnproperty(basicContactInfo.getReturnProperty());
		basicContactInfoData.setQ2qeemah(basicContactInfo.getQeemah());
    }
}
