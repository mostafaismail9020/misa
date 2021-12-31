/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2019 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package de.hybris.platform.investsaudistore.service.impl;

import java.util.List;

import de.hybris.platform.commerceservices.dataimport.impl.SampleDataImportService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;


/**
 *
 */
public class InvestsaudiSampleDataImportService extends SampleDataImportService
{

	@Override
	public void execute(final AbstractSystemSetup systemSetup, final SystemSetupContext context, final List<ImportData> importData)
	{
		final boolean importSampleData = systemSetup.getBooleanSystemSetupParameter(context, IMPORT_SAMPLE_DATA);

		if (importSampleData)
		{
			for (final ImportData data : importData)
			{
				importAllData(systemSetup, context, data, true);
			}
		}
	}

	@Override
	protected void importProductCatalog(final String extensionName, final String productCatalogName)
	{
		// Load Categories
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/productCatalogs/%sProductCatalog/categories.impex", extensionName,
						productCatalogName), false);

		// Load medias for Categories as Suppliers loads some new Categories
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/productCatalogs/%sProductCatalog/categories-media.impex", extensionName,
						productCatalogName), false);

		// Load Products
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/productCatalogs/%sProductCatalog/products.impex", extensionName,
						productCatalogName), false);
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/productCatalogs/%sProductCatalog/products-media.impex", extensionName,
						productCatalogName), false);

		// Load Products Relations
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/productCatalogs/%sProductCatalog/products-relations.impex", extensionName,
						productCatalogName), false);

		// Load Prices
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/productCatalogs/%sProductCatalog/products-prices.impex", extensionName,
						productCatalogName), false);

		// Load Stock Levels
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/productCatalogs/%sProductCatalog/products-stocklevels.impex", extensionName,
						productCatalogName), false);

	}

	@Override
	protected void importContentCatalog(final String extensionName, final String contentCatalogName)
	{
		if (isResponsive())
		{
			final String responsiveContentFile = String.format(
					"/%s/import/sampledata/contentCatalogs/%sContentCatalog/cms-responsive-content.impex", extensionName,
					contentCatalogName);
			if (getInputStream(responsiveContentFile) != null)
			{
				getSetupImpexService().importImpexFile(responsiveContentFile, false);
			}
			else
			{
				getSetupImpexService().importImpexFile(
						String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/cms-content.impex", extensionName,
								contentCatalogName), false);
			}
		}
		else
		{
			getSetupImpexService().importImpexFile(
					String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/cms-content.impex", extensionName,
							contentCatalogName), false);

			if (getConfigurationService().getConfiguration().getBoolean(IMPORT_MOBILE_DATA, false))
			{
				getSetupImpexService().importImpexFile(
						String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/cms-mobile-content.impex", extensionName,
								contentCatalogName), false);
			}
		}

		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/email-content.impex", extensionName,
						contentCatalogName), false);

	}

	@Override
	protected void importStore(final String extensionName, final String storeName, final String productCatalogName)
	{
		// no Impex to load
	}

	@Override
	protected void importJobs(final String extensionName, final String storeName)
	{
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/stores/%s/jobs.impex", extensionName, storeName), false);
	}

	@Override
	protected void importSolrIndex(final String extensionName, final String storeName)
	{
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/stores/%s/solr.impex", extensionName, storeName), false);

		getSetupSolrIndexerService().createSolrIndexerCronJobs(String.format("%sIndex", storeName));
	}

	
	public void importCommerceOrgData(final SystemSetupContext context)
	{
		final String extensionName = context.getExtensionName();

		getSetupImpexService().importImpexFile(String.format("/%s/import/sampledata/commerceorg/user-groups.impex", extensionName),
				false);
	}
}
