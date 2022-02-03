package com.sap.ibso.eservices.storefront.forms.validation.license.entity;

import static com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil.isWebAddress;

import javax.annotation.Resource;

import com.sap.ibso.eservices.facades.data.EntityInformationData;
import com.sap.ibso.eservices.storefront.util.SagiaLicenseFieldsUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.storefront.forms.translator.StorefrontLozalizedMessageTranslator;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;

import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;

@Component("entityInfoValidator")
public class EntityInfoValidator implements Validator {

    @Resource(name = "storefrontLozalizedMessageTranslator")
    private StorefrontLozalizedMessageTranslator storefrontLozalizedMessageTranslator;

    @Resource(name= "sagiaLicenseFieldsUtils")
    private SagiaLicenseFieldsUtils sagiaLicenseFieldsUtils;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return OrganizationInformation.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        final EntityInformationData entityInformationData = (EntityInformationData) object;

        if(entityInformationData.isIsEntrepreneur())
        {
        	entityInformationData.setLicenseDuration("1");
        }
        
        entityInformationData.setEntityName(XSSFilterUtil.filter(entityInformationData.getEntityName()));
        
        if(StringUtils.isEmpty(entityInformationData.getEntityName())
                ||  !sagiaLicenseFieldsUtils.isMaxLength(entityInformationData.getEntityName(), 100)
                ||  !sagiaLicenseFieldsUtils.isAlphaNumericWithSpace(entityInformationData.getEntityName())) {
                    errors.rejectValue("entityName", "validation.basicinformation.entityName");
        }

        entityInformationData.setEntityNameArabic(XSSFilterUtil.filter(entityInformationData.getEntityNameArabic()));
               
        if(StringUtils.isNotEmpty(entityInformationData.getEntityNameArabic())
                && (!SagiaValidationUtil.isArabic(entityInformationData.getEntityNameArabic())
                || !sagiaLicenseFieldsUtils.isMaxLength(entityInformationData.getEntityNameArabic(), 80))) {
            errors.rejectValue("entityNameArabic", "validation.basicinformation.entityNameArabic");
        }
        if(entityInformationData.getLicenseType().toString().equals("11"))
        {
            entityInformationData.setLegalStatus("BRFC");
        }
        entityInformationData.setLegalStatus(XSSFilterUtil.filter(entityInformationData.getLegalStatus()));
        
        if(StringUtils.isEmpty(entityInformationData.getLegalStatus())) {
            errors.rejectValue("legalStatus", "validation.basicinformation.legalStatus");
        }
        
        entityInformationData.setBasicInfoExtendedMultinationalCompany(XSSFilterUtil.filter(entityInformationData.getBasicInfoExtendedMultinationalCompany()));       

        if(StringUtils.isEmpty(entityInformationData.getBasicInfoExtendedMultinationalCompany())) {
            errors.rejectValue("basicInfoExtendedMultinationalCompany", "validation.basicinformation.multinationalCompany");
        }

        entityInformationData.setCapital(XSSFilterUtil.filter(entityInformationData.getCapital()));       
        
        if((!StringUtils.isNumeric(entityInformationData.getCapital())
                || !sagiaLicenseFieldsUtils.capitalCheck(entityInformationData.getCapital())
                || !SagiaValidationUtil.isPositiveInteger(entityInformationData.getCapital()))) {
            errors.rejectValue("capital", "validation.basicinformation.capital");
        }

        entityInformationData.setEmail(XSSFilterUtil.filter(entityInformationData.getEmail()));       
               
        if(StringUtils.isEmpty(entityInformationData.getEmail()) || !SagiaValidationUtil.isEmail(entityInformationData.getEmail())) {
            errors.rejectValue("email", "validation.basicinformation.email");
        }

        entityInformationData.setTelephone(XSSFilterUtil.filter(entityInformationData.getTelephone()));

        if(StringUtils.isEmpty(entityInformationData.getTelephone()) || !sagiaLicenseFieldsUtils.telePhoneNumberCheck(entityInformationData.getTelephone())){
            errors.rejectValue("telephone", "validation.basicinformation.telephone");
        }
        
        entityInformationData.setMobilePhone(XSSFilterUtil.filter(entityInformationData.getMobilePhone()));       
        
        if(StringUtils.isEmpty(entityInformationData.getMobilePhone()) || !sagiaLicenseFieldsUtils.mobilePhoneNumberCheck(entityInformationData.getMobilePhone())){
            errors.rejectValue("mobilePhone", "validation.basicinformation.mobilePhone");
        }
        
        entityInformationData.setWebsite(XSSFilterUtil.filter(entityInformationData.getWebsite()));       
        
        String website = entityInformationData.getWebsite();
        if(StringUtils.isEmpty(website) || !isWebAddress(website) || !sagiaLicenseFieldsUtils.websiteCheck(website)){
            errors.rejectValue("website", "validation.basicinformation.website");
        }

        entityInformationData.setCountry(XSSFilterUtil.filter(entityInformationData.getCountry()));       
               
        if(StringUtils.isEmpty(entityInformationData.getCountry())){
            errors.rejectValue("country", "validation.basicinformation.country");
        }
        
        entityInformationData.setRegion(XSSFilterUtil.filter(entityInformationData.getRegion()));       
        
        if(StringUtils.isEmpty(entityInformationData.getRegion())){
            errors.rejectValue("region", "validation.basicinformation.region");
        }
        
        entityInformationData.setCity(XSSFilterUtil.filter(entityInformationData.getCity()));       
        
        if(StringUtils.isEmpty(entityInformationData.getCity())){
            errors.rejectValue("city", "validation.basicinformation.city");
        }

        entityInformationData.setAddress(XSSFilterUtil.filter(entityInformationData.getAddress()));       
        
        if(StringUtils.isEmpty(entityInformationData.getAddress()) || !sagiaLicenseFieldsUtils.addressCheck(entityInformationData.getAddress())){
            errors.rejectValue("address", "validation.basicinformation.address");
        }
        
        entityInformationData.setPoBox(XSSFilterUtil.filter(entityInformationData.getPoBox()));              

        if(StringUtils.isEmpty(entityInformationData.getPoBox()) || !sagiaLicenseFieldsUtils.poBoxCheck(entityInformationData.getPoBox())){
            errors.rejectValue("poBox", "validation.basicinformation.poBox");
        }
        
        entityInformationData.setPostalCode(XSSFilterUtil.filter(entityInformationData.getPostalCode()));              

        if(StringUtils.isEmpty(entityInformationData.getPostalCode()) || !sagiaLicenseFieldsUtils.postalCodeCheck(entityInformationData.getPostalCode())){
            errors.rejectValue("postalCode", "validation.basicinformation.postalCode");
        }

        entityInformationData.setInvestment(XSSFilterUtil.filter(entityInformationData.getInvestment()));              

        if(StringUtils.isEmpty(entityInformationData.getInvestment())) {
            errors.rejectValue("investment", "validation.basicinformation.investment");
        }

        final boolean boardResolutionFileAdded = entityInformationData.isBoardResolutionFileAdded();
        final boolean letterOfSupportFileAdded = entityInformationData.isLetterOfSupportFileAdded();
        final boolean entityFinancialFileAdded = entityInformationData.isEntityFinancialStatementFileAdded();
        final boolean commercialRegMainEntryFileAdded = entityInformationData.isCommercialRegMainEntryFileAdded();
        final boolean commercialRegBranch1EntryFileAdded = entityInformationData.isCommercialRegBranch1FileAdded();
        final boolean commercialRegBranch2EntryFileAdded = entityInformationData.isCommercialRegBranch2FileAdded();


        final boolean financialStatementFileAdded = entityInformationData.isFinancialStatementFileAdded();
        final boolean iqamaFileAdded = entityInformationData.isIqamaFileAdded();
        final boolean crCertificateFileAdded = entityInformationData.isCrCertificateFileAdded();
        final boolean gosiCertificateFileAdded = entityInformationData.isGosiCertificateFileAdded();
        final boolean noObjectionCertificateFileAdded = entityInformationData.isNoObjectionCertificateFileAdded();

        if(entityInformationData.isIsEntrepreneur()){

            if (!boardResolutionFileAdded) {
                errors.rejectValue("boardResolutionFile", "validation.basicinformation.file");
            }

            if (!letterOfSupportFileAdded) {
                errors.rejectValue("letterOfSupportFile", "validation.basicinformation.file");
            }

        }
        
        if(entityInformationData.isIsPreApprovalNumber()) {
        	 if (!financialStatementFileAdded) {
                 errors.rejectValue("financialStatementFileAdded", "validation.basicinformation.file");
             }

             if (!iqamaFileAdded) {
                 errors.rejectValue("iqamaFileAdded", "validation.basicinformation.file");
             }
             if (!crCertificateFileAdded) {
                 errors.rejectValue("crCertificateFileAdded", "validation.basicinformation.file");
             }

             if (!gosiCertificateFileAdded) {
                 errors.rejectValue("gosiCertificateFileAdded", "validation.basicinformation.file");
             }
             if (!noObjectionCertificateFileAdded) {
                 errors.rejectValue("noObjectionCertificateFileAdded", "validation.basicinformation.file");
             }
        }

        if(entityInformationData.getLicenseType() != null && entityInformationData.getLicenseType().equals("6")) {
            entityInformationData.setTemporaryLicenseText(XSSFilterUtil.filter(entityInformationData.getTemporaryLicenseText()));
            if(StringUtils.isEmpty(entityInformationData.getTemporaryLicenseText())) {
                errors.rejectValue("temporaryLicenseTextBoxContent", "validation.basicinformation.temporaryLicenseText");
            }
        }
        if(entityInformationData.getLicenseType() != null && entityInformationData.getLicenseType().equals("11")) {
            if (!entityFinancialFileAdded) {
                errors.rejectValue("entityFinancialStatementFileAdded", "validation.basicinformation.file");
            }
            if (!commercialRegMainEntryFileAdded) {
                errors.rejectValue("commercialRegMainEntryFileAdded", "validation.basicinformation.file");
            }
            if (!commercialRegBranch1EntryFileAdded) {
                errors.rejectValue("commercialRegBranch1FileAdded", "validation.basicinformation.file");
            }
            if (!commercialRegBranch2EntryFileAdded) {
                errors.rejectValue("commercialRegBranch2FileAdded", "validation.basicinformation.file");
            }
        }

    }

    private String getMessage(String key) {
    		return storefrontLozalizedMessageTranslator.getLocalizedMessageValue(key);
    }
}
