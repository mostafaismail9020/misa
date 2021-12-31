package com.sap.ibso.eservices.storefront.forms.validation.license;

import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import com.sap.ibso.eservices.facades.data.zqeemah.DelegateInfo;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;

@Component("delegateInfoValidator")
public class DelegateInfoValidator {

	private static final Logger LOG = Logger.getLogger( NewShareholderPersonQeemah1Validator.class.getName() );
	
	@Resource
	private SagiaFormatProvider sagiaFormatProvider;

	public Map<String, String> validate(DelegateInfo shareholderDelegateInfo) {
		Map<String, String> delegateError = new HashMap<>();

		// IDCopyFile
		if (shareholderDelegateInfo.getNicVerified().equalsIgnoreCase("false") || shareholderDelegateInfo.getIdType().equals("4")) {
			validateIdCopyFile(shareholderDelegateInfo, delegateError);
		}

        if(StringUtils.isEmpty(shareholderDelegateInfo.getFirstNameArabic()) || !SagiaValidationUtil.isArabic(shareholderDelegateInfo.getFirstNameArabic())){
        	delegateError.put("delegate.firstNameArabic", "validation.shareholder.person.firstNameArabic");
        }
        if(StringUtils.isEmpty(shareholderDelegateInfo.getLastNameArabic()) || !SagiaValidationUtil.isArabic(shareholderDelegateInfo.getLastNameArabic())){
        	delegateError.put("delegate.lastNameArabic", "validation.shareholder.person.lastNameArabic");
        }
        if(StringUtils.isEmpty(shareholderDelegateInfo.getFullNameEnglish()) || !SagiaValidationUtil.isEnglish(shareholderDelegateInfo.getFullNameEnglish())) {
        	delegateError.put("delegate.fullNameEnglish", "validation.shareholder.person.fullNameEnglish");
        }
        
		if (StringUtils.isEmpty(shareholderDelegateInfo.getCountry())) {
			delegateError.put("delegate.country", "validation.shareholder.delegate.country");
		}

		if (StringUtils.isEmpty(shareholderDelegateInfo.getNationality())) {
			delegateError.put("delegate.nationality", "validation.shareholder.delegate.nationality");
		}

		// postalCode,PoBox and dates
		if (StringUtils.isEmpty(shareholderDelegateInfo.getIdType())) {
			delegateError.put("delegate.idType", "validation.shareholder.delegate.idType");
		} else {
			if (shareholderDelegateInfo.getIdType().equals("4")) {
				if (StringUtils.isEmpty(shareholderDelegateInfo.getPostalCode())) {
					delegateError.put("delegate.postalCode", "validation.shareholder.delegate.postalCode");
				}

				if (StringUtils.isEmpty(shareholderDelegateInfo.getPoBox())
						|| !StringUtils.isNumeric(shareholderDelegateInfo.getPoBox())) {
					delegateError.put("delegate.poBox", "validation.shareholder.delegate.poBox");
				}
				validateDates(shareholderDelegateInfo, delegateError, false);
			} else if (shareholderDelegateInfo.getIdType().equals("1")) {
				if ((StringUtils.isEmpty(shareholderDelegateInfo.getCountry()))
						&& !(shareholderDelegateInfo.getCountry().equals("SA"))) {
					shareholderDelegateInfo.setCountry("SA");
				}

				if (StringUtils.isEmpty(shareholderDelegateInfo.getNationality())
						&& !(shareholderDelegateInfo.getNationality().equals("SA"))) {
					shareholderDelegateInfo.setNationality("SA");
				}
				validateDates(shareholderDelegateInfo, delegateError, true);

			} else {
				validateDates(shareholderDelegateInfo, delegateError, false);
			}
		}

		// common details

		if (StringUtils.isEmpty(shareholderDelegateInfo.getIdNumber())) {
			delegateError.put("delegate.idNumber", "validation.shareholder.delegate.idNumber");
		}
		else if(shareholderDelegateInfo.getIdType() != null && (shareholderDelegateInfo.getIdType().equals("1") || shareholderDelegateInfo.getIdType().equals("2")))
		{
			if(shareholderDelegateInfo.getIdNumber().trim().length() != 10)
			{
				delegateError.put("delegate.idNumber", "validation.shareholder.delegate.idNumber.10digit");
			}
			else
			{
				if(shareholderDelegateInfo.getIdType().equals("1") && !shareholderDelegateInfo.getIdNumber().trim().startsWith("1")){
					delegateError.put("delegate.idNumber", "validation.shareholder.delegate.idNumber.startwith1");
				}
				if(shareholderDelegateInfo.getIdType().equals("2") && !shareholderDelegateInfo.getIdNumber().trim().startsWith("2")){
					delegateError.put("delegate.idNumber", "validation.shareholder.delegate.idNumber.startwith2");
				}
			}
		}

		if (StringUtils.isEmpty(shareholderDelegateInfo.getFirstNameArabic())
				|| !SagiaValidationUtil.isArabic(shareholderDelegateInfo.getFirstNameArabic())) {
			delegateError.put("delegate.firstNameArabic", "validation.shareholder.delegate.firstNameArabic");
		}
		if (StringUtils.isEmpty(shareholderDelegateInfo.getLastNameArabic())
				|| !SagiaValidationUtil.isArabic(shareholderDelegateInfo.getLastNameArabic())) {
			delegateError.put("delegate.lastNameArabic", "validation.shareholder.delegate.lastNameArabic");
		}
		if (StringUtils.isEmpty(shareholderDelegateInfo.getFullNameEnglish())) {
			delegateError.put("delegate.fullNameEnglish", "validation.shareholder.delegate.fullNameEnglish");
		}

		if (StringUtils.isEmpty(shareholderDelegateInfo.getGender())) {
			delegateError.put("delegate.gender", "validation.shareholder.delegate.gender");
		}

		if (StringUtils.isEmpty(shareholderDelegateInfo.getCountryCodeForTelephone())
				|| !StringUtils.isNumeric(shareholderDelegateInfo.getCountryCodeForTelephone())) {
			delegateError.put("delegate.countryCodeForTelephone",
					"validation.shareholder.delegate.countryCodeForTelephone");
		}
		if (StringUtils.isEmpty(shareholderDelegateInfo.getTelephone())
				|| !StringUtils.isNumeric(shareholderDelegateInfo.getTelephone())) {
			delegateError.put("delegate.telephone", "validation.shareholder.delegate.telephone");
		}
		if (StringUtils.isEmpty(shareholderDelegateInfo.getCountryCodeForMobile())
				|| !StringUtils.isNumeric(shareholderDelegateInfo.getCountryCodeForMobile())) {
			delegateError.put("delegate.countryCodeForMobile",
					"validation.shareholder.delegate.countryCodeForMobile");
		}
		if (StringUtils.isEmpty(shareholderDelegateInfo.getMobile())
				|| !StringUtils.isNumeric(shareholderDelegateInfo.getMobile())) {
			delegateError.put("delegate.mobile", "validation.shareholder.delegate.mobile");
		}
		if (StringUtils.isEmpty(shareholderDelegateInfo.getEmail())
				|| !SagiaValidationUtil.isEmail(shareholderDelegateInfo.getEmail())) {
			delegateError.put("delegate.email", "validation.shareholder.delegate.email");
		}
		if (StringUtils.isEmpty(shareholderDelegateInfo.getAuthorizationLetterFileName())) {
			delegateError.put("delegate.authorizationLetterFile",
					"validation.shareholder.delegate.authorizationLetterFile");
		}

		if (shareholderDelegateInfo.getAuthorizationLetterFile() == null
				|| !SagiaValidationUtil.isBase64(shareholderDelegateInfo.getAuthorizationLetterFile())) {
			delegateError.put("delegate.authorizationLetterFile",
					"validation.shareholder.delegate.authorizationLetterFile");
		}

		return delegateError;
	}

	public void validateIdCopyFile(DelegateInfo shareholderDelegateInfo, Map<String, String> delegateError) {
		if (StringUtils.isEmpty(shareholderDelegateInfo.getIdCopyFileName())) {
			delegateError.put("delegate.idCopyFile", "validation.shareholder.delegate.idCopyFile");
		}

		if (shareholderDelegateInfo.getIdCopyFile() == null
				|| !SagiaValidationUtil.isBase64(shareholderDelegateInfo.getIdCopyFile())) {
			delegateError.put("delegate.idCopyFile", "validation.shareholder.delegate.idCopyFile");
		}
	}

	public void validateDates(DelegateInfo shareholderDelegateInfo, Map<String, String> delegateError, boolean isUAQDate) {
		if (StringUtils.isEmpty(shareholderDelegateInfo.getDateofBirth())) {
			delegateError.put("delegate.dateofBirth", "validation.shareholder.delegate.dateofBirth");
		} else {
			dateValidator(shareholderDelegateInfo.getDateofBirth(), "delegate.dateofBirth", "validation.shareholder.delegate.dateofBirth", delegateError, isUAQDate, true);
		}

		if (StringUtils.isEmpty(shareholderDelegateInfo.getExpiryDate())) {
			delegateError.put("delegate.expiryDate", "validation.shareholder.delegate.expiryDate");
		} else {
			dateValidator(shareholderDelegateInfo.getExpiryDate(), "delegate.expiryDate", "validation.shareholder.delegate.expiryDate", delegateError, isUAQDate, false);
		}

		if (StringUtils.isEmpty(shareholderDelegateInfo.getIssueDate())) {
			delegateError.put("delegate.issueDate", "validation.shareholder.delegate.issueDate");
		} else {
			dateValidator(shareholderDelegateInfo.getIssueDate(), "delegate.issueDate", "validation.shareholder.delegate.issueDate", delegateError, isUAQDate, true);
		}
	}

	private void dateValidator(String date, String errorKey, String errorMsg, Map<String, String> errorMap, boolean isUAQDate, boolean isAfter)
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
					if(sagiaFormatProvider.getDateFromUIDateStringOrNull(date).isAfter(LocalDate.now())) {
						errorMap.put(errorKey, errorMsg);
					}
				}
				else if(sagiaFormatProvider.getDateFromUIDateStringOrNull(date).isBefore(LocalDate.now())) {
					errorMap.put(errorKey, errorMsg);
				}	
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
		}
	}
	
}
