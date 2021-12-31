/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
/**
 *
 */
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.docu
/**
 * Aggregate information about running test (test name, called web service request)
 */
//@ToString(includeNames=true)
class SummaryOfRunningTest {
	def String testName;
	List<WSRequestSummary> requests = new ArrayList<WSRequestSummary>();

	void addRequest(String resource, String accept, String method)	{
		requests.add(new WSRequestSummary(resource: resource, accept: accept, httpMethod:method));
	}

	void addResponse(String response) {
		int lastIndex = requests.size() - 1;
		WSRequestSummary last = requests.get(lastIndex);
		if(last.response != null) {
			throw new IllegalStateException();
		}
		last.response = response;
	}
}
