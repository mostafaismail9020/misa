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
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.addons.v2.spock

import de.hybris.bootstrap.annotations.ManualTest
import de.hybris.platform.util.Config
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.AbstractSpockFlowTest
import groovyx.net.http.HttpResponseDecorator
import org.junit.Before
import spock.lang.Unroll

import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.ContentType.XML
import static org.apache.http.HttpStatus.SC_OK

@ManualTest
@Unroll
class ExtendedBaseSitesTest extends AbstractSpockFlowTest  {
	@Before
	public void ignoreIf() {
		org.junit.Assume.assumeTrue(Config.getBoolean("misawebservicetest.enableAccTest", false))
	}

	/*
	This test is checking for presence of cmsoccaddon which is responsible for displaying urlPatters field.
	So far we don't have possibility to check if acceleratorwebserviceaddon is installed. This addons is responsible for field: urlEncodingAttributes
	Those two fields have to be initialized for junit_cmssite table by file: /commercewebservices-module/misawebservicetest/resources/misawebservicetest/import/coredata/stores/wsTest/site.impex
	*/
	def "Client retrieves a base sites with fields equals full: #format"() {
		when:
		HttpResponseDecorator response = restClient.get(
				path: getBasePath() + '/basesites',
				query : ['fields' : FIELD_SET_LEVEL_FULL],
				contentType: format)

		then:
		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_OK
			// line below should be tested by cmsoccaddon
			//data.baseSites.any { it.urlPatterns != null }
			// line below should be tested by acceleratorwebserviceaddon
			data.baseSites.any { it.urlEncodingAttributes != null }
		}

		where:
		format << [JSON, XML]
	}

}
