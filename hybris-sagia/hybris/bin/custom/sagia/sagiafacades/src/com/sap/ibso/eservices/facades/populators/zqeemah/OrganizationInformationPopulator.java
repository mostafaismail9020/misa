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
public class OrganizationInformationPopulator implements Populator<OrgInfoData, OrganizationInformation> {

    @Override
    public void populate(OrgInfoData source, OrganizationInformation target) throws ConversionException {
        target.setEntityName(source.getOrgName());
        target.setEntityNameArabic(source.getOrgName1());
        target.setLegalStatus(source.getLegalStatus());
        target.setCapital(source.getCapital());
        target.setEmail(source.getEmail());
        target.setTelephone(source.getTelephone());
        target.setMobilePhone(source.getMobile());
        target.setCountryCodeForMobilePhone(source.getCcode_Mobile());
        target.setCountryCodeForTelephone(source.getCcode_Tele());
        target.setWebsite(source.getWebsite());
        target.setRegion(source.getRegion());
        target.setCity(source.getCity());
        target.setAddress(source.getStreet());
        target.setPostalCode(source.getPostalCode());
        target.setPoBox(source.getPoBox());
        target.setMultinationalCompany(source.getMncComp());
        target.setInvestment(source.getCommMtd());
        target.setLicenseDuration(source.getLicenseDuration());
    }
}
