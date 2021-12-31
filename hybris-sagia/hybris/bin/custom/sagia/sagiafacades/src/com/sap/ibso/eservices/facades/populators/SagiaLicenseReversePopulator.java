package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.facades.data.ContactPersonsData;
import com.sap.ibso.eservices.facades.data.EntityInformationData;
import com.sap.ibso.eservices.facades.data.SagiaLicenseData;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerReversePopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

public class SagiaLicenseReversePopulator implements Populator<SagiaLicenseData, SagiaLicenseModel> {

	@Resource
	private CustomerReversePopulator customerReversePopulator;
	
	@Resource
	private LicenseEntityInformationReversePopulator licenseEntityInformationReversePopulator;
	
	@Resource
	private LicenseContactPersonReversePopulator licenseContactPersonReversePopulator;
	
	@Resource
	private ModelService modelService;
	
	@Override
	public void populate(SagiaLicenseData source, SagiaLicenseModel target) throws ConversionException {

		if (source.getCustomer() != null) {
			CustomerModel customerModel = getModelService().create(CustomerModel.class);
			CustomerData customerData = source.getCustomer();
			getCustomerReverseConverter().populate(customerData, customerModel);
			target.setCustomer(customerModel);
		}
		if (source.getEntityInformation() != null) {
			EntityInformationModel entityInformationModel = getModelService().create(EntityInformationModel.class);
			EntityInformationData entityInformationData = source.getEntityInformation();
			getLicenseEntityInformationReversePopulator().populate(entityInformationData, entityInformationModel);
			target.setEntityInformation(entityInformationModel);
		}
		if (source.getContactPerson() != null) {
			ContactPersonModel contactPersonModel = getModelService().create(ContactPersonModel.class);
			ContactPersonsData contactPersonsData = source.getContactPerson();
			getContactPersonReverseConverter().populate(contactPersonsData, contactPersonModel);
			target.setContactPerson(contactPersonModel);
		}
	
	}

	public CustomerReversePopulator getCustomerReverseConverter() {
		return customerReversePopulator;
	}

	public void setCustomerReverseConverter(CustomerReversePopulator customerReverseConverter) {
		this.customerReversePopulator = customerReverseConverter;
	}

	public LicenseEntityInformationReversePopulator getLicenseEntityInformationReversePopulator() {
		return licenseEntityInformationReversePopulator;
	}

	public void setLicenseEntityInformationReversePopulator(
			LicenseEntityInformationReversePopulator licenseEntityInformationReversePopulator) {
		this.licenseEntityInformationReversePopulator = licenseEntityInformationReversePopulator;
	}

	public LicenseContactPersonReversePopulator getContactPersonReverseConverter() {
		return licenseContactPersonReversePopulator;
	}

	public void setContactPersonReverseConverter(LicenseContactPersonReversePopulator contactPersonReverseConverter) {
		this.licenseContactPersonReversePopulator = contactPersonReverseConverter;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

}
