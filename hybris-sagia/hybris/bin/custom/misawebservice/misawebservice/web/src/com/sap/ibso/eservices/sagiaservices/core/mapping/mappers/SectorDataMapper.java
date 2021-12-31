/**
 *
 */
package com.sap.ibso.eservices.sagiaservices.core.mapping.mappers;

import de.hybris.platform.webservicescommons.mapping.mappers.AbstractCustomMapper;

import com.sap.ibso.eservices.core.sagia.dto.SectorWsDTO;
import com.sap.ibso.eservices.facades.data.SagiaSectorData;

import ma.glasnost.orika.MappingContext;


/**
 * @author gandrade
 *
 */
public class SectorDataMapper extends AbstractCustomMapper<SagiaSectorData, SectorWsDTO>
{

	@Override
	public void mapAtoB(final SagiaSectorData a, final SectorWsDTO b, final MappingContext context)
	{

		mapCodeAtoB(a, b, context);
		mapNameAtoB(a, b, context);
	}

	/**
	 * @param a
	 * @param b
	 * @param context
	 */
	private void mapNameAtoB(final SagiaSectorData a, final SectorWsDTO b, final MappingContext context)
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
	private void mapCodeAtoB(final SagiaSectorData a, final SectorWsDTO b, final MappingContext context)
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
	public void mapBtoA(final SectorWsDTO b, final SagiaSectorData a, final MappingContext context)
	{

		mapCodeBtoA(b, a, context);
		mapNameBtoA(b, a, context);
	}

	/**
	 * @param b
	 * @param a
	 * @param context
	 */
	private void mapNameBtoA(final SectorWsDTO b, final SagiaSectorData a, final MappingContext context)
	{

		context.beginMappingField("name", getBType(), b, "name", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getName() != null)
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
	private void mapCodeBtoA(final SectorWsDTO b, final SagiaSectorData a, final MappingContext context)
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
