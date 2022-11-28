package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.sap.ibso.eservices.core.enums.ContactPersonRole;
import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCityDAO;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.ContactPersonsData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LicenseContactPersonReversePopulator implements Populator<ContactPersonsData, ContactPersonModel> {

	@Resource
	private SagiaLicenseReversePopulator sagiaLicenseReversePopulator;
	
	@Resource
    private ModelService modelService;

	@Resource
    private SagiaCountryDAO sagiaCountryDAO;
	
	@Resource
    private SagiaCityDAO sagiaCityDAO;

	@Resource
	private SagiaFormatProvider sagiaFormatProvider;
	
	@Override
	public void populate(ContactPersonsData source, ContactPersonModel target) throws ConversionException {

		boolean isUMQDate = false;
		
		  // cahnge requested by MISA team
		  if("OTHER".equalsIgnoreCase(source.getContacts()) &&
		  "1".equals(source.getIdType())) { isUMQDate = true; }
		 
		
		target.setTitle(source.getTitle());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setRole(ContactPersonRole.valueOf(source.getRole()));
		target.setEducation(source.getEducation());
		target.setPassportNumber(source.getPassportNumber());
		target.setFullName(source.getFullName());
		if(null!=source.getContactPersonNationality()) {
			SagiaCountryModel contactPersonNationality = sagiaCountryDAO.getCountryForCode(source.getContactPersonNationality());
			target.setNationality(contactPersonNationality);
		}
		if(source.getDateOfBirth() != null) {
			target.setDateOfBirth(formatToGregorianDate(source.getDateOfBirth(),  isUMQDate));
		}

		if(source.getPassportIssueDate() != null) {
			target.setPassportIssueDate(formatDate(source.getPassportIssueDate()));
		}

		if(source.getPassportExpiryDate() != null) {
			target.setPassportExpiryDate(formatToGregorianDate(source.getPassportExpiryDate(),false));
		}

		SagiaCountryModel sagiaCountryModel = sagiaCountryDAO.getCountryForCode(source.getCountry());
		target.setCountry(sagiaCountryModel);

		target.setContactPersonCity(source.getCity());

		target.setAddress(source.getAddress());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setCountryCodeTelephone(source.getCountryCodeTelephone());
		target.setTelephoneNumber(source.getTelephoneNumber());
		target.setCountryCodeMobile(source.getCountryCodeMobile());
		target.setMobileNumber(source.getMobileNumber());
		target.setEmail(source.getEmail());
		target.setOriginContact(source.getContacts());
		target.setIdType(source.getIdType());
		
	}


	private Date formatDate(String date){
		return date != null ? sagiaFormatProvider.formatUIStrToBackDate(date) : null;
	}
	
	private Date formatToGregorianDate(String date, boolean isUMQDate) {
		if(isUMQDate)
		{
			return sagiaFormatProvider.convertuiUAQStrToBackEndGreDate(date);
		}
		else
		{
			return sagiaFormatProvider.formatUIStrToBackDate(date);
		}
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public SagiaLicenseReversePopulator getSagiaLicenseReversePopulator() {
		return sagiaLicenseReversePopulator;
	}

	public void setSagiaLicenseReversePopulator(SagiaLicenseReversePopulator sagiaLicenseReversePopulator) {
		this.sagiaLicenseReversePopulator = sagiaLicenseReversePopulator;
	}
}
