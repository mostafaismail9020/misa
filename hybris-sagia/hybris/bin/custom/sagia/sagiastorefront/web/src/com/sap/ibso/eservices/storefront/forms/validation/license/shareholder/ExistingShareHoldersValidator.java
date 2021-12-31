/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.forms.validation.license.shareholder;

import com.sap.ibso.eservices.facades.data.ExistingShareholderData;
import com.sap.ibso.eservices.storefront.util.SagiaLicenseFieldsUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;


/**
 * Validator for Existing Shareholder in new quemmah flow
 */

@Component("existingShareHoldersValidator")
public class ExistingShareHoldersValidator implements Validator
{

	@Resource(name= "sagiaLicenseFieldsUtils")
	private SagiaLicenseFieldsUtils sagiaLicenseFieldsUtils;

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return ExistingShareholderData.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final ExistingShareholderData form = (ExistingShareholderData) object;
		final String sharesPercentage = form.getSharesPercentage();
		final String shareHolderEntityNumber = form.getShareHolderEntityNumber();
		final String shareHolderName = form.getShareHolderName();
		final String parentCompanyCountry = form.getParentCompanyCountry();
		final Boolean professionalLicense = form.getProfessionalLicense() ; 
		form.setProfessionalLicense(professionalLicense);

		final boolean existingProfessionalLicenseFileAdded = form.isProfessionalLicenseFileAdded();

		if (StringUtils.isEmpty(sharesPercentage) || !sagiaLicenseFieldsUtils.sharesPercentageCheck(sharesPercentage))
		{
			errors.rejectValue("sharesPercentage", "validation.existingShareholder.percentage");
		}
		

		if (StringUtils.isEmpty(shareHolderEntityNumber))
		{
			errors.rejectValue("shareHolderEntityNumber", "validation.existing.shareholder.shareHolderEntityNumber");
		}

		if (StringUtils.isEmpty(shareHolderName))
		{
			errors.rejectValue("shareHolderName", "validation.existing.shareholder.shareHolderName");
		}

		if (StringUtils.isEmpty(parentCompanyCountry))
		{
			errors.rejectValue("parentCompanyCountry", "validation.existing.shareholder.parentCompanyCountry");
		}
		
		if(professionalLicense == null) {
			errors.rejectValue("professionalLicense", "validation.shareholder.organization.professionalLicense");
		}
		if(!existingProfessionalLicenseFileAdded && professionalLicense) {
			errors.rejectValue("professionalLicenseCertificate", "validation.shareholder.organization.professionalLicenseCertificate");
		}
	}

}
