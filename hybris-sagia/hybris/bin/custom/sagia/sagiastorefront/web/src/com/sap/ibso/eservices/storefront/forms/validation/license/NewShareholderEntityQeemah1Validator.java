package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;
import com.sap.ibso.eservices.facades.data.zqeemah.DelegateInfo;
import com.sap.ibso.eservices.facades.data.zqeemah.ShareholderInfo;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

@Component("newShareholderEntityQeemah1Validator")
public class NewShareholderEntityQeemah1Validator {

    private static final Logger LOG = Logger.getLogger(NewShareholderEntityQeemah1Validator.class);
    private final String foreignEntityLegalStatus = "BRFC";
    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "i18NService")
    private I18NService i18NService;
    
    @Resource
    private DelegateInfoValidator delegateInfoValidator;

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S138 | Methods should not have too many lines
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776", "squid:MethodCyclomaticComplexity","squid:S138"})
    public void validateNewShareholderEntity(ShareholderInfo shareholderInfo, OrganizationInformation organizationInformation, Map<String, String> errors, List<SagiaCountryData> shareHolderCheckCountries) {

        if(StringUtils.isEmpty(shareholderInfo.getOrganizationNameEnglish())
                || !SagiaValidationUtil.isOrgNameEnglish(shareholderInfo.getOrganizationNameEnglish())
                || shareholderInfo.getOrganizationNameEnglish().length() > 100) {
            errors.put("organizationNameEnglish", messageSource.getMessage("validation.shareholder.organization.organizationNameEnglish", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isNotEmpty(shareholderInfo.getOrganizationNameArabic())
                && (!SagiaValidationUtil.isArabic(shareholderInfo.getOrganizationNameArabic())
                || shareholderInfo.getOrganizationNameArabic().length() > 100)) {
            errors.put("organizationNameArabic", messageSource.getMessage("validation.shareholder.organization.organizationNameArabic", null, i18NService.getCurrentLocale()));
        }

        if(StringUtils.isEmpty(shareholderInfo.getOrganizationLegalStatus())) {
            errors.put("organizationLegalStatus",  messageSource.getMessage("validation.shareholder.organization.organizationLegalStatus", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getMultinationalCompany())) {
            errors.put("multinationalCompany", messageSource.getMessage( "validation.shareholder.organization.multinationalCompany", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyRegistrationNumber()) || !StringUtils.isAlphanumeric(shareholderInfo.getCompanyRegistrationNumber())){
            errors.put("companyRegistrationNumber", messageSource.getMessage( "validation.shareholder.organization.companyRegistrationNumber", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyCapital()) || !SagiaValidationUtil.isPositiveInteger(shareholderInfo.getCompanyCapital())){
            errors.put("companyCapital",messageSource.getMessage(  "validation.shareholder.organization.companyCapital", null, i18NService.getCurrentLocale()));
        }
        
        //if(StringUtils.isEmpty(shareholderInfo.getMofaNumber()) || !StringUtils.isAlphanumeric(shareholderInfo.getMofaNumber())){
            //errors.put("mofaNumber", messageSource.getMessage( "validation.shareholder.organization.mofaNumber", null, i18NService.getCurrentLocale()));
        //}

        if (foreignEntityLegalStatus.equals(organizationInformation.getLegalStatus())) {
            try {
                if (StringUtils.isEmpty(shareholderInfo.getCompanySharesPercentage()) ||
                        !SagiaValidationUtil.isPercentage(shareholderInfo.getCompanySharesPercentage()) ||
                    Double.valueOf(shareholderInfo.getCompanySharesPercentage()) != 100) {
                    errors.put("companySharesPercentage", messageSource.getMessage("validation.shareholder.organization.companySharesPercentageForeignEntity", null, i18NService.getCurrentLocale()));
                }
            } catch (Exception e) {
                    LOG.warn(e.getMessage(), e);
                    errors.put("companySharesPercentage", messageSource.getMessage("validation.shareholder.organization.companySharesPercentageForeignEntity", null, i18NService.getCurrentLocale()));
            }

        } else {

                if (StringUtils.isEmpty(shareholderInfo.getCompanySharesPercentage()) ||
                        !SagiaValidationUtil.isPercentage(shareholderInfo.getCompanySharesPercentage())) {
                     errors.put("companySharesPercentage", messageSource.getMessage("validation.shareholder.organization.companySharesPercentage", null, i18NService.getCurrentLocale()));
                }

        }

        if(StringUtils.isEmpty(shareholderInfo.getCompanySection())){
            errors.put("companySection", messageSource.getMessage("validation.shareholder.organization.companySection", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyDivision())){
            errors.put("companyDivision", messageSource.getMessage("validation.shareholder.organization.companyDivision", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getParentCompanyName())){
            errors.put("parentCompanyName", messageSource.getMessage( "validation.shareholder.organization.parentCompanyName", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getParentCompanyCountry())){
            errors.put("parentCompanyCountry", messageSource.getMessage( "validation.shareholder.organization.parentCompanyCountry", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyCountry())){
            errors.put("companyCountry", messageSource.getMessage("validation.shareholder.organization.companyCountry", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyCountryOfRegistration())){
            errors.put("companyCountryOfRegistration", messageSource.getMessage("validation.shareholder.organization.companyCountryOfRegistration", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyCity())){
            errors.put("companyCity", messageSource.getMessage( "validation.shareholder.organization.companyCity", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyAddress())){
            errors.put("companyAddress", messageSource.getMessage( "validation.shareholder.organization.companyAddress", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyPostalCode())){
            errors.put("companyPostalCode", messageSource.getMessage("validation.shareholder.organization.companyPostalCode", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isNotEmpty(shareholderInfo.getCompanyPostalCode()) && shareholderInfo.getCompanyPostalCode().length() > 10) {
            errors.put("companyPostalCode", messageSource.getMessage( "validation.shareholder.person.postalCode.length", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyPOBox()) || !StringUtils.isNumeric(shareholderInfo.getCompanyPOBox())){
            errors.put("companyPOBox", messageSource.getMessage("validation.shareholder.organization.companyPOBox", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyCountryCodeForTelephone()) || !StringUtils.isNumeric(shareholderInfo.getCompanyCountryCodeForTelephone())){
            errors.put("companyCountryCodeForTelephone", messageSource.getMessage("validation.shareholder.organization.companyCountryCodeForTelephone", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyTelephone()) || !StringUtils.isNumeric(shareholderInfo.getCompanyTelephone())){
            errors.put("companyTelephone", messageSource.getMessage("validation.shareholder.organization.companyTelephone", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyCountryCodeForMobile()) || !StringUtils.isNumeric(shareholderInfo.getCompanyCountryCodeForMobile())){
            errors.put("companyCountryCodeForMobile", messageSource.getMessage("validation.shareholder.organization.companyCountryCodeForMobile", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyMobile()) || !StringUtils.isNumeric(shareholderInfo.getCompanyMobile())){
            errors.put("companyMobile", messageSource.getMessage("validation.shareholder.organization.companyMobile", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyEmail()) || !SagiaValidationUtil.isEmail(shareholderInfo.getCompanyEmail())){
            errors.put("companyEmail", messageSource.getMessage("validation.shareholder.organization.companyEmail", null, i18NService.getCurrentLocale()));
        }
        if(StringUtils.isEmpty(shareholderInfo.getCompanyWebsite()) || !SagiaValidationUtil.isWebAddress(shareholderInfo.getCompanyWebsite())){
            errors.put("companyWebsite", messageSource.getMessage("validation.shareholder.organization.companyWebsite", null, i18NService.getCurrentLocale()));
        }
                
        if(StringUtils.isNotEmpty(shareholderInfo.getCompanyCountry()) && !"SA".equalsIgnoreCase(shareholderInfo.getCompanyCountry())) {
        	
        	if(!"true".equalsIgnoreCase(shareholderInfo.getMofaNumberVerified())) {
        		if(StringUtils.isEmpty(shareholderInfo.getCompanyRegistrationFileName())){
                    errors.put("companyRegistrationFileName", messageSource.getMessage("validation.shareholder.organization.companyRegistrationFile", null, i18NService.getCurrentLocale()));
                }

                if (shareholderInfo.getCompanyRegistrationFile() == null || !SagiaValidationUtil.isBase64(shareholderInfo.getCompanyRegistrationFile())) {
                    errors.put("companyRegistrationFileName", messageSource.getMessage("validation.shareholder.organization.valid.content.companyRegistrationFile", null, i18NService.getCurrentLocale()));
                }

                if(StringUtils.isEmpty(shareholderInfo.getCompanyFinancialStatementFileName())){
                    errors.put("companyFinancialStatementFileName", messageSource.getMessage("validation.shareholder.organization.companyFinancialStatementFile", null, i18NService.getCurrentLocale()));
                }

                if (shareholderInfo.getCompanyFinancialStatementFile() == null || !SagiaValidationUtil.isBase64(shareholderInfo.getCompanyFinancialStatementFile())) {
                    errors.put("companyFinancialStatementFileName", messageSource.getMessage("validation.shareholder.organization.valid.content.companyFinancialStatementFile", null, i18NService.getCurrentLocale()));
                }
        	}
        }
        
        if(StringUtils.isNotEmpty(shareholderInfo.getCompanyCountry()) && shareHolderCheckCountries.size() > 0){
        	for(SagiaCountryData shareHolderCheckCountry : shareHolderCheckCountries) {
        		if(shareholderInfo.getCompanyCountry().equalsIgnoreCase(shareHolderCheckCountry.getCode())) {
        			if(shareholderInfo.getCompanyMemoAssociationFile() == null || StringUtils.isEmpty(shareholderInfo.getCompanyMemoAssociationFileName())){
        	            errors.put("companyMemoAssociationFileName", messageSource.getMessage("validation.shareholder.organization.companyFinancialStatementFile", null, i18NService.getCurrentLocale()));
        	        }
        		}
            }
        }
        
        
        if(shareholderInfo.getHasDelegateInfo().equalsIgnoreCase("true")){
			DelegateInfo shareholderDelegateInfo = shareholderInfo.getDelegate();
			Map<String,String> delegateError=delegateInfoValidator.validate(shareholderDelegateInfo);
			for(Map.Entry<String, String> error:delegateError.entrySet()){
				errors.put(error.getKey(), messageSource.getMessage((error.getValue()), null, i18NService.getCurrentLocale()));
			}
        }
    }
}
