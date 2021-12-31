package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.PersonShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.PersonShareholderData;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.text.SimpleDateFormat;

public class LicensePersonShareholderPopulator implements Populator<PersonShareholderModel, PersonShareholderData> {

	@Resource
	private Converter<MediaModel, MediaData> mediaConverter;
	
	@Resource
    private Converter<SagiaCountryModel,SagiaCountryData> sagiaCountryConverter;

	@Resource
	private SagiaFormatProvider sagiaFormatProvider;
    
	@Override
	public void populate(PersonShareholderModel source, PersonShareholderData target) throws ConversionException {

		target.setShareHolderIdType(source.getShareHolderIdType());
		target.setCode(source.getCode());
		target.setMainName(source.getFullName());
		target.setShareHolderTitle(source.getShareHolderTitle());
		target.setAcademicTitle(source.getAcademicTitle());
		target.setFirstNameArabic(source.getFirstNameArabic());
		target.setLastNameArabic(source.getLastNameArabic());
		target.setFullName(source.getFullName());
		target.setSharesPercentage(source.getSharesPercentage());
		target.setPassportNumber(source.getPassportNumber());
		target.setMofaNumber(source.getMofaNumber());
		target.setMofaNumberVerified(source.isIsMofaNumberVerified());
		
		
		if(source.getDateOfBirth() != null){
			if("1".equals(source.getShareHolderIdType() )) {
				target.setDateOfBirth(source.getDateOfBirth() != null ? sagiaFormatProvider.convertGregorianDateToUAQStr(source.getDateOfBirth()) : null);
			}else {
				target.setDateOfBirth(source.getDateOfBirth() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getDateOfBirth()) : null);
			}  // cahnge requested by MISA team
			
		}

		if(source.getPassportIssueDate() != null){
		    target.setPassportIssueDate(source.getPassportIssueDate() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getPassportIssueDate()) : null);
		}

		if(source.getPassportExpiryDate() != null){
			  // cahnge requested by MISA team
			/*
			 * if("1".equals(source.getShareHolderIdType() )) {
			 * target.setPassportExpiryDate(source.getPassportExpiryDate() != null ?
			 * sagiaFormatProvider.convertGregorianDateToUAQStr(source.getPassportExpiryDate
			 * ()) : null); }else {
			 */
				target.setPassportExpiryDate(source.getPassportExpiryDate() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getPassportExpiryDate()) : null);
		//	}
			
		}


		target.setNationality(source.getCurrentNationality().getName());
    	target.setCurrentNationality(source.getCurrentNationality().getCode());
    	target.setPreviousNationality(source.getPreviousNationality().getCode());
      	target.setCountry(source.getCountry().getCode());
		target.setPremiumResident(source.isPremiumResident());

		target.setCity(source.getPersonShareholderCity());

		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setCountryCodeTelephone(source.getCountryCodeTelephone());
		target.setTelephoneNumber(source.getTelephoneNumber());
		target.setCountryCodeMobile(source.getCountryCodeMobile());
		target.setMobileNumber(source.getMobileNumber());
		target.setEmail(source.getEmail());
		target.setProfessionalLicense(source.isProfessionalLicense());
		if(source.getProfessionalLicenseCertificate() != null) {
			target.setProfessionalLicenseCertificate(getMediaConverter().convert(source.getProfessionalLicenseCertificate()));
		}
		if (source.getPassportIdCopy() != null) {
        	target.setPassportIdCopy(getMediaConverter().convert(source.getPassportIdCopy()));
        }
        if (source.getOther() != null) {
        	target.setOther(getMediaConverter().convert(source.getOther()));
        }
	}

	public Converter<MediaModel, MediaData> getMediaConverter() {
		return mediaConverter;
	}

	public void setMediaConverter(Converter<MediaModel, MediaData> mediaConverter) {
		this.mediaConverter = mediaConverter;
	}

	public Converter<SagiaCountryModel, SagiaCountryData> getSagiaCountryConverter() {
		return sagiaCountryConverter;
	}

	public void setSagiaCountryConverter(Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter) {
		this.sagiaCountryConverter = sagiaCountryConverter;
	}

}
