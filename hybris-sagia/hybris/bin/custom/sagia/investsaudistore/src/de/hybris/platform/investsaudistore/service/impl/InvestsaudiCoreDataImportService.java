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

import de.hybris.platform.commerceservices.dataimport.impl.CoreDataImportService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.validation.services.ValidationService;


/**
 *
 */
public class InvestsaudiCoreDataImportService extends CoreDataImportService
{
	
	@Override
	public void execute(final AbstractSystemSetup systemSetup, final SystemSetupContext context, final List<ImportData> importData)
	{
		final boolean importCoreData = systemSetup.getBooleanSystemSetupParameter(context, IMPORT_CORE_DATA);

		if (importCoreData)
		{
			for (final ImportData data : importData)
			{
				importAllData(systemSetup, context, data, false);
			}

			systemSetup.logInfo(context, String.format("Begin importing cms-content-custom.impex"));
            getSetupImpexService().importImpexFile(String.format("/%s/import/coredata/contentCatalogs/investsaudiContentCatalog/cms-content-custom.impex", context.getExtensionName()),
                            true);
            
			final ValidationService validation = getBeanForName("validationService");
			validation.reloadValidationEngine();
		}
	}
	
	@Override
	protected void importStore(final String extensionName, final String storeName, final String productCatalogName)
	{
		super.importStore(extensionName, storeName, productCatalogName);
		
		if (getConfigurationService().getConfiguration().getBoolean("setup.siteoverride", false))
		{
			getSetupImpexService().importImpexFile(
					String.format("/%s/import/coredata/stores/%s/site-override.impex", extensionName, storeName), false);
		}
	}

}
