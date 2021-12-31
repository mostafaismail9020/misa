package com.sap.ibso.eservices.facades.populators.zqeemah;

import java.time.ZoneId;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.sap.ibso.eservices.core.model.DelegateInfoModel;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.sagiaservices.data.zqeemah.DelegateInfoData;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class DelegateInfoODataPopulator implements Populator<DelegateInfoModel, DelegateInfoData> {
	
	public static final String DATE_FORMAT_RECEIVED = "MMM d, yyyy";
	public static final String DATE_FORMAT_RETURNED = "YYYYMMDD";
	
	@Resource
	private SagiaFormatProvider sagiaFormatProvider;

	@Override
	public void populate(DelegateInfoModel source, DelegateInfoData target) throws ConversionException {
	
		
		
		target.setEntityNo(source.getDelegateIdentityNumber());
		target.setFirstname(source.getDelegateFirstNameArabic());
		
		target.setLastname(source.getDelegateLastNameArabic());
		target.setFullname(source.getDelegateFullName());
		//target.setNationality(source.get); // MISSING
		target.setGender(source.getGender()==null?null:source.getGender().getCode());
		target.setCcodeTele(source.getDelegateCountryCodeTel());
		target.setCcodeMobile(source.getDelegateCountryCodeMobile());
		target.setTelephone(source.getDelegateTelephoneNumber());
		target.setMobile(source.getDelegateMobileNumber());
		target.setEmail(source.getDelegateEmail());
		//target.setCountry(source.get); //MISSING
		//target.setPoBox(source.get);   //MISSING
		//target.setPostalCode(source.get); //MISSING
		target.setIdNumber(source.getDelegateIdentityNumber());
		target.setIdType(source.getDelegateIdentityType());
		
		
		
	//	 if("true".equalsIgnoreCase(source.getNicVerified())) //MISSING
   	  {
   		  target.setNicVerified("X");
   	  }
   	      
		  
		  populateDateFields(source, target); 
	}
	
	
	private void populateDateFields(DelegateInfoModel source, DelegateInfoData target) {
		if(StringUtils.isNotBlank(source.getDelegateIdentityType()) && "1".equals(source.getDelegateIdentityType()))
		{
			target.setIdIssueDate(source.getIdIssueDate()!=null ? sagiaFormatProvider.convertNFormatuiUAQDateToBackEndGreDate(source.getIdIssueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()) : null);
			target.setBirthdate(source.getDelegateDateOfBirth()!=null ? sagiaFormatProvider.convertNFormatuiUAQDateToBackEndGreDate(source.getDelegateDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()) : null);
		}
		else
		{
			target.setIdIssueDate(source.getIdIssueDate()!=null ? sagiaFormatProvider.formatUIDateStrForBackEndDate(source.getIdIssueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()) : null);
			target.setBirthdate(source.getDelegateDateOfBirth()!=null ? sagiaFormatProvider.formatUIDateStrForBackEndDate(source.getDelegateDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()) : null);
		}
	}
	
	
	
	
}
