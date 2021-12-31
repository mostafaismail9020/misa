package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.sap.ibso.eservices.facades.data.zqeemah2.BasicOrganizationInformation;
import com.sap.ibso.eservices.facades.data.zqeemah2.Shareholder;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;
import de.hybris.platform.servicelayer.i18n.I18NService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.Map;

@Component("shareholderEntityValidatorQm2")
public class ShareholderEntityValidatorQm2 {

    private final String foreignEntityLegalStatus = "BRFC";

    private static final Logger LOG = Logger.getLogger(ShareholderEntityValidatorQm2.class);

    @Resource(name = "baseMessageSource")
    private MessageSource messageSource;

    @Resource(name = "i18NService")
    private I18NService i18NService;

    /*
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({ "squid:MethodCyclomaticComplexity"})
    public void validateEntityShareholder(Shareholder shareholder, BasicOrganizationInformation basicOrganizationInformation,  Map<String, String> errors) {


        if(StringUtils.isBlank(shareholder.getName()) ||
                !SagiaValidationUtil.isOrgNameEnglish(shareholder.getName())||
                shareholder.getName().length() > 100) {
            errors.put("name", messageSource.getMessage("validation.shareholder.entity.companyName", null, i18NService.getCurrentLocale()));
        }

        if (foreignEntityLegalStatus.equals(basicOrganizationInformation.getLegalStatus())) {
            try {
                if (StringUtils.isEmpty(shareholder.getSharesPercentage()) ||
                        !SagiaValidationUtil.isPercentage(shareholder.getSharesPercentage()) ||
                        Double.valueOf(shareholder.getSharesPercentage()) != 100) {
                    errors.put("sharesPercentage", messageSource.getMessage("validation.shareholder.entity.sharesPercentageForeignEntity", null, i18NService.getCurrentLocale()));
                }
            } catch (Exception e) {
                LOG.warn(e.getMessage(), e);
                errors.put("sharesPercentage", messageSource.getMessage("validation.shareholder.entity.sharesPercentageForeignEntity", null, i18NService.getCurrentLocale()));
            }

        } else {

                if (StringUtils.isEmpty(shareholder.getSharesPercentage()) ||
                        !SagiaValidationUtil.isPercentage(shareholder.getSharesPercentage())) {
                    errors.put("sharesPercentage", messageSource.getMessage("validation.shareholder.entity.sharesPercentage", null, i18NService.getCurrentLocale()));
                }
        }

        if(StringUtils.isBlank(shareholder.getNationality())) {
            errors.put("nationality", messageSource.getMessage("validation.shareholder.entity.nationality", null, i18NService.getCurrentLocale()));
        }

        if(StringUtils.isBlank(shareholder.getType())) {
            errors.put("type", messageSource.getMessage("validation.shareholder.entity.type", null, i18NService.getCurrentLocale()));
        }

        if(StringUtils.isBlank(shareholder.getLegalStatus())) {
            errors.put("legalStatus", messageSource.getMessage("validation.shareholder.entity.legalStatus", null, i18NService.getCurrentLocale()));
        }
    }
}
