/*
 * Copyright (c) 2020 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.sagia.investsaudi.portal.core.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.util.Config;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sagia.investsaudi.portal.core.constants.SagiainvestsaudistoreConstants;
import com.sagia.investsaudi.portal.core.service.SagiainvestsaudistoreService;



@SystemSetup(extension = SagiainvestsaudistoreConstants.EXTENSIONNAME)
public class SagiainvestsaudistoreSystemSetup extends AbstractSystemSetup
{
	public static final Logger log = LoggerFactory.getLogger(SagiainvestsaudistoreSystemSetup.class);

	public static final String IMPORT_ACCESS_RIGHTS = "accessRights";

	private static final String IMPORT_CORE_DATA = "importCoreData";
	private static final String IMPORT_SAMPLE_DATA = "importSampleData";

	private static final String SAGIA = "sagia";
	private static final String CORE_DATA = "Core Data";
	private static final String COMMON_DATA = "Common data";
	private static final String SAMPLE_DATA = "Sample Data";
	private static final String PRODUCTION_DATA = "Production Data";
	private static final String PATCHES_DATA = "Patches Data";
	private static final String REDESIGN_DATA = "Redesign Data";
	private static final String IMPORT = "import";
	private static final String IMPORT_DATA_TYPE = "Data type";

	private static final String SAGIAINVESTSAUDISTORE_IMPORT = "\\sagiainvestsaudistore\\import";
	private static final String BACK_SLASH = "/";
	private static final String FORWARD_SLASH = "\\\\";

	private final SagiainvestsaudistoreService sagiainvestsaudistoreService;

	public SagiainvestsaudistoreSystemSetup(final SagiainvestsaudistoreService sagiainvestsaudistoreService)
	{
		this.sagiainvestsaudistoreService = sagiainvestsaudistoreService;
	}

	/*
	 * @SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL) public void
	 * createEssentialData() { sagiainvestsaudistoreService.createLogo(PLATFORM_LOGO_CODE); }
	 */

	private InputStream getImageStream()
	{
		return SagiainvestsaudistoreSystemSetup.class.getResourceAsStream("/sagiainvestsaudistore/sap-hybris-platform.png");
	}

	/**
	 * This method will be called by system creator during initialization and system update. Be sure that this method can
	 * be called repeatedly.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		/*
		 * importImpexFile(context, "/sagiainvestsaudistore/import/common/essential-data.impex"); importImpexFile(context,
		 * "/sagiainvestsaudistore/import/common/countries.impex"); importImpexFile(context,
		 * "/sagiainvestsaudistore/import/common/delivery-modes.impex");
		 *
		 * importImpexFile(context, "/sagiainvestsaudistore/import/common/themes.impex"); importImpexFile(context,
		 * "/sagiainvestsaudistore/import/common/user-groups.impex"); importImpexFile(context,
		 * "/sagiainvestsaudistore/import/common/cronjobs.impex");
		 */
	}

	/**
	 * Generates the Dropdown and Multi-select boxes for the project data import
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createMultiSelectSystemSetupParameter(SAGIA, IMPORT_DATA_TYPE, false));

		return params;
	}

	/**
	 * Creates main parameters: Core, Sample
	 *
	 * @param key
	 *           the key of the param
	 * @param label
	 *           the label of the param
	 * @return main parameters: Core, Sample
	 */
	private SystemSetupParameter createMultiSelectSystemSetupParameter(final String key, final String label,
			final boolean defaultValue)
	{
		final SystemSetupParameter syncProductsParam = new SystemSetupParameter(key);
		syncProductsParam.setMultiSelect(true);
		syncProductsParam.setLabel(label);
		syncProductsParam.addValue(CORE_DATA, defaultValue);

//		if (Config.getBoolean("portal.enable.sample.data", true))
//		{
//			syncProductsParam.addValue(SAMPLE_DATA, defaultValue);
//		}

//		if (Config.getBoolean("portal.enable.production.data", true))
//		{
//			syncProductsParam.addValue(PRODUCTION_DATA, defaultValue);
//		}

		if (Config.getBoolean("portal.enable.patches.data", true))
		{
			syncProductsParam.addValue(PATCHES_DATA, defaultValue);
		}
		
		if (Config.getBoolean("portal.enable.patches.data", true))
		{
			syncProductsParam.addValue(REDESIGN_DATA, defaultValue);
		}

		return syncProductsParam;
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
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = Type.PROJECT, process = Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		final String[] selectedParameters = getMultiSelectSetupParameter(context, SAGIA);

		if (ArrayUtils.contains(selectedParameters, CORE_DATA))
		{
			importImpexData(context, "/sagiainvestsaudistore/import/coredata/");
		}
//		if (ArrayUtils.contains(selectedParameters, SAMPLE_DATA))
//		{
//			importImpexData(context, "/sagiainvestsaudistore/import/sampledata/");
//		}
//		if (ArrayUtils.contains(selectedParameters, PRODUCTION_DATA))
//		{
//			importImpexData(context, "/sagiainvestsaudistore/import/productiondata/");
//		}
		if (ArrayUtils.contains(selectedParameters, PATCHES_DATA))
		{
			importImpexData(context, "/sagiainvestsaudistore/import/patches/");
		}
		if (ArrayUtils.contains(selectedParameters, REDESIGN_DATA))
		{
			importImpexData(context, "/sagiainvestsaudistore/import/redesign/");
		}

		synchronizeContentCatalog(context, "sagia", true);
		synchronizeProductCatalog(context, "sagia", true);
	}

	protected boolean synchronizeContentCatalog(final SystemSetupContext context, final String catalogName, final boolean sync)
	{
		logInfo(context, "Begin synchronizing Content Catalog [" + catalogName + "ContentCatalog]");

		createContentCatalogSyncJob(context, catalogName + "ContentCatalog");

		return synchronizeCatalog(context, catalogName + "ContentCatalog", sync);
	}

	protected boolean synchronizeProductCatalog(final SystemSetupContext context, final String catalogName, final boolean sync)
	{
		logInfo(context, "Begin synchronizing Product Catalog [" + catalogName + "ProductCatalog]");

		createProductCatalogSyncJob(context, catalogName + "ProductCatalog");

		return synchronizeCatalog(context, catalogName + "ProductCatalog", sync);
	}

	protected boolean synchronizeCatalog(final SystemSetupContext context, final String catalogName, final boolean sync)
	{
		boolean result = true;

		if (sync)
		{
			final PerformResult syncCronJobResult = executeCatalogSyncJob(context, catalogName);
			if (isSyncRerunNeeded(syncCronJobResult))
			{
				logInfo(context, "Catalog [" + catalogName + "] sync has issues.");
				result = false;
			}
		}

		logInfo(context, "Done synchronizing Catalog [" + catalogName + "]");
		return result;
	}

	private String[] getMultiSelectSetupParameter(final SystemSetupContext context, final String key)
	{
		return context.getParameters(context.getExtensionName() + "_" + key);
	}

	/**
	 * Imports all impex files under the roolUrl. Checks if common is checked, then imports rootUrl/common also.
	 *
	 * @param context
	 *           the context
	 * @param rootUrl
	 *           the root url to import
	 */
	private void importImpexData(final SystemSetupContext context, final String rootUrl)
	{
		URL impexRootPathURL = null;

		//Go to common data folder path.
		impexRootPathURL = this.getClass().getResource(rootUrl);
		if (impexRootPathURL != null)
		{
			doProcessFilesAndDirs(context, getFilesDirectory(impexRootPathURL));
		}
	}

	/**
	 * Sorts all files and folders under dir. Imports first all files. Imports then recursively all files in sub folders
	 *
	 * @param context
	 *           the context
	 * @param rootDir
	 *           the directory to import
	 */
	private void doProcessFilesAndDirs(final SystemSetupContext context, final File rootDir)
	{
		//Sort all files
		final File[] listFiles = rootDir.listFiles();
		Arrays.sort(listFiles, NameFileComparator.NAME_COMPARATOR);

		final List<File> subDirs = new ArrayList<>();
		for (final File file : listFiles)
		{
			//if it is a folder...collect it, if its not the countries folder
			if (file.isDirectory())
			{
				subDirs.add(file);
			}
			//if it is impex file...
			else if (file.isFile() && StringUtils.endsWithIgnoreCase(file.getName(), "impex"))
			{
				logInfo(context, "Importing: " + StringUtils.substringAfter(file.getPath(), "/sagiainvestsaudistore" + "/import"));
				importImpex(context, file); //start importing this impex file.
			}
		}
		if (subDirs.size() > 0)
		{
			for (final File subDir : subDirs)
			{
				doProcessFilesAndDirs(context, subDir);
			}
		}
	}

	/**
	 * Imports an impex file.
	 *
	 * @param context
	 * @param file, This is a impex file,containing absloute path . Splitting this absolute path at "import" substring and
	 *           preparing relative path from resource classpath.
	 */
	private void importImpex(final SystemSetupContext context, final File file)
	{
		final String[] splitPath = file.toString().split(IMPORT);
		final String impexPath = SAGIAINVESTSAUDISTORE_IMPORT.concat(splitPath[1]).replaceAll(FORWARD_SLASH, BACK_SLASH);
		importImpexFile(context, impexPath);
	}

	/**
	 * Returns a File object
	 *
	 * @param impexRootPathURL
	 *           the context
	 * @return file and folder if available at this URI path
	 */
	private File getFilesDirectory(final URL impexRootPathURL)
	{
		File dir = null;
		try
		{
			dir = new File(impexRootPathURL.toURI());
		}
		catch (final URISyntaxException e)
		{
			log.error("This is not a correct uri syntax for a file or folder path", e);
		}
		return dir;
	}

	protected List<String> getExtensionNames()
	{
		return Registry.getCurrentTenant().getTenantSpecificExtensionNames();
	}
}
