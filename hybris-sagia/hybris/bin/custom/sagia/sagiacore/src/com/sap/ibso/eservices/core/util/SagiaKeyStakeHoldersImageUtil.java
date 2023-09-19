package com.sap.ibso.eservices.core.util;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.sap.ibso.eservices.core.job.SagiaKeyStakeholdersImageGeneratorJob;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

public class SagiaKeyStakeHoldersImageUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(SagiaKeyStakeholdersImageGeneratorJob.class);
	private static final String REAL_FILE_NAME = "keyStakeholders-";
	private static final String SAGIA_PRODUCT_CATALOG = "sagiaProductCatalog";
	private static final String CATALOG_VERSION_STAGED = "Staged";
	private static final String PNG_MIME_TYPE = "application/png";
	private static final String PNG_FILE_TYPE = ".png";

	
	
	final ModelService modelService = (ModelService) Registry.getApplicationContext()
			.getBean("modelService");

	final FlexibleSearchService flexibleSearchService = (FlexibleSearchService) Registry.getApplicationContext()
			.getBean("flexibleSearchService");
	
	final MediaService mediaService = (MediaService) Registry.getApplicationContext()
			.getBean("mediaService");
	
	final CatalogVersionService catalogVersionService = (CatalogVersionService) Registry.getApplicationContext()
			.getBean("catalogVersionService");
	
	
	
	public void generateKeyStakeholdersImages(OpportunityProductModel opportunity)
			throws Exception {
		
		Set<MediaModel> mediaList = new HashSet<>();
		
		for (MediaModel media : opportunity.getKeyStakeholders()) {
			LOG.info("******************* Iterating over getKeyStakeholders *******************");
			try {
				if (StringUtils.isNotBlank(media.getEncodedString())) {
					
					if (isPng(media.getEncodedString())) {
						LOG.info("The encoded string is a PNG image for media --> "+media.getCode());
						byte[] decodedBytes = Base64.decodeBase64(media.getEncodedString());
						System.out.println("******************* Before creating temp file *******************");
						Path tempFile = Files.createTempFile(media.getCode(), PNG_FILE_TYPE);
						try {
							LOG.info("******************* Before writing temp file *******************");
							Files.write(tempFile, decodedBytes);

							// Delete existing media if present
							LOG.info("******************* Before deleting existing media *******************");
							deleteExistingMedia(media.getCode());

							// Create a new media model and save it to the media storage
							LOG.info("******************* Before creating and saving new media *******************");
							MediaModel newMedia = createAndSaveNewMedia(media, tempFile);

							// Import the file into media storage.
							try (InputStream is = Files.newInputStream(tempFile)) {
								System.out.println("******************* Before setting stream *******************");
								mediaService.setStreamForMedia(newMedia, is);
							}

							mediaList.add(newMedia);
						} finally {
							Files.delete(tempFile);
						}
						opportunity.setKeyStakeholders(mediaList);
						LOG.info("Saving of decoded keyStakeholders Media for OpportunityProduct...");
						modelService.save(opportunity);
					}
					
					else
					{
						LOG.info("The encoded string is NOT a PNG image for media --> "+media.getCode());
					}
				}
			} catch (Exception e) {
				LOG.error("Error while processing media for OpportunityProduct with code " + opportunity.getCode(),
						e);
			}
		}
		
	}
	
	private void deleteExistingMedia(String mediaCode) {
		List<MediaModel> existingMediaList = getMedia(mediaCode);

		if (existingMediaList != null) {
			for (MediaModel media : existingMediaList) {
				LOG.info("Removing existing keyStakeholders media with code: {}", mediaCode);
				modelService.remove(media);
			}
		}
	}
	
	public List<MediaModel> getMedia(String mediaCode) {
		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {PK} FROM {Media} WHERE {code} = ?mediaCode");
		query.addQueryParameter("mediaCode", mediaCode);
		LOG.debug("The query is: {}", query.getQuery());

		SearchResult<MediaModel> result = flexibleSearchService.search(query);
		return result.getResult();
	}
	
	private MediaModel createAndSaveNewMedia(MediaModel transientModel, Path tempFile) {
		MediaModel media = modelService.create(MediaModel.class);

		media.setCode(transientModel.getCode());
		media.setRealFileName(REAL_FILE_NAME + getCurrentDateTime() + ".jpg");
		media.setCatalogVersion(catalogVersionService.getCatalogVersion(SAGIA_PRODUCT_CATALOG, CATALOG_VERSION_STAGED));
		media.setMime(PNG_MIME_TYPE);
		media.setIsKeyStakeholderLogo(true);
		System.out.println("******************* Before modelService.save(media) new media *******************");
		modelService.save(media);

		return media;
	}
	
	public static boolean isPng(String encodedString) {
        // Decode the Base64 encoded string
		byte[] decodedBytes = Base64.decodeBase64(encodedString);

        // Check the initial bytes for PNG's magic number
        if (decodedBytes.length > 4) {
            return decodedBytes[0] == (byte) 0x89 &&
                   decodedBytes[1] == (byte) 0x50 &&
                   decodedBytes[2] == (byte) 0x4E &&
                   decodedBytes[3] == (byte) 0x47;
        }
        return false;
    }
	
	public String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmssSSSnnn");
		String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}
	

}
