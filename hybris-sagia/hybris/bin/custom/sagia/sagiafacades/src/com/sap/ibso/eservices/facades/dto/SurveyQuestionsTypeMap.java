package com.sap.ibso.eservices.facades.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *Each question for a survey can requests answers in different types
 *The CRM responds only with the code value foreach type
 *Added this map in order for the developer to have an overview on what each code actually means
 *The CRM does not provide this mapping from any request, it is hardcoded 
 */
public class SurveyQuestionsTypeMap {

	private static final Map<String, String> TYPE_MAP = createMap();
	private SurveyQuestionsTypeMap() {} 

	private static Map<String, String> createMap() {
		Map<String, String> result = new HashMap<>();
		result.put("FIELD", "1");
		result.put("DATE", "4");
		result.put("TIME", "5");
		result.put("NUMBER", "6");
		result.put("TEXT", "2");
		result.put("RADIOBUTTON", "10");
		result.put("Checkbox", "3");
		result.put("CUSTOM_CHECKBOX", "101");
		result.put("ListBox", "12");
		result.put("MultipleChoice", "13");

		return Collections.unmodifiableMap(result);
	}

	public static String getTypeName(String value) {
		return TYPE_MAP.get(value);
	}

}
