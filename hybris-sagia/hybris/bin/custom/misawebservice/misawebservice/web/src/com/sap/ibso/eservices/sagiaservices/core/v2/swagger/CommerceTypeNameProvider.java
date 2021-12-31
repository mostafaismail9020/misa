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
package com.sap.ibso.eservices.sagiaservices.core.v2.swagger;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import springfox.documentation.schema.DefaultTypeNameProvider;
import springfox.documentation.swagger.common.SwaggerPluginSupport;


@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER - 10)
public class CommerceTypeNameProvider extends DefaultTypeNameProvider
{
	private static final String TYPE_NAME_SUFFIX = "WsDTO";

	@Override
	public String nameFor(final Class<?> type)
	{
		final ApiModel annotation = AnnotationUtils.findAnnotation(type, ApiModel.class);
		final String defaultTypeName = getDefaultTypeName(type);
		return annotation != null ? StringUtils.defaultIfEmpty(annotation.value(), defaultTypeName) : defaultTypeName;
	}

	private String getDefaultTypeName(final Class<?> type)
	{
		return StringUtils.removeEndIgnoreCase(super.nameFor(type), TYPE_NAME_SUFFIX);
	}
}
