package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.google.gson.internal.LinkedTreeMap;
import com.sap.ibso.eservices.core.sagia.services.SagiaFormatProvider;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.util.*;

@Component("sagiaLicenseApplyValidator")
public class SagiaLicenseApplyValidator implements SagiaLicenseValidator {
    private static final String SHARES_PERCENTAGE = "sharesPercentage";
    private static final String POSTAL_CODE = "postalCode";
    private static final String PO_BOX = "poBox";
    private static final String EMAIL = "email";
    private static final String TELEPHONE = "telephone";
    private static final String CITY = "city";
    private static final String COUNTRY = "country";
    private static final String PREVIOUS_NATIONALITY = "previousNationality";
    private static final String PREMIUM_RESIDENT = "premiumResident";
    private static final String CURRENT_NATIONALITY = "currentNationality";
    private static final String PASSPORT_EXPIRY_DATE = "passportExpiryDate";
    private static final String MOBILE_NUMBER = "mobileNumber";
    private static final String ROLE = "role";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String NATIONALITY = "nationality";
    private static final String ORGANIZATION_NAME_ENGLISH = "organizationNameEnglish";
    private static final String ORGANIZATION_NAME_ARABIC = "organizationNameArabic";

    private static final String VALIDATION = "validation.";
    private static final String LICENSE_VALIDATION_ATTACHMENT = VALIDATION + "attachment";
    private static final String LICENSE_VALIDATION_CONTACT_PERSON = VALIDATION + "contactPerson.";
    private static final String LICENSE_VALIDATION_EXISTING_SHAREHOLDER = VALIDATION + "existingShareholder.";
    private static final String LICENSE_VALIDATION_SHAREHOLDER = VALIDATION + "shareholder.";
    private static final String LICENSE_VALIDATION_SHAREHOLDER_PERSON = LICENSE_VALIDATION_SHAREHOLDER + "person.";
    private static final String LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION = LICENSE_VALIDATION_SHAREHOLDER + "organization.";
    private static final String LICENSE_VALIDATION_DELEGATE = LICENSE_VALIDATION_SHAREHOLDER + "delegate.";
    private static final String ACADEMIC_TITLE = "academicTitle";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String PASSPORT_NUMBER = "passportNumber";
    private static final String PASSPORT_ISSUE_DATE = "passportIssueDate";
    private static final String FULL_NAME_ENGLISH = "fullNameEnglish";
    private static final String FIRST_NAME_ARABIC = "firstNameArabic";
    private static final String LAST_NAME_ARABIC = "lastNameArabic";
    private static final String MOBILE = "mobile";
    private static final String PASSPORT_FILE = "passportFile";
    private static final String TYPE = "type";
    private static final String NAME = "name";
    private static final String ENTITY_NUMBER = "entityNumber";
    private static final String PARENT_COMPANY_COUNTRY = "parentCompanyCountry";
    private static final String COMMERCIAL_REGISTRATION_FILE = "commercialRegistrationFile";
    private static final String LAST_BUDGET_FILE = "lastBudgetFile";
    private static final String PERCENTAGE = "percentage";
    private static final String EDUCATION = "education";
    private static final String TITLE = "title";
    private static final String GENDER = "gender";
    private static final String ORGANIZATION_LEGAL_STATUS = "organizationLegalStatus";
    private static final String MULTINATIONAL_COMPANY = "multinationalCompany";
    private static final String COMPANY_CAPITAL = "companyCapital";
    private static final String PARENT_COMPANY_NAME = "parentCompanyName";
    private static final String COMPANY_COUNTRY = "companyCountry";
    private static final String COMPANY_FINANCIAL_STATEMENT_FILE_NAME = "companyFinancialStatementFileName";
    private static final String COMPANY_REGISTRATION_FILE = "companyRegistrationFile";
    private static final String COMPANY_SHARES_PERCENTAGE = "companySharesPercentage";
    private static final String COMPANY_DIVISION = "companyDivision";
    private static final String COMPANY_SECTION = "companySection";
    private static final String COMPANY_MOBILE = "companyMobile";
    private static final String COMPANY_WEBSITE = "companyWebsite";
    private static final String COMPANY_EMAIL = "companyEmail";
    private static final String COMPANY_POBOX = "companyPOBox";
    private static final String COMPANY_TELEPHONE = "companyTelephone";
    private static final String COMPANY_POSTAL_CODE = "companyPostalCode";
    private static final String COMPANY_CITY = "companyCity";
    private static final String COMPANY_COUNTRY_OF_REGISTRATION = "companyCountryOfRegistration";
    private static final String IS_ENTREPRENEUR = "isEntrepreneur";
    private static final String IS_MORE_THAN_2_BRANCH = "isMoreThan2Branch";
    private static final String ATTACHMENTS = "attachments";
    private static final String MAIN_BRANCH_CR_FILE = "mainBranchCRFile";
    private static final String OTHER_BRANCH_CR_1_FILE = "otherBranchCR1File";
    private static final String OTHER_BRANCH_CR_2_FILE = "otherBranchCR2File";
    private static final String BOARD_RESOLUTION_FILE = "boardResolutionFile";
    private static final String LETTER_OF_SUPPORT_FILE = "letterOfSupportFile";
    private static final String TOTAL_PERCENTAGE = "totalPercentage";
    private static final String SHAREHOLDERS_TOTAL_PERCENTAGE = "shareholdersTotalPercentage";
    private static final String CONTACT_PERSON = "contactPerson";
    private static final String QEEMAH2_DATA = "qeemah2Data";
    private static final String SHAREHOLDERS = "shareholders";
    private static final String BASIC_INFORMATION = "basicInformation";
    private static final String EXISTING_SHAREHOLDERS = "existingShareholders";
    private static final String NEW_SHAREHOLDERS = "newShareholders";
    private static final String QEEMAH1_DATA = "qeemah1Data";
    private static final String BASIC_INFORMATION_EXTENDED = "basicInformationExtended";
    private static final String VAL = "val";
    private static final String LICENSEDURATION = "licenseYear";

    //Delegate
    private static final String DELEGATE = "delegate";
    private static final String DELEGATE_IDTYPE = "idType";
    private static final String DELEGATE_IDNUMBER = "idNumber";
    private static final String DELEGATE_DATEOFBIRTH = "dateofBirth";
    private static final String DELEGATE_FIRSTNAME_ARABIC = "firstNameArabic";
    private static final String DELEGATE_LASTNAME_ARABIC = "lastNameArabic";
    private static final String DELEGATE_FULLNAME_ENGLISH = "fullNameEnglish";
    private static final String DELEGATE_GENDER = "gender";
    private static final String DELEGATE_ISSUEDATE = "issueDate";
    private static final String DELEGATE_EXPIRYDATE = "expiryDate";
    private static final String DELEGATE_COUNTRY = "country";
    private static final String DELEGATE_NATIONALITY = "nationality";
    private static final String DELEGATE_POSTALCODE = "postalCode";
    private static final String DELEGATE_POBOX = "poBox";
    private static final String DELEGATE_TELEPHONE = "telephone";
    private static final String DELEGATE_MOBILE = "mobile";
    private static final String DELEGATE_EMAIL = "email";
    private static final String DELEGATE_IDCOPYFILE = "idCopyFile";
    private static final String DELEGATE_AUTHLETTERFILE = "authorizationLetterFile";
    private static final String DELEGATE_NICVERIFED = "nicVerified";
    
    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;
    @Resource(name = "sagiaFormatProvider")
    SagiaFormatProvider sagiaFormatProvider;
    @Resource(name = "i18NService")
    private I18NService i18NService;
    @Resource(name = "basicOrganizationInformationValidator")
    private BasicOrganizationInformationValidator basicOrganizationInformationValidator;
    @Resource(name = "organizationInformationValidator")
    OrganizationInformationValidator organizationInformationValidator;
    
    private static final String QEEMAH = "qeemah";
    private static final String QEEMAH_1 = "1.0";

    private static final String SHAREHOLDER_PERSON = "Person";
    private static final String SHAREHOLDER_ORGANIZATION = "Organization";

    private static final BigDecimal TOTAL_EXPECTED_PERCENTAGE = new BigDecimal(100);

    private static final String ARABIC_REGEX = "^[\\s\\u060C\\u060D\\u0610-\\u0615\\u061B\\u061C\\u061E\\u061F\\u0621-" +
            "\\u062F\\u0630-\\u063A\\u0640-\\u064F\\u0650-\\u065E\\u0671\\u0673\\u0674\\u0678\\u06BE\\u06CC\\u06CE" +
            "\\u06CF\\d\\?><\\(\\)\"\\\\;,\\{\\}\\[\\]\\-_\\+=!@.\\#\\$%^&\\*\\|\\']+$";
    private static final String ENGLISH_REGEX = "^[\\s\\w\\d\\?><;,\\{\\}\\[\\]\\-_\\+=!@.\\#\\$%^&\\*\\|\\']+$";
    private static final String INTEGER_REGEX = "^\\d*$";
    private static final String NUMERIC_REGEX = "^[0-9.]+$";
    private static final String WEBSITE_REGEX = "^(http:\\/\\/|https:\\/\\/|HTTP:\\/\\/|HTTPS:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-zA-Z]{2,3}.?([a-zA-Z]+)?$";
    private static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*?\\.[A-Za-z]{2,6}|" +
            "(\\d{1,3}\\.){3}\\d{1,3})(:\\d{4})?$";
    private static final String GENDER_REGEX = "Female|Male";

    @Override
    public Map<String, Object> validate(Map<String, Object> objectMap) {
        final Locale locale = resolveLocale();
        Map<String, Object> result = new HashMap<>();
        validateLicenseDuration(objectMap, resolveLocale(), result);
        validateEntrepreneurFlow(objectMap, resolveLocale(), result);
        validateRHQFlow(objectMap, resolveLocale(), result);

        String qeemah = String.valueOf(objectMap.get(QEEMAH));
        if (QEEMAH_1.equals(qeemah)) { // Qeemah 1 validation path
            validateExtendedOrganizationInformation(objectMap, locale, result);
        } else { // Qeemah 2 validation path
            validateBasicOrganizationInformation(objectMap, locale, result);
        }
        validateBusinessActivities(objectMap, resolveLocale(), result);
        return result;
    }
    private void validateLicenseDuration(Map<String, Object> objectMap, Locale locale, Map<String, Object> result) {

        if (objectMap.containsKey(IS_ENTREPRENEUR) || !(boolean) objectMap.get(IS_ENTREPRENEUR)) {
        	String licenseDuartion=(String)objectMap.get(LICENSEDURATION);
        	if (licenseDuartion == null || licenseDuartion.isEmpty()) {
        		Map<String, String> licenseInformationMap = new HashMap<>();
        		licenseInformationMap.put("licenseYear", messageSource.getMessage("license.validation.license.duration", null, locale));
                result.put(BASIC_INFORMATION, licenseInformationMap);
           }
        }
    }

    private void validateEntrepreneurFlow(Map<String, Object> objectMap, Locale locale, Map<String, Object> result) {
        String isEntrepreneurKey = IS_ENTREPRENEUR;
        if (objectMap.containsKey(isEntrepreneurKey) && (boolean) objectMap.get(isEntrepreneurKey)) {
            Map attachments = (Map) objectMap.get(ATTACHMENTS);

            if(attachments != null){

                Map<String, String> attachmentsResult = new HashMap<>();
                String boardResolutionFileKey = BOARD_RESOLUTION_FILE;
                String boardResolutionFile = (String) attachments.get(boardResolutionFileKey);
                if (boardResolutionFile == null || boardResolutionFile.isEmpty()) {
                    attachmentsResult.put(boardResolutionFileKey, messageSource.getMessage(LICENSE_VALIDATION_ATTACHMENT, null, locale));
                }

                String letterOfSupportFileKey = LETTER_OF_SUPPORT_FILE;
                String letterOfSupportFile = (String) attachments.get(letterOfSupportFileKey);
                if (letterOfSupportFile == null || letterOfSupportFile.isEmpty()) {
                    attachmentsResult.put(letterOfSupportFileKey, messageSource.getMessage(LICENSE_VALIDATION_ATTACHMENT, null, locale));
                }

                if (MapUtils.isNotEmpty(attachmentsResult)) {
                    result.put(ATTACHMENTS, attachmentsResult);
                }

            }


        }
    }
    
    private void validateRHQFlow(Map<String, Object> objectMap, Locale locale, Map<String, Object> result) {
        String isMoreThan2Branch = IS_MORE_THAN_2_BRANCH;
        if (objectMap.containsKey(isMoreThan2Branch) && (boolean) objectMap.get(isMoreThan2Branch)) {
            Map attachments = (Map) objectMap.get(ATTACHMENTS);

            if(attachments != null){

                Map<String, String> attachmentsResult = new HashMap<>();
                String mainBranchCRFileKey = MAIN_BRANCH_CR_FILE;
                String mainBranchCRFile = (String) attachments.get(mainBranchCRFileKey);
                if (mainBranchCRFile == null || mainBranchCRFile.isEmpty()) {
                    attachmentsResult.put(mainBranchCRFileKey, messageSource.getMessage(LICENSE_VALIDATION_ATTACHMENT, null, locale));
                }
                
                String otherBranchCR1FileKey = OTHER_BRANCH_CR_1_FILE;
                String otherBranchCR1File = (String) attachments.get(otherBranchCR1FileKey);
                if (otherBranchCR1File == null || otherBranchCR1File.isEmpty()) {
                    attachmentsResult.put(otherBranchCR1FileKey, messageSource.getMessage(LICENSE_VALIDATION_ATTACHMENT, null, locale));
                } 
                
                String otherBranchCR2FileKey = OTHER_BRANCH_CR_2_FILE;
                String otherBranchCR2File = (String) attachments.get(otherBranchCR2FileKey);
                if (otherBranchCR2File == null || otherBranchCR2File.isEmpty()) {
                    attachmentsResult.put(otherBranchCR2FileKey, messageSource.getMessage(LICENSE_VALIDATION_ATTACHMENT, null, locale));
                }

                if (MapUtils.isNotEmpty(attachmentsResult)) {
                    result.put(ATTACHMENTS, attachmentsResult);
                }

            }


        }
    }

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity","squid:S3776"})
    private void validateExtendedOrganizationInformation(Map<String, Object> objectMap, Locale locale, Map<String, Object> result) {
        Map<String, String> basicInformationExtendedResultMap = organizationInformationValidator.validate((Map)objectMap.get(BASIC_INFORMATION_EXTENDED));
        if (MapUtils.isNotEmpty(basicInformationExtendedResultMap)) {
            result.put(BASIC_INFORMATION_EXTENDED, basicInformationExtendedResultMap);
        }

        // Validate extended contact information
        Map<String, Object> qeemah1result = new HashMap<>();
        Map<String, Object> qeemah1DataMap = (Map) objectMap.get(QEEMAH1_DATA);
        Map<String, Object> contactPersonExtendedMap = (Map) qeemah1DataMap.get(CONTACT_PERSON);
        Map<String, String> contactPersonExtendedResultMap = validateQeemah1ContactPerson(contactPersonExtendedMap, locale);

        if (MapUtils.isNotEmpty(contactPersonExtendedResultMap)) {
            qeemah1result.put(CONTACT_PERSON, contactPersonExtendedResultMap);
        }

        BigDecimal totalPercentage = new BigDecimal(0);
        List shareholders = (List) qeemah1DataMap.get(NEW_SHAREHOLDERS);
        List shareholdersResult = new ArrayList();
        if (shareholders != null && !shareholders.isEmpty()) {
            for (Object shareholder : shareholders) {
                if(SHAREHOLDER_PERSON.equalsIgnoreCase(((Map) shareholder).get("type").toString())){
                    totalPercentage = totalPercentage.add(new BigDecimal((String) ((Map) shareholder).get(SHARES_PERCENTAGE)));
                } else {
                    totalPercentage = totalPercentage.add(new BigDecimal((String) ((Map) shareholder).get(COMPANY_SHARES_PERCENTAGE)));
                }
                Map<String, String> shareholderResultMap = validateQeemah1Shareholder((Map) shareholder, locale);
                if(MapUtils.isNotEmpty(shareholderResultMap)) {
                    shareholdersResult.add(shareholderResultMap);
                }
            }
        }
        if (!shareholdersResult.isEmpty()) {
            qeemah1result.put(NEW_SHAREHOLDERS, shareholdersResult);
        }

        List existingShareholders = (List) qeemah1DataMap.get(EXISTING_SHAREHOLDERS);
        List existingShareholdersResult = new ArrayList();
        if (existingShareholders != null && !existingShareholders.isEmpty()) {
            for (Object existingShareholder : existingShareholders) {
                totalPercentage = totalPercentage.add(new BigDecimal((String) ((Map) existingShareholder).get(SHARES_PERCENTAGE)));
                Map<String, String> existingShareholderResultMap = validateQeemah1ExistingShareholder((Map) existingShareholder, locale);
                if(MapUtils.isNotEmpty(existingShareholderResultMap)) {
                    existingShareholdersResult.add(existingShareholderResultMap);
                }
            }
        }
        if (!existingShareholdersResult.isEmpty()) {
            qeemah1result.put(EXISTING_SHAREHOLDERS, existingShareholdersResult);
        }

        if (TOTAL_EXPECTED_PERCENTAGE.compareTo(totalPercentage) != 0) {
            result.put(SHAREHOLDERS_TOTAL_PERCENTAGE, messageSource.getMessage(LICENSE_VALIDATION_SHAREHOLDER + TOTAL_PERCENTAGE, null, locale));
        }

        if(MapUtils.isNotEmpty(qeemah1result)) {
            result.put(QEEMAH1_DATA, qeemah1result);
        }
    }

    private void validateBasicOrganizationInformation(Map<String, Object> objectMap, Locale locale, Map<String, Object> result) {
		Map<String, String> basicInformationResultMap = basicOrganizationInformationValidator.validate((Map)objectMap.get(BASIC_INFORMATION));

        if (MapUtils.isNotEmpty(basicInformationResultMap)) {
            result.put(BASIC_INFORMATION, basicInformationResultMap);
        }

        // Validate extended contact information
        Map<String, Object> qeemah2result = new HashMap<>();
        Map<String, Object> qeemah2DataMap = (Map) objectMap.get(QEEMAH2_DATA);
        Map<String, Object> contactPersonMap = (Map) qeemah2DataMap.get(CONTACT_PERSON);
        Map<String, String> contactPersonResultMap = validateQeemah2ContactPerson(contactPersonMap, locale);

        if (MapUtils.isNotEmpty(contactPersonResultMap)) {
            qeemah2result.put(CONTACT_PERSON, contactPersonResultMap);
        }

        BigDecimal totalPercentage = new BigDecimal(0);
        List shareholders = (List) qeemah2DataMap.get(SHAREHOLDERS);
        List shareholdersResult = new ArrayList();
        if (shareholders != null && !shareholders.isEmpty()) {
            for (Object shareholder : shareholders) {
                totalPercentage = totalPercentage.add(new BigDecimal((String) ((Map) shareholder).get(SHARES_PERCENTAGE)));
                Map<String, String> shareholderResultMap = validateQeemah2Shareholder((Map) shareholder, locale);
                if(MapUtils.isNotEmpty(shareholderResultMap)) {
                    shareholdersResult.add(shareholderResultMap);
                }
            }
        }

        if (TOTAL_EXPECTED_PERCENTAGE.compareTo(totalPercentage) != 0) {
            result.put(SHAREHOLDERS_TOTAL_PERCENTAGE, messageSource.getMessage(LICENSE_VALIDATION_SHAREHOLDER + TOTAL_PERCENTAGE, null, locale));
        }

        if(!shareholdersResult.isEmpty()) {
            qeemah2result.put(SHAREHOLDERS, shareholdersResult);
        }

        if(!qeemah2result.isEmpty()) {
            result.put(QEEMAH2_DATA, qeemah2result);
        }
    }

    private Map<String, String> validateQeemah1Shareholder(Map<String, Object> shareholder, Locale locale) {
        Map<String, String> result = new HashMap<>();

        if (SHAREHOLDER_PERSON.equals(shareholder.get(TYPE))) {
            validateShareholderPerson(shareholder, locale, result);
        } else if (SHAREHOLDER_ORGANIZATION.equals(shareholder.get(TYPE))) {
            validateOrganizationShareholder(shareholder, locale, result);
        }

        return result;
    }

    private void validateShareholderPerson(Map<String, Object> shareholder, Locale locale, Map<String, String> result) {
        validateEnglishName(FULL_NAME_ENGLISH, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON, locale, result);
        validateArabicName(FIRST_NAME_ARABIC, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + FIRST_NAME_ARABIC, 40, locale, result);
        validateArabicOrEnglishName(LAST_NAME_ARABIC, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + LAST_NAME_ARABIC, locale, result);
        validatePercentage(SHARES_PERCENTAGE,shareholder,LICENSE_VALIDATION_SHAREHOLDER_PERSON + SHARES_PERCENTAGE, locale, result);

        validateString(ACADEMIC_TITLE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + ACADEMIC_TITLE, locale, result);
        validateString(CURRENT_NATIONALITY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + CURRENT_NATIONALITY, locale, result);
        validateString(PREVIOUS_NATIONALITY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + PREVIOUS_NATIONALITY, locale, result);
        validateString(PREMIUM_RESIDENT, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + PREMIUM_RESIDENT, locale, result);
        validateString(COUNTRY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + COUNTRY, locale, result);
        validateString(PASSPORT_FILE, shareholder, LICENSE_VALIDATION_ATTACHMENT, locale, result);
        validateStringWithMaxLength(CITY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + CITY, 40, locale, result);

        validateString(POSTAL_CODE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON, locale, result, 0, 10);
        validateInteger(PO_BOX, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON, locale, result);

        validateDateInThePast(DATE_OF_BIRTH, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON, locale, result, null);
        validateDateInThePast(PASSPORT_ISSUE_DATE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON, locale, result, null);
        validateDateInTheFuture(LICENSE_VALIDATION_SHAREHOLDER_PERSON, shareholder, locale, result);

        validatePassportNumber(LICENSE_VALIDATION_SHAREHOLDER_PERSON, shareholder, locale, result);
        validateEmail(LICENSE_VALIDATION_SHAREHOLDER_PERSON, shareholder, locale, result);

        validateTelephone(TELEPHONE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON, locale, result);
        validateTelephone(MOBILE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON, locale, result);
        if(((String) shareholder.get("hasDelegateInfo")).equalsIgnoreCase("true") && ((String) shareholder.get("selfDelegate")).equalsIgnoreCase("false"))
        {
        	Map<String, Object> delegate= (Map)shareholder.get(DELEGATE);
        	validateDelegate(delegate, locale, result);
        }	
    }

    private void validateOrganizationShareholder(Map<String, Object> shareholder, Locale locale, Map<String, String> result) {
        validateOrganizationName(ORGANIZATION_NAME_ENGLISH, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION, locale, result);
        validateArabicNameAllowNull(ORGANIZATION_NAME_ARABIC, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + ORGANIZATION_NAME_ARABIC, 100, locale, result);

        validateString(ORGANIZATION_LEGAL_STATUS, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + ORGANIZATION_LEGAL_STATUS, locale, result);
        validateString(MULTINATIONAL_COMPANY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + MULTINATIONAL_COMPANY, locale, result);
        validateString(PARENT_COMPANY_COUNTRY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + PARENT_COMPANY_COUNTRY, locale, result);
        validateString(COMPANY_COUNTRY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_COUNTRY, locale, result);
        validateString(COMPANY_COUNTRY_OF_REGISTRATION, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_COUNTRY_OF_REGISTRATION, locale, result);
        validateString(COMPANY_SECTION, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_SECTION, locale, result);
        validateString(COMPANY_DIVISION, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_DIVISION, locale, result);
        validateString(COMPANY_REGISTRATION_FILE, shareholder, LICENSE_VALIDATION_ATTACHMENT, locale, result);
        validateString(COMPANY_FINANCIAL_STATEMENT_FILE_NAME, shareholder, LICENSE_VALIDATION_ATTACHMENT, locale, result);

        validateStringWithMaxLength(PARENT_COMPANY_NAME, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + PARENT_COMPANY_NAME, 100, locale, result);
        validateStringWithMaxLength(COMPANY_CITY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_CITY, 40, locale, result);

        validateInteger(COMPANY_CAPITAL, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION, locale, result);
        validateString(COMPANY_POSTAL_CODE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION, locale, result, 0, 10);
        validateInteger(COMPANY_POBOX, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION, locale, result);

        validateCompanyEmail(shareholder, locale, result);
        validateCompanyWebsite(shareholder, locale, result);

        validateTelephone(COMPANY_TELEPHONE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION, locale, result);
        validateTelephone(COMPANY_MOBILE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION, locale, result);

        String companySharesPercentage = (String) shareholder.get(COMPANY_SHARES_PERCENTAGE);
        if (isNullOrEmpty(companySharesPercentage) || !isPercentage(companySharesPercentage)) {
            result.put(COMPANY_SHARES_PERCENTAGE, messageSource.getMessage(LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_SHARES_PERCENTAGE, null, locale));
        }
        
        if(((String) shareholder.get("hasDelegateInfo")).equalsIgnoreCase("true"))
        {
	        Map<String, Object> delegate= (Map)shareholder.get(DELEGATE);
	        validateDelegate(delegate, locale, result);
        }
    }

    private Map<String, String> validateQeemah2Shareholder(Map<String, Object> shareholder, Locale locale) {
        Map<String, String> result = new HashMap<>();

        validateString(TYPE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + TYPE, locale, result);
        validateString(NATIONALITY, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + NATIONALITY, locale, result);
        validateStringWithMaxLength(NAME, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + NAME, 80, locale, result);
        validatePercentage(SHARES_PERCENTAGE, shareholder, LICENSE_VALIDATION_SHAREHOLDER_PERSON + SHARES_PERCENTAGE, locale, result);

        return result;
    }

    private Map<String, String> validateQeemah1ExistingShareholder(Map<String, Object> existingShareholder, Locale locale) {
        Map<String, String> result = new HashMap<>();

        String entityNumber = (String) existingShareholder.get(ENTITY_NUMBER);
        if (isNullOrEmpty(entityNumber) || !isNumeric(entityNumber) || !isMaxLength(entityNumber, 10)) {
            result.put(ENTITY_NUMBER, messageSource.getMessage(LICENSE_VALIDATION_EXISTING_SHAREHOLDER + ENTITY_NUMBER, null, locale));
        }

        validateString(PARENT_COMPANY_COUNTRY, existingShareholder, LICENSE_VALIDATION_EXISTING_SHAREHOLDER + PARENT_COMPANY_COUNTRY, locale, result);

        String sharesPercentage = (String) existingShareholder.get(SHARES_PERCENTAGE);
        if (isNullOrEmpty(sharesPercentage) || !isPercentage(sharesPercentage)) {
            result.put(SHARES_PERCENTAGE, messageSource.getMessage(LICENSE_VALIDATION_EXISTING_SHAREHOLDER + PERCENTAGE, null, locale));
        }

        String commercialRegistrationFile = (String) existingShareholder.get(COMMERCIAL_REGISTRATION_FILE);
        if (commercialRegistrationFile == null || commercialRegistrationFile.isEmpty()) {
            result.put(COMMERCIAL_REGISTRATION_FILE, messageSource.getMessage(LICENSE_VALIDATION_ATTACHMENT, null, locale));
        }

        String lastBudgetFile = (String) existingShareholder.get(LAST_BUDGET_FILE);
        if (lastBudgetFile == null || lastBudgetFile.isEmpty()) {
            result.put(LAST_BUDGET_FILE, messageSource.getMessage(LICENSE_VALIDATION_ATTACHMENT, null, locale));
        }

        return result;
    }

    private Map<String, String> validateQeemah1ContactPerson(Map<String, Object> contactPersonExtendedMap, Locale locale) {
        Map<String, String> result = validateQeemah2ContactPerson(contactPersonExtendedMap, locale);

        validateString(TITLE, contactPersonExtendedMap, LICENSE_VALIDATION_CONTACT_PERSON + GENDER, locale, result);
        validateString(EDUCATION, contactPersonExtendedMap, LICENSE_VALIDATION_CONTACT_PERSON  + EDUCATION, locale, result);
        validateStringWithMaxLength(CITY, contactPersonExtendedMap, LICENSE_VALIDATION_CONTACT_PERSON + CITY, 40, locale, result);

        validateInteger(PO_BOX, contactPersonExtendedMap, LICENSE_VALIDATION_CONTACT_PERSON + PO_BOX, locale, result);
        validateString(POSTAL_CODE, contactPersonExtendedMap, LICENSE_VALIDATION_CONTACT_PERSON + POSTAL_CODE, locale, result, 0, 10);

        validatePassportNumber(LICENSE_VALIDATION_CONTACT_PERSON, contactPersonExtendedMap, locale, result);

        validateDateInThePast(DATE_OF_BIRTH, contactPersonExtendedMap, LICENSE_VALIDATION_CONTACT_PERSON + DATE_OF_BIRTH, locale, result, null);
        validateDateInThePast(PASSPORT_ISSUE_DATE, contactPersonExtendedMap, LICENSE_VALIDATION_CONTACT_PERSON + PASSPORT_ISSUE_DATE, locale, result, null);
        validateDateInTheFuture(LICENSE_VALIDATION_CONTACT_PERSON, contactPersonExtendedMap, locale, result);

        return result;
    }

    private void validateDateInTheFuture(String property, Map<String, Object> propertiesMap, Locale locale, Map<String, String> result) {
        String passportExpiryDate = (String) propertiesMap.get(PASSPORT_EXPIRY_DATE);
        if (isNullOrEmpty(passportExpiryDate) || !isDateInTheFuture(passportExpiryDate)) {
            result.put(PASSPORT_EXPIRY_DATE, messageSource.getMessage(property + PASSPORT_EXPIRY_DATE, null, locale));
        }
    }

    private void validateInteger(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result) {
        String prop = (String) propertiesMap.get(property);
        if (isNullOrEmpty(prop) || !isInteger(prop)) {
            result.put(property, messageSource.getMessage(path + property, null, locale));
        }
    }

    private Map<String, String> validateQeemah2ContactPerson(Map<String, Object> contactPersonMap, Locale locale) {
        Map<String, String> result = new HashMap<>();

        String firstName = (String) contactPersonMap.get(FIRST_NAME);
        if (isNullOrEmpty(firstName) || (!isEnglish(firstName) && !isArabic(firstName)) || !isMaxLength(firstName, 40)) {
            result.put(FIRST_NAME, messageSource.getMessage(LICENSE_VALIDATION_CONTACT_PERSON + FIRST_NAME, null, locale));
        }

        String lastName = (String) contactPersonMap.get(LAST_NAME);
        if (isNullOrEmpty(lastName) || (!isEnglish(lastName) && !isArabic(lastName)) || !isMaxLength(lastName, 40)) {
            result.put(LAST_NAME, messageSource.getMessage(LICENSE_VALIDATION_CONTACT_PERSON + LAST_NAME, null, locale));
        }

        validateString(NATIONALITY, contactPersonMap, LICENSE_VALIDATION_CONTACT_PERSON + NATIONALITY, locale, result);
        validateString(COUNTRY, contactPersonMap, LICENSE_VALIDATION_CONTACT_PERSON + COUNTRY, locale, result);
        validateString(ROLE, contactPersonMap, LICENSE_VALIDATION_CONTACT_PERSON + ROLE, locale, result);

        validateEmail(LICENSE_VALIDATION_CONTACT_PERSON, contactPersonMap, locale, result);

        validateInteger(TELEPHONE, contactPersonMap, LICENSE_VALIDATION_CONTACT_PERSON, locale, result);
        validateInteger(MOBILE_NUMBER, contactPersonMap, LICENSE_VALIDATION_CONTACT_PERSON, locale, result);

        return result;
    }

    private void validateEmail(String path, Map<String, Object> propertiesMap, Locale locale, Map<String, String> result) {
        String email = (String) propertiesMap.get(EMAIL);
        if (isNullOrEmpty(email) || !isEmail(email)) {
            result.put(EMAIL, messageSource.getMessage(path + EMAIL, null, locale));
        }
    }


    private void validateCompanyWebsite(Map<String, Object> shareholder, Locale locale, Map<String, String> result) {
        String companyWebsite = (String) shareholder.get(COMPANY_WEBSITE);
        if (isNullOrEmpty(companyWebsite) || !isWebsite(companyWebsite)) {
            result.put(COMPANY_WEBSITE, messageSource.getMessage(LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_WEBSITE, null, locale));
        }
    }

    private void validateCompanyEmail(Map<String, Object> shareholder, Locale locale, Map<String, String> result) {
        String companyEmail = (String) shareholder.get(COMPANY_EMAIL);
        if (isNullOrEmpty(companyEmail) || !isEnglish(companyEmail) || !isEmail(companyEmail)) {
            result.put(COMPANY_EMAIL, messageSource.getMessage(LICENSE_VALIDATION_SHAREHOLDER_ORGANIZATION + COMPANY_EMAIL, null, locale));
        }
    }
    
    private void validateDelegate(Map<String, Object> delegate, Locale locale, Map<String, String> result) {
    	validateInteger(DELEGATE_IDTYPE, delegate, LICENSE_VALIDATION_DELEGATE, locale, result );
    	validateString(DELEGATE_IDNUMBER, delegate, LICENSE_VALIDATION_DELEGATE+DELEGATE_IDNUMBER, locale, result );
        validateArabicName(DELEGATE_FIRSTNAME_ARABIC, delegate,LICENSE_VALIDATION_DELEGATE+DELEGATE_FIRSTNAME_ARABIC, 40, locale, result);
    	validateArabicName(DELEGATE_LASTNAME_ARABIC, delegate,LICENSE_VALIDATION_DELEGATE+DELEGATE_LASTNAME_ARABIC, 40, locale, result);
    	validateEnglishName(DELEGATE_FULLNAME_ENGLISH, delegate,LICENSE_VALIDATION_DELEGATE, locale, result);
    	validateGender(DELEGATE_GENDER, delegate, LICENSE_VALIDATION_DELEGATE, locale, result);
    	validateString(DELEGATE_COUNTRY, delegate, LICENSE_VALIDATION_DELEGATE + DELEGATE_COUNTRY, locale, result);
    	validateString(DELEGATE_NATIONALITY, delegate, LICENSE_VALIDATION_DELEGATE + DELEGATE_NATIONALITY, locale, result);
        validateTelephone(DELEGATE_MOBILE, delegate, LICENSE_VALIDATION_DELEGATE, locale, result);
        validateTelephone(DELEGATE_TELEPHONE, delegate, LICENSE_VALIDATION_DELEGATE, locale, result);
        validateEmail(DELEGATE_EMAIL, delegate, LICENSE_VALIDATION_DELEGATE, locale, result);
    	validateString(DELEGATE_AUTHLETTERFILE, delegate, LICENSE_VALIDATION_DELEGATE + DELEGATE_AUTHLETTERFILE, locale, result);
    	String nicVerifed = (String) delegate.get(DELEGATE_NICVERIFED);
    	String idType = (String) delegate.get(DELEGATE_IDTYPE);
    	
    	Chronology chronology = null;
		if (idType.equals("4")) {
	        validateString(DELEGATE_POSTALCODE, delegate, LICENSE_VALIDATION_DELEGATE+DELEGATE_POSTALCODE, locale, result, 0, 10);
	        validateInteger(DELEGATE_POBOX, delegate, LICENSE_VALIDATION_DELEGATE, locale, result);
		} else if (idType.equals("1")) {
			chronology = HijrahChronology.INSTANCE;
		} 
		
        validateDateInThePast(DELEGATE_DATEOFBIRTH, delegate, LICENSE_VALIDATION_DELEGATE, locale, result, chronology);
    	validateDateInThePast(DELEGATE_ISSUEDATE, delegate, LICENSE_VALIDATION_DELEGATE, locale, result, chronology);
    	validateDateInTheFuture(DELEGATE_EXPIRYDATE, delegate, LICENSE_VALIDATION_DELEGATE, locale, result, chronology);
    	
    	if( nicVerifed.equalsIgnoreCase("false")  || idType.equalsIgnoreCase("4"))
    		validateString(DELEGATE_IDCOPYFILE, delegate, LICENSE_VALIDATION_DELEGATE + DELEGATE_IDCOPYFILE, locale, result);

    }
    
    private void validateArabicOrEnglishName(String property, Map<String, Object> shareholder,String path, Locale locale, Map<String, String> result) {
        String lastNameArabic = (String) shareholder.get(property);
        if (isNullOrEmpty(lastNameArabic) || !isArabicOrEnglish(lastNameArabic) || !isMaxLength(lastNameArabic, 40)) {
            result.put(property, messageSource.getMessage(path, null, locale));
        }
    }

    private void validateEnglishName(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result) {
        String fullNameEnglish = (String) propertiesMap.get(property);
        if (isNullOrEmpty(fullNameEnglish) || !isEnglish(fullNameEnglish) || !isMaxLength(fullNameEnglish, 100)) {
            result.put(property, messageSource.getMessage(path + property, null, locale));
        }
    }

    private void validatePercentage(String property, Map<String, Object> propertiesMap,String path, Locale locale, Map<String, String> result) {
        String sharesPercentage = (String) propertiesMap.get(property);
        if (isNullOrEmpty(sharesPercentage) || !isPercentage(sharesPercentage)) {
            result.put(property, messageSource.getMessage(path, null, locale));
        }
    }

    private void validatePassportNumber(String path, Map<String, Object> propertiesMap, Locale locale, Map<String, String> result) {
        String passportNumber = (String) propertiesMap.get(PASSPORT_NUMBER);
        if (isNullOrEmpty(passportNumber) || !isAlphanumeric(passportNumber)) {
            result.put(PASSPORT_NUMBER, messageSource.getMessage(path + PASSPORT_NUMBER, null, locale));
        }
    }

    private void validateDateInThePast(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result, Chronology chronology) {
        String dateOfBirth = (String) propertiesMap.get(property);
        if (isNullOrEmpty(dateOfBirth) || !isDateInThePast(dateOfBirth, chronology)) {
            result.put(property, messageSource.getMessage(path + property, null, locale));
        }
    }
    private void validateDateInTheFuture(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result, Chronology chronology) {
        String dateOfBirth = (String) propertiesMap.get(property);
        if (isNullOrEmpty(dateOfBirth) || !isDateInTheFuture(dateOfBirth, chronology)) {
            result.put(property, messageSource.getMessage(path + property, null, locale));
        }
    }
    
    private void validateArabicNameAllowNull(String property, Map<String, Object> propertiesMap, String path, int maxLength, Locale locale, Map<String, String> result) {
        String arabicProperty = (String) propertiesMap.get(property);
        if (!isNullOrEmpty(arabicProperty) && (!isArabic(arabicProperty) || !isMaxLength(arabicProperty, maxLength))) {
            result.put(property, messageSource.getMessage(path, null, locale));
        }
    }

    private void validateArabicName(String property, Map<String, Object> propertiesMap, String path, int maxLength, Locale locale, Map<String, String> result) {
        String arabicProperty = (String) propertiesMap.get(property);
        if (isNullOrEmpty(arabicProperty) || !isArabic(arabicProperty) || !isMaxLength(arabicProperty, maxLength)) {
            result.put(property, messageSource.getMessage(path, null, locale));
        }
    }
    
    private void validateOrganizationName(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result) {
        validateEnglishName(property, propertiesMap, path, locale, result);
    }

    private void validateString(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result) {
        validateString(property, propertiesMap, path, locale, result, 0, 0);
    }
    
    private void validateEmail(String property, Map<String, Object> propertiesMap,String path, Locale locale, Map<String, String> result) {
        String email = (String) propertiesMap.get(property);
        if (isNullOrEmpty(email) || !isEmail(email)) {
            result.put(property, messageSource.getMessage(path + property, null, locale));
        }
    }
    private void validateGender(String property, Map<String, Object> propertiesMap,String path, Locale locale, Map<String, String> result) {
        String gender = (String) propertiesMap.get(property);
        if (isNullOrEmpty(gender) || !isGender(gender)) {
            result.put(property, messageSource.getMessage(path + property, null, locale));
        }
    }

    /**
     *
     * @param property
     * @param propertiesMap
     * @param path
     * @param locale
     * @param result
     * @param minLength
     * @param maxLength -
     */
    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S3776"})
    private void validateString(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result, int minLength, int maxLength) {
        Object mapProperty = propertiesMap.get(property);
        boolean hasError = false;
        String value = "";
        if (mapProperty instanceof String && isNullOrEmpty((String) mapProperty)) {
            result.put(property, messageSource.getMessage(path, null, locale));
            hasError = true;
        } else if (mapProperty instanceof LinkedTreeMap) {
            if (propertiesMap.get(property) != null && ((LinkedTreeMap) propertiesMap.get(property)).get(VAL) != null) {
                value = ((LinkedTreeMap) propertiesMap.get(property)).get(VAL).toString();
                if (isNullOrEmpty(value)) {
                    result.put(property, messageSource.getMessage(path, null, locale));
                    hasError = true;
                }
            } else {
                result.put(property, messageSource.getMessage(path, null, locale));
                hasError = true;
            }
        }
        if (!hasError) {
            if (isNullOrEmpty(value) && mapProperty instanceof String) {
                value = (String) mapProperty;
            } else if ((mapProperty instanceof LinkedTreeMap) && (propertiesMap.get(property) != null && ((LinkedTreeMap) propertiesMap.get(property)).get(VAL) != null)) {
                value = ((LinkedTreeMap) propertiesMap.get(property)).get(VAL).toString();

            }
            if (minLength > 0 && value.length() < minLength) {
                result.put(property, messageSource.getMessage(path, null, locale));
            }
            if (maxLength > 0 && value.length() > maxLength) {
                result.put(property, messageSource.getMessage(path, null, locale));
            }
        }
    }

    private void validateTelephone(String property, Map<String, Object> propertiesMap, String path, Locale locale, Map<String, String> result) {
        String companyTelephone = (String) propertiesMap.get(property);
        if (!isNullOrEmpty(companyTelephone) && !isInteger(companyTelephone)) { // Not mandatory
            result.put(property, messageSource.getMessage(path + property, null, locale));
        }
    }

    private void validateStringWithMaxLength(String property, Map<String, Object> propertiesMap, String path, int maxLength, Locale locale, Map<String, String> result) {
        String parentCompanyName = (String) propertiesMap.get(property);
        if (isNullOrEmpty(parentCompanyName) || !isMaxLength(parentCompanyName, maxLength)) {
            result.put(property, messageSource.getMessage(path, null, locale));
        }
    }

    private Locale resolveLocale() {
       return i18NService.getCurrentLocale();
    }

    private void validateBusinessActivities(Map<String, Object> objectMap, Locale locale, Map<String, Object> result) {
        Map<String, String> businessActivitiesMap = (Map<String, String>) objectMap.get("businessActivities");
        if (MapUtils.isEmpty(businessActivitiesMap)) {
            String errorMessage =  messageSource.getMessage("license.validation.business.activities.empty", null, locale);
            result.put("businessActivities", errorMessage);
        }
    }

    private boolean isNullOrEmpty(String value) {
        return StringUtils.isBlank(value);
    }

    private boolean isAlphanumeric(String value) {
        return StringUtils.isAlphanumeric(value);
    }

    private boolean isEnglish(String value) {
        return value.matches(ENGLISH_REGEX);
    }

    private boolean isArabic(String value) {
        return value.matches(ARABIC_REGEX);
    }

    private boolean isArabicOrEnglish(String value) {
        return isEnglish(value) || isArabic(value);
    }

    private boolean isDateInThePast(String value) {
        return LocalDate.now().isAfter(sagiaFormatProvider.getDateFromUIDateStringOrNull(value));
    }

    private boolean isDateInTheFuture(String value) {
        return LocalDate.now().isBefore(sagiaFormatProvider.getDateFromUIDateStringOrNull(value));
    }
    
    private boolean isDateInThePast(String value, Chronology chronology) {
    	if(chronology != null)
    	{
    		return LocalDate.now().isAfter(sagiaFormatProvider.getLocalDateFromuiUAQDateString(value, chronology));
    	}
        return isDateInThePast(value);
    }

    private boolean isDateInTheFuture(String value, Chronology chronology) {
    	if(chronology != null)
    	{
    		return LocalDate.now().isBefore(sagiaFormatProvider.getLocalDateFromuiUAQDateString(value, chronology));
    	}
        return isDateInTheFuture(value);
    }

    private boolean isInteger(String value) {
        return value.matches(INTEGER_REGEX);
    }

    private boolean isNumeric(String value) {
        return value.matches(NUMERIC_REGEX);
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

    private boolean isWebsite(String value) {
        return value.matches(WEBSITE_REGEX);
    }

    private boolean isEmail(String value) {
        return value.matches(EMAIL_REGEX);
    }

    private boolean isMaxLength(String value, int maxLength) {
        return value.length() <= maxLength;
    }
    private boolean isGender(String value) {
        return value.matches(GENDER_REGEX);
    }
}
