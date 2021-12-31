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
package com.sap.ibso.eservices.sagiaservices.test.test.groovy.webservicetests.addons


import de.hybris.platform.util.Config
import com.sap.ibso.eservices.sagiaservices.test.setup.TestSetupUtils

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite.class)
@Suite.SuiteClasses([ExtendedCartTests.class, AccProductTests.class, ExtendedCustomersTests.class, ExtendedCustomersTests.class])
class AllAccTests {

	@BeforeClass
	public static void setUpClass() {
		if (Config.getBoolean("misawebservicetest.enableAccTest", false)
		&& Config.getBoolean("misawebservicetest.enableV1", false)) {
			TestSetupUtils.loadData();
			TestSetupUtils.startServer();
		}
	}

	@AfterClass
	public static void tearDown(){
		if (Config.getBoolean("misawebservicetest.enableAccTest", false)
		&& Config.getBoolean("misawebservicetest.enableV1", false)) {
			TestSetupUtils.stopServer();
			TestSetupUtils.cleanData();
		}
	}

	@Test
	public static void testing() {
		//dummy test class
	}
}
