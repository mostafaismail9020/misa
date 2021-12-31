package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.facades.data.zqeemah.ExistingShareholderInfo;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("existingShareholderValidator")
public class ExistingShareholderValidator {

    private final String foreignEntityLegalStatus = "BRFC";
    private static final Logger LOG = Logger.getLogger(ExistingShareholderValidator.class);

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "i18NService")
    private I18NService i18NService;


    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity","squid:S3776"})
    public void validateExistingShareholder(ExistingShareholderInfo existingShareholderInfo, OrganizationInformation organizationInformation, Map<String, String> errors) {
        if (StringUtils.isBlank(existingShareholderInfo.getCommercialRegistrationFileName())) {
            errors.put("existingShareholderCommercialRegistrationFileName", messageSource.getMessage("validation.existing.shareholder.shareholderCommercialRegistrationFile", null, i18NService.getCurrentLocale()));

        }

        if ( existingShareholderInfo.getCommercialRegistrationFile() == null || !SagiaValidationUtil.isBase64(existingShareholderInfo.getCommercialRegistrationFile())){
            errors.put("existingShareholderCommercialRegistrationFileName", messageSource.getMessage("validation.existing.shareholder.valid.content.shareholderCommercialRegistrationFile", null, i18NService.getCurrentLocale()));
        }

        if (StringUtils.isBlank(existingShareholderInfo.getLastBudgetFileName())) {
            errors.put("existingShareholderLastBudgetFileName", messageSource.getMessage("validation.existing.shareholder.existingShareholderLastBudgetFile", null, i18NService.getCurrentLocale()));

        }

        if ( existingShareholderInfo.getLastBudgetFile() == null || !SagiaValidationUtil.isBase64(existingShareholderInfo.getLastBudgetFile())){
            errors.put("existingShareholderLastBudgetFileName", messageSource.getMessage("validation.existing.shareholder.valid.content.existingShareholderLastBudgetFile", null, i18NService.getCurrentLocale()));
        }



        if (foreignEntityLegalStatus.equals(organizationInformation.getLegalStatus())) {
            try {
                if (StringUtils.isEmpty(existingShareholderInfo.getSharesPercentage()) ||
                        !SagiaValidationUtil.isPercentage(existingShareholderInfo.getSharesPercentage()) ||
                        Double.valueOf(existingShareholderInfo.getSharesPercentage()) != 100) {
                    errors.put("existingShareholderSharesPercentage", messageSource.getMessage("validation.shareholder.entity.sharesPercentageForeignEntity", null, i18NService.getCurrentLocale()));
                }
            } catch (Exception e) {
                LOG.warn(e.getMessage(), e);
                errors.put("existingShareholderSharesPercentage", messageSource.getMessage("validation.shareholder.entity.sharesPercentageForeignEntity", null, i18NService.getCurrentLocale()));
            }

        } else {

                if (StringUtils.isEmpty(existingShareholderInfo.getSharesPercentage()) ||
                        !SagiaValidationUtil.isPercentage(existingShareholderInfo.getSharesPercentage())) {
                    errors.put("existingShareholderSharesPercentage", messageSource.getMessage("validation.shareholder.entity.sharesPercentage", null, i18NService.getCurrentLocale()));
                }

        }

    }

}