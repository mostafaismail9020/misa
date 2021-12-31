package com.sap.ibso.eservices.facades.populators.odata;


import java.util.Date;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.DelegateInfoModel;
import com.sap.ibso.eservices.core.model.ExistingShareholderModel;
import com.sap.ibso.eservices.core.model.OrganizationShareholderModel;
import com.sap.ibso.eservices.core.model.PersonShareholderModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.odata.ShareholderData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ShareHolderODataPopulator implements Populator<ShareHolderModel, ShareholderData> {

	
    private static final String PERSON_SHAREHOLDER = "Person";
    private static final String ORGANIZATION_SHAREHOLDER = "Organization";
    private static final String EXISTING_SHAREHOLDER = "Existing";
    private static final String GENDER_FEMALE = "FEMALE";
    private static final String SHAREHOLDER_ID_TYPE_PASSPORT = "4";
    private static final String COUNTRY_SA = "SA";
    
	@Resource
	private SagiaFormatProvider  sagiaFormatProvider;
    
    
	@Override
	public void populate(ShareHolderModel source, ShareholderData target) throws ConversionException {
		
		    target.setShhldcode(source.getCode());
		    target.setGuid(source.getLicense().getGuid());
		    target.setRefid(source.getLicense().getApplicantReferenceID());
		    target.setObjectid(source.getLicense().getApplicationServiceRequestID());		    
		    target.setSharesPercentage(source.getSharesPercentage());
		    target.setShareHolderType(source.getShareHolderType());
		    DelegateInfoModel delegate = source.getDelegateInfo();
		    boolean hasDelegate = false ;
		    if(ORGANIZATION_SHAREHOLDER.equalsIgnoreCase(source.getShareHolderType()) && ( delegate != null &&  delegate.isDelegate())  ) {
		    	hasDelegate = true ;
		    }else if (PERSON_SHAREHOLDER.equalsIgnoreCase(source.getShareHolderType()) && ( delegate != null &&  !delegate.isDelegateYourself())  ) {
		    	hasDelegate = true ;
		    }
		    
		    
		    if(hasDelegate)
		    {
		    	target.setDelegate(odataBoolean(true));
		    	target.setSelfDelegate(odataBoolean(false));
		    	
		    		target.setIsNICVerified(odataBoolean(delegate.isNicVerified()));
		    		target.setDelIdentityType(delegate.getDelegateIdentityType());
				    target.setDelIdentityNumber(delegate.getDelegateIdentityNumber());
				    target.setDelDob(odataDate(delegate.getDelegateDateOfBirth()));
				    target.setDelFirstName(delegate.getDelegateFirstNameArabic());
				    target.setDelLastName(delegate.getDelegateLastNameArabic());
				    target.setDelFullName(delegate.getDelegateFullName());
				    String gender= null;
				    if(delegate.getGender()!=null) {
				    	gender = GENDER_FEMALE.equalsIgnoreCase(delegate.getGender().getCode())?"1":"2";
				    }
				    target.setDelGender(gender);	      	
				    target.setDelIdIssueDt(odataDate(delegate.getIdIssueDate()));
				    target.setDelIdExpiryDt(odataDate(delegate.getIdExpiryDate()));
				    target.setDelCcode_tele(delegate.getDelegateCountryCodeTel());
				    target.setDelTelephoneNo(delegate.getDelegateTelephoneNumber());
				    target.setDelCcode_Mobile(delegate.getDelegateCountryCodeMobile());
				    target.setDelMobileNo(delegate.getDelegateMobileNumber());
				    target.setDelEmail(delegate.getDelegateEmail());
				    target.setDelCountry(delegate.getDelegateCountry()==null?null:delegate.getDelegateCountry().getCode());
				    target.setDelNationality(delegate.getDelegateNationality()==null?null:delegate.getDelegateNationality().getCode());	    
				    target.setDelPostalCode(delegate.getDelegatePostalCode());
				    target.setDelPoBox(delegate.getDelegatePoBox());
		    	
		    }
		    else
		    {
		    	target.setDelegate(odataBoolean(false));
		    }
		    
		    if (PERSON_SHAREHOLDER.equalsIgnoreCase(source.getShareHolderType())) {
				populatePersonSH((PersonShareholderModel) source, target);
			} else if (ORGANIZATION_SHAREHOLDER.equalsIgnoreCase(source.getShareHolderType())) {
				populateOrgSH((OrganizationShareholderModel) source, target);
			} else if (EXISTING_SHAREHOLDER.equalsIgnoreCase(source.getShareHolderType())) {
				populateExistingSH((ExistingShareholderModel) source, target);
			}
		    //testing
		    target.setFileType("COMM");
	}

	private String odataDate(Date value) {
		return sagiaFormatProvider.formatBackEndDateToBackEndStr(value);
	}

	private String odataBoolean(Boolean value) {
		return sagiaFormatProvider.formatBooleanForODATA(value);
	}
	
	private void populateExistingSH(ExistingShareholderModel source, ShareholderData target) {

		target.setEntityNo(source.getShareHolderEntityNumber());
		target.setShareHolderName(source.getShareHolderName()); // TBD
		target.setParCompCntry(source.getParentCompanyCountry() != null ? source.getParentCompanyCountry().getCode():"");
		target.setExtProfessionalLicense(odataBoolean(source.isProfessionalLicense()));
	}
	
	private void populateOrgSH(OrganizationShareholderModel source, ShareholderData target) {

		target.setOrgName(source.getOrganizationName());
		target.setOrgNameArabic(source.getOrganizationNameArabic());
		target.setLegalStatus(source.getLegalStatus().getCode());
		target.setMncCompany(odataBoolean(source.isMultinationalCompany()));
		target.setCompRegNo(source.getCompanyRegNumber());
		target.setCapital(source.getCapital());
		target.setSection(source.getSection());
		target.setDivision(source.getDivision());
		target.setParCompName(source.getParentCompanyName());
		target.setParCompCountry(source.getParentCompanyCountry().getCode());
		target.setCompCountry(source.getCompanyCountry().getCode());		
		target.setCountryofReg(source.getCountryOfReg().getCode());
		target.setCity(source.getOrganizationShareholderCity());
		target.setStreet(source.getAddress());		
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setCcode_Tele(source.getCountryCodeTelephone());
		target.setTelephoneNo(source.getTelephoneNumber());
		target.setCcode_Mobile(source.getCountryCodeMobile());
		target.setMobileNo(source.getMobileNumber());
		target.setEmail(source.getEmail());
		target.setWebsite(source.getWebsite());
		target.setOrgProfessionalLicense(odataBoolean(source.isProfessionalLicense()));
		target.setMofaNumber(source.getMofaNumber());
		target.setMofaNumberVerified(odataBoolean(source.isIsMofaNumberVerified()));
		
		Boolean isMCVerified = false ;
		if (COUNTRY_SA.equalsIgnoreCase(source.getCompanyCountry().getCode()) ) {
			isMCVerified = true;
		}
		target.setIsMCVerified(odataBoolean(isMCVerified));
	}
	
	private void populatePersonSH(PersonShareholderModel source, ShareholderData target) {
		target.setTitle("Mr.".equalsIgnoreCase(source.getShareHolderTitle()) ? "0002" : "0001"); 
		target.setAcademicTitle(source.getAcademicTitle());
		target.setFirstName(source.getFirstNameArabic());
		target.setLastName(source.getLastNameArabic());
		target.setFullName(source.getFullName());
		target.setDob(odataDate(source.getDateOfBirth()));
		target.setPassportNo(source.getPassportNumber());
		target.setPassportIssueDate(odataDate(source.getPassportIssueDate()));
		target.setPassportExpDate(odataDate(source.getPassportExpiryDate()));
		target.setCurrNationality(source.getCurrentNationality().getCode());
		target.setPrevNationality(source.getPreviousNationality().getCode());
		target.setCountry(source.getCountry().getCode());
		target.setPremiumResident(odataBoolean(source.isPremiumResident()));
		target.setShCity(source.getPersonShareholderCity());
		target.setShPoBox(source.getPoBox());
		target.setShPostalCode(source.getPostalCode());
		target.setShCcode_Tele(source.getCountryCodeTelephone());
		target.setShTelephoneNo(source.getTelephoneNumber());
		target.setShCcode_Mobile(source.getCountryCodeMobile());
		target.setShMobileNo(source.getMobileNumber());
		target.setShEmail(source.getEmail());
		target.setPerProfessionalLicense(odataBoolean(source.isProfessionalLicense()));
		Boolean isPersonNICVerified = false ;
		if (source.getShareHolderIdType()!=null && !SHAREHOLDER_ID_TYPE_PASSPORT.equals(source.getShareHolderIdType())) {
			isPersonNICVerified = true;
		}
		target.setIsPersonNICVerified(odataBoolean(isPersonNICVerified));
		target.setPerIdentityType(source.getShareHolderIdType());
		target.setMofaNumber(source.getMofaNumber());
		target.setMofaNumberVerified(odataBoolean(source.isIsMofaNumberVerified()));
		
	}
}
