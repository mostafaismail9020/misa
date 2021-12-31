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
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v1
import de.hybris.bootstrap.annotations.ManualTest
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.DocumentationSuiteGuard
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.TestUtil
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.docu.SaveWSOutputStrategy
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.docu.TestUtilCustomDelegatingMetaClass
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.markers.AvoidCollectingOutputFromTest
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.markers.CollectOutputFromTest
import org.codehaus.groovy.runtime.InvokerHelper
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.experimental.categories.Categories
import org.junit.experimental.categories.Categories.ExcludeCategory
import org.junit.experimental.categories.Categories.IncludeCategory
import org.junit.runner.RunWith
/**
 * Suite contains all tests from {@link com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.v1.AllTests} suite, including test from {@link CollectOutputFromTest} category
 * and excluding test from {@link AvoidCollectingOutputFromTest} category. Suite log responses from webservices
 */
@RunWith(Categories.class)
@IncludeCategory(CollectOutputFromTest.class)
@ExcludeCategory(AvoidCollectingOutputFromTest.class)
@ManualTest
class DocumentationSuite extends AllTests{

	private static MetaClass defaulttestUtilMetaClass = null;

	@BeforeClass
	public static void beforeClass() {
		DocumentationSuiteGuard.setSuiteRunning(true);
		defaulttestUtilMetaClass = TestUtil.getMetaClass();
		def customMetaClass = new TestUtilCustomDelegatingMetaClass(TestUtil.class)
		InvokerHelper.metaRegistry.setMetaClass(TestUtil.class, customMetaClass)
		cleanOutputDir();
	}

	@AfterClass
	public static void afterClass() {
        DocumentationSuiteGuard.setSuiteRunning(false);
		InvokerHelper.metaRegistry.setMetaClass(TestUtil.class, defaulttestUtilMetaClass);
	}

	private static void cleanOutputDir() {
		File dir = new File(SaveWSOutputStrategy.WS_OUTPUT_DIR)
		dir.deleteDir();
	}
}
