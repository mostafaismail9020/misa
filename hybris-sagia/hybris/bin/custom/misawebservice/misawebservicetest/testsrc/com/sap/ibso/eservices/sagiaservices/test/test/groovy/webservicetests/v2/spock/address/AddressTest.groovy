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
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.address

import de.hybris.bootstrap.annotations.ManualTest
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.users.AbstractUserTest
import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll

import static groovyx.net.http.ContentType.*
import static org.apache.http.HttpStatus.*

@ManualTest
@Unroll
class AddressTest extends AbstractUserTest {

    def "Customer creates address when request: #requestFormat and response: #responseFormat"() {
        //This is happy path
        given: "a registered and logged in customer"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, responseFormat)
        authorizeCustomer(restClient, customer)

        when: "user attempts to create a new address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/",
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "the address is added and proper address id is returned"
        with(response) {
            if (isNotEmpty(data)) println data
            status == SC_CREATED
            data.id != null
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        URLENC        | XML            | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        JSON          | JSON           | "{\"titleCode\": \"${CUSTOMER_TITLE_CODE}\", \"town\": \"${CUSTOMER_ADDRESS_TOWN}\", \"line1\": \"${CUSTOMER_ADDRESS_LINE1}\", \"postalCode\": \"${CUSTOMER_ADDRESS_POSTAL_CODE}\", \"country\": { \"isocode\": \"${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}\" }, \"firstName\": \"${CUSTOMER_FIRST_NAME}\", \"lastName\": \"${CUSTOMER_LAST_NAME}\"}"
        XML           | XML            | "<address><titleCode>${CUSTOMER_TITLE_CODE}</titleCode><town>${CUSTOMER_ADDRESS_TOWN}</town><line1>${CUSTOMER_ADDRESS_LINE1}</line1><postalCode>${CUSTOMER_ADDRESS_POSTAL_CODE}</postalCode><country><isocode>${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}</isocode></country><firstName>${CUSTOMER_FIRST_NAME}</firstName><lastName>${CUSTOMER_LAST_NAME}</lastName></address>"
    }

    def "Customer creates address [with no titleCode] when request: #requestFormat and response: #responseFormat"() {
        //This is happy path
        given: "a registered and logged in customer"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, responseFormat)
        authorizeCustomer(restClient, customer)

        when: "user attempts to create a new address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/",
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "the address is added and proper address id is returned"
        with(response) {
            if (isNotEmpty(data)) println data
            status == SC_CREATED
            data.id != null
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        URLENC        | XML            | ['town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        JSON          | JSON           | "{\"town\": \"${CUSTOMER_ADDRESS_TOWN}\", \"line1\": \"${CUSTOMER_ADDRESS_LINE1}\", \"postalCode\": \"${CUSTOMER_ADDRESS_POSTAL_CODE}\", \"country\": { \"isocode\": \"${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}\" }, \"firstName\": \"${CUSTOMER_FIRST_NAME}\", \"lastName\": \"${CUSTOMER_LAST_NAME}\"}"
        XML           | XML            | "<address><town>${CUSTOMER_ADDRESS_TOWN}</town><line1>${CUSTOMER_ADDRESS_LINE1}</line1><postalCode>${CUSTOMER_ADDRESS_POSTAL_CODE}</postalCode><country><isocode>${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}</isocode></country><firstName>${CUSTOMER_FIRST_NAME}</firstName><lastName>${CUSTOMER_LAST_NAME}</lastName></address>"
    }

    def "Customer creates address with district and cellphone when request: #requestFormat and response: #responseFormat"() {
        //This is happy path
        given: "a registered and logged in customer"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, responseFormat)
        authorizeCustomer(restClient, customer)

        when: "user attempts to create a new address"
        HttpResponseDecorator response = restClient.post(
                        path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/",
                        body: postBody,
                        contentType: responseFormat,
                        requestContentType: requestFormat)

        then: "the address is added and proper address id is returned"
        with(response) {
                if (isNotEmpty(data)) println data
                status == SC_CREATED
                data.id != null
                data.district == CUSTOMER_ADDRESS_DISTRICT
                data.cellphone == CUSTOMER_ADDRESS_CELLPHONE
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME, 'district': CUSTOMER_ADDRESS_DISTRICT, 'cellphone': CUSTOMER_ADDRESS_CELLPHONE]
        URLENC        | XML            | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME, 'district': CUSTOMER_ADDRESS_DISTRICT, 'cellphone': CUSTOMER_ADDRESS_CELLPHONE]
        JSON          | JSON           | "{\"titleCode\": \"${CUSTOMER_TITLE_CODE}\", \"town\": \"${CUSTOMER_ADDRESS_TOWN}\", \"line1\": \"${CUSTOMER_ADDRESS_LINE1}\", \"postalCode\": \"${CUSTOMER_ADDRESS_POSTAL_CODE}\", \"country\": { \"isocode\": \"${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}\" }, \"firstName\": \"${CUSTOMER_FIRST_NAME}\", \"lastName\": \"${CUSTOMER_LAST_NAME}\",\"district\": \"${CUSTOMER_ADDRESS_DISTRICT}\" ,\"cellphone\": \"${CUSTOMER_ADDRESS_CELLPHONE}\"  }"
        XML           | XML            | "<address><titleCode>${CUSTOMER_TITLE_CODE}</titleCode><town>${CUSTOMER_ADDRESS_TOWN}</town><line1>${CUSTOMER_ADDRESS_LINE1}</line1><postalCode>${CUSTOMER_ADDRESS_POSTAL_CODE}</postalCode><country><isocode>${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}</isocode></country><firstName>${CUSTOMER_FIRST_NAME}</firstName><lastName>${CUSTOMER_LAST_NAME}</lastName><district>${CUSTOMER_ADDRESS_DISTRICT}</district><cellphone>${CUSTOMER_ADDRESS_CELLPHONE}</cellphone></address>"
    }

    def "Customer gets single address with cellphone and district: #format"() {
        given: "a registered and logged in customer with already existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)
        def address = createAddressWithAllMappingFileds(restClient, customer, format)

        when: "user attempts to retrieve his address"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                contentType: format,
                requestContentType: URLENC)

        then: "he gets requested address details with default fields"
        with(response) {
                if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
                status == SC_OK
                data.id != null
                data.firstName == CUSTOMER_FIRST_NAME
                data.lastName == CUSTOMER_LAST_NAME
                data.titleCode == CUSTOMER_TITLE_CODE
                data.line1 == CUSTOMER_ADDRESS_LINE1
                data.line2 == CUSTOMER_ADDRESS_LINE2
                data.postalCode == CUSTOMER_ADDRESS_POSTAL_CODE
                data.phone == CUSTOMER_ADDRESS_PHONE
                data.country.isocode == CUSTOMER_ADDRESS_COUNTRY_ISO_CODE
                data.town == CUSTOMER_ADDRESS_TOWN
                data.district == CUSTOMER_ADDRESS_DISTRICT
                data.cellphone == CUSTOMER_ADDRESS_CELLPHONE

        }

        where:
        format << [XML, JSON]
    }


    def "Customer gets single address : #format"() {
        given: "a registered and logged in customer with already existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)
        def address = createAddress(restClient, customer, format)

        when: "user attempts to retrieve his address"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                contentType: format,
                requestContentType: URLENC)

        then: "he gets requested address details with default fields"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
            data.id != null
            data.firstName == CUSTOMER_FIRST_NAME
            data.lastName == CUSTOMER_LAST_NAME
            data.titleCode == CUSTOMER_TITLE_CODE
            data.line1 == CUSTOMER_ADDRESS_LINE1
            data.line2 == CUSTOMER_ADDRESS_LINE2
            data.postalCode == CUSTOMER_ADDRESS_POSTAL_CODE
            data.phone == CUSTOMER_ADDRESS_PHONE
            data.country.isocode == CUSTOMER_ADDRESS_COUNTRY_ISO_CODE
            data.town == CUSTOMER_ADDRESS_TOWN
        }

        where:
        format << [XML, JSON]
    }

    def "Customer gets single address when parameter fields euqals DEFAULT, checking presence of titleCode : #format"() {
        given: "a registered and logged in customer with already existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)
        def address = createAddress(restClient, customer, format)

        when: "user attempts to retrieve his address"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                query: [
                        'fields': FIELD_SET_LEVEL_DEFAULT
                ],
                contentType: format,
                requestContentType: URLENC)

        then: "he gets requested address details with default fields"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
            data.id != null
            data.firstName == CUSTOMER_FIRST_NAME
            data.lastName == CUSTOMER_LAST_NAME
            data.titleCode == CUSTOMER_TITLE_CODE
        }

        where:
        format << [XML, JSON]
    }

    def "Customer gets non existing address : #format"() {
        given: "a registered and logged in customer"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)

        when: "user attempts to retrieve address that does not exist"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/notExistingAddress",
                contentType: format,
                requestContentType: URLENC)

        then: "he gets bad request response"
        // BAD_REQUEST and not NOT_FOUND because NOT_FOUND is used when a method can not be matched to the call
        // and in this case method was matched, only the parameter (addres ID) is wrong
        with(response) {
            status == SC_BAD_REQUEST
            data.errors[0].message == 'Address with given id: \'notExistingAddress\' doesn\'t exist or belong to another user'
        }

        where:
        format << [XML, JSON]
    }

    def "Customer deletes his address : #format"() {
        given: "a registered and logged in customer with already existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)
        def address = createAddress(restClient, customer, format)

        when: "user attempts to remove his address"
        HttpResponseDecorator response = restClient.delete(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                contentType: format,
                requestContentType: URLENC)

        then: "the address is removed"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        where:
        format << [XML, JSON]
    }

    def "Customer PUTs not existing address when request: #requestFormat and response: #responseFormat"() {
        given: "a registered and logged in customer"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

        when: "user attempts to pu to non existing address"
        HttpResponseDecorator response = restClient.put(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/notExistingAddress",
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "the error message is thrown"
        def msg = 'Address with given id : \'notExistingAddress\' doesn\'t exist or belong to another user'
        with(response) {
            status == SC_BAD_REQUEST
            data.errors[0].message == 'Address with given id: \'notExistingAddress\' doesn\'t exist or belong to another user'
        }

        where:
        requestFormat | responseFormat
        URLENC        | JSON
        URLENC        | XML
        JSON          | JSON
        XML           | XML
    }

    def "Customer deletes not existing address : #format"() {
        given: "a registered and logged in customer"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)

        when: "user attempts to remove non existing address"
        HttpResponseDecorator response = restClient.delete(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/notExistingAddress",
                contentType: format,
                requestContentType: URLENC)

        then: "error is thrown"
        def msg = 'Address with given id : \'notExistingAddress\' doesn\'t exist or belong to another user'
        with(response) {
            status == SC_BAD_REQUEST
            data.errors[0].message == 'Address with given id: \'notExistingAddress\' doesn\'t exist or belong to another user'
        }

        where:
        format << [XML, JSON]
    }

    def "Customer edits his own address when request: #requestFormat and response: #responseFormat"() {
        given: "a registered user with existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, responseFormat)
        authorizeCustomer(restClient, customer)
        def address = createAddress(restClient, customer, responseFormat)

        when: "user attempts to edit address"
        HttpResponseDecorator response = restClient.patch(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        and: "retrieves the hopefully edited address"
        def newAddress = getAddress(restClient, customer, address, responseFormat, true)

        then: "address is correctly updated"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        and: "new values are visible on the edited fields"
        with(newAddress) {
            town == 'Montreal'
            postalCode == '80335'
            phone == '901901901'

            // those shouldn't change:
            id != null
            firstName == CUSTOMER_FIRST_NAME
            line1 == CUSTOMER_ADDRESS_LINE1
            line2 == CUSTOMER_ADDRESS_LINE2
            country.isocode == CUSTOMER_ADDRESS_COUNTRY_ISO_CODE
            firstName == CUSTOMER_FIRST_NAME
            lastName == CUSTOMER_LAST_NAME
            titleCode == CUSTOMER_TITLE_CODE
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['town': 'Montreal', 'postalCode': '80335', 'phone': '901901901']
        URLENC        | XML            | ['town': 'Montreal', 'postalCode': '80335', 'phone': '901901901']
        JSON          | JSON           | '{ "town": "Montreal", "postalCode": "80335", "phone": "901901901" }'
        XML           | XML            | "<address><town>Montreal</town><postalCode>80335</postalCode><phone>901901901</phone></address>"
    }

    def "Customer replaces his own address when request: #requestFormat and response: #responseFormat"() {
        given: "a registered user with existing address"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)
        def address = createAddress(restClient, customer, responseFormat)

        when: "user attempts to replace address with only required fields"
        HttpResponseDecorator response = restClient.put(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        and: "retrieves his updated address"
        def newAddress = getAddress(restClient, customer, address, responseFormat, true)

        then: "address is correctly replaced and all not required fields are null"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        and: "contents of the address are proper"
        with(newAddress) {
            titleCode == 'mr'
            town == 'Montreal'
            line1 == 'Maple'
            firstName == 'John'
            lastName == 'Smith'
            phone == '901901901'
            postalCode == '80335'
            country.isocode == 'US'
            (isEmpty(line2)) // should be empty
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['titleCode': 'mr', 'town': 'Montreal', 'postalCode': '80335', 'line1': 'Maple', 'country.isocode': 'US', 'firstName': 'John', 'lastName': 'Smith', 'phone': '901901901']
        URLENC        | XML            | ['titleCode': 'mr', 'town': 'Montreal', 'postalCode': '80335', 'line1': 'Maple', 'country.isocode': 'US', 'firstName': 'John', 'lastName': 'Smith', 'phone': '901901901']
        JSON          | JSON           | '{ "titleCode": "mr", "town": "Montreal", "postalCode": "80335", "line1": "Maple", "country": { "isocode": "US" }, "firstName": "John", "lastName": "Smith", "phone": "901901901" }'
        XML           | XML            | "<address><titleCode>mr</titleCode><town>Montreal</town><postalCode>80335</postalCode><line1>Maple</line1><country><isocode>US</isocode></country><firstName>John</firstName><lastName>Smith</lastName><phone>901901901</phone></address>"
    }

    def "Customer replaces his own address [with no titleCode] when request: #requestFormat and response: #responseFormat"() {
        given: "a registered user with existing address"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)
        def address = createAddress(restClient, customer, responseFormat)

        when: "user attempts to replace address with only required fields"
        HttpResponseDecorator response = restClient.put(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        and: "retrieves his updated address"
        def newAddress = getAddress(restClient, customer, address, responseFormat, true)

        then: "address is correctly replaced and all not required fields are null"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        and: "contents of the address are proper"
        with(newAddress) {
            town == 'Montreal'
            line1 == 'Maple'
            firstName == 'John'
            lastName == 'Smith'
            postalCode == '80335'
            country.isocode == 'US'
            (isEmpty(line2)) // should be empty
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['town': 'Montreal', 'postalCode': '80335', 'line1': 'Maple', 'country.isocode': 'US', 'firstName': 'John', 'lastName': 'Smith']
        URLENC        | XML            | ['town': 'Montreal', 'postalCode': '80335', 'line1': 'Maple', 'country.isocode': 'US', 'firstName': 'John', 'lastName': 'Smith']
        JSON          | JSON           | '{ "town": "Montreal", "postalCode": "80335", "line1": "Maple", "country": { "isocode": "US" }, "firstName": "John", "lastName": "Smith" }'
        XML           | XML            | "<address><town>Montreal</town><postalCode>80335</postalCode><line1>Maple</line1><country><isocode>US</isocode></country><firstName>John</firstName><lastName>Smith</lastName></address>"
    }

    def "Customer replaces his own address with missing requried field when request: #requestFormat and response: #responseFormat"() {
        given: "a registered user with existing address"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)
        def address = createAddress(restClient, customer, responseFormat)

        when: "user attempts to replace address with missing required field"
        HttpResponseDecorator response = restClient.put(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address.id,
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "user receives an error"
        with(response) {
            status == SC_BAD_REQUEST
            data.errors.size() > 0
            data.errors[0].type == 'ValidationError'
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | BAD_ADDRESS_DE
        URLENC        | XML            | BAD_ADDRESS_DE
        JSON          | JSON           | BAD_ADDRESS_DE_JSON
        XML           | XML            | BAD_ADDRESS_DE_XML
    }

    def "Customer changes his default address : #format"() {
        given: "a logged in customer with two addresses"
        def customer = registerAndAuthorizeCustomer(restClient, format)
        def address = createAddress(restClient, customer, format)
        def secondAddress = [
                'titleCode'      : 'mr',
                'firstName'      : 'Jane',
                'lastName'       : 'Smith',
                'line1'          : 'Zwyciestwa 23',
                'line2'          : 'pietro 2',
                'postalCode'     : '44-100',
                'phone'          : '11123234545',
                'town'           : 'Gliwice',
                'country.isocode': 'PL'
        ]
        def address2 = createAddress(secondAddress, restClient, customer, format)

        when: "customer marks second of the addresses as default"
        HttpResponseDecorator response = restClient.patch(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/" + address2.id,
                query: [
                        'defaultAddress': 'true'
                ],
                contentType: format,
                requestContentType: URLENC)

        and: "retrieves his profile"
        HttpResponseDecorator response2 = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id,
                contentType: format,
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                requestContentType: URLENC)

        then: "his changes are saved and his profile shows new default address"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }
        and:
        with(response2) {
            data.defaultAddress.id == address2.id
        }

        where:
        format << [XML, JSON]
    }

    def "Customer verifies correct address when request: #requestFormat and response: #responseFormat"() {
        given: "a logged in customer"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

        when: "customer verifies correct address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/verification",
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "decision is accepted"
        with(response) {
            data.decision == "ACCEPT"
            !isNotEmpty(data.suggestedAddresses)
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | GOOD_ADDRESS_DE
        URLENC        | XML            | GOOD_ADDRESS_DE
        JSON          | JSON           | GOOD_ADDRESS_DE_JSON
        XML           | XML            | GOOD_ADDRESS_DE_XML
    }

    def "Address gets rejected by validator when request: #requestFormat and response: #responseFormat"() {
        given: "a logged in customer"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

        when: "customer verifies correct address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/verification",
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "decision is rejected"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
            data.decision == "REJECT"
            !isNotEmpty(data.suggestedAddresses)
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | BAD_LINE1_ADDRESS_DE
        URLENC        | XML            | BAD_LINE1_ADDRESS_DE
        JSON          | JSON           | BAD_LINE1_ADDRESS_DE_JSON
        XML           | XML            | BAD_LINE1_ADDRESS_DE_XML
    }

    def "Address gets rejected by service when request: #requestFormat and response: #responseFormat"() {
        given: "a logged in customer"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

        when: "customer verifies correct address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/verification",
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "decision is rejected"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
            data.decision == "REJECT"
            !isNotEmpty(data.suggestedAddresses)
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | BAD_LINE2_ADDRESS_DE
        URLENC        | XML            | BAD_LINE2_ADDRESS_DE
        JSON          | JSON           | BAD_LINE2_ADDRESS_DE_JSON
        XML           | XML            | BAD_LINE2_ADDRESS_DE_XML
    }

    def "Address gets corrected after verification when request: #requestFormat and response: #responseFormat"() {
        given: "a logged in customer"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

        when: "customer verifies correct address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/verification",
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "decision is rejected"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
            data.decision == "REVIEW"
            isNotEmpty(data.suggestedAddresses)
            data.suggestedAddresses[0].line1.toString().contains('corrected')
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ADDRESS_TO_CORRECT
        URLENC        | XML            | ADDRESS_TO_CORRECT
        JSON          | JSON           | ADDRESS_TO_CORRECT_JSON
        XML           | XML            | ADDRESS_TO_CORRECT_XML
    }

    def "Current customer creates address when request: #requestFormat and response: #responseFormat"() {
        //This is happy path
        given: "a registered and logged in customer"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, responseFormat)
        authorizeCustomer(restClient, customer)

        when: "user attempts to create a new address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/current/addresses/',
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "the address is added and proper address id is returned"
        with(response) {
            if (isNotEmpty(data)) println data
            status == SC_CREATED
            data.id != null
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        URLENC        | XML            | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        JSON          | JSON           | "{\"titleCode\": \"${CUSTOMER_TITLE_CODE}\", \"town\": \"${CUSTOMER_ADDRESS_TOWN}\", \"line1\": \"${CUSTOMER_ADDRESS_LINE1}\", \"postalCode\": \"${CUSTOMER_ADDRESS_POSTAL_CODE}\", \"country\": { \"isocode\": \"${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}\" }, \"firstName\": \"${CUSTOMER_FIRST_NAME}\", \"lastName\": \"${CUSTOMER_LAST_NAME}\"}"
        XML           | XML            | "<address><titleCode>${CUSTOMER_TITLE_CODE}</titleCode><town>${CUSTOMER_ADDRESS_TOWN}</town><line1>${CUSTOMER_ADDRESS_LINE1}</line1><postalCode>${CUSTOMER_ADDRESS_POSTAL_CODE}</postalCode><country><isocode>${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}</isocode></country><firstName>${CUSTOMER_FIRST_NAME}</firstName><lastName>${CUSTOMER_LAST_NAME}</lastName></address>"
    }

    def "Current customer gets single address : #format"() {
        given: "a registered and logged in customer with already existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)
        def address = createAddress(restClient, customer, format)

        when: "user attempts to retrieve his address"
        HttpResponseDecorator response = restClient.get(
                path: getBasePathWithSite() + '/users/current/addresses/' + address.id,
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                contentType: format,
                requestContentType: URLENC)

        then: "he gets requested address details with default fields"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
            data.id != null
            data.firstName == CUSTOMER_FIRST_NAME
            data.town == CUSTOMER_ADDRESS_TOWN
            data.line1 == CUSTOMER_ADDRESS_LINE1
            data.line2 == CUSTOMER_ADDRESS_LINE2
            data.postalCode == CUSTOMER_ADDRESS_POSTAL_CODE
            data.phone == CUSTOMER_ADDRESS_PHONE
            data.country.isocode == CUSTOMER_ADDRESS_COUNTRY_ISO_CODE
            data.firstName == CUSTOMER_FIRST_NAME
            data.lastName == CUSTOMER_LAST_NAME
            data.titleCode == CUSTOMER_TITLE_CODE
        }

        where:
        format << [XML, JSON]
    }

    def "Current customer deletes his address : #format"() {
        given: "a registered and logged in customer with already existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, format)
        authorizeCustomer(restClient, customer)
        def address = createAddress(restClient, customer, format)

        when: "user attempts to remove his address"
        HttpResponseDecorator response = restClient.delete(
                path: getBasePathWithSite() + '/users/current/addresses/' + address.id,
                contentType: format,
                requestContentType: URLENC)

        then: "the address is removed"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        where:
        format << [XML, JSON]
    }

    def "Current customer edits his own address when request: #requestFormat and response: #responseFormat"() {
        given: "a registered user with existing address"
        authorizeTrustedClient(restClient)
        def customer = registerCustomer(restClient, responseFormat)
        authorizeCustomer(restClient, customer)
        def address = createAddress(restClient, customer, responseFormat)

        when: "user attempts to edit address"
        HttpResponseDecorator response = restClient.patch(
                path: getBasePathWithSite() + '/users/current/addresses/' + address.id,
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        and: "retrieves the hopefully edited address"
        def newAddress = getAddress(restClient, customer, address, responseFormat, true)

        then: "address is correctly updated"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        and: "new values are visible on the edited fields"
        with(newAddress) {
            town == 'Montreal'
            postalCode == '80335'

            // those shouldn't change:
            id != null
            firstName == CUSTOMER_FIRST_NAME
            line1 == CUSTOMER_ADDRESS_LINE1
            line2 == CUSTOMER_ADDRESS_LINE2
            phone == CUSTOMER_ADDRESS_PHONE
            country.isocode == CUSTOMER_ADDRESS_COUNTRY_ISO_CODE
            firstName == CUSTOMER_FIRST_NAME
            lastName == CUSTOMER_LAST_NAME
            titleCode == CUSTOMER_TITLE_CODE
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['town': 'Montreal', 'postalCode': '80335']
        URLENC        | XML            | ['town': 'Montreal', 'postalCode': '80335']
        JSON          | JSON           | '{ "town": "Montreal", "postalCode": "80335" }'
        XML           | XML            | "<address><town>Montreal</town><postalCode>80335</postalCode></address>"
    }

    def "Current customer replaces his own address when request: #requestFormat and response: #responseFormat"() {
        given: "a registered user with existing address"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)
        def address = createAddress(restClient, customer, responseFormat)

        when: "user attempts to replace address with only required fields"
        HttpResponseDecorator response = restClient.put(
                path: getBasePathWithSite() + '/users/current/addresses/' + address.id,
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        and: "retrieves his updated address"
        def newAddress = getAddress(restClient, customer, address, responseFormat, true)

        then: "address is correctly replaced and all not required fields are null"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }

        and: "contents of the address are proper"
        with(newAddress) {
            titleCode == 'mr'
            town == 'Montreal'
            line1 == 'Maple'
            firstName == 'John'
            lastName == 'Smith'
            postalCode == '80335'
            country.isocode == 'US'
            (isEmpty(line2)) // should be empty
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['titleCode': 'mr', 'town': 'Montreal', 'postalCode': '80335', 'line1': 'Maple', 'country.isocode': 'US', 'firstName': 'John', 'lastName': 'Smith']
        URLENC        | XML            | ['titleCode': 'mr', 'town': 'Montreal', 'postalCode': '80335', 'line1': 'Maple', 'country.isocode': 'US', 'firstName': 'John', 'lastName': 'Smith']
        JSON          | JSON           | '{ "titleCode": "mr", "town": "Montreal", "postalCode": "80335", "line1": "Maple", "country": { "isocode": "US" }, "firstName": "John", "lastName": "Smith" }'
        XML           | XML            | "<address><titleCode>mr</titleCode><town>Montreal</town><postalCode>80335</postalCode><line1>Maple</line1><country><isocode>US</isocode></country><firstName>John</firstName><lastName>Smith</lastName></address>"
    }

    def "Current customer changes his default address : #format"() {
        given: "a logged in customer with two addresses"
        def customer = registerAndAuthorizeCustomer(restClient, format)
        def address = createAddress(restClient, customer, format)
        def secondAddress = [
                'titleCode'      : 'mr',
                'firstName'      : 'Jane',
                'lastName'       : 'Smith',
                'line1'          : 'Zwyciestwa 23',
                'line2'          : 'pietro 2',
                'postalCode'     : '44-100',
                'phone'          : '11123234545',
                'town'           : 'Gliwice',
                'country.isocode': 'PL'
        ]
        def address2 = createAddress(secondAddress, restClient, customer, format)

        when: "customer marks second of the addresses as default"
        HttpResponseDecorator response = restClient.patch(
                path: getBasePathWithSite() + '/users/current/addresses/' + address2.id,
                query: [
                        'defaultAddress': 'true'
                ],
                contentType: format,
                requestContentType: URLENC)

        and: "retrieves his profile"
        HttpResponseDecorator response2 = restClient.get(
                path: getBasePathWithSite() + '/users/' + customer.id,
                contentType: format,
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                requestContentType: URLENC)

        then: "his changes are saved and his profile shows new default address"
        with(response) {
            if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
            status == SC_OK
        }
        and:
        with(response2) {
            data.defaultAddress.id == address2.id
        }

        where:
        format << [XML, JSON]
    }

    def "Current customer verifies correct address when request: #requestFormat and response: #responseFormat"() {
        given: "a logged in customer"
        def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

        when: "customer verifies correct address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/current/addresses/verification',
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "decision is accepted"
        with(response) {
            data.decision == "ACCEPT"
            !isNotEmpty(data.suggestedAddresses)
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | GOOD_ADDRESS_DE
        URLENC        | XML            | GOOD_ADDRESS_DE
        JSON          | JSON           | GOOD_ADDRESS_DE_JSON
        XML           | XML            | GOOD_ADDRESS_DE_XML
    }

    def "Customer manager creates address for customer when request: #requestFormat and response: #responseFormat"() {
        given: "a registered customer and a registered and logged in customer manager"
        def customer = registerCustomerWithTrustedClient(restClient, JSON)
        authorizeCustomerManager(restClient)

        when: "customer manager attempts to create a new address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/",
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "the address is added and proper address id is returned"
        with(response) {
            if (isNotEmpty(data)) println data
            status == SC_CREATED
            data.id != null
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        URLENC        | XML            | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        JSON          | JSON           | "{\"titleCode\": \"${CUSTOMER_TITLE_CODE}\", \"town\": \"${CUSTOMER_ADDRESS_TOWN}\", \"line1\": \"${CUSTOMER_ADDRESS_LINE1}\", \"postalCode\": \"${CUSTOMER_ADDRESS_POSTAL_CODE}\", \"country\": { \"isocode\": \"${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}\" }, \"firstName\": \"${CUSTOMER_FIRST_NAME}\", \"lastName\": \"${CUSTOMER_LAST_NAME}\"}"
        XML           | XML            | "<address><titleCode>${CUSTOMER_TITLE_CODE}</titleCode><town>${CUSTOMER_ADDRESS_TOWN}</town><line1>${CUSTOMER_ADDRESS_LINE1}</line1><postalCode>${CUSTOMER_ADDRESS_POSTAL_CODE}</postalCode><country><isocode>${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}</isocode></country><firstName>${CUSTOMER_FIRST_NAME}</firstName><lastName>${CUSTOMER_LAST_NAME}</lastName></address>"
    }

    def "Trusted client creates address for customer when request: #requestFormat and response: #responseFormat"() {
        given: "a registered customer and a registered and trusted client"
        def customer = registerCustomerWithTrustedClient(restClient, JSON)
        authorizeTrustedClient(restClient)

        when: "trusted client attempts to create a new address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/' + customer.id + "/addresses/",
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "the address is added and proper address id is returned"
        with(response) {
            if (isNotEmpty(data)) println data
            status == SC_CREATED
            data.id != null
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        URLENC        | XML            | ['titleCode': CUSTOMER_TITLE_CODE, 'town': CUSTOMER_ADDRESS_TOWN, 'line1': CUSTOMER_ADDRESS_LINE1, 'postalCode': CUSTOMER_ADDRESS_POSTAL_CODE, 'country.isocode': CUSTOMER_ADDRESS_COUNTRY_ISO_CODE, 'firstName': CUSTOMER_FIRST_NAME, 'lastName': CUSTOMER_LAST_NAME]
        JSON          | JSON           | "{\"titleCode\": \"${CUSTOMER_TITLE_CODE}\", \"town\": \"${CUSTOMER_ADDRESS_TOWN}\", \"line1\": \"${CUSTOMER_ADDRESS_LINE1}\", \"postalCode\": \"${CUSTOMER_ADDRESS_POSTAL_CODE}\", \"country\": { \"isocode\": \"${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}\" }, \"firstName\": \"${CUSTOMER_FIRST_NAME}\", \"lastName\": \"${CUSTOMER_LAST_NAME}\"}"
        XML           | XML            | "<address><titleCode>${CUSTOMER_TITLE_CODE}</titleCode><town>${CUSTOMER_ADDRESS_TOWN}</town><line1>${CUSTOMER_ADDRESS_LINE1}</line1><postalCode>${CUSTOMER_ADDRESS_POSTAL_CODE}</postalCode><country><isocode>${CUSTOMER_ADDRESS_COUNTRY_ISO_CODE}</isocode></country><firstName>${CUSTOMER_FIRST_NAME}</firstName><lastName>${CUSTOMER_LAST_NAME}</lastName></address>"
    }

    def "Guest user verifies correct address when request: #requestFormat and response: #responseFormat"() {
        given: "authorized guest user"
        authorizeClient(restClient)

        when: "guest verifies correct address"
        HttpResponseDecorator response = restClient.post(
                path: getBasePathWithSite() + '/users/anonymous/addresses/verification',
                query: [
                        'fields': FIELD_SET_LEVEL_FULL
                ],
                body: postBody,
                contentType: responseFormat,
                requestContentType: requestFormat)

        then: "decision is accepted"
        with(response) {
            data.decision == "ACCEPT"
            !isNotEmpty(data.suggestedAddresses)
        }

        where:
        requestFormat | responseFormat | postBody
        URLENC        | JSON           | GOOD_ADDRESS_DE
        URLENC        | XML            | GOOD_ADDRESS_DE
        JSON          | JSON           | GOOD_ADDRESS_DE_JSON
        XML           | XML            | GOOD_ADDRESS_DE_XML
    }
}
