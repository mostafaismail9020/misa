package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.PersonShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.PersonShareholderData;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LicensePersonShareholderReversePopulator
		implements Populator<PersonShareholderData, PersonShareholderModel> {

	@Resource
	private SagiaMediaReversePopulator sagiaMediaReversePopulator;

	@Resource
	private ModelService modelService;

	@Resource
	private SagiaCountryDAO sagiaCountryDAO;

	@Resource
	private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void populate(PersonShareholderData source, PersonShareholderModel target) throws ConversionException {

		target.setShareHolderTitle(source.getShareHolderTitle());
		target.setAcademicTitle(source.getAcademicTitle());
		target.setFirstNameArabic(source.getFirstNameArabic());
		target.setLastNameArabic(source.getLastNameArabic());
		target.setFullName(source.getFullName());
		target.setSharesPercentage(source.getSharesPercentage());
		target.setPassportNumber(source.getPassportNumber()); 
		target.setMofaNumber(source.getMofaNumber());
		target.setIsMofaNumberVerified(source.isMofaNumberVerified());

		if (source.getDateOfBirth() != null) {
			target.setDateOfBirth(formatToGregorianDate(source.getDateOfBirth(), source.getShareHolderIdType()));
		}

		if (source.getPassportIssueDate() != null) {
			target.setPassportIssueDate(sagiaFormatProvider.formatUIStrToBackDate(source.getPassportIssueDate()));
		}                                
		
		if (source.getPassportExpiryDate() != null) {
			target.setPassportExpiryDate(sagiaFormatProvider.formatUIStrToBackDate(source.getPassportExpiryDate()));
		}

		SagiaCountryModel currentNationality = sagiaCountryDAO.getCountryForCode(source.getCurrentNationality());
		target.setCurrentNationality(currentNationality);

		SagiaCountryModel previousNationality = sagiaCountryDAO.getCountryForCode(source.getPreviousNationality());
		target.setPreviousNationality(previousNationality);

		SagiaCountryModel country = sagiaCountryDAO.getCountryForCode(source.getCountry());
		target.setCountry(country);

		target.setPersonShareholderCity(source.getCity());

		target.setPremiumResident(source.getPremiumResident());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setCountryCodeTelephone(source.getCountryCodeTelephone());
		target.setTelephoneNumber(source.getTelephoneNumber());
		target.setCountryCodeMobile(source.getCountryCodeMobile());
		target.setMobileNumber(source.getMobileNumber());
		target.setEmail(source.getEmail());
		target.setProfessionalLicense(source.getProfessionalLicense());

		if (source.getPassportIdCopy() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getPassportIdCopy();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setPassportIdCopy(mediaModel);
		}
		if (source.getOther() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getOther();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setOther(mediaModel);
		}
		if (source.getProfessionalLicenseCertificate() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getProfessionalLicenseCertificate();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setProfessionalLicenseCertificate(mediaModel);
		}
	}

	private Date formatDate(String date) {
		return date != null ? sagiaFormatProvider.formatUIStrToBackDate(date) : null;
	}

	private Date formatToGregorianDate(String date, String idType) {
		// change requested by MISA team
		if (StringUtils.isNotBlank(idType) && "1".equals(idType)) {

			return sagiaFormatProvider.convertuiUAQStrToBackEndGreDate(date);
		} else {

			return sagiaFormatProvider.formatUIStrToBackDate(date);
		}
	}

	public SagiaMediaReversePopulator getSagiaMediaReversePopulator() {
		return sagiaMediaReversePopulator;
	}

	public void setSagiaMediaReversePopulator(SagiaMediaReversePopulator sagiaMediaReversePopulator) {
		this.sagiaMediaReversePopulator = sagiaMediaReversePopulator;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

}
