package com.sap.ibso.eservices.facades.populators.zqeemah;

import java.time.LocalDate;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zqeemah.BasicContactInformation;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.BasicContactInformationData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class BasicContactInformationReversePopulatorQ1 implements Populator<BasicContactInformation, BasicContactInformationData> {
    private SagiaFormatProvider sagiaFormatProvider;
    private InvestorMappingService investorMappingService;

    @Override
    public void populate(BasicContactInformation source, BasicContactInformationData target) throws ConversionException {
        target.setRefID(investorMappingService.getApplicantReferenceId(null));
        target.setFileType(source.getFileType());
        target.setNameFirst(source.getFirstName());
        target.setNameLast(source.getLastName());
        target.setGender("mr".equalsIgnoreCase(source.getTitle()) ? "2" : "1");
        target.setCountry(source.getCountry());
        target.setCcode_Tele(source.getCountryCodeForTelephone());
        target.setTelephone(source.getTelephone());
        target.setCcode_Mobile(source.getCountryCodeForMobileNumber());
        target.setMobile(source.getMobileNumber());
        target.setCcode_Fax(source.getCcode_Fax());
        target.setFax(source.getFax());
        target.setEmail(source.getEmail());
        target.setNationality(source.getCountry());
        target.setCity(source.getCity());
        target.setStreet(source.getAddress());
        target.setPoBox(source.getPoBox());
        target.setPostalCode(source.getPostalCode());
        target.setCommMtd(source.getCommMtd());
        target.setRole(source.getRole());
        target.setMime("");
        target.setReturnProperty(source.getReturnProperty());
        target.setDob(getDateOrNullFrom(source.getDateOfBirth()));
        target.setPassNo(source.getPassportNumber());
        target.setPassExp(getDateOrNullFrom(source.getPassportExpiryDate()));
        target.setEduDet("Master");
        target.setPassIssue(getDateOrNullFrom(source.getPassportIssueDate()));
        target.setCcode_Mobile("1809");
        target.setCcode_Tele("1809");
    }
    
	private LocalDate getDateOrNullFrom(String date) {
		if (date == null) {
			return null;
		}
		return sagiaFormatProvider.getDateFromUIDateStringOrNull(date);
	}

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }

    public void setInvestorMappingService(InvestorMappingService investorMappingService) {
        this.investorMappingService = investorMappingService;
    }
}
