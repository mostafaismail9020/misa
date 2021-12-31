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
package com.sap.ibso.eservices.initialdata.setup;

import com.sap.ibso.eservices.initialdata.constants.SagiaInitialDataConstants;
import de.hybris.platform.commerceservices.dataimport.impl.CoreDataImportService;
import de.hybris.platform.commerceservices.dataimport.impl.SampleDataImportService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.commerceservices.setup.events.CoreDataImportedEvent;
import de.hybris.platform.commerceservices.setup.events.SampleDataImportedEvent;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.util.Config;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class provides hooks into the system's initialization and update processes.
 */
@SystemSetup(extension = SagiaInitialDataConstants.EXTENSIONNAME)
public class InitialDataSystemSetup extends AbstractSystemSetup {
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(InitialDataSystemSetup.class);

    private static final String IMPORT_CORE_DATA = "importCoreData";
    private static final String IMPORT_SAMPLE_DATA = "importSampleData";
    private static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";

    private static final String SAGIA = "sagia";
    private static final String SAGIA_EXTENSION_NAME = "sagiainitialdata";

    private CoreDataImportService coreDataImportService;
    private SampleDataImportService sampleDataImportService;

    /**
     * Generates the Dropdown and Multi-select boxes for the project data import
     */
    @Override
    @SystemSetupParameterMethod
    public List<SystemSetupParameter> getInitializationOptions() {
        final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

        params.add(createBooleanSystemSetupParameter(IMPORT_CORE_DATA, "Import Core Data", true));
        params.add(createBooleanSystemSetupParameter(IMPORT_SAMPLE_DATA, "Import Sample Data", true));
        params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", true));
        // Add more Parameters here as you require

        return params;
    }

    /**
     * Implement this method to create initial objects. This method will be called by system creator during
     * initialization and system update. Be sure that this method can be called repeatedly.
     *
     * @param context the context provides the selected parameters and values
     */
    @SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
    public void createEssentialData(final SystemSetupContext context) {
        // Add Essential Data here as you require
    }

    /**
     * Implement this method to create data that is used in your project. This method will be called during the system
     * initialization. <br>
     * Add import data for each site you have configured
     *
     * <pre>
     * final List<ImportData> importData = new ArrayList<ImportData>();
     *
     * final ImportData sampleImportData = new ImportData();
     * sampleImportData.setProductCatalogName(SAMPLE_PRODUCT_CATALOG_NAME);
     * sampleImportData.setContentCatalogNames(Arrays.asList(SAMPLE_CONTENT_CATALOG_NAME));
     * sampleImportData.setStoreNames(Arrays.asList(SAMPLE_STORE_NAME));
     * importData.add(sampleImportData);
     *
     * getCoreDataImportService().execute(this, context, importData);
     * getEventService().publishEvent(new CoreDataImportedEvent(context, importData));
     *
     * getSampleDataImportService().execute(this, context, importData);
     * getEventService().publishEvent(new SampleDataImportedEvent(context, importData));
     * </pre>
     *
     * @param context the context provides the selected parameters and values
     */
    @SystemSetup(type = Type.PROJECT, process = Process.ALL)
    public void createProjectData(final SystemSetupContext context) {
        /*
         * Add import data for each site you have configured
         */
        final List<ImportData> importData = new ArrayList<ImportData>();
        final ImportData sampleImportData = new ImportData();
        sampleImportData.setProductCatalogName(SAGIA);
        sampleImportData.setContentCatalogNames(Arrays.asList(SAGIA));
        sampleImportData.setStoreNames(Arrays.asList(SAGIA));
        importData.add(sampleImportData);
        getCoreDataImportService().execute(this, context, importData);
        getEventService().publishEvent(new CoreDataImportedEvent(context, importData));
        getSampleDataImportService().execute(this, context, importData);
        getEventService().publishEvent(new SampleDataImportedEvent(context, importData));
        this.importSagiaImpexes();
    }


    public void importSagiaImpexes() {
        String environment = Config.getString("sagia.environment", "local");

        getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-countries.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-media.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-sectors.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);
		getSetupImpexService().importImpexFile(
				String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-availability.impex",
						SAGIA_EXTENSION_NAME, SAGIA), false);
		getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-services.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-service-types.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-status.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);

        //environment dependant
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/projectdata/%s/sagia-configuration.impex",
                        SAGIA_EXTENSION_NAME, environment), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/projectdata/%s/foreign-investors.impex",
                        SAGIA_EXTENSION_NAME, environment), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/projectdata/%s/sap-integration.impex",
                        SAGIA_EXTENSION_NAME, environment), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/projectdata/%s/site.impex",
                        SAGIA_EXTENSION_NAME, environment), false);

        getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagiaGovtServices.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/projectdata/sap-formats.impex", SAGIA_EXTENSION_NAME), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/sampledata/contentCatalogs/%sContentCatalog/sagia-classifications.impex",
                        SAGIA_EXTENSION_NAME, SAGIA), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/projectdata/jobs.impex", SAGIA_EXTENSION_NAME), false);
        getSetupImpexService().importImpexFile(
                String.format("/%s/import/projectdata/%s/tutorial.impex",
                        SAGIA_EXTENSION_NAME, environment), false);
    }

    public CoreDataImportService getCoreDataImportService() {
        return coreDataImportService;
    }

    @Required
    public void setCoreDataImportService(final CoreDataImportService coreDataImportService) {
        this.coreDataImportService = coreDataImportService;
    }

    public SampleDataImportService getSampleDataImportService() {
        return sampleDataImportService;
    }

    @Required
    public void setSampleDataImportService(final SampleDataImportService sampleDataImportService) {
        this.sampleDataImportService = sampleDataImportService;
    }
}
