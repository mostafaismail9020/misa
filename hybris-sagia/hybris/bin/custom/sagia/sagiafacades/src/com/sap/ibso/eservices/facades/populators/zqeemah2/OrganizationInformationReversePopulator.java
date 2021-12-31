package com.sap.ibso.eservices.facades.populators.zqeemah2;

import com.sap.ibso.eservices.facades.data.zqeemah2.BasicOrganizationInformation;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah2.BasicOrganizationInformationData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class OrganizationInformationReversePopulator implements Populator<BasicOrganizationInformation, BasicOrganizationInformationData> {

    @Override
    public void populate(BasicOrganizationInformation basicOrganizationInformation, BasicOrganizationInformationData basicOrganizationInformationData) throws ConversionException {
        basicOrganizationInformationData.setQ2companyname(basicOrganizationInformation.getEntityName());
        basicOrganizationInformationData.setQ2investment(basicOrganizationInformation.getCapital());
        basicOrganizationInformationData.setQ2legalstatus(basicOrganizationInformation.getLegalStatusText());
        basicOrganizationInformationData.setQ2city(basicOrganizationInformation.getCity());
        basicOrganizationInformationData.setQ2capital(basicOrganizationInformation.getCapital());
        basicOrganizationInformationData.setQ2qeemah(basicOrganizationInformation.getQeemah());
        basicOrganizationInformationData.setQ2citytext(basicOrganizationInformation.getCityText());
        basicOrganizationInformationData.setQ2refid(basicOrganizationInformation.getRefId());
        basicOrganizationInformationData.setQ2region(basicOrganizationInformation.getRegion());
        basicOrganizationInformationData.setQ2regiontext(basicOrganizationInformation.getRegionText());
        basicOrganizationInformationData.setQ2return(basicOrganizationInformation.getReturnProperty());
        basicOrganizationInformationData.setQ2entnameEn(basicOrganizationInformation.getEntityName());
        basicOrganizationInformationData.setQ2entnameAr(basicOrganizationInformation.getEntityNameArabic());
        basicOrganizationInformationData.setQ2legalstatus(basicOrganizationInformation.getLegalStatus());
        basicOrganizationInformationData.setQ2laboursize(basicOrganizationInformation.getLabourSize());
    }

}
