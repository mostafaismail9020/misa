package com.sap.ibso.eservices.storefront.controllers.pages.portal;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sap.ibso.eservices.storefront.util.SagiaPDFChartGenerator;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.media.MediaService;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Controller
public class OpportunityPdfDownloadController extends AbstractPageController {
	
	@Resource(name = "productService")
	private ProductService productService;
	
	@Resource(name = "mediaService")
	private MediaService mediaService;
	
	

	@RequestMapping(value = "/merged-pdf-download/{productCode}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> downloadPDF(@PathVariable String productCode, final HttpServletRequest request,
	                                          final HttpServletResponse response) throws IOException {

	    final Logger LOG = LoggerFactory.getLogger(this.getClass());
	    ProductModel productModel;
	    MediaModel mediaModel;

	    LOG.info("Processing request for opportunityCode: {}", productCode);

	    try {
	        productModel = productService.getProductForCode(productCode);
	        mediaModel = productModel.getDetail().iterator().next();
	    } catch (Exception e) {
	        LOG.error("Opportunity retrieval failed for opportunity code: {}", productCode, e);
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    File secFile = convertMediaToFile(mediaModel);
	    File file = SagiaPDFChartGenerator.generatePdfFile(productModel, secFile);

	    if (file.exists()) {
	        try {
	            byte[] fileContent = Files.readAllBytes(file.toPath());
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.APPLICATION_PDF);
	            ContentDisposition contentDisposition = ContentDisposition.builder("inline")
	                    .filename("Final_File_Name.pdf")
	                    .build();
	            headers.setContentDisposition(contentDisposition);

	            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	        } catch (IOException e) {
	            LOG.error("Error occurred while processing the PDF file", e);
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } else {
	        LOG.warn("File does not exist");
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

    
	

	public File convertMediaToFile(MediaModel mediaModel) throws IOException {

	    final Logger LOG = LoggerFactory.getLogger(this.getClass());

	    // Get the file data from the MediaModel
	    InputStream inputStream = mediaService.getStreamFromMedia(mediaModel);

	    // Create a temporary File object
	    File tempFile;

	    try {
	        tempFile = File.createTempFile("temp_file", ".pdf");
	        
	        // Copy the data from the InputStream to the File
	        FileUtils.copyInputStreamToFile(inputStream, tempFile);

	    } catch (IOException e) {
	        // Handle the exception, if any
	        LOG.error("Error while converting MediaModel to file", e);
	        throw e;
	    }

	    return tempFile;
	}



}
