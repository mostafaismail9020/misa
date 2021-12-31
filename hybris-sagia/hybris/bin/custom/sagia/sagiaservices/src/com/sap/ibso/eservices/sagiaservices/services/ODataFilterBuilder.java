/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.sagiaservices.services;

import com.sap.ibso.eservices.sagiaservices.utils.ODataEdmConvertor;
import org.apache.olingo.odata2.api.edm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;




/**
 * Tool to build $filter System Query Option.<br>
 * <a href="http://www.odata.org/documentation/odata-version-2-0/uri-conventions/">URI Conventions (OData Version
 * 2.0)</a><br>
 * <b>4.5. Filter System Query Option ($filter)</b>
 */
public class ODataFilterBuilder
{
	protected enum LO
	{
		AND(" and "), EQ(" eq ");

		private final String uriString;

		private LO(final String uriString)
		{
			this.uriString = uriString;
		}
	}

	public class ODataFilterPredicate
	{
		protected final Object literal;
		protected final LO lo;
		protected final ODataFilterPredicateParent parentPredicate;
		protected final String property;

		protected ODataFilterPredicate(final ODataFilterPredicateParent parentPredicate, final String property, final LO lo,
				final Object literal)
		{
			this.parentPredicate = parentPredicate;
			this.property = property;
			this.lo = lo;
			this.literal = literal;
		}

		public ODataFilterProperty and(final String property)
		{
			return new ODataFilterProperty(new ODataFilterPredicateParent(this, LO.AND), property);
		}

		public String toExpression() throws EdmException
		{
			final String parentExpression = this.parentPredicate == null ? "" : this.parentPredicate.toExpression();
			final Collection<?> literalCollection = this.literal instanceof Collection ? (Collection<?>) this.literal
					: Collections.singleton(this.literal);

			final EdmElement element = getElement(this.property);

			final List<String> literalStrings = new ArrayList<>();
			for (final Object value : literalCollection)
			{

				literalStrings.add(convertObjectToString(value, element));
			}

			if (literalStrings.size() == 1)
			{
				return parentExpression + this.property + lo.uriString + literalStrings.iterator().next();
			}
			return parentExpression + literalStrings.stream().map(li -> this.property + lo.uriString + li)
					.collect(Collectors.joining(" or ", "(", ")"));
		}
	}

	protected static class ODataFilterPredicateParent
	{
		protected final ODataFilterPredicate filterPredicate;
		protected final LO lo;

		protected ODataFilterPredicateParent(final ODataFilterPredicate filterPredicate, final LO lo)
		{
			this.filterPredicate = filterPredicate;
			this.lo = lo;
		}

		protected String toExpression() throws EdmException
		{
			return this.filterPredicate.toExpression() + this.lo.uriString;
		}
	}

	public class ODataFilterProperty
	{
		protected final ODataFilterPredicateParent parentPredicate;
		protected final String property;

		protected ODataFilterProperty(final ODataFilterPredicateParent parentPredicate, final String property)
		{
			this.parentPredicate = parentPredicate;
			this.property = property;
		}

		public ODataFilterPredicate eq(final Object literal)
		{
			return new ODataFilterPredicate(parentPredicate, property, LO.EQ, literal);
		}

	}

	private static final Logger LOG = LoggerFactory.getLogger(ODataFilterBuilder.class);

	protected final EdmEntitySet entitySet;
	protected final ODataEdmConvertor oDataEdmConvertor;

	/**
	 * @param entitySet
	 * @param oDataEdmConvertor
	 */
	protected ODataFilterBuilder(final EdmEntitySet entitySet, final ODataEdmConvertor oDataEdmConvertor)
	{
		this.entitySet = entitySet;
		this.oDataEdmConvertor = oDataEdmConvertor;
	}

	protected static ODataFilterBuilder of(final EdmEntitySet entitySet, final ODataEdmConvertor oDataEdmConvertor)
	{
		return new ODataFilterBuilder(entitySet, oDataEdmConvertor);
	}

	protected String convertObjectToString(final Object value, final EdmElement element) throws EdmException
	{
		try
		{
			final EdmSimpleType type = (EdmSimpleType) element.getType();
			final Object newValue = this.oDataEdmConvertor.convertEdm(value, element);
			return type.valueToString(newValue, EdmLiteralKind.URI, element.getFacets());
		}
		catch (final EdmException e)
		{
			LOG.error("Error converting attribute '{}' using value '{}'", element.getName(), value);
			throw e;
		}
	}

	/**
	 * @param propertyNames
	 * @return {@link EdmElement}
	 * @throws EdmException exception
	 */
	protected EdmElement getElement(final String propertyNames) throws EdmException
	{
		final String[] split = propertyNames.split("/");
		EdmStructuralType type = this.entitySet.getEntityType();
		EdmElement element = null;
		for (int i = 0; i < split.length; i++)
		{
			element = (EdmElement) type.getProperty(split[i]);
			if (element == null)
			{
				throw new IllegalStateException(
						"Property '" + split[i] + "' of '" + propertyNames + "' was not found on EdmStructuralType " + type.getName());
			}
			if (i + 1 < split.length)
			{
				if (!(element.getType() instanceof EdmStructuralType))
				{
					throw new IllegalStateException(
							"Property '" + split[i] + "' of '" + propertyNames + "' is not a EdmStructuralType in " + type.getName()
									+ ", because it is a " + element.getType().getClass().getSimpleName());
				}
				type = (EdmStructuralType) element.getType();
			}
		}
		return element;
	}

	/**
	 * on
	 * @param property property
	 * @return
	 */
	public ODataFilterProperty on(final String property)
	{
		return new ODataFilterProperty(null, property);
	}

}
