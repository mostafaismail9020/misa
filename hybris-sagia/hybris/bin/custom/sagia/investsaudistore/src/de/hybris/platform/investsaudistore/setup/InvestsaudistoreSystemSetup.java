/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.investsaudistore.setup;

import static de.hybris.platform.investsaudistore.constants.InvestsaudistoreConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.commerceservices.setup.events.CoreDataImportedEvent;
import de.hybris.platform.commerceservices.setup.events.SampleDataImportedEvent;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.investsaudistore.constants.InvestsaudistoreConstants;
import de.hybris.platform.investsaudistore.service.InvestsaudistoreService;
import de.hybris.platform.investsaudistore.service.impl.InvestsaudiCoreDataImportService;
import de.hybris.platform.investsaudistore.service.impl.InvestsaudiSampleDataImportService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


@SystemSetup(extension = InvestsaudistoreConstants.EXTENSIONNAME)
public class InvestsaudistoreSystemSetup extends AbstractSystemSetup
{
	public static final String INVESTSAUDI = "investsaudi";

	private static final String IMPORT_CORE_DATA = "importCoreData";
	private static final String IMPORT_SAMPLE_DATA = "importSampleData";
	private static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";

	private InvestsaudiCoreDataImportService investsaudiCoreDataImportService;
	private InvestsaudiSampleDataImportService investsaudiSampleDataImportService;

	private final InvestsaudistoreService investsaudistoreService;

	public InvestsaudistoreSystemSetup(final InvestsaudistoreService investsaudistoreService)
	{
		this.investsaudistoreService = investsaudistoreService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		investsaudistoreService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return InvestsaudistoreSystemSetup.class.getResourceAsStream("/investsaudistore/sap-hybris-platform.png");
	}

	@SystemSetupParameterMethod
	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(IMPORT_CORE_DATA, "Import Core Data", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_SAMPLE_DATA, "Import Sample Data", true));
		params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", true));

		return params;
	}

	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		final List<ImportData> importData = new ArrayList<ImportData>();

		final ImportData investsaudiImportData = new ImportData();
		investsaudiImportData.setProductCatalogName(INVESTSAUDI);
		investsaudiImportData.setContentCatalogNames(Arrays.asList(INVESTSAUDI));
		investsaudiImportData.setStoreNames(Arrays.asList(INVESTSAUDI));
		importData.add(investsaudiImportData);

		getInvestsaudiCoreDataImportService().execute(this, context, importData);
		getEventService().publishEvent(new CoreDataImportedEvent(context, importData));

		getInvestsaudiSampleDataImportService().execute(this, context, importData);
		getInvestsaudiSampleDataImportService().importCommerceOrgData(context);
		getEventService().publishEvent(new SampleDataImportedEvent(context, importData));
	}

	public InvestsaudiCoreDataImportService getInvestsaudiCoreDataImportService()
	{
		return investsaudiCoreDataImportService;
	}

	@Required
	public void setInvestsaudiCoreDataImportService(final InvestsaudiCoreDataImportService investsaudiCoreDataImportService)
	{
		this.investsaudiCoreDataImportService = investsaudiCoreDataImportService;
	}


	public InvestsaudiSampleDataImportService getInvestsaudiSampleDataImportService()
	{
		return investsaudiSampleDataImportService;
	}

	@Required
	public void setInvestsaudiSampleDataImportService(final InvestsaudiSampleDataImportService investsaudiSampleDataImportService)
	{
		this.investsaudiSampleDataImportService = investsaudiSampleDataImportService;
	}

}
