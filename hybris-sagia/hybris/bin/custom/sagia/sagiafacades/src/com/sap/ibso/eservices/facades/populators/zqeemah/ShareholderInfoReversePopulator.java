package com.sap.ibso.eservices.facades.populators.zqeemah;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderCurrentNationality;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderInfo;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.ShareholderInfoData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

/*
 * Suppress sonar warning (squid:RedundantThrowsDeclarationCheck | "throws" declarations should not be superfluous
 * Marker exception needed even if is a RuntimeException
 */
@SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
public class ShareholderInfoReversePopulator implements Populator<ShareholderInfo, ShareholderInfoData> {
    public static final String DATE_FORMAT_RECEIVED = "MMM d, yyyy";
    private static final String PERSON_SHAREHOLDER = "Person";
    private static final String ORGANIZATION_SHAREHOLDER = "Organization";
    private static final String EXISTING_SHAREHOLDER = "Existing";
    public static final String DATE_FORMAT_RETURNED = "yyyyMMdd";

    private SagiaFormatProvider sagiaFormatProvider;

    @Override
    public void populate(ShareholderInfo source, ShareholderInfoData target) throws ConversionException {
        
		populateCommon(source, target);
		
		if (PERSON_SHAREHOLDER.equalsIgnoreCase(source.getType())) {
			populatePersonSH(source, target);
		} else if (ORGANIZATION_SHAREHOLDER.equalsIgnoreCase(source.getType())) {
			populateOrgSH(source, target);
		} else if (EXISTING_SHAREHOLDER.equalsIgnoreCase(source.getType())) {
			populateExistingSH(source, target);
		}
    }

	private void populateExistingSH(ShareholderInfo source, ShareholderInfoData target) {
		target.setShldrType(source.getType());
		target.setExbpno(source.getEntityNo());
		target.setPercentage(source.getSharesPercentage());
		target.setApprMinistry(source.getFullNameEnglish());
		target.setCompanycountry(source.getParentCompanyCountry());
	}

	private void populateOrgSH(ShareholderInfo source, ShareholderInfoData target) {
		source.getId();
		target.setShldrType(source.getType());
		target.setEntityFname(source.getOrganizationNameEnglish());
		target.setEntityLname(source.getOrganizationNameArabic());
		target.setCurrAssets12(source.getOrganizationLegalStatus());
		target.setCurrLiability14(source.getCompanyRegistrationNumber());
		target.setCurrAssets14(source.getCompanyCapital());
		target.setPercentage(source.getCompanySharesPercentage());
		target.setStock13(source.getCompanySection());
		target.setStock12(source.getCompanyDivision());
		target.setCurrLiability13(source.getParentCompanyName());
		target.setCountry(source.getCompanyCountry());
		target.setCompanycountry(source.getCompanyCountry());
		target.setNetSales12(source.getCompanyCountryOfRegistration());
		target.setCity(source.getCompanyCity());
		target.setPostalCode(source.getCompanyPostalCode());
		target.setPoBox(source.getCompanyPOBox());
		target.setCcodeTele(source.getCompanyCountryCodeForTelephone());
		target.setTelephone(source.getCompanyTelephone());
		target.setCcodeMobile(source.getCompanyCountryCodeForMobile());
		target.setMobile(source.getCompanyMobile());
		target.setEmail(source.getCompanyEmail());
		target.setWebsite(source.getCompanyWebsite());
		target.setMofaNumber(source.getMofaNumber());
		target.setMofaNumberVerified(source.getMofaNumberVerified());
	}

	private void populatePersonSH(ShareholderInfo source, ShareholderInfoData target) {
		target.setShldrType(source.getType());
		target.setGender("mr.".equalsIgnoreCase(source.getTitle()) ? "1" : "2");
		target.setAcademic(source.getAcademicTitle());
		target.setEntityFname(source.getFirstNameArabic());
		target.setEntityLname(source.getLastNameArabic());
		target.setApprMinistry(source.getFullNameEnglish());
		target.setPercentage(source.getSharesPercentage());
		target.setDob(source.getDateOfBirth()!=null?formatDate(source.getDateOfBirth()):null);
		target.setCurrLiability14(source.getPassportNumber());
		target.setCurrLiability13(source.getPassportIssueDate()!= null ? formatDate(source.getPassportIssueDate()):null);
		target.setCompanycountry(source.getPassportExpiryDate()!=null?formatDate(source.getPassportExpiryDate()):null);
		ShareholderCurrentNationality currentNationality = source.getCurrentNationality();
		target.setCurrNationalty(currentNationality != null  ? currentNationality.getVal() : null);
		target.setPrevNationalty(source.getPreviousNationality());
		target.setCountry(source.getCountry());
		target.setCity(source.getCity());
		target.setPostalCode(source.getPostalCode());
		target.setPoBox(source.getPoBox());
		target.setCcodeTele(source.getCountryCodeForTelephone());
		target.setTelephone(source.getTelephone());
		target.setCcodeMobile(source.getCountryCodeForMobile());
		target.setMobile(source.getMobile());
		target.setEmail(source.getEmail());		
		target.setPremiumResident(source.getPremiumResident());
		target.setMofaNumber(source.getMofaNumber());
		target.setMofaNumberVerified(source.getMofaNumberVerified());
	}

	private void populateCommon(ShareholderInfo source, ShareholderInfoData target) {
		target.setRefID(source.getRefID());
		if(source.getSelfDelegate() != null && "true".equalsIgnoreCase(source.getSelfDelegate()))
		{
			target.setSelfDelegate("X");
		}
	}

    private String formatDate(String date) {
        LocalDateTime dateTime = sagiaFormatProvider.getDateTimeFromUIDateString(date);
        return DateTimeFormatter.ofPattern(DATE_FORMAT_RETURNED).format(dateTime);
    }

    public void setSagiaFormatProvider(SagiaFormatProvider sagiaFormatProvider) {
        this.sagiaFormatProvider = sagiaFormatProvider;
    }
}
