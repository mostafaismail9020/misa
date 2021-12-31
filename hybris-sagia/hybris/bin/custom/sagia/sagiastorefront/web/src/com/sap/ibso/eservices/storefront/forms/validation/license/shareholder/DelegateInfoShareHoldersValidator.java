package com.sap.ibso.eservices.storefront.forms.validation.license.shareholder;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.DelegateInformationData;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;

import com.sap.ibso.eservices.storefront.util.SagiaLicenseFieldsUtils;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.HijrahChronology;
import java.util.HashMap;
import java.util.Map;

@Component("delegateInfoShareHoldersValidator")
public class DelegateInfoShareHoldersValidator {

	private static final Logger LOG = Logger.getLogger( DelegateInfoShareHoldersValidator.class.getName() );
	
	@Resource
	private SagiaFormatProvider sagiaFormatProvider;

	@Resource(name= "sagiaLicenseFieldsUtils")
	private SagiaLicenseFieldsUtils sagiaLicenseFieldsUtils;

	public Map<String, String> validate(DelegateInformationData form) {

		Map<String, String> delegateError = new HashMap<>();

		final String delegateIdentityType = XSSFilterUtil.filter(form.getDelegateIdentityType());
		form.setDelegateIdentityType(delegateIdentityType);
		
		final boolean isNicVerified = form.isNicVerified();
		
		final String delegateIdentityNumber = XSSFilterUtil.filter(form.getDelegateIdentityNumber());
		form.setDelegateIdentityNumber(delegateIdentityNumber);
		
		final String delegateFirstNameArabic = XSSFilterUtil.filter(form.getDelegateFirstNameArabic());
		form.setDelegateFirstNameArabic(delegateFirstNameArabic);
		
		final String delegateLastNameArabic = XSSFilterUtil.filter(form.getDelegateLastNameArabic());
		form.setDelegateLastNameArabic(delegateLastNameArabic);
		
		final String delegateFullName = XSSFilterUtil.filter(form.getDelegateFullName());
		form.setDelegateFullName(delegateFullName);
		
		final String gender = XSSFilterUtil.filter(form.getGender()); 
		form.setGender(gender);
		
		final String delegateCountryCodeTel = XSSFilterUtil.filter(form.getDelegateCountryCodeTel());	
		form.setDelegateCountryCodeTel(delegateCountryCodeTel);
		
		final String delegateTelephoneNumber = XSSFilterUtil.filter(form.getDelegateTelephoneNumber()); 
		form.setDelegateTelephoneNumber(delegateTelephoneNumber);
		
		final String delegateCountryCodeMobile = XSSFilterUtil.filter(form.getDelegateCountryCodeMobile()); 
		form.setDelegateCountryCodeMobile(delegateCountryCodeMobile);
		
		final String delegateMobileNumber = XSSFilterUtil.filter(form.getDelegateMobileNumber());
		form.setDelegateMobileNumber(delegateMobileNumber);
		
		final String delegateEmail = XSSFilterUtil.filter(form.getDelegateEmail()); 
		form.setDelegateEmail(delegateEmail);
		
		final String delegatePostalCode =  XSSFilterUtil.filter(form.getDelegatePostalCode());
		form.setDelegatePostalCode(delegatePostalCode);
		
		final String delegatePoBox = XSSFilterUtil.filter(form.getDelegatePoBox());  
		form.setDelegatePostalCode(delegatePoBox);

		final boolean isAuthorisationLetterFileAdded = form.isAuthorisationLetterFileAdded();
		final boolean isSaudiIdCopyFileAdded =  form.isSaudiIdCopyFileAdded();
		final boolean mofaNumberVerified =  form.isMofaNumberVerified();

		String delegateCountry = XSSFilterUtil.filter(form.getDelegateCountry()); 
		form.setDelegateCountry(delegateCountry);
		
		String delegateNationality = XSSFilterUtil.filter(form.getDelegateNationality()); 
		form.setDelegateNationality(delegateNationality);


        if(StringUtils.isEmpty(delegateFirstNameArabic)	|| !sagiaLicenseFieldsUtils.isMaxLength(delegateFirstNameArabic, 40)){
        	delegateError.put("delegateInfo.delegateFirstNameArabic", "validation.shareholder.person.firstNameArabic");
        }
        if(StringUtils.isEmpty(delegateLastNameArabic)	|| !sagiaLicenseFieldsUtils.isMaxLength(delegateLastNameArabic, 40)){
        	delegateError.put("delegateInfo.delegateLastNameArabic", "validation.shareholder.person.lastNameArabic");
        }
        if(StringUtils.isEmpty(delegateFullName) || !sagiaLicenseFieldsUtils.fullNameEnglishCheck(delegateFullName)) {
        	delegateError.put("delegateInfo.delegateFullName", "validation.shareholder.person.fullNameEnglish");
        }
        
		if (StringUtils.isEmpty(delegateCountry)) {
			delegateError.put("delegateInfo.delegateCountry", "validation.shareholder.delegate.country");
		}

		if (StringUtils.isEmpty(delegateNationality)) {
			delegateError.put("delegateInfo.delegateNationality", "validation.shareholder.delegate.nationality");
		}

		// postalCode,PoBox and dates
		if (StringUtils.isEmpty(delegateIdentityType)) {
			delegateError.put("delegateInfo.delegateIdentityType", "validation.shareholder.delegate.idType");
		} else {
			if (delegateIdentityType.equals("4")) {
				if (StringUtils.isEmpty(delegatePostalCode) || !sagiaLicenseFieldsUtils.postalCodeCheck(delegatePostalCode)) {
					delegateError.put("delegateInfo.delegatePostalCode", "validation.shareholder.delegate.postalCode");
				}

				if (StringUtils.isEmpty(delegatePoBox)
						|| !StringUtils.isNumeric(delegatePoBox) || !sagiaLicenseFieldsUtils.poBoxCheck(delegatePoBox)) {
					delegateError.put("delegateInfo.delegatePoBox", "validation.shareholder.delegate.poBox");
				}

				validateDates(form, delegateError, false);

			} else if (delegateIdentityType.equals("1")) {
				if ((StringUtils.isEmpty(delegateCountry))	&& !(delegateCountry.equals("SA"))) {
					delegateCountry = "SA";
				}

				if (StringUtils.isEmpty(delegateNationality) && !(delegateNationality.equals("SA"))) {
					delegateNationality = "SA";
				}

				validateDates(form, delegateError, true);

			} else {
				validateDates(form, delegateError, false);
			}
		}

		// common details

		if (StringUtils.isEmpty(delegateIdentityNumber)) {
			delegateError.put("delegateInfo.delegateIdentityNumber", "validation.shareholder.delegate.idNumber");
		}
		else if(delegateIdentityType != null && (delegateIdentityType.equals("1") || delegateIdentityType.equals("2")))
		{
			if(delegateIdentityNumber.trim().length() != 10)
			{
				delegateError.put("delegateInfo.delegateIdentityNumber", "validation.shareholder.delegate.idNumber.10digit");
			}
			else
			{
				if(delegateIdentityType.equals("1") && !delegateIdentityNumber.trim().startsWith("1")){
					delegateError.put("delegateInfo.delegateIdentityNumber", "validation.shareholder.delegate.idNumber.startwith1");
				}
				if(delegateIdentityType.equals("2") && !delegateIdentityNumber.trim().startsWith("2")){
					delegateError.put("delegateInfo.delegateIdentityNumber", "validation.shareholder.delegate.idNumber.startwith2");
				}
			}
		}

		if (StringUtils.isEmpty(gender)) {
			delegateError.put("delegateInfo.gender", "validation.shareholder.delegate.gender");
		}

		if (StringUtils.isEmpty(delegateCountryCodeTel)	|| !sagiaLicenseFieldsUtils.countryCodeCheck(delegateCountryCodeTel)) {
			delegateError.put("delegateInfo.delegateCountryCodeTel",	"validation.shareholder.delegate.countryCodeForTelephone");
		}
		if (StringUtils.isEmpty(delegateTelephoneNumber) || !sagiaLicenseFieldsUtils.telePhoneNumberCheck(delegateTelephoneNumber)) {
			delegateError.put("delegateInfo.delegateTelephoneNumber", "validation.shareholder.delegate.telephone");
		}
		if (StringUtils.isEmpty(delegateCountryCodeMobile) || !sagiaLicenseFieldsUtils.countryCodeCheck(delegateCountryCodeMobile)) {
			delegateError.put("delegateInfo.delegateCountryCodeMobile", "validation.shareholder.delegate.countryCodeForMobile");
		}
		if (StringUtils.isEmpty(delegateMobileNumber) || !sagiaLicenseFieldsUtils.telePhoneNumberCheck(delegateTelephoneNumber)) {
			delegateError.put("delegateInfo.delegateMobileNumber", "validation.shareholder.delegate.mobile");
		}
		if (StringUtils.isEmpty(delegateEmail)	|| !SagiaValidationUtil.isEmail(delegateEmail)) {
			delegateError.put("delegateInfo.delegateEmail", "validation.shareholder.delegate.email");
		}
		
		if (!isSaudiIdCopyFileAdded && !isNicVerified) {
			delegateError.put("delegateInfo.saudiIdCopy", "validation.shareholder.delegate.idCopyFile");
		}

		if (!mofaNumberVerified && !isAuthorisationLetterFileAdded) {
			delegateError.put("delegateInfo.authorisationLetter", "validation.shareholder.delegate.authorizationLetterFile");
		}

		return delegateError;
	}


	public void validateDates(DelegateInformationData delegateInformationData, Map<String, String> delegateError, boolean isUAQDate) {
		
		delegateInformationData.setDelegateDateOfBirth(XSSFilterUtil.filter(delegateInformationData.getDelegateDateOfBirth()));
		if (StringUtils.isEmpty(delegateInformationData.getDelegateDateOfBirth())) {
			delegateError.put("delegateInfo.delegateDateOfBirth", "validation.shareholder.delegate.dateofBirth");
		} else {
			dateValidator(delegateInformationData.getDelegateDateOfBirth(), "delegateInfo.delegateDateOfBirth", "validation.shareholder.delegate.dateofBirth", delegateError, isUAQDate, true);
		}

		delegateInformationData.setIdExpiryDate(XSSFilterUtil.filter(delegateInformationData.getIdExpiryDate()));
		
		if (StringUtils.isEmpty(delegateInformationData.getIdExpiryDate())) {
			delegateError.put("delegateInfo.idExpiryDate", "validation.shareholder.delegate.expiryDate");
		} else {
			dateValidator(delegateInformationData.getIdExpiryDate(), "delegateInfo.idExpiryDate", "validation.shareholder.delegate.expiryDate", delegateError, isUAQDate, false);
		}

		delegateInformationData.setIdIssueDate(XSSFilterUtil.filter(delegateInformationData.getIdIssueDate()));
		
		if (StringUtils.isEmpty(delegateInformationData.getIdIssueDate())) {
			delegateError.put("delegateInfo.idIssueDate", "validation.shareholder.delegate.issueDate");
		} else {
			dateValidator(delegateInformationData.getIdIssueDate().toString(), "delegateInfo.idIssueDate", "validation.shareholder.delegate.issueDate", delegateError, isUAQDate, true);
		}
	}

	final void dateValidator(String date, String errorKey, String errorMsg, Map<String, String> errorMap, boolean isUAQDate, boolean isAfter)
	{
		try
		{
			if(isUAQDate)
			{
				if(isAfter)
				{
					if(sagiaFormatProvider.getLocalDateFromuiUAQDateString(date, HijrahChronology.INSTANCE).isAfter(LocalDate.now()))
					{
						errorMap.put(errorKey, errorMsg);
					}
				}
				else if (sagiaFormatProvider.getLocalDateFromuiUAQDateString(date, HijrahChronology.INSTANCE).isBefore(LocalDate.now()))
				{
					errorMap.put(errorKey, errorMsg);
				}
			}
			else
			{ 
				if(isAfter)
				{
					LocalDate currenDate = sagiaFormatProvider.getDateTimeFromUIDateString(date).toLocalDate();

					if(currenDate.isAfter(LocalDate.now())) {
						errorMap.put(errorKey, errorMsg);
					}
				}
				else if(sagiaFormatProvider.getDateTimeFromUIDateString(date).toLocalDate().isBefore(LocalDate.now())) {
					errorMap.put(errorKey, errorMsg);
				}	
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		}
	}
	
}
