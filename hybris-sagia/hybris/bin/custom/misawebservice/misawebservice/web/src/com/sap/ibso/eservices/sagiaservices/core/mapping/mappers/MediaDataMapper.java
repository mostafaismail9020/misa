/**
 *
 */
package com.sap.ibso.eservices.sagiaservices.core.mapping.mappers;

import de.hybris.platform.cmsfacades.data.MediaData;
import de.hybris.platform.webservicescommons.mapping.mappers.AbstractCustomMapper;

import com.sap.ibso.eservices.core.sagia.dto.MediaWsDTO;

import ma.glasnost.orika.MappingContext;
/**
 * @author gandrade
 *
 */
public class MediaDataMapper extends AbstractCustomMapper<MediaData, MediaWsDTO>
{

	@Override
	public void mapAtoB(final MediaData a, final MediaWsDTO b, final MappingContext context)
	{

		mapDescriptionAtoB(a, b, context);
		mapUrlAtoB(a, b, context);
		mapDownloadUrlAtoB(a, b, context);
		mapFileNameAtoB(a, b, context);

	}

	/**
	 * @param a
	 * @param b
	 * @param context
	 */
	private void mapFileNameAtoB(final MediaData a, final MediaWsDTO b, final MappingContext context)
	{

		context.beginMappingField("filename", getAType(), a, "filename", getBType(), b);
		try
		{
			if (shouldMap(a, b, context))
			{
				if (a.getFileName() != null)
				{
					b.setFileName(a.getFileName());
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
	private void mapDownloadUrlAtoB(final MediaData a, final MediaWsDTO b, final MappingContext context)
	{

		context.beginMappingField("downloadUrl", getAType(), a, "downloadUrl", getBType(), b);
		try
		{
			if (shouldMap(a, b, context))
			{
				if (a.getDownloadUrl() != null)
				{
					b.setDownloadUrl(a.getDownloadUrl());
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
	private void mapUrlAtoB(final MediaData a, final MediaWsDTO b, final MappingContext context)
	{

		context.beginMappingField("url", getAType(), a, "url", getBType(), b);
		try
		{
			if (shouldMap(a, b, context))
			{
				if (a.getUrl() != null)
				{
					b.setUrl(a.getUrl());
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
	private void mapDescriptionAtoB(final MediaData a, final MediaWsDTO b, final MappingContext context)
	{

		context.beginMappingField("description", getAType(), a, "description", getBType(), b);
		try
		{
			if (shouldMap(a, b, context))
			{
				if (a.getDescription() != null)
				{
					b.setDescription(a.getDescription());
				}
			}
		}
		finally
		{
			context.endMappingField();
		}
	}

	@Override
	public void mapBtoA(final MediaWsDTO b, final MediaData a, final MappingContext context)
	{

		mapDescriptionBtoA(b, a, context);
		mapUrlBtoA(b, a, context);
		mapDownloadUrlBtoA(b, a, context);
		mapFileNameBtoA(b, a, context);

	}

	/**
	 * @param b
	 * @param a
	 * @param context
	 */
	private void mapFileNameBtoA(final MediaWsDTO b, final MediaData a, final MappingContext context)
	{

		context.beginMappingField("filename", getBType(), b, "filename", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getFileName() != null)
			{
				a.setFileName(b.getFileName());
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
	private void mapDownloadUrlBtoA(final MediaWsDTO b, final MediaData a, final MappingContext context)
	{

		context.beginMappingField("downloadUrl", getBType(), b, "downloadUrl", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getDownloadUrl() != null)
			{
				a.setDownloadUrl(b.getDownloadUrl());
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
	private void mapUrlBtoA(final MediaWsDTO b, final MediaData a, final MappingContext context)
	{

		context.beginMappingField("url", getBType(), b, "url", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getUrl() != null)
			{
				a.setUrl(b.getUrl());
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
	private void mapDescriptionBtoA(final MediaWsDTO b, final MediaData a, final MappingContext context)
	{

		context.beginMappingField("description", getBType(), b, "description", getAType(), a);
		try
		{
			if (shouldMap(b, a, context) && b.getDescription() != null)
			{
				a.setDescription(b.getDescription());
			}
		}
		finally
		{
			context.endMappingField();
		}

	}
}
