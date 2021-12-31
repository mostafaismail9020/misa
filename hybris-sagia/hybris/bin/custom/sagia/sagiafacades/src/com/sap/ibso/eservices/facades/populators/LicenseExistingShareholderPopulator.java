package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.ExistingShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.facades.data.ExistingShareholderData;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class LicenseExistingShareholderPopulator implements Populator<ExistingShareholderModel, ExistingShareholderData> {

	@Resource
	private Converter<MediaModel, MediaData> mediaConverter;

	@Resource
	private Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter;

	@Override
	public void populate(ExistingShareholderModel source, ExistingShareholderData target) throws ConversionException {
		target.setCode(source.getCode());
		target.setShareHolderEntityNumber(source.getShareHolderEntityNumber());
		target.setEntityNumber(source.getShareHolderEntityNumber());
		target.setShareHolderName(source.getShareHolderName());
		target.setMainName(source.getShareHolderName());

		target.setNationality(source.getParentCompanyCountry().getName());
		target.setParentCompanyCountry(source.getParentCompanyCountry().getCode());
		target.setSharesPercentage(source.getSharesPercentage());
		target.setProfessionalLicense(source.isProfessionalLicense());
		if(source.getProfessionalLicenseCertificate() != null) {
			target.setProfessionalLicenseCertificate(getMediaConverter().convert(source.getProfessionalLicenseCertificate()));
		}
		if (source.getCommercialRegistration() != null) {
			target.setCommercialRegistration(getMediaConverter().convert(source.getCommercialRegistration()));
		}
		if (source.getLastBudget() != null) {
			target.setLastBudget(getMediaConverter().convert(source.getLastBudget()));
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
