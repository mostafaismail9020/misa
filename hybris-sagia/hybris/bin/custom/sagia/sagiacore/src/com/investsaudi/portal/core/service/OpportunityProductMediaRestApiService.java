package com.investsaudi.portal.core.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.media.MediaModel;

public interface OpportunityProductMediaRestApiService {
	
	byte[] getMediaAttachmentPdf(String url);
	
	MediaModel uploadMediaAttachmentAsProduct(String url, String productCode,CatalogVersionModel catalogVersion);

}
