package com.sap.ibso.eservices.facades.populators.odata;

import java.util.Date;

import javax.annotation.Resource;

import com.sap.ibso.eservices.core.model.ContactPersonModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.odata.BasicContactInformationData;
import com.sap.ibso.eservices.sagiaservices.investor.InvestorMappingService;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class ContactPersonODataPopulator implements Populator<ContactPersonModel, BasicContactInformationData> {

	@Resource
	InvestorMappingService investorMappingService ;
	
	@Resource
	private SagiaFormatProvider  sagiaFormatProvider;
	
	@Override
	public void populate(ContactPersonModel source, BasicContactInformationData target) throws ConversionException {

		    target.setRefid(source.getLicense().getApplicantReferenceID());
		    target.setObjectid(source.getLicense().getApplicationServiceRequestID());
		    target.setGuid(source.getLicense().getGuid());
		    target.setTitle("Mr".equalsIgnoreCase(source.getTitle()) ? "2" : "1");	    
	        target.setFirstName(source.getFirstName());
	        target.setLastName(source.getLastName());        
	        target.setRole(source.getRole().getCode());
	        target.setEducation(getEducationCode(source.getEducation()));
	        target.setDob(odataDate(source.getDateOfBirth()));
	        target.setPassportNo(source.getPassportNumber());
	        target.setPassportIssueDt(odataDate(source.getPassportIssueDate()));
	        target.setPassportExpDt(odataDate(source.getPassportExpiryDate()));
	        target.setCountry(source.getCountry().getCode());
	        target.setCity(source.getContactPersonCity());
	        target.setStreet(source.getAddress());
	        target.setPoBox(source.getPoBox().toString());
	        target.setPostalCode(source.getPostalCode());	        
	        target.setCcode_Tele(source.getCountryCodeTelephone());
	        target.setTelephoneNo(source.getTelephoneNumber());
	        target.setCcode_Mobile(source.getCountryCodeMobile());
	        target.setMobileNo(source.getMobileNumber());
	        target.setEmail(source.getEmail());
	}
	
	private String odataDate(Date value) {
		return sagiaFormatProvider.formatBackEndDateToBackEndStr(value);
	}
	
	private String getEducationCode(String education) {
		if ("High School".equalsIgnoreCase(education)) {
			return "0001";
		}else if ("Diploma".equalsIgnoreCase(education))  {
			return "0002";
		}else if ("BS".equalsIgnoreCase(education))  {
			return "0003";
		}else if ("Master".equalsIgnoreCase(education))  {
			return "0004";
		}else if ("PhD".equalsIgnoreCase(education))  {
			return "0005";
		}else if ("Professor".equalsIgnoreCase(education))  {
			return "0006";
		}
		return "";
	}
	
}
