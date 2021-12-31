/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.forms.validation.license.shareholder;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.DelegateInformationData;
import com.sap.ibso.eservices.facades.data.PersonShareholderData;
import com.sap.ibso.eservices.storefront.controllers.pages.SagiaLicenseApplyController;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;

import com.sap.ibso.eservices.storefront.util.SagiaLicenseFieldsUtils;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.util.Map;


/**
 * Validator for PersonShareHolders in new quemmah flow
 */

@Component("personShareHoldersValidator")
public class PersonShareHoldersValidator implements Validator
{

	private static final Logger LOG = Logger.getLogger( PersonShareHoldersValidator.class.getName() );

	@Resource
	private SagiaFormatProvider sagiaFormatProvider;

	@Resource
	private DelegateInfoShareHoldersValidator delegateInfoShareHoldersValidator;

	@Resource(name= "sagiaLicenseFieldsUtils")
	private SagiaLicenseFieldsUtils sagiaLicenseFieldsUtils;

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return PersonShareholderData.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final PersonShareholderData form = (PersonShareholderData) object;

		
		final String shareHolderTitle = XSSFilterUtil.filter(form.getShareHolderTitle());
		form.setShareHolderTitle(shareHolderTitle);
		final String academicTitle = XSSFilterUtil.filter(form.getAcademicTitle());
		form.setAcademicTitle(academicTitle);
		final String firstNameArabic = XSSFilterUtil.filter(form.getFirstNameArabic()); 
		form.setFirstNameArabic(firstNameArabic);
		final String lastNameArabic = XSSFilterUtil.filter(form.getLastNameArabic());
		form.setLastNameArabic(lastNameArabic);
		final String fullName = XSSFilterUtil.filter(form.getFullName());
		form.setFullName(fullName);
		final String sharesPercentage = XSSFilterUtil.filter(form.getSharesPercentage()); 
		form.setSharesPercentage(sharesPercentage);
		final String dateOfBirth = XSSFilterUtil.filter(form.getDateOfBirth()); 
		form.setDateOfBirth(dateOfBirth);
		final String passportNumber = XSSFilterUtil.filter(form.getPassportNumber()); 
		form.setPassportNumber(passportNumber);
		final String passportIssueDate = XSSFilterUtil.filter(form.getPassportIssueDate());  
		form.setPassportIssueDate(passportIssueDate);
		final String passportExpiryDate = XSSFilterUtil.filter(form.getPassportExpiryDate());  
		form.setPassportExpiryDate(passportExpiryDate);
		final String currentNationality = XSSFilterUtil.filter(form.getCurrentNationality()); 
		form.setCurrentNationality(currentNationality);
		final String previousNationality = XSSFilterUtil.filter(form.getPreviousNationality()); 
		form.setPreviousNationality(previousNationality);
		final String country = XSSFilterUtil.filter(form.getCountry());  
		form.setCountry(country);
		final Boolean premiumResident =  form.getPremiumResident();
		form.setPremiumResident(premiumResident);
		final String city = XSSFilterUtil.filter(form.getCity()); 
		form.setCity(city);
		final String poBox = XSSFilterUtil.filter(form.getPoBox()); 
		form.setPoBox(poBox);
		final String postalCode = XSSFilterUtil.filter(form.getPostalCode());  
		form.setPostalCode(postalCode);
		final String countryCodeTelephone = XSSFilterUtil.filter(form.getCountryCodeTelephone());
		form.setCountryCodeTelephone(countryCodeTelephone);
		final String telephoneNumber = XSSFilterUtil.filter(form.getTelephoneNumber()); 
		form.setTelephoneNumber(telephoneNumber);
		final String countryCodeMobile = XSSFilterUtil.filter(form.getCountryCodeMobile());
		form.setCountryCodeMobile(countryCodeMobile);
		final String mobileNumber = XSSFilterUtil.filter(form.getMobileNumber()); 
		form.setMobileNumber(mobileNumber);
		final String email = XSSFilterUtil.filter(form.getEmail()); 
		form.setEmail(email);
		final Boolean professionalLicense =  form.getProfessionalLicense();
		form.setProfessionalLicense(professionalLicense);
		final String mofaNumber = XSSFilterUtil.filter(form.getMofaNumber()) ;
		form.setMofaNumber(mofaNumber);

		final boolean passportIdCopyAdded = form.isPassportIdCopyFileAdded();
		final boolean otherFileAdded = form.isOtherFileAdded();
		final boolean professionalLicenseFileAdded = form.isProfessionalLicenseFileAdded();
		boolean mofaNumberVerified = form.isMofaNumberVerified();
		
		if(!"4".equals(form.getShareHolderIdType())) {
			mofaNumberVerified = false;
		}
		
		boolean isUMQDate = false ;
		if("1".equals(form.getShareHolderIdType())) {
			isUMQDate = true ;
		}


		if(StringUtils.isEmpty(shareHolderTitle)){
			errors.rejectValue("shareHolderTitle", "validation.shareholder.person.title");
		}
		if(StringUtils.isEmpty(academicTitle)){
			errors.rejectValue("academicTitle", "validation.shareholder.person.academicTitle");
		}
		if(StringUtils.isEmpty(firstNameArabic) || !SagiaValidationUtil.isArabic(firstNameArabic) || !sagiaLicenseFieldsUtils.isMaxLength(firstNameArabic, 40)){
			errors.rejectValue("firstNameArabic", "validation.shareholder.person.firstNameArabic");
		}
		if(StringUtils.isEmpty(lastNameArabic) || !SagiaValidationUtil.isArabic(lastNameArabic) || !sagiaLicenseFieldsUtils.isMaxLength(firstNameArabic, 40)){
			errors.rejectValue("lastNameArabic", "validation.shareholder.person.lastNameArabic");
		}
		if(StringUtils.isEmpty(fullName) || !SagiaValidationUtil.isEnglish(fullName) || !sagiaLicenseFieldsUtils.isMaxLength(firstNameArabic, 100)) {
			errors.rejectValue("fullName", "validation.shareholder.person.fullNameEnglish");
		}
		if(StringUtils.isBlank(sharesPercentage) || !sagiaLicenseFieldsUtils.sharesPercentageCheck(sharesPercentage) || !isPercentage(sharesPercentage)){
			errors.rejectValue("sharesPercentage", "validation.shareholder.person.sharesPercentage");
		}
		if(StringUtils.isEmpty(currentNationality)){
			errors.rejectValue("currentNationality", "validation.shareholder.person.nationality");
		}
		if(StringUtils.isEmpty(dateOfBirth)){
			errors.rejectValue("dateOfBirth", "validation.shareholder.person.dateOfBirth");
		} else if (!isDateInThePast(dateOfBirth,isUMQDate)){
            errors.rejectValue("dateOfBirth", "validation.shareholder.person.dateOfBirth");
        }
		if(StringUtils.isEmpty(passportNumber) || !sagiaLicenseFieldsUtils.passportNumberCheck(passportNumber)){
			errors.rejectValue("passportNumber", "validation.shareholder.person.passportNumber");
		}
		if(StringUtils.isEmpty(passportIssueDate)) {
			errors.rejectValue("passportIssueDate", "validation.shareholder.person.passportIssueDate");
		} else if (!isDateInThePast(passportIssueDate,false)){
            errors.rejectValue("passportIssueDate", "validation.shareholder.person.passportIssueDate");
        }

		if(StringUtils.isEmpty(passportExpiryDate)){
			errors.rejectValue("passportExpiryDate", "validation.shareholder.person.passportExpiryDate");
		}

		if(StringUtils.isEmpty(previousNationality)){
			errors.rejectValue("previousNationality", "validation.shareholder.person.previousNationality");
		}

		if(premiumResident == null){
			errors.rejectValue("premiumResident", "validation.shareholder.person.premiumResident");
		}
		
		if(professionalLicense == null){
			errors.rejectValue("professionalLicense", "validation.shareholder.person.professionalLicense");
		}

		if(StringUtils.isEmpty(country)){
			errors.rejectValue("country", "validation.shareholder.person.country");
		}

		if(StringUtils.isEmpty(city)){
			errors.rejectValue("city", "validation.shareholder.person.city");
		}

		if(StringUtils.isEmpty(postalCode) || !sagiaLicenseFieldsUtils.postalCodeCheck(postalCode)) {
			errors.rejectValue("postalCode", "validation.shareholder.person.postalCode");
		}

		if(StringUtils.isEmpty(poBox) || !sagiaLicenseFieldsUtils.poBoxCheck(poBox)){
			errors.rejectValue("poBox", "validation.shareholder.person.poBox");
		}

		if(StringUtils.isEmpty(countryCodeTelephone) || !sagiaLicenseFieldsUtils.countryCodeCheck(countryCodeTelephone)){
			errors.rejectValue("countryCodeTelephone", "validation.shareholder.person.countryCodeForTelephone");
		}

		if(StringUtils.isEmpty(telephoneNumber) || !sagiaLicenseFieldsUtils.telePhoneNumberCheck(telephoneNumber)){
			errors.rejectValue("telephoneNumber", "validation.shareholder.person.telephone");
		}

		if(StringUtils.isEmpty(countryCodeMobile) || !sagiaLicenseFieldsUtils.countryCodeCheck(countryCodeMobile)){
			errors.rejectValue("countryCodeMobile", "validation.shareholder.person.countryCodeForMobile");
		}

		if(StringUtils.isEmpty(mobileNumber) || !sagiaLicenseFieldsUtils.mobilePhoneNumberCheck(mobileNumber)){
			errors.rejectValue("mobileNumber", "validation.shareholder.person.mobile");
		}

		if(StringUtils.isEmpty(email) || !SagiaValidationUtil.isEmail(email)){
			errors.rejectValue("email", "validation.shareholder.person.email");
		}

		//Media Validation
		if(!passportIdCopyAdded  && SagiaLicenseApplyController.SHAREHOLDER_ID_TYPE_PASSPORT.equals(form.getShareHolderIdType())){
			errors.rejectValue("passportIdCopy", "validation.shareholder.organization.companyRegistrationFile");
		}

		//if(!otherFileAdded  ){
			//errors.rejectValue("other", "validation.shareholder.organization.companyFinancialStatementFile");
		//}
		
		if(!professionalLicenseFileAdded && professionalLicense ) {
			errors.rejectValue("professionalLicenseCertificate", "validation.shareholder.organization.professionalLicenseCertificate");
		}

		if("4".equals(form.getShareHolderIdType()) &&  form.getDelegateInfo().isDelegateYourself() == false){

			DelegateInformationData delegateInformationData = form.getDelegateInfo();
			
			delegateInformationData.setMofaNumberVerified(mofaNumberVerified);

			Map<String,String> delegateError = delegateInfoShareHoldersValidator.validate(delegateInformationData);
			for(Map.Entry<String, String> error:delegateError.entrySet()){
				errors.rejectValue(error.getKey(), error.getValue());
			}
		}

	}
	private boolean isDateInThePast(String value, boolean isUAQDate) {
	    
        return dateValidator(value, isUAQDate,  true);
    }
	
	final boolean dateValidator(String date, boolean isUAQDate, boolean isAfter)
	{
		try
		{
			if(isUAQDate)
			{
				if(isAfter)
				{
					if(sagiaFormatProvider.getLocalDateFromuiUAQDateString(date, HijrahChronology.INSTANCE).isAfter(LocalDate.now()))
					{
						return false;
					}
				}
				else if (sagiaFormatProvider.getLocalDateFromuiUAQDateString(date, HijrahChronology.INSTANCE).isBefore(LocalDate.now()))
				{
					return false;
				}
			}
			else
			{ 
				if(isAfter)
				{
					LocalDate currenDate = sagiaFormatProvider.getDateTimeFromUIDateString(date).toLocalDate();

					if(currenDate.isAfter(LocalDate.now())) {
						return false;
					}
				}
				else if(sagiaFormatProvider.getDateTimeFromUIDateString(date).toLocalDate().isBefore(LocalDate.now())) {
					return false;
				}	
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private boolean isPercentage(String value) {
		try {
			Double percentage = Double.valueOf(value);
			if (percentage <= 0 || percentage > 100) {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
