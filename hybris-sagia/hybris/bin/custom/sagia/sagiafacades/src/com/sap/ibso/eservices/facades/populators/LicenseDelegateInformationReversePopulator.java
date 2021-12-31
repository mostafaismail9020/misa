package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.DelegateInfoModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.ShareHolderModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.DelegateInformationData;
import com.sap.ibso.eservices.facades.data.ShareHoldersData;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.enums.Gender;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LicenseDelegateInformationReversePopulator implements Populator<DelegateInformationData, DelegateInfoModel> {

	@Resource
	private SagiaMediaReversePopulator sagiaMediaReversePopulator;
	
	@Resource
	private LicenseShareholderReversePopulator licenseShareholderReversePopulator;

	@Resource
	private ModelService modelService;

	@Resource
	private SagiaCountryDAO sagiaCountryDAO;

	@Resource
	private SagiaFormatProvider sagiaFormatProvider;
	
	@Override
	public void populate(DelegateInformationData source, DelegateInfoModel target) throws ConversionException {

		boolean isPersonSH = target.getShareholder().getShareHolderType().equalsIgnoreCase("Person");
		
		target.setDelegate(source.isDelegate());
		if(isPersonSH)
		{
			target.setDelegateYourself(source.isDelegateYourself());
		}
		
		if((isPersonSH  && !source.isDelegateYourself()) || (!isPersonSH && source.isDelegate()))
		{
			target.setDelegateIdentityType(source.getDelegateIdentityType());
			target.setDelegateIdentityNumber(source.getDelegateIdentityNumber());

			if(StringUtils.isNotEmpty(source.getDelegateDateOfBirth())){
				target.setDelegateDateOfBirth(formatToGregorianDate(source.getDelegateDateOfBirth(), source.getDelegateIdentityType()));
			}

			target.setDelegateFirstNameArabic(source.getDelegateFirstNameArabic());
			target.setDelegateLastNameArabic(source.getDelegateLastNameArabic());
			target.setDelegateFullName(source.getDelegateFullName());
			if (!StringUtils.isEmpty(source.getGender())) {
				target.setGender(Gender.valueOf(source.getGender().toUpperCase()));
			}

			if(StringUtils.isNotEmpty(source.getIdIssueDate())) {
				target.setIdIssueDate(formatToGregorianDate(source.getIdIssueDate(),"2"));
			}

			if(StringUtils.isNotEmpty(source.getIdExpiryDate())) {
				target.setIdExpiryDate(formatToGregorianDate(source.getIdExpiryDate(), "2"));
			}

			target.setDelegateCountryCodeTel(source.getDelegateCountryCodeTel());
			target.setDelegateTelephoneNumber(source.getDelegateTelephoneNumber());
			target.setDelegateCountryCodeMobile(source.getDelegateCountryCodeMobile());
			target.setDelegateMobileNumber(source.getDelegateMobileNumber());
			target.setDelegateEmail(source.getDelegateEmail());

			SagiaCountryModel delegateNationality = sagiaCountryDAO.getCountryForCode(source.getDelegateNationality());
			target.setDelegateNationality(delegateNationality);

			SagiaCountryModel delegateCountry = sagiaCountryDAO.getCountryForCode(source.getDelegateCountry());
			target.setDelegateCountry(delegateCountry);

			target.setDelegatePoBox(source.getDelegatePoBox());
			target.setDelegatePostalCode(source.getDelegatePostalCode());
			target.setNicVerified(source.isNicVerified());

			if (source.getAuthorisationLetter() != null) {
				MediaModel mediaModel = getModelService().create(MediaModel.class);
				MediaData mediaData = source.getAuthorisationLetter();
				getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
				target.setAuthorisationLetter(mediaModel);
			}
			if (!source.isNicVerified() && source.getSaudiIdCopy() != null) {
				MediaModel mediaModel = getModelService().create(MediaModel.class);
				MediaData mediaData = source.getSaudiIdCopy();
				getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
				target.setSaudiIdCopy(mediaModel);
			}
		}
	}


	private Date formatToGregorianDate(String date, String delegateType) {
		
		  if(StringUtils.isNotBlank(delegateType) && "1".equals(delegateType)) { return
		  sagiaFormatProvider.convertuiUAQStrToBackEndGreDate(date); } else {
		 
			return sagiaFormatProvider.formatUIStrToBackDate(date);
		}   // change requested by MISA team
	}
	
	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public SagiaMediaReversePopulator getSagiaMediaReversePopulator() {
		return sagiaMediaReversePopulator;
	}

	public void setSagiaMediaReversePopulator(SagiaMediaReversePopulator sagiaMediaReversePopulator) {
		this.sagiaMediaReversePopulator = sagiaMediaReversePopulator;
	}

	public LicenseShareholderReversePopulator getLicenseShareholderReversePopulator() {
		return licenseShareholderReversePopulator;
	}

	public void setLicenseShareholderReversePopulator(
			LicenseShareholderReversePopulator licenseShareholderReversePopulator) {
		this.licenseShareholderReversePopulator = licenseShareholderReversePopulator;
	}

}
