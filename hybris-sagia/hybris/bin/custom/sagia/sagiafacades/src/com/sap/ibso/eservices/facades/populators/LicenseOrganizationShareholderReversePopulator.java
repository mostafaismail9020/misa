package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.*;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCityDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaLegalStatusDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaLegalStatusService;
import com.sap.ibso.eservices.facades.data.OrganizationShareholderData;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

public class LicenseOrganizationShareholderReversePopulator implements Populator<OrganizationShareholderData, OrganizationShareholderModel> {

	@Resource
	private SagiaMediaReversePopulator sagiaMediaReversePopulator;

	@Resource
	private ModelService modelService;
    
    @Resource
    SagiaLegalStatusService sagiaLegalStatusService;
    
	@Resource
    private SagiaCountryDAO sagiaCountryDAO;
	
	@Resource
    private SagiaCityDAO sagiaCityDAO;

	@Resource
	private SagiaLegalStatusDAO sagiaLegalStatusDAO;
	
	@Override
	public void populate(OrganizationShareholderData source, OrganizationShareholderModel target)
			throws ConversionException {

		target.setOrganizationName(source.getOrganizationName());
		target.setOrganizationNameArabic(source.getOrganizationNameArabic());
		target.setLegalStatus(sagiaLegalStatusService.getLegalStatusForCode(source.getLegalStatus()));
		target.setMultinationalCompany(source.getMultinationalCompany() != null && source.getMultinationalCompany().equals("1")?true:false);
		target.setCompanyRegNumber(source.getCompanyRegNumber());
		target.setCapital(source.getCapital());
		target.setSharesPercentage(source.getSharesPercentage());
		target.setSection(source.getSection());
		target.setDivision(source.getDivision());
		target.setParentCompanyName(source.getParentCompanyName());

		SagiaCountryModel companyCountry = sagiaCountryDAO.getCountryForCode(source.getCompanyCountry());
		target.setCompanyCountry(companyCountry);

		SagiaCountryModel parentCompanyCountry = sagiaCountryDAO.getCountryForCode(source.getParentCompanyCountry());
		target.setParentCompanyCountry(parentCompanyCountry);

		SagiaCountryModel countryOfReg = sagiaCountryDAO.getCountryForCode(source.getCountryOfReg());
		target.setCountryOfReg(countryOfReg);

		target.setOrganizationShareholderCity(source.getCity());

		SagiaLegalStatusModel legalStatus = sagiaLegalStatusDAO.getLegalStatusForCode(source.getLegalStatus());
		target.setLegalStatus(legalStatus);

		target.setAddress(source.getAddress());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setCountryCodeTelephone(source.getCountryCodeTelephone());
		target.setTelephoneNumber(source.getTelephoneNumber());
		target.setCountryCodeMobile(source.getCountryCodeMobile());
		target.setMobileNumber(source.getMobileNumber());
		target.setEmail(source.getEmail());
		target.setWebsite(source.getWebsite());
		target.setProfessionalLicense(source.getProfessionalLicense());
		target.setMofaNumber(source.getMofaNumber());
		target.setIsMofaNumberVerified(source.isMofaNumberVerified());
		if (source.getCommercialRegCopy() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getCommercialRegCopy();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setCommercialRegCopy(mediaModel);
        }
        if (source.getLastYearFinStatement() != null) {
        	MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getLastYearFinStatement();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setLastYearFinStatement(mediaModel);
        }
		if (source.getCompanyMemoAssociation() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getCompanyMemoAssociation();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setCompanyMemoAssociation(mediaModel);
		}
		if (source.getProfessionalLicenseCertificate() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getProfessionalLicenseCertificate();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setProfessionalLicenseCertificate(mediaModel);
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

	public SagiaLegalStatusService getSagiaLegalStatusService() {
		return sagiaLegalStatusService;
	}

	public void setSagiaLegalStatusService(SagiaLegalStatusService sagiaLegalStatusService) {
		this.sagiaLegalStatusService = sagiaLegalStatusService;
	}
	
}
