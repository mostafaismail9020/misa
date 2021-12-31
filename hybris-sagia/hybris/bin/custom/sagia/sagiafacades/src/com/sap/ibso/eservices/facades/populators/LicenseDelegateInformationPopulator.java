package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.DelegateInfoModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.DelegateInformationData;
import com.sap.ibso.eservices.facades.data.ShareHoldersData;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;

public class LicenseDelegateInformationPopulator implements Populator<DelegateInfoModel, DelegateInformationData> {

	@Resource
	private Converter<MediaModel, MediaData> mediaConverter;
	
	@Resource
	private Converter<ShareHolderModel, ShareHoldersData> licenseShareholderConverter;

	@Resource
	private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void populate(DelegateInfoModel source, DelegateInformationData target) throws ConversionException {

		if (source.getShareholder() != null) {
			target.setShareholder(getShareholderConverter().convert(source.getShareholder()));
		}
		target.setDelegate(source.isDelegate());
		target.setDelegateYourself(source.isDelegateYourself());
		target.setDelegateIdentityType(source.getDelegateIdentityType());
		target.setDelegateIdentityNumber(source.getDelegateIdentityNumber());

		if(StringUtils.isNotBlank(source.getDelegateIdentityType()) && "1".equals(source.getDelegateIdentityType()))
		{
			target.setDelegateDateOfBirth(source.getDelegateDateOfBirth() != null
					? sagiaFormatProvider.convertGregorianDateToUAQStr(source.getDelegateDateOfBirth())
					: null);

			if (source.getIdIssueDate() != null) {
				target.setIdIssueDate(source.getIdIssueDate() != null
						? sagiaFormatProvider.formatBackEndDateToUIStr(source.getIdIssueDate())
						: null);
			}

			if (source.getIdExpiryDate() != null) {
				target.setIdExpiryDate(source.getIdExpiryDate() != null
						? sagiaFormatProvider.formatBackEndDateToUIStr(source.getIdExpiryDate())
						: null);
			}
		}
		
		  else {
		   
			target.setDelegateDateOfBirth(source.getDelegateDateOfBirth() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getDelegateDateOfBirth()) : null);

			if(source.getIdIssueDate() != null){
				target.setIdIssueDate(source.getIdIssueDate() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getIdIssueDate()) : null);
			}

			if(source.getIdExpiryDate() != null) {
				target.setIdExpiryDate(source.getIdExpiryDate() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getIdExpiryDate()) : null);
			}
		  }

		target.setDelegateFirstNameArabic(source.getDelegateFirstNameArabic());
		target.setDelegateLastNameArabic(source.getDelegateLastNameArabic());
		target.setDelegateFullName(source.getDelegateFullName());

		if(source.getGender() != null){
			target.setGender(source.getGender().getCode());
		}

		target.setDelegateCountryCodeTel(source.getDelegateCountryCodeTel());
		target.setDelegateTelephoneNumber(source.getDelegateTelephoneNumber());
		target.setDelegateCountryCodeMobile(source.getDelegateCountryCodeMobile());
		target.setDelegateMobileNumber(source.getDelegateMobileNumber());
		target.setDelegateEmail(source.getDelegateEmail());

		if(source.getDelegateNationality() != null){
			target.setDelegateNationality(source.getDelegateNationality().getCode());
		}

		if(source.getDelegateCountry() != null){
			target.setDelegateCountry(source.getDelegateCountry().getCode());
		}

		target.setDelegatePostalCode(source.getDelegatePostalCode());
		target.setDelegatePoBox(source.getDelegatePoBox());
		target.setNicVerified(source.isNicVerified());

		if (source.getAuthorisationLetter() != null) {
			target.setAuthorisationLetter(getMediaConverter().convert(source.getAuthorisationLetter()));
		}
		if (source.getSaudiIdCopy() != null) {
			target.setSaudiIdCopy(getMediaConverter().convert(source.getSaudiIdCopy()));
		}
	}

	public Converter<MediaModel, MediaData> getMediaConverter() {
		return mediaConverter;
	}

	public void setMediaConverter(Converter<MediaModel, MediaData> mediaConverter) {
		this.mediaConverter = mediaConverter;
	}

	public Converter<ShareHolderModel, ShareHoldersData> getShareholderConverter() {
		return licenseShareholderConverter;
	}

	public void setShareholderConverter(Converter<ShareHolderModel, ShareHoldersData> shareholderConverter) {
		this.licenseShareholderConverter = shareholderConverter;
	}

}
