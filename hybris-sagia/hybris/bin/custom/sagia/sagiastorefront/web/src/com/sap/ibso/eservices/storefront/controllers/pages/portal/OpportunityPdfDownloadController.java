package com.sap.ibso.eservices.storefront.controllers.pages.portal;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sap.ibso.eservices.storefront.util.SagiaPDFChartGenerator;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.media.MediaService;

import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



@Controller
public class OpportunityPdfDownloadController extends AbstractPageController {
	
	@Resource(name = "productService")
	private ProductService productService;
	
	@Resource(name = "mediaService")
	private MediaService mediaService;
	
	@RequestMapping(value = "/merged-pdf-download/{productCode}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadPDF(@PathVariable String productCode,  
                              final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException, IOException 
    {
		
    	System.out.println("******************************* Portal Product CALLING TARGET METHOD ******************************");
    	System.out.println("productCode from parameter is -------> "+productCode);
		final ProductModel productModel = productService.getProductForCode(productCode);
    	final MediaModel mediaModel = productModel.getDetail().iterator().next();
		
		File secFile = convertMediaToFile(mediaModel);

    	//System.out.println("********************** ################# All parameter names -----> "+request.getParameterNames());
    	//System.out.println("********************** ################# All parameter names to String -----> "+request.getParameterNames().toString());

    	//System.out.println("********************** #################  attribute names  -----> "+request.getAttributeNames());
    	//System.out.println("********************** ################# attribute names to String -----> "+request.getAttributeNames().toString());
    	Enumeration myEnum = request.getParameterNames();
    	Enumeration myEnumAtt = request.getAttributeNames();
//    	while (myEnum.hasMoreElements())
//		{
//    		System.out.println("*********** Inside while param *************");
//			final String paramName = (String) myEnum.nextElement();
//			System.out.println("*************** paramName ************ "+paramName);
//			final String paramValue = request.getParameter(paramName);
//			System.out.println("*************** paramValue ************ "+paramValue);
//		}
//		
//    	while (myEnumAtt.hasMoreElements())
//		{
//    		System.out.println("*********** Inside while att *************");
//			final String paramNameAtt = (String) myEnumAtt.nextElement();
//			System.out.println("*************** paramNameAtt ************ "+paramNameAtt);
//			final String paramValueAtt = request.getParameter(paramNameAtt);
//			System.out.println("*************** paramValueAtt ************ "+paramValueAtt);
//		}
		

    	
    	System.out.println("************************************ CALLING TARGET METHOD ************************************");
    	String fileName = "merged.pdf"; // Name of the PDF file

        File file = SagiaPDFChartGenerator.generatePdfFile(productModel,secFile);
        

//        if (file.exists()) {
//        	System.out.println("************ FILE EXISTS *****************");
//        	response.resetBuffer();
//            response.setContentType("application/pdf");
//            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//
//            try(InputStream in = new FileInputStream(file);
//                    OutputStream out = response.getOutputStream()) {
//            	System.out.println("************* INSIDE TRY *************");
//                      byte[] buffer = new byte[1048];
//                  
//                      int numBytesRead;
//                      while ((numBytesRead = in.read(buffer)) > 0) {
//                          out.write(buffer, 0, numBytesRead);
//                      }
//                      System.out.println("************* BEFORE FLUSH *************");
//                      out.flush();
//                      out.close();
//                  }
//        } else {
//            // Handle file not found scenario
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }
    
        

        if (file.exists()) {
          byte[] fileContent = Files.readAllBytes(file.toPath());
          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_PDF);
          ContentDisposition contentDisposition = ContentDisposition.builder("inline")
                  .filename("Final_File_Name.pdf")
                  .build();
          headers.setContentDisposition(contentDisposition);

          return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    
    
    
    }
    
    public File convertMediaToFile(MediaModel mediaModel) {
        // Get the file data from the MediaModel
        InputStream inputStream = mediaService.getStreamFromMedia(mediaModel);

        // Create a temporary File object
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp_file", ".pdf");

            // Copy the data from the InputStream to the File
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
        } catch (IOException e) {
            // Handle the exception, if any
        }

        return tempFile;
    }


}
