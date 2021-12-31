package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.EntityInformationModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.facades.data.ContactPersonsData;
import com.sap.ibso.eservices.facades.data.EntityInformationData;
import com.sap.ibso.eservices.facades.data.SagiaLicenseData;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

public class SagiaLicensePopulator implements Populator<SagiaLicenseModel, SagiaLicenseData> {

	@Resource
	private Converter<CustomerModel, CustomerData> customerConverter;
	
	@Resource
	private Converter<EntityInformationModel, EntityInformationData> licenseEntityInformationConverter;
	
	@Resource
	private Converter<ContactPersonModel, ContactPersonsData> licenseContactPersonConverter;
	
	public void populate(SagiaLicenseModel source, SagiaLicenseData target) throws ConversionException {

		if (source.getCustomer() != null) {
			target.setCustomer(getCustomerConverter().convert(source.getCustomer()));
		}
		if (source.getEntityInformation() != null) {
			target.setEntityInformation(getEntityInfoConverter().convert(source.getEntityInformation()));
		}
		if (source.getContactPerson() != null) {
			target.setContactPerson(getContactPersonConverter().convert(source.getContactPerson()));
		}
	}

	public Converter<CustomerModel, CustomerData> getCustomerConverter() {
		return customerConverter;
	}

	public void setCustomerConverter(Converter<CustomerModel, CustomerData> customerConverter) {
		this.customerConverter = customerConverter;
	}

	public Converter<EntityInformationModel, EntityInformationData> getEntityInfoConverter() {
		return licenseEntityInformationConverter;
	}

	public void setEntityInfoConverter(Converter<EntityInformationModel, EntityInformationData> entityInfoConverter) {
		this.licenseEntityInformationConverter = entityInfoConverter;
	}

	public Converter<ContactPersonModel, ContactPersonsData> getContactPersonConverter() {
		return licenseContactPersonConverter;
	}

	public void setContactPersonConverter(Converter<ContactPersonModel, ContactPersonsData> contactPersonConverter) {
		this.licenseContactPersonConverter = contactPersonConverter;
	}

}
