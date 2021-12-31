package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.OrganizationShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.facades.data.OrganizationShareholderData;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class LicenseOrganizationShareholderPopulator implements Populator<OrganizationShareholderModel, OrganizationShareholderData> {

	@Resource
	private Converter<MediaModel, MediaData> mediaConverter;
	
	@Resource
	private Converter<SagiaCountryModel,SagiaCountryData> sagiaCountryConverter;

	@Override
	public void populate(OrganizationShareholderModel source, OrganizationShareholderData target)
			throws ConversionException {
		target.setCode(source.getCode());
		target.setMainName(source.getOrganizationName());
		target.setOrganizationName(source.getOrganizationName());
		target.setOrganizationNameArabic(source.getOrganizationNameArabic());

		target.setMultinationalCompany(source.isMultinationalCompany()?"1":"2");
		target.setCompanyRegNumber(source.getCompanyRegNumber());
		target.setCapital(source.getCapital());
		target.setSharesPercentage(source.getSharesPercentage());
		target.setSection(source.getSection());
		target.setDivision(source.getDivision());
		target.setParentCompanyName(source.getParentCompanyName());

		target.setParentCompanyCountry(source.getParentCompanyCountry().getCode());
		target.setCountryOfReg(source.getCountryOfReg().getCode());

		target.setCity(source.getOrganizationShareholderCity());

		if(source.getLegalStatus() != null){
			target.setLegalStatus(source.getLegalStatus().getCode());
            target.setMainLegalStatus(source.getLegalStatus().getName());
		}

		if (source.getCompanyCountry() != null) {
			target.setNationality(source.getCompanyCountry().getName());
			target.setCompanyCountry(source.getCompanyCountry().getCode());
		}

		target.setAddress(source.getAddress().toString());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setCountryCodeTelephone(source.getCountryCodeTelephone());
		target.setTelephoneNumber(source.getTelephoneNumber());
		target.setCountryCodeMobile(source.getCountryCodeMobile());
		target.setMobileNumber(source.getMobileNumber());
		target.setEmail(source.getEmail());
		target.setWebsite(source.getWebsite());
		target.setProfessionalLicense(source.isProfessionalLicense());
		target.setMofaNumber(source.getMofaNumber());
		target.setMofaNumberVerified(source.isIsMofaNumberVerified());
		
		if(source.getProfessionalLicenseCertificate() != null) {
			target.setProfessionalLicenseCertificate(getMediaConverter().convert(source.getProfessionalLicenseCertificate()));
		}
		if (source.getCommercialRegCopy() != null) {
        	target.setCommercialRegCopy(getMediaConverter().convert(source.getCommercialRegCopy()));
        }
        if (source.getLastYearFinStatement() != null) {
        	target.setLastYearFinStatement(getMediaConverter().convert(source.getLastYearFinStatement()));
        }
		if (source.getCompanyMemoAssociation() != null) {
			target.setCompanyMemoAssociation(getMediaConverter().convert(source.getCompanyMemoAssociation()));
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
