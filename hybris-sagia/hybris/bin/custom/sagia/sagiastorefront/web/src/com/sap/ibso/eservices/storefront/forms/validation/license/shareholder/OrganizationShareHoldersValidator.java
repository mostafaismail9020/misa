/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sap.ibso.eservices.storefront.forms.validation.license.shareholder;

import com.sap.ibso.eservices.core.model.SagiaCountryModel;
import com.sap.ibso.eservices.core.sagia.dao.SagiaCountryDAO;
import com.sap.ibso.eservices.core.sagia.services.LicenseApplyService;
import com.sap.ibso.eservices.facades.data.DelegateInformationData;
import com.sap.ibso.eservices.facades.data.OrganizationShareholderData;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;

import com.sap.ibso.eservices.storefront.util.SagiaLicenseFieldsUtils;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.Map;


/**
 * Validator for OrgnizationShareHolders in new quemmah flow
 */

@Component("organizationShareHoldersValidator")
public class OrganizationShareHoldersValidator implements Validator {

	private static final Logger LOG = Logger.getLogger(OrganizationShareHoldersValidator.class);

	private final String foreignEntityLegalStatus = "BRFC";

	@Resource
	private SagiaCountryDAO sagiaCountryDAO;

	@Resource
	private LicenseApplyService licenseApplyService;

	@Resource
	private DelegateInfoShareHoldersValidator delegateInfoShareHoldersValidator;

	@Resource(name= "sagiaLicenseFieldsUtils")
	private SagiaLicenseFieldsUtils sagiaLicenseFieldsUtils;


	@Override
	public boolean supports(final Class<?> aClass) {
		return OrganizationShareHoldersValidator.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors) {

		final OrganizationShareholderData form = (OrganizationShareholderData) object;

		final String organizationName = XSSFilterUtil.filter(form.getOrganizationName()) ;
		form.setOrganizationName(organizationName);
		final String organizationNameArabic = XSSFilterUtil.filter(form.getOrganizationNameArabic()) ; 
		form.setOrganizationNameArabic(organizationNameArabic);
		final String legalStatus = XSSFilterUtil.filter(form.getLegalStatus()) ; 
		form.setLegalStatus(legalStatus);
		final String multinationalCompany = XSSFilterUtil.filter(form.getMultinationalCompany()) ; 
		form.setMultinationalCompany(multinationalCompany);
		final String companyRegNumber =  XSSFilterUtil.filter(form.getCompanyRegNumber()) ; 
		form.setCompanyRegNumber(companyRegNumber);
		final String capital =  XSSFilterUtil.filter(form.getCapital()) ;
		form.setCapital(capital);
		final String section = XSSFilterUtil.filter(form.getSection()) ;
		form.setSection(section);
		final String division = XSSFilterUtil.filter(form.getDivision()) ;
		form.setDivision(division);
		final String parentCompanyName = XSSFilterUtil.filter(form.getParentCompanyName()) ;
		form.setParentCompanyName(parentCompanyName);
		final String parentCompanyCountry = XSSFilterUtil.filter(form.getCompanyCountry()) ;
		form.setCompanyCountry(parentCompanyCountry);
		final String companyCountry = XSSFilterUtil.filter(form.getCompanyCountry()) ;
		form.setCompanyCountry(companyCountry);
		final String countryOfReg = XSSFilterUtil.filter(form.getCountryOfReg()) ;
		form.setCountryOfReg(countryOfReg);
		final String city = XSSFilterUtil.filter(form.getCity()) ;
		form.setCity(city);
		final String address = XSSFilterUtil.filter(form.getAddress()) ;
		form.setAddress(address);
		final String poBox = XSSFilterUtil.filter(form.getPoBox()) ;
		form.setPoBox(poBox);
		final String postalCode = XSSFilterUtil.filter(form.getPostalCode()) ;
		form.setPostalCode(postalCode);
		final String countryCodeTelephone = XSSFilterUtil.filter(form.getCountryCodeTelephone()) ;
		form.setCountryCodeTelephone(countryCodeTelephone);
		final String telephoneNumber = XSSFilterUtil.filter(form.getTelephoneNumber()) ;
		form.setTelephoneNumber(telephoneNumber);
		final String countryCodeMobile = XSSFilterUtil.filter(form.getCountryCodeMobile()) ;
		form.setCountryCodeMobile(countryCodeMobile);
		final String mobileNumber = XSSFilterUtil.filter(form.getMobileNumber()) ;
		form.setMobileNumber(mobileNumber);
		final String email = XSSFilterUtil.filter(form.getEmail()) ;
		form.setEmail(email);
		final String website = XSSFilterUtil.filter(form.getWebsite()) ;
		form.setWebsite(website);
		final Boolean professionalLicense = form.getProfessionalLicense() ; 
		form.setProfessionalLicense(professionalLicense);
		final String mofaNumber = XSSFilterUtil.filter(form.getMofaNumber()) ;
		form.setMofaNumber(mofaNumber);

		final boolean commercialRegCopyFileAdded = form.isCommercialRegCopyFileAdded();
		final boolean lastYearFinStatementFileAdded = form.isLastYearFinStatementFileAdded();
		final boolean companyMemoAssociationAdded = form.isCompanyMemoAssociationFileAdded();
		final boolean professionalLicenseFileAdded = form.isProfessionalLicenseFileAdded();
		
		boolean mofaNumberVerified = form.isMofaNumberVerified();
		
		if("SA".equals(companyCountry)) {
			mofaNumberVerified = false;
		}

		if (StringUtils.isEmpty(organizationName)
				|| !sagiaLicenseFieldsUtils.isAlphaNumericWithSpace(organizationName)
				|| !sagiaLicenseFieldsUtils.organizationNameCheck(organizationName)) {
			errors.rejectValue("organizationName", "validation.shareholder.organization.organizationNameEnglish");
		}

		if (StringUtils.isNotEmpty(organizationNameArabic)
				&& (!SagiaValidationUtil.isArabic(organizationNameArabic)
				|| !sagiaLicenseFieldsUtils.organizationNameCheck(organizationName))) {
			errors.rejectValue("organizationNameArabic", "validation.shareholder.organization.organizationNameArabic");
		}

		if (StringUtils.isEmpty(legalStatus)) {
			errors.rejectValue("legalStatus", "validation.shareholder.organization.organizationLegalStatus");
		}
		if (StringUtils.isEmpty(multinationalCompany)) {
			errors.rejectValue("multinationalCompany", "validation.shareholder.organization.multinationalCompany");
		}
		if(professionalLicense == null) {
			errors.rejectValue("professionalLicense", "validation.shareholder.organization.professionalLicense");
		}
		if (StringUtils.isEmpty(companyRegNumber) || !sagiaLicenseFieldsUtils.companyRegNumberCheck(companyRegNumber)) {
			errors.rejectValue("companyRegNumber", "validation.shareholder.organization.companyRegistrationNumber");
		}
		if (StringUtils.isEmpty(capital) || !sagiaLicenseFieldsUtils.capitalCheck(capital) || !SagiaValidationUtil.isPositiveInteger(capital)) {
			errors.rejectValue("capital", "validation.shareholder.organization.companyCapital");
		}

		if (foreignEntityLegalStatus.equals(legalStatus)) {
			try {
				if (StringUtils.isEmpty(form.getSharesPercentage()) ||
						!SagiaValidationUtil.isPercentage(form.getSharesPercentage()) ||
						Double.valueOf(form.getSharesPercentage()) > 100) {
					errors.rejectValue("sharesPercentage", "validation.shareholder.organization.companySharesPercentageForeignEntity");
				}
			} catch (Exception e) {
				LOG.warn(e.getMessage(), e);
				errors.rejectValue("sharesPercentage", "validation.shareholder.organization.companySharesPercentageForeignEntity");
			}

		} else {

			if (StringUtils.isEmpty(form.getSharesPercentage()) || !sagiaLicenseFieldsUtils.sharesPercentageCheck(form.getSharesPercentage())
					|| !SagiaValidationUtil.isPercentage(form.getSharesPercentage())) {
				errors.rejectValue("sharesPercentage", "validation.shareholder.organization.companySharesPercentage");
			}

		}

		if (StringUtils.isEmpty(section)) {
			errors.rejectValue("section", "validation.shareholder.organization.companySection");
		}

		if (StringUtils.isEmpty(division)) {
			errors.rejectValue("division", "validation.shareholder.organization.companyDivision");
		}

		if (StringUtils.isEmpty(parentCompanyName)) {
			errors.rejectValue("parentCompanyName", "validation.shareholder.organization.parentCompanyName");
		}
		if (StringUtils.isEmpty(parentCompanyCountry)) {
			errors.rejectValue("parentCompanyCountry", "validation.shareholder.organization.parentCompanyCountry");
		}

		if (StringUtils.isEmpty(companyCountry)) {
			errors.rejectValue("companyCountry", "validation.shareholder.organization.companyCountry");
		}
		if (StringUtils.isEmpty(countryOfReg)) {
			errors.rejectValue("countryOfReg", "validation.shareholder.organization.companyCountryOfRegistration");
		}

		if (StringUtils.isEmpty(city) || !sagiaLicenseFieldsUtils.cityCheck(city)) {
			errors.rejectValue("city", "validation.shareholder.organization.companyCity");
		}

		if (StringUtils.isEmpty(address) || !sagiaLicenseFieldsUtils.addressCheck(address)) {
			errors.rejectValue("address", "validation.shareholder.organization.companyAddress");
		}
		if (StringUtils.isEmpty(postalCode) || !sagiaLicenseFieldsUtils.postalCodeCheck(postalCode)) {
			errors.rejectValue("postalCode", "validation.shareholder.organization.companyPostalCode");
		}

		if (StringUtils.isEmpty(poBox) || !sagiaLicenseFieldsUtils.poBoxCheck(poBox)) {
			errors.rejectValue("poBox", "validation.shareholder.organization.companyPOBox");
		}

		if (StringUtils.isEmpty(countryCodeTelephone) || !sagiaLicenseFieldsUtils.countryCodeCheck(countryCodeTelephone)) {
			errors.rejectValue("countryCodeTelephone", "validation.shareholder.organization.companyCountryCodeForTelephone");
		}
		if (StringUtils.isEmpty(telephoneNumber) || !sagiaLicenseFieldsUtils.telePhoneNumberCheck(telephoneNumber)) {
			errors.rejectValue("telephoneNumber", "validation.shareholder.organization.companyTelephone");
		}
		if (StringUtils.isEmpty(countryCodeMobile) || !sagiaLicenseFieldsUtils.countryCodeCheck(countryCodeMobile)) {
			errors.rejectValue("countryCodeMobile", "validation.shareholder.organization.companyCountryCodeForMobile");
		}
		if (StringUtils.isEmpty(mobileNumber) || !sagiaLicenseFieldsUtils.mobilePhoneNumberCheck(mobileNumber)) {
			errors.rejectValue("mobileNumber", "validation.shareholder.organization.companyMobile");
		}
		if (StringUtils.isEmpty(email) || !SagiaValidationUtil.isEmail(email)) {
			errors.rejectValue("email", "validation.shareholder.organization.companyEmail");
		}
		if (StringUtils.isEmpty(website) || !SagiaValidationUtil.isWebAddress(website) || !sagiaLicenseFieldsUtils.websiteCheck(website)) {
			errors.rejectValue("website", "validation.shareholder.organization.companyWebsite");
		}

		SagiaCountryModel companyCountryFound = sagiaCountryDAO.getCountryForCode(companyCountry);

		if (!companyMemoAssociationAdded && companyCountryFound != null && companyCountryFound.getIsShareHolderCheck() != null && companyCountryFound.getIsShareHolderCheck()) {
			errors.rejectValue("companyMemoAssociation", "validation.shareholder.organization.companyMemoAssociationFile");
		}
		
		if(!professionalLicenseFileAdded && professionalLicense) {
			errors.rejectValue("professionalLicenseCertificate", "validation.shareholder.organization.professionalLicenseCertificate");
		}
		
		if(!mofaNumberVerified && !"SA".equals(companyCountry)) {
			if (!commercialRegCopyFileAdded && !"SA".equals(companyCountry)) {
				errors.rejectValue("commercialRegCopy", "validation.shareholder.organization.companyRegistrationFile");
			}
			if((!licenseApplyService.getEntityInformation().getLicenseType().getCode().equals("11")) && (!lastYearFinStatementFileAdded))
			{
				errors.rejectValue("lastYearFinStatement", "validation.shareholder.organization.companyFinancialStatementFile");
			}
		}

		if(form.getDelegateInfo()!= null && form.getDelegateInfo().isDelegate()){
			DelegateInformationData delegateInformationData = form.getDelegateInfo();
			
			delegateInformationData.setMofaNumberVerified(mofaNumberVerified);
			Map<String,String> delegateError = delegateInfoShareHoldersValidator.validate(delegateInformationData);
			for(Map.Entry<String, String> error:delegateError.entrySet()){
				errors.rejectValue(error.getKey(), error.getValue());
			}
		}
	}
}
