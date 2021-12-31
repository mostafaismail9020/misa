package com.sap.ibso.eservices.facades.populators;

import com.sap.ibso.eservices.core.model.ExistingShareholderModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.facades.data.ExistingShareholderData;
import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

public class LicenseExistingShareholderReversePopulator implements Populator<ExistingShareholderData, ExistingShareholderModel> {

	@Resource
	private SagiaMediaReversePopulator sagiaMediaReversePopulator;

	@Resource
	private ModelService modelService;
	
	@Resource
    private SagiaCountryDAO sagiaCountryDAO;


	@Override
	public void populate(ExistingShareholderData source, ExistingShareholderModel target) throws ConversionException {

		target.setShareHolderEntityNumber(source.getShareHolderEntityNumber());
		target.setShareHolderName(source.getShareHolderName());
		target.setProfessionalLicense(source.getProfessionalLicense());

		SagiaCountryModel sagiaCountryModel = sagiaCountryDAO.getCountryForCode(source.getParentCompanyCountry());
		target.setParentCompanyCountry(sagiaCountryModel);
		  
		target.setSharesPercentage(source.getSharesPercentage());
		if (source.getCommercialRegistration() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getCommercialRegistration();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setCommercialRegistration(mediaModel);
		}
		if (source.getLastBudget() != null) {
			MediaModel mediaModel = getModelService().create(MediaModel.class);
			MediaData mediaData = source.getLastBudget();
			getSagiaMediaReversePopulator().populate(mediaData, mediaModel);
			target.setLastBudget(mediaModel);
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

}
