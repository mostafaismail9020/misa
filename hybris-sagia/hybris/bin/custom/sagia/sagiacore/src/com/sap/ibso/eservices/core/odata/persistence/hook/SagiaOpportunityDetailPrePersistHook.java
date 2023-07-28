package com.sap.ibso.eservices.core.odata.persistence.hook;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;

import com.investsaudi.portal.core.model.OpportunityProductModel;
import com.sap.ibso.eservices.core.model.TransientEncodedPdfModel;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.odata2services.odata.persistence.hook.PrePersistHook;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;

public class SagiaOpportunityDetailPrePersistHook implements PrePersistHook {

	private static final Logger LOG = LoggerFactory.getLogger(SagiaOpportunityDetailPrePersistHook.class);
	private static final String REAL_FILE_NAME = "test-file";
	private static final String PDF_EXTENSION = ".pdf";
	private static final String SAGIA_PRODUCT_CATALOG = "sagiaProductCatalog";
	private static final String CATALOG_VERSION_STAGED = "Staged";
	private static final String PDF_MIME_TYPE = "application/pdf";
	
	@Autowired
	private ModelService modelService;

	@Autowired
	private MediaService mediaService;

	@Autowired
	private CatalogVersionService catalogVersionService;
	
	@Autowired
    private FlexibleSearchService flexibleSearchService;

	@Override
	public Optional<ItemModel> execute(ItemModel item) {
		if (item instanceof OpportunityProductModel) {

			LOG.info(
					"***************** In SagiaOpportunityDetailPrePersistHook persist hook *************");
			OpportunityProductModel opportunity = (OpportunityProductModel) item;

			
			
			if ( Objects.nonNull(opportunity)) {
				LOG.info("***************** Before calling savePdfToProduct *************");
				savePdfToProduct( opportunity);
			}

			// Return the updated opportunity model.
			return Optional.of(opportunity);
		} else {
			// Return the original item if it is not a product model.
			LOG.info("***************** Before returning item object *************");
			return Optional.of(item);
		}
	}

	public void savePdfToProduct(OpportunityProductModel opportunity) {
	    List<MediaModel> mediaList = new ArrayList<>();

	    for (TransientEncodedPdfModel transientModel : opportunity.getTransientEncodendPdf()) {
	        byte[] decodedBytes = Base64.getDecoder().decode(transientModel.getEncodedString());

	        try {
	            LOG.info("Processing PDF data for Transient Model: {}", transientModel.getCode());

	            // Create a temporary file to hold the decoded bytes.
	            Path tempFile = Files.createTempFile(opportunity.getCode(), PDF_EXTENSION);

	            try {
	                Files.write(tempFile, decodedBytes);

	                // Delete existing media if present
	                deleteExistingMedia(transientModel.getCode());

	                // Create a new media model and save it to the media storage
	                MediaModel newMedia = createAndSaveNewMedia(transientModel, tempFile);
	                
	                // Import the file into media storage.
	                try (InputStream is = Files.newInputStream(tempFile)) {
	                    mediaService.setStreamForMedia(newMedia, is);
	                }

	                mediaList.add(newMedia);
	            } finally {
	                Files.delete(tempFile);
	            }
	        } catch (IOException e) {
	            LOG.error("Error while processing PDF data for Transient Model: " + transientModel.getCode(), e);
	        }
	    }

	    opportunity.setDetail(mediaList);
	    modelService.save(opportunity);
	}

	private void deleteExistingMedia(String mediaCode) {
	    List<MediaModel> existingMediaList = getMedia(mediaCode);
	    
	    if (existingMediaList != null) {
	        for(MediaModel media : existingMediaList) {
	            LOG.info("Removing existing media with code: {}", mediaCode);
	            modelService.remove(media);
	        }
	    }
	}

	private MediaModel createAndSaveNewMedia(TransientEncodedPdfModel transientModel, Path tempFile) {
	    MediaModel media = modelService.create(MediaModel.class);

	    media.setCode(transientModel.getCode());
	    media.setRealFileName(REAL_FILE_NAME + ".pdf");
	    media.setCatalogVersion(catalogVersionService.getCatalogVersion(SAGIA_PRODUCT_CATALOG, CATALOG_VERSION_STAGED));
	    media.setMime(PDF_MIME_TYPE);
	    
	    modelService.save(media);
	    
	    return media;
	}


	
	public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmssSSSnnn");
        String formattedDateTime = now.format(formatter);
        LOG.info("Current time is ----> "+formattedDateTime);
		return formattedDateTime;
    }
	
	public List<MediaModel> getMedia(String mediaCode) {
		final FlexibleSearchQuery query = new FlexibleSearchQuery("SELECT {PK} FROM {Media} WHERE {code} = ?mediaCode");
        query.addQueryParameter("mediaCode", mediaCode);
        LOG.info("The query is: {}", query.getQuery());

        SearchResult<MediaModel> result = flexibleSearchService.search(query);
        return result.getResult();
    }

	@SuppressWarnings("deprecation")
	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	@SuppressWarnings("deprecation")
	@Required
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}

}
