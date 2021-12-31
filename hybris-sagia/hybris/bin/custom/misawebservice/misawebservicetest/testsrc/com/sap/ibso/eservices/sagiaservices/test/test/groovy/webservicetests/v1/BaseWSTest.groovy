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
import static org.junit.Assert.fail

import de.hybris.bootstrap.annotations.ManualTest
import de.hybris.platform.util.Config
import com.sap.ibso.eservices.sagiaservices.test.setup.TestSetupUtils
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.TestNamePrinter
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.TestUtil
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.config.TestConfigFactory
import com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.docu.BaseWSTestWatcher

import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule

/**
 * Base class for all groovy webservice tests
 */
@ManualTest
class BaseWSTest {
	private static final ThreadLocal<Boolean> SERVER_NEEDS_SHUTDOWN = new ThreadLocal<Boolean>();
	protected final TestUtil testUtil;
	protected final ConfigObject config;

	@BeforeClass
	public static void startServerIfNeeded(){
		if (!TestSetupUtils.isServerStarted()){
			SERVER_NEEDS_SHUTDOWN.set(true);
			TestSetupUtils.startServer();
		}
	}

	@AfterClass
	public static void stopServerIfNeeded(){
		if (SERVER_NEEDS_SHUTDOWN.get()){
			TestSetupUtils.stopServer();
			SERVER_NEEDS_SHUTDOWN.set(false);
		}
	}

	@Before
	public void ignoreIf(){
		org.junit.Assume.assumeTrue(Config.getBoolean("misawebservicetest.enableV1", false))
	}

	@Rule
	public TestNamePrinter tnp = new TestNamePrinter(System.out);

	protected BaseWSTest() {
		config = TestConfigFactory.createConfig("v1", "/misawebservicetest/groovytests-property-file.groovy");
		testUtil = new TestUtil(config);
	}

	/**
	 * Ancillary method to mark that some webservice resources is not tested
	 * @param resource
	 */
	protected void missingTest(String resource) {
		fail("Missing test for resource :" + resource);
	}

	@Rule
	public BaseWSTestWatcher testWatcher = new BaseWSTestWatcher();
}