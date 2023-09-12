package com.sap.ibso.eservices.core.job;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

public class SagiakeyStakeholdersImageDecoderJob extends AbstractJobPerformable<CronJobModel> {

	private static final Logger LOG = LoggerFactory.getLogger(SagiakeyStakeholdersImageDecoderJob.class);
	private static final String REAL_FILE_NAME = "keyStakeholders-";
	private static final String SAGIA_PRODUCT_CATALOG = "sagiaProductCatalog";
	private static final String CATALOG_VERSION_STAGED = "Staged";
	private static final String JPG_MIME_TYPE = "application/png";

	private FlexibleSearchService flexibleSearchService;
	private ModelService modelService;
	private MediaService mediaService;
	private CatalogVersionService catalogVersionService;

	

	@Override
	public PerformResult perform(CronJobModel cronjob) {
		LOG.info("Starting the process of converting encoded Media for OpportunityProduct...");

		
		final FlexibleSearchQuery queryOpportunity = new FlexibleSearchQuery("select {op.pk} from {Media as m},{OpportunityProduct as op} where  {op.systemOrigin} = 'C4C'");

		Set<MediaModel> mediaList = new HashSet<>();

		SearchResult<OpportunityProductModel> searchResult = flexibleSearchService.search(queryOpportunity);
		for (OpportunityProductModel product : searchResult.getResult()) {
			for (MediaModel media : product.getKeyStakeholders()) {
				try {
					if (StringUtils.isNotBlank(media.getEncodedString())) {
						byte[] decodedBytes = Base64.decodeBase64(media.getEncodedString());
						Path tempFile = Files.createTempFile(media.getCode(), JPG_MIME_TYPE);
						try {
							Files.write(tempFile, decodedBytes);

							// Delete existing media if present
							deleteExistingMedia(media.getCode());

							// Create a new media model and save it to the media storage
							MediaModel newMedia = createAndSaveNewMedia(media, tempFile);

							// Import the file into media storage.
							try (InputStream is = Files.newInputStream(tempFile)) {
								mediaService.setStreamForMedia(newMedia, is);
							}

							mediaList.add(newMedia);
						} finally {
							Files.delete(tempFile);
						}

						product.setKeyStakeholders(mediaList);
						LOG.info("Saving of decoded keyStakeholders Media for OpportunityProduct...");
						modelService.save(product);
					}
				} catch (Exception e) {
					LOG.error("Error while processing media for OpportunityProduct with code " + product.getCode(),
							e.getMessage());
				}
			}
		}
		LOG.info("Finished processing encoded Media for OpportunityProduct.");

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
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
		media.setMime(JPG_MIME_TYPE);

		modelService.save(media);

		return media;
	}

	public String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmssSSSnnn");
		String formattedDateTime = now.format(formatter);
		return formattedDateTime;
	}

	public CatalogVersionService getCatalogVersionService() {
		return catalogVersionService;
	}

	public void setCatalogVersionService(CatalogVersionService catalogVersionService) {
		this.catalogVersionService = catalogVersionService;
	}

	public MediaService getMediaService() {
		return mediaService;
	}

	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}
	
	@Required
	public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
		this.flexibleSearchService = flexibleSearchService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

}
