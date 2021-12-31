package com.sap.ibso.eservices.storefront.forms.validation.license;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sap.ibso.eservices.facades.data.zqeemah2.BasicOrganizationInformation;
import com.sap.ibso.eservices.storefront.forms.translator.StorefrontLozalizedMessageTranslator;
import com.sap.ibso.eservices.storefront.forms.validation.SagiaValidationUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component("basicOrganizationInformationValidator")
public class BasicOrganizationInformationValidator implements Validator {
	
    @Resource(name = "storefrontLozalizedMessageTranslator")
    private StorefrontLozalizedMessageTranslator storefrontLozalizedMessageTranslator;

    @Override
    public boolean supports(Class<?> aClass) {
        return BasicOrganizationInformation.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        final BasicOrganizationInformation basicOrganizationInformation = (BasicOrganizationInformation)object;
        if(StringUtils.isEmpty(basicOrganizationInformation.getEntityName()) ||
                !SagiaValidationUtil.isOrgNameEnglish(basicOrganizationInformation.getEntityName())){
            errors.rejectValue("entityName","validation.basicinformation.entityName");
        }
        if(StringUtils.isNotEmpty(basicOrganizationInformation.getEntityNameArabic()) && !SagiaValidationUtil.isArabic(basicOrganizationInformation.getEntityNameArabic())) {
            errors.rejectValue("entityNameArabic","validation.basicinformation.entityNameArabic");
        }
        if(StringUtils.isEmpty(basicOrganizationInformation.getLegalStatus())){
            errors.rejectValue("legalStatus","validation.basicinformation.legalStatus");
        }
        if(!StringUtils.isNumeric(basicOrganizationInformation.getCapital()) || !SagiaValidationUtil.isPositiveInteger(basicOrganizationInformation.getCapital())) {
            errors.rejectValue("capital", "validation.basicinformation.capital");
        }
        if(StringUtils.isEmpty(basicOrganizationInformation.getRegion())){
            errors.rejectValue("region","validation.basicinformation.region");
        }
        if(StringUtils.isEmpty(basicOrganizationInformation.getCity())){
            errors.rejectValue("city","validation.basicinformation.city");
        }

    }
    
	public Map<String, String> validate(Map<String, Object> objectMap) {
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(objectMap);
		BasicOrganizationInformation basicOrganizationInformation = gson.fromJson(jsonElement, BasicOrganizationInformation.class);

		Map<String, String> result = new HashMap<>();
		if (StringUtils.isEmpty(basicOrganizationInformation.getEntityName())) {
			result.put("entityName", getMessage("validation.basicinformation.entityName"));
		}
		if (StringUtils.isNotEmpty(basicOrganizationInformation.getEntityNameArabic())
				&& !SagiaValidationUtil.isArabic(basicOrganizationInformation.getEntityNameArabic())) {
			result.put("entityNameArabic", getMessage("validation.basicinformation.entityNameArabic"));
		}
		if (StringUtils.isEmpty(basicOrganizationInformation.getLegalStatus())) {
			result.put("legalStatus", getMessage("validation.basicinformation.legalStatus"));
		}
		if (!StringUtils.isNumeric(basicOrganizationInformation.getCapital())
				|| !SagiaValidationUtil.isPositiveInteger(basicOrganizationInformation.getCapital())) {
			result.put("capital", getMessage("validation.basicinformation.capital"));
		}
		if (StringUtils.isEmpty(basicOrganizationInformation.getRegion())) {
			result.put("region", getMessage("validation.basicinformation.region"));
		}
		if (StringUtils.isEmpty(basicOrganizationInformation.getCity())) {
			result.put("city", getMessage("validation.basicinformation.city"));
		}
		return result;
	}
    
    private String getMessage(String key) {
    		return storefrontLozalizedMessageTranslator.getLocalizedMessageValue(key);
    }
}
