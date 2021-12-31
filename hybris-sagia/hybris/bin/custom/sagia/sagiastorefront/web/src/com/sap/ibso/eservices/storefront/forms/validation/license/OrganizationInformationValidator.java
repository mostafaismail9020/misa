package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sap.ibso.eservices.facades.data.OrganizationInformation;
import com.sap.ibso.eservices.storefront.forms.translator.StorefrontLozalizedMessageTranslator;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil.isWebAddress;

@Component("organizationInformationValidator")
public class OrganizationInformationValidator implements Validator {

    @Resource(name = "storefrontLozalizedMessageTranslator")
    private StorefrontLozalizedMessageTranslator storefrontLozalizedMessageTranslator;
    
    @Override
    public boolean supports(Class<?> aClass) {
        return OrganizationInformation.class.equals(aClass);
    }

    /* Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776", "squid:MethodCyclomaticComplexity"})
    @Override
    public void validate(Object object, Errors errors) {
        final OrganizationInformation organizationInformation = (OrganizationInformation)object;
        if(StringUtils.isEmpty(organizationInformation.getEntityName()) ||
           !SagiaValidationUtil.isOrgNameEnglish(organizationInformation.getEntityName())){
            errors.rejectValue("entityName", "validation.basicinformation.entityName");
        }
        if(StringUtils.isNotEmpty(organizationInformation.getEntityNameArabic()) && !SagiaValidationUtil.isArabic(organizationInformation.getEntityNameArabic())) {
            errors.rejectValue("entityNameArabic", "validation.basicinformation.entityNameArabic");
        }
        if(StringUtils.isEmpty(organizationInformation.getLegalStatus())) {
            errors.rejectValue("legalStatus", "validation.basicinformation.legalStatus");
        }
        if((!StringUtils.isNumeric(organizationInformation.getCapital())
                || !SagiaValidationUtil.isPositiveInteger(organizationInformation.getCapital()))) {
            errors.rejectValue("capital", "validation.basicinformation.capital");
        }
        if(StringUtils.isEmpty(organizationInformation.getEmail()) || !SagiaValidationUtil.isEmail(organizationInformation.getEmail())) {
            errors.rejectValue("email", "validation.basicinformation.email");
        }
        if(StringUtils.isEmpty(organizationInformation.getAddress())){
            errors.rejectValue("address", "validation.basicinformation.address");
        }
        if(StringUtils.isEmpty(organizationInformation.getPostalCode()) || organizationInformation.getPostalCode().length() > 10){
            errors.rejectValue("postalCode", "validation.basicinformation.postalCode");
        }
        String website = organizationInformation.getWebsite();
        if(StringUtils.isEmpty(website) || !isWebAddress(website)){
            errors.rejectValue("website", "validation.basicinformation.website");
        }
        if(StringUtils.isEmpty(organizationInformation.getTelephone())){
            errors.rejectValue("telephone", "validation.basicinformation.telephone");
        }
        if(StringUtils.isEmpty(organizationInformation.getMobilePhone())){
            errors.rejectValue("mobilePhone", "validation.basicinformation.mobilePhone");
        }
        if(StringUtils.isEmpty(organizationInformation.getRegion())){
            errors.rejectValue("region", "validation.basicinformation.region");
        }
        if(StringUtils.isEmpty(organizationInformation.getCity())){
            errors.rejectValue("city", "validation.basicinformation.city");
        }
        if(StringUtils.isEmpty(organizationInformation.getPoBox())){
            errors.rejectValue("poBox", "validation.basicinformation.poBox");
        }
        if(StringUtils.isEmpty(organizationInformation.getInvestment())) {
            errors.rejectValue("investment", "validation.basicinformation.investment");
        }
    }

    /*
     * Suppress sonar warning (squid:S3776 | Cognitive Complexity of methods should not be too high
     * Suppress sonar warning (squid:MethodCyclomaticComplexity | Methods should not be too complex
     * The structure of this method is not difficult to understand in the given context.
     */
    @SuppressWarnings({"squid:S3776", "squid:MethodCyclomaticComplexity"})
	public Map<String, String> validate(Map<String, Object> objectMap) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(objectMap);
		OrganizationInformation organizationInformation = gson.fromJson(jsonElement,
				OrganizationInformation.class);

		Map<String, String> result = new HashMap<>();
		if (StringUtils.isEmpty(organizationInformation.getEntityName())) {
			result.put("entityName", getMessage("validation.basicinformation.entityName"));
		}
		if (StringUtils.isNotEmpty(organizationInformation.getEntityNameArabic())
				&& (!SagiaValidationUtil.isArabic(organizationInformation.getEntityNameArabic()))) {
			result.put("entityNameArabic", getMessage("validation.basicinformation.entityNameArabic"));
		}
		if (StringUtils.isEmpty(organizationInformation.getLegalStatus())) {
			result.put("legalStatus", getMessage("validation.basicinformation.legalStatus"));
		}
		if (!StringUtils.isNumeric(organizationInformation.getCapital())
				|| !SagiaValidationUtil.isPositiveInteger(organizationInformation.getCapital())) {
			result.put("capital", getMessage("validation.basicinformation.capital"));
		}
		if (StringUtils.isEmpty(organizationInformation.getEmail()) || !SagiaValidationUtil.isEmail(organizationInformation.getEmail())) {
			result.put("email", getMessage("validation.basicinformation.email"));
		}
		if (StringUtils.isEmpty(organizationInformation.getAddress())) {
			result.put("address", getMessage("validation.basicinformation.address"));
		}
		if (StringUtils.isEmpty(organizationInformation.getPostalCode()) || organizationInformation.getPostalCode().length() > 10) {
			result.put("postalCode", "validation.basicinformation.postalCode");
		}
		String website = organizationInformation.getWebsite();
		if (StringUtils.isEmpty(website) || !isWebAddress(website)) {
			result.put("website", getMessage("validation.basicinformation.website"));
		}
		if (StringUtils.isEmpty(organizationInformation.getTelephone())) {
			result.put("telephone", getMessage("validation.basicinformation.telephone"));
		}
		if (StringUtils.isEmpty(organizationInformation.getMobilePhone())) {
			result.put("mobilePhone", getMessage("validation.basicinformation.mobilePhone"));
		}
		if (StringUtils.isEmpty(organizationInformation.getRegion())) {
			result.put("region", getMessage("validation.basicinformation.region"));
		}
		if (StringUtils.isEmpty(organizationInformation.getCity())) {
			result.put("city", getMessage("validation.basicinformation.city"));
		}
		if (StringUtils.isEmpty(organizationInformation.getPoBox())) {
			result.put("poBox", getMessage("validation.basicinformation.poBox"));
		}
		return result;
	}
    
    private String getMessage(String key) {
    		return storefrontLozalizedMessageTranslator.getLocalizedMessageValue(key);
    }
}
