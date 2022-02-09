package com.investsaudi.portal.core.service.impl;

import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.catalog.CatalogService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.util.Config;

import com.investsaudi.portal.core.service.InvestSaudiProductService;
import com.investsaudi.portal.core.service.OpportunityProductMediaRestApiService;
import com.investsaudi.portal.core.model.OpportunityProductModel;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.UUID;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class OpportunityProductMediaRestApiServiceImpl implements OpportunityProductMediaRestApiService {
	
	private static final Logger LOG = Logger.getLogger(OpportunityProductMediaRestApiServiceImpl.class);
	
	private static final String SAGIA_CONTENT_CATALOG = "sagiaContentCatalog";
	private static final String ONLINE_CATALOG_VERSION = "Online";
	private static final String PDF_FILENAME_SUFFIX = ".pdf";
	private static final String OPPORTUNITY_MEDIA_REST_API_USERNAME = Config.getString("opportunitymedia.restapi.username", "naveenp");
	private static final String OPPORTUNITY_MEDIA_REST_API_PASSWORD = Config.getString("opportunitymedia.restapi.password", "Anjala@28");
	
	private MediaService mediaService;
	
	private ModelService modelService;
	
	@Resource
    private InvestSaudiProductService investSaudiProductService;

	@Resource
	private CMSSiteService cmsSiteService;

	@Resource
	private CatalogService catalogService;
	
	@Override
    public byte[] getMediaAttachmentPdf(String url) {
		
		byte[] content = new byte[1024];
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_PDF, MediaType.APPLICATION_OCTET_STREAM));
		requestHeaders.setBasicAuth(OPPORTUNITY_MEDIA_REST_API_USERNAME,OPPORTUNITY_MEDIA_REST_API_PASSWORD);
		HttpEntity<String> entity = new HttpEntity<>(requestHeaders);
		ResponseEntity<byte[]> result = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
		content = result.getBody();
		return content;
		
    }
	
	@Override
    public MediaModel uploadMediaAttachmentAsProduct(String url, String productCode) {
		
		final MediaModel mediaModel = new MediaModel();
		if(null != url) {
			LOG.info("url: "+url);
				byte[] bytes = getMediaAttachmentPdf(url);
				if (null != bytes) {
				        final InputStream inputStream = new ByteArrayInputStream(bytes);
				        final String currentCatalogVersion = cmsSiteService.getCurrentSite().getContentCatalogs().get(0).getId();
				 		final CatalogVersionModel catalogVersionModel = catalogService.getCatalogVersion(SAGIA_CONTENT_CATALOG, ONLINE_CATALOG_VERSION);
				 		mediaModel.setCode(productCode+UUID.randomUUID().toString());
				 		mediaModel.setCatalogVersion(catalogVersionModel);
				 		mediaModel.setRealFileName(productCode+PDF_FILENAME_SUFFIX);
				 		getModelService().saveAll(mediaModel);
				 		
						getMediaService().setStreamForMedia(mediaModel, inputStream);
                        
				 		try
				 		{
				 			inputStream.close();
				 		}catch (final IOException ex)
				 		{
				 			LOG.error(ex.getMessage());
				 			ex.printStackTrace();
				 		}
				 		return mediaModel; 
				}
		}
		return mediaModel;
	}
	
	/**
	 * @return the mediaService
	 */
	public MediaService getMediaService() {
		return mediaService;
	}

	/**
	 * @param mediaService the mediaService to set
	 */
	public void setMediaService(MediaService mediaService) {
		this.mediaService = mediaService;
	}
	
	/**
	 * @return the modelService
	 */
	public ModelService getModelService() {
		return modelService;
	}

	/**
	 * @param modelService the modelService to set
	 */
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

}
