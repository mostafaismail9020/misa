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
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.filters

import de.hybris.bootstrap.annotations.ManualTest
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.users.AbstractUserTest
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll

import static groovyx.net.http.ContentType.*
import static org.apache.http.HttpStatus.*

/**
 *
 * `
 */
@Unroll
@ManualTest
class UserMatchingFilterTest extends AbstractUserTest {

    def "Not matching path for trusted client: #format"() {
        given: "trusted client"
        authorizeTrustedClient(restClient)

        when: "request for non existing path is made"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/some/much/much/longer/path',
                contentType: format,
                requestContentType: URLENC
        )

        then: "he receives a 404 error"
        with(response) { status == SC_NOT_FOUND }

        where:
        format << [XML, JSON]
    }

    def "Not matching path for customer manager: #format"() {
        given: "trusted client"
        authorizeCustomerManager(restClient)

        when: "request for non existing path is made"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/some/much/much/longer/path',
                contentType: format,
                requestContentType: URLENC
        )

        then: "he receives a 404 error"
        with(response) { status == SC_NOT_FOUND }

        where:
        format << [XML, JSON]
    }

    def "Not matching path for client: #format"() {
        given: "trusted client"
        authorizeClient(restClient)

        when: "request for non existing path is made"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/some/much/much/longer/path',
                contentType: format,
                requestContentType: URLENC
        )

        then: "he receives a 404 error"
        with(response) { status == SC_NOT_FOUND }

        where:
        format << [XML, JSON]
    }

    def "Unknown customer path matching: #format"() {
        given: "trusted client and unknown customer"
        authorizeTrustedClient(restClient)
        def customerId = "nonexistingcustomer@test.de"

        when: "request for user profile is made"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/${customerId}',
                contentType: format,
                requestContentType: URLENC
        )

        then: "request results in error"
        with(response) {
            status == SC_BAD_REQUEST
            data.errors[0].type == 'UnknownIdentifierError'
            data.errors[0].message == 'Cannot find user with propertyValue \'${customerId}\''
        }

        where:
        format << [XML, JSON]
    }

    def "Path matches for authenticated customer: #format"() {
        given: "authorized customer"
        def customer = registerAndAuthorizeCustomer(restClient, format)

        when: "request for user profile is made"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id,
                contentType: format,
                requestContentType: URLENC
        )

        then: "request results in success"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        where:
        format << [XML, JSON]
    }

    def "Path matches for unauthenticated customer: #format"() {
        given: "existing customer"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient)
        removeAuthorization(restClient)

        when: "request for user profile is made without any authentication"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id,
                contentType: format,
                requestContentType: URLENC
        )

        then: "request results in success"
        with(response) { status == SC_UNAUTHORIZED }

        where:
        format << [XML, JSON]
    }

    def "Matching path for current customer: #format"() {
        given: "authorized customer"
        def customer = registerAndAuthorizeCustomer(restClient, format)

        when: "request for user profile is made without any authentication"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/current/addresses',
                contentType: format,
                requestContentType: URLENC
        )

        then: "request results in success"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        where:
        format << [XML, JSON]
    }

    def "Matching path for customer manager: #format"() {
        given: "authorized customer"
        authorizeCustomerManager(restClient)

        when: "request for user profile is made without any authentication"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/customergroups',
                contentType: format,
                requestContentType: URLENC
        )

        then: "request results in success"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        where:
        format << [XML, JSON]
    }

    def "Matching path for client: #format"() {
        given: "authorized customer"
        authorizeClient(restClient)
        def randomUID = System.currentTimeMillis() + "@test.v2.com"

        when: "request for user profile is made without any authentication"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users',
                contentType: format,
                body: [
                        'login'    : randomUID,
                        'password' : PASSWORD,
                        'firstName': 'fName',
                        'lastName' : 'lName',
                        'titleCode': 'dr'
                ],
                requestContentType: URLENC
        )

        then: "request results in success"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_CREATED
        }

        where:
        format << [XML, JSON]
    }
}
