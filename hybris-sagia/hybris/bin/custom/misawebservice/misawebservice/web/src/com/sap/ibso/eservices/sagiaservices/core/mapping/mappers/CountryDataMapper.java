/**
 *
 */
package com.sap.ibso.eservices.sagiaservices.core.mapping.mappers;

import de.hybris.platform.webservicescommons.mapping.mappers.AbstractCustomMapper;

import com.sap.ibso.eservices.core.sagia.dto.CountryWsDTO;
import com.sap.ibso.eservices.facades.data.SagiaCountryData;

import ma.glasnost.orika.MappingContext;


/**
 * @author gandrade
 *
 */
public class CountryDataMapper extends AbstractCustomMapper<SagiaCountryData, CountryWsDTO>
{

	@Override
	public void mapAtoB(final SagiaCountryData a, final CountryWsDTO b, final MappingContext context)
	{

		mapCodeAtoB(a, b, context);
		mapNameAtoB(a, b, context);
		mapPrefixAtoB(a, b, context);
	}

	/**
	 * @param a
	 * @param b
	 * @param context
	 */
	private void mapPrefixAtoB(final SagiaCountryData a, final CountryWsDTO b, final MappingContext context)
	{

		context.beginMappingField("phoneprefix", getAType(), a, "prefix", getBType(), b);
		try
		{
			if (shouldMap(a, b, context))
			{
				if (a.getPhoneprefix() != null)
				{
					b.setPrefix(a.getPhoneprefix());
				}
			}
		}
		finally
		{
			context.endMappingField();
		}


	}

	/**
	 * @param a
	 * @param b
	 * @param context
	 */
	private void mapNameAtoB(final SagiaCountryData a, final CountryWsDTO b, final MappingContext context)
	{

		context.beginMappingField("name", getAType(), a, "name", getBType(), b);
		try
		{
			if (shouldMap(a, b, context))
			{
				if (a.getName() != null)
				{
					b.setName(a.getName());
				}
			}
		}
		finally
		{
			context.endMappingField();
		}

	}

	/**
	 * @param a
	 * @param b
	 * @param context
	 */
	private void mapCodeAtoB(final SagiaCountryData a, final CountryWsDTO b, final MappingContext context)
	{


		context.beginMappingField("code", getAType(), a, "code", getBType(), b);
		try
		{
			if (shouldMap(a, b, context))
			{
				if (a.getCode() != null)
				{
					b.setCode(a.getCode());
				}
			}
		}
		finally
		{
			context.endMappingField();
		}


	}

	@Override
	public void mapBtoA(final CountryWsDTO b, final SagiaCountryData a, final MappingContext context)
	{

		mapCodeBtoA(b, a, context);
		mapNameBtoA(b, a, context);
		mapPrefixBtoA(b, a, context);

	}

	/**
	 * @param b
	 * @param a
	 * @param context
	 */
	private void mapPrefixBtoA(final CountryWsDTO b, final SagiaCountryData a, final MappingContext context)
	{

		context.beginMappingField("prefix", getBType(), b, "phoneprefix", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getCode() != null)
			{
				a.setPhoneprefix(b.getPrefix());
			}
		}
		finally
		{
			context.endMappingField();
		}

	}

	/**
	 * @param b
	 * @param a
	 * @param context
	 */
	private void mapNameBtoA(final CountryWsDTO b, final SagiaCountryData a, final MappingContext context)
	{

		context.beginMappingField("name", getBType(), b, "name", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getCode() != null)
			{
				a.setName(b.getName());
			}
		}
		finally
		{
			context.endMappingField();
		}

	}

	/**
	 * @param b
	 * @param a
	 * @param context
	 */
	private void mapCodeBtoA(final CountryWsDTO b, final SagiaCountryData a, final MappingContext context)
	{

		context.beginMappingField("code", getBType(), b, "code", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getCode() != null)
			{
				a.setCode(b.getCode());
			}
		}
		finally
		{
			context.endMappingField();
		}

	}
}
