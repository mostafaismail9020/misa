package com.sap.ibso.eservices.storefront.util;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class SagiaContextFormErrorsConverter {

	private static final Logger LOG = Logger.getLogger(SagiaContextFormErrorsConverter.class);

	
	private static final String BASIC_INFORMATION = "basicInformation";
	private static final String BASIC_INFORMATION_EXTENDED = "basicInformationExtended";
	private static final String BUSINESS_ACTIVITIES = "businessActivities";
	private static final String NEW_SHAREHOLDERS = "newShareholders";
	private static final String EXISTING_SHAREHOLDERS = "existingShareholders";
	private static final String SHAREHOLDERS_TOTAL_PERCENTAGE = "shareholdersTotalPercentage";
	private static final String QEEMAH_2_DATA = "qeemah2Data";
	private static final String QEEMAH_1_DATA = "qeemah1Data";
	private static final String CONTACT_PERSON = "contactPerson";
	private static final String ENTITY_INFORMATION_TAB = "#entityInformationTab";

	private SagiaContextFormErrorsConverter() {}

	protected static Map<String, String> tabNames() {
        final Map<String, String> numMap = new HashMap<>();
        numMap.put(BASIC_INFORMATION, ENTITY_INFORMATION_TAB);
        numMap.put(BASIC_INFORMATION_EXTENDED, ENTITY_INFORMATION_TAB);
		numMap.put(BUSINESS_ACTIVITIES, ENTITY_INFORMATION_TAB);
		numMap.put(NEW_SHAREHOLDERS, "#shareholdersTab");
		numMap.put(EXISTING_SHAREHOLDERS, "#shareholdersTab");
		numMap.put(SHAREHOLDERS_TOTAL_PERCENTAGE, "#shareholdersTab");
        numMap.put(CONTACT_PERSON, "#contactPersonTab");
        return Collections.unmodifiableMap(numMap);
    }
	
	protected static Map<String, String> sectionNames() {
        final Map<String, String> numMap = new HashMap<>();
        numMap.put(BASIC_INFORMATION, "#basicInformationSection");
		numMap.put(BASIC_INFORMATION_EXTENDED, "#basicInformationExtendedSection");
		numMap.put(BUSINESS_ACTIVITIES, "#basicInformationSection");
		numMap.put(NEW_SHAREHOLDERS, "#shareholdersTab");
		numMap.put(EXISTING_SHAREHOLDERS, "#shareholdersTab");
		numMap.put(SHAREHOLDERS_TOTAL_PERCENTAGE, "#shareholdersTab");
        numMap.put(CONTACT_PERSON, "#contactPersonTabData");
        return Collections.unmodifiableMap(numMap);
    }
	
	protected static Map<String, Integer> errorSectionPriority() {
        final Map<String, Integer> errorOrder = new HashMap<>();
        errorOrder.put(BASIC_INFORMATION, 10);
        errorOrder.put(BASIC_INFORMATION_EXTENDED, 20);
		errorOrder.put(BUSINESS_ACTIVITIES, 30);
		errorOrder.put(SHAREHOLDERS_TOTAL_PERCENTAGE, 40);
		errorOrder.put(NEW_SHAREHOLDERS, 40);
		errorOrder.put(EXISTING_SHAREHOLDERS, 40);
		errorOrder.put(QEEMAH_1_DATA, 50);
        errorOrder.put(QEEMAH_2_DATA, 60);
        errorOrder.put(CONTACT_PERSON, 70);
        return errorOrder;
	}
	public static SagiaContextFormErrors fromValidation(Map<String, Object> errorsMap) {
		 Optional<String> errorMapKey = getFirstErrorKey(errorsMap);
		 if(errorMapKey.isPresent()) {
			 String context = errorMapKey.get();
			 if(StringUtils.equalsIgnoreCase(context, QEEMAH_2_DATA) || StringUtils.equalsIgnoreCase(context, QEEMAH_1_DATA)) {
				 return fromNestedMap(errorsMap, context);
			 }
			 return from(errorsMap, context);
		 }
		 return new SagiaContextFormErrors();
	}

	private static SagiaContextFormErrors from(Map<String, Object> errorsMap, String invalidSection) {
		Object fieldErrors = errorsMap.get(invalidSection);
		return createContextFormErrorsFrom(invalidSection, fieldErrors);
	}
	
	private static SagiaContextFormErrors fromNestedMap(Map<String, Object> errorsMap, String invalidSection) {
		//Object is Map<String,Object?
		Map<String, Object> singleMapError = getMapObjectFrom(errorsMap.get(invalidSection));
		String key = singleMapError.keySet().stream().findFirst().orElse(null);
		if(key == null) {
			return new SagiaContextFormErrors();
		}
		Object fieldErrors = singleMapError.get(key);
		return createContextFormErrorsFrom(key, fieldErrors);
	}

	private static SagiaContextFormErrors createContextFormErrorsFrom(String context, Object fieldErrors) {
		if (fieldErrors instanceof String) {
			return new SagiaContextFormErrors()
					.setSection(sectionNames().get(context))
					.setTabName(tabNames().get(context))
					.setPopupError(String.valueOf(fieldErrors));
		}
		else if (fieldErrors instanceof List) {
			List<Map<String, String>> list = (ArrayList<Map<String, String>>)fieldErrors;
			 Map<String, String> merged = new HashMap<String, String>();
			for( Map<String, String> map : list)
			{
				merged.putAll(map);
			}
			return new SagiaContextFormErrors()
					.setSection(sectionNames().get(context))
					.setTabName(tabNames().get(context))
					.setFormErrors(merged);
		}
		return new SagiaContextFormErrors()
				.setSection(sectionNames().get(context))
				.setTabName(tabNames().get(context))
				.setFormErrors(getMapStringFrom(fieldErrors));
	}
	
	public static Map<String, Object> getMapObjectFrom(Object object) {
		try {
			return (Map<String, Object>) object;
		} catch (ClassCastException ex) {
			LOG.warn(ex.getMessage(),ex);
			return Collections.emptyMap();
		}
	}
	
	public static Map<String, String> getMapStringFrom(Object object) {
		try {
			return (Map<String, String>) object;
		} catch (ClassCastException ex) {
			LOG.warn(ex.getMessage(),ex);
			return Collections.emptyMap();
		}
	}
	
	/**
	 * return the first tab which should be validated
	 * @param errorsMap
	 * @return
	 */
	private static Optional<String> getFirstErrorKey(Map<String, Object> errorsMap) {
		if (MapUtils.isEmpty(errorsMap)) {
			return Optional.empty();
		}
		List<String> activeErrorTabs = errorsMap.keySet().stream().collect(Collectors.toList());
		Map<String, Integer> activeErrorTabsPriority = errorSectionPriority();
		activeErrorTabsPriority.keySet().retainAll(activeErrorTabs);

		return activeErrorTabsPriority
				.entrySet()
				.stream()
				.min(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey);
	}
}