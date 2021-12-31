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
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.basesites

import de.hybris.bootstrap.annotations.ManualTest
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.AbstractSpockFlowTest
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.XML
import static org.apache.http.HttpStatus.SC_BAD_REQUEST
import static org.apache.http.HttpStatus.SC_OK

@ManualTest
@Unroll
class BaseSitesTest extends AbstractSpockFlowTest {
	def "Client retrieves a base sites: #format"() {
		when:
		HttpResponseDecorator response = restClient.get(path: getBasePath() + '/basesites', contentType: format)

		then:
		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_OK
			data.baseSites.any { it.uid == 'wsTest' }
		}

		where:
		format << [JSON, XML]
	}

	def "Client retrieves countries for basesites store"() {
		when:
		HttpResponseDecorator response = restClient.get(path: getBasePath() + '/basesites/countries')

		then:
		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_BAD_REQUEST
			data.errors.get(0).get("type") == "InvalidResourceError"
			data.errors.get(0).get("message") == "Base site basesites doesn't exist"
		}
	}
}
