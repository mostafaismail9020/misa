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
public class OrganizationInformationPopulator implements Populator<BasicOrganizationInformationData, BasicOrganizationInformation> {

    @Override
    public void populate(BasicOrganizationInformationData basicOrganizationInformationData, BasicOrganizationInformation basicOrganizationInformation) throws ConversionException {
        basicOrganizationInformation.setCompanyName(basicOrganizationInformationData.getQ2companyname());
        basicOrganizationInformation.setInvestment(basicOrganizationInformationData.getQ2investment());
        basicOrganizationInformation.setLegalStatusText(basicOrganizationInformationData.getQ2legalstatus());
        basicOrganizationInformation.setCity(basicOrganizationInformationData.getQ2city());
        basicOrganizationInformation.setRegionText(basicOrganizationInformationData.getQ2capital());
        basicOrganizationInformation.setQeemah(basicOrganizationInformationData.getQ2qeemah());
        basicOrganizationInformation.setCityText(basicOrganizationInformationData.getQ2citytext());
        basicOrganizationInformation.setRefId(basicOrganizationInformationData.getQ2refid());
        basicOrganizationInformation.setRegion(basicOrganizationInformationData.getQ2region());
        basicOrganizationInformation.setReturnProperty(basicOrganizationInformationData.getQ2return());
        basicOrganizationInformation.setEntityName(basicOrganizationInformationData.getQ2entnameEn());
        basicOrganizationInformation.setEntityNameArabic(basicOrganizationInformationData.getQ2entnameAr());
        basicOrganizationInformation.setLegalStatus(basicOrganizationInformationData.getQ2legalstatus());
        basicOrganizationInformation.setLabourSize(basicOrganizationInformationData.getQ2laboursize());
        basicOrganizationInformation.setCapital(basicOrganizationInformationData.getQ2capital());
    }

}
