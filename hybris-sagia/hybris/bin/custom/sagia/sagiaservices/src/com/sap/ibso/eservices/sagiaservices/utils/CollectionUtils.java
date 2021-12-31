package com.sap.ibso.eservices.sagiaservices.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

public class CollectionUtils {

	private CollectionUtils() {
	}

	public static String getValueFrom(Map<String, Object> map, String key) {
		return String.valueOf(map.get(key));
	}

	public static <T> T getFirstOrNullFrom(Collection<T> list) {
		return list.stream().findFirst().orElse(null);
	}
	
	public static <T> T getByIndexOrNull(List<T> list, int index) {
		if (index < 0) {
			throw new IllegalArgumentException("index of querying list should be >= 0: " + index);
		}
		if (index <= list.size() - 1) {
			return list.get(index);
		}
		return null;
	}
}
