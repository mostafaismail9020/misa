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
package com.sap.ibso.eservices.sagiaservices.core.xstream;

import de.hybris.bootstrap.annotations.UnitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;


/**
 *
 */
@UnitTest
public class JsonXStreamFactoryTest
{
	private JsonXStreamFactory factory;

	@Before
	public void prepare() throws Exception
	{
		factory = new JsonXStreamFactory();
		factory.afterPropertiesSet();
	}

	@Test
	public void testMapperOverridenJson() throws Exception
	{
		final Object streamObject = factory.getObject();

		Assert.assertTrue(streamObject instanceof XStream);

		final XStream stream = (XStream) streamObject;

		Assert.assertEquals("testData", stream.getMapper().aliasForSystemAttribute("testData"));
		Assert.assertEquals(null, stream.getMapper().aliasForSystemAttribute("class"));
	}
}
