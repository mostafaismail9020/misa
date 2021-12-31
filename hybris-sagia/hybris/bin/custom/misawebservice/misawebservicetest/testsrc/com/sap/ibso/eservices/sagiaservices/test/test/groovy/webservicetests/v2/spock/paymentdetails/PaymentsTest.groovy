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
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.paymentdetails

import de.hybris.bootstrap.annotations.ManualTest
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v2.spock.users.AbstractUserTest

import groovyx.net.http.HttpResponseDecorator
import spock.lang.Unroll

import static groovyx.net.http.ContentType.*
import static org.apache.http.HttpStatus.*

@ManualTest
@Unroll
class PaymentsTest extends AbstractUserTest {

	def "User creates payment info when request: #requestFormat and response: #responseFormat"() {
		given: "a logged in user with created cart"
		def customerWithCart = createAndAuthorizeCustomerWithCart(restClient, responseFormat)
		def customer = customerWithCart[0]
		def cart = customerWithCart[1]

		when: "user attempts to store payment info for his cart"
		HttpResponseDecorator response = restClient.post(
				path: getBasePathWithSite() + '/users/' + customer.id + '/carts/' + cart.code + '/paymentdetails',
				body: postBody,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "his payment details are saved and his first payment info is marked as default"
		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_CREATED
			data.saved == true
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | DEFAULT_PAYMENT
		URLENC        | JSON           | DEFAULT_PAYMENT
		JSON          | JSON           | DEFAULT_PAYMENT_JSON
		XML           | XML            | DEFAULT_PAYMENT_XML
	}

	def "User creates payment info [no titleCode] when request: #requestFormat and response: #responseFormat"() {
		given: "a logged in user with created cart"
		def customerWithCart = createAndAuthorizeCustomerWithCart(restClient, responseFormat)
		def customer = customerWithCart[0]
		def cart = customerWithCart[1]

		when: "user attempts to store payment info for his cart"
		HttpResponseDecorator response = restClient.post(
				path: getBasePathWithSite() + '/users/' + customer.id + '/carts/' + cart.code + '/paymentdetails',
				body: postBody,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "his payment details are saved and his first payment info is marked as default"
		with(response) {
			if (isNotEmpty(data) && isNotEmpty(data.errors)) println(data)
			status == SC_CREATED
			data.saved == true
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | DEFAULT_PAYMENT_NO_TITLECODE
		URLENC        | JSON           | DEFAULT_PAYMENT_NO_TITLECODE
		JSON          | JSON           | DEFAULT_PAYMENT_JSON_NO_TITLECODE
		XML           | XML            | DEFAULT_PAYMENT_XML_NO_TITLECODE
	}

	def "Anonymous user cannot set payment info when request: #requestFormat and response: #responseFormat"() {
		given: "anonymous cart"
		authorizeClient(restClient)
		def anonymous = ['id': 'anonymous']
		def cart = createCart(restClient, anonymous, responseFormat)

		when: "anonymous user attempts to store payment info for cart"
		HttpResponseDecorator response = restClient.post(
				path: getBasePathWithSite() + '/users/anonymous/carts/' + cart.guid + '/paymentdetails',
				body: postBody,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "he is not allowed to do this"
		with(response) { status == SC_UNAUTHORIZED }

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | DEFAULT_PAYMENT
		URLENC        | JSON           | DEFAULT_PAYMENT
		JSON          | JSON           | DEFAULT_PAYMENT_JSON
		XML           | XML            | DEFAULT_PAYMENT_XML
	}

	def "User creates payment without saving it when request: #requestFormat and response: #responseFormat"() {
		given: "a logged in user with created cart"
		def customerWithCart = createAndAuthorizeCustomerWithCart(restClient, responseFormat)
		def customer = customerWithCart[0]
		def cart = customerWithCart[1]

		when: "user attempts to store payment info for his cart"
		HttpResponseDecorator response = restClient.post(
				path: getBasePathWithSite() + '/users/' + customer.id + '/carts/' + cart.code + '/paymentdetails',
				body: postBody,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "his payment details is created"
		with(response) {
			status == SC_CREATED
			data.saved == false
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | UNSAVED_PAYMENT
		URLENC        | JSON           | UNSAVED_PAYMENT
		JSON          | JSON           | UNSAVED_PAYMENT_JSON
		XML           | XML            | UNSAVED_PAYMENT_XML
	}

	def "Customer manager creates payment info when request: #requestFormat and response: #responseFormat"() {
		given: "a user with created cart and logged in customer manager"
		def customerWithCart = createAndAuthorizeCustomerWithCart(restClient, responseFormat)
		def customer = customerWithCart[0]
		def cart = customerWithCart[1]
		authorizeCustomerManager(restClient)

		when: "manager attempts to store payment info for custmer's cart"
		HttpResponseDecorator response = restClient.post(
				path: getBasePathWithSite() + '/users/' + customer.id + '/carts/' + cart.code + '/paymentdetails',
				body: postBody,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: responseFormat,
				requestContentType: requestFormat)
		def isDefault = getPaymentInfo(restClient, customer, response.data.id, responseFormat)
		then: "the payment details are saved and first payment info is marked as default"
		with(response) {
			status == SC_CREATED
			isDefault.defaultPayment == true

		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | DEFAULT_PAYMENT
		URLENC        | JSON           | DEFAULT_PAYMENT
		JSON          | JSON           | DEFAULT_PAYMENT_JSON
		XML           | XML            | DEFAULT_PAYMENT_XML
	}

	def "User gets his payment info: #format"() {
		given: "user with payment info"
		def customerWithPaymentInfo = createCustomerWithPaymentInfo(restClient, format)
		def customer = customerWithPaymentInfo[0]
		def info = customerWithPaymentInfo[1]

		when: "user attempts to retrieve his info"
		HttpResponseDecorator response = restClient.get(
				path: getBasePathWithSite() + '/users/' + customer.id + '/paymentdetails/' + info.id,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: format,
				requestContentType: URLENC)
		then: "he is able to do so"
		with(response) {
			status == SC_OK
			data.accountHolderName == "John Doe"
		}
		where:
		format << [XML, JSON]
	}

	def "Customer can not get payment info of another customer: #format"() {
		given: "two users, one with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, format)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]

		def customer2 = registerAndAuthorizeCustomer(restClient, format)
		//now logged in as customer2

		when: "user attempts to retrieve info of another user"
		HttpResponseDecorator response = restClient.get(
				path: getBasePathWithSite() + '/users/' + customer1.id + '/paymentdetails/' + info1.id,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: format,
				requestContentType: URLENC)

		then: "he is not able to do so"
		with(response) { status == SC_FORBIDDEN }

		where:
		format << [XML, JSON]
	}

	def "Customer can not get not existing payment details : #format"() {
		given: "registered users"
		def customer = registerAndAuthorizeCustomer(restClient, format)

		when: "user tries to get payment details with wrong identifier"
		HttpResponseDecorator response = restClient.get(
				path: getBasePathWithSite() + '/users/' + customer.id + '/paymentdetails/wrongPaymentId',
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: format,
				requestContentType: URLENC)

		then: "he is not able to do so"
		with(response) {
			status == SC_BAD_REQUEST
			data.errors.size() > 0
			data.errors[0].type == 'ValidationError'
			data.errors[0].reason == 'unknownIdentifier'
			data.errors[0].subject == 'paymentDetailsId'
			data.errors[0].message == 'Payment details [wrongPaymentId] not found.'
		}
		where:
		format << [XML, JSON]
	}

	def "Customer can not create payment info for another customer when request: #requestFormat and response: #responseFormat"() {
		given: "two users, one with cart"
		def customer1 = registerCustomerWithTrustedClient(restClient, responseFormat)
		def cart1 = createCart(restClient, customer1, responseFormat)
		def customer2 = registerAndAuthorizeCustomer(restClient, responseFormat)

		when: "user attempts to create info for another user"
		HttpResponseDecorator response = restClient.post(
				path: getBasePathWithSite() + '/users/' + customer1.id + '/carts/' + cart1.code + '/paymentdetails',
				body: postBody,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "he is not able to do so"
		with(response) { status == SC_FORBIDDEN }

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | SECOND_PAYMENT_NON_DEFAULT
		URLENC        | JSON           | SECOND_PAYMENT_NON_DEFAULT
		JSON          | JSON           | SECOND_PAYMENT_NON_DEFAULT_JSON
		XML           | XML            | SECOND_PAYMENT_NON_DEFAULT_XML
	}

	def "Customer manager can get payment info of customer: #format"() {
		given: "user with payment info and logged in customer manager"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, format)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]
		authorizeCustomerManager(restClient)

		when: "customer manager attempts to retrieve info of customer"
		HttpResponseDecorator response = restClient.get(
				path: getBasePathWithSite() + '/users/' + customer1.id + '/paymentdetails/' + info1.id,
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: format,
				requestContentType: URLENC)

		then: "he is able to do so"
		with(response) { status == SC_OK }

		where:
		format << [XML, JSON]
	}

	def "Customer deletes his payment info : #format"() {
		given: "customer with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, format)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]

		when: "customer tries to delete his payment info"
		HttpResponseDecorator response = restClient.delete(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info1.id,
				contentType: format,
				requestContentType: URLENC)

		then: "he can do this"
		with(response) { status == SC_OK }

		where:
		format << [XML, JSON]
	}

	def "Customer manager deletes payment info: #format"() {
		given: "a user with payment info and logged in customer manager"
		def customerWithPaymentInfo = createCustomerWithPaymentInfo(restClient, format)
		def customer = customerWithPaymentInfo[0]
		def info = customerWithPaymentInfo[1]
		authorizeCustomerManager(restClient)

		when: "customer tries to delete his payment info"
		HttpResponseDecorator response = restClient.delete(
				path: getBasePathWithSite() + "/users/" + customer.id + "/paymentdetails/" + info.id,
				contentType: format,
				requestContentType: URLENC)

		then: "he can do this"
		with(response) { status == SC_OK }

		where:
		format << [XML, JSON]
	}

	
	def "Customer can not delete not exisiting payment details: #format"() {
		given: "registered users"
		def customer = registerAndAuthorizeCustomer(restClient, format)

		when: "user tries to delete payment details with wrong identifier"
		HttpResponseDecorator response = restClient.delete(
				path: getBasePathWithSite() + '/users/' + customer.id + '/paymentdetails/wrongPaymentId',
				query: ["fields": FIELD_SET_LEVEL_FULL],
				contentType: format,
				requestContentType: URLENC)

		then: "he is not able to do so"
		with(response) {
			status == SC_BAD_REQUEST
			data.errors.size() > 0
			data.errors[0].type == 'ValidationError'
			data.errors[0].reason == 'unknownIdentifier'
			data.errors[0].subject == 'paymentDetailsId'
			data.errors[0].message == 'Payment details [wrongPaymentId] not found.'
		}
		where:
		format << [XML, JSON]
	}

	def "Customer can not delete payment info of another customer : #format"() {
		given: "customer with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, format)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]
		def customer2 = registerAndAuthorizeCustomer(restClient, format)

		when: "customer tries to delete his payment info"
		HttpResponseDecorator response = restClient.delete(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info1.id,
				contentType: format,
				requestContentType: URLENC)

		then: "he can do this"
		with(response) { status == SC_FORBIDDEN }

		where:
		format << [XML, JSON]
	}

	def "Customer updates his payment details when request: #requestFormat and response: #responseFormat"() {
		given: "A customer with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, responseFormat)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]

		when: "customer updates his payment info"
		HttpResponseDecorator response = restClient.patch(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info1.id,
				body: postBody,
				contentType: responseFormat,
				requestContentType: requestFormat)
		def updatedInfo = getPaymentInfo(restClient, customer1, info1.id, responseFormat)

		then: "his changes are successfully saved"
		with(response) { status == SC_OK }

		and: "new values are visible"
		with(updatedInfo) {		
			issueNumber == "2"	
			startMonth == "12"	
			startYear == "2007"	
			expiryMonth == "02"
			defaultPayment == true
			subscriptionId == "updatedSubscriptionId"
			billingAddress.line1 == "updatedLine1"
			billingAddress.line2 == "updatedLine2"
			billingAddress.postalCode == "987654321"
			billingAddress.town == "updatedCity"
			billingAddress.country.isocode == "US"

			// other fields are untouched:
			accountHolderName == DEFAULT_PAYMENT.accountHolderName
			cardNumber.toString().substring(12) == DEFAULT_PAYMENT.cardNumber.substring(12)
			cardType.code == DEFAULT_PAYMENT.cardType
			expiryYear == DEFAULT_PAYMENT.expiryYear
			saved == DEFAULT_PAYMENT.saved
			billingAddress.titleCode == DEFAULT_PAYMENT."billingAddress.titleCode"
			billingAddress.firstName == DEFAULT_PAYMENT."billingAddress.firstName"
			billingAddress.lastName == DEFAULT_PAYMENT."billingAddress.lastName"
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | ["issueNumber": "2", "startMonth": "12", "startYear": "2007", "expiryMonth": "02", "subscriptionId": "updatedSubscriptionId", "defaultPayment": true, "billingAddress.line1": "updatedLine1", "billingAddress.line2": "updatedLine2", "billingAddress.postalCode": "987654321", "billingAddress.town": "updatedCity", "billingAddress.country.isocode": "US"]
		URLENC        | JSON           | ["issueNumber": "2", "startMonth": "12", "startYear": "2007", "expiryMonth": "02", "subscriptionId": "updatedSubscriptionId", "defaultPayment": true, "billingAddress.line1": "updatedLine1", "billingAddress.line2": "updatedLine2", "billingAddress.postalCode": "987654321", "billingAddress.town": "updatedCity", "billingAddress.country.isocode": "US"]
		JSON          | JSON           | "{ \"issueNumber\" : \"2\", \"startMonth\" : \"12\", \"startYear\" : \"2007\", \"expiryMonth\" : \"02\", \"subscriptionId\" : \"updatedSubscriptionId\", \"defaultPayment\" : true, \"billingAddress\" : { \"line1\" : \"updatedLine1\", \"line2\" : \"updatedLine2\", \"postalCode\" : \"987654321\", \"town\" : \"updatedCity\", \"country\": { \"isocode\": \"US\" }}}"
		XML           | XML            | "<paymentDetails><issueNumber>2</issueNumber><startMonth>12</startMonth><startYear>2007</startYear><expiryMonth>02</expiryMonth><subscriptionId>updatedSubscriptionId</subscriptionId><defaultPayment>true</defaultPayment><billingAddress><country><isocode>US</isocode></country><postalCode>987654321</postalCode><town>updatedCity</town><line1>updatedLine1</line1><line2>updatedLine2</line2></billingAddress></paymentDetails>"
	}

	def "Customer updates his payment details [with no titleCode] when request: #requestFormat and response: #responseFormat"() {
		given: "A customer with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, responseFormat)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]

		when: "customer updates his payment info"
		HttpResponseDecorator response = restClient.patch(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info1.id,
				body: postBody,
				contentType: responseFormat,
				requestContentType: requestFormat)
		def updatedInfo = getPaymentInfo(restClient, customer1, info1.id, responseFormat)

		then: "his changes are successfully saved"
		with(response) { status == SC_OK }

		and: "new values are visible"
		with(updatedInfo) {
			issueNumber == "2"
			startMonth == "12"
			startYear == "2007"
			expiryMonth == "02"
			defaultPayment == true
			subscriptionId == "updatedSubscriptionId"
			billingAddress.line1 == "updatedLine1"
			billingAddress.line2 == "updatedLine2"
			billingAddress.postalCode == "987654321"
			billingAddress.town == "updatedCity"
			billingAddress.country.isocode == "US"

			// other fields are untouched:
			accountHolderName == DEFAULT_PAYMENT_NO_TITLECODE.accountHolderName
			cardNumber.toString().substring(12) == DEFAULT_PAYMENT_NO_TITLECODE.cardNumber.substring(12)
			cardType.code == DEFAULT_PAYMENT_NO_TITLECODE.cardType
			expiryYear == DEFAULT_PAYMENT_NO_TITLECODE.expiryYear
			saved == DEFAULT_PAYMENT_NO_TITLECODE.saved
			billingAddress.firstName == DEFAULT_PAYMENT_NO_TITLECODE."billingAddress.firstName"
			billingAddress.lastName == DEFAULT_PAYMENT_NO_TITLECODE."billingAddress.lastName"
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | ["issueNumber": "2", "startMonth": "12", "startYear": "2007", "expiryMonth": "02", "subscriptionId": "updatedSubscriptionId", "defaultPayment": true, "billingAddress.line1": "updatedLine1", "billingAddress.line2": "updatedLine2", "billingAddress.postalCode": "987654321", "billingAddress.town": "updatedCity", "billingAddress.country.isocode": "US"]
		URLENC        | JSON           | ["issueNumber": "2", "startMonth": "12", "startYear": "2007", "expiryMonth": "02", "subscriptionId": "updatedSubscriptionId", "defaultPayment": true, "billingAddress.line1": "updatedLine1", "billingAddress.line2": "updatedLine2", "billingAddress.postalCode": "987654321", "billingAddress.town": "updatedCity", "billingAddress.country.isocode": "US"]
		JSON          | JSON           | "{ \"issueNumber\" : \"2\", \"startMonth\" : \"12\", \"startYear\" : \"2007\", \"expiryMonth\" : \"02\", \"subscriptionId\" : \"updatedSubscriptionId\", \"defaultPayment\" : true, \"billingAddress\" : { \"line1\" : \"updatedLine1\", \"line2\" : \"updatedLine2\", \"postalCode\" : \"987654321\", \"town\" : \"updatedCity\", \"country\": { \"isocode\": \"US\" }}}"
		XML           | XML            | "<paymentDetails><issueNumber>2</issueNumber><startMonth>12</startMonth><startYear>2007</startYear><expiryMonth>02</expiryMonth><subscriptionId>updatedSubscriptionId</subscriptionId><defaultPayment>true</defaultPayment><billingAddress><country><isocode>US</isocode></country><postalCode>987654321</postalCode><town>updatedCity</town><line1>updatedLine1</line1><line2>updatedLine2</line2></billingAddress></paymentDetails>"
	}

	def "Customer updates his payment details with invalid fields when request: #requestFormat and response: #responseFormat"() {
		given: "A customer with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, responseFormat)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]

		when: "customer attempts to replace his payment details with missing field"
		HttpResponseDecorator response = restClient.patch(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info1.id,
				body: postBody,
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "customer receives an error"
		with(response) {
			status == SC_BAD_REQUEST
			data.errors.size() > 0
			data.errors[0].type == 'ValidationError'
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | ['startYear': '2020', 'expiryYear': '1999', 'startMonth': '01', 'expiryMonth': '01']
		URLENC        | JSON           | ['startYear': '2020', 'expiryYear': '1999', 'startMonth': '01', 'expiryMonth': '01']
		JSON          | JSON           | '{ "startYear": "2020", "expiryYear": "1999", "startMonth": "01", "expiryMonth": "01" }'
		XML           | XML            | "<paymentDetails><startYear>2020</startYear><expiryYear>1999</expiryYear><startMonth>01</startMonth><expiryMonth>01</expiryMonth></paymentDetails>"
	}

	def "Customer updates not existing payment when request: #requestFormat and response: #responseFormat"() {
		given: "a registered and logged in customer"
		def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

		when: "user attempts to replace his payment details with missing field"
		HttpResponseDecorator response = restClient.patch(
				path: getBasePathWithSite() + "/users/" + customer.id + "/paymentdetails/wrongPaymentId",
				body: postBody,
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "user receives an error"
		with(response) {
			status == SC_BAD_REQUEST
			data.errors.size() > 0
			data.errors[0].type == 'ValidationError'
			data.errors[0].reason == 'unknownIdentifier'
			data.errors[0].subject == 'paymentDetailsId'
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | GOOD_PAYMENT
		URLENC        | JSON           | GOOD_PAYMENT
		JSON          | JSON           | GOOD_PAYMENT_JSON
		XML           | XML            | GOOD_PAYMENT_XML
	}

	def "Customer changes his default payment when request: #requestFormat and response: #responseFormat"() {
		given: "Customer with two payment infos"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, responseFormat)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]
		def extendedInfo1 = getPaymentInfo(restClient, customer1, info1.id, responseFormat)
		def cart1 = customerWithPaymentInfo1[2]
		def info2 = createPaymentInfo(SECOND_PAYMENT_NON_DEFAULT, restClient, customer1, cart1.code, responseFormat)
		def extendedInfo2 = getPaymentInfo(restClient, customer1, info2.id, responseFormat)

		when: "customer wants to change his default payment info"
		HttpResponseDecorator response = restClient.patch(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info2.id,
				body: postBody,
				contentType: responseFormat,
				requestContentType: requestFormat)
		def updatedInfo1 = getPaymentInfo(restClient, customer1, info1.id, responseFormat)
		def updatedInfo2 = getPaymentInfo(restClient, customer1, info2.id, responseFormat)

		then: "his second payment info is marked as default"
		with(response) { status == SC_OK }
		updatedInfo2.id == info2.id
		extendedInfo2.defaultPayment == false
		updatedInfo2.defaultPayment == true
		extendedInfo1.defaultPayment == true
		updatedInfo1.defaultPayment == false

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | ['defaultPaymentInfo': true]
		URLENC        | JSON           | ['defaultPaymentInfo': true]
		JSON          | JSON           | '{ "defaultPayment": true }'
		XML           | XML            | "<paymentDetails><defaultPayment>true</defaultPayment></paymentDetails>"
	}

	def "Customer replaces his payment details when request: #requestFormat and response: #responseFormat"() {
		given: "A customer with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, responseFormat)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]

		when: "customer replaces his payment info"
		HttpResponseDecorator response = restClient.put(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info1.id,
				body: postBody,
				contentType: responseFormat,
				requestContentType: requestFormat)
		def updatedInfo = getPaymentInfo(restClient, customer1, info1.id, responseFormat)

		then: "his changes are successfully saved"
		with(response) { status == SC_OK }

		and: "new values are visible"
		with(updatedInfo) {
			accountHolderName == GOOD_PAYMENT.accountHolderName
			cardNumber.toString().substring(12) == GOOD_PAYMENT.cardNumber.substring(12)
			cardType.code == GOOD_PAYMENT.cardType
			expiryMonth == GOOD_PAYMENT.expiryMonth
			expiryYear == GOOD_PAYMENT.expiryYear
			saved == GOOD_PAYMENT.saved
			defaultPayment == GOOD_PAYMENT.defaultPaymentInfo
			billingAddress.titleCode == GOOD_PAYMENT."billingAddress.titleCode"
			billingAddress.firstName == GOOD_PAYMENT."billingAddress.firstName"
			billingAddress.lastName == GOOD_PAYMENT."billingAddress.lastName"
			billingAddress.line1 == GOOD_PAYMENT."billingAddress.line1"
			billingAddress.line2 == GOOD_PAYMENT."billingAddress.line2"
			billingAddress.postalCode == GOOD_PAYMENT."billingAddress.postalCode"
			billingAddress.town == GOOD_PAYMENT."billingAddress.town"
			billingAddress.region.isocode == GOOD_PAYMENT."billingAddress.region.isocode"
			billingAddress.country.isocode == GOOD_PAYMENT."billingAddress.country.isocode"
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | GOOD_PAYMENT
		URLENC        | JSON           | GOOD_PAYMENT
		JSON          | JSON           | GOOD_PAYMENT_JSON
		XML           | XML            | GOOD_PAYMENT_XML
	}

	def "Customer replaces his payment details with missing requried field when request: #requestFormat and response: #responseFormat"() {
		given: "A customer with payment info"
		def customerWithPaymentInfo1 = createCustomerWithPaymentInfo(restClient, responseFormat)
		def customer1 = customerWithPaymentInfo1[0]
		def info1 = customerWithPaymentInfo1[1]

		when: "user attempts to replace his payment details with missing field"
		HttpResponseDecorator response = restClient.put(
				path: getBasePathWithSite() + "/users/" + customer1.id + "/paymentdetails/" + info1.id,
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
		URLENC        | XML            | BAD_PAYMENT
		URLENC        | JSON           | BAD_PAYMENT
		JSON          | JSON           | BAD_PAYMENT_JSON
		XML           | XML            | BAD_PAYMENT_XML
	}

	def "Customer PUTs not existing payment when request: #requestFormat and response: #responseFormat"() {
		given: "a registered and logged in customer"
		def customer = registerAndAuthorizeCustomer(restClient, responseFormat)

		when: "user attempts to replace his payment details with missing field"
		HttpResponseDecorator response = restClient.put(
				path: getBasePathWithSite() + "/users/" + customer.id + "/paymentdetails/wrongPaymentId",
				body: postBody,
				contentType: responseFormat,
				requestContentType: requestFormat)

		then: "user receives an error"
		with(response) {
			status == SC_BAD_REQUEST
			data.errors.size() > 0
			data.errors[0].type == 'ValidationError'
			data.errors[0].reason == 'unknownIdentifier'
			data.errors[0].subject == 'paymentDetailsId'
		}

		where:
		requestFormat | responseFormat | postBody
		URLENC        | XML            | GOOD_PAYMENT
		URLENC        | JSON           | GOOD_PAYMENT
		JSON          | JSON           | GOOD_PAYMENT_JSON
		XML           | XML            | GOOD_PAYMENT_XML
	}

}
