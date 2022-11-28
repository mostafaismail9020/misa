package com.sap.ibso.eservices.facades.populators;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.model.SagiaLicenseModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.ContactPersonsData;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;
import com.sap.ibso.eservices.facades.data.SagiaLicenseData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.text.SimpleDateFormat;

public class LicenseContactPersonPopulator implements Populator<ContactPersonModel, ContactPersonsData> {

	@Resource
	private Converter<SagiaLicenseModel, SagiaLicenseData> sagiaLicenseConverter;
	
	@Resource
	private Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter;

	@Resource
	private SagiaFormatProvider sagiaFormatProvider;
	
	private static final String ORIGIN_CONTACT_OTHER = "OTHER" ;
	private static final String ID_TYPE_PASSPORT = "4" ;
	private static final String ID_TYPE_SAUDI_ID = "1" ;

	@Override
	public void populate(ContactPersonModel source, ContactPersonsData target) throws ConversionException {

		boolean isUMQDate = false;
		
		// change requested by MISA team
		
		  if(ORIGIN_CONTACT_OTHER.equalsIgnoreCase(source.getOriginContact()) &&
		  ID_TYPE_SAUDI_ID.equals(source.getIdType())) { isUMQDate = true; }
		 
		target.setTitle(source.getTitle());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setRole(source.getRole().getCode());
		target.setEducation(source.getEducation());
		target.setPassportNumber(source.getPassportNumber());
		target.setFullName(source.getFullName());
		if(null!=source.getNationality()) {
			target.setContactPersonNationality(source.getNationality().getCode());
		}

		if(source.getDateOfBirth() != null){
			if(!isUMQDate) {
				target.setDateOfBirth(source.getDateOfBirth() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getDateOfBirth()) : null);
			}else {
				target.setDateOfBirth(source.getDateOfBirth() != null ?sagiaFormatProvider.convertGregorianDateToUAQStr(source.getDateOfBirth()) : null);
			}
		}
		
		if(source.getPassportIssueDate() != null){
			target.setPassportIssueDate(source.getPassportIssueDate() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getPassportIssueDate()) : null);
		}

		if(source.getPassportExpiryDate() != null){
			
			target.setPassportExpiryDate(source.getPassportExpiryDate() != null ? sagiaFormatProvider.formatBackEndDateToUIStr(source.getPassportExpiryDate()) : null);
			/*
			 * if(!isUMQDate) { target.setPassportExpiryDate(source.getPassportExpiryDate()
			 * != null ?
			 * sagiaFormatProvider.formatBackEndDateToUIStr(source.getPassportExpiryDate())
			 * : null); }else { target.setPassportExpiryDate(source.getPassportExpiryDate()
			 * != null ?sagiaFormatProvider.convertGregorianDateToUAQStr(source.
			 * getPassportExpiryDate()) : null); }
			 */
		
		}
		
		/*
		 * if(StringUtils.isNotBlank(source.getDelegateIdentityType()) &&
		 * "1".equals(source.getDelegateIdentityType())) {
		 * target.setDelegateDateOfBirth(source.getDelegateDateOfBirth() != null ?
		 * sagiaFormatProvider.convertGregorianDateToUAQStr(source.
		 * getDelegateDateOfBirth()) : null);
		 * 
		 * if(source.getIdIssueDate() != null){
		 * target.setIdIssueDate(source.getIdIssueDate() != null ?
		 * sagiaFormatProvider.convertGregorianDateToUAQStr(source.getIdIssueDate()) :
		 * null); }
		 * 
		 * if(source.getIdExpiryDate() != null) {
		 * target.setIdExpiryDate(source.getIdExpiryDate() != null ?
		 * sagiaFormatProvider.convertGregorianDateToUAQStr(source.getIdExpiryDate()) :
		 * null); } } else {
		 * target.setDelegateDateOfBirth(source.getDelegateDateOfBirth() != null ?
		 * sagiaFormatProvider.formatBackEndDateToUIStr(source.getDelegateDateOfBirth())
		 * : null);
		 * 
		 * if(source.getIdIssueDate() != null){
		 * target.setIdIssueDate(source.getIdIssueDate() != null ?
		 * sagiaFormatProvider.formatBackEndDateToUIStr(source.getIdIssueDate()) :
		 * null); }
		 * 
		 * if(source.getIdExpiryDate() != null) {
		 * target.setIdExpiryDate(source.getIdExpiryDate() != null ?
		 * sagiaFormatProvider.formatBackEndDateToUIStr(source.getIdExpiryDate()) :
		 * null); } }
		 */


		/*
		 * if (source.getCountry() != null) {
		 * target.setCountry(getSagiaCountryConverter().convert(source.getCountry())); }
		 */

		target.setCountry(source.getCountry().getCode());
		target.setCountryText(source.getCountry().getName());
		target.setCity(source.getContactPersonCity());
		target.setAddress(source.getAddress());
		target.setPoBox(source.getPoBox());
		target.setPostalCode(source.getPostalCode());
		target.setCountryCodeTelephone(source.getCountryCodeTelephone());
		target.setTelephoneNumber(source.getTelephoneNumber());
		target.setCountryCodeMobile(source.getCountryCodeMobile());
		target.setMobileNumber(source.getMobileNumber());
		target.setEmail(source.getEmail());
		//For old contact records use default value as OTHER and PASSPORT
		target.setContacts(source.getOriginContact()==null?ORIGIN_CONTACT_OTHER:source.getOriginContact());
		target.setIdType(source.getOriginContact()==null?ID_TYPE_PASSPORT:source.getIdType());
	}

	public Converter<SagiaLicenseModel, SagiaLicenseData> getSagiaLicenseConverter() {
		return sagiaLicenseConverter;
	}

	public void setSagiaLicenseConverter(Converter<SagiaLicenseModel, SagiaLicenseData> sagiaLicenseConverter) {
		this.sagiaLicenseConverter = sagiaLicenseConverter;
	}

	public Converter<SagiaCountryModel, SagiaCountryData> getSagiaCountryConverter() {
		return sagiaCountryConverter;
	}

	public void setSagiaCountryConverter(Converter<SagiaCountryModel, SagiaCountryData> sagiaCountryConverter) {
		this.sagiaCountryConverter = sagiaCountryConverter;
	}

}
